<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <title>CORS测试</title>
</head>

<body style="margin: 45px;">

<script type="text/javascript">
    $(function() {
        $.ajax({
            type: "GET",
            url: "http://192.168.124.8/test2",
            dataType: "text",
            success: function(data){
                alert(data);
            },
            error: function (xhr, textStatus) {
                alert("状态码："+xhr.status);
                alert("错误信息:"+xhr.statusText);
                alert("请求状态："+textStatus);
            }
        });
    })
</script>

</body>
</html>