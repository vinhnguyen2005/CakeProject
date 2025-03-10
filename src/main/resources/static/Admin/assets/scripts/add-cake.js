document.addEventListener("DOMContentLoaded", function () {
    const categorySelect = document.getElementById("categorySelect");
    const cakeIdContainer = document.getElementById("cakeIdContainer");
    const prefixSelect = document.getElementById("prefixSelect");
    const cakeNumber = document.getElementById("cakeNumber");

    function updatePrefixOptions(){
        prefixSelect.innerText = "";
        // Convert selected categories from dropdown and then we collect their id to turn it into a list of id
        const selectedCategories = Array.from(categorySelect.selectedOptions).map(option => option.value);
        if(selectedCategories.length > 0){
            cakeIdContainer.style.display = "block";
            // From the array of categoryid now we take that id and add it to the prefix options array in here
            selectedCategories.forEach(categoryID => {
                const option = document.createElement("option");
                option.value = categoryID;
                option.text = categoryID;
                prefixSelect.appendChild(option);
            });

            // We automatically set the default value is the first category admin choose
            if(selectedCategories.length > 0){
                prefixSelect.selectedIndex = 0;
            }
        } else {
            cakeIdContainer.style.display = "none";
        }
    }

    cakeNumber.addEventListener("input", function () {
        this.value = this.value.replace(/\D/g, "").substring(0, 4);
    });

    categorySelect.addEventListener("change", updatePrefixOptions);
});

document.addEventListener("DOMContentLoaded", function () {
    const filterDropdown = document.getElementById("categoryFilter");
    const table = $('#dataTables-example').DataTable();

    filterDropdown.addEventListener("change", function () {
        const selectedCategory = this.value;

        if (selectedCategory === "") {
            table.search("").columns().search("").draw();
        } else {
            table.column(2).search(selectedCategory, false, true).draw();
        }
    });
});


function viewAddTable(){
    window.location.href = "/daibacbakery/admin/cake/manage";
}

