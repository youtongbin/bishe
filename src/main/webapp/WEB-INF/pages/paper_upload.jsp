<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>在线投稿</title>
    <style type="text/css" rel="stylesheet">
        *{
            margin: 0;
            padding: 0;
        }

        #fm{
            text-align: center;
        }

        fieldset{
            width: 75%;
            padding: 20px;
            margin: 20px auto;
        }

        legend{
            text-align: left;
            font-size: 2em;
        }

        table{
            text-align: center;
            margin: 0px auto;
            box-sizing: border-box;
            border-color: aliceblue;
        }

        table tr td{
            padding: 10px;
        }

        textarea{
            width: 100%;
            height: 8em;
            font-size: 1.5em;
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

    <form id="fm" enctype="multipart/form-data">

        <fieldset>
            <legend>论文上传</legend>

            <table border="1" cellspacing="0" cellpadding="0">

                <tr>
                    <td>选择文件：</td>
                    <td>
                        <input type="file" id="file_upload" name="file_upload" />
                        <span style="color: red;">(doc、docx &nbsp;格式)</span>
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
                        <input type="button" id="submit" value="提交"/>
                    </td>
                </tr>
            </table>

        </fieldset>

    </form>
    
</div>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.serializejson.min.js"></script>
<script type="text/javascript">
    $(function () {

        init()

        $("body").on("click","#submit",function () {
            var fd = new FormData($("#fm")[0])

            if (fd.get("file_upload").name == ""){
                alert("请选择要上传的文件")
            }else {
                if (confirm("确认提交？")){
                    $.ajax({
                        url:"paper/upload.do",
                        type:"post",
                        contentType:false,
                        cache:false,
                        processData:false,
                        data:fd,
                        success:function (result) {
                            alert(result.msg)
                            if (result.code == 0){
                                $("#file_upload")[0].value=""
                                $("#paper_desc")[0].value=""
                            }
                        }
                    })
                }
            }
        })

        function init() {

        }

    })
</script>

</body>
</html>