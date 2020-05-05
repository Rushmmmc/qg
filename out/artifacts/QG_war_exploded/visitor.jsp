<%@ page import="com.zhangmengcong.www.po.Goods" %>
<%@ page import="com.zhangmengcong.www.po.PageBean" %>
<%@ page import="com.zhangmengcong.www.po.DividePage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/4/29
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">

<html>
<head>
    <title>QG闲鱼</title>
</head>
<body>

<a href="/login.jsp">返回登录页面</a>

<h1 align="center">欢迎<font color="#8a2be2" >   ${sessionScope.username}</font> </h1>
<h1 align="center">亲爱的<font color="#1e90ff" >   ${sessionScope.sendLevel}</font> </h1>
<form action="/Quit" method="post" align="center"  >
    <input type="submit" name="" value="Quit" >
</form>

<h1 align="center"><font color="aqua">在QG闲鱼尽情享受高质量购物的快乐吧</font></h1>
<form class="form-inline" align="center" action="/DividePageController" method="post">
    <div class="form-group">
        <label>商品名:</label>
        <input type="text" value="${requestScope.dp.goods.goodsName}" pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+$" class="form-control" name="goodsName">
    </div>
    <div class="form-group">
        <label>商品类型:</label>
        <input type="text" value="${requestScope.dp.goods.type}" pattern="^[\u4e00-\u9fa5]+$" class="form-control" name="type">
    </div>
    <div class="form-group">
        <label>商品卖家:</label>
        <input type="text" value="${requestScope.dp.goods.seller}" pattern="[\w]{4,10}" class="form-control" name="seller">
    </div>
    <br>
    <div class="form-group">
        <label>价格最小值:</label>
        <input type="text" value="${requestScope.dp.minPrice}" class="form-control" pattern="^\d{1,8}$" name="rangemin">
    </div>

    <div class="form-group">
        <label>价格最大值:</label>
        <input type="text" value="${requestScope.dp.maxPrice}" class="form-control"  pattern="^\d{1,8}$"name="rangemax">
    </div>
    <br>
    <strong>价格排序系统:</strong>
    <select name="rank">
        <option value="#">不使用排序</option>
        <option value="positiveSequence">价格正序</option>
        <option value="invertedSequence">价格逆序</option>
    </select>

    <input type="submit" value="模糊查询">


</form>




<table border="1px" width="70%" align="center" cellspacing="0px">
    <tr>
        <th>Id</th>
        <th>商品名</th>
        <th>商品类型</th>
        <th>卖家</th>
        <th>商品信息</th>
        <th>价格</th>
        <th>剩余存货</th>
        <th>本日销量</th>
        <th>卖家信誉</th>
        <th>购买</th>
        <th>加入购物车</th>
    </tr>
    <!--通过循环 显示信息-->



    <%
        DividePage dp = (DividePage)request.getAttribute("dp");
        PageBean<Goods> pb =  dp.getPb();
        for(Goods goods :pb.getList()){
    %>
    <tr>
        <td><%=goods.getId()%></td>
        <td><%=goods.getGoodsName()%></td>
        <td><%=goods.getType()%></td>
        <td><%=goods.getSeller()%></td>
        <td><%=goods.getImformation()%></td>
        <td><%=goods.getPrice()%></td>
        <td><%=goods.getAmount()%></td>
        <td><%=goods.getBoughtAmount()%></td>
        <td><%=goods.getSellerReputation()%></td>
        <td><a href="/ChangePage?method=ReadNote&id=<%=goods.getId()%>">买买买！</a></td>
        <td><a href="/ChangePage?method=ReadNote&id=<%=goods.getId()%>">加入购物车</a></td>
    <tr>
            <%
    }
%>



</table>

<% int i = 1;%>
<form align="center">
    <nav  aria-label="Page navigation" >
        <ul class="pagination">
            <li>
                <a href="/DivedePageController?currentPage=<%if(i!=1) --i;%><%=i%>
&rows=3&goodsNames=${requestScope.textMessage.goodsName}
&type=${requestScope.textMessage.type}&seller=${requestScope.textMessage.seller}&rangeMin=${requestScope.dp.minPrice}
&rangeMax=${requestScope.dp.maxPrice}&rank=${requestScope.dp.rank}" aria-label="Next">        <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li><a href="/DividePageController?currentPage=1&rows=3&goodsNames=${requestScope.textMessage.goodsName}
&type=${requestScope.textMessage.type}&seller=${requestScope.textMessage.seller}&rangeMin=${requestScope.dp.minPrice}
&rangeMax=${requestScope.dp.maxPrice}&rank=${requestScope.dp.rank}">1</a></li>
            <li><a href="/DividePageController?currentPage=2&rows=3&goodsNames=${requestScope.textMessage.goodsName}
&type=${requestScope.textMessage.type}&seller=${requestScope.textMessage.seller}&rangeMin=${requestScope.dp.minPrice}
&rangeMax=${requestScope.dp.maxPrice}&rank=${requestScope.dp.rank}">2</a></li>
            <li><a href="/DividePageController?currentPage=3&rows=3&goodsNames=${requestScope.textMessage.goodsName}
&type=${requestScope.textMessage.type}&seller=${requestScope.textMessage.seller}&rangeMin=${requestScope.dp.minPrice}
&rangeMax=${requestScope.dp.maxPrice}&rank=${requestScope.dp.rank}">3</a></li>
            <li><a href="/DividePageController?currentPage=4&rows=3&goodsNames=${requestScope.textMessage.goodsName}
&type=${requestScope.textMessage.type}&seller=${requestScope.textMessage.seller}&rangeMin=${requestScope.dp.minPrice}
&rangeMax=${requestScope.dp.maxPrice}&rank=${requestScope.dp.rank}">4</a></li>
            <li><a href="/DividePageController?currentPage=5&rows=3&goodsNames=${requestScope.textMessage.goodsName}
&type=${requestScope.textMessage.type}&seller=${requestScope.textMessage.seller}&rangeMin=${requestScope.dp.minPrice}
&rangeMax=${requestScope.dp.maxPrice}&rank=${requestScope.dp.rank}">5</a></li>
            <li>
                <a href="/DividePageController?currentPage=<%if(i!=pb.getTotalPage()) ++i;%><%=i%>
&rows=3&goodsNames=${requestScope.textMessage.goodsName}
&type=${requestScope.textMessage.type}&seller=${requestScope.textMessage.seller}&rangeMin=${requestScope.dp.minPrice}
&rangeMax=${requestScope.dp.maxPrice}&rank=${requestScope.dp.rank}" aria-label="Next">

                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <h2><font color="aqua">共<%=pb.getTotalCount()%>条信息,共<%=pb.getTotalPage()%>页</font></h2>

        </ul>
    </nav>

</form>






</table>


<c:if test="${not empty requestScope.message}">
    <Script Language="JavaScript">
        alert("${requestScope.message}");
    </Script>

</c:if>


</body>
</html>
