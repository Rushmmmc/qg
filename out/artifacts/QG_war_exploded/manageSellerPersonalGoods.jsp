<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page import="com.zhangmengcong.www.po.Goods" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/5/11
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/bootstrap/css/bootstrap-theme.css">
<script src="/jquery-3.5.0.min.js"></script>
<html>
<head>
    <title>管理您的商品</title>
    <script>
        function appear(event,id,goodsName,price,amount){
            event.preventDefault();
            document.getElementById("1").style.display = "block";
            document.getElementById("id").value = id;
            document.getElementById("goodsName").value = goodsName;
            document.getElementById("price").value = price;
            document.getElementById("amount").value = amount;
        }

        function deleteGoods(event,goodsId) {
            event.preventDefault();
            var log_id = /^\d{1,8}$/;
            var flag_i = log_id.test(goodsId);
            if(!flag_i){
                alert("商品名不可为空或包含特殊符号！");
                return;
            }
            if (flag_i) {
                $.ajax({
                    url: "/GoodsController/SellerDeleteGoods",
                    type: "POST",
                    data: "goodsId="+goodsId,
                    success: function (result) {
                        alert(result);
                        location.href = "/ChangePageController/changePageToSellerManageGoodsPage";
                    },
                    error: function (msg) {
                        alert("出错啦")
                    }
                });
            }

        }
        function changeGoodsMessage(event) {
            event.preventDefault();
            var id = document.getElementById("id").value;
            var goodsName = document.getElementById("goodsName").value;
            var price = document.getElementById("price").value;
            var amount = document.getElementById("amount").value;
            var log_id = /^\d{1,8}$/;
            var log_price = /(^[0-9]{1,6}$)|(^[0-9]{1,6}[\.]{1}[0-9]{1,2}$)/;
            var log_amount = /^\d{1,8}$/;
            var log_goodsName = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;

            var flag_i = log_id.test(id);
            var flag_g = log_goodsName.test(goodsName);
            var flag_p = log_price.test(price);
            var flag_a = log_amount.test(amount);

            if (!flag_g) {
                alert("商品名称不可为空或包含特殊符号┭┮﹏┭┮");
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

            if (!flag_i) {
                alert("请注意id格式┭┮﹏┭┮");
                return;
            }
            if (flag_i && flag_g && flag_p && flag_a) {
                $.ajax({
                    url: "/GoodsController/changeGoodsMessage",
                    type: "POST",
                    dataType: 'html',
                    data: "goodsId=" + id+"&goodsName="+goodsName+"&price="+price+"&amount="+amount,
                    success: function (result) {
                        if(result === "修改成功！") {
                            alert(result);
                            location.href = "/ChangePageController/changePageToSellerManageGoodsPage";
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

        function confirmChangeGoods(event){
            event.preventDefault();
            var msg=confirm("亲爱的商家，确定无误?");
            if(msg==true)
            {
                changeGoodsMessage(event);
                return true;
            }
            else
            {
                return false;
            }
        }

        function confirmDeleteGoods(event,indentId){
            event.preventDefault();
            var msg=confirm("亲爱的商家，确定无误?");
            if(msg==true)
            {
                deleteGoods(event,indentId);
                return true;
            }
            else
            {
                return false;
            }
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

<h1 align="center"><font color="purple">商家管理商品界面</font></h1>


<div id="1" style="display: none" >

    <FORM align="center"  method="post"  >
        <%--    <input type="button" value="修改订单" class="check" style="margin-bottom: 0"/>--%>
        <%--    <br>--%>
            <a  class="text">需要修改的商品的id :</a><br>
            <input type="text"  pattern="^\d{1,10}$" id="id" class="text" name="id" required  /><br>
            <a  class="text">需要修改的商品名 :</a><br>
            <input type="text"   class="text" name="goodsName" id="goodsName" pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+$" required  />
        <br>



        <a  class="text">需要修改的单价:</a><br>
        <input type="text" class="text" pattern="^\d{1,10}$" name="price" id="price" pattern="^\d{1,8}$"required  />
        <br>
        <a  class="text">修改的商品存货数量:</a><br>
        <input type="text" class="text" pattern="^\d{1,10}$" name="amount" id="amount" pattern="^\d{1,8}$" required  />
        <br>
        <input type="submit" value="提交" onclick="return confirmChangeGoods(event)"  class="text" >
    </FORM>
</div>

<div class="font">
    <table  border="0px" width="70%" align="center" cellspacing="0px" class="table">
        <tr>
            <th>Id</th>
            <th>商品名称</th>
            <th>商家名</th>
            <th>商家信誉分</th>
            <th>商品类型</th>
            <th>商品信息</th>
            <th>单价</th>
            <th>存货数量</th>
            <th>今日销量</th>
            <th>推荐信息</th>
            <th>商品状态</th>
            <th>操作</th>
        </tr>
        <!--通过循环 显示信息-->


        <%
            List<Goods> emps = (List<Goods>)request.getAttribute("goodsList");
            for(Goods goods : emps){
        %>
        <tr>
            <td><%=goods.getId()%></td>
            <td><%=goods.getGoodsName()%></td>
            <td><%=goods.getSeller()%></td>
            <td><%=goods.getSellerReputation()%></td>
            <td><%=goods.getType()%></td>
            <td><%=goods.getImformation()%></td>
            <td><font color="#00ced1"><%=goods.getPrice()%>元</font></td>
            <td><%=goods.getAmount()%></td>
            <td><font color="#00ced1"><%=goods.getBoughtAmount()%></font></td>
            <td><%=goods.getRecommend()%></td>
            <td><font color="#00ced1"><%=goods.getStatus()%></font></td>
            <td>
                <c:if test='<%=(goods.getStatus().contains("已审核"))%>'>
                    <a href="#" onclick="appear(event,<%=goods.getId()%>,'<%=goods.getGoodsName()%>',
                            <%=goods.getPrice()%>,<%=goods.getAmount()%>)"><font color="#00bfff">修改商品</font></a>
                    <a href="#" onclick="confirmDeleteGoods(event,<%=goods.getId()%>)">
                        <font color="#00bfff">删除商品
                    </font></a>

                </c:if>


            </td>





        <tr>
                <%
        }
    %>




    </table>
</div>



</body>
</html>
