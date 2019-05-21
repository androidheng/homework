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
            <label class="layui-form-label">选择文件</label>
            <div class="layui-input-inline">
              <button type="button" class="layui-btn" id="test1">
                     <i class="layui-icon">&#xe67c;</i>上传作业
              </button>
            </div>
           </div>
           <div class="layui-form-item">
              <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
           </div>
        </form>
       
     </div>
    <div class="layui-content">
        <div class="layui-page-header">
            <div class="pagewrap">
                <span class="layui-breadcrumb">
                  <a>首页</a>
                  <a>作业</a>
                </span>
                <h2 class="title">作业信息</h2>
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
     
       {{#  if(d.tstatus==0){ }}
		  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">提交作业</a>
	   {{#  } else { }}
		
	   {{#  } }}
    </script>
    <script type="text/html" id="titleTpl">
  {{#  if(d.tstatus == 0){ }}
          未批改
  {{#  } else { }}
         已批改
  {{#  } }}
</script>
   
    <script>
  layui.use('table', function(){
      var table = layui.table,form = layui.form,$=layui.$;
      var urlPath = ''
       //展示已知数据
       table.render({
           elem: '#demo'
          ,url:'<%=basePath%>swork/searchwork'
          ,cols: [[ //标题栏
             {field: 'workname', title: '作业名称', }
            ,{field: 'content', title: '作业内容'}
            ,{field: 'endtime', title: '截止时间'}
            ,{field: 'tstatus', title: '批改状态',templet: '#titleTpl'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
         ]]
        ,skin: 'line' //表格风格
        ,even: true
        ,page: true //是否显示分页
        ,limits: [5, 7, 10]
        ,limit: 5 //每页默认显示的数量
       });
      
      
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
  	        	urlPath = ''
  	        	layui.use('upload', function(){
  	        	  var upload = layui.upload;
  	        	
  	        	  //执行实例
  	        	  var uploadInst = upload.render({
  	        	    elem: '#test1' //绑定元素
  	        	    ,accept:'file'
  	        	    ,url: '<%=basePath%>upload' //上传接口
  	        	    ,done: function(res){
  	        	    	urlPath = res.message
  	        	    	
  	        	      //上传完毕回调
  	        	    }
  	        	    ,error: function(){
  	        	      //请求异常回调
  	        	    }
  	        	  });
  	        	});
  	           //监听提交
                form.on('submit(demo1)', function(data){
                 if(!urlPath)return alert('请先上传作业')
              	 let parames = {
              		 ttid:row.id,
              		 url:urlPath,
              	 }
              	  $.ajax({
                        url:"<%=basePath%> swork/add",
                        type:'post',//method请求方式，get或者post
                        dataType:'json',//预期服务器返回的数据类型
                        data:JSON.stringify(parames),
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
        	 getCitys(data)
         } else {
        	 
         }
       });
     
});
   </script>
</body>
</html>