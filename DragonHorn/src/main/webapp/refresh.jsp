<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GooDragon</title>
<style>
 img{
  width: 500px;
  height: 500px;
  position: absolute;
  top: 50%;
  left: 50%;
  margin: -300px 0 0 -250px;
}
</style>
</head>
<body>
<img src="https://rmt-files.saowen.com/rmt_tk/img0/QjqiI3A.gif">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
            response.sendRedirect(basePath+ "googleitem.jsp");
%>
</body>
</html>