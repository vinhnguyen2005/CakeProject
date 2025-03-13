document.addEventListener("DOMContentLoaded", function () {
    const filterDropdown = document.getElementById("statusFilter");
    const table = $('#dataTables-example').DataTable();

    filterDropdown.addEventListener("change", function () {
        const selectedCategory = this.value;
        console.log("Selected Status:", selectedCategory);

        if (selectedCategory === "") {
            table.search("").columns().search("").draw();
        } else {
            console.log("Applying filter:", selectedCategory);
            table.column(5).search("^" + selectedCategory + "$", true, false).draw();
        }
    });
});
