function saveSearch() {
    $.ajax({
        type: "POST",
        url: "/save",
        success: onSuccessSave,
        data: JSON.stringify(searchResult),
        contentType: "application/json"
    });
}

function onSuccessSave(result){
    $("#saveSearch").remove();
    $("#searchContainer").prepend("<p class='my-2 my-sm-0'>Save succeeded! (id: " + result + ")</p>");
}

function deleteSearch(id){
    $.ajax({
        type: "POST",
        url: "/delete",
        success: onSuccessDelete,
        data: JSON.stringify(id),
        contentType: "application/json"
    });
}

function onSuccessDelete(result){
    $("#search_" + result).remove();

    var message = "<p id='deletedInfo' class='my-2 my-sm-0'>Delete succeeded! (id: " + result + ")</p>";
    if(!$("#deletedInfo")[0]) {
        $("#savedSearchesContainer").prepend(message);
    }
    else
    {
        $("#deletedInfo")[0].innerHTML = message;
    }
}

function details(id){
    $("#savedSearchesContainer").css("display", "none");
    $("#savedSearchesDetails_" + id).css("display", "");
}

function back(id){
    $("#savedSearchesDetails_" + id).css("display", "none");
    $("#savedSearchesContainer").css("display", "");
}