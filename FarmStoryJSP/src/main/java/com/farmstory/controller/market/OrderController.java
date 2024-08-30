package com.farmstory.controller.market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmstory.dto.CartDto;
import com.farmstory.dto.OrderDto;
import com.farmstory.dto.ProductDto;
import com.farmstory.dto.UserDto;
import com.farmstory.service.CartService;
import com.farmstory.service.OrderService;
import com.farmstory.service.ProductService;
import com.farmstory.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/market/order.do")
public class OrderController extends HttpServlet{

	private static final long serialVersionUID = -5384513430322205886L;
	
	private UserService userservice = UserService.INSTANCE;
	private ProductService productservice = ProductService.INSTANCE;
	private CartService cartservice = CartService.INSTANCE;
	private OrderService orderService = OrderService.INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		String uid = ((UserDto)session.getAttribute("sessUser")).getUserId();
		// 선택된 상품 정보를 저장할 리스트
		List<ProductDto> products = new ArrayList<>();
	    List<CartDto> carts = new ArrayList<>();
		
		//어떤 주문 인지 확인 하기 위한 정보 가져오기
		String ordercheck = req.getParameter("ordercheck");
		//view 에서 바로구매 버튼을 눌렀을때 동작
		if(ordercheck.equals("1")) {
			String stock = req.getParameter("stock");
			String proNo = req.getParameter("no");
			ProductDto product = productservice.selectProduct(proNo);
			product.setCartstock(stock);
			products.add(product);
		}
		
		//장바구니에서 구매 버튼을 눌렀을때만 동작
		else if(ordercheck.equals("2")) {
			// 선택된 상품의 ID 리스트
			String[] prono = req.getParameter("ids").split(",");
			
		    for (String id : prono) {
		    	carts.add(cartservice.selectUserCartForPay(uid, id));
		    }
		    
		}
		req.setAttribute("products", products);
		req.setAttribute("carts", carts);
		
		
		//아이디만을 활용해서 유저 정보를 가져오기
		UserDto userdto = userservice.selectUser(uid);
		req.setAttribute("userdto", userdto);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/market/order.jsp");
        dispatcher.forward(req, resp);
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청의 Body에서 JSON 데이터 읽기
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();

        // JSON을 Java 객체로 변환
        Type listType = new TypeToken<List<Map<String, String>>>(){}.getType();
        List<Map<String, String>> datas = gson.fromJson(reader, listType);
        
        
     // 확인을 위해 데이터 출력
        for (Map<String, String> data : datas) {
            String prono = data.get("prono");
            String stock = data.get("stock");
            System.out.println("Prono: " + prono + ", Stock: " + stock);
        }
        
        HttpSession session = req.getSession();
        String uid = ((UserDto)session.getAttribute("sessUser")).getUserId();
		int result = 0;
        for(Map<String, String> data : datas) {
        	OrderDto dto = new OrderDto();
        	dto.setOrderprodno(data.get("prono"));
        	dto.setOrderuid(uid);
        	dto.setOrderstock(data.get("stock"));
            logger.debug("prono : " + data.get("prono"));
            logger.debug("uid : " + uid);
            logger.debug("stock : " + data.get("stock"));
        	result += orderService.insertOrder(dto);
        	cartservice.deleteCart(data.get("prono"), uid);
        }
        logger.debug("result : " + result);
        boolean success = result>0;
        
        JsonObject json = new JsonObject();
		json.addProperty("success", success);
		
		PrintWriter printWriter = resp.getWriter();
		printWriter.print(json);
        
        
	}
}
