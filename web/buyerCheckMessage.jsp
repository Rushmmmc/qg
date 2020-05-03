<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page import="com.zhangmengcong.www.po.Indent" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/5/2
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">
<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<html>
<head>
    <title>查看留言</title>
</head>
<body>

<a href="/login.jsp">返回登录页面</a>
<a href="/DividePageController">返回主页面</a>


<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading" align="center" ></div>
    <h2><STRONG><p  align="center"><font color="#ff1493">用户个人留言板</font></p></STRONG></h2>
</div>
<div class="font">
    <table  border="0px" width="70%" align="center" cellspacing="0px" class="table">
        <tr>
            <th>订单id</th>
            <th>商品名称</th>
            <th>用户留言</th>
            <th>商家留言</th>
            <th>处理</th>
        </tr>
        <!--通过循环 显示信息-->


        <%
            List<Indent> emps = (List<Indent>)request.getAttribute("emps");
            for (int i = 0; i < emps.size(); i++){
                if(emps.get(i).getSellerMessage().contains("暂无"))
                {
                    continue;
                }
        %>
        <tr><td><%=emps.get(i).getId()%></td>
            <td><%=emps.get(i).getGoodsName()%></td>
            <td><%=emps.get(i).getBuyerMessage()%></td>
            <td><%=emps.get(i).getSellerMessage()%></td>
            <td><a href="/DeleteMessageController?method=buyerDeleteMessage&id=<%=emps.get(i).getId()%>&ifSeller=0">清除留言</a></td>
        <tr>
                <%
        }
    %>




    </table>

</div>

<c:if test="${not empty requestScope.message}">
    <Script Language="JavaScript">
        alert("${requestScope.message}");
    </Script>

</c:if>



</body>
</html>
