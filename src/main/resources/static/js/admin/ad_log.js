table = $('#log_table');
table.bootstrapTable({
    url: "/admin/logs", // 获取表格数据的url
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
    strictSearch: true,
    clickToSelect: true,    //是否启用点击选中行
    searchOnEnterKey: true,
    queryParams: function (params) {//上传服务器的参数
        return {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
            pageSize: params.limit, // 每页显示数量
            pageNumber: (params.offset / params.limit) + 1, //当前页码
            ip: '%' + $('#ip').val() + '%',
            resource: '%' + $('#resource-type').val() + '%'
        };
    },
    columns: [
        // {
        //     checkbox: true
        // },
        {
            title: '序号', // 表格表头显示文字
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return index + 1;
            }
        },
        {
            title: 'ip',
            field: 'ip',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '访问地址',
            field: 'url',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '所在城市',
            field: 'city',
            align: 'center',
            valign: 'middle'
        },
        {
            title: 'URI',
            field: 'uri',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '资源类型',
            field: 'resourceType',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '访问时间',
            field: 'time',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '耗费时间',
            field: 'executeTime',
            align: 'center',
            valign: 'middle'
        },
        {
            title: 'user-agent',
            field: 'browser',
            align: 'center',
            valign: 'middle'
        }
    ],
    locale: 'zh-CN'//中文支持
});
function searchLogs() {
    table.bootstrapTable('refresh', {url: '/admin/logs'});
}