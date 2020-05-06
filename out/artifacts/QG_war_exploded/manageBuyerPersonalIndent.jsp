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
</script>











    <title>用户订单中心</title>
    <script>

        $(function(){
            $(".check").click(function(){
                $(".text").show();
            })
        })

    </script>
</head>
<body>
<body >
<a href="/login.jsp">返回登录页面</a>
<a href="/DividePageController">返回主页面</a>


<FORM align="center"   method="post" >
    <input type="button" value="给商家留言" class="check" />
    <br>
    <a class="text"  style="display:none;" >订单id:</a>
    <input type="text"  class="text" id="id2"  name="id2" pattern="^\d{1,10}$" required style="display:none;" />
    <br>
    <a class="text" style="display:none;">内容:</a>
    <input type="text" class="text" id="message" pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+$" name="message" required style="display:none;" />
    <br>
    <input type="submit" value="提交" onclick="fun2(event)" class="text" style="display:none;">
</FORM>

<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading" align="center" ></div>
    <STRONG><p align="center">用户购买订单</p></STRONG>
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
            <th>总价</th>
            <th>使用积分</th>
            <th>实际付款</th>
            <th>订单状态</th>
            <th>评价</th>
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
            <td><%=indent.getBuyer()%></td>
            <td><%=indent.getSeller()%></td>
            <td><%=indent.getPrice()%></td>
            <td><%=indent.getAmount()%></td>
            <td><%=indent.getTotalPrice()%></td>
            <td><%=indent.getUseIntegral()%></td>
            <td><%=indent.getActuallyPrice()%></td>
            <td><%=indent.getStatus()%></td>
            <td><%=indent.getReputation()%></td>
            <td>
                <c:if test='<%=!(indent.getStatus().contains("完成"))%>'>
                    <a onclick="fun4(event,<%=indent.getId()%>)" href="#">确认收货</a>
                    <a>/</a>
                </c:if>
                <c:if test='<%=(indent.getStatus().contains("完成")) && indent.getReputation().contains("暂无")%>'>
                    <a onclick="fun5(event,<%=indent.getId()%>)" href="#">给好评</a>
                    <a onclick="fun6(event,<%=indent.getId()%>)" href="#">给差评</a>
                    <a>/</a>
                </c:if>

            <a onclick="fun3(event,<%=indent.getId()%>)" href="#" >取消(删除)订单</a>
                <c:if test='<%=!(indent.getSellerMessage().contains("暂无"))%>'>
                    <a>&nbsp&nbsp&nbsp&nbsp</a>
                    <a href="/ChangePageController?method=messageBoard&ifSeller=0">商家给您留言啦,请打开留言板</a>
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
