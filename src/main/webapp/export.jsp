<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common.jsp" %>
<!DOCTYPE html >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="icon" href="/favicon.ico">
    <title>管理后台</title>
</head>
	<body>
		<div class="input-group-btn" style="text-align: center;">
			<button type="button" class="btn btn-import btn-derive" onclick="doImportPdf();">下载作业</button><br><br>
		</div>
		<script type="text/javascript">
			
		function doImportPdf(){
			location.href="<%=basePath%>swork/testFileDownLoad";
		}
 
		</script>
	</body>
</html>