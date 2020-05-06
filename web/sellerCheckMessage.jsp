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



    <script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script>
        function fun(event,id) {


            var log_id = /^\d{1,8}$/;

            var flag_i = log_imformation.test(id);

            if(!flag_i){
                alert("id仅支持整数┭┮﹏┭┮");
                return;
            }
            if (flag_i && flag_a && flag_p && flag_g && flag_t) {
                $.ajax({
                    url: "/DeleteMessageController?method=sellerDeleteMessage",
                    type: "POST",
                    dataType: 'html',
                    data: "ifSeller=1",
                    success: function (result) {
                        alert(result);
                        location.href = "/ChangePageController?method=messageBoard&ifSeller=1";
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
<a href="/DividePageController">返回主页面</a>


<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading" align="center" ></div>
    <h2><STRONG><p  align="center"><font color="#ff1493">商家小店留言板</font></p></STRONG></h2>
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
                if(emps.get(i).getBuyerMessage().contains("暂无"))
                {
                    continue;
                }
        %>
        <tr><td><%=emps.get(i).getId()%></td>
            <td><%=emps.get(i).getGoodsName()%></td>
            <td><%=emps.get(i).getBuyerMessage()%></td>
            <td><%=emps.get(i).getSellerMessage()%></td>
            <td><a onclick="fun(event,<%=emps.get(i).getId()%>)" href="#&">清除留言</a></td>
        <tr>
                <%
        }
    %>




    </table>

</div>





</body>
</html>
