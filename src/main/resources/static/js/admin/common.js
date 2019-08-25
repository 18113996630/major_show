$("#infoModal").on("hide.bs.modal",function(){//模态框隐藏时执行
    window.location.reload();//重置模态框内容（变成一开始的样子）
});
