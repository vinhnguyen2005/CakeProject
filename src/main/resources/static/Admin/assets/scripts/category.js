function showSubcategoryForm(categoryId) {
    document.getElementById("maincategoryid").value = categoryId;
    sessionStorage.setItem("maincategoryid", categoryId);
    document.getElementById("addsubcategory-form").style.display = "block";
}

function hideSubcategoryForm() {
    document.getElementById("addsubcategory-form").style.display = "none";
}

function viewTable(){
    const maincategoryId = sessionStorage.getItem("maincategoryid");


    window.location.href = "/daibacbakery/admin/category/subcategory-list?id=" + maincategoryId;
}

function closeSuccessOverlay(){
    document.getElementById("successOverlay").style.display = "none";
}

function closeFailOverlay(){
    document.getElementById("failOverlay").style.display = "none";
}
