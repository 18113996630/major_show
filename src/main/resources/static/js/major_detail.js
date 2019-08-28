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
        now = id + 'up';
        $('.video_ul .small_item .video_feedback button').each(function () {
            value = $(this).val();
            if (value == now) {
                $(this).addClass('disabled');
                $(this).prop('disabled', true);
                $('#' + now).html('<span aria-hidden="true" class="glyphicon glyphicon-thumbs-up"></span><span>' + (count + 1) + '</span>')
            }
        })
    });
}

function video_down(id, count) {
    $.post("/video/back/" + id, {type: 'down'}, function (result) {
        layer.msg('踩成功');
        now = id + 'down';
        $('.video_ul .small_item .video_feedback button').each(function () {
            value = $(this).val();
            if (value == now) {
                $(this).addClass('disabled');
                $(this).prop('disabled', true);
                $('#' + now).html('<span aria-hidden="true" class="glyphicon glyphicon-thumbs-down"></span><span>' + (count + 1) + '</span>')
            }
        })
    });
}

function clickVideo(id, count) {
    $.post("/video/back/" + id, {type: 'click'}, function (result) {
        now = id + 'click';
        $('.video_ul .small_item .video_feedback button').each(function () {
            value = $(this).val();
            if (value == now) {
                console.log('匹配上了，开始更新点击数量:' + (count + 1));
                $('#' + now).html('<span aria-hidden="true" class="glyphicon glyphicon-eye-open"></span><span>' + (count + 1) + '</span>')
            }
        })
    })
}

function video_need(id) {
    $.post("/video/need/" + id, function (result) {
        btn = $(".btn-video-need");
        btn.addClass('disabled');
        btn.prop('disabled', true);
        btn.text('其他' + result.data + '个小伙伴也想看');
        layer.msg('知道啦~我会尽快找的，记得回来看哟~');
    })
}

function comment() {
    if ($('#content').val() == '') {
        layer.msg('评论内容不能为空哦~')
    } else {
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            contentType: "application/json;charset=UTF-8",
            url: "/comment",//url
            // data: {content: $('#content').val(), majorDetailId:$('#majorDetailId').val()},
            data: formSerializeJson('comment-form'),
            success: function (result) {
                if (result.code === 200){
                    window.location.reload()
                }
                if (result.code === 500) {
                    layer.msg(result.message)
                }
            },
            error: function (error) {
            }
        });
    }
}

function comment_up(id) {
    $.ajax({
        type: 'POST',
        url: "/comment/"+id,
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        success: function (result) {
            if (result.code === 200){
                window.location.reload()
            }
            if (result.code === 500) {
                layer.msg(result.message)
            }
        }
    });
}


function formSerializeJson(formId) {
    var obj = {};
    var serializeJson = $("#" + formId).serializeArray();
    var jsonArray = JSON.stringify(serializeJson);
    $.each(eval("(" + jsonArray + ")"), function (i, n) {
        obj[n.name] = n.value;
    });
    return JSON.stringify(obj);
}