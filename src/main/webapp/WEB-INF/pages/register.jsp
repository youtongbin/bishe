<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账户注册</title>
    <style type="text/css">
        body,input{
            margin: 0;
            padding: 0;
            background: pink;
        }
        input{
            display: inline-block;
            background: #fff;
        }
        .xiao-container{
            width: 100%;
        }
        .xiao-register-box{
            position: relative;
            height: 680px;
            width: 800px;
            top: 50px;
            margin: 0 auto;
            z-index: 99999;
            background:#ffffff;
            border: 7px solid #ccc;
        }
        .xiao-title-box{
            position: absolute;
            width: 300px;
            height: 50px;
            margin-left: 250px;
            margin-top: 5px;
            text-align: center;
            font-size: 28px;
            font-weight: 800;
            color: #ff5000;
            line-height: 50px;
        }
        .xiao-username-box{
            position: absolute;
            width: 420px;
            height: 40px;
            line-height: 40px;
            margin-top:100px;
            margin-left:80px;
            font-weight: 700;
        }
        .xiao-username-input{
            display: inline-block;
            margin-left: 45px;
            /*background: green;*/
        }
        #username{
            height: 35px;
            width: 290px;
            border: 2px solid #ccc;
            border-radius: 5px;
        }
        .xiao-userPassword-box{
            position: absolute;
            width: 420px;
            height: 40px;
            line-height: 40px;
            margin-top:180px;
            margin-left:80px;
            font-weight: 700;
        }
        .xiao-userPassword-input{
            display: inline-block;
            margin-left: 61px;
        }
        #password{
            height: 35px;
            width: 290px;
            border: 2px solid #ccc;
            border-radius: 5px;
        }
        .xiao-userRePassword-box{
            position: absolute;
            width: 420px;
            height: 40px;
            line-height: 40px;
            margin-top:260px;
            margin-left:80px;
            font-weight: 700;
        }
        .xiao-userRePassword-input{
            display: inline-block;
            margin-left: 28px;
        }
        #userRePassword{
            height: 35px;
            width: 290px;
            border: 2px solid #ccc;
            border-radius: 5px;
        }
        .xiao-userPhone-box{
            position: absolute;
            width: 420px;
            height: 40px;
            line-height: 40px;
            margin-top:340px;
            margin-left:80px;
            font-weight: 700;
        }
        .xiao-userPhone-input{
            display: inline-block;
            margin-left: 28px;
        }
        #phone{
            height: 35px;
            width: 290px;
            border: 2px solid #ccc;
            border-radius: 5px;
        }
        .xiao-userEmail-box{
            position: absolute;
            width: 420px;
            height: 40px;
            line-height: 40px;
            margin-top:420px;
            margin-left:80px;
            font-weight: 700;
        }
        .xiao-userEmail-input{
            display: inline-block;
            margin-left: 28px;
        }
        #email{
            height: 35px;
            width: 290px;
            border: 2px solid #ccc;
            border-radius: 5px;
        }

        .xiao-require{
            color: red;
        }
        .xiao-submit-box{
            position:absolute;
            width: 80px;
            height: 40px;
            line-height: 40px;
            margin-top: 520px;
            margin-left:200px;
            border-radius: 5px;
            background: grey;
        }
        #xiao-submit-button{
            display: inline-block;
            width: 80px;
            height: 40px;
            border-radius: 5px;
            background: red;
        }
        .xiao-goLogin-box{
            position:absolute;
            width: 150px;
            height: 20px;
            margin-top: 540px;
            margin-left:320px;

        }

    </style>
</head>
<body>
<div class="xiao-container">

    <div class="xiao-register-box">
        <div class="xiao-title-box">
            <span>用户注册</span>
        </div>
        <form id="register_fm">
            <div class="xiao-username-box">
                <span class="xiao-require">*</span>
                <label for="username">用户名</label>
                <div class="xiao-username-input">
                    <input type="text" id="username" name="username" placeholder="请输入用户名 长度:6-12个字符" />
                </div>
            </div>

            <div class="xiao-userPassword-box">
                <span class="xiao-require">*</span>
                <label for="password">密码</label>
                <div class="xiao-userPassword-input">
                    <input type="password" id="password" name="password" placeholder="请输入密码 长度:6-12个字符" />
                </div>
            </div>

            <div class="xiao-userRePassword-box">
                <span class="xiao-require">*</span>
                <label for="userRePassword">确认密码</label>
                <div class="xiao-userRePassword-input">
                    <input type="password" id="userRePassword" name="userRePassword" placeholder="请重复输入密码" />
                </div>
            </div>

            <div class="xiao-userPhone-box">
                <span class="xiao-require">*</span>
                <label for="phone">手机号码</label>
                <div class="xiao-userPhone-input">
                    <input type="text" id="phone" name="phone" placeholder="请输入您的手机号码，11位有效数字" />
                </div>
            </div>

            <div class="xiao-userEmail-box">
                <span class="xiao-require">*</span>
                <label for="email">电子邮箱</label>
                <div class="xiao-userEmail-input">
                    <input type="text" id="email" name="email" placeholder="请输入您的邮箱地址，如：123@qq.com" />
                </div>
            </div>

            <div class="xiao-submit-box">
                <input id = "xiao-submit-button" type="button" value="注册">
            </div>

            <div class="xiao-goLogin-box">
                <a href="login.do" style="text-decoration: none;">已有账号？去登录</a>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.serializejson.min.js"></script>
<script>

    $(function(){

        $("#xiao-submit-button").click(function () {
            var jsonObj = $("#register_fm").serializeJSON()
            var username_len = $("#username").val().toString().length
            var password = $("#password").val().toString()
            var rePassword = $("#userRePassword").val().toString()
            var phone = $("#phone").val().toString()
            var email = $("#email").val().toString()
            
            if (username_len >= 6 && username_len <= 12){
                if (password.length >= 6 && password.length <= 12){
                    if (password==rePassword){
                        if(/^1[34578]\d{9}$/.test(phone)){
                            if (/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/.test(email)){
                                $.ajax({
                                    type:"post",
                                    url:"user/register.do",
                                    contentType: "application/json",
                                    data:JSON.stringify(jsonObj),
                                    success:function (result) {
                                        alert(result.msg)
                                        if (result.code == 0){
                                            window.location.href="login.do"
                                        }
                                    }
                                })
                            } else {
                                alert("邮箱格式输入错误")
                            }
                        }else {
                            alert("手机号码有误，请重填")
                        }
                    } else {
                        alert("密码验证错误")
                    }
                } else {
                    alert("密码输入错误")
                }
            }else {
                alert("用户名输入错误")
            }

        })

    })

</script>


</body>
</html>