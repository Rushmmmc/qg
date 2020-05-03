<%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/4/30
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>用户提交商品页面</title>
</head>
<body>

<a href="/login.jsp">返回登录页面</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/DividePageController">返回主页面</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController?method=manageIndent&ifSeller=1">管理订单</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/Quit">注销</a>
<br><br><br>
<h1 align="center">欢迎<font color="#8a2be2" >   ${sessionScope.username}</font> </h1>
<h1 align="center">亲爱的<font color="#1e90ff" >   ${sessionScope.sendLevel},欢迎您成为卖家</font> </h1>

<form   align="center" action="/GoodsCommitController" method="post" >
    商品名称:<input type="text"  name="goodsName"  required/>
    <br>
    商品类型:<input type="text"  name="type"  required/>
    <br>
    商品价格:<input type="text" name="price" pattern=^\d{1,20}$ required/>
    </br>
    商品存量:<input type="text" name="amount" pattern="^\d{1,20}$" required>
    <br>
    商品信息:<input type="text" name="imformation"  required>
    <br>
    <input type="submit" value="提交至管理员审核">
</form>

</body>
</html>
