// cart-summary.js

document.addEventListener('DOMContentLoaded', function() {
    const checkboxes = document.querySelectorAll('input[name="selectedItems"]');

    function updateTotals() {
        let totalItemCount = 0;
        let totalProductPrice = 0;
        let totalDiscount = 0;
        let maxDeliveryFee = 0;
        let totalPoints = 0;

        checkboxes.forEach(cb => {
            if (cb.checked) {
                const stock = parseInt(cb.dataset.cartStock, 10);
                const price = parseInt(cb.dataset.proPrice, 10);
                const sale = parseInt(cb.dataset.proSale, 10);
                const points = parseInt(cb.dataset.proPoint, 10);
                const deliveryFee = parseInt(cb.dataset.proDeliveryFee, 10);

                totalItemCount += stock;
                const discountedPrice = price - (price * sale / 100);
                totalProductPrice += price * stock;
                totalDiscount += discountedPrice * stock;
                if (deliveryFee > maxDeliveryFee) maxDeliveryFee = deliveryFee;
                totalPoints += points * stock;
            }
        });

        const totalOrderAmount = totalProductPrice - totalDiscount + maxDeliveryFee;

        document.getElementById('total-item-count').textContent = totalItemCount;
        document.getElementById('total-product-price').textContent = totalProductPrice.toLocaleString() + '원';
        document.getElementById('total-discount').textContent = totalDiscount.toLocaleString() + '원';
        document.getElementById('total-delivery-fee').textContent = maxDeliveryFee.toLocaleString() + '원';
        document.getElementById('total-points').textContent = totalPoints.toLocaleString() + '원';
        document.getElementById('total-order-amount').textContent = totalOrderAmount.toLocaleString() + '원';
    }

    checkboxes.forEach(cb => cb.addEventListener('change', updateTotals));
    
    // 초기 로드 시 계산
    updateTotals();
});
