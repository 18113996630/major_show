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
    if ($('#name').val() == '') {
        layer.msg('评论内容不能为空哦~')
    } else {
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            contentType: "application/json;charset=UTF-8",
            url: "/comment",//url
            // data: {name: $('#name').val(), majorDetailId:$('#majorDetailId').val()},
            data: formSerializeJson('comment-form'),
            success: function (result) {
                if (result.code === 200) {
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

function comment_up(id, count) {
    $.ajax({
        type: 'POST',
        url: "/comment/" + id,
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        success: function (result) {
            if (result.code === 200) {
                layer.msg('点赞成功');
                var btn = $('#' + 'common-up' + id);
                btn.attr('disabled', true);
                btn.html('<span aria-hidden="true" class="glyphicon glyphicon-thumbs-up"></span><span>' + (count + 1) + '</span>')
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

/**
 * 修改专业描述信息
 * 1. 将详情信息展示在textarea中
 * 2. 生成修改意见input
 * 3. 生成提交按钮
 * @param detailId 详情id
 */
function updateDesc(detailId) {
    //修改按钮
    var btn = $('#detail_desc_btn');
    //专业介绍p标签
    var detail = $('#detail_desc');
    //专业介绍的文字内容
    var detail_desc_content = detail.text();
    //使按钮失效
    btn.attr('disabled', true);
    $('#detail_course_btn').attr('disabled', true);
    $('#detail_job_btn').attr('disabled', true);
    //隐藏p标签
    detail.css("display", "none");
    //隐藏查看更多
    detail.next().css('display', 'none');
    //拼接textarea
    detail.after("<div class='clearfloat'></div>");
    detail.after("<button text='修改' id='agree_btn' class='btn btn-primary btn-agree' style='float: right;width: 40%;' onclick='update_detail(" + detailId + ", \"description\", \"detail_desc\")'>修改</button>");
    detail.after("<button text='取消' id='cancel_btn' class='btn btn-info btn-cancel' style='float: left; width: 40%;' onclick='update_cancel()'>取消</button>");
    detail.after("<textarea id='_reason' class='form-control update_area' rows='3' placeholder='请填写修改理由'></textarea>");
    detail.after("<textarea id='_area' class='form-control update_area' rows='7'></textarea>");
    $('#_area').val(detail_desc_content)
}

function updateCourse(detailId) {
    //修改按钮
    var btn = $('#detail_course_btn');
    //专业介绍p标签
    var course = $('#detail_course');
    //专业介绍的文字内容
    var detail_course_content = course.text();
    //使按钮失效
    btn.attr('disabled', true);
    $('#detail_desc_btn').attr('disabled', true);
    $('#detail_job_btn').attr('disabled', true);
    //隐藏p标签
    course.css("display", "none");
    //隐藏查看更多
    course.next().css('display', 'none');
    //拼接textarea
    course.after("<div class='clearfloat'></div>");
    course.after("<button text='修改' id='agree_btn' class='btn btn-primary btn-agree' style='float: right;width: 40%;' onclick='update_detail(" + detailId + ", \"course\", \"detail_course\")'>修改</button>");
    course.after("<button text='取消' id='cancel_btn' class='btn btn-info btn-cancel' style='float: left; width: 40%;' onclick='update_cancel()'>取消</button>");
    course.after("<textarea id='_reason' class='form-control update_area' rows='3' placeholder='请填写修改理由'></textarea>");
    course.after("<textarea id='_area' class='form-control update_area' rows='7'></textarea>");
    $('#_area').val(detail_course_content)
}

function updateJob(detailId) {
    //修改按钮
    var btn = $('#detail_job_btn');
    //专业介绍p标签
    var detail = $('#detail_job');
    //专业介绍的文字内容
    var detail_job_content = detail.text();
    //使按钮失效
    btn.attr('disabled', true);
    $('#detail_desc_btn').attr('disabled', true);
    $('#detail_course_btn').attr('disabled', true);
    //隐藏p标签
    detail.css("display", "none");
    //隐藏查看更多
    detail.next().css('display', 'none');
    //拼接textarea
    detail.after("<div class='clearfloat'></div>");
    detail.after("<button text='修改' id='agree_btn' class='btn btn-primary btn-agree' style='float: right;width: 40%;' onclick='update_detail(" + detailId + ", \"job\", \"detail_job\")'>修改</button>");
    detail.after("<button text='取消' id='cancel_btn' class='btn btn-info btn-cancel' style='float: left; width: 40%;' onclick='update_cancel()'>取消</button>");
    detail.after("<textarea id='_reason' class='form-control update_area' rows='3' placeholder='请填写修改理由'></textarea>");
    detail.after("<textarea id='_area' class='form-control update_area' rows='7'></textarea>");
    $('#_area').val(detail_job_content)
}

/**
 * 更新专业描述、专业课程、专业前景相关
 * id: desc course job
 */
function update_detail(id, divId, contentId) {
    var _area = $('#_area');
    var _reason = $('#_reason');
    var _content = $('#' + contentId);
    var content = _area.val();
    var reason = _reason.val();
    if (content === '' || reason === '') {
        layer.msg('修改内容或修改原因不能为空~');
        return;
    }
    $.post('/major',
        {majorDetailId: id, content: content, type: divId, reason: reason} ,
        function (data) {
            if (data.code === 200) {
                $('#cancel_btn').css('display', 'none');
                $('#agree_btn').css('display', 'none');
                $('#detail_desc_btn').attr('disabled', false);
                $('#detail_course_btn').attr('disabled', false);
                $('#detail_job_btn').attr('disabled', false);
                _area.css('display', 'none');
                _reason.css('display', 'none');
                _content.css('display', 'block');
                _content.css("height", "auto");
                layer.msg(data.data, {icon: 1})
            } else {
                layer.alert(data.message);
            }
    });
}

function update_cancel() {
    window.location.reload();
}