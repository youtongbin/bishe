<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>个人申请列表</title>

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
        $.ajax({
            url:"power/power_up_list.do",
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

        function apply_list_view(apply_list) {
            var size = apply_list.length

            var tbody = $("#tbody")[0]

            for (var i = 0;i < size;i++){
                var tr = document.createElement("tr")
                var applyId = document.createElement("td")
                var apply_status = document.createElement("td")
                var create_time = document.createElement("td")
                var update_time = document.createElement("td")

                applyId.append(apply_list[i].applyId)
                apply_status.append(apply_list[i].applyStatusDesc)
                create_time.append(apply_list[i].createTime)
                update_time.append(apply_list[i].updateTime)

                tr.appendChild(applyId)
                tr.appendChild(apply_status)
                tr.appendChild(create_time)
                tr.appendChild(update_time)

                tbody.appendChild(tr)
            }
        }
    })

</script>
</body>
</html>