<%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/4/23
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>欢迎使用QG闲鱼</title>
    <br>

    <link rel="stylesheet" href="./bootstrap/css/bootstrap.css">

    <link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">

    <script src="jquery-3.5.0.min.js"></script>
    <script src="./bootstrap/js/bootstrap.js"></script>



    <link type="text/css" rel="stylesheet" href="./style.css">


    <!--        //点击验证码更新-->
<script>
    //点击超链接或图片,换一张.
    //1.给超链接和图片绑定单击事件
    //2.重新设置图片的src属性值
    window.onload = function () {
        //1.获取图片对象,
        var img = document.getElementById("checkCode");
        //2.绑定单击事件
        img.onclick = function(){
            //3.加时间戳
            var date = new Date().getTime();
            img.src = "/captcha?"+date;
        }
    }
</script>

<!--        点击看不清,换一张-->
<script type="text/javascript">
    function change(){
        var img=document.getElementById("checkCode");
        img.src="/captcha?"+new Date().getTime();
    }
</script>

<!--        //点击看不清,换一张-->
</head>
<body>

<div class="box">
    <h2>感谢使用QG闲鱼</h2>
    <form   action="/login?way=normal" method="post" align="center"  >
        <div class="inputBox">
            <input type="text" name="username" pattern="[\w]{4,10}" required>
            <label>Username</label>
        </div>
        <div class="inputBox">
            <input type="password" name="password" required>
            <label>Password</label>
                <div class="inputBox">
                    <input type="text" name="captcha" pattern="[\w]{4,4}" required>
                    <label>验证码</label>
                </div>

                <input type="submit" name="" value="submit" >
    </form>
</div>

<div id="inline-block">
    <form align="center" method="post" action="/Register.jsp">
        <div class="inputBox">
            <input type="submit" name="" value="register" >
    </form>
</div >



    <div>
        <form  method="post" align="center">
            <img id="checkCode" src="/captcha"  /><br>
            <a href="#" onclick="change();">看不清，换一张</a><br/>
        </form>
    </div>

    <form  method="post" align="center">
        <a   href="/login?way=cookie" ><font color="blue" >使用cookie登录</font></a>
        <a   href="/forget.jsp" ><font color="blue" >忘记密码?</font></a>
        <a   href="/login?way=normal&username=visitor&password=visitor&captcha=qqqq" ><font color="blue" >以游客登陆</font></a>
        </form>


    <h4 align="center"> <font color="#dc143c" >  您还未登录,请先登录 </font></h4>
    <c:if test="${not empty requestScope.message}">
        <Script Language="JavaScript">
            alert("${requestScope.message}");
        </Script>
    </c:if>

    <c:if test="${not empty requestScope.cookiemessage}">
        <Script Language="JavaScript">
            alert("${requestScope.cookiemessage}");
        </Script>

    </c:if>



</body>
</html>

