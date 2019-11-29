<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<body>
<h1>All products list:</h1>
<table>
    <thead>
    <tr>
        <td>ID</td>
        <td>First Name</td>
        <td>Last Name</td>
    </tr>
    </thead>
    <tbody>
    <jstl:forEach var="s" items="${products}">
    <tr>
        <td>${s.id}</td>
        <td>${s.title}</td>
        <td>${s.price}</td>
    </tr>
    </jstl:forEach>
    </tbody>
</table>
</body>
</html>