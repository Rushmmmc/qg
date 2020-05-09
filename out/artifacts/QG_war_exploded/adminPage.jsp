<%@ page import="com.zhangmengcong.www.po.Goods" %>
<%@ page import="com.zhangmengcong.www.po.PageBean" %>
<%@ page import="com.zhangmengcong.www.po.DividePage" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/4/29
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/bootstrap/css/bootstrap-theme.css">
<link rel="stylesheet" href="/beautiful.css">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">

    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>QG闲鱼</title>
    <style>
    * {
    margin: 0;
    padding: 0;
    }
    body {
    background-color: #f5f5f5;
    }
    .box {
    margin-left: 100px;
    width: 298px;
    height: 650px;
    background-color: #ffe7f8;
    margin-right: 50px;

    }
    .box img {
    width: 100%;
    }
    .review {
    height: 70px;
    font-size: 14px;

    padding: 0 28px;
    margin-top: 30px;
    }
    .appraise {
    font-size: 12px;
    color: #b0b0b0;
    margin-top: 20px;
    padding: 0 28px;
    }
    .info {
    font-size: 14px;
    margin-top: 15px;
    padding: 0 28px;
    }
    .info h4{
    display: inline-block;
    font-weight: 400;
    }
    .info span {
    color: #ff6700;
    }


    .side {
    display: block;
    width: 230px;
    height: 40px;
    background-color: #55585a;
    font-size: 14px;
    color: #fff;
    text-decoration: none;
    text-indent: 2em;
    }



    .side:hover {
    background-color: #ff6700;
    }

</style>


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
<h1 align="center">亲爱的<font color="#1e90ff" >   ${sessionScope.sendLevel}</font> </h1>
<form action="/ChangePageController/quit" method="post" align="center"  >
    <a href="/ChangePageController/changeMessagePage">个人中心(查询及修改个人信息)</a>
    <a>&nbsp&nbsp&nbsp</a>
    <a href="/admin/changeLevel?level=3">一键成为管理员(用于测试)</a>
    <a>&nbsp&nbsp&nbsp</a>
    <a href="/admin/changeLevel?level=2">一键变回用户(用于测试)</a>
    <h1 align="center"><font color="aqua">在QG闲鱼尽情享受高质量购物的快乐吧</font></h1>

</form>





<br>

<form class="form-inline" align="center" action="/DividePageController" method="post">
    <div class="form-group">
        <label>商品名:</label>
        <input type="text" value="${requestScope.dp.goods.goodsName}" class="form-control" name="goodsName">
    </div>
    <div class="form-group">
        <label>商品类型:</label>
        <input type="text" value="${requestScope.dp.goods.type}" class="form-control" name="type">
    </div>
    <div class="form-group">
        <label>商品卖家:</label>
        <input type="text" value="${requestScope.dp.goods.seller}" class="form-control" name="seller">
    </div>
    <br>
    <div class="form-group">
        <label>价格最小值:</label>
        <input type="text" value="${requestScope.dp.minPrice}" class="form-control" name="rangemin">
    </div>

    <div class="form-group">
        <label>价格最大值:</label>
        <input type="text" value="${requestScope.dp.maxPrice}" class="form-control" name="rangemax">
    </div>
    <br>
    <strong>价格排序系统:</strong>
    <select name="rank" >
        <option value="#">不使用排序</option>
        <option value="positiveSequence">价格正序</option>
        <option value="invertedSequence">价格逆序</option>
    </select>

    <input type="submit" value="模糊查询">


</form>

<%
    DividePage dp = (DividePage)request.getAttribute("dp");
    PageBean<Goods> pb =  dp.getPb();
    for(Goods goods :pb.getList()){
%>
<div>


    <div style="float: left;margin-left: 70px;margin-bottom: 50px;margin-top: 30px;" class="box">
        <c:if test='<%=!goods.getPhotoPath().contains("暂无")%>'>
            <img src="/photo/<%=goods.getPhotoPath()%>" height="400" width="200">
        </c:if>
        <c:if test='<%=goods.getPhotoPath().contains("暂无")%>'>
            <img src="./img/1.jpg" height="400" width="200">
        </c:if>
        <p class="review"><font color="#00bfff"><%=goods.getImformation()%></font> </p>
        <c:if test='<%=!goods.getRecommend().contains("暂无")%>'>
            <h4 style="margin-left: 24px"><font color="#9acd32">用户推荐理由:<%=goods.getRecommend()%></font></h4>
        </c:if>
        <div class="appraise"><font color="blue">来自用户</font><font color="#ff1493"><%=goods.getSeller()%></font><font color="blue">的二手商品</font>
            <font color="red">信誉分:<%=goods.getSellerReputation()%></font></div>
        <div class="info">
            <h4><%=goods.getGoodsName()%></h4>
            <span><%=goods.getPrice()%>元</span>
            <a>&nbsp&nbsp&nbsp</a>
            <span>存货:<%=goods.getAmount()%></span>
        </div>


          <font align="center" style="margin-left: 50px" color="red">管理员账号无法购买商品</font>


    </div>

</div>
<%
    }
%>




<% int i = 1;

%>
<form align="center" style="margin-top:650px; ">
    <nav  aria-label="Page navigation" >
        <ul class="pagination">
            <li>
                <a href="/DividePageController?currentPage=<%if(i!=1) --i;%><%=i%>
&rows=3&goodsNames=${requestScope.textMessage.goodsName}
&type=${requestScope.textMessage.type}&seller=${requestScope.textMessage.seller}&rangeMin=${requestScope.dp.minPrice}
&rangeMax=${requestScope.dp.maxPrice}&rank=${requestScope.dp.rank}" aria-label="Next">        <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <%
                int j=1;
                for( ;j <= dp.getPb().getTotalPage();j++)
                {
            %>
            <li><a href="/DividePageController?currentPage=<%=j%>&rows=3&goodsNames=${requestScope.textMessage.goodsName}
&type=${requestScope.textMessage.type}&seller=${requestScope.textMessage.seller}&rangeMin=${requestScope.dp.minPrice}
&rangeMax=${requestScope.dp.maxPrice}&rank=${requestScope.dp.rank}"><%=j%></a></li>

            <%
                }
            %>
            <li>
                <a href="/DividePageController?currentPage=<%if(i!=pb.getTotalPage()) ++i;%><%=i%>
&rows=3&goodsNames=${requestScope.textMessage.goodsName}
&type=${requestScope.textMessage.type}&seller=${requestScope.textMessage.seller}&rangeMin=${requestScope.dp.minPrice}
&rangeMax=${requestScope.dp.maxPrice}&rank=${requestScope.dp.rank}" aria-label="Next">

                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li><br>
            <h2><font color="aqua">共<%=pb.getTotalCount()%>条信息,共<%=pb.getTotalPage()%>页</font></h2>

        </ul>
    </nav>

</form>


</body>
</html>