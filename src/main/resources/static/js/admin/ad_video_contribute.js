table = $('#video_table');
table.bootstrapTable({
    url: "/admin/contribute/video", // 获取表格数据的url
    method: 'get',
    contentType: "application/x-www-form-urlencoded",
    cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
    striped: true,  //表格显示条纹，默认为false
    showRefresh: true,//刷新按钮
    sidePagination: 'server', // 设置为服务器端分页
    toolbarAlign: 'right',
    buttonsAlign: 'right',//按钮对齐方式
    toolbar: '#toolbar',//指定工作栏
    pagination: false,     //是否显示分页（*）
    sortable: false,      //是否启用排序
    sortOrder: "asc",     //排序方式
    pageNumber: 1,      //初始化加载第一页，默认第一页
    pageSize: 10,      //每页的记录行数（*）
    pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
    strictSearch: true,
    clickToSelect: false,    //是否启用点击选中行
    searchOnEnterKey: true,
    queryParams: '',
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
            title: '视频标题',
            field: 'title',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '地址',
            field: 'url',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '时长',
            field: 'duration',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '发布时间',
            field: 'pubtime',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '封面文件名称',
            field: 'coverName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '头像文件名称',
            field: 'faceName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: 'up主页',
            field: 'upPage',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '来源',
            field: 'sourceName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '播放量',
            field: 'play',
            align: 'center',
            valign: 'middle'
        },
        {
            title: 'up名字',
            field: 'upName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: 'up主页',
            field: 'upPage',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '联系方式',
            field: 'upContact',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '是否授权',
            field: 'isAuth',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                var res = '';
                if (value === 1) {
                    res = '已授权';
                } else {
                    res = '未授权';
                }
                return res;
            }
        },
        {
            title: '所属专业',
            field: 'majorName',
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
                } else if(value === 2){
                    res = '待审核';
                }else if(value === 0){
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
                return '<button class="btn btn-primary btn-sm" onclick="updateVideo(\'' + row.id + '\')">开始审核完善</button> '
            }
        }
    ],
    locale: 'zh-CN'//中文支持
});

function updateVideo(id) {
    $.ajax({
        url: "/admin/video/" + id,
        type: "GET",
        // 成功后开启模态框
        success: showQuery,
        error: function () {
            alert("请求失败");
        }
    });
}

function showQuery(data) {
    data = data.data;
    $("#id").val(data.id);
    $("#title").val(data.title);
    $("#majorId option[value='"+data.majorId+"']").attr("selected", "selected");
    $("#url").val(data.url);
    $("#intro").val(data.intro);
    $("#duration").val(data.duration);
    $("#pubtime").val(data.pubtime);
    $("#play").val(data.play);
    $("#upName").val(data.upName);
    $("#isAuth").val(data.isAuth);
    $("#deleted").val(data.deleted);
    $("#orderNumber").val(data.orderNumber);
    // 显示模态框
    $('#infoModal').modal('show');
}

function saveVideo() {
    var formData = formSerializeJson('video_form');
    var res = validateInput(formData);
    if (res) {
        $.ajax({
            type: 'POST',
            url: "/admin/video",
            data: formData,
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            success: function (data) {
                if (data.code === 200) {
                    $('#infoModal').modal('hide');
                    var opt = {
                        url: "/admin/videos",
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

function getIdSelections() {
    rows = $("#video_table").bootstrapTable('getSelections');
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length-1);
    return ids
}

function auth_video() {
    data = getIdSelections();
    $.post('/admin/videos/auth', {"ids": data}, function (result) {
        table.bootstrapTable('refresh', {url: '/admin/videos'});
    })
}