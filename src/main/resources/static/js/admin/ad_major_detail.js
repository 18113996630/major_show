table = $('#major_table');
table.bootstrapTable({
    url: "/admin/major/details", // 获取表格数据的url
    method: 'get',
    contentType: "application/x-www-form-urlencoded",
    cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
    striped: true,  //表格显示条纹，默认为false
    showRefresh: true,//刷新按钮
    sidePagination: 'server', // 设置为服务器端分页
    toolbarAlign: 'right',
    buttonsAlign: 'right',//按钮对齐方式
    toolbar: '#toolbar',//指定工作栏
    pagination: true,     //是否显示分页（*）
    sortable: false,      //是否启用排序
    sortOrder: "asc",     //排序方式
    pageNumber: 1,      //初始化加载第一页，默认第一页
    pageSize: 10,      //每页的记录行数（*）
    pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
    queryParams: function (params) {//上传服务器的参数
        return {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
            pageSize: params.limit, // 每页显示数量
            pageNumber: (params.offset / params.limit) + 1, //当前页码
            majorName: '%'+$('#major-name').val()+'%',
            subjectName: '%'+$('#subject-name').val()+'%'
        };
    },
    strictSearch: true,
    clickToSelect: true,    //是否启用点击选中行
    searchOnEnterKey: true,
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
            title: '名称',
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
            title: '学制',
            field: 'year',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '描述',
            field: 'description',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '核心课程',
            field: 'course',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '就业前景',
            field: 'job',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '能力要求',
            field: 'ability',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '所属类别',
            field: 'subjectName',
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
            title: "操作",
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return '<button class="btn btn-primary btn-sm" onclick="updateMajor(\'' + row.id + '\')">修改</button> ' +
                    '<button class="btn btn-primary btn-sm" onclick="deleteMajor(\'' + row.id + '\')">无效</button> ' +
                    '<button class="btn btn-danger btn-sm" onclick="recoveryMajor(\'' + row.id + '\')">恢复</button> ';
            }
        }
    ],
    locale: 'zh-CN'//中文支持
});

function updateMajor(id) {
    $.ajax({
        url: "/admin/major/detail/" + id,
        type: "GET",
        // 成功后开启模态框
        success: showQuery,
        error: function () {
            alert("请求失败");
        }
    });
}

function deleteMajor(id) {
    $.ajax({
        url: "/admin/major/detail/" + id,
        type: "DELETE",
        success: function () {
            table.bootstrapTable('refresh');
            layer.msg('删除成功', {icon: 1})
        }, error: function () {
            layer.alert("删除失败");
        }
    });
}

function recoveryMajor(id) {
    $.ajax({
        url: "/admin/major/detail/" + id,
        type: "POST",
        success: function () {
            table.bootstrapTable('refresh');
            layer.msg('恢复成功', {icon: 1})
        },
        error: function () {
            alert("恢复失败");
        }
    });
}

function showQuery(data) {
    data = data.data;
    console.log(data);
    $("#id").val(data.id);
    $("#name").val(data.name);
    $("#code").val(data.code);
    $("#year").val(data.year);
    $("#description").val(data.description);
    $("#course").val(data.course);
    $("#job").val(data.job);
    $("#ability").val(data.ability);
    // 显示模态框
    $('#infoModal').modal('show');
}

function searchDetails() {
    table.bootstrapTable('refresh', {url: '/admin/major/details'});
}

function saveMajor() {
    var formData = formSerializeJson('major_form');
    var res = validateInput(formData);
    if (res) {
        $.ajax({
            type: 'POST',
            url: "/admin/major/detail",
            data: formData,
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            success: function (data) {
                if (data.code === 200) {
                    $('#infoModal').modal('hide');
                    var opt = {
                        url: "/admin/major/details",
                        silent: true,
                        query: {}
                    };
                    table.bootstrapTable('refresh', opt);
                    layer.msg('修改成功', {icon: 1})
                } else {
                    layer.alert(data.message);
                }
            },
            error: function (error) {
                layer.alert(error);
            }
        });
    } else {
        layer.msg('请填写好所有信息再进行提交操作！', {icon: 2})
    }
}

function validateInput(data) {
    var formData = JSON.parse(data);
    if (formData['name'] === "" || formData['code'] === "" || formData['orderNumber'] === "") {
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