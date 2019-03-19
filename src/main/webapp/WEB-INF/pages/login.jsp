<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账户登录</title>
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
        .xiao-require{
            color: red;
        }
        .xiao-submit-box{
            position:absolute;
            width: 80px;
            height: 40px;
            line-height: 40px;
            margin-top: 280px;
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
            margin-top: 300px;
            margin-left:320px;

        }

    </style>
</head>
<body>
<div class="xiao-container">
    <div class="xiao-register-box">
        <div class="xiao-title-box">
            <span>账户登录</span>
        </div>
        <form id="login_fm">
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

            <div class="xiao-submit-box">
                <input id = "xiao-submit-button" type="button" value="登录">
            </div>

            <div class="xiao-goLogin-box">
                <a href="register.do" style="text-decoration: none;">没有账号？去注册</a>
            </div>
        </form>
    </div>
</div>


<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.serializejson.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#xiao-submit-button").click(function () {
            var loginInfo = $("#login_fm").serializeJSON()

            $.ajax({
                url:"user/login.do",
                type:"post",
                contentType:"application/json",
                data: JSON.stringify(loginInfo),
                success:function (result) {
                    console.log(result)
                    if (result.code=="0"){
                        window.location.href="home.do"
                    }else {
                        alert(result.msg)
                    }
                }
            })
        })
    })
</script>

</body>
</html>