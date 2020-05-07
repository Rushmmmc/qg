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

    <script>
        function fun(event) {
            event.preventDefault();
            var id = document.getElementById("id").value;
            var goodsName = document.getElementById("goodsName").value;
            var price = document.getElementById("price").value;
            var amount = document.getElementById("amount").value;

            var log_id = /^\d{1,8}$/;
            var log_price = /^\d{1,8}$/;
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
                alert("商品价格不可为空,仅可包含整数┭┮﹏┭┮");
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
                    url: "/ChangeIndentController",
                    type: "POST",
                    dataType: 'html',
                    data: "method=changeIndent&id=" + id+"&goodsName="+goodsName+"&price="+price+"&amount="+amount,
                    success: function (result) {
                        alert(result);
                        location.href = "/ChangePageController?method=manageIndent";
                    },
                    error: function (msg) {
                        alert("出错啦")
                    }
                });
            }
        }


        function fun2(event) {
            event.preventDefault();
            var id = document.getElementById("id2").value;
            var message = document.getElementById("message").value;

            var log_id = /^\d{1,8}$/;
            var log_message = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;

            var flag_i = log_id.test(id);
            var flag_m = log_message.test(message);


            if (!flag_m) {
                alert("消息不可为空或包含特殊符号┭┮﹏┭┮");
                return;
            }


            if (!flag_i) {
                alert("请注意id格式┭┮﹏┭┮");
                return;
            }
            if (flag_i && flag_m ) {
                $.ajax({
                    url: "/IndentSendMessageController?method=sellerSendMessage",
                    type: "POST",
                    dataType: 'html',
                    data: "id=" + id+"&message="+message,
                    success: function (result) {
                        alert(result);
                        location.href = "/ChangePageController?method=manageIndent";
                    },
                    error: function (msg) {
                        alert("出错啦")
                    }
                });
            }
        }

        function fun3(event,id) {
            event.preventDefault();


            var log_id = /^\d{1,8}$/;

            var flag_i = log_id.test(id);


            if (!flag_i) {
                alert("请注意id格式┭┮﹏┭┮");
                return;
            }
            if (flag_i  ) {
                $.ajax({
                    url: "/ChangeIndentController",
                    type: "POST",
                    dataType: 'html',
                    data: "id=" + id+"&method=sell",
                    success: function (result) {
                        alert(result);
                        location.href = "/ChangePageController?method=manageIndent";
                    },
                    error: function (msg) {
                        alert("出错啦")
                    }
                });
            }
        }


    </script>


<script>
    function fun7(event,id) {
    event.preventDefault();

    var log_id = /^\d{1,8}$/;

    var flag_i = log_id.test(id);


    if (!flag_i) {
    alert("请注意id格式┭┮﹏┭┮");
    return;
    }
    if (flag_i  ) {
    // $.ajax({
    //     url: "/ChangePageController?method=manageBuyerPersonalIndent",
    //     type: "POST",
    //     dataType: 'html',
    //     data: "id=" + id+"&ifSeller=0",
    // });
    location.href = "/ChangePageController?method=manageIndent&id="+id+"&ifSeller=1";
    }
    }
    </script>


</head>
<body>
<body >
<a href="/login.jsp">返回登录页面</a>
<a href="/DividePageController">返回主页面</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController?method=commit">申卖商品</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController?method=manageIndent&ifSeller=1">管理卖出订单</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController?method=manageBuyerPersonalIndent">管理买入订单</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageToShoppingCarController">查看购物车</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageToHelpUser">进行申诉</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/Quit">注销</a>
<FORM align="center"  method="post" >
    <input type="button" value="修改订单" class="check" style="margin-bottom: 0"/>
    <br>
    <a style="display:none;margin-top: 0px" class="text">需要修改的订单id :</a>
    <input type="text" style="display:none;" pattern="^\d{1,10}$" id="id" class="text" name="id" required style="display:none;" />
    <br>
    <a style="display:none;margin-top: 0px" class="text">需要修改的订单商品名 :</a>
    <input type="text" style="display:none;"  class="text" name="goodsName" id="goodsName" pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+$" required style="display:none;" />
    <br>
    <a style="display:none;" class="text">需要修改的订单单价:</a>
    <input type="text" class="text" pattern="^\d{1,10}$" name="price" id="price" pattern="^\d{1,8}$"required style="display:none;" />
    <br>
    <a style="display:none;" class="text">修改的订单商品数量:</a>
    <input type="text" class="text" pattern="^\d{1,10}$" name="amount" id="amount" pattern="^\d{1,8}$" required style="display:none;" />
    <br>
    <input type="submit" value="提交" onclick="fun(event)"  class="text" style="display:none;">
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
            <td><a  onclick="fun3(event,<%=indent.getId()%>)" href="#">发货</a>
                <c:if test='<%=!(indent.getBuyerMessage().contains("暂无"))%>'>
                    <a>&nbsp&nbsp&nbsp&nbsp&nbsp</a>
                    <a href="/ChangePageController?method=messageBoard&ifSeller=1">用户给您留言啦,请打开留言板</a>
                </c:if>
                <a o href="/ChangePageController?method=messageBoard&ifSeller=1&id=<%=indent.getId()%>">前往留言</a>
        </td>
        <tr>
                <%
        }
    %>




    </table>
</div>



</body>
</html>
