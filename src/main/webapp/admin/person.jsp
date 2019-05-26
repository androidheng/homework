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
               <label class="layui-form-label">用户名</label>
               <div class="layui-input-block">
                  <input type="text" name="username" id="username" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
               </div>
           </div>
           <div class="layui-form-item">
             <label class="layui-form-label">密码</label>
                 <div class="layui-input-inline">
                   <input type="text" name="password" id="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                 </div>
           </div>
           <div class="layui-form-item">
            <div class="layui-input-block">
               <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
               <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
          </div>
        </form>
       
     </div>
    <div class="layui-content">
        <div class="layui-page-header">
            <div class="pagewrap">
                <span class="layui-breadcrumb">
                  <a>首页</a>
                  <a>用户信息</a>
                </span>
                <h2 class="title">用户信息</h2>
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
     <script type="text/html" id="toolbarDemo">
     <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加用户</button>
     </div>
    </script>
    <script type="text/html" id="barDemo">
       <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
       <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
     	<script src="<%=basePath%>assets/layui.all.js"></script>
    <script>
  layui.use('table', function(){
      var table = layui.table,form = layui.form,$=layui.$;
       //展示已知数据
       table.render({
           elem: '#demo'
          ,toolbar: '#toolbarDemo'
          ,url:'<%=basePath%>user/search'
          ,cols: [[ //标题栏
             {field: 'username', title: '用户名', }
            ,{field: 'password', title: '密码'}
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
       function getoption(data){
    	    $.ajax({
                url:"<%=basePath%>course/findAll",
                type:'post',//method请求方式，get或者post
                dataType:'json',//预期服务器返回的数据类型
                contentType: "application/json; charset=utf-8",
                success:function(res){//res为相应体,function为回调函数
               	
                    let options = "<option value=''></option>"
                    res.forEach(item=>{
                   	 options+="<option value='" + item.id + "'>" + item.coursename + "</option>";
                    })
                   
                    $("#cid").html(options)
                    renderForm()
                },
                error:function(){
                 
                }
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
  	        	 layui.use('laydate', function(){
  	 	     	  var laydate = layui.laydate;
  	 	          //执行一个laydate实例
  	 	     	  laydate.render({
  	 	     	    elem: '#endtime', //指定元素
  	 	     	  });
  	 	          console.log(row)
  	 	     	  row&&(row.id = +row.id)
  	 	          row?form.val('formTest', row): $("#addGoodsForm")[0].reset();
  	 	         
  	 	     	 //监听提交
                  form.on('submit(demo1)', function(data){
                	  row&&(data.field.id = row.id)
                	  $.ajax({
                          url:" <%=basePath%>user/addOrUpdate",
                          type:'post',//method请求方式，get或者post
                          dataType:'json',//预期服务器返回的数据类型
                          data:JSON.stringify(data.field),
                          contentType: "application/json; charset=utf-8",
                          success:function(res){//res为相应体,function为回调函数
                        	  layer.close(index)
                              $(".layui-laypage-btn")[0].click();
                          },
                          error:function(){
                           
                          }
                      });
                  
                    return false;
                  });
  	          });
  	        }
  	       ,end:function(index){
  	    	    $("#box").hide()
  	        	layer.close(index)
  	        }
  	      });
  	   
       }
       //头工具栏事件
       table.on('toolbar(demo)', function(obj){
         switch(obj.event){
           case 'add':
             getCitys()
           break;
         
         };
       });
       //监听行工具事件
       table.on('tool(demo)', function(obj){
         var data = obj.data;
         //console.log(obj)
         if(obj.event === 'del'){
           layer.confirm('真的删除行么', function(index){
        	  $.ajax({
                   url:"<%=basePath%>user/delete",
                   type:'post',//method请求方式，get或者post
                   dataType:'json',//预期服务器返回的数据类型
                   data:JSON.stringify({id:data.id}),
                   contentType: "application/json; charset=utf-8",
                   success:function(res){//res为相应体,function为回调函数
                	   obj.del();
                       layer.close(index);
                       $(".layui-laypage-btn")[0].click();
                    
                   },
                   error:function(){
                       layer.alert('操作失败！！！',{icon:5});
                   }
                 });
           
           });
         } else if(obj.event === 'edit'){
        	 getCitys(data)
         } 
       });
     
});
   </script>
</body>
</html>