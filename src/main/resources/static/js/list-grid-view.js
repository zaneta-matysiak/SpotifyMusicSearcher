function saveSearch() {
    $.ajax({
        type: "POST",
        url: "/search-saved",
        success: onSuccess,
        data: JSON.stringify(searchResult),
        contentType: "application/json"
    });
}

function onSuccess(result){
    $("#saveSearch").remove();
    $("#searchContainer").prepend("<p class='my-2 my-sm-0'>Save succeeded! (id: " + result + ")</p>");
}