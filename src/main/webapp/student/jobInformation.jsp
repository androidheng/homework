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
               <label class="layui-form-label">作业打分</label>
               <div class="layui-input-block">
                  <input type="number" name="score" id="score" required  lay-verify="required"  class="layui-input">
               </div>
           </div>
           <div class="layui-form-item">
             <label class="layui-form-label">评价内容</label>
                 <div class="layui-input-inline">
                   <input type="text" name="content" id="content" required lay-verify="required" placeholder="请输入作业内容" autocomplete="off" class="layui-input">
                 </div>
           </div>
           
           
           <div class="layui-form-item">
            <div class="layui-input-block">
               <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            </div>
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
       <script type="text/html" id="titleTpl">
  {{#  if(d.sstatus == 0){ }}
          未批改
  {{#  } else { }}
         已批改
  {{#  } }}
</script>
    <script type="text/html" id="barDemo">
          <a class="layui-btn layui-btn-info layui-btn-xs" lay-event="downLoad">下载</a>
       {{#  if(d.sstatus==1){ }}
		  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">打分</a>
	   {{#  } else { }}
		
	   {{#  } }}
    </script>
   
    <script>
  layui.use('table', function(){
      var table = layui.table,form = layui.form,$=layui.$;
       //展示已知数据
       table.render({
           elem: '#demo'
          ,toolbar: '#toolbarDemo'
          ,url:'<%=basePath%>swork/searchswork'
          ,cols: [[ //标题栏
             {field: 'score', title: '作业分数', }
            ,{field: 'content', title: '作业内容'}
            ,{field: 'sstatus', title: '批改状态',templet: '#titleTpl'}
            ,{field: 'createtime', title: '创建时间'}
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
  	        	 //监听提交
                  form.on('submit(demo1)', function(data){
                	  data.field.id = row.id
                	  $.ajax({
                          url:"<%=basePath%>swork/stuscore",
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
          if(obj.event === 'downLoad'){
        	  location.href = "<%=basePath%>swork/fileDownLoad?id="+data.id
        	  $(".layui-laypage-btn")[0].click();
          } else {
        	 getCitys(data)
         }
       });
     
});
   </script>
</body>
</html>