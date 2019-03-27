<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主页</title>

    <link rel="stylesheet" type="text/css" href="css/home.css">

    <style type="text/css" rel="stylesheet">

        #right_request{
            width: 100%;
            height: 100%
        }
        .head > div{
            float: left;
        }
        #logout_btn{
            display: inline-block;
            height: 30px;
            margin-top: 25px;
            margin-left: 100px;
        }
    </style>
</head>
<body>

<div class="con">
    <div class="head">
        <div>
            <h1>Welcome：<span id="head-username"></span></h1>
        </div>
        <div>
            <button id="logout_btn">退出登录</button>
        </div>
    </div>

    <div class="main">
        <div class="left" id="left-menu">
            <%--<dl>
                <dt></dt>
                <dd></dd>
            </dl>--%>
        </div>

        <div class="right">
            <div class="right_title" id="right_title">
                <span id="right_title_">欢迎你~~</span>
            </div>
            <div class="right_con" id="right_con">
                <iframe id="right_request" src="welcome.do">

                </iframe>
            </div>
        </div>
    </div>

    <div class="foot">

    </div>


</div>


<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.serializejson.min.js"></script>
<script type="text/javascript">

    $(function () {

        init()

        $("body").on("click","dl dt",function(){
            $(this).siblings().toggle().parent().siblings().children("dd").hide();
        })

        $("body").on("click",".function",function (e) {
            var url_info = $(e.target.attributes.getNamedItem("url"))

            $("#right_title_").text(($(e.target.attributes.getNamedItem("function_name")))[0].value)

            var right_con = document.getElementById("right_con")
            right_con.removeChild(document.getElementById("right_request"))

            var iframe = document.createElement("iframe")
            iframe.setAttribute("src",url_info[0].value)
            iframe.setAttribute("id","right_request")

            right_con.appendChild(iframe)
        })

        $("body").on("click","#logout_btn",function () {
            $.ajax({
                url:"user/logout.do",
                type:"post",
                contentType:"application/json",
                success:function (result) {
                    if (result.code == 0){
                        window.location.href = "login.do"
                    } else {
                        alert(result.msg)
                    }
                }
            })
        })

        function init(){
            $.ajax({
                url:"function/home.do",
                type:"post",
                contentType:"application/json",
                success:function (result) {
                    /*console.log(result)*/
                    if (result.code == 0){
                        home_view(result)
                    } else {
                        alert(result.msg)
                    }
                }
            })
        }

        function home_view(result) {
            var user = result.data.user
            $("#head-username").text(user.username)

            var functionVOList = result.data.functionVOList
            var size1 = functionVOList.length
            for (var i = 0;i < size1;i++){
                var newNodeName = functionVOList[i].functionName

                var dl = document.createElement("dl")
                var dt = document.createElement("dt")

                var functionVOListChild = functionVOList[i].functionVOList
                var size2 = functionVOListChild.length

                dt.append(newNodeName)
                dl.appendChild(dt)

                for (var j = 0;j < size2;j++){
                    var functionVOChild = functionVOListChild[j]
                    newNodeName = functionVOChild.functionName
                    var dd = document.createElement("dd")

                    dd.setAttribute("value",functionVOChild.functionId)
                    dd.setAttribute("class","function")
                    dd.setAttribute("url",functionVOChild.url)
                    dd.setAttribute("function_name",functionVOChild.functionName)
                    dd.append(newNodeName)
                    dl.appendChild(dd)
                }

                document.getElementById("left-menu").appendChild(dl)
            }
        }
    })
</script>
</body>
</html>