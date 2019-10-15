table = $('#major_question_table');
table.bootstrapTable({
    url: "/admin/major/questions", // 获取表格数据的url
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
    sortable: true,      //是否启用排序
    sortOrder: "asc",     //排序方式
    pageNumber: 1,      //初始化加载第一页，默认第一页
    pageSize: 10,      //每页的记录行数（*）
    pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
    strictSearch: true,
    clickToSelect: true,    //是否启用点击选中行
    searchOnEnterKey: true,
    queryParams: function (params) {//上传服务器的参数
        return {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
            pageSize: params.limit, // 每页显示数量
            pageNumber: (params.offset / params.limit) + 1, //当前页码
            majorName: '%' + $('#major-name').val() + '%'
        };
    },
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
            title: '问题名称',
            field: 'title',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '问题描述',
            field: 'description',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '回答数量',
            field: 'answerCount',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '操作',
            field: 'url',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return '<button class="btn btn-primary btn-sm" onclick="deleteQuestion(\'' + row.id + '\')">删除</button> '

            }
        }
    ],
    locale: 'zh-CN'//中文支持
});

function deleteQuestion(id) {
    $.ajax({
        url: "/admin/major/question/" + id,
        type: "DELETE",
        success: function (result) {
            layer.msg(result.data);
            table.bootstrapTable('refresh');
        },
        error: function (error) {
            alert(error);
        }
    });
}

function searchMajorQuestions() {
    table.bootstrapTable('refresh', {url: '/admin/major/questions'});
}
