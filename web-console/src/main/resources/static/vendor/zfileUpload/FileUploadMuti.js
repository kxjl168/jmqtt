/**
 * 多图片-文件上传组件
 * 
 * 依赖jquery, jquery bootstrap,
 * iot.js
 * 需要配合 FileUploadMuti.css使用
 * 
 * 
 * step1
	//多图片上传-
	$kfile.init({
		fileUploadname:"fileUploadURL", //上传文件的name
		httppath:$("#val2").val(),  //img -static目录前缀
		isimg:true,
		filesufix:'png,jpg,gif,jpeg,',
		maxFileSize:2*1024*1024,//2M
		maximgupload : 4,//最多可上传图片数量
		uploadurl:'/upload/UploadFileXhr',//上传图片action url
		container:$("body").find('#imgs'), //图片容器
	});


	//多附件
	$kfile.init({
		fileUploadname:"fileUploadURL", //上传文件的name
		httppath:$("#val2").val(),  //img -static目录前缀
		maximgupload : 4,//最多可上传图片数量
		isimg:false,
		maxFileSize:2*1024*1024,//2M
		uploadurl:'/upload/UploadFileXhr',//上传图片action url
		container:$("body").find('#attach_files2'), //附件容器
	});
 * 
 * step2
 * //添加默认占位图片 
 * $kfile.get("attach_files2").initFile(callback);
 * 
 * //赋值图片
 * $kfile.get("attach_files2").addFile(item.fileId,$("#val2").val()+item.fileinfo.http_relative_path,oldname);
 * 
 * //获取图片fileids 
 * $kfile.get("attach_files2").getAllFileIds()
 * 
 * 
 * @param window
 * @returns
 * @date 20180611
 * @ahuor zj
 */

