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

<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/bootstrap/css/bootstrap-theme.css">
<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<html>
<head>
    <title>管理员界面</title>



<script>
    function fun2(event) {
        event.preventDefault();
        var operate = $("#operate").val();
        var username = $("#name123").val();
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
        if (!flag_r ) {
            alert("原因不能为空且不要包含特殊符号┭┮﹏┭┮");
            return;
        }
        if (!flag_o) {
            alert("仅支持封禁与解封");
            return;
        }
        if (flag_o && flag_r && flag_u) {
            $.ajax({
                url: "/admin/ban",
                type: "POST",
                dataType: 'html',
                data: "operate=" +operate+"&username="+ username + "&reason="+reason,
                success: function (result) {
                    if (result === "处理成功!") {
                        alert(result);
                        location.href = "/ChangePageController/adminManageGoodsAndSellerSystem";
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
                url: "/GoodsController/goodsDeleteOrPassController",
                type: "POST",
                dataType: 'html',
                data: "id=" + id+"&ifDelete=0",
                success: function (result) {
                    alert(result);
                    location.href ="/ChangePageController/adminManageGoodsAndSellerSystem";
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
                url: "/GoodsController/goodsDeleteOrPassController",
                type: "POST",
                dataType: 'html',
                data: "id=" + id+"&ifDelete=1",
                success: function (result) {
                    alert(result);
                    location.href ="/ChangePageController/adminManageGoodsAndSellerSystem";
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
    }


    function fun10(event,name) {
        event.preventDefault();
        document.getElementById("6").style.display = "block";
        document.getElementById("name123").value = name;
    }


    </script>



</head>
<body>

<a href="/login.jsp">返回登录页面</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/DividePageController">返回主页面</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/adminManageGoodsAndSellerSystem">管理用户、商品系统</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/changePageToHelpUser">管理申诉系统</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/quit">注销</a>

<h1 align="center">欢迎<font color="#8a2be2" >   ${sessionScope.username}</font> </h1>
<h1 align="center" style="margin-bottom:0">亲爱的<font color="#1e90ff" >   ${sessionScope.sendLevel}</font> </h1>


<div id="6" style="display: none">
<FORM align="center"    method="post" >


    <br>


    <select id="operate" name="operate" class="text" id="2"  >
        <option value="1">封禁</option>
        <option value="0">解封</option>
    </select>


    <br>
    <a style="margin-top: 0px"   class="text" id="3">商家用户名:</a><br>
    <input type="text"  class="text"  name="username" id="name123" required  />
    <br>
    <a style="margin-top: 0px"   class="text" id="5">封禁/解封理由:</a><br>
    <input type="text" class="text"  pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+$" name="reason" id="reason" required />
    <br>
    <input type="submit" value="提交"  onclick="fun2(event)" class="text" >
</FORM>
</div>




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
            <a href="#" onclick="fun4(event,<%=goods.getId()%>)">删除商品</a>
            <a href="#" onclick="fun10(event,'<%=goods.getSeller()%>')">封禁/解封</a>
    <tr>
            <%
        }
    %>



</table>
</div>

</body>
</html>
