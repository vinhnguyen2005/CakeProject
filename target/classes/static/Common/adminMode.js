document.addEventListener("keydown", function(event){
    if (event.shiftKey && (event.key === "V" || event.key === "v")) {
        alert("Admin mode activated! Not an Admin? Please contact: 0915336966.");
        window.location.href = "/daibacbakery/admin/login";
    }
});