table = $('#video_author_table');
table.bootstrapTable({
    url: "/admin/video/authors", // 获取表格数据的url
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
            upName: '%' + $('#up-name').val() + '%',
            isAuth: $('#is-auth').val()
        };
    },
    columns: [
        {
            checkbox: true
        },
        {
            title: '序号', // 表格表头显示文字
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return index + 1;
            }
        },
        {
            title: 'UP名字',
            field: 'upName',
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
                if (value == 1) {
                    res = '已授权';
                } else {
                    res = '暂未授权';
                }
                return res;
            }
        },
        {
            title: '视频数量',
            field: 'count',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '视频名字',
            field: 'titles',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return '您好，我是一个转行一年半的程序员，我最近正在做一个网站，网站是关于普及大学各个专业需要学习的课程、就业前景啊什么的，我看到您主页里有些视频挺不错的，' +
                    '可能会对同学们有帮助，所以想问问您可以把链接贴到我的网站里吗？\r\n' +
                    '贴的方式是将封面放在网站同时声明原作者信息，点击封面会跳回您的主页进行播放，这是网站地址，您可以先看看: http://39.106.190.74 \r\n'
            }
        },
        {
            title: '主页地址',
            field: 'url',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return '<a class="btn btn-primary btn-sm" target="_blank" href="'+value+'">查看主页</a> ' +
                    '<a class="btn btn-primary btn-sm" target="_blank" href="'+value+'">修改</a> '
            }
        }
    ],
    locale: 'zh-CN'//中文支持
});

function queryVideos(id) {
    $.ajax({
        url: "/admin/video/author/info/" + id,
        type: "GET",
        // 成功后开启模态框
        success: showQuery,
        error: function () {
            alert("请求失败");
        }
    });
}

function searchAuthors() {
    table.bootstrapTable('refresh', {url: '/admin/video/authors'});
}

function showQuery(data) {
    data = data.data;
    $("#info").val(data.info);
    $('#infoModal').modal('show');
}