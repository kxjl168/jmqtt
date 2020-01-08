$(function () {
    initValidate();


    selectcommunity1();

    $('#selectcommunity1').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
        clearroom1();
        clearbuilding1();
        selectbuilding1();
    });

    $('#selectbuilding1').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
        clearroom1();
        selectroom1();
    });

    $('#selectroom1').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
        $("#room_id").val($('#selectroom1').val());
    });



    var picker1 = $('#datetimepicker1').datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        todayHighlight: true,
        minView: 2
    });

    var picker2 = $('#datetimepicker2').datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        todayHighlight: true,
        minView: 2
    });

    picker1.on('changeDate', function (e) {
        $('#datetimepicker2').datetimepicker('setStartDate', e.date);
    });
    picker2.on('changeDate', function (e) {
        $('#datetimepicker1').datetimepicker('setEndDate', e.date);
    });


    $(".passwordType").on("click", ".fa-eye-slash", function () {
        $(this).removeClass("fa-eye-slash").addClass("fa-eye");
        $(this).siblings("input").attr("type", "text");
    });

    $(".passwordType").on("click", ".fa-eye", function () {
        $(this).removeClass("fa-eye").addClass("fa-eye-slash");
        $(this).siblings("input").attr("type", "password");
    });


    $("#btnAdd").click(function () {
        $("#title").html("租户新增");
        $("#residentsid").val("");
        $("#leaseid").val("");
        $("#room_id").val("");
        $("#myModal").modal();
    });




    $("#btnSubmit").click(function () {
        $("#mform").data("bootstrapValidator").validate();
        var flag = $("#mform").data("bootstrapValidator").isValid();
        if (!flag) {
            return;
        }

        var residentsid = $("#residentsid").val();
        var leaseid = $("#leaseid").val();
        var room_id = $("#room_id").val();

        var residentsname = $('#residentsname').val();
        var nationality = $('#nationality').val();
        var gender = $('#gender').val();
        var birth = $('#birth').val();
        var id_crad = $('#id_crad').val();
        var permanent_address = $('#permanent_address').val();
        var telephone = $('#telephone').val();
        var password = $('#password').val();


        var start_time = $("#datetimepicker1").val();
        var end_time = $("#datetimepicker2").val();


        var params = {
            "residents_id": residentsid,
            "name": residentsname,
            "nationality": nationality,
            "gender": gender,
            "birth": birth,
            "id_crad": id_crad,
            "permanent_address": permanent_address,
            "telephone": telephone,
            "password": password,
            "start_time": start_time,
            "end_time": end_time,
            "room_id": room_id,
            "leaseid": leaseid,
        };

        var url = "";
        if (residentsid === "") {
            url = '/manager/lease/addLease';
        } else {
            url = '/manager/lease/updateLease';
        }

        $.ajax({
            type: "post",
            url: url,
            data: {params: JSON.stringify(params)},
            async: false,
            dataType: "json",
            success: function (response) {
                if (response.bol) {

                    Query();
                    alert("操作成功")
                    $('#myModal').modal("hide");
                } else {
                    alert(response.message);
                }

            }
        });

    });

});



function selectcommunity1() {
    $("#selectcommunity1").empty();
    var option = $("<option  value=''>" + "----请选择---" + "</option>");
    $("#selectcommunity1").append(option);
    $('#selectcommunity1').selectpicker('refresh');
    $.ajax({
        type: "post",
        async:false,
        url: "/manager/community/selectcommunity",
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                var data = response[i];
                var option = $("<option  value='" + data.id + "'>" + data.name + "</option>");
                $('#selectcommunity1').append(option);
            }
            $('#selectcommunity1').selectpicker('refresh');
        }
    });
}

function selectbuilding1() {
    var communityid = $('#selectcommunity1').val();
    $.ajax({
        type: "post",
        async:false,
        url: "/manager/building/selectbuilding",
        data: {
            "communityid": communityid
        },
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                var data = response[i];
                var option = $("<option  value='" + data.id + "'>" + data.name + "</option>");
                $('#selectbuilding1').append(option);
            }
            $('#selectbuilding1').selectpicker('refresh');
        }
    });
}

function selectroom1() {
    var buildingid = $('#selectbuilding1').val();
    $.ajax({
        type: "post",
        async:false,
        url: "/manager/room/selectRoomAvailable",
        data: {
            "buildingid": buildingid
        },
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                var data = response[i];
                var option = $("<option  value='" + data.id + "'>" + data.name + "</option>");
                $('#selectroom1').append(option);
            }
            $('#selectroom1').selectpicker('refresh');
        }
    });

}

function clearbuilding1() {
    $("#selectbuilding1").empty();
    var option = $("<option  value=''>" + "----请选择---" + "</option>");
    $("#selectbuilding1").append(option);
    $('#selectbuilding1').selectpicker('refresh');
}

function clearroom1() {
    $("#selectroom1").empty();
    var option = $("<option  value=''>" + "----请选择---" + "</option>");
    $("#selectroom1").append(option);
    $('#selectroom1').selectpicker('refresh');
}

