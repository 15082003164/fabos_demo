
var activity = [];
var currentPage = 1;
$(document).ready(function () {

    var path = $("#path").text();


    to_page(path, 1);

});


$(document).on("click",".templatemo-edit-btn",function () {
    $("#update-goods").modal({
        backdrop:'static'
    });

    //获取当前点击商品的数据
    var upKindsid = $(this).parents("tr").find("td:eq(0)").text();
    var upKindsname = $(this).parents("tr").find("td:eq(1)").text();
    var upKindsNum = $(this).parents("tr").find("td:eq(3)").text();

    $("#kinds_id").text(upKindsid);
    $("#kinds_name").val(upKindsname);
    $("#kinds_num").val(upKindsNum);
});

//修改cl信息
$(document).on("click","#saveUpdate",function () {
    var ukindsid = $("#kinds_id").text();
    var ukindsname = $("#kinds_name").val();
    var ukindsnum = $("#kinds_num").val();


    $.ajax({
        url:"/shop/admin/kinds/update/",
        type:"POST",
        data:{
            kinds_id:ukindsid,
            kinds_name:ukindsname,
            kinds_num:ukindsnum,
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

$(document).on("click",".templatemo-delete-btn",function () {
    var kindsname = $(this).parents("tr").find("td:eq(1)").text();
    var kindsid = $(this).parents("tr").find("td:eq(0)").text();
    swal({
            title: "确定删除" + kindsname + "吗？",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定删除！",
            closeOnConfirm: false,
        },
        function () {
            $.ajax({
                url: "/shop/admin/kinds/delete/" + kindsid,
                type: "DELETE",
                success:function (result) {
                    swal(result.msg, "","success");
                    to_page('/shop',currentPage);
                },
                error:function () {
                    to_page('/shop',currentPage);
                }
            });
        });
});


function to_page(path, page) {
    $.ajax({
        url: path + "/admin/kinds/showjson",
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
    $("#kindsinfo tbody").empty();
    var kinds = result.info.pageInfo.list;
    $.each(kinds, function (index,item) {
        var kindsid = $("<td></td>").append(item.kinds_id);
        var kindsname = $("<td></td>").append(item.kinds_name);
        var kindsnum = $("<td></td>").append(item.kinds_num);

        var editBtn = $("<button></button>").addClass("templatemo-edit-btn").append("编辑");
        var deleteBtn = $("<button></button>").addClass("templatemo-delete-btn").append("删除");

        var editTd = $("<td></td>").append(editBtn);
        var deleteTd = $("<td></td>").append(deleteBtn);

        $("<tr></tr>").append(kindsid).append(kindsname).append(kindsnum).append(editTd).append(deleteTd).appendTo("#kindsinfo tbody");
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

