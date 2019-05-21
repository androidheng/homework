<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<base href="<%=basePath%>">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

