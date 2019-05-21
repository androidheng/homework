<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=basePath%>assets/css/layui.css">
    <link rel="stylesheet" href="<%=basePath%>assets/css/login.css">
    <link rel="icon" href="/favicon.ico">
    <title>管理后台</title>
    <style>
       h4{
         width:100%;
         text-align:center;
         margin-bottom:10px;
       }
       .layui-form-label{
         text-align:left;
       }
       .layui-form-label{
         width:60px;
       
       }
       .layui-input-block{
         margin-left:90px;
       }
    </style>
</head>
<body class="login-wrap">
    <div class="login-container">
        <form class="login-form layui-form"  action="">
            <h4>管理后台</h4>
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                  <div class="layui-input-block">
                    <input type="text" id="username" name="username" lay-verify="username"  class="layui-input">
                  </div>
            </div>
           <div class="layui-form-item">
             <label class="layui-form-label">密码</label>
             <div class="layui-input-block">
                <input type="password" name="password"  id="password" lay-verify="required" class="layui-input">
             </div>
           </div>
           <div class="layui-form-item">
             <label class="layui-form-label">角色类型</label>
               <div class="layui-input-block">
                    <select name="usertype" lay-verify="required">
                        <option value=""></option>
                        <option value="0">学生 </option>
                        <option value="1">教师</option>
                    </select>
                 </div>
            
           </div>
          <button type="button" class="login-button" lay-submit="" lay-filter="login">登录<i class="ai ai-enter"></i></button>
        </form>
    </div>
</body>
<script src="<%=basePath%>assets/layui.js"></script>
<script src="<%=basePath%>/js/login.js"></script>
  <script>
	  layui.use(['form'], function(){
      var form = layui.form
      ,layer = layui.layer
      ,$ = layui.$
      //自定义验证规则
      form.verify({
        
      });
    
     //监听提交
     form.on('submit(login)', function(data){
    	  let actions = {
    		'0':'student',
    		'1':'teacher'
    	  }
 
    	  $.ajax({
            url:"<%=basePath%>login",
            type:'post',//method请求方式，get或者post
            dataType:'json',//预期服务器返回的数据类型
            data:JSON.stringify(data.field),//表格数据序列化
            contentType: "application/json; charset=utf-8",
            success:function(res){//res为相应体,function为回调函数
              if(res.success){
                //layer.alert(res.message,{icon:1});
                location.href="<%=basePath%>"+actions[data.field.usertype]+"/index.jsp";
              
              }else{
                layer.alert(res.message,{icon: 5});
              }
           },
           error:function(){
            layer.alert('操作失败！！！',{icon:5});
          }
        });
       
        return false;
      });
     });
   
    </script>
</html>