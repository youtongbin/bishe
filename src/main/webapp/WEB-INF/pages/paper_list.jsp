<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>个人论文列表</title>

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
            width: 20%;
        }

        #con > table tbody tr:nth-child(odd){
            background-color: beige;
        }

        #update_tb_con{
            position: fixed;
            top: 0px;
            left: 0px;
            right: 0px;
            bottom: 0px;
            margin: auto;
            width: 50%;
            height: 400px;
            padding-top: 10px;
            text-align: center;
            background-color: white;
            border: 1px solid #DDDDDD;
            border-radius: 5px;
            box-shadow: 0px 0px 10px 10px rgba(0,0,0,0.5) ;
            display: none;
        }

        #update_tb{
            margin-top: 10px;
        }

        textarea{
            width: 100%;
            height: 10em;
        }

        #button input{
            height: 30px;
            text-indent: 1em;
            letter-spacing: 1em;
        }

    </style>

</head>
<body>

<div id="con">
    <table border="1" cellpadding="0" cellspacing="0" id="tb">
        <thead>
            <tr>
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

    <div id="update_tb_con">
        <h2>修改论文</h2>
        <form id="update_fm" enctype="multipart/form-data">
            <table border="1" cellspacing="0" cellpadding="0" id="update_tb">
                <input type="hidden" id="paperId" name="paperId">
                <tr>
                    <td>文件名：</td>
                    <td id="paper_name"></td>
                </tr>
                <tr>
                    <td>审核状态：</td>
                    <td id="paper_status"></td>
                </tr>
                <tr>
                    <td>重新上传：</td>
                    <td>
                        <input type="file" id="file_upload" name="file_upload" />
                        <br/>
                    </td>
                </tr>
                <tr>
                    <td>文件描述：</td>
                    <td><textarea id="paper_desc" name="paper_desc" maxlength="67" placeholder="请输入描述信息，字数不超过80"></textarea></td>
                </tr>
                <tr>
                    <td id="button" colspan="2">
                        <input type="reset" id="reset" value="重置"/>&emsp;&emsp;
                        <input type="button" id="submit" value="提交"/> &emsp;
                        <input type="button" id="cancel" value="取消">
                    </td>
                </tr>
            </table>
        </form>
    </div>

</div>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.serializejson.min.js"></script>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url:"paper/list.do",
            type:"get",
            contentType:"application/json",
            success:function (result) {
                if (result.code == 0){
                    var paper_list = result.data
                    paper_list_view(paper_list)

                    $(".update_btn").click(function (e) {
                        var paperId = $(e)[0].target.attributes.getNamedItem("paperid").value
                        $.ajax({
                            url:"paper/one.do",
                            type:"get",
                            contentType:"application/json",
                            data:{"paperId":paperId},
                            success:function (result) {
                                if (result.code == 0){
                                    var paper = result.data
                                    update_table_view(paper)
                                }else {
                                    alert(result.msg)
                                }
                            }
                        })
                    })

                    $("#cancel").click(function () {
                        $("#update_tb_con").css("display","none")
                    })

                    $("#submit").click(function () {
                        if (confirm("确定修改？")){
                            var fd = new FormData($("#update_fm")[0])
                            $.ajax({
                                url:"paper/update.do",
                                type:"post",
                                contentType:false,
                                cache:false,
                                processData:false,
                                data:fd,
                                success:function (result) {
                                    alert(result.msg)
                                    if (result.code == 0){
                                        $("#update_tb_con").css("display","none")
                                        var paper_list = result.data
                                        paper_list_view(paper_list)
                                    }
                                }
                            })
                        }
                    })

                    $(".delete_btn").click(function (e) {
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

                } else {
                    alert(result.msg)
                }
            }
        })

        function update_table_view(paper) {
            $("#paperId")[0].value=paper.paperId
            $("#paper_name").text(paper.paperName)
            $("#paper_status").text(paper.paperStatusDesc)
            $("#paper_desc")[0].value=paper.paperDesc

            $("#update_tb_con").css("display","block")
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
                var paper_name = document.createElement("td")
                var paper_status = document.createElement("td")
                var create_time = document.createElement("td")
                var update_time = document.createElement("td")
                var opt = document.createElement("td")

                paper_name.append(paper_list[i].paperName)
                paper_status.append(paper_list[i].paperStatusDesc)
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
                var update_btn = document.createElement("input")
                update_btn.setAttribute("type","button")
                update_btn.setAttribute("class","update_btn")
                update_btn.setAttribute("value","修改")
                update_btn.setAttribute("paperId",paper_list[i].paperId)
                opt.appendChild(a)
                opt.innerHTML+="&emsp;"
                opt.appendChild(delete_btn)
                opt.innerHTML+="&emsp;"
                opt.appendChild(update_btn)

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