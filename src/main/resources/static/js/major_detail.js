$(document).ready(function () {
    $(".detail_div .major_info").each(function () {
        height = $(this).height();
        if (height > 80) {
            $(this).css("height", "80");
            $(this).after("<a class=\"more-con\" style='font-size: 1.2em' >查看更多 &raquo</a>");
        }
    });
    $(".more-con").click(function () {
        $(this).parent().children(".major_info").css("height", "auto");
        $(this).css("display", "none");
    });
});
$('#go_top_btn').click(function () {
    $('html,body').animate({scrollTop: '0px'}, 1000);
    return false;
});

function video_up(id, count) {
    $.post("/video/back/" + id, {type: 'up'}, function (result) {
        layer.msg('点赞成功');
        now = id+'up';
        $('.video_ul .small_item .video_feedback button').each(function () {
            value = $(this).val();
            if (value == now) {
                $(this).addClass('disabled');
                $(this).prop('disabled', true);
                $('#'+now).html('<span aria-hidden="true" class="glyphicon glyphicon-thumbs-up"></span><span>'+(count+1)+'</span>')
            }
        })
    });
}

function video_down(id, count) {
    $.post("/video/back/" + id, {type: 'down'}, function (result) {
        layer.msg('踩成功');
        now = id+'down';
        $('.video_ul .small_item .video_feedback button').each(function () {
            value = $(this).val();
            if (value == now) {
                $(this).addClass('disabled');
                $(this).prop('disabled', true);
                $('#'+now).html('<span aria-hidden="true" class="glyphicon glyphicon-thumbs-down"></span><span>'+(count+1)+'</span>')
            }
        })
    });
}
function clickVideo(id, count) {
    $.post("/video/back/" + id, {type: 'click'}, function (result) {
        now = id+'click';
        $('.video_ul .small_item .video_feedback button').each(function () {
            value = $(this).val();
            if (value == now) {
                console.log('匹配上了，开始更新点击数量:'+(count+1));
                $('#'+now).html('<span aria-hidden="true" class="glyphicon glyphicon-eye-open"></span><span>'+(count+1)+'</span>')
            }
        })
    })
}
function video_need(id) {
    $.post("/video/need/" + id, function (result) {
        $('.btn-video-need').addClass('disabled');
        $('.btn-video-need').prop('disabled', true);
        $('.btn-video-need').text('其他'+result.data+'个小伙伴也想看');
        layer.msg('知道啦~我会尽快找的，记得回来看哟~');
    })
}