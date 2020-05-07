<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zhangmengcong.www.po.Appeal" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/5/3
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">
<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<html>
<head>
    <title>处理申诉</title>


    <script>
        function fun1(event) {
            event.preventDefault();
            var id = document.getElementById("id").innerHTML;
                $.ajax({
                    url: "/AdminManageAppealController",
                    type: "POST",
                    dataType: 'html',
                    data:"id="+id+"&type=complaintSeller",
                    success: function (result) {
                        alert(result);
                        location.href="/ChangePageToHelpUser";
                    },
                    error: function (msg) {
                        alert("出错啦")
                    }
                });
        }

        function fun2(event) {
            event.preventDefault();
            var id = document.getElementById("id").innerHTML;
            $.ajax({
                url: "/AdminManageAppealController",
                type: "POST",
                dataType: 'html',
                data:"id="+id+"&type=defendLegalRight",
                success: function (result) {
                    alert(result);
                    location.href="/ChangePageToHelpUser";
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
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
<h1 align="center" ><font color="#ff1493" >申诉信息将长期留底,消息按时间逆序显示</font></h1>

<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading" align="center" ></div>

</div>
<div class="font">
    <table  border="0px" width="70%" align="center" cellspacing="0px" class="table">
        <tr>
            <th>Id</th>
            <th>申诉类型</th>
            <th>申诉用户</th>
            <th>订单号</th>
            <th>被投诉商家</th>
            <th>内容</th>
            <th>申诉状态</th>
            <th>申诉时间</th>
            <th>管理员留言</th>
            <th>处理</th>
        </tr>
        <!--通过循环 显示信息-->


        <%
            List<Appeal> emps = (List<Appeal>)request.getAttribute("appealList");
            for(Appeal appeal :emps){
        %>
        <tr>
            <td><%=appeal.getId()%></td>
            <td><%=appeal.getType()%></td>
            <td><%=appeal.getUsername()%></td>
            <td id="id"><%=appeal.getIdentId()%></td>
            <td><%=appeal.getSeller()%></td>
            <td><%=appeal.getReason()%></td>
            <td><%=appeal.getStatus()%></td>
            <td><%=appeal.getAppealDate()%></td>
            <td><%=appeal.getMessage()%></td>
            <td>
                <c:if test='<%=appeal.getType().contains("投诉商家")%>'>
                    <a onclick="fun1(event)" href="#"><font color="green">扣商家分</font></a>
                </c:if>
                <c:if test='<%=appeal.getType().contains("交易维权")%>'>
                    <a onclick="fun2(event)" href="#"><font color="green">退款并返还积分</font></a>
                </c:if>
            </td>
        <tr>

                <%
        }
    %>
    </table>


</body>
</html>
