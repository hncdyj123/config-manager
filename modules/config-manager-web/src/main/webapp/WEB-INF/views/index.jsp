<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
	<link href="<%=request.getContextPath() %>/statics/esayui/themes/bootstrap/easyui.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/statics/esayui/themes/icon.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/statics/css/app.css" rel="stylesheet">
	<script src="<%=request.getContextPath() %>/statics/jquery/jquery-1.12.3.min.js"></script>
	<script src="<%=request.getContextPath() %>/statics/esayui/js/jquery.easyui.min.js"></script>
	<script src="<%=request.getContextPath() %>/statics/esayui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		var ctp = "<%=request.getContextPath() %>";
	</script>
    <script src="<%=request.getContextPath() %>/statics/js/index.js"></script>
</head>
<body>
  <div class="easyui-layout" style="width:100%;height:100%;">
    <div data-options="region:'west',split:true" title="业务菜单" style="width:220px;">
      <div id="config" class="easyui-accordion" data-options="fit:true,border:false">
        <div title="配置管理">
          <a id="leaf" href="#" class="easyui-linkbutton">配置查询</a>
          <a id="leaf" href="#" class="easyui-linkbutton">配置查询</a>
        </div>
      </div>
    </div>
    <div data-options="region:'center',iconCls:'icon-ok'">
      <div id="tt" class="easyui-tabs" data-options="fit:true,border:false,plain:true" style="width:100%;height: 100%">
        <div title="我的工作区" style="padding:5px" data-options="iconCls:'icon-man'">
          <p>欢迎来到配置管理系统.</p>
        </div>
      </div>
    </div>
</body>
</html>