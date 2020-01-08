$(function () {

    $(".passwordType").on("click", ".fa-eye-slash", function () {
        $(this).removeClass("fa-eye-slash").addClass("fa-eye");
        $(this).siblings("input").attr("type", "text");
    });

    $(".passwordType").on("click", ".fa-eye", function () {
        $(this).removeClass("fa-eye").addClass("fa-eye-slash");
        $(this).siblings("input").attr("type", "password");
    });


    $("#btnSubmit").click(function () {
           //用户信息插入
        var originalPassword = $.trim($("#originalPassword").val());
        if ("" === originalPassword) {
            showPopover($("#originalPassword"), "请输入原密码");
            return;
        }

        var newPassword = $.trim($("#newPassword").val());
        if ("" === newPassword) {
            showPopover($("#newPassword"), "请输入新密码");
            return;
        }

        var confirmPassword = $.trim($("#confirmPassword").val());
        if ("" === confirmPassword) {
            showPopover($("#confirmPassword"), "请输入确认密码");
            return;
        }
        if (newPassword != confirmPassword) {
            showPopover($("#confirmPassword"), "确认密码输入不一致");
            return;
        }
        var hash = md5(newPassword);


        var telephone = $.trim($("#telephone").val());
        var hash1 = md5(hash + telephone);
        var oriPass = md5(originalPassword);
        var oriPassword = md5(oriPass + telephone);

        var data = $("#userForm").serialize();

        data = data + "&" + "password=" + hash1+ "&" + "oriPassword=" + oriPassword;
        $.ajax({
            type: "post",
            url: '/manager/admin/password/update.action',
            data: data,
            async: false,
            dataType: "json",
            success: function (response) {
                if (response.result) {
                    showPopover($("#originalPassword"), "密码修改成功");
                } else {
                    showPopover($("#originalPassword"), "密码修改失败");
                }
            }
        });
    });

});

function checkPassWord(obj) {
    var reg = new RegExp(/^(?![^a-z]+$)(?![^A-Z]+$)(?!\D+$)/);
    var bol = reg.test(obj.val());
    if (!bol) {
        alert("密码必须由6-16位大小写英文字母加数字组成！");
        obj.val("");
    }
}

