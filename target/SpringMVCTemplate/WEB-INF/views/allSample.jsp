<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Include the above line to enable jstl support -->


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>All Models</title>

<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet"></link>

<!-- This is the way to include static files like js, css ,image -->

</head>
<div class="generic-container">
            <div class="panel panel-default">
                  <!-- Default panel contents -->
                <div class="panel-heading"><span class="lead">All Data</span></div>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Home Address</th>
                            <th>PET Name</th>
                            <th>Office Address</th>
                            <th>Roles</th>
                            <%-- <th width="100"></th>
                            <th width="100"></th>  --%>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${allSampleList}" var="sample">
                        <tr>
                            <td>${sample.name}</td>
                            <td>${sample.oneToOne.name}</td>
                            <td>${sample.oneToOneBidirectional.name}</td>
                            <td>${sample.oneToOneForiegnKey.name}</td>

                            <td>
                            <ul>
                            <c:forEach items="${sample.manyUnidirectionals}" var="s">
                            <li>${s.name}</li>
                            </c:forEach>
                            </ul>
                            </td>

                            <td><a href="<c:url value='/edit-user-${sample.id}' />" class="btn btn-success

    custom-width">edit</a></td>
                            <td><a href="<c:url value='/delete-user-${sample.id}' />" class="btn btn-danger

    custom-width">delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="well">
                            <a href="<c:url value='/new' />">Add New Data</a>
                </div>
            </div>



</html>