;(function(window) {

	function FileUploadMuti(options){
	
		this.default={
				cur_file_id:1,
				num:0,  //总数计算
				indexnum:0,//计数标识唯一
				maximgupload : 4,// 最多可上传图片数量
				minimgupload : 1,// 最少可上传图片数量
				filesufix:'',// 允许上传文件类型后缀. png,jpg.
				isimg:true,// 是否为上传图片
				uploadurl:'',// 上传图片url
				container:$("body").find('#imgs'), // 容器控件
				mform:"#mform",
				fileUploadname:"fileUploadURL",
				maxFileSize:2*1024*1024, //2M 大小，最大允许上传文件大小， byte
				httppath:'',
			    showurl: getRPath()+'/upload/file',//显示图片action url
			    defaultimg:'/img/default.jpg',
			    defaultadd:'/img/add.png',
				notnull:false,// 是否至少一个文件
				notnullmsg:'',//缺少文件提示
		// 
		};
	/*
	 * fileUploadname:"fileUploadURL", //上传文件的name httppath:$("#val2").val(),
	 * //img -static目录前缀 isimg:true, maximgupload : 4,//最多可上传图片数量
	 * uploadurl:'/upload/UploadFileXhr',//上传图片action url
	 * container:$("body").find('#attach_files'), //图片容器
	 */		
		
	} ;
	
	/**
	 * 获取图片fileids
	 */
	FileUploadMuti.prototype.getAllFileIds=function(options){
		
		var me =this;
		
		// 添加图片
		var imgobjs=new Array();
		var imgs= $(me.container).find(".gdfileid");
		$.each(imgs,function(index,item){
			imgobjs.push({id:$(item).val()});
		})
		return JSON.stringify(imgobjs);
	}


	FileUploadMuti.prototype.init=function(options){
		
		

	    this.options = $.extend({}, this.default, options);
	    
	    this.container=$("body").find(this.options.container);
		
	    
	    
	    this.options.modal_id= "myModal_file_"+this.container.attr("id");
	    if(!this.options.isimg)
	    	this.options.isimg=false;
	    else
	    	this.options.isimg=true;
	    
	    if(this.options.notnull&&this.options.notnullmsg=="")
	    	{
	    	if(this.options.isimg)
	    		this.options.notnullmsg="至少需要一张图片";
	    	else
	    		this.options.notnullmsg="至少需要一个附件";
	    	}
	    	
	    
		var  me = this;
	     
	    	 
	    	 me.initDom();
	    	 me.initEvent();
	    	
	    
		
	};
	

	FileUploadMuti.prototype.initEvent=function(e){
			
			var me = this;
		   

			
			// modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用
			 $("body").find("#"+me.options.modal_id).on('show.bs.modal', function(e) {
				 if(me.options.notnull)
				$(me.options.mform).data('bootstrapValidator').resetForm();
			});
			
		
			  $("body").find("#"+me.options.modal_id+  " #uploadSub").off("click").on("click", function(e) {
				 me.uploadfile();
				});


				$("body").find("#btnremoveimg").click(function() {
					// me.removedropimg();
				});

				//window.onload = function() {
					me.dropBox =$("body").find("#"+me.options.modal_id).find(".dropBox")[0];
					me.dropBox.ondragenter =me. ignoreDrag;
					me.dropBox.ondragover = me.ignoreDrag;
					//me.dropBox.ondrop =function(e){ me.drop(e,me)};
					me.dropBox.ondrop = function(e){me.drop(e,me)};
					
				//}
				
			
		};
	
		FileUploadMuti.prototype.initDom=function(e){
			
			var me = this;
			
			
		   
			
	       var dom =$('<div class="">'
	    		   +' <div class="modal fade mdfile " id="'+me.options.modal_id+'" tabindex="-1 " '
	    		   +' data-backdrop="static" role="dialog " aria-labelledby="myModalLabel "> '
	    		   +' <div class="modal-dialog " > '
	    		   +' 	<div class="modal-content   "> '
	    		   +' 		<div class="modal-header "> '
	    		   +' 		<button type="button " class="close hide " data-dismiss="modal " '
	    		   +' 			aria-hidden="true ">&times;</button> '
	    		   +' 		<h4 class="modal-title " id="myModalLabel ">选择文件</h4> '


	    		   +' 	</div> '


					

	    		   +' <div class="row modal-body margin-top-10 "> '

	    	


	    		   +' 		<div class="row col-xs-12"> '
	    		   +' 			<div class="control-label padding-top-0  flabetxt ">图标：</div> '
	    		   +' 		</div> '
	    		   +' 			<div class="row col-xs-12"> '

	    		   +' 				<div class=" text-right "> '
								

	    		   +' 					<div class="dropBox" > '
	    		   +' 						<div class="dropBoxInner"> '
	    		   +' 							<div class="imgtip">将文件拖放到此处...</div> '
	    		   +' 							<div class="filename"></div> '
	    		   +' 							<img id="imgpre" class="imgpre img-responsive"> '


	    		   +' 							<div class="prodiv"> '
	    		   +' 								<hr class="hrback"> '
	    		   +' 									<hr class="hrpro"> '
	    		   +' 							</div> '
	    		   +' 						</div> '

	    		   +' 					</div> '





	    		   +' 				</div> '
	    		   +' 				<div class="row"> '
	    		   +' 					<div class=" col-xs-5  "> '
									
	    		   +' 				<div tabindex="500"  class="btn btn-primary btn-file"> '
	    		   +' 				<i class="glyphicon glyphicon-folder-open"></i>&nbsp;   '
	    		   +' 				<span >选择 …</span> '
	    		   +' 				<input type="hidden" class="fname" /> '
									
									
	    		   +' 				<input type="file"  	name="fileUploadURL" id="fileUploadURL" class="form-control"  data-preview-file-type="text"> '
	    		   +' 				</div> '
	    		   +' 				</div> '
									
	    		   +' 				<button type="button" id="btnremoveimg" tabindex="500" title="清除选中文件" class="hide btn btn-default btn-secondary fileinput-remove fileinput-remove-button"> '
	    		   +' 				<i class="glyphicon glyphicon-trash"></i>  <span class="hidden-xs">移除</span></button> '
									
	    		   +' 				<div class="col-xs-2  "> '
	    		   +' 					<button type="button " class="btn btn-primary" name="uploadSub" '
	    		   +' 						id="uploadSub">上传</button> '
	    		   +' 				</div> '
	    		   +' 				</div> '

	    		   +' 			</div> '


	    		   +' 	</div> '

	    		   +' 	<div class="modal-footer "> '
	    		   +' 			<button type="button " class="btn btn-default btn-warning " '
	    		   +' 			data-dismiss="modal">取消</button> '

	    		   +' 	</div> '
	    		   +' </div> '
	    		   +' </div> '
	    		   +' </div> '
	    		   +' </div> ');
			
	       var spig=$("#"+me.options.modal_id);
	       if(spig.length<=0)
	       	{
	    
	       	$("body").append(dom);
	       	}
	       
	       
	    	  $("body").find("#"+me.options.modal_id+" #fileUploadURL").off("change").on("change",function(e) {
	   			me.processFiles(e.target.files);
	   		});
			
		};
	
	
	/**
	 * 显示上传窗口
	 */
	FileUploadMuti.prototype.uploadimg=function(e){
		
		var me = this;
		
		$("body").find("#"+me.options.modal_id).modal('show');
		me.options.cur_file_id = $(e.target || e).parent().find("img").attr("id");

		me.removedropimg();
	};
			

	/**
	 * 清除弹出选择文件框内容
	 */
	FileUploadMuti.prototype.removedropimg=function(){
		var me =this;
		$("body").find("#"+me.options.modal_id).find("#imgpre").attr("src", "");
		me.hidepro();
		
		$("body").find("#"+me.options.modal_id).find(".imgtip").show();
		if(me.options.isimg)
			{
			$("body").find("#"+me.options.modal_id).find(".flabetxt" ).html("图片:");
			// $("body").find("#"+me.options.modal_id).find(".imgtip").show();
				
			}
		
		else
			{
			$("body").find("#"+me.options.modal_id).find(".flabetxt" ).html("文件:");
			$("body").find("#"+me.options.modal_id).find(".imgpre " ).show();//撑大div
			$("body").find("#"+me.options.modal_id).find(".filename" ).hide();
			$("body").find("#"+me.options.modal_id).find(".filename" ).html("");
			$("body").find("#"+me.options.modal_id).find(".fname").val("");
			$("body").find("#"+me.options.modal_id).find("#fileUploadURL").val("");
			 
			// $("body").find("#"+me.options.modal_id).find(".imgtip").hide();
			}
			
	};
	
	/**
	 * 动态添加的图片框上的删除按钮 事件删除多图片框
	 */
	FileUploadMuti.prototype.removeimg=function(e){
		var me=this;
		
		var id=$(e.target).parent().find(".gdfileid").attr("index");
		me.container.find("#fullurl" + id).parent().remove();

		var tnum = me.options.num - 1;
		me.options.num =tnum;

		


		if (tnum <me.options. maximgupload)
			me.container.find("#imgadd").show();
		
		
		//对于文件上传，清除最后一个文件时，默认再添加一个空的文件框
		if(tnum==0&&
			!me.options.isimg)
			{
			me.addFile();
			}
		
		
		
	}

			
	/**
	 * 初始化/新增或者编辑时使用
	 */
	FileUploadMuti.prototype.initFile=function(callback,maxnum){
		var me = this;
		
		me.container.html("");
		// $("#files_count").val(0);
		me.options.num =0;
		

		var hide = " ";

		me.options.maximgupload =me.options.maximgupload|| maxnum ;

		
		var center=" center ";
		
		if( !me.options.isimg)
		{
			center=" ";
			
		}
		
		
		
		
		if(me.options.maximgupload==1)
			col="  row ";
		else if(me.options.maximgupload==2)
			col=" col-xs-4 row ";
		else if(me.options.maximgupload==3)
			col=" col-xs-3 row ";
	
		
		
		
		var add = $(' <div id="imgadd" class=" '
				+ hide
				+ col+'   row margin-top-5 gdcontainer"> '

				+ ' <img src="'+me.options.defaultadd+'"  class=" '+center+' img-responsive "></img> '

				+ ' </div> ');

		me.container.append(add);
		
		me.container.find("#imgadd").off("click").on("click",function(){me.addFile();});
		
	


		me.addFile();
		
		if(me.options.minimgupload>1)
			{
			for (var i = 1; i < me.options.minimgupload; i++) {
				me.addFile();
			}
			}
		

		if(me.options.notnull)
			{
			for (var i = 0; i < me.options.minimgupload; i++) {
				me.updateNormalValidate(me.options.mform,$( $(me.options.container).find(".gdfileid")[i]),
				me.options.notnullmsg);// "至少需要一张图片");
			}
			}
			
			
	

		if (typeof (callback) == "function")
			callback();
	}

			
			
	FileUploadMuti.prototype.resetValidate=function(){
		var me = this;
		$.each(validateFileds,function(index,item){
			me.updateNormalValidate(me.options.mform,item,"",true);
		})
	};
	
	
	var validateFileds=new Array();
	
	

				/**
				 * 动态添加图片验证
				 * 
				 * @param ele
				 * @param fieldname
				 * @param text
				 * @param isremove
				 * @returns
				 */
	FileUploadMuti.prototype.updateNormalValidate=function(ele, fieldnameOrEle, messagetext, isremove){
		var me = this;
		
		if (isremove)
			$(ele).bootstrapValidator("removeField", fieldnameOrEle);
		else {
			$(ele).bootstrapValidator("addField", fieldnameOrEle, {
				validators : {
					notEmpty : {
						message : messagetext
					},

				}
			});
			
			validateFileds.push(fieldnameOrEle.attr("name"));
		}
	};
	
	
	/**
	 * 添加图片/id为空则添加空占位图片 id不为空则优先填充占用图片 然后新增图片
	 */
	FileUploadMuti.prototype.addFile=function(id, url,oldname){
		var me = this;
		var tnum = me.options.num + 1;
		var index = me.options.indexnum + 1;
		

		var hide = " hide ";
		if (tnum > me.options.minimgupload)
			hide = "";

		id = id || "";
		url = url || me.options.defaultimg;

		if (id != "") {
			var eitem = null;
			var isexistnullimg = false;
			
			$.each(me.container.find(".gdfileid"), function(index, item) {
				if ($(item).val() == "") {
					isexistnullimg = true;
					eitem = item;
				}

			});
			
			if (isexistnullimg) {
				$(eitem).val(id);
				$(eitem).parent().find(".gdtext").val(oldname);
				$(eitem).parent().find(".gdimg").attr("src", url);
				//$(eitem).parent().find(".gddownlink").attr("href", url);

				//对于文件上传，可以清除第一个文件内容
				if(!me.options.isimg)
					{
					$(eitem).parent().find(".gddownlink").removeClass("hide");
					$(eitem).parent().find(".close").removeClass("hide");
					}
				
				
				return;
			}
		}

		
		var showtext=" hidevalidate hide ";
		var showimg=" ";
		var col=" col-xs-3 row ";
		
		if(me.options.maximgupload==1)
			col=" col-xs-10 row ";
		else if(me.options.maximgupload==2)
			col=" col-xs-5 row ";
		else if(me.options.maximgupload==3)
			col=" col-xs-4 row ";
		else
			 col=" col-xs-3 row ";
		
		if( !me.options.isimg)
		{
			 showtext=" ";
			 showimg=" hide ";
			 col=" col-xs-10 ";
		}
		
		var downvisible=" hide ";
		oldname=oldname||"";
		if(oldname!="")
			downvisible="";
		
		var target = $(' <div class=" '+col+'  gdcontainer newcontainer"> '
				+ '  <input   readonly="readonly" '
				+ ' class=" form-control gdfileid  hidevalidate" '
				+ ' type="text" name="fullurl'+index+'_fileId" index="'+index+'"   id="fullurl'
				+ index
				+ '_fileId"  value="'
				+ id
				+ '"  placeholder=" "> '
				+ '  <input   readonly="readonly" '
				+ ' class=" form-control gdtext col-xs-10 '+showtext+' " '
				+ ' type="text" name="fileId" index="'+index+'"  id="fullurl'
				+ index
				+ '_oldname"  value="'
				+ oldname
				+ '"  placeholder=" "> '
				
				
				+ '  <a'
				+ ' class="  gddownlink  '+downvisible+' " '
				+ ' href="javascript:void(0)"  target="_blank"  id="fullurl'
				+ index
				+ '_downlink"  value="'
				+ url
				+ '"  placeholder=" ">下载</a> '
				
				
				+ ' 	 <span class="  '
				+ hide
				+ 'close imgclose " '
				
				+ ' >&times;</span> '

				+ ' <img id="fullurl'
				+ index
				+ '"  src="'
				+ url
				+ '" class="img-responsive gdimg '+ showimg +'"></img> '
				+ ' </div> ');

		
		
		
		me.options.num= tnum;
		me.options.indexnum= index;

		target.insertBefore(me.container.find("#imgadd"));

		if (tnum == me.options.maximgupload)
			me.container.find("#imgadd").hide();
		
		
		
		me.container.find(".gddownlink").off("click").on("click",function(e) {
			me.download(e);
		});
		
		
		me.container.find(".gdimg").off("click").on("click",function(e) {
			me.uploadimg(e);
		});
		
		me.container.find(".gdtext").off("click").on("click",function(e) {
			me.uploadimg(e);
		});
		
		me.container.find(".imgclose").off("click").on("click",function(e) {
			me.removeimg(e);
			
			
			
		});
		
		
		
	}
	
	
			
	FileUploadMuti.prototype.download=function(e){
		
		
		var id=$(e.target || e).parent().find(".gdfileid").val();
		window.location.href="/upload/downFile?id="+id;
	}		

			

	FileUploadMuti.prototype.ignoreDrag=function(e){
		var me = this;
		// 因为我们在处理拖放，所以应该确保没有其他元素会取得这个事件
		e.stopPropagation();
		e.preventDefault();
		
	}
	
	/**
	 * 拖拽drop
	 */
	FileUploadMuti.prototype.drop=function(e,instance){
	
		// 取消事件传播及默认行为
		e.stopPropagation();
		e.preventDefault();

		// 取得拖进来的文件
		var data = e.dataTransfer;
		var files = data.files;

		// 将其传给真正的处理文件的函数
		this.processFiles(files);
	}
		
	/**
	 * 文件预览
	 */
	FileUploadMuti.prototype.processFiles=function(files){
		
		
		
			var me = this;
			$("#"+me.options.modal_id).find(".fname").val(files[0].name);
			me.fileObj = files[0];
			$("#"+me.options.modal_id).find(".imgtip").hide();

			// var output = document.getElementById("fileOutput");
			// 创建FileReader
			var reader = new FileReader();
			// 告诉它在准备好数据之后做什么
			reader.onload = function(e) {
				// 使用图像URL来绘制dropBox的背景
				if(/image/.test(files[0].type)){// 操作图像
					$("#"+me.options.modal_id).find(".filename").hide();
					$("#"+me.options.modal_id).find(".imgpre").show();
					$("#"+me.options.modal_id).find(".imgpre").attr("src", "" + e.target.result + "");
				}
				else
				{
					$("#"+me.options.modal_id).find(".filename").show();
					$("#"+me.options.modal_id).find(".filename").html(files[0].name);
					$("#"+me.options.modal_id).find(".imgpre").hide();
				}
				
				// $("body").find(".imgpre").src = me.options.httppath+
				// "/img/default.jpg";// + e.target.result + "";
			};
			// 读取图片
			reader.readAsDataURL(me.fileObj);
	}
	
	
	FileUploadMuti.prototype.error=function(msg){
	if(typeof(error)=="function") error(msg);
	else
		alert(msg);
	
	return;
	}
	
	FileUploadMuti.prototype.info=function(msg){
		if(typeof(info)=="function") info(msg);
		else
			alert(msg);
		return;
		}
	

			
	/**
	 * xhr上传文件
	 */
	FileUploadMuti.prototype.uploadfile=function(e){
		var me = this;

		var fileName = $("#"+me.options.modal_id).find(".fname").val();
		if (fileName != null && "" != fileName) {
			var fileExtension = fileName.substring(fileName
					.lastIndexOf(".") + 1, fileName.length);
			
			
			var allow=false;
			if(me.options.filesufix!="")
			{
				
			var types=me.options.filesufix.split(',');
			
			$.each(types,function(index,item){
				if(item==fileExtension)
					{
					 allow=true;
					}
				});
			}
			else
			{
				allow=true;
			}
			
			if(!allow)
				{
				me.error("请上传"+me.options.filesufix+"格式的文件！");
				eturn;
				}
			
			if(me.fileObj.size==0)
				{
				me.error("文件为空!不能上传大小为0的文件！");
				
				return;
				}
			if(me.options.maxFileSize<me.fileObj.size)
				{
				me.error("文件过大!最大允许"+me.options.maxFileSize/1024.0+"Kb的文件！");
			
				return;
					
				}
			
			
	/*
	 * if ("png" == fileExtension || "jpeg" == fileExtension || "ico" ==
	 * fileExtension || "gif" == fileExtension || "jpg" == fileExtension) {
	 */

				var url = me.options.uploadurl||"/upload/UploadFileXhr"; // 接收上传文件的后台地址

				var form = new FormData(); // FormData 对象
				form.append(me.options.fileUploadname, me.fileObj); // 文件对象

				me.xhr = new XMLHttpRequest(); // XMLHttpRequest 对象
				me.xhr.open("post", url, true); // post方式，url为服务器请求地址，true
				// 该参数规定请求是否异步处理。
				me.xhr.onload =function(evt){ me.uploadComplete(evt,me)}; // 请求完成
				me.xhr.onerror = me.uploadFailed; // 请求失败

				// xhr.responseType ="document"; "text" String字符串
				// "document" Document对象 希望返回 XML 格式数据时使用
				// "json" javascript 对象 存在兼容性问题，IE10/IE11不支持
				// "blob" Blob对象
				// xhr.responseType =

				me.xhr.upload.onprogress = function(evt){me.progressFunction(evt,me)};// 【上传进度调用方法实现】
				me.xhr.upload.onloadstart = function() {// 上传开始执行方法
					ot = new Date().getTime(); // 设置上传开始时间
					oloaded = 0;// 设置上传开始时，以上传的文件大小为0
				};

				me.xhr.send(form); // 开始上传，发送form数据

				// ajaxLoading();
				me.showpro();

			
		/*
		 * } else { if(typeof(error)=="function") error("请上传图片格式的文件！"); }
		 */
		} else {
			
			me.error("请选择上传文件！");
			// $("#uploadState").hide();
		}
	}		


	
	FileUploadMuti.prototype.uploaddone=function(jsonStr){
		var me = this;
		var obj = JSON.parse(jsonStr);
		
		if(obj.ResponseCode!=200)
			{me.error("上传失败！"+obj.ResponseMsg); 
			 return;
			}

		// var obj =
		// document.getElementById("fileUploadFrame").contentWindow;
		var uploadUrl = null;
		var fileMd5 = null;
		var fileid = null;
		// if(obj.document.getElementById("uploadUrl")!=null)
		uploadUrl = obj.relativeURL;
		fileMd5 = obj.md5;

		// if(obj.document.getElementById("returnMsg")!=null)
		returnMsg = obj.ResponseMsg;

		if (uploadUrl != null && uploadUrl != "") {
			
				// $("#URL").val(uploadUrl);

				me.container.find("#" + me.options.cur_file_id + "_fileId").val(
						fileMd5);

				/*me.container.find("#" + me.options.cur_file_id).attr("src",
						me.options.httppath + uploadUrl);*/
				
				me.container.find("#" + me.options.cur_file_id).attr("src",
						me.options.showurl +"/"+ fileMd5);
				
				
				me.container.find("#" + me.options.cur_file_id+"_oldname").val(obj.oldname);
				
				
				me.container.find("#" + me.options.cur_file_id+"_downlink").attr("href",me.options.showurl +"/"+ fileMd5);
				if(!me.options.isimg)
				me.container.find("#" + me.options.cur_file_id+"_downlink").removeClass("hide");

				$("body").find("#"+me.options.modal_id).modal("hide");

				
				me.info("文件上传成功！");
			

		}
	}
	
	/**
	 * xhr complete
	 */
	FileUploadMuti.prototype.uploadComplete=function(evt,instance){
		
		instance.uploaddone(evt.target.responseText);
	}
	
	FileUploadMuti.prototype.uploadFailed=function(jsonStr){
	
		me.error("上传失败！");
	}
	
	// 取消上传
	FileUploadMuti.prototype.cancleUploadFile=function(jsonStr,instance){
		
		
		instance.xhr.abort();
	}
	
	/**
	 * 显示上传进度条
	 */
	FileUploadMuti.prototype.showpro=function(jsonStr){
		var me = this;
		$("#"+me.options.modal_id).find(".hrback").show();
		$("#"+me.options.modal_id).find(".hrpro").show();
	}
	

	// 隐藏上传进度条
	FileUploadMuti.prototype.hidepro=function(jsonStr){
		var me = this;
		$("#"+me.options.modal_id).find(".hrback").hide();
		$("#"+me.options.modal_id).find(".hrpro").hide();
	}
	
	// 进度条计算
	FileUploadMuti.prototype.progressFunction=function(evt,instance){
		
		
		if (evt.lengthComputable) {
			// progressBar.max = evt.total;
			// progressBar.value = evt.loaded;
			var per = Math.round(evt.loaded / evt.total * 100);
			// "%";

			$("#"+instance.options.modal_id).find(".hrpro").css("width", per + "%");

			if (per == 100)
				instance.hidepro();

		}
	}
	
	
	window.$kfile={};
	window.$kfile.instance={};
	
	
	window.$kfile.init=function(options){
				
				var obj=null;
				var cname=$(options.container).attr("id");
				
				if(typeof(window.$kfile.instance[cname])!="undefined")
					obj=window.$kfile.instance[cname];
				else
					{
					 obj=new FileUploadMuti();
					 obj.init(options);
					 window.$kfile.instance[cname]=obj;
					}
				
				return obj;
				
			};
			
	window.$kfile.get=function(name){
				if(typeof(window.$kfile.instance[name])!="undefined")
					return window.$kfile.instance[name];
				else
					return null;
			}
	

})(window);

