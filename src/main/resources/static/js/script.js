let basePrice = 0;

document.addEventListener("DOMContentLoaded", function() {
    let activeButton = document.querySelector(".size-option.active");

    if (activeButton) {
        updatePrice(activeButton);
    } else {
        let priceElement = document.getElementById('displayprice');
        if (priceElement) {
            let priceText = priceElement.textContent.replace(/\./g, '').replace('đ', '').trim();
            basePrice = Number(priceText);

            updateTotalPrice();
        }
    }
});

function updatePrice(button) {
    let rawPrice = button.value.replace(/\./g, "").replace("đ", "").trim();
    basePrice = Number(rawPrice);

    if (isNaN(basePrice) || basePrice <= 0) {
        console.error("Invalid price detected:", rawPrice);
        return;
    }

    console.log("Updated Base Price:", basePrice);
    let selectedSize = button.textContent.trim();

    document.getElementById('displayprice').innerHTML = formatPrice(basePrice * getQuantity()) + "đ";

    document.getElementById("sizeInput").value = selectedSize;
    document.getElementById("sizeInput2").value = selectedSize;

    document.getElementById("unitPriceInput").value = basePrice;
    document.getElementById("unitPriceInput2").value = basePrice;

    document.getElementById("priceInput").value = basePrice * getQuantity();
    document.getElementById("priceInput2").value = basePrice * getQuantity();

    document.querySelectorAll('.size-option').forEach(btn => btn.classList.remove("active"));
    button.classList.add("active");

    updateTotalPrice();
}

function getQuantity() {
    return parseInt(document.getElementById('quantity').value) || 1;
}

function updateTotalPrice() {
    if (isNaN(basePrice) || basePrice <= 0) {
        document.getElementById('displayprice').innerHTML = "0đ";
        document.getElementById("priceInput").value = "0";
        document.getElementById("priceInput2").value = "0";
        return;
    }

    let totalPrice = basePrice * getQuantity();
    document.getElementById('displayprice').innerHTML = formatPrice(totalPrice) + "đ";
    document.getElementById("priceInput").value = totalPrice;
    document.getElementById("priceInput2").value = totalPrice;
}

document.querySelector(".increment").addEventListener("click", function() {
    let quantityInput = document.getElementById('quantity');
    let quantity = getQuantity() + 1;

    quantityInput.value = quantity;
    document.getElementById("quantityInput").value = quantity;
    document.getElementById("quantityInput2").value = quantity;

    updateTotalPrice();
});

document.querySelector(".decrement").addEventListener("click", function() {
    let quantityInput = document.getElementById('quantity');
    let quantity = getQuantity();

    if (quantity > 1) {
        quantityInput.value = quantity - 1;
        document.getElementById("quantityInput").value = quantity - 1;
        document.getElementById("quantityInput2").value = quantity - 1;

        updateTotalPrice();
    }
});

function formatPrice(price) {
    if (price <= 0) return "0đ";
    return price.toLocaleString("vi-VN").replace(/,/g, ".");
}
