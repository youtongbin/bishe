<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>添加用户</title>

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
            /*border: transparent;*/
        }

        #role{
            height: 30px;
            width: 163px;
        }

        #submit{
            display: inline-block;
            width: 80px;
            height: 30px;
            margin-top: 30px;
        }

    </style>


</head>
<body>

<div id="con">
    <form id="user_fm">
        <table id="user_tb" border="1" cellpadding="0" cellspacing="0">

            <tr>
                <td>用户名：</td>
                <td><input type="text" id="username" name="username"></td>
            </tr>
            <tr>
                <td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                <td><input type="text" id="password" name="password"></td>
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
                <td>
                    <select name="role" id="role">
                        <option value="2" selected="selected">普通用户</option>
                        <option value="1">专家老师</option>
                    </select>
                </td>
            </tr>

        </table>
        <button type="button" id="submit">添&nbsp;&nbsp;加</button>
    </form>

</div>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.serializejson.min.js"></script>
<script type="text/javascript">

    $(function () {

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

                            $.ajax({
                                type:"post",
                                url:"user_manage/add_user.do",
                                contentType: "application/json",
                                data:JSON.stringify(fm),
                                success:function (result) {
                                    alert(result.msg)
                                }
                            })
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

    })

</script>

</body>
</html>