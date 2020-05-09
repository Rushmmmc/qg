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
    <style>
        img[src=""],img:not([src]){
            opacity:0;
        }
    </style>
    <script>
        function uploadPhoto() {
            $("#photoFile").click();
        }

        /**
         * 上传图片
         */
        function upload() {
            if ($("#photoFile").val() == '') {
                return;
            }
            var formData = new FormData();
            formData.append('photo', document.getElementById('photoFile').files[0]);
            $.ajax({
                url:"/GoodsController/uploadPhoto?goodsName=",
                type:"post",
                data: formData,
                processData: false,
                contentType: false,
                success: function(data) {
                    if(data === "管理员正在火速审核中！") {
                        alert(data);
                        location.href = "/DividePageController";
                    }else {
                        alert(data)
                    }
                },
                error:function(data) {
                    alert("上传失败")
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
<br><br><br>
<h1 align="center">欢迎<font color="#8a2be2" >   ${sessionScope.username}</font> </h1>
<h1 align="center">亲爱的<font color="#1e90ff" >   ${sessionScope.sendLevel},欢迎您成为卖家</font> </h1>

<form   align="center"  method="post" enctype="multipart/form-data">

    <h2 align="center"><a href="javascript:void(0)" onclick="uploadPhoto()">选择图片</a></h2>
    <input type="file" accept="image/jpeg"  id="photoFile" style="display: none;" onchange="upload()">

    <%--        商品图片<br>--%>
<%--        <input type="file" style="margin-left: 580px" id="file" accept="image/pjpeg"  >--%>
    <br>

</form>

</body>
</html>
