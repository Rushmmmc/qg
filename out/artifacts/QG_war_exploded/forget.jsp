<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/4/30
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/bootstrap/css/bootstrap-theme.css">
<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>mc的救赎系统</title>



    <script>

        function sendMail(event) {
            event.preventDefault();
            var mailaddress = $("#mailaddress").val();
            var log_mailaddress = /^[0-9A-Za-z][\.-_0-9A-Za-z]*@[0-9A-Za-z]+(?:\.[0-9A-Za-z]+)+$/;
            var flag_m = log_mailaddress.test(mailaddress);
            if(!flag_m){
                alert("邮箱不可包含中文与特殊符号┭┮﹏┭┮,长度为8-20");
                return;
            }
            if (flag_m) {
                $.ajax({
                    url: "/UserController/forgetPassword",
                    type: "POST",
                    dataType: 'html',
                    data: "address=" + mailaddress,
                    success: function (result) {
                        alert(result);
                        location.href="/forget.jsp";
                    },
                    error: function (msg) {
                        alert("出错啦")
                    }
                });
            }
        }


    </script>



</head>
<body style="background-color: plum">
<a href="/login.jsp">返回登录页面</a><a>&nbsp&nbsp&nbsp</a>
<br>
<br><br>
<form   action="/UserController/forgetPassword"  method="post" align="center"  >

    邮箱地址 :<input type="text" name="address" id="mailaddress"  pattern="^[0-9A-Za-z][\.-_0-9A-Za-z]*@[0-9A-Za-z]+(?:\.[0-9A-Za-z]+)+$" required/>

    </br>
    <input type="submit" onclick="sendMail(event)" value="Submit" style="width:70px; height:30px;"/>


</form>


</body>
</html>
