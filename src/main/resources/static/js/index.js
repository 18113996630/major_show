function searchHot(name,formId) {
    var id = 'form'+formId;
    $.ajax({
        type: "POST",   //提交的方法
        url:"/majors", //提交的地址
        data: $('#'+id).serialize(),// 序列化表单值
        async: false,
        error: function(request) {  //失败的话
            alert(2)
            // alert("Connection error");
        },
        success: function(data) {  //成功
            alert(1)
            // alert(data);  //就将返回的数据显示出来
            // window.location.href="跳转页面"
        }
    });
}