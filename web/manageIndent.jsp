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

<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/bootstrap/css/bootstrap-theme.css">
<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<html>
<head>
    <title>商家管理订单界面</title>





    <script>
        function fun(event) {
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
                    url: "/IndentController/changeIndent",
                    type: "POST",
                    dataType: 'html',
                    data: "indentId=" + id+"&goodsName="+goodsName+"&price="+price+"&amount="+amount,
                    success: function (result) {
                        alert(result);
                        location.href = "/ChangePageController/goCheckSalesIndent";
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
                    url: "/IndentController/indentSendMessage?method=sellerSendMessage",
                    type: "POST",
                    dataType: 'html',
                    data: "id=" + id+"&message="+message,
                    success: function (result) {
                        alert(result);
                        location.href = "/ChangePageController/goCheckSalesIndent";
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
                    url: "/IndentController/sellerSendGoods",
                    type: "POST",
                    dataType: 'html',
                    data: "indentId=" + id+"&amount="+amount,
                    success: function (result) {
                        alert(result);
                        location.href = "/ChangePageController/goCheckSalesIndent";
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

    location.href = "/ChangePageController/goCheckSalesIndent";
    }
    }

    function appear(event,id,goodsName,price,amount){
        event.preventDefault();
        document.getElementById("1").style.display = "block";
        document.getElementById("id").value = id;
        document.getElementById("goodsName").value = goodsName;
        document.getElementById("price").value = price;
        document.getElementById("amount").value = amount;
    }

    function fun8(event,indentId,recommend) {
        event.preventDefault();

            $.ajax({
                url: "/GoodsController/recommend",
                type: "POST",
                dataType: 'html',
                data: "indentId=" + indentId+"&recommend="+recommend,
                success: function (result) {
                    alert(result);
                    location.href = "/ChangePageController/goCheckSalesIndent";
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
    }


            function fun11(event,indentId) {
                event.preventDefault();
                var log_id = /^\d{1,8}$/;

                var flag_i = log_id.test(indentId);

                if (!flag_i) {
                    alert("请注意id格式┭┮﹏┭┮");
                    return;
                }

                if (flag_i ) {
                    $.ajax({
                        url: "/GoodsController/returnGoods",
                        type: "POST",
                        dataType: 'html',
                        data: "ifSeller=1&indentId="+indentId+"&type=agreeReturn",
                        success: function (result) {
                            alert(result);
                            location.href = "/ChangePageController/goCheckSalesIndent";
                        },
                        error: function (msg) {
                            alert("出错啦")
                        }
                    });
                }
            }


    function fun12(event,indentId) {
        event.preventDefault();
        var log_id = /^\d{1,8}$/;

        var flag_i = log_id.test(indentId);

        if (!flag_i) {
            alert("请注意id格式┭┮﹏┭┮");
            return;
        }

        if (flag_i ) {
            $.ajax({
                url: "/GoodsController/returnGoods",
                type: "POST",
                dataType: 'html',
                data: "ifSeller=1&indentId="+indentId+"&type=rejectReturn",
                success: function (result) {
                    alert(result);
                    location.href = "/ChangePageController/goCheckSalesIndent";
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
    }

        function deleteIndent(event,id) {
            event.preventDefault();

            var log_id = /^\d{1,8}$/;

            var flag_i = log_id.test(id);



            if (!flag_i) {
                alert("请注意id格式┭┮﹏┭┮");
                return;
            }
            if (flag_i  ) {
                $.ajax({
                    url: "/IndentController/deleteIndent",
                    type: "POST",
                    dataType: 'html',
                    data: "indentId=" + id +"&ifseller=1",
                    success: function (result) {
                        alert(result);
                        location.href = "/ChangePageController/goCheckBuyIndent";
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
    </script>


</head>
<body>

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





       <h3> <a align="center" href="/IndentController/generateXlsIndent"><font><p>一键生成订单xls文件并下载</p></font></a> </h3>

    <h1 align="center"><font color="purple">商家管理商品订单</font></h1>
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
            <th>好/差评</th>
            <th>详细评价</th>
            <th>操作</th>
        </tr>
        <!--通过循环 显示信息-->


        <%
            List<Indent> emps = (List<Indent>)request.getAttribute("emps");
            for(Indent indent : emps){
                if(indent.getIfSellerDelete() == 1){
                    continue;
                }
        %>
        <tr>
            <td><%=indent.getId()%></td>
            <td><%=indent.getGoodsName()%></td>
            <td><%=indent.getBuyer()%></td>
            <td><%=indent.getSeller()%></td>
            <td><font color="#00ced1"><%=indent.getPrice()%>元</font></td>
            <td><%=indent.getAmount()%></td>
            <td><font color="#00ced1"><%=indent.getTotalPrice()%>元</font></td>
            <td><%=indent.getUseIntegral()%></td>
            <td><font color="#00ced1"><%=indent.getActuallyPrice()%>元</font></td>
            <td><%=indent.getStatus()%></td>
            <td><%=indent.getReputation()%></td>
            <td><%=indent.getEvaluate()%>
                <c:if test='<%=!indent.getEvaluate().contains("暂无")%>'>
                    <a  onclick="fun8(event,<%=indent.getId()%>,'<%=indent.getEvaluate()%>')" href="#" )>
                        <font color="red">把该评价设为商品推荐信息</font></a>
                </c:if>


            </td>
            <td>
                <c:if test='<%=indent.getStatus().contains("用户申请退货")%>'>
                    <a onclick="fun11(event,<%=indent.getId()%>)" href="#" >同意退货</a>
                    <a>/</a><a onclick="fun12(event,<%=indent.getId()%>)" href="#" >拒绝退货并让小二介入</a>
                </c:if>
                <c:if test='<%=(indent.getStatus().contains("订单已完成") || indent.getStatus().contains("已退货"))%>'>
                    <a  onclick="deleteIndent(event,<%=indent.getId()%>)" href="#">删除订单</a>
                </c:if>
                <c:if test='<%=!(indent.getStatus().contains("取消")) && !(indent.getStatus().contains("退货")) &&
                !(indent.getStatus().contains("在路上"))%>'>
                <a  onclick="fun3(event,<%=indent.getId()%>)" href="#">发货</a>
                </c:if>
                    <c:if test='<%=!(indent.getBuyerMessage().contains("暂无"))%>'>
                    <a>&nbsp&nbsp&nbsp&nbsp&nbsp</a>
                    <a href="/ChangePageController/goToMessageBoard?ifSeller=1&id=<%=indent.getId()%>"><font color="#ff1493">用户给您留言啦,请打开留言板</font></a>
                </c:if>
                <a  href="/ChangePageController/goToMessageBoard?ifSeller=1&id=<%=indent.getId()%>"><font color="#8a2be2">前往留言</font></a>
                <a  href="#" onclick="appear(event,<%=indent.getId()%>,'<%=indent.getGoodsName()%>',
                    <%=indent.getPrice()%>,<%=indent.getAmount()%>)">
                    <c:if test='<%=!(indent.getStatus().contains("退货"))%>'>
                    <font color="#00bfff">修改订单</font></a>
                </c:if>
            </td>
        <tr>
                <%
        }
    %>




    </table>
</div>

<div id="1" style="display: none" >
    <br><br><br><br><br>
    <FORM align="center"  method="post"  >
        <%--    <input type="button" value="修改订单" class="check" style="margin-bottom: 0"/>--%>
        <%--    <br>--%>
        <a  class="text">需要修改的订单id :</a><br>
        <input type="text"  pattern="^\d{1,10}$" id="id" class="text" name="id" required  />
        <br>
        <a  class="text">需要修改的订单商品名 :</a><br>
        <input type="text"   class="text" name="goodsName" id="goodsName" pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+$" required  />
        <br>
        <a  class="text">需要修改的订单单价:</a><br>
        <input type="text" class="text" pattern="^\d{1,10}$" name="price" id="price" pattern="^\d{1,8}$"required  />
        <br>
        <a  class="text">修改的订单商品数量:</a><br>
        <input type="text" class="text" pattern="^\d{1,10}$" name="amount" id="amount" pattern="^\d{1,8}$" required  />
        <br>
        <input type="submit" value="提交" onclick="fun(event)"  class="text" >
    </FORM>
</div>

</body>
</html>
