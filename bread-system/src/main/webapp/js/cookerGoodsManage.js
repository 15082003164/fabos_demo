
var activity = [];
var currentPage = 1;
$(document).ready(function () {

    var path = $("#path").text();


    to_page(path, 1);

});



$(document).on("click",".description",function () {
    $(this).popover();
});

$(document).on("click",".templatemo-edit-btn",function () {
    $("#update-goods").modal({
        backdrop:'static'
    });

    //获取当前点击商品的数据
    var upGoodsid = $(this).parents("tr").find("td:eq(0)").text();
    var upGoodsname = $(this).parents("tr").find("td:eq(1)").text();
    var upGoodsNum = $(this).parents("tr").find("td:eq(2)").text();
    var upGoodsMakings1 = $(this).parents("tr").find("td:eq(3)").text();
    var upGoodsMakings2 = $(this).parents("tr").find("td:eq(4)").text();
    var upGoodsMakings3 = $(this).parents("tr").find("td:eq(5)").text();
    var upGoodsDetailCate = $(this).parents("tr").find("td:eq(6)").text();

    $("#goodsid").text(upGoodsid);
    $("#goodsname").text(upGoodsname);
    $("#num").val(upGoodsNum);
    $("#makings_1").text(upGoodsMakings1);
    $("#makings_2").text(upGoodsMakings2);
    $("#makings_3").text(upGoodsMakings3);
    $("#detailcate").val(upGoodsDetailCate);
});

//修改商品信息
$(document).on("click","#saveUpdate",function () {
    var ugoodsid = $("#goodsid").text();
    var ugoodsname = $("#goodsname").text();
    var unum = $("#num").val();
    var umakings1 = $("#makings_1").text();
    var umakings2 = $("#makings_2").text();
    var umakings3 = $("#makings_3").text();
    var udetailcate = $("#detailcate").val();



    $.ajax({
        url:"/shop/cooker/update/",
        type:"POST",
        data:{
            goodsid:ugoodsid,
            goodsname:ugoodsname,
            num:unum,
            makings_1:umakings1,
            makings_2:umakings2,
            makings_3:umakings3,
            detailcate:udetailcate,
        },
        success:function(result){
            $("#update-goods").modal('hide');
            swal(result.msg,'','success');
            to_page('/shop',currentPage);
        },
        error:function(){
            alert("错误！！");
        }
    });


});


function to_page(path, page) {
    $.ajax({
        url: path + "/cooker/showjson",
        data: "page=" + page,
        type: "get",
        success: function (result) {

            //解析显示
            build_goods_table(path, result);

            //页面信息
            build_page_info(path, result);

            //分页
            build_page_nav(path, result);

            currentPage = page;
        }
    });
}

function build_goods_table(path,result) {
    $("#goodsinfo tbody").empty();
    var goods = result.info.pageInfo.list;
    $.each(goods, function (index,item) {
        var goodsid = $("<td></td>").append(item.goodsid);
        var goodsname = $("<td></td>").append(item.goodsname);
        var num = $("<td></td>").append(item.num);
        var makings_1 = $("<td></td>").append(item.makings_1);
        var makings_2 = $("<td></td>").append(item.makings_2);
        var makings_3 = $("<td></td>").append(item.makings_3);
        var detailcate = $("<td></td>").append(item.detailcate);


        var editBtn = $("<button></button>").addClass("templatemo-edit-btn").append("编辑");

        var editTd = $("<td></td>").append(editBtn);

        $("<tr></tr>").append(goodsid).append(goodsname).append(num).append(makings_1).append(makings_2).append(makings_3).append(detailcate).append(editTd).appendTo("#goodsinfo tbody");
    })
}

function build_page_info(path,result) {
    $("#page-info-area").empty();
    $("#page-info-area").append("当前第"+ result.info.pageInfo.pageNum +"页，总共"+ result.info.pageInfo.pages +"页，总共"+ result.info.pageInfo.total +"记录")
}

function build_page_nav(path,result) {
    $("#page-div-nav ul").empty();
    var pageUl = $("<ul></ul>").addClass("pagination")

    var firstPage = $("<li></li>").append($("<a aria-label=\"Next\"></a>")
        .append($("<span aria-hidden=\"true\"></span>")
            .append("首页")));

    var prePage = $("<li></li>").append($("<a aria-label=\"Next\"></a>")
        .append($("<span aria-hidden=\"true\"><i class=\"fa fa-backward\"></i></span>")));

    if(!result.info.pageInfo.hasPreviousPage) {
        prePage.addClass("li-none");
    } else {
        prePage.click(function () {
            to_page('/shop',result.info.pageInfo.prePage);
        });
    }

    //跳转
    firstPage.click(function () {
        to_page('/shop',1);
    });

    var nextPage = $("<li></li>").append($("<a aria-label=\"Next\"></a>")
        .append($("<span aria-hidden=\"true\"><i class=\"fa fa-forward\"></i></span>")));

    var lastPage = $("<li></li>").append($("<a aria-label=\"Next\"></a>")
        .append($("<span aria-hidden=\"true\"></span>")
            .append("末页")));

    if(!result.info.pageInfo.hasNextPage) {
        nextPage.addClass("li-none");
    } else {
        nextPage.click(function () {
            to_page('/shop',result.info.pageInfo.nextPage);
        });
    }

    lastPage.click(function () {
        to_page('/shop',result.info.pageInfo.lastPage);
    });

    pageUl.append(firstPage).append(prePage);

    $.each(result.info.pageInfo.navigatepageNums,function (index,item) {
        var numLi = $("<li></li>").append($("<a></a>")
            .append($("<span aria-hidden=\"true\"></span>").append(item)));
        if(result.info.pageInfo.pageNum === item) {
            numLi.addClass("active");
        }
        numLi.click(function () {
            to_page('/shop',item);
        });
        pageUl.append(numLi);
    });

    pageUl.append(nextPage).append(lastPage).appendTo("#page-div-nav");
}

