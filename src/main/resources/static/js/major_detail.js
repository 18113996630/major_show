$(document).ready(function () {
    $(".detail_div .major_info").each(function(){
        height=$(this).height();
        if(height>80) {
            $(this).css("height","80");
            $(this).after("<a class=\"more-con\" style='font-size: 1.2em' >查看更多 &raquo</a>");
        }
    });
    $(".more-con").click(function(){
        $(this).parent().children(".major_info").css("height","auto");
        $(this).css("display","none");
    });
});
$('#go_top_btn').click(function () {
    $('html,body').animate({ scrollTop: '0px' }, 1000); return false;
});