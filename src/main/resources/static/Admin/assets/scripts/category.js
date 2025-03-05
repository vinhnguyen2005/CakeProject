document.addEventListener("DOMContentLoaded", function() {
    const addButtons = document.querySelectorAll(".btn-primary");
    const addsubcategoryform = document.getElementById("addsubcategory-form");
    addButtons.forEach((button) => {
        button.addEventListener("click", (e) => {
            e.preventDefault();

            addsubcategoryform.style.display = "block";
        })
    })
});