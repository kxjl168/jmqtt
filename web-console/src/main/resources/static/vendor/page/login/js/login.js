$('#captcha_code').keyup(function (e) {
    if (e.keyCode === 13 && 4 === $(e.target).val().length) {
        $('#login_form').submit();
    }

    /*if (e.keyCode == 13 ) {
        $('#login_form').submit();
    }*/
});

$('#captcha').click(function () {
    document.getElementById('captcha_image').src =getRootPath()+ '/validateCode.action?time=' + new Date();
});

//验证
$(function () {
    $("#login_form").bootstrapValidator({
        feedbackIcons: {
            /*input状态样式通过，刷新，非法三种图片*/
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitButtons: 'button[type="submit"]',//提交按钮
        fields: {
            account: {//验证账户
                validators: {
                    notEmpty: {//非空
                        message: '用户名不能为空'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            }
            /* ,
             captchaCode: {
                 validators: {
                     notEmpty: {
                         message: '验证码不能为空'
                     }
                 }
             }*/
        }
    });

    //显示密码
    $('#password').password().on('show.bs.password', function (e) {
    }).on('hide.bs.password', function (e) {
    });


    //生成下载二维码
    //生成前景色为红色背景色为白色的二维码
    $.ajax({
        type: "post",
        url: getRootPath()+'/interface/downloadappUrl',
        data: {},
        success: function (data) {
            var qrcode = new QRCode("qrcode", {
                text: data,
                width: 100,
                height: 100,
                colorDark: "#000000",
                colorLight: "#ffffff",
                correctLevel: QRCode.CorrectLevel.H
            });
        }
    });


});

function checkInput() {
    var password_state = $("#password_state").val();
    if (password_state.length <= 0) {
        var telephone = $("#telephone").val();
        var password_md5 = $("#password_md5").val();

       /* var hash = md5(password_md5);

        var hash1 = md5(hash + telephone);

        $("#password_md5").val(hash1);
        $("#password_state").val(hash1);*/
        
        
        var hash = md5(password_md5,telephone);

        var hash1 =password_md5;//hash;// md5(hash + telephone);

        $("#password_md5").val(hash1);
        $("#password_state").val(hash1);
        
    }
    return true;
}