<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zhangmengcong.www.po.Indent" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/5/1
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="./bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">
<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<html>
<head>
    <title>商家管理订单界面</title>

    <script>

        $(function(){
            $(".check").click(function(){
                $(".text").show();
            })
        })



        $(function(){
            $(".check2").click(function(){
                $(".text2").show();
            })
        })

    </script>

</head>
<body>
<body >
<a href="/login.jsp">返回登录页面</a>
<a href="/DividePageController">返回主页面</a>

<FORM align="center"  action="/ChangeIndentController" method="post" >
    <input type="button" value="修改订单" class="check" style="margin-bottom: 0"/>
    <br>
    <a style="display:none;margin-top: 0px" class="text">需要修改的订单id :</a>
    <input type="text" style="display:none;" pattern="^\d{1,10}$" class="text" name="id" required style="display:none;" />
    <br>
    <a style="display:none;margin-top: 0px" class="text">需要修改的订单商品名 :</a>
    <input type="text" style="display:none;"  class="text" name="goodsName" required style="display:none;" />
    <br>
    <a style="display:none;" class="text">需要修改的订单单价:</a>
    <input type="text" class="text" pattern="^\d{1,10}$" name="price" required style="display:none;" />
    <br>
    <a style="display:none;" class="text">修改的订单商品数量:</a>
    <input type="text" class="text" pattern="^\d{1,10}$" name="amount" required style="display:none;" />
    <br>
    <input type="submit" value="提交"  class="text" style="display:none;">
</FORM>

<FORM align="center"  action="/IndentSendMessageController?method=sellerSendMessage" method="post" style="float: top" >
    <input type="button" value="给用户留言" class="check2" />
    <br>
    <a class="text2"  style="display:none;" >订单id:</a>
    <input type="text"  class="text2"  name="id" pattern="^\d{1,10}$" required style="display:none;" />
    <br>
    <a class="text2" style="display:none;">内容:</a>
    <input type="text" class="text2"  name="message" required style="display:none;" />
    <br>
    <input type="submit" value="提交"  class="text2" style="display:none;">
</FORM>



<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading" align="center" >
        <a  href="/GenerateXlsIndentController"><font><p>一键生成订单xls文件并下载</p></font></a>

    </div>
    <STRONG><p align="center">商家管理商品订单</p></STRONG>
    </div>
<div class="font">
    <table  border="0px" width="70%" align="center" cellspacing="0px" class="table">
        <tr>
            <th>Id</th>
            <th>商品名称</th>
            <th>购买者</th>
            <th>出售者</th>
            <th>单价</th>
            <th>数量</th>
            <th>订单总价</th>
            <th>使用积分</th>
            <th>实际付款</th>
            <th>订单状态</th>
            <th>评价</th>
            <th>操作</th>
        </tr>
        <!--通过循环 显示信息-->


        <%
            List<Indent> emps = (List<Indent>)request.getAttribute("emps");
            for(Indent indent : emps){
        %>
        <tr>
            <td><%=indent.getId()%></td>
            <td><%=indent.getGoodsName()%></td>
            <td><%=indent.getBuyer()%></td>
            <td><%=indent.getSeller()%></td>
            <td><%=indent.getPrice()%></td>
            <td><%=indent.getAmount()%></td>
            <td><%=indent.getTotalPrice()%></td>
            <td><%=indent.getUseIntegral()%></td>
            <td><%=indent.getActuallyPrice()%></td>
            <td><%=indent.getStatus()%></td>
            <td><%=indent.getReputation()%></td>
            <td><a href="/ChangeIndentController?method=sell&id=<%=indent.getId()%>">发货</a>
                <c:if test='<%=!(indent.getBuyerMessage().contains("暂无"))%>'>
                    <a>&nbsp&nbsp&nbsp&nbsp&nbsp</a>
                    <a href="/ChangePageController?method=messageBoard&ifSeller=1">用户给您留言啦,请打开留言板</a>
                </c:if>
        </td>
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
