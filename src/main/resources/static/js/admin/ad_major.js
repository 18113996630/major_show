$('#subject_table').bootstrapTable({
    url: "/subjects", // 获取表格数据的url
    method: 'post',
    contentType: "application/x-www-form-urlencoded",
    cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
    striped: true,  //表格显示条纹，默认为false
    showRefresh: true,//刷新按钮
    sidePagination: 'server', // 设置为服务器端分页
    toolbarAlign: 'right',
    buttonsAlign: 'right',//按钮对齐方式
    toolbar: '#toolbar',//指定工作栏
    columns: [
        {
            title: '序号', // 表格表头显示文字
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return index + 1;
            }
        },
        {
            title: '名字',
            field: 'name',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '编码',
            field: 'code',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '文章正确',
            field: 'isRightWord',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '文章错误',
            field: 'isWrongWord',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '视频正确',
            field: 'isRightVideo',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '视频错误',
            field: 'isWrongVideo',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '详情url',
            field: 'iconUri',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '图标路径',
            field: 'iconUri',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '排序号',
            field: 'orderNumber',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '是否有效',
            field: 'deleted',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                var res = '';
                if (value === 1) {
                    res = '无效';
                } else {
                    res = '有效';
                }
                return res;
            }
        },
        {
            title: '专业类别',
            field: 'subjectId',
            align: 'center',
            valign: 'middle'
        },
        {
            title: "操作",
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return '<button class="btn btn-primary btn-sm" onclick="updateSubject(\'' + row.id + '\')">修改</button> ' +
                    '<button class="btn btn-primary btn-sm" onclick="deleteSubject(\'' + row.id + '\')">无效</button> ' +
                    '<button class="btn btn-danger btn-sm" onclick="recoverySubject(\'' + row.id + '\')">恢复</button> ';
            }
        }
    ],
    locale: 'zh-CN'//中文支持
});

function updateSubject(id) {
    $.ajax({
        url: "/subject/" + id,
        type: "GET",
        // 成功后开启模态框
        success: showQuery,
        error: function () {
            alert("请求失败");
        }
    });
}

function deleteSubject(id) {
    $.ajax({
        url: "/subject/" + id,
        type: "DELETE",
        success: function () {
            $('#subject_table').bootstrapTable('refresh');
            layer.msg('删除成功', {icon:1})
        },error: function () {
            layer.alert("删除失败");
        }
    });
}

function recoverySubject(id) {
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
}

function showQuery(data) {
    data = data.data;
    $("#id").val(data.id);
    $("#name").val(data.name);
    $("#code").val(data.code);
    $("#description").val(data.description);
    $("#iconUri").val(data.iconUri);
    $("#orderNumber").val(data.orderNumber);
    // 显示模态框
    $('#infoModal').modal('show');
}

function saveSubject() {
    var formData = formSerializeJson('subject_form');
    var res = validateInput(formData);
    if (res) {
        $.ajax({
            type: 'POST',
            url: "/subject",
            data: formData,
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            success: function (data) {
                if (data.code === 200) {
                    $('#infoModal').modal('hide');
                    var opt = {
                        url: "/subjects",
                        silent: true,
                        query: {}
                    };
                    $('#subject_table').bootstrapTable('refresh', opt);
                    layer.msg('修改成功', {icon:1})
                } else {
                    layer.alert(data.message);
                }
            },
            error: function (error) {
                layer.alert(error);
            }
        });
    } else {
        layer.msg('请填写好所有信息再进行提交操作！', {icon:2})
    }
}

function validateInput(data) {
    var formData = JSON.parse(data);
    if (formData['name'] === "" || formData['code'] === "" || formData['iconUri'] === "" || formData['orderNumber'] === "") {
        return false;
    } else {
        return true;
    }
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