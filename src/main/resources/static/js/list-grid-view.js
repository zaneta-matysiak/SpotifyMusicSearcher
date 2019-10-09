function saveSearch() {
    console.log("save search");
    $.ajax({
        type: "GET",
        url: "/search-saved",
        success: onSuccess
    });
}

function onSuccess(){
    alert("saved");
}