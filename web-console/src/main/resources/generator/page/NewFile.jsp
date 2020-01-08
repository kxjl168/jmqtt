<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>








<#list fields >
					   <#if type==normal>
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">[[displayName]]</label>

										<div class="col-lg-9">
											<input type="text" name="[[id]]" class="form-control" id="[[id]]"
												placeholder="[[displayName]]" >
											<p class="help-block"></p>
										</div>
									</div>
						</#if>
						 <#if type==select>
								<div class="form-group">
										<label for="name" class="col-lg-3 control-label">[[displayName]]</label>

										<div class="col-lg-9">
											<select name="type" class="form-control" id="type">

												<option value="1">一级菜单</option>
												<option value="2" selected="selected">二级菜单</option>
												<option value="3">按钮</option>
											</select>
											<p class="help-block"></p>
										</div>
									</div>
						</#if>
					</#list>




</body>
</html>