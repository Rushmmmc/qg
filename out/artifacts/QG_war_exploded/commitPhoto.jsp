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
    <link rel="stylesheet" href="./bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">
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
                url:"/uploadPhoto?goodsName=",
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
    </script>


</head>
<body style="background-color: plum">

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
