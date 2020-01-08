/**
 * 房间批量操作
 */

// uuid
function guid() {
	function S4() {
		return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
	}
	return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4()
			+ S4() + S4());
}

// num传入的数字，n需要的字符长度 ，批量添加房间数，房号计算，左加0
function PrefixInteger(num, n) {
	return (Array(n).join(0) + num).slice(-n);
}

// 添加单个房间
function addOneRoom(buildingid, layer, roomnum, roomName) {
	var html = "";
	// 每一个房间
	html += ' '
			+ ' <li class="roomitem"><input class="room_id" type="hidden" name="room_id" value="'
			+ buildingid + "_" + layer + "_" + roomnum + '"> '
			+ ' <num title="" class="tooltip-f">' + roomName
			+ '</num><span></span></li> '

	// 插入到添加按钮前面
	$(html).insertBefore($(".roomNo").find(".f_" + layer).find('.addRoom'));

	// 房间数
	var roomcount = $(".roomNo").find(".f_" + layer).find('.roomitem').length;
	// 每层最大房间数
	var roommaxNumPerFloor = $("#mform_r").find("#roomNumber").val();

	// 计算是否要换行
	var rownum = roomcount / 4;
	var rowheight = rownum * 30 + 22;
	var headStyle = "height: " + rowheight + "px; line-height: " + rowheight
			+ "px;";

	var headHeight = $(".roomNo").find(".f_" + layer).find('.floorNo').height();
	if (headHeight < rowheight) {
		// 超高，替换
		$(".roomNo").find(".f_" + layer).find('.floorNo').attr("style",
				headStyle);
	}

	ShowOrHideAddRoomBtn(layer);
	//

}

//load楼栋的全部房间
//response 为 接口返回所有房间数据
function listAllRoomInbuild(response){
	var curfloor=0;
	var lastfloorRoomnum=0;
	var html="";
	
	// 每层最大房间数
	var roommaxNumPerFloor = $("#mform_r").find("#roomNumber").val();
	
	$.each(response.rows,function(index,item){
		
		//上层结束
		if(item.layer!=curfloor)
			{
			curfloor=item.layer;
			
			
			//每层开始设置上层的开头的高度
			var rownum=lastfloorRoomnum/4;
			var rowheight=rownum* 30+22;
			html=html.replace("{{headstyle}}","height: "+rowheight+"px; line-height: "+rowheight+"px;");
			
			
			if(curfloor!=1)
				{
			//添加在每一层的结尾
				
			if(lastfloorRoomnum< parseInt( roommaxNumPerFloor))
					{
				//没加满才可见添加按钮
				html+=' <li class="addRoom"></li> '
				
			
					}
			
			//一层div闭环
			html+='</ul> '
			+'</li>	';
			
				}
			
		
			//下层开始
			html+='<li class="floor f_'+curfloor+'" "><ul class="clear">'
			+' <li   layer="'+curfloor+'" class="floorNo " style="{{headstyle}}">'+curfloor+'层</li> ';
			
			
			//重置房间计数
			lastfloorRoomnum=0;
			}
		
		
		//每一个房间
		html+=' '
		+' <li class="roomitem"><input class="room_id" type="hidden" name="room_id" value="'+item.id+'"> '
		+' <num title="" class="tooltip-f">'+item.name+'</num><span></span></li> '
	;
		
		lastfloorRoomnum++;
		
	
		
		
	});
	
	//最后一层结束
	//上层结束
	html+=
		' <li class="addRoom"></li> '
		+'</ul> '
		+'</li>	';
	
	$(".roomNo").html(html);
	
	
	
}


// 是否显示该层的添加按钮
function ShowOrHideAddRoomBtn(layer) {
	// 房间数
	var roomcount = $(".roomNo").find(".f_" + layer).find('.roomitem').length;
	// 每层最大房间数
	var roommaxNumPerFloor = $("#mform_r").find("#roomNumber").val();
	//是否达最大房间数
	if (roomcount >= parseInt( roommaxNumPerFloor))
		$(".roomNo").find(".f_" + layer).find('.addRoom').hide();
	else {
		$(".roomNo").find(".f_" + layer).find('.addRoom').show();
	}

}

// 添加一层的全部房间
function addAllRoom(buildingid, layer, roomnum) {

	if ($(".roomNo").find(".f_" + (layer)).length > 0) {
		error("要添加的 '层' 已存在,不可批量创建");
		return;
	}

	// 每层开始设置上层的开头的高度
	var rownum = roomnum / 4;
	var rowheight = rownum * 30 + 22;
	var headStyle = "height: " + rowheight + "px; line-height: " + rowheight
			+ "px;";

	var html = "";
	// 下层开始
	html += '<li class="floor f_' + layer + '" "><ul class="clear">'
			+ ' <li layer="' + layer + '" class="floorNo" style="' + headStyle
			+ '">' + layer + '层</li> ';

	var widthsize = roomnum.toString().length;
	if (widthsize < 2)
		widthsize = 2; // 默认至少2位 房间号:701
	for (var i = 0; i < roomnum; i++) {

		// 每一个房间
		html += ' '
				+ ' <li class="roomitem"><input class="room_id" type="hidden" name="room_id" value="'
				+ buildingid + "_" + layer + "_" + i + '"> '
				+ ' <num title="" class="tooltip-f">' + layer + ""
				+ PrefixInteger(i + 1, widthsize) + '</num><span></span></li> ';

	}

	// 第一层
	html += ' <li class="addRoom"></li> ' + '</ul> ' + '</li>	';

	var find = false;
	for (var i = 0; i < layer; i++) {
		// 一层层比较，反向比较，添加到比当前层小的 最大的层后面
		if ($(".roomNo").find(".f_" + (layer - i - 1)).length > 0) {
			$(html).insertAfter($(".roomNo").find(".f_" + (layer - i - 1)));
			find = true;
			return;
		}

	}
	// 没找到比自己小的，插入父dom第一个
	if (!find)
		$(html).prependTo($(".roomNo"));
	
	ShowOrHideAddRoomBtn(layer);

}
