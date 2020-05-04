<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zhangmengcong.www.po.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/4/14
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="./bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">
<html>
<head>
    <title>个人中心</title>



</head>
<body bgcolor="#00bfff">
<a href="/login.jsp">返回登录页面</a>
<a href="/DividePageController">返回主页面</a>


<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading" align="center" ></div>

    </div>
    <div class="font">
<table  border="0px" width="70%" align="center" cellspacing="0px" class="table">
    <tr>
        <th>Id</th>
        <th>用户名</th>
        <th>密码</th>
        <th>邮箱地址</th>
        <th>会员积分</th>
        <th>会员经验</th>
        <th>信誉等级</th>
        <th>注册时间</th>
        <th>售卖权限</th>
    </tr>
    <!--通过循环 显示信息-->


    <%
        List<User> emps = (List<User>)request.getAttribute("emps");
        for(User user :emps){
    %>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getUsername()%></td>
        <td><%=user.getPassword()%></td>
        <td><%=user.getMailAddress()%></td>
        <td><%=user.getIntegral()%></td>
        <td><%=user.getExp()%>
            <c:if test="<%=user.getExp()<10%>">
                <a><font color="#ff1493">(一级会员)</font></a>
            </c:if>
            <c:if test="<%=user.getExp() >= 10 && user.getExp() < 20 %>">
                <a><font color="blue">(二级会员)</font></a>
            </c:if>
            <c:if test="<%=user.getExp() >= 20 && user.getExp() < 50 %>">
                <a><font color="#00ced1">(三级会员)</font></a>
            </c:if>
            <c:if test="<%=user.getExp() >= 50 && user.getExp() < 100 %>">
                <a><font color="red">(四级会员)</font></a>
            </c:if>






        </td>
        <td><%=user.getReputationPoint()%></td>
        <td><%=user.getRegisterDate()%></td>
        <td><%=user.getStatus()%></td>
    <tr>

            <%
        }
    %>




</table>

</div>







<form   action="/ChangeMessageController" method="post" align="center"  >







    <%
        List<User> emps2 = (List<User>)request.getAttribute("emps");
        for(User user :emps){
    %>

    新名称:<input type="text" value=<%=user.getUsername()%> name="newusername" pattern="[\w]{4,10}" required/>
    <br>
    新密码:<input type="password"  name="newpassword" pattern="[\w]{4,10}" required/>
    <br>
    新邮箱:<input type="text" value=<%=user.getMailAddress()%> name="newaddress" pattern="^[0-9A-Za-z][\.-_0-9A-Za-z]*@[0-9A-Za-z]+(?:\.[0-9A-Za-z]+)+$" required/>
    </br>
    注意:如部分信息不需要改,则输入原信息即可,用户名和电话不可与其他用户相同,用户名、密码为4-10 仅支持数字及字母
    <br>
    <%
        }
    %>
    <br>
    <input type="submit"  value="Change" style="width:70px; height:30px;"/>


</form>
</div>


<form align="center">
    等级说明<br>
    <font color="#ff1493">(一级会员) exp < 10<br>
        <font color="blue">(二级会员) 10 <= exp < 20<br>
            <font color="#00ced1">(三级会员) 20 <= exp < 50<br>
                <font color="red">(四级会员)</font> exp >= 50
                <font color="#00ff7f"> <h2>后续会有许多会员活动哦( •̀ ω •́ )y请多多积攒积分和会员经验</h2></font>
</form>

<c:if test="${not empty requestScope.message}">
    <Script Language="JavaScript">
        alert("${requestScope.message}");
    </Script>
</c:if>


</body>
</html>
