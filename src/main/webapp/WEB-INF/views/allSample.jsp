<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Include the above line to enable jstl support -->


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>All Models</title>

<link href="<c:url value='/static/bootstrap.css' />" rel="stylesheet"></link>

<!-- This is the way to include static files like js, css ,image -->

</head>
    <body>
    <table>
            <tr>
                            <td>First NAME</td><td>Last Name/td><td>DOB</td><td>EMAIL</td><td></td>
                        </tr>
                        <c:forEach items="${allSampleList}" var="allSample"> <!-- iterate over the list-->
                           <tr>
            			<td>${allSample.name}</td>
            			<td><select multiple="true" itemValue="id" itemLabel="name">
            				<c:forEach items="${allSample.manyUnidirectionals}" var="sample">
            					<option>${sample.name}</option>
            				</c:forEach>
            			    </select>
            			</td>
            	       </tr>
                        </c:forEach>
    </table>



        <br/>
        <a href="<c:url value='/new' />">Add New Employee</a> <!-- go to "new" page -->

    </body>
</html>






