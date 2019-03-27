<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>管理员论文列表</title>

    <style type="text/css" rel="stylesheet">
        *{
            margin: 0;
            padding: 0;
        }

        #con{
            position: relative;
        }

        table{
            width: 100%;
            text-align: center;
            box-sizing: border-box;
            border-color: aliceblue;
        }

        table tr{
            height: 50px;
        }

        #con > table tr th{
            width: 16.7%;
        }

        #con > table tbody tr:nth-child(odd){
            background-color: beige;
        }

    </style>

</head>
<body>

<div id="con">
    <table border="1" cellpadding="0" cellspacing="0" id="tb">
        <thead>
        <tr>
            <th>用户名</th>
            <th>文件名</th>
            <th>审核状态</th>
            <th>上传时间</th>
            <th>修改时间</th>
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
                var paperId = $(e)[0].target.attributes.getNamedItem("paperid").value
                var userId = $(e)[0].target.attributes.getNamedItem("userid").value
                var tr = $(this).parent().parent().parent()
                $.ajax({
                    url:"paper/delete.do",
                    type:"get",
                    contentType:"application/json",
                    data:{"paperId":paperId,"userId":userId},
                    success:function (result) {
                        console.log(result)
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
                url:"paper_manage/list.do",
                type:"get",
                contentType:"application/json",
                success:function (result) {
                    if (result.code == 0){
                        var paper_list = result.data
                        paper_list_view(paper_list)
                    } else {
                        alert(result.msg)
                    }
                }
            })
        }

        function paper_list_view(paper_list) {
            var table = $("#tb")[0]
            table.removeChild($("#tbody")[0])

            var tbody = document.createElement("tbody")
            tbody.setAttribute("id","tbody")
            table.appendChild(tbody)
            /*console.log(paper_list)*/
            var size = paper_list.length
            for (var i = 0;i < size;i++){
                var tr = document.createElement("tr")
                var username = document.createElement("td")
                var paper_name = document.createElement("td")
                var paper_status = document.createElement("td")
                var create_time = document.createElement("td")
                var update_time = document.createElement("td")
                var opt = document.createElement("td")

                username.append(paper_list[i].username)
                username.setAttribute("class","username")
                username.setAttribute("value",paper_list[i].username)
                paper_name.append(paper_list[i].paperName)
                paper_status.append(paper_list[i].paperStatusDesc)
                if (paper_list[i].paperStatus != 2){
                    paper_status.setAttribute("class","paper_status")
                    paper_status.setAttribute("paperId",paper_list[i].paperId)
                }
                if (paper_list[i].paperStatus == 0){
                    paper_status.setAttribute("style","color:#0ed995")
                }
                if (paper_list[i].paperStatus == 1){
                    paper_status.setAttribute("style","color:red")
                }
                var a = document.createElement("a")
                a.setAttribute("href",paper_list[i].paperUrl)
                paper_name.appendChild(a)
                create_time.append(paper_list[i].createTime)
                update_time.append(paper_list[i].updateTime)
                var download_btn = document.createElement("input")
                download_btn.setAttribute("type","button")
                download_btn.setAttribute("class","download_btn")
                download_btn.setAttribute("value","下载")
                download_btn.setAttribute("paperId",paper_list[i].paperId)
                download_btn.setAttribute("url",paper_list[i].paperUrl)
                var a = document.createElement("a")
                a.setAttribute("href",paper_list[i].paperUrl)
                a.appendChild(download_btn)
                var delete_btn = document.createElement("input")
                delete_btn.setAttribute("type","button")
                delete_btn.setAttribute("class","delete_btn")
                delete_btn.setAttribute("value","删除")
                delete_btn.setAttribute("paperId",paper_list[i].paperId)
                delete_btn.setAttribute("userId",paper_list[i].userId)
                opt.appendChild(a)
                opt.innerHTML+="&emsp;"
                opt.appendChild(delete_btn)

                tr.appendChild(username)
                tr.appendChild(paper_name)
                tr.appendChild(paper_status)
                tr.appendChild(create_time)
                tr.appendChild(update_time)
                tr.appendChild(opt)

                $("#tbody")[0].appendChild(tr)
            }
        }

    })
</script>

</body>
</html>