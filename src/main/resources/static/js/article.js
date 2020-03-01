function searchArticles(type) {
    var title = $('#search-title').val();
    var param = {
        "name": title,
        "page": 1,
        "size":10
    };
    /*$('#articles').load('/articles/search/'+type, param, function (data) {
        console.log(data);
    })*/
    $.get('/articles/search/'+type, param, function (data) {
        console.log(data);
        $('#articles').html(data);
    })
}