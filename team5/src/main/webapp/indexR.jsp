<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/Login.css">
<script type="text/javascript" src="Script/member.js"></script>
</head>
<body>
   <div style="flex: 20%;  ">
          <div style="width: 100%; height:909px; float: right; border-left: 1px solid black; ">
        <div style="margin-top: 20%; margin-left: 20px;">
            <img src="Logo1.jpg" width="350" height="350">
        </div>
        <div style="padding-top: 20%; margin-left: 15%;">
        <form action="login.do" method="post" name="frm">
   <!-- <table border="1"> -->
   <table>
      <tr>
         <td>ID</td>
         <td><input type="text" name="userId"></td>
      </tr>
      <tr>
         <td>Password</td>
         <td><input type="password" name="userPwd"></td>
      </tr>
      <tr>
         <td colspan="2" align="center">
            <input type="submit" value="Login" onclick="return checkLogin()">
            <input type="reset" value="reset">
            <input type="button" value="Join" onclick="location.href='join.do'">
         </td>
      </tr>   
   </table>
</form>
        </div>
    </div>
   </div>
</body>
</html>