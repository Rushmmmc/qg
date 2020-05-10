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
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap-theme.css">
    <script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>





    <script>

        function fun(event) {
            event.preventDefault();
            var username = $("#username").val();
            var password = $("#password").val();
            var captcha = $("#captcha").val();
            var mailaddress = $("#mailaddress").val();
            var log_username = /^\w{4,10}$/;
            var log_password = /^\w{6,10}$/;
            var log_captcha = /^\w{4,4}$/;
            var log_mailaddress = /^[0-9A-Za-z][\.-_0-9A-Za-z]*@[0-9A-Za-z]+(?:\.[0-9A-Za-z]+)+$/;
            var flag_u = log_username.test(username);
            var flag_p = log_password.test(password);
            var flag_c = log_captcha.test(captcha);
            var flag_m = log_mailaddress.test(mailaddress);
            if(!flag_u){
                alert("用户名不可包含中文与特殊符号┭┮﹏┭┮,长度为4-10");
                return;
            }
            if(!flag_p){
                alert("密码不可包含特殊符号┭┮﹏┭┮,长度为6-10");
                return;
            }
            if(!flag_c){
                alert("请正确填写验证码┭┮﹏┭┮");
                return;
            }
            if(!flag_m){
                alert("邮箱不可包含中文和特殊符号┭┮﹏┭┮,长度为8-20");
                return;
            }
            if (flag_u && flag_p && flag_c && flag_m) {
                $.ajax({
                    url: "/UserController/register",
                    type: "POST",
                    dataType: 'html',
                    data: "username=" + username + "&password=" + password + "&captcha=" + captcha + "&mailaddress=" + mailaddress,
                    success: function (result) {
                        if(result === "true"){
                            alert("注册成功");

                        }
                        alert(result);
                    },
                    error: function (msg) {
                        alert("出错啦")
                    }
                });
            }
        }


    </script>




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
<body style="background-color: plum">

<a href="/login.jsp">返回登录页面</a>




<form     method="post" align="center"  >
    <h1 >感谢使用QG闲鱼</h1>
    用户名:<input type="text" id="username" name="username" pattern="[\w]{4,10}" required/>
    <br>
    新密码:<input type="password" name="password" id="password" pattern="[\w]{6,10}" required/>
    </br>
    邮箱号:<input type="text" name="mailaddress" id="mailaddress" pattern="^[0-9A-Za-z][\.-_0-9A-Za-z]*@[0-9A-Za-z]+(?:\.[0-9A-Za-z]+)+$" required/>
    <br>
    验证码:<input type="text" name="captcha" id="captcha" pattern="[\w]{4,4}" required/>





    <br>
    <input type="submit"  value="Finish Register" onclick="fun(event)" style="width:140px; height:30px;"/>
    <br>
    <font color="red"> 注意:用户名 为4-10 新密码 长度为6-10 邮箱地址长度为8-20<br> 用户名和密码仅支持数字及字母 </font>

</form>
<form  method="post" align="center">
    <img id="checkCode" src="/captcha"  /><br>
    <a href="#" onclick="change();">看不清，换一张</a><br/>
</form>




</body>
</html>

