<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改个人信息</title>


    <style type="text/css">

        *{
            margin: 0;
            padding: 0;
        }

        #user_fm{
            text-align: center;
        }

        #user_tb{
            width: 100%;
            box-sizing: border-box;
            border-color: aliceblue;
        }

        #user_tb tr{
            height: 50px;
            line-height: 50px;
        }

        #user_tb tr td{
            text-align: center;
        }

        #user_tb tr td:nth-child(0){
            width: 30%;
        }

        input{
            height: 30px;
            line-height: 30px;
            text-indent: 1em;
            border: transparent;
        }

        #submit{
            display: inline-block;
            width: 80px;
            height: 30px;
            margin-top: 30px;
        }

        #role_btn{
            text-align: center;
            margin-left: 20px;
            text-indent: 0;
        }
        #role_btn:hover{
            cursor: pointer;
        }

    </style>

</head>
<body>

<div id="con">
    <form id="user_fm">
        <table id="user_tb" border="1" cellpadding="0" cellspacing="0">
            <tr>
                <td>用户ID：</td>
                <td><input type="text" id="userId" name="userId" readonly="readonly"></td>
            </tr>
            <tr>
                <td>用户名：</td>
                <td><input type="text" id="username" name="username"></td>
            </tr>
            <tr>
                <td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                <td><input type="password" id="password" name="password"></td>
            </tr>
            <tr>
                <td>手&nbsp;&nbsp;&nbsp;&nbsp;机：</td>
                <td><input type="text" id="phone" name="phone"></td>
            </tr>
            <tr>
                <td>邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</td>
                <td><input type="text" id="email" name="email"></td>
            </tr>
            <tr>
                <td>角&nbsp;&nbsp;&nbsp;&nbsp;色：</td>
                <td><span id="role"></span></td>
            </tr>
            <tr>
                <td>创建时间：</td>
                <td><span id="create_time"></span></td>
            </tr>
            <tr>
                <td>修改时间：</td>
                <td><span id="update_time"></span></td>
            </tr>
        </table>
        <button type="button" id="submit">修&nbsp;&nbsp;改</button>
    </form>

</div>


<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.serializejson.min.js"></script>
<script type="text/javascript">

    $(function () {
        $.ajax({
            url:"user/info.do",
            type:"post",
            contentType:"application/json",
            success:function (result) {
                if (result.code == 0){
                    var user = result.data
                    /*console.log(user)*/
                    $("#userId")[0].value = user.userId
                    $("#username")[0].value = user.username
                    $("#password")[0].value = user.password
                    $("#phone")[0].value = user.phone
                    $("#email")[0].value = user.email
                    $("#role").text(user.roleDesc)
                    $("#create_time").text(user.createTime)
                    $("#update_time").text(user.updateTime)

                    /*用户申请授权按钮*/
                    if (user.role == 2){
                        var role_td = $("#role").parent()[0]
                        var role_btn = document.createElement("input")
                        role_btn.setAttribute("type","button")
                        role_btn.setAttribute("id","role_btn")
                        role_btn.setAttribute("value","申请授权")
                        $(role_td)[0].appendChild(role_btn)
                    }

                    $("#submit").click(function () {
                        var fm = $("#user_fm").serializeJSON()
                        var username = $("#username").val().toString()
                        var password = $("#password").val().toString()
                        var phone = $("#phone").val().toString()
                        var email = $("#email").val().toString()

                        /*console.log(JSON.stringify(fm))*/
                        if (username.length >= 6 && username.length <= 12){
                            if (password.length >= 6 && password.length <= 12){
                                if(/^1[34578]\d{9}$/.test(phone)){
                                    if (/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/.test(email)){

                                        if (confirm("确认修改？")){
                                            $.ajax({
                                                type:"post",
                                                url:"user/update.do",
                                                contentType: "application/json",
                                                data:JSON.stringify(fm),
                                                success:function (result) {
                                                    alert(result.msg)
                                                }
                                            })
                                        }
                                    } else {
                                        alert("邮箱格式输入错误")
                                    }
                                }else {
                                    alert("手机号码有误，请重填")
                                }
                            } else {
                                alert("密码输入错误")
                            }
                        } else {
                            alert("用户名输入错误")
                        }
                    })

                    $("#role_btn").click(function () {
                        if (confirm("确认提交申请？")){
                            $.ajax({
                                url:"power/power_up.do",
                                type:"get",
                                contentType:"application/json",
                                success:function (result) {
                                    alert(result.msg)
                                }
                            })
                        }
                    })

                } else {
                    alert(result.msg)
                }
            }
        })
    })

</script>

</body>
</html>