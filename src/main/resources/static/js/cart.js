document.addEventListener("DOMContentLoaded", () => {
    formatPrices();
    setupQuantityControls();
});

function formatPrices() {
    document.querySelectorAll(".total-price, .price").forEach(priceElement => {
        let rawText = priceElement.textContent.trim();
        console.log("Raw Price Text:", rawText);

        let cleanedText = rawText.replace(/[^\d]/g, ""); 
        console.log("Cleaned Text:", cleanedText);

        let price = parseFloat(cleanedText);
        console.log("Parsed Price:", price);

        if (price > 1000000) {
            console.warn("Price seems too high, dividing by 10:", price);
            price /= 10;
        }

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
            }
        });

        incrementBtn.addEventListener("click", function () {
            let quantity = parseInt(quantityInput.value);
            quantity++;
            quantityInput.value = quantity.toString();
            updateTotalPrice(quantity, priceElement, totalPriceElement);
        });
    });
}


function updateTotalPrice(quantity, priceElement, totalPriceElement) {
    let price = parseFloat(priceElement.textContent.replace(/\./g, "").replace("đ", "").trim());
    let totalPrice = quantity * price;
    totalPriceElement.textContent = formatPrice(totalPrice) + " đ";
}

function formatPrice(price) {
    return price.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}
