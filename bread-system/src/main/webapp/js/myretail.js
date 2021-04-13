

$(document).ready(function(){

    // 请求数据
    var data1 = new Array();
    data1.length = 0;
    $.ajax({
        url: './info/retail',
        type: 'GET',
        dataType: 'json',
        sync: true,
        success: function (data) {
            data1.push(data);//要使用push而不能使用=赋值
        }
    });



});
