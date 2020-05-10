<%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/4/23
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>欢迎使用QG闲鱼</title>
    <br>

    <link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="/style.css">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap-theme.css">
    <script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>




<script>
    window.onload = function () {
        var link = document.getElementById('link');
        var check = document.getElementById("check");
        // 表单元素获取
        var inputTexts = document.querySelectorAll("[type='text']");
        link.onclick = function () {
            //取消浏览器默认行为
            event.preventDefault();
            return false;
        }
    }
</script>

<script>

    function fun(event) {
        event.preventDefault();
        var username = $("#username").val();
        var password = $("#password").val();
        var captcha = $("#captcha").val();
        var log_username = /^\w{4,10}$/;
        var log_password = /^\w{6,10}$/;
            var log_captcha = /^\w{4,4}$/;
        var flag_u = log_username.test(username);
        var flag_p = log_password.test(password);
        var flag_c = log_captcha.test(captcha);
        if(!flag_u){
            alert("请正确填写用户名┭┮﹏┭┮");
            return;
        }
        if(!flag_p){
            alert("请正确填写密码┭┮﹏┭┮");
            return;
        }
        if(!flag_c){
            alert("请正确填写验证码┭┮﹏┭┮");
            return;
        }

        if (flag_u && flag_p && flag_c) {
            $.ajax({
                url: "/UserController/login",
                type: "POST",
                dataType: 'html',
                data: "username=" + username + "&password=" + password + "&captcha=" + captcha + "&way=normal",
                success: function (result) {
                    if(result === "登录成功"){
                        location.href ="/GoodsController/seleteGoodsByInterest";
                    } else {
                        alert(result);
                    }
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
    }

    function fun2(event) {
        event.preventDefault();
            $.ajax({
                url: "/UserController/login",
                type: "POST",
                dataType: 'html',
                data: "way=cookie",
                success: function (result) {
                    if(result === "登录成功"){
                        location.href ="/GoodsController/seleteGoodsByInterest";
                    } else {
                        alert(result);
                    }
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }


    function fun3(event) {
        event.preventDefault();

            $.ajax({
                url: "/UserController/login",
                type: "POST",
                dataType: 'html',
                data: "username=visitor"  + "&password=visitor"+ "&captcha=qqqq"+ "&way=normal",
                success: function (result) {
                    if(result === "登录成功"){
                        location.href ="/GoodsController/seleteGoodsByInterest";
                    }
                    else {
                        alert(result);
                    }
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
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
<body>

<div class="box">
    <h2>感谢使用QG闲鱼</h2>

    <form     align="center"  >
        <div class="inputBox">
            <input type="text" name="username" id="username" pattern="[\w]{4,10}" required>
            <label>Username</label>
            <span id="s_username" class="error"></span>
        </div>
        <div class="inputBox">
            <input type="password" name="password" id="password" pattern="[\w]{6,10}" required>
            <label>Password</label>
            <span id="s_password" class="error"></span>
                <div class="inputBox">
                    <input type="text" name="captcha" id="captcha" pattern="[\w]{4,4}" required>
                    <label>验证码</label>
                </div>
            <input type="submit" name="" value="submit" onclick="fun(event)" >

        </div>
    </form>




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
    <div id="error" ></div>
    <form  method="post" align="center">
        <a   href="#" onclick="fun2(event)" ><font color="blue" >使用cookie登录</font></a>
        <a   href="/forget.jsp" ><font color="blue" >忘记密码?</font></a>
        <a  onclick="fun3(event)"  href="#" ><font color="blue" >以游客登陆</font></a>
        </form>


    <h4 align="center"> <font color="#dc143c" >  您还未登录,请先登录 </font></h4>






</body>
</html>

