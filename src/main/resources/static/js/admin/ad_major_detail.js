$(document).ready(function () {
    $.ajax({
        url: "/subject/" + id,
        type: "POST",
        success: function () {
            $('#subject_table').bootstrapTable('refresh');
            layer.msg('恢复成功', {icon:1})
        },
        error: function () {
            alert("恢复失败");
        }
    });
});