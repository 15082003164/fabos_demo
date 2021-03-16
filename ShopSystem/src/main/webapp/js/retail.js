

$(document).ready(function(){

    $("#retail_first_percent").val($("#retail_first_percentVal").text());
    $("#retail_second_percent").val($("#retail_second_percentVal").text());
    $("#retail_third_percent").val($("#retail_third_percentVal").text());
    $("#price_config").val($("#price_configVal").text());
    $("#changeRetailConfig").click(function(){
        $("#updateRetailConfig").modal({
            backdrop:'static'
        });
    });


    $("#saveRetailConfig").click(function (){
        var saveInfo={};
        saveInfo.retail_first_percent=$("#retail_first_percent").val();
        saveInfo.retail_second_percent=$("#retail_second_percent").val();
        saveInfo.retail_third_percent=$("#retail_third_percent").val();
        saveInfo.price_config=$("#price_config").val();
        $.ajax({
            type: "POST",
            url: "/shop/admin/retail/saveConfig",
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            data:saveInfo,
            dateType:"json",
            success: function(result){
                if (result.msg=="更新失败")
                {
                    swal(result.msg);
                }
                else {
                    $("#updateRetailConfig").modal('hide');
                    swal("修改成功", "", "success");
                    $("button").click(function (){
                        location.reload();
                    });
                }
            },
            error:function (){
                alert("修改失败");
            }
        });
    });


});
