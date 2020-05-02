<%@ page import="com.zhangmengcong.www.po.DividePage" %>
<%@ page import="com.zhangmengcong.www.po.Goods" %>
<%@ page import="com.zhangmengcong.www.po.PageBean" %><%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/5/1
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购买</title>
</head>
<body>
<a href="/login.jsp">返回登录页面</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/DividePageController">返回主页面</a>



<form   action="/BuyGoodsController?tempGoodsName=${requestScope.tempGoodsName}&tempPrice=${requestScope.tempPrice}&tempSeller=${requestScope.tempSeller}" method="post" align="center">
    <h1 >感谢使用QG闲鱼</h1>
    <font color="green">您购买的商品为<p>商家${requestScope.tempSeller}</p><p>的${requestScope.tempGoodsName}</p></font>
    购买数量:<input type="text"  name="tempAmount" pattern="^\d{1,10}$" required/>
    <br>
    <input type="submit"  value="立刻下单" style="width:140px; height:30px;"/>
    <br>
</form>

</body>
</html>
