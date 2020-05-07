<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page import="com.zhangmengcong.www.po.Goods" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/4/29
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="./bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">
<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<html>
<head>
    <title>管理员界面</title>

    <script>

        $(function(){
            $(".check").click(function(){
                $(".text").show();
            })
        })

    </script>
<script>
    function fun2(event) {
        event.preventDefault();
        var operate = $("#operate").val();
        var username = $("#username").val();
        var reason = $("#reason").val();
        var log_username = /^\w{4,10}$/;
        var log_reason = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;
        var flag_u = log_username.test(username);
        var flag_o = false;
        var flag_r = log_reason.test(reason);
        if (operate === "1" || operate === "0") {
            flag_o = true;
        }
        if (!flag_u) {
            alert("请正确填写用户名┭┮﹏┭┮");
            return;
        }
        if (!flag_r) {
            alert("原因请不要包含特殊符号┭┮﹏┭┮");
            return;
        }
        if (!flag_o) {
            alert("仅支持封禁与解封");
            return;
        }
        if (flag_o && flag_r && flag_u) {
            $.ajax({
                url: "/AdminBanUserController",
                type: "POST",
                dataType: 'html',
                data: "operate=" +operate+"&username="+ username + "&reason="+reason,
                success: function (result) {
                    if (result === "处理成功!") {
                        alert(result);
                        location.href = "/ChangePageController?method=manageSystem";
                    } else {
                        alert(result);
                    }
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
    }</script>



    <script>
    function fun3(event,id) {
        event.preventDefault();
        var log_id = /^\d{1,8}$/;
        var flag_i = log_id.test(id);
        if (!flag_i) {
            alert("请注意id格式┭┮﹏┭┮");
            return;
        }
        if (flag_i) {
            $.ajax({
                url: "/GoodsDeleteOrPassController",
                type: "POST",
                dataType: 'html',
                data: "id=" + id+"&ifDelete=0",
                success: function (result) {
                    alert(result);
                    location.href ="/ChangePageController?method=manageSystem";
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
    }

    function fun4(event,id) {
        event.preventDefault();
        var log_id = /^\d{1,8}$/;
        var flag_i = log_id.test(id);
        if (!flag_i) {
            alert("请注意id格式┭┮﹏┭┮");
            return;
        }
        if (flag_i) {
            $.ajax({
                url: "/GoodsDeleteOrPassController",
                type: "POST",
                dataType: 'html',
                data: "id=" + id+"&ifDelete=1",
                success: function (result) {
                    alert(result);
                    location.href ="/ChangePageController?method=manageSystem";
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
    }





    </script>



</head>
<body>

<a href="/login.jsp">返回登录页面</a>
<a>&nbsp&nbsp&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController?method=manageSystem">管理用户、商品系统</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageToHelpUser">管理申诉系统</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/Quit">注销</a>

<h1 align="center">欢迎<font color="#8a2be2" >   ${sessionScope.username}</font> </h1>
<h1 align="center" style="margin-bottom:0">亲爱的<font color="#1e90ff" >   ${sessionScope.sendLevel}</font> </h1>

<FORM align="center"    method="post" >
    <input type="button" value="封禁/解封用户"   class="check" style="
    margin-top:30px;margin-bottom: 0px"/>
    <br>


    <select id="operate" name="operate" class="text" style="display:none;" >
        <option value="1">封禁</option>
        <option value="0">解封</option>
    </select>


    <br>
    <a style="display:none;margin-top: 0px" class="text">卖家用户名:</a>
    <input type="text" style="display:none;" class="text" name="username" id="username" required style="display:none;" />
    <br>
    <a style="display:none;" class="text">封禁/解封理由:</a>
    <input type="text" class="text" pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+$" name="reason" id="reason" required style="display:none;" />
    <br>
    <input type="submit" value="提交"  onclick="fun2(event)" class="text" style="display:none;">
</FORM>
<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading" align="center">
        <strong><font color="red" style="font-size: 30px">管理系统</font></strong></div>
    <div class="panel-body">
        <p align="center" style="font-size: 20px">商品表单</p>
    </div>
<table  border="0px" width="70%" align="center" style="margin-top: 0" cellspacing="0px" class="table">
    <tr>
        <th>商品Id</th>
        <th>商品名</th>
        <th>类型</th>
        <th>卖家</th>
        <th>卖家声誉</th>
        <th>商品信息</th>
        <th>价格</th>
        <th>货存量</th>
        <th>状态</th>
        <th>管理</th>
    </tr>
    <!--通过循环 显示信息-->


    <%
        List<Goods> emps = (List<Goods>)request.getAttribute("goodsList");
        for(Goods goods :emps){
    %>
    <tr>
        <td><%=goods.getId()%></td>
        <td><%=goods.getGoodsName()%></td>
        <td><%=goods.getType()%></td>
        <td><%=goods.getSeller()%></td>
        <td><%=goods.getSellerReputation()%></td>
        <td><%=goods.getImformation()%></td>
        <td><%=goods.getPrice()%></td>
        <td><%=goods.getAmount()%></td>
        <td><%=goods.getStatus()%></td>
        <td><a href="#" onclick="fun3(event,<%=goods.getId()%>)">通过</a>
            <a>/</a>
            <a href="#" onclick="fun4(event,<%=goods.getId()%>)">删除</a>


    <tr>
            <%
        }
    %>



</table>
</div>

</body>
</html>
