/**
 * Created by admin on 2017/5/3.
 */
$(function () {
    //邮箱域名补全方法
    function inputList(input,list){
        var mailBox = ["@qq.com", "@sina.com", "@163.com", "@126.com", "@yahoo.com.cn", "@gmail.com", "@sohu.com", "@hotmail.com"];
        input.bind('input click', function() {
            var key = input.val();
            if(key.indexOf("@") != -1){
                key = key.slice(0,key.indexOf("@"));
            }
            var mailBoxLen = mailBox.length;
            var html = "";
            for(var i=0; i<mailBoxLen; i++){
                html += '<option value="'+ key + mailBox[i] +'"></option>';
            }
            list.html(html);
        });
    }
    //外部调用邮箱域名补全方法
    inputList($("#email"),$("#input_list"));
});