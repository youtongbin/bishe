<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>专家论文列表</title>

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

        #read_tb_con{
            position: fixed;
            top: 0px;
            left: 0px;
            right: 0px;
            bottom: 0px;
            margin: auto;
            width: 50%;
            height: 412px;
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

        #readStatus{
            width: 30%;
            height: 2em;
            text-indent: 2em;
            letter-spacing: 2em;
        }

        textarea{
            width: 100%;
            height: 6em;
            font-size: 1.5em;
        }

        #button input{
            height: 30px;
            text-indent: 1em;
            letter-spacing: 1em;
        }

        .paper_status{
            cursor: pointer;
        }

        #read_list_view{
            position: fixed;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            margin: auto;
            width: 100%;
            height: 100%;
            background-color: white;

            display: none;
            text-align: center;
            overflow: auto;
        }

        #read_paper_name{
            display: inline-block;
            margin: 10px auto;
        }

        #reback{
            position: fixed;
            right: 10px;
            bottom: 10px;
            display: inline-block;
            height: 30px;
            width: 4em;
            text-indent: .5em;
            letter-spacing: .5em;
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

    <div id="read_tb_con">
        <h2>审批论文</h2>
        <form id="read_fm">
            <table border="1" cellspacing="0" cellpadding="0" id="update_tb">
                <input type="hidden" id="paperId" name="paperId">
                <input type="hidden" id="userId" name="userId">
                <tr>
                    <td>用户名：</td>
                    <td id="username_read_tb"></td>
                </tr>
                <tr>
                    <td>文件名：</td>
                    <td id="paper_name"></td>
                </tr>
                <tr>
                    <td>审批状态：</td>
                    <td id="paper_status"></td>
                </tr>
                <tr>
                    <td>是否通过：</td>
                    <td>
                        <select name="readStatus" id="readStatus">
                            <option value="0" selected="selected">是</option>
                            <option value="1">否</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>审批意见：</td>
                    <td><textarea id="readView" name="readView" maxlength="67" placeholder="请输入描述信息，字数不超过80"></textarea></td>
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

    <div id="read_list_view">
        <h2 id="read_paper_name">论文名</h2>
        <table id="read_list_tb" border="1" cellspacing="0" cellpadding="0">
            <thead>
                <tr>
                    <th>评审专家</th>
                    <th>评审状态</th>
                    <th>审批时间</th>
                    <th>修改时间</th>
                    <th>审批意见</th>
                </tr>
            </thead>
            <tbody id="read_list_tbody">

            </tbody>
        </table>

        <button id="reback">返回</button>
    </div>

</div>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.serializejson.min.js"></script>
<script type="text/javascript">
    $(function () {

        init()

        $("body").on("click",".read_btn",function (e) {
            var paperId = $(e)[0].target.attributes.getNamedItem("paperid").value
            /*console.log(paperId)*/
            $.ajax({
                url:"paper/one.do",
                type:"get",
                contentType:"application/json",
                data:{"paperId":paperId},
                success:function (result) {
                    if (result.code == 0){
                        var paper = result.data
                        $("#read_tb_con").css("display","block")
                        read_table_view(paper)
                    }else {
                        alert(result.msg)
                    }
                }
            })
        })

        $("body").on("click","#cancel",function () {
            $("#read_tb_con").css("display","none")
        })

        $("body").on("click","#submit",function () {
            if (confirm("确定审批？")){
                var read_fm = $("#read_fm").serializeJSON()
                $.ajax({
                    url:"read_judge/insert.do",
                    type:"post",
                    contentType:"application/json",
                    data:JSON.stringify(read_fm),
                    success:function (result) {
                        alert(result.msg)
                        if (result.code == 0){
                            $("#paperId")[0].value=""
                            $("#userId")[0].value=""
                            $("#readView")[0].value=""
                            $("#read_tb_con").css("display","none")
                            var paper_list = result.data
                            paper_list_view(paper_list)
                        }
                    }
                })
            }
        })

        $("body").on("click",".paper_status",function (e) {
            var paperId = $(e)[0].target.attributes.getNamedItem("paperid").value
            /*console.log(paperId)*/
            $.ajax({
                url:"read_judge/list.do",
                type:"get",
                contentType:"application/json",
                data:{"paperId":paperId},
                success:function (result) {
                    if (result.code == 0){
                        var read_list = result.data
                        $("#read_list_view").css("display","block")
                        read_list_view(read_list)
                    } else {
                        alert(result.msg)
                    }
                }
            })
        })

        $("body").on("click","#reback",function () {
            $("#read_list_view").css("display","none")
        })

        function init(){
            $.ajax({
                url:"paper_judge/list.do",
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

        function read_list_view(read_list) {
            /*console.log(read_list)*/
            var table = $("#read_list_tb")[0]
            table.removeChild($("#read_list_tbody")[0])
            var tbody = document.createElement("tbody")
            tbody.setAttribute("id","read_list_tbody")
            table.appendChild(tbody)


            var size = read_list.length
            for (var i = 0;i < size;i++){
                var tr = document.createElement("tr")
                var judge = document.createElement("td")
                var read_status = document.createElement("td")
                var create_time = document.createElement("td")
                var update_time = document.createElement("td")
                var read_view = document.createElement("td")

                $("#read_paper_name").text(read_list[i].paperName)
                judge.append(read_list[i].username)
                tr.appendChild(judge)

                read_status.append(read_list[i].readStatusDesc)
                if (read_list[i].readStatus == 0){
                    read_status.setAttribute("style","color:#0ed995")
                }
                if (read_list[i].readStatus == 1){
                    read_status.setAttribute("style","color:red")
                }
                tr.appendChild(read_status)

                create_time.append(read_list[i].createTime)
                tr.appendChild(create_time)

                update_time.append(read_list[i].updateTime)
                tr.appendChild(update_time)

                var button = document.createElement("button")
                button.innerText="查看"


                read_view.appendChild(button)
                tr.appendChild(read_view)

                tbody.appendChild(tr)

            }
        }

        function read_table_view(paper) {
            $("#paperId")[0].value=paper.paperId
            $("#userId")[0].value=paper.userId
            $("#username_read_tb").text(paper.username)
            $("#paper_name").text(paper.paperName)
            $("#paper_status").text(paper.paperStatusDesc)

            $("#read_tb_con").css("display","block")
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
                tr.appendChild(username)

                paper_name.append(paper_list[i].paperName)
                tr.appendChild(paper_name)

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
                tr.appendChild(paper_status)

                create_time.append(paper_list[i].createTime)
                tr.appendChild(create_time)

                update_time.append(paper_list[i].updateTime)
                tr.appendChild(update_time)

                var download_btn = document.createElement("input")
                download_btn.setAttribute("type","button")
                download_btn.setAttribute("class","download_btn")
                download_btn.setAttribute("value","下载")
                download_btn.setAttribute("paperId",paper_list[i].paperId)
                download_btn.setAttribute("url",paper_list[i].paperUrl)
                var a = document.createElement("a")
                a.setAttribute("href",paper_list[i].paperUrl)
                a.appendChild(download_btn)
                var read_btn = document.createElement("input")
                read_btn.setAttribute("type","button")
                read_btn.setAttribute("class","read_btn")
                read_btn.setAttribute("value","审批")
                read_btn.setAttribute("paperId",paper_list[i].paperId)
                opt.appendChild(a)
                opt.innerHTML+="&emsp;"
                opt.appendChild(read_btn)
                tr.appendChild(opt)

                $("#tbody")[0].appendChild(tr)
            }
        }

    })
</script>


</body>
</html>