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
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>QG闲鱼</title>

    <script>
        function checkPriceIfInput() {
            var min = document.getElementById("min").value;
            var max = document.getElementById("max").value;

            var log_min = /^\d{1,8}$/;
            var flag_i  = log_min.test(min);
            var flag_x  = log_min.test(max);

            if((min==="" && max==="") || (flag_x && flag_i)){
                if(min > max){
                    alert("价格最小值不可大于价格最大值！");
                    return false;
                }
                return true;
            } else {
                alert("若使用价格筛选功能，请同时输入整数的价格最大值与价格最小值");
            }
            return false;
        }

        window.onload = function () {
            document.getElementById("form").onsubmit = function () {
                return checkPriceIfInput();
            }
        }
    </script>

</head>




<body>

<a href="/login.jsp">登录</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/Register.jsp">注册</a>
<h1 align="center">欢迎<font color="#8a2be2" >   ${sessionScope.username}</font> </h1>
<h1 align="center">亲爱的<font color="#1e90ff" >   ${sessionScope.sendLevel}</font> </h1>
<form action="/ChangePageController/quit" method="post" align="center"  >
    <h1 align="center"><font color="aqua">在QG闲鱼尽情享受高质量购物的快乐吧</font></h1>

</form>





<br>

<form class="form-inline" align="center" action="/DividePageController" id="form" method="post">
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
        <input type="text" value="${requestScope.dp.minPrice}" id="min" class="form-control" name="rangemin">
    </div>

    <div class="form-group">
        <label>价格最大值:</label>
        <input type="text" value="${requestScope.dp.maxPrice}" id="max" class="form-control" name="rangemax">
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
            <img src="./photo/<%=goods.getPhotoPath()%>" height="400" width="200">
        </c:if>
        <c:if test='<%=goods.getPhotoPath().contains("暂无")%>'>
            <img src="./img/1.jpg" height="400" width="200">
        </c:if>
        <p class="review"><%=goods.getImformation()%> </p>
        <div class="appraise">来自用户<%=goods.getSeller()%>的二手商品
            <a>&nbsp&nbsp&nbsp</a><font color="#9370db">商品类型:<%=goods.getType()%></font>
            信誉分:<font color="red"><%=goods.getSellerReputation()%></font></div>
        <div class="info">
            <h4><%=goods.getGoodsName()%></h4>
            <span><%=goods.getPrice()%>元</span>
            <a>&nbsp&nbsp&nbsp</a>
            <span>存货:<%=goods.getAmount()%></span>
            <br><a align="center" href="/login.jsp" >登录后购买</a>
            <a>&nbsp&nbsp&nbsp</a>
            <a href="/login.jsp">登录后加入购物车</a>
        </div>

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