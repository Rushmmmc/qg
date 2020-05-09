<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page import="com.zhangmengcong.www.po.Indent" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zhangmengcong.www.po.Message" %><%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/5/2
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">
<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<html>
<head>
    <title>查看留言</title>
<script>
    function fun(event,id2) {
    event.preventDefault();
    var id = document.getElementById("id").value;
    var message = document.getElementById("message").value;
    var operate = document.getElementById("operate").value;
    var log_id = /^\d{1,8}$/;
    var log_message = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;
    var flag_o = false;
    var flag_i = log_id.test(id);
    var flag_m = log_message.test(message);
    if(operate == 1 || operate == 0){
        flag_o = true;
    }
    if (!flag_m && operate ===0) {
    alert("回复时必须选择信息id进行回复");
    return;
    }
    if(!flag_m){
        alert("消息不可包含特殊符号！");
    }
    if (flag_o && flag_m) {
    $.ajax({
    url: "/MessageController",
    type: "POST",
    dataType: 'html',
    data: "id=" + id+"&ifSeller=0&message="+message+"&ifReply="+operate,
    success: function (result) {
    alert(result);
    location.href ="/ChangePageController?method=messageBoard&ifSeller=0&id="+id2;
    },
    error: function (msg) {
    alert("出错啦")
    }
    });
    }
    }

    function fun2(event,id) {
        event.preventDefault();
        document.getElementById("1").style.display = "block";
        if(id != 0 ){
            document.getElementById("id").value = id;
            document.getElementById("operate").value = 1;
        }else {
            document.getElementById("id").value = "";
            document.getElementById("operate").value = 0;
        }

    }




    </script>








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


<br><br>







<h2><STRONG><p  align="center"><font color="#ff1493">用户面板</font></p></STRONG></h2>
    <h2><STRONG><p  align="center"><font color="#ff1493">订单信息</font></p></STRONG></h2>
</div>
<div class="font">
    <table  border="0px" width="70%" align="center" cellspacing="0px" class="table">
        <tr>
            <th>订单id</th>
            <th>卖家</th>
            <th>买家</th>
            <th>商品名称</th>
            <th>商品单价</th>
            <th>买入数量</th>
            <th>总价</th>
            <th>使用积分</th>
            <th>实际付款</th>
            <th>买家评价</th>
            <th>返回查看买入订单页面</th>
        </tr>
        <!--通过循环 显示信息-->


        <%
            List<Indent> emps = (List<Indent>)session.getAttribute("emps");
            for (Indent indent :emps){
        %>
        <tr><td><%=indent.getId()%></td>
            <td><%=indent.getSeller()%></td>
            <td><%=indent.getBuyer()%></td>
            <td><%=indent.getGoodsName()%></td>
            <td><%=indent.getPrice()%></td>
            <td><%=indent.getAmount()%></td>
            <td><%=indent.getTotalPrice()%></td>
            <td><%=indent.getUseIntegral()%></td>
            <td><%=indent.getActuallyPrice()%></td>
            <td><%=indent.getReputation()%></td>
            <td><a href="/ChangePageController?method=manageBuyerPersonalIndent">返回总订单</a></td>
         <tr>
                <%
        }
    %>




    </table>

</div>



    <h2><STRONG><p  align="center"><font color="#ff1493">关于订单号${sessionScope.emps.get(0).id}的留言板</font></p></STRONG></h2>
    <h3 align="center"> <font color="#8a2be2"><a href="#" onclick="fun2(event,0)">点击留言</a></font></h3>
</div>
<div class="font">
    <table  border="0px" width="70%" align="center" cellspacing="0px" class="table">
        <tr>
            <th>消息id</th>
            <th>商家留言</th>
            <th>用户留言</th>
            <th>处理</th>
        </tr>
        <!--通过循环 显示信息-->


        <%
            List<Message> emps2 = (List<Message>)request.getAttribute("emps2");
            for(Message message : emps2){
                if(message.getBuyerDelete() == 1){
                    continue;
                }
        %>
        <tr><td><%=message.getId()%></td>
            <td><%=message.getSellerMessage()%></td>
            <td><%=message.getBuyerMessage()%></td>

            <td>
                <font color="#8a2be2"><a href="#" onclick="fun2(event,<%=message.getId()%>)">回复</a></font>
                <a>&nbsp&nbsp&nbsp</a><a>&nbsp&nbsp&nbsp</a>

                <a href="/DeleteMessageController?id=<%=message.getId()%>&ifSeller=0">清除留言</a></td>
        <tr>
                <%
        }
    %>





    </table>

</div>



<div id="1" style="display: none">
<form method="post" align="center">
    <select id="operate" name="operate" class="text"  >
        <option value="0">留言</option>
        <option value="1">回复</option>
    </select>
    <br>
    回复信息id(新增留言则可不填)
    <br>
    <input type="text" id="id">
    <br>
    文本信息
    <br>
    <input type="text" id="message">
    <br>
    <input type="submit" onclick="fun(event,${sessionScope.emps.get(0).id})">
</form>
</div>
</body>
</html>
