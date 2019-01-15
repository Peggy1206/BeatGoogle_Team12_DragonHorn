<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
<style>
body {
  margin: 0;
}

/* Style the header */
.header {
  background-color: #DDA0DD;
  padding: 20px;
  text-align: center;
}
</style>
<body>
<div class="header">
  <h1>GooDragon</h1>
</div>

<%
String[][] orderList = (String[][]) request.getAttribute("query");
for(int i =0 ; i < orderList.length;i++){%>
	<a href='<%= orderList[i][1] %>'><%= orderList[i][0] %></a><br><h style="font-size:5px ;"><%= orderList[i][1] %></h><br><br>
<%
}
%>
</body>
</head>
</html>