
<div class="panel-left  layout-panel layout-panel-west" style="">
    <div class="panel-header">
        <div class="panel-title">
            <p class="centered">
					<a href="#"> <img src="${ctx}/img/morentouxiang.png"
						class="img-circle" style="width: 100px; height: 100px;">
					</a>
				</p>

				<h5 class="centered">${principal.userName!""}</h5>
        </div>
        <div class="panel-tool">
            <a href="javascript:void(0)" class="panel-tool-collapse" style="display: none;"></a>
            <a href="javascript:void(0)" class="layout-button-left"></a>
        </div>
    </div>
    
    

  
	<div id="sidebar" class="panel-body layout-body"  style="z-index: 1000;">



		<ul class="nav-sidebar">

		
			
			<#list principal.userMenus as menu>

			
			<li class="menu1"><a href="javascript:;"><i class="micon ${menu.icon?default('fa fa-cube')}"></i>
					${menu.name}<span class=" fa fa-chevron-right"></span></a>
				<ul class="nav nav-second-level " style="display: none;">
					<#assign persissions = menu.permissions> <#list persissions as
					permission>
					<li><a href="${ctx}${permission.url}">${permission.name}</a></li>
					</#list>
				</ul></li> 
				
			</#list>


		</ul>


	</div>
</div>


<script>
	$(function() {
		var url = window.location;
		var element = $('#sidebar li a').filter(function(index, item) {
			$(item).removeClass('active');
			var isin = false;
			if ($(item).attr("href") != null && $(item).attr("href") !== "#")
				isin = url.href.indexOf(item.href) > -1;
			else
				isin = false;
			return isin;
		}).addClass('active').parent();


		   $("#sidebar").on('mouseleave',' .menu1 ',function(){
				 $("#sidebar .nav-sidebar ul").hide();
			    });

			    
			    $('#sidebar').on('mouseover','.nav-sidebar li a',function(e){//显示隐藏
			    	e.stopPropagation();
			        $('.nav-sidebar a').removeClass('active');
			        $(this).addClass('active');
			        $(this).parents("ul").prev().addClass('active');
			    	
			        $(this).parent().parent().find('ul').hide();
			    	$(this).parent().children('ul').show();
			    	if($(this).parent().find('li').length){
			    		$(this).parent().children('ul').show();
			    	}
			    });

		
	})
</script>