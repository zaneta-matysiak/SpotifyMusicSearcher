function saveSearch() {
    $.ajax({
        type: "POST",
        url: "/search-saved",
        success: onSuccess,
        data: JSON.stringify(searchResult),
        contentType: "application/json"
    });
}

function onSuccess(){
    alert("saved");
}