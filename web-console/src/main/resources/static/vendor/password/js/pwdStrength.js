/**
 * Created by admin on 2017/5/3.
 */
$(function () {
    //定义不同的字符
    var characters = 0;
    var capitalLetters = 0;
    var lowLetters = 0;
    var number = 0;
    var special = 0;
    var upperCase = new RegExp('[A-Z]');
    var lowerCase = new RegExp('[a-z]');
    var numbers = new RegExp('[0-9]');
    var specialChars = new RegExp('([!,%,&,@,#,$,^,*,?,_,~])');
    $("#password").keyup(function () {
        var pwd = $(this).val();
        if (pwd.length >= 6) {
            check_strength(pwd);
        } else {
            $("#strong").css("display", "none");
        }
    });
    //需要控制退格键8和删除键46的事件，修改了bootstrap的重新验证，不需要额外判断了。
    //根据不同字符计算密码强度
    function check_strength(thisval) {
        if (thisval.length >= 8) {
            characters = 1;
        } else {
            characters = 0;
        }
        ;
        if (thisval.match(upperCase)) {
            capitalLetters = 1
        } else {
            capitalLetters = 0;
        }
        ;
        if (thisval.match(lowerCase)) {
            lowLetters = 1
        } else {
            lowLetters = 0;
        }
        ;
        if (thisval.match(numbers)) {
            number = 1
        } else {
            number = 0;
        }
        ;
        if (thisval.match(specialChars)) {
            special = 1
        } else {
            special = 0;
        }
        ;
        var total = characters + capitalLetters + lowLetters + number + special;
        get_total(total);
    }

    //获取密码强度结果
    function get_total(total) {
        var color;//进度条颜色
        var count;//进度条宽度
        var level;//密码强度提示文字
        switch (total) {
            case 1:
                count = "15%";
                color = "progress-bar-danger";
                level = "极弱";
                break;
            case 2:
                count = "35%";
                color = "progress-bar-warning";
                level = "一般";
                break;
            case 3:
                count = "55%";
                color = "progress-bar-info";
                level = "中等";
                break;
            case 4:
                count = "75%";
                color = "progress-bar-primary";
                level = "较强";
                break;
            case 5:
                count = "98%";
                color = "progress-bar-success";
                level = "极强";
                break;
        }
        $("#strong").css("display", "block").children(".progress-bar").css({"width": count});
        $("#strong>div").attr("class", "progress-bar " + color);
        $("#strong>div").text(level);
        //手动触发bootstrapValidator验证
        //需要传入参数，要提交的表单名称和作用于name值
        // $(formName).data("bootstrapValidator").validate('fieldName');
        // $('form').data("bootstrapValidator").validate('password');
        var bootstrapValidator = $('form').data('bootstrapValidator');
        bootstrapValidator.updateStatus('password', 'NOT_VALIDATED').validateField('password'); //错误提示信息变了
    }
});