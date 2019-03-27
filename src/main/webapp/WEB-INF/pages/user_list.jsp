<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>用户列表</title>

    <style type="text/css" rel="stylesheet">
        *{
            margin: 0;
            padding: 0;
        }
        table{
            text-align: center;
            width: 100%;
            box-sizing: border-box;
            border-color: aliceblue;
        }
        table tr{
            height: 50px;
        }
        table tr td{
            padding: 10px;
        }
        table tbody tr:nth-child(odd){
            background-color: #DDDDDD;
        }

    </style>

</head>
<body>

<div id="con">
    <table border="1" cellspacing="0" cellpadding="0">
        <thead>
            <tr>
                <th>用户ID</th>
                <th>用户名</th>
                <th>密码</th>
                <th>手机</th>
                <th>邮箱</th>
                <th>角色</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody id="tbody">
        </tbody>
    </table>
</div>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.serializejson.min.js"></script>
<script type="text/javascript">

    $(function () {

        init()

        $("body").on("click",".delete_btn",function (e) {
            if (confirm("确定删除？")){
                var userId = $($(e)[0].target)[0].attributes.getNamedItem("userid").value
                var tr = $(this).parent().parent()

                $.ajax({
                    url:"user_manage/delete_user.do",
                    type:"get",
                    contentType:"application/json",
                    data:{"userId":userId},
                    success:function (result) {
                        /*console.log(result)*/
                        alert(result.msg)
                        if (result.code == 0){
                            tr.remove()
                        }
                    }
                })
            }
        })

        function init() {
            $.ajax({
                url:"user_manage/list.do",
                type:"get",
                contentType:"application/json",
                success:function (result) {
                    if (result.code == 0){
                        var user_list = result.data
                        /*console.log(user_list)*/
                        user_list_view(user_list)
                    } else {
                        alert(result.msg)
                    }
                }
            })
        }

        function user_list_view(user_list) {
            var size = user_list.length

            var tbody = $("#tbody")[0]

            for (var i = 0;i < size;i++){
                var tr = document.createElement("tr")
                var userId = document.createElement("td")
                var username = document.createElement("td")
                var password = document.createElement("td")
                var phone = document.createElement("td")
                var email = document.createElement("td")
                var role = document.createElement("td")
                var option = document.createElement("td")
                var op_btn = document.createElement("input")
                op_btn.setAttribute("type","button")
                op_btn.setAttribute("userId",user_list[i].userId)
                op_btn.setAttribute("value","删除")
                op_btn.setAttribute("class","delete_btn")
                userId.append(user_list[i].userId)
                username.append(user_list[i].username)
                password.append(user_list[i].password)
                phone.append(user_list[i].phone)
                email.append(user_list[i].email)
                role.append(user_list[i].roleDesc)
                option.append(op_btn)
                tr.appendChild(userId)
                tr.appendChild(username)
                tr.appendChild(password)
                tr.appendChild(phone)
                tr.appendChild(email)
                tr.appendChild(role)
                tr.appendChild(option)
                tbody.appendChild(tr)
            }
        }
    })

</script>

</body>
</html>