/**
 * Created by Iceberg on 2017/3/17.
 */

function ajaxLoad(url, asyncMode, data, callback) {
    $.ajax({
        url: url,
        type: 'POST',
        async: asyncMode,
        data: data,
        dataType: 'json',
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            var errorMessage = "加载数据失败" + XMLHttpRequest.status;
            if (!data) {
                errorMessage += ", 错误信息: " + data.error;
            }
            alert(errorMessage);
        },
        success: function (result) {
            callback(result);
        }
    });
}