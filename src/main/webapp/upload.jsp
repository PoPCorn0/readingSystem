<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--
  ~ @copyright ：神农大学生软件创新中心 版权所有 © 2019
  ~
  ~ @author 16级信息与计算科学潘鹏程
  ~
  ~ @version
  ~
  ~ @date 2019.05.21
  ~
  ~ @Description
  --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文件上传</title>
</head>
<body>
<h2>文件上传</h2>
<form enctype="multipart/form-data" action="common/upload.do?id=task1&mark=1" method="post">
    <table>
        <tr>
            <td>请选择文件:</td>
            <td><input type="file" name="file"></td>
        </tr>
        <tr>
            <td><input type="submit" value="上传"></td>
        </tr>
    </table>
</form>
</body>
</html>