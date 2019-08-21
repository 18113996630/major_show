function querySubjectDesc(majorId, subjectId) {
    $.ajax({
        url: "/subject/" + subjectId,
        type: "GET",
        success: function (data) {
            $(".major_ul .search_major_li button").each(function(){
                maj_id = $(this).val();
                if (majorId == maj_id) {
                    $(this).after("<div class=\"sub-desc\" style='font-size: 1.2em'>"+data.data.description+"</div>");
                    $(this).css("display","none");
                }
            });
        }
    });
}