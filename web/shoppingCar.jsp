<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zhangmengcong.www.po.Indent" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/5/1
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/bootstrap/css/bootstrap-theme.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/bootstrap/css/bootstrap-theme.css">
<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<html>
<head>
    <title>用户订单中心</title>
    <script>

        function fun(event,id,price) {
            event.preventDefault();
            var tempAmount = document.getElementById("amount").value;
            var integral = document.getElementById("integral").value;
            var log_Amount = /^\d{1,10}$/;
            var log_integral = /^\d{1,10}$/;
            var flag_a = log_Amount.test(tempAmount);
            var flag_i = log_integral.test(integral);
            if (!flag_a) {
                alert("商品数量有误┭┮﹏┭┮ 仅支持整数且不能超过10亿");
                return;
            }
            if (!flag_i) {
                alert("积分数量有误┭┮﹏┭┮ 仅支持整数且不能超过10亿");
                return;
            }
            if (flag_i && flag_a) {
                $.ajax({
                    url: "/GoodsController/buyGoodsFromShoppingCar",
                    type: "POST",
                    dataType: 'html',
                    data: "amount="+tempAmount +"&integral="+integral+"&id="+id+"&price="+price,
                    success: function (result) {
                            alert(result);
                            if(result==="购买成功！")
                            {
                                location.href = "/ChangePageController/changePageToShoppingCar";
                            }
                    },
                    error: function (msg) {
                        alert("出错啦")
                    }
                });
            }
        }


        function del(event,indentId)
        {
            $.ajax({
                url: "/IndentController/deleteShoppingCarIndent",
                type: "POST",
                dataType: 'html',
                data: "indentId="+indentId,
                success: function (result) {
                    alert(result);
                        location.href = "/ChangePageController/changePageToShoppingCar";
                    },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
        function goToCommitGoods(event) {
            event.preventDefault();
            $.ajax({
                url: "/ChangePageController/sellerCommitGoods",
                type: "POST",
                dataType: 'html',
                success: function (result) {
                    if(result === ""){
                        location.href = "/commit.jsp";
                    }else {
                        alert(result);
                    }
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
        function changePageToManagePersonalGoods(event) {
            event.preventDefault();
            $.ajax({
                url: "/GoodsController/checkUserIfExistGoods",
                type: "POST",
                dataType: 'html',
                success: function (result) {
                    if(result === "您暂无商品，快去申请卖出您的宝贝吧( •̀ ω •́ )y"){
                        alert(result);
                    }else {
                        location.href = "/ChangePageController/changePageToSellerManageGoodsPage";
                    }
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
<a>&nbsp&nbsp&nbsp</a>
<a href="/DividePageController">返回主页面</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/goCheckBuyIndent">管理买入订单</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/changePageToShoppingCar">查看购物车</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/goCheckSalesIndent">管理卖出订单</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="#" onclick="changePageToManagePersonalGoods(event)">管理商品</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="#" onclick="goToCommitGoods(event)">申卖商品</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/changePageToHelpUser">进行申诉</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/quit">注销</a>



    <br><br><br>
    <h2><STRONG><p align="center"><font color="#00bfff">用户购物车</font></p></STRONG></h2>
</div>
<div class="font">
    <table  border="0px" width="70%" align="center" cellspacing="0px" class="table">
        <tr>
            <th>Id</th>
            <th>商品名称</th>
            <th>存货数量</th>
            <th>购买者</th>
            <th>出售者</th>
            <th>单价</th>
            <th>数量</th>
            <th>使用积分</th>
            <th>实际付款</th>
            <th>订单状态</th>
            <th>管理</th>
        </tr>
        <!--通过循环 显示信息-->


        <%
            List<Indent> emps = (List<Indent>)request.getAttribute("emps");
            for(Indent indent : emps){
        %>
        <tr>
            <td><%=indent.getId()%></td>
            <td><%=indent.getGoodsName()%></td>
            <td><%=indent.getLastAmount()%></td>
            <td><%=indent.getBuyer()%></td>
            <td><%=indent.getSeller()%></td>
            <td><%=indent.getPrice()%></td>
            <form method="post" action="/GoodsController/buyGoodsFromShoppingCar?method=buyGoodsFromShoppingCar&id=<%=indent.getId()%>&price=<%=indent.getPrice()%>">
            <td><input type="text" name="amount" id="amount" required value="1"></td>
                <td><input type="text" name="integral" id="integral"  required value="0"></td>
            <td><%=indent.getTotalPrice()%></td>
            <td><%=indent.getStatus()%></td>
            <td><a href="#" onclick="del(event,<%=indent.getId()%>
            )">从购物车删除</a>
                <input type="submit" onclick="fun(event,<%=indent.getId()%>,<%=indent.getPrice()%>)" value="购买">
            </form>
            </td>
        <tr>
                <%
        }
    %>




    </table>

</div>




</body>
</html>
