<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

   String uname= (String) session.getAttribute("username");
       if (uname==null)
       {
         response.sendRedirect("login.jsp");
       }
    %>
    <h2>Welcome to the site!</h2>
    <%= uname %>
    <a href="videos.jsp">Videos here</a>
    <form action= "Logout">
       <input type= "submit" value= "Logout">
    </form>
</body>
</html>