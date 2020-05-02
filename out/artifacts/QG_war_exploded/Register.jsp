<%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/4/14
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>欢迎使用QG闲鱼</title>
    <link rel="stylesheet" href="./bootstrap/css/bootstrap.css">

    <link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">

    <script src="./bootstrap/js/bootstrap.js"></script>





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
<body bgcolor="#00bfff">

<a href="/login.jsp">返回登录页面</a>




<form   action="/Register"  method="post" align="center"  >
    <h1 >感谢使用QG闲鱼</h1>
    用户名:<input type="text" id="username" name="username" pattern="[\w]{4,10}" required/>
    <br>
    新密码:<input type="password" name="password" pattern="[\w]{6,10}" required/>
    </br>
    邮箱号:<input type="text" name="mailaddress" pattern="^[0-9A-Za-z][\.-_0-9A-Za-z]*@[0-9A-Za-z]+(?:\.[0-9A-Za-z]+)+$" required/>
    <br>
    验证码:<input type="captcha" name="captcha" pattern="[\w]{4,4}" required/>





    <br>
    <input type="submit"  value="Finish Register" style="width:140px; height:30px;"/>
    <br>
    <font color="red"> 注意:用户名 为4-10 新密码 长度至少为6 邮箱地址长度为8-20<br> 用户名和密码仅支持数字及字母 </font>

</form>
<form  method="post" align="center">
    <img id="checkCode" src="/captcha"  /><br>
    <a href="#" onclick="change();">看不清，换一张</a><br/>
</form>


<c:if test="${not empty requestScope.message}">
<Script Language="JavaScript">
    alert("${requestScope.message}");
</Script>

</c:if>
</body>
</html>

