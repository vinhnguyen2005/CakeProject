document.addEventListener("DOMContentLoaded", () => {
    formatPrices();
    setupQuantityControls();
    updateGrandTotal();

});

function formatPrices() {
    document.querySelectorAll(".total-price, .price").forEach(priceElement => {
        let rawText = priceElement.textContent.trim();
        console.log("Raw Price Text:", rawText);

        let cleanedText = rawText.replace(/[^\d]/g, "");
        console.log("Cleaned Text:", cleanedText);

        let price = parseFloat(cleanedText);
        console.log("Parsed Price:", price);

        price/=10;

        let formattedPrice = formatPrice(price) + " đ";
        console.log("Formatted Price:", formattedPrice);

        priceElement.textContent = formattedPrice;
    });
}




function setupQuantityControls() {
    document.querySelectorAll(".quantity-controls").forEach(container => {
        let decrementBtn = container.querySelector(".decrement");
        let incrementBtn = container.querySelector(".increment");
        let quantityInput = container.querySelector("input");
        let row = container.closest("tr");
        let priceElement = row.querySelector(".price");
        let totalPriceElement = row.querySelector(".total-price");
        console.log(priceElement.textContent);

        decrementBtn.addEventListener("click", function () {
            let quantity = parseInt(quantityInput.value);
            if (quantity > 1) {
                quantity--;
                quantityInput.value = quantity.toString();
                updateTotalPrice(quantity, priceElement, totalPriceElement);
                document.getElementById("update-cart-button").style.display = "block";
            }
        });

        incrementBtn.addEventListener("click", function () {
            let quantity = parseInt(quantityInput.value);
            quantity++;
            quantityInput.value = quantity.toString();
            updateTotalPrice(quantity, priceElement, totalPriceElement);
            document.getElementById("update-cart-button").style.display = "block";
        });
    });
}


function updateTotalPrice(quantity, priceElement, totalPriceElement) {
    let price = parseFloat(priceElement.textContent.replace(/\./g, "").replace("đ", "").trim());
    let totalPrice = quantity * price;

    totalPriceElement.textContent = formatPrice(totalPrice) + " đ";
    updateGrandTotal();
}

function formatPrice(price) {
    return price.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

function updateGrandTotal() {
    let total = 0;
    document.querySelectorAll(".total-price").forEach(priceElement => {
        let price = parseFloat(priceElement.textContent.replace(/\./g, "").replace("đ", "").trim());
        total += price
    })
    document.querySelector("#grand-total").textContent = formatPrice(total) + " đ";
    document.getElementById("total-order-price-hidden").value = total;
}

function viewOrder(){
    window.location.href = "/daibacbakery/checkout/confirm";
}

function continueShopping(){
    window.location.href = "/daibacbakery/home";
}





