table = $('#detail_update_table');
table.bootstrapTable({
    url: "/admin/major/details/update", // 获取表格数据的url
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
    pageList: [10, 25, 50],  //可供选择的每页的行数（*）
    queryParams: function (params) {//上传服务器的参数
        return {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
            pageSize: params.limit, // 每页显示数量
            pageNumber: (params.offset / params.limit) + 1, //当前页码
            status: $('#status').val(),
            majorDetailId: $('#majorDetailId').val()
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
            title: '专业名称',
            field: 'majorName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '修改内容类别',
            field: 'type',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '原内容',
            field: 'contentBefore',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '修改内容',
            field: 'content',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '修改原因',
            field: 'reason',
            align: 'center',
            valign: 'middle'
        },
        {
            title: 'ip',
            field: 'ip',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '城市',
            field: 'city',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '提交时间',
            field: 'time',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '状态',
            field: 'status',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                var res = '';
                if (value === 1) {
                    res = '已通过';
                } else if(value === -1){
                    res = '已否定';
                }else {
                    res = '待处理';
                }
                return res;
            }
        },
        {
            title: "操作",
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return '<button class="btn btn-primary btn-sm" onclick="show(\'' + row.id + '\')">查看</button> ' +
                    '<button class="btn btn-primary btn-sm" onclick="agree(\'' + row.id + '\')">通过</button> ' +
                    '<button class="btn btn-primary btn-sm" onclick="negative(\'' + row.id + '\')">否定</button> ';
            }
        }
    ],
    locale: 'zh-CN'//中文支持
});

function show(id) {
    $.ajax({
        url: "/admin/major/detail/update/" + id,
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
    $("#majorId option[value='"+data.majorId+"']").attr("selected", "selected");
    $("#type").val(data.type);
    $("#contentBefore").val(data.contentBefore);
    $("#content").val(data.content);
    $("#ip").val(data.ip);
    $("#city").val(data.city);
    $("#time").val(data.time);
    // 显示模态框
    $('#infoModal').modal('show');
}

function agree(id) {
    $.ajax({
        url: "/admin/major/detail/update/agree/" + id,
        type: "POST",
        success: function () {
            table.bootstrapTable('refresh');
            layer.msg('修改通过审核', {icon: 1})
        },
        error: function () {
            alert("操作失败");
        }
    });
}
function negative(id) {
    $.ajax({
        url: "/admin/major/detail/update/negative/" + id,
        type: "POST",
        success: function () {
            table.bootstrapTable('refresh');
            layer.msg('审核未通过', {icon: 1})
        },
        error: function () {
            alert("操作失败");
        }
    });
}



function searchDetailUpdateInfo() {
    table.bootstrapTable('refresh', {url: '/admin/major/details/update'});
}
