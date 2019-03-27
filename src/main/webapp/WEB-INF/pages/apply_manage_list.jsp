<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>授权申请列表</title>

    <style type="text/css" rel="stylesheet">
        *{
            margin: 0;
            padding: 0;
        }

        table{
            width: 100%;
            text-align: center;
            box-sizing: border-box;
            border-color: aliceblue;
        }

        table tr{
            height: 40px;
        }
    </style>

</head>
<body>

<div id="con">
    <table border="1" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th>申请ID</th>
            <th>申请状态</th>
            <th>申请时间</th>
            <th>处理时间</th>
            <th>授&nbsp;&nbsp;权</th>
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

        $("body").on("click",".opt_true",function (e) {
            var applyId = $(e.target.attributes.getNamedItem("applyid"))[0].value

            var apply_status = $(this).parent().siblings(".apply_status")

            /*console.log(apply_status)*/

            if (confirm("确认授权？")){
                $.ajax({
                    url:"user_manage/make_power.do",
                    type:"get",
                    contentType:"application/json",
                    data:{"applyId":applyId},
                    success:function (result) {
                        alert(result.msg)
                        if (result.code == 0){
                            apply_status.text("已通过")
                        }
                    }
                })
            }
        })

        function init(){
            $.ajax({
                url:"power_manager/list.do",
                type:"get",
                contentType:"application/json",
                success:function (result) {
                    /*console.log(result)*/
                    if (result.code == 0){

                        var apply_list = result.data
                        apply_list_view(apply_list)
                    } else {
                        alert(result.msg)
                    }
                }
            })
        }

        function apply_list_view(apply_list) {
            var size = apply_list.length

            var tbody = $("#tbody")[0]

            for (var i = 0;i < size;i++){
                var tr = document.createElement("tr")
                var applyId = document.createElement("td")
                var apply_status = document.createElement("td")
                var create_time = document.createElement("td")
                var update_time = document.createElement("td")
                var option = document.createElement("td")

                apply_status.setAttribute("class","apply_status")

                applyId.append(apply_list[i].applyId)
                apply_status.append(apply_list[i].applyStatusDesc)
                create_time.append(apply_list[i].createTime)
                update_time.append(apply_list[i].updateTime)
                var opt_true = document.createElement("input")
                opt_true.setAttribute("type","button")
                opt_true.setAttribute("class","opt_true")
                opt_true.setAttribute("value","通过")
                opt_true.setAttribute("applyId",apply_list[i].applyId)
                option.appendChild(opt_true)

                tr.appendChild(applyId)
                tr.appendChild(apply_status)
                tr.appendChild(create_time)
                tr.appendChild(update_time)
                tr.appendChild(option)

                tbody.appendChild(tr)
            }
        }
    })

</script>


</body>
</html>