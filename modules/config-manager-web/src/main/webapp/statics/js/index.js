$(document).ready(function() {
    // 菜单事件绑定
    $("#config").delegate("a", "click",
    function() {
       if ($('#tt').tabs('exists', "配置查询")){
           $('#tt').tabs('select', "配置查询");
       } else {
           var content = '<iframe scrolling="auto" frameborder="0"  src="'+ctp+'/index/query.html" style="width:100%;height:100%;"></iframe>';
           $('#tt').tabs('add',{
               title:"配置查询",
               content:content,
               closable:true
           });
       }
    });
});