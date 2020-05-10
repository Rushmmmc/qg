<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/bootstrap/css/bootstrap-theme.css">
<html>
<head>
    <title>购买</title>
    <script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script>
        function fun(event) {
            event.preventDefault();
            var tempAmount = document.getElementById("tempAmount").value;
            var integral = document.getElementById("integral").value;
            var log_Amount = /^\d{1,10}$/;
            var log_integral = /^\d{1,10}$/;
            var flag_a = log_Amount.test(tempAmount);
            var flag_i = log_integral.test(integral);
            if (!flag_a) {
                alert("商品数量有误┭┮﹏┭┮ 仅支持整数且不能超过10亿┭┮﹏┭┮");
                return;
            }
            if (!flag_i) {
                alert("积分数量有误┭┮﹏┭┮ 仅支持整数且不能超过10亿┭┮﹏┭┮");
                return;
            }
            if (flag_i && flag_a) {
                $.ajax({
                    url: "/GoodsController/buyGoods",
                    type: "POST",
                    dataType: 'html',
                    data: "tempAmount="+tempAmount +"&integral="+integral,
                    success: function (result) {
                        if(result === "商家正在火速处理您的订单( •̀ ω •́ )y"){
                            alert(result);
                            location.href = "/ChangePageController/goCheckBuyIndent";
                        }else {
                            alert(result)
                        }
                    },
                    error: function (msg) {
                        alert("出错啦")
                    }
                });
            }
        }function goToCommitGoods(event) {
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
    </script>




</head>
<body style="background-color: plum">
<a href="/login.jsp">返回登录页面</a><a>&nbsp&nbsp&nbsp</a>
<a href="/DividePageController">返回主页面</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="#" onclick="goToCommitGoods(event)">申卖商品</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/goCheckSalesIndent">管理卖出订单</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/goCheckBuyIndent">管理买入订单</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/changePageToShoppingCar">查看购物车</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/changePageToHelpUser">进行申诉</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/quit">注销</a>


<form    method="post" align="center">
    <h1 >感谢使用QG闲鱼</h1>
    <h2><font color="green">您购买的商品为<p>商家<font color="purple">${sessionScope.goods.seller}
    </font>的</p><p><font color="#00bfff">${sessionScope.goods.goodsName}</font></p></font>
    <p>单价为<font color="#8a2be2">${sessionScope.goods.price}元</font></p>
    <br><p>存货竟然只有<font color="red">${sessionScope.goods.amount}个了</font></p></h2>
    购买数量:<input type="text"  id="tempAmount" name="tempAmount" value="1" pattern="^\d{1,10}$" required/>
    <br>
    使用积分:<input type="text"  id="integral" name="integral" value="0" pattern="^\d{1,10}$" required/>
    <br>
    <input type="submit"  value="立刻下单" onclick="fun(event)" style="width:140px; height:30px;"/>
    <br>
</form>

</body>
</html>
