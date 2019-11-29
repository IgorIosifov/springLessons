<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <body>
        <h1>Student Form Result</h1>
        <br>
        First name: ${product.title}
        <br>
        Last name: ${product.price}
        <br>
        Country: ${product.country}
        <br>
        Programming Languages:
        <br>
        <ul>
            <jstl:forEach var="item" items="${product.programmingLanguages}">
                <li>${item}</li>
            </jstl:forEach>
        </ul>
    </body>
</html>