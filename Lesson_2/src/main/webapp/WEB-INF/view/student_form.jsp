<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
    <body>
        <form:form action="process_form" modelAttribute="product">
            First Name: <form:input path="title" />
            <br>
            Last Name: <form:input path="price" />
            <br>
            Country:
            <form:select path="country">
                <form:option value="Russia" label="Russia" />
                <form:option value="France" label="France" />
            </form:select>
            <br>
            Programming Languages:
            <br>
            Java <form:checkbox path="programmingLanguages" value="Java" />
            C++ <form:checkbox path="programmingLanguages" value="C++" />
            php <form:checkbox path="programmingLanguages" value="php" />
            <br>
            <input type="submit" value="Submit" />
        </form:form>
    </body>
</html>
