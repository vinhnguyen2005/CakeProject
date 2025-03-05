let basePrice = 0;

document.addEventListener("DOMContentLoaded", function() {
    let activeButton = document.querySelector(".size-option.active");
    if (activeButton) {
        updatePrice(activeButton);
    }
});


function updatePrice(button) {
    basePrice = parseFloat(button.value.replace(/\./g, '').replace('đ', '')) || 0;
    let selectedSize = button.textContent.trim();

    document.getElementById('displayprice').innerHTML = formatPrice(basePrice * getQuantity()) + "đ";
    document.getElementById("sizeInput").value = selectedSize;
    document.getElementById("sizeInput2").value = selectedSize;
    document.getElementById("priceInput").value = basePrice * getQuantity();


    document.querySelectorAll('.size-option').forEach(btn => btn.classList.remove("active"));
    button.classList.add("active");
    updateTotalPrice();
}


function getQuantity(){
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

document.querySelector(".increment").addEventListener("click", function(){
    let quantityInput = document.getElementById('quantity');
    let quantity = getQuantity() + 1;

    quantityInput.value = quantity;
    document.getElementById("quantityInput").value = quantity;
    document.getElementById("quantityInput2").value = quantity;

    updateTotalPrice();
});

document.querySelector(".decrement").addEventListener("click", function(){
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
    return price ? price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".") : "0";
}
