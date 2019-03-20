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
    </style>
</head>
<body>

<div class="con">
    <div class="head">
        <h1>Welcome：<span id="head-username"></span></h1>

    </div>

    <div class="main">
        <div class="left" id="left-menu">
            <%--<dl>
                <dt>用户中心</dt>
                <dd>个人中心</dd>
                <dd>用户管理</dd>
                <dd>添加用户</dd>
                <dd>授权管理</dd>
            </dl>
            <dl>
                <dt>关于我们</dt>
                <dd>用户反馈</dd>
            </dl>--%>
        </div>

        <div class="right">
            <div class="right_item">
                <div class="right_title">
                    <span id="right_title">个人中心</span>
                </div>
                <div class="right_con" id="right_con">
                    <iframe id="right_request" src="">

                    </iframe>
                </div>
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
        $.ajax({
            url:"function/home.do",
            type:"post",
            contentType:"application/json",
            success:function (result) {
                console.log(result)
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
                        dd.append(newNodeName)
                        dl.appendChild(dd)
                    }

                    document.getElementById("left-menu").appendChild(dl)
                }

                $("dl dt").click(function(){
                    $(this).siblings().toggle().parent().siblings().children("dd").hide();
                });

                $(".function").click(function (e) {
                    var url_info = $(e.target.attributes.getNamedItem("url"))
                    console.log(url_info[0].value)

                    $("#right_title").text()

                    var right_con = document.getElementById("right_con")
                    right_con.removeChild(document.getElementById("right_request"))

                    var iframe = document.createElement("iframe")
                    iframe.setAttribute("src",url_info[0].value)
                    iframe.setAttribute("id","right_request")

                    right_con.appendChild(iframe)

                })
            }
        })
    })
</script>
</body>
</html>