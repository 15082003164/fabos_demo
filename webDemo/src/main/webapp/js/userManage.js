$(document).ready(function () {

    var path = $("#path").text();

    var currentPage = 1;

    to_page(path, 1);
});


function to_page(path, page) {
    $.ajax({
        url: path + "/select",
        data: "page=" + page,
        type: "get",
        success: function (result) {

            //解析显示
            build_user_table(path, result);

            //页面信息
            build_page_info(path, result);

            //分页
            build_page_nav(path, result);

            currentPage = page;
        }
    });
}

$(document).on("click",".templatemo-edit-btn",function () {
    $("#update-info").modal({
        backdrop:'static'
    });

    //获取当前点击商品的数据
    var upId = $(this).parents("tr").find("td:eq(0)").text();
    var upName = $(this).parents("tr").find("td:eq(1)").text();
    var upTel = $(this).parents("tr").find("td:eq(2)").text();
    var upClassname = $(this).parents("tr").find("td:eq(3)").text();

    $("#id").text(upId);
    $("#name").val(upName);
    $("#tel").val(upTel);
    $("#classname").val(upClassname);
});

//修改用户信息
$(document).on("click","#saveInfo",function () {
    var uid = $("#id").text();
    var uname = $("#name").val();
    var utel = $("#tel").val();
    var uclassname = $("#classname").val();

    $.ajax({
        url:"/update/",
        type:"POST",
        data:{
            id:uid,
            name:uname,
            tel:utel,
            classname:uclassname,
        },
        success:function(result){
            $("#update-info").modal('hide');
            swal(result.msg,'','success');
            to_page('',currentPage);
        },
        error:function(){
            alert("错误！！");
        }
    });
});

$(document).on("click",".templatemo-delete-btn",function () {
    var name = $(this).parents("tr").find("td:eq(1)").text();
    var id = $(this).parents("tr").find("td:eq(0)").text();
    swal({
            title: "确定删除" + name + "吗？",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定删除！",
            closeOnConfirm: false,
        },
        function () {
            /*swal("删除！", "你的虚拟文件已经被删除。", "success");*/
            $.ajax({
                url: "/delete/" + id,
                type: "DELETE",
                success:function (result) {
                    swal(result.msg, "","success");
                    to_page('',currentPage);
                },
                error:function () {
                }
            });
        });
});

function build_user_table(path,result) {
    $("#students tbody").empty();
    var students = result.info.pageInfo.list;
    $.each(students, function (index,item) {
        var id = $("<td></td>").append(item.id);
        var name = $("<td></td>").append(item.name);
        var tel = $("<td></td>").append(item.tel);
        var classname = $("<td></td>").append(item.classname);

        var deleteBtn = $("<button></button>").addClass("templatemo-delete-btn").append("删除");

        var editBtn = $("<button></button>").addClass("templatemo-edit-btn").append("编辑");

        var deleteTd = $("<td></td>").append(deleteBtn);

        var editTd = $("<td></td>").append(editBtn);



        $("<tr></tr>").append(id)
            .append(name)
            .append(tel)
            .append(classname)
            .append(deleteTd)
            .append(editTd).appendTo("#students tbody");
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
            to_page('',result.info.pageInfo.prePage);
        });
    }

    //跳转
    firstPage.click(function () {
        to_page('',1);
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
            to_page('',result.info.pageInfo.nextPage);
        });
    }

    lastPage.click(function () {
        to_page('',result.info.pageInfo.lastPage);
    });

    pageUl.append(firstPage).append(prePage);

    $.each(result.info.pageInfo.navigatepageNums,function (index,item) {
        var numLi = $("<li></li>").append($("<a></a>")
            .append($("<span aria-hidden=\"true\"></span>").append(item)));
        if(result.info.pageInfo.pageNum === item) {
            numLi.addClass("active");
        }
        numLi.click(function () {
            to_page('',item);
        });
        pageUl.append(numLi);
    });

    pageUl.append(nextPage).append(lastPage).appendTo("#page-div-nav");
}
