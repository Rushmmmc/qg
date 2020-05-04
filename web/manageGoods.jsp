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


</head>
<body>

<a href="/login.jsp">返回登录页面</a>
<a href="/DividePageController">返回主页面</a>


<h1 align="center">欢迎<font color="#8a2be2" >   ${sessionScope.username}</font> </h1>
<h1 align="center" style="margin-bottom:0">亲爱的<font color="#1e90ff" >   ${sessionScope.sendLevel}</font> </h1>

<FORM align="center"  action="/AdminBanUserController" method="post" >
    <input type="button" value="封禁/解封用户" class="check" style="
    margin-top:30px;margin-bottom: 0px"/>
    <br>


    <select name="operate" class="text" style="display:none;" >
        <option value="1">封禁</option>
        <option value="0">解封</option>
    </select>


    <br>
    <a style="display:none;margin-top: 0px" class="text">卖家用户名:</a>
    <input type="text" style="display:none;" class="text" name="username" required style="display:none;" />
    <br>
    <a style="display:none;" class="text">封禁/解封理由:</a>
    <input type="text" class="text" pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+$" name="reason" required style="display:none;" />
    <br>
    <input type="submit" value="提交"  class="text" style="display:none;">
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
        <td><a href="/GoodsDeleteOrPassController?ifDelete=0&id=<%=goods.getId()%>">通过</a>
            <a>/</a>
            <a href="/GoodsDeleteOrPassController?ifDelete=1&id=<%=goods.getId()%>">删除</a>


    <tr>
            <%
        }
    %>



</table>
</div>

</body>
</html>
