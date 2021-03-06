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
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap-theme.css">
    <script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script>










        function fun(event) {
            event.preventDefault();
            var goodsName = document.getElementById("goodsName").value;
            var type = document.getElementById("type").value;
            var price = document.getElementById("price").value;
            var amount = document.getElementById("amount").value;
            var imformation = document.getElementById("imformation").value;

            var log_price = /(^[0-9]{1,6}$)|(^[0-9]{1,6}[\.]{1}[0-9]{1,2}$)/;
            var log_amount = /^\d{1,8}$/;
            var log_goodsName = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;
            var log_type = /^[\u4e00-\u9fa5]+$/;
            var log_imformation = /^[\u4E00-\u9FA5+a-zA-Z0-9_\?\.,!\-，。？！]{1,50}$/;

            var flag_g = log_goodsName.test(goodsName);
            var flag_t = log_type.test(type);
            var flag_p = log_price.test(price);
            var flag_a = log_amount.test(amount);
            var flag_i = log_imformation.test(imformation);
            if (!flag_g) {
                alert("商品名称不可为空或包含特殊符号┭┮﹏┭┮");
                return;
            }
            if (!flag_t) {
                alert("商品种类不可为空,仅可包含汉字┭┮﹏┭┮");
                return;
            }
            if (!flag_p) {
                alert("商品价格不可为空或超过十亿元,仅可输入零到两位小数┭┮﹏┭┮");
                return;
            }
            if (!flag_a) {
                alert("商品存货仅可包含整数┭┮﹏┭┮");
                return;
            }
            if(!flag_i){
                alert("商品新信息不可包含特殊符号┭┮﹏┭┮");
                return;
            }
            if (flag_i && flag_a && flag_p && flag_g && flag_t) {
                $.ajax({
                    url: "/GoodsController/commitGoods",
                    type: "POST",
                    dataType: 'html',
                    data: "goodsName=" + goodsName+"&type="+type+"&price="+price+"&amount="+amount+"&imformation="+imformation,
                    success: function (result) {
                        if(result === "接下来请您添加商品图片！"){
                            alert(result);
                            location.href = "/commitPhoto.jsp";
                        }else {
                            alert(result);

                        }
                        },
                    error: function (msg) {
                        alert("出错啦")
                    }
                });
            }
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
<body style="background-color: plum">

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
<h1 align="center">欢迎<font color="#8a2be2" >   ${sessionScope.username}</font> </h1>
<h1 align="center">亲爱的<font color="#1e90ff" >   ${sessionScope.sendLevel},欢迎您成为卖家</font> </h1>

<form   align="center"  method="post" >
<%--    商品图片<br>--%>
<%--    <input type="file" style="margin-left: 580px" id="file" accept="image/pjpeg"  >--%>
    商品名称:<input type="text" id="goodsName" name="goodsName" pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+$"  required/>
    <br>
    商品类型:<input type="text" id="type" name="type" pattern="^[\u4e00-\u9fa5]+$" required/>
    <br>
    商品价格:<input type="text" id="price" name="price" pattern=^\d{1,8}$ required/>

    </br>
    商品存量:<input type="text" id="amount" name="amount" pattern="^\d{1,8}$" required>
    <br>
    商品信息:<input type="text" id="imformation" name="imformation" pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+$" required>
    <br>
    <input type="submit" style="margin-left: 30px" onclick="fun(event)" value="提交至管理员审核"><br>
</form>

</body>
</html>
