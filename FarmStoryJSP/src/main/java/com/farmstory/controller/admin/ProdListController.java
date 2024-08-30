package com.farmstory.controller.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.PageGroupDto;
import com.farmstory.dto.ProductDto;
import com.farmstory.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/product/list.do"})
public class ProdListController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ProductService service = ProductService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pg = req.getParameter("page");

		int current = service.getCurrentPage(pg);
		int start = service.getStartNum(current);
		int total = service.selectCountTotal();
		int lastPage = service.getLastPageNum(total);
		PageGroupDto pageGroup = service.getCurrentPageGroup(current);
		
		logger.debug("total : "+total);
		logger.debug("current : "+current);
		logger.debug("last : "+lastPage);
		
		List<ProductDto> products = service.selectProducts(start);
		req.setAttribute("Products", products);
		req.setAttribute("Current", current);
		req.setAttribute("PageGroup", pageGroup);
		req.setAttribute("LastPage", lastPage);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/list.jsp");
        dispatcher.forward(req, resp);
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 본문에서 JSON 데이터 읽기
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }
        // JSON 문자열을 String 배열로 변환
		Gson gson = new Gson();
		Type listType = new TypeToken<List<String>>(){}.getType();
        List<String> ids = gson.fromJson(requestBody.toString(), listType);


		// 총 삭제된 수를 저장할 변수
        int totalDeleted = 0;

        // 각 ID에 대해 삭제 작업 수행
        for (String id : ids) {
        	try {
                totalDeleted += service.deleteProduct(id);
                System.out.println(totalDeleted);
            } catch (Exception e) {
                logger.error("Failed to delete product with ID: " + id, e);
                // 에러가 발생한 ID는 계속 진행하되, 에러를 기록
            }
        }
        logger.debug("totalDeleted : " + totalDeleted);
        // 전체 삭제 성공 여부 판단
        boolean success = totalDeleted == ids.size();

        // 응답 객체 생성
        JsonObject json = new JsonObject();
        json.addProperty("success", success);

        // 응답 설정 및 전송
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = resp.getWriter()) {
        	writer.print(json.toString());
        	writer.flush();
        }
	}
}
