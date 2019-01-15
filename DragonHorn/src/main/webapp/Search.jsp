<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GooDragon</title>
<style>
.keyword {
  width: 600px;
  height: 300px;
  position: absolute;
  top: 50%;
  left: 50%;
  margin: -120px 0 0 -56px;
}
.submit {
  width: 600px;
  height: 300px;
  position: absolute;
  top: 50%;
  left: 50%;
  margin: -90px 0 0 -8px;
}
h1{
 width: 600px;
  height: 300px;
  position: absolute;
  top: 50%;
  left: 50%;
  margin: -165px 0 0 -56px;
}
img{
  width: 200px;
  height: 200px;
  position: absolute;
  top: 50%;
  left: 50%;
  margin: -350px 0 0 -76px;
}
</style>
</head>
<body>
<section>

<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2-7aot1NQsNNGUm5XbRCD316PUxAcbHodhLAmOd_gJFBUh4_wkw">

</section>
<h1>GooDragon</h1>
<form action='${requestUri}' method='get' >
<div class="keyword">
<input style="text-align:center;" type='text' name='keyword' placeholder = 'keyword'/>
</div>
<div class="submit">
<input style="text-align:center;" type='submit' value='submit' />
</div>
</form>
</body>
</html>