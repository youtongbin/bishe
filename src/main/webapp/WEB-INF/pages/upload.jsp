<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="http://localhost:8080/bishe/paper/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="file_upload" />
    <input type="submit" value="文件上传" />
</form>

</body>
</html>