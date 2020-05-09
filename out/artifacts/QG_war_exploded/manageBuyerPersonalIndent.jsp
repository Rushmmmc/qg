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
<script>

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
    url: "/IndentSendMessageController?method=buyerSendMessage",
    type: "POST",
    dataType: 'html',
    data: "id=" + id+"&message="+message,
    success: function (result) {
    alert(result);
    location.href = "/ChangePageController?method=manageBuyerPersonalIndent";
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
                url: "/ChangeIndentController?method=deleteIndent",
                type: "POST",
                dataType: 'html',
                data: "id=" + id,
                success: function (result) {
                    alert(result);
                    location.href = "/ChangePageController?method=manageBuyerPersonalIndent";
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
    }


    function fun4(event,id) {
        event.preventDefault();

        var log_id = /^\d{1,8}$/;

        var flag_i = log_id.test(id);



        if (!flag_i) {
            alert("请注意id格式┭┮﹏┭┮");
            return;
        }

        if (flag_i  ) {
            $.ajax({
                url: "ChangeIndentController?method=finishIndent",
                type: "POST",
                dataType: 'html',
                data: "id=" + id,
                success: function (result) {
                    alert(result);
                    location.href = "/ChangePageController?method=manageBuyerPersonalIndent";
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
    }





    function fun5(event,id) {
        event.preventDefault();

        var log_id = /^\d{1,8}$/;

        var flag_i = log_id.test(id);



        if (!flag_i) {
            alert("请注意id格式┭┮﹏┭┮");
            return;
        }
        if (flag_i  ) {
            $.ajax({
                url: "/GiveReputationController?method=goodReputation",
                type: "POST",
                dataType: 'html',
                data: "id=" + id,
                success: function (result) {
                    alert(result);
                    location.href = "/ChangePageController?method=manageBuyerPersonalIndent";
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
    }
    function fun6(event,id) {
        event.preventDefault();

        var log_id = /^\d{1,8}$/;

        var flag_i = log_id.test(id);



        if (!flag_i) {
            alert("请注意id格式┭┮﹏┭┮");
            return;
        }
        if (flag_i  ) {
            $.ajax({
                url: "/GiveReputationController?method=badReputation",
                type: "POST",
                dataType: 'html',
                data: "id=" + id,
                success: function (result) {
                    alert(result);
                    location.href = "/ChangePageController?method=manageBuyerPersonalIndent";
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
    }

    function fun7(event,id) {
        event.preventDefault();

        var log_id = /^\d{1,8}$/;

        var flag_i = log_id.test(id);


        if (!flag_i) {
            alert("请注意id格式┭┮﹏┭┮");
            return;
        }
        if (flag_i  ) {
            location.href = "/ChangePageController?method=manageBuyerPersonalIndent&id="+id+"&ifSeller=0";
        }
    }

    function fun8(event) {
        event.preventDefault();
        var indentId = document.getElementById("indentId").value;
        var evaluate = document.getElementById("evaluate").value;

        var log_id = /^\d{1,8}$/;
        var log_evaluate = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;


        var flag_i = log_id.test(indentId);
        var flag_l = log_evaluate.test(evaluate);

        if (!flag_i) {
            alert("请注意id格式┭┮﹏┭┮");
            return;
        }
        if(!flag_l){
            alert("评价不可为空或包含特殊符号┭┮﹏┭┮");
            return;
        }
        if (flag_i &&flag_l ) {
            $.ajax({
                url: "/AddEvaluateMessage",
                type: "POST",
                dataType: 'html',
                data: "indentId=" + indentId+"&evaluate="+evaluate,
                success: function (result) {
                    alert(result);
                    location.href = "/ChangePageController?method=manageBuyerPersonalIndent";
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
    }

    function fun9(event,id) {
        event.preventDefault();
        document.getElementById("2").style.display = "block";
        document.getElementById("indentId").value = id;
    }



    function fun10(event,indentId) {
        event.preventDefault();
        var log_id = /^\d{1,8}$/;

        var flag_i = log_id.test(indentId);

        if (!flag_i) {
            alert("请注意id格式┭┮﹏┭┮");
            return;
        }

        if (flag_i ) {
            $.ajax({
                url: "/ReturnGoodsController",
                type: "POST",
                dataType: 'html',
                data: "ifSeller=0&indentId="+indentId,
                success: function (result) {
                    alert(result);
                    location.href = "/ChangePageController?method=manageBuyerPersonalIndent";
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
    }

    function giveUp(event,indentId) {
        event.preventDefault();
        var log_id = /^\d{1,8}$/;

        var flag_i = log_id.test(indentId);

        if (!flag_i) {
            alert("请注意id格式┭┮﹏┭┮");
            return;
        }

        if (flag_i ) {
            $.ajax({
                url: "/GiveUpIndentController",
                type: "POST",
                dataType: 'html',
                data: "indentId="+indentId,
                success: function (result) {
                    alert(result);
                    location.href = "/ChangePageController?method=manageBuyerPersonalIndent";
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
    }

</script>











    <title>用户订单中心</title>

</head>
<body>

<a href="/login.jsp">返回登录页面</a><a>&nbsp&nbsp&nbsp</a>
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

<br><br><br>
    <h1 align="center"><font color="purple">用户购买订单</font></h1>
<br><br><br>
</div>

<div id="2" style="display: none" align="center">
    <h2><font color="#ff1493">您的评价很可能成为商品的介绍内容</font><br></h2>
    评价订单id<br>
    <input type="text" id="indentId"><br>
    评价内容<br>
    <input type="text" id="evaluate">
    <br>
    <input type="submit" onclick="fun8(event)">


</div>


<div class="font">
    <table  border="0px" width="70%" align="center" cellspacing="0px" class="table">
        <tr>
            <th>Id</th>
            <th>商品名称</th>
            <th>购买者<a>&nbsp</a></th>
            <th>出售者</th>
            <th>单价</th>
            <th>数量<a>&nbsp</a></th>
            <th>总价</th>
            <th>使用积分<a>&nbsp</a></th>
            <th>实际付款<a>&nbsp</a></th>
            <th>订单状态</th>
            <th>评价<a>&nbsp</a></th>
            <th>详细评价</th>
            <th>  <a>&nbsp&nbsp</a> <a>&nbsp&nbsp</a>
                管理</th>
        </tr>
        <!--通过循环 显示信息-->


        <%
            List<Indent> emps = (List<Indent>)request.getAttribute("emps");
            for(Indent indent : emps){
                    if(indent.getIfBuyerDelete() == 1){
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
            <td><%=indent.getEvaluate()%></td>
            <td>
                <c:if test='<%=(indent.getStatus().contains("未接单"))%>'>
                    <a>&nbsp&nbsp</a>  <a onclick="giveUp(event,<%=indent.getId()%>)" href="#">放弃订单</a>
                </c:if>


                <c:if test='<%=(indent.getStatus().contains("在路上"))%>'>
                    <a>&nbsp&nbsp</a>  <a onclick="fun4(event,<%=indent.getId()%>)" href="#">确认收货</a>
                </c:if>
                <c:if test='<%=(indent.getStatus().contains("完成")) && indent.getReputation().contains("暂无")%>'>

                    <a onclick="fun9(event,<%=indent.getId()%>)" href="#"><font color="red">给商品进行详细评价</font></a>
                    <a>&nbsp&nbsp</a>
                    <a onclick="fun5(event,<%=indent.getId()%>)" href="#">给好评</a>
                    <a>&nbsp&nbsp</a>
                    <a onclick="fun6(event,<%=indent.getId()%>)" href="#">给差评</a>


                </c:if>
            <c:if test='<%=indent.getStatus().contains("完成") || indent.getStatus().contains("已退款") %>'>
                <a>&nbsp&nbsp</a>
            <a onclick="fun3(event,<%=indent.getId()%>)" href="#" >删除订单</a>
            </c:if>
                <c:if test='<%=indent.getStatus().contains("在路上")%>'>
                    <a>&nbsp&nbsp</a>
                    <a onclick="fun10(event,<%=indent.getId()%>)" href="#" >申请退货</a>
                </c:if>

                <c:if test='<%=(indent.getStatus().contains("介入"))%>'>
                    <font color="blue">商家拒绝退货,闲鱼小二已介入,请关注申诉中心！</font>
                </c:if>

                <c:if test='<%=!(indent.getSellerMessage().contains("暂无"))%>'>
                    <a>&nbsp&nbsp</a>
                    <a href="/ChangePageController?method=messageBoard&ifSeller=0&id=<%=indent.getId()%>"><font color="#ff1493">商家给您留言啦,请打开留言板</font><a>&nbsp&nbsp</a></a>
                </c:if>

                    <a>&nbsp&nbsp</a>
                    <a  href="/ChangePageController?method=messageBoard&ifSeller=0&id=<%=indent.getId()%>">前往留言</a>


            </td>
        <tr>
                <%
        }
    %>




    </table>

</div>




</body>
</html>
