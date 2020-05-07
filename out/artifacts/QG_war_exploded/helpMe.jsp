<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zhangmengcong.www.po.Appeal" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zhangmengcong.www.po.Indent" %><%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/5/3
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">
<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<html>
<head>
                    <script>
                    function fun(event) {
                        event.preventDefault();
                        var id = document.getElementById("id").value;
                        var reason = document.getElementById("reason").value;
                        var type = document.getElementById("type").value;
                        var log_reason = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;
                        var log_id = /^\d{1,8}$/;
                        var flag_i = log_id.test(id);
                        var flag_reason = log_reason.test(reason);
                        var flag_type = false;
                        if(type === "投诉商家" || type === "交易维权"){
                            flag_type = true;
                        }else {
                            alert("请注意类型仅支持投诉商家及交易维权");
                            return;
                        }
                        if (!flag_i) {
                            alert("请注意id格式┭┮﹏┭┮");
                            return;
                        }
                        if (!flag_reason) {
                            alert("请注意原因格式┭┮﹏┭┮");
                            return;
                        }
                        if (flag_i && flag_reason && flag_type) {
                            $.ajax({
                                url: "/UserCommitAppealController",
                                type: "POST",
                                dataType: 'html',
                                data: "indentId=" + id + "&reason="+reason+"&type="+type,
                                success: function (result) {
                                    alert(result);
                                    location.href = "/ChangePageToHelpUser";
                                },
                                error: function (msg) {
                                    alert("出错啦")
                                }
                            });
                        }
                    }

                    </script>





    <script>

        function fun2(event,id) {
            event.preventDefault();
            document.getElementById("1").style.display = "block";
            document.getElementById("id").value = id;
        }



    </script>

    <title>处理申诉</title>
</head>
<div>

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
<h1 align="center" ><font color="#ff1493" >申诉信息将长期留底,消息按时间逆序显示</font></h1>


<div id="1" style="display: none">
<FORM align="center"   method="post" >


    <select id="type" name="type" class="text"  >
        <option value="投诉商家">投诉商家</option>
        <option value="交易维权">交易维权</option>
    </select>

    <br>
    <a style="display:none;margin-top: 0px" class="text">订单Id:</a><br>
    <input type="text"  class="text" id="id" name="indentId" pattern="^\d{1,8}$" required  />
    <br>
    <a  class="text">申诉理由:</a><br>
    <input type="text" class="text"name="reason" id="reason" required  pattern="^[a-zA-Z0-9\u4e00-\u9fa5]+$" />
    <br>
    <input type="submit" value="提交"  class="text"  onclick="fun(event)" >
</FORM>
</div>








</div>


<div class="font">
    <table  border="0px" width="70%" align="center" cellspacing="0px" class="table">
        <tr>
            <th>Id</th>
            <th>申诉类型</th>
            <th>申诉用户</th>
            <th>订单号</th>
            <th>被投诉商家</th>
            <th>内容</th>
            <th>申诉状态</th>
            <th>申诉时间</th>
            <th>管理员留言</th>
        </tr>
        <!--通过循环 显示信息-->


        <%
            List<Appeal> emps = (List<Appeal>)request.getAttribute("appealList");
            for(Appeal appeal :emps){
        %>
        <tr>
            <td><%=appeal.getId()%></td>
            <td><%=appeal.getType()%></td>
            <td><%=appeal.getUsername()%></td>
            <td><%=appeal.getIdentId()%></td>
            <td><%=appeal.getSeller()%></td>
            <td><%=appeal.getReason()%></td>
            <td><%=appeal.getStatus()%></td>
            <td><%=appeal.getAppealDate()%></td>
            <td><%=appeal.getMessage()%></td>

        <tr>

                <%
        }
    %>
    </table>
</div>
<br><br><br><br>
<h2 align="center"><font  color="#8a2be2">您的订单</font></h2>
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
                <th>操作</th>
            </tr>
            <!--通过循环 显示信息-->


            <%
                List<Indent> emps2 = (List<Indent>)request.getAttribute("emps");
                for(Indent indent : emps2){
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
                <td><a href="#" onclick="fun2(event,<%=indent.getId()%>)">申诉</a></td>
            <tr>
                    <%
        }
    %>




        </table>

    </div>





</body>
</html>
