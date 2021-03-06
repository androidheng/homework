<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common.jsp" %>
<!DOCTYPE html >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="icon" href="/favicon.ico">
    <link rel="stylesheet" href="<%=basePath%>assets/css/layui.css">
	<link rel="stylesheet" href="<%=basePath%>assets/css/view.css"/>
    <title>管理后台</title>
</head>
<body class="layui-view-body">
     <div class="layui-content" id="box" style="display:none">
          <form class="layui-form" action="" lay-filter="formTest" id="addGoodsForm">
          
           <div class="layui-form-item">
             <label class="layui-form-label">作业内容</label>
                 <div class="layui-input-inline">
                   <input type="text" name="content" disabled id="content"  autocomplete="off" class="layui-input">
                 </div>
           </div>
           <div class="layui-form-item">
              <label class="layui-form-label">作业图片</label>
                <div class="layui-input-block" id="logo">
                   <img src=""></img>
               </div>
           </div>
        </form>
       
     </div>
    <div class="layui-content">
        <div class="layui-page-header">
            <div class="pagewrap">
                <span class="layui-breadcrumb">
                  <a>首页</a>
                  <a>课程</a>
                </span>
                <h2 class="title">课程信息</h2>
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-card">
                <div class="layui-card-body">
                    <div class="form-box">
                        <table id="demo" lay-filter="demo" ></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
   </div>
  	<script src="<%=basePath%>assets/layui.all.js"></script>
    
    <script type="text/html" id="barDemo">
       <a class="layui-btn layui-btn-xs" lay-event="checkDetail">查看详情</a>
       {{#  if(d.stat == 0){ }}
		  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">选课</a>
	   {{#  } else { }}
		
	   {{#  } }}
    </script>
   
    <script>
  layui.use('table', function(){
      var table = layui.table,form = layui.form,$=layui.$;
       //展示已知数据
       table.render({
           elem: '#demo'
         
          ,url:'<%=basePath%>course/searchcourse'
          ,cols: [[ //标题栏
             {field: 'coursename', title: '课程名称', }
            ,{field: 'tname', title: '老师名字'}
            
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
         ]]
        ,skin: 'line' //表格风格
        ,even: true
        ,page: true //是否显示分页
        ,limits: [5, 7, 10]
        ,limit: 5 //每页默认显示的数量
       });
       //重新渲染表单
       function renderForm(){
        layui.use('form', function(){
        var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
        form.render();
        });
       }
      
       //打开添加站点弹窗
       function getCitys(data){
    	   let row = data
    	   layer.open({
  	         type: 1
  	        ,title: false //不显示标题栏
  	        ,closeBtn: true
  	        ,area: ['600px','400px']
  	        ,shade: 0.8
  	        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
  	        
  	        ,btnAlign: 'c'
  	        ,moveType: 1 //拖拽模式，0或者1
  	        ,content: $("#box")
  	        ,success:function(layero, index){
  	        	$("#logo img").attr('src',data.logo) 
  	        	$("#content").val(data.content) 
  	         
  	        	
  	        }
  	       ,end:function(index){
  	    	    $("#box").hide()
  	        	layer.close(index)
  	        }
  	      });
  	   
       }
     
       //监听行工具事件
       table.on('tool(demo)', function(obj){
         var data = obj.data;
         if(obj.event === 'del'){
        	 $.ajax({
                 url:"<%=basePath%>select/add",
                 type:'post',//method请求方式，get或者post
                 dataType:'json',//预期服务器返回的数据类型
                 data:JSON.stringify({cid:data.id}),
                 contentType: "application/json; charset=utf-8",
                 success:function(res){//res为相应体,function为回调函数
              	   $(".layui-laypage-btn")[0].click();
                 },
                 error:function(){
                     layer.alert('操作失败！！！',{icon:5});
                 }
               });
         } else {
        	 getCitys(data)
         }
       });
     
});
   </script>
</body>
</html>