<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet"></link>

    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>

    <div class="generic-container">
    <div class="well lead">User Registration Form</div>
    <form:form method="POST" modelAttribute="sampleModel" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="name">Name</label>
                <div class="col-md-7">
                    <form:input type="text" path="name" id="name" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="name" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <%-- <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="oneToOne.name">Home Address/label>
                <div class="col-md-7">
                    <form:input type="text" path="oneToOne.name" id="homeAddress" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="oneToOne.name" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div> --%>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="oneToOneBidirectional.name">PET NAME</label>
                <div class="col-md-7">
                    <c:choose>
                        <c:when test="${edit}">
                            <form:input type="text" path="oneToOneBidirectional.name" id="ssoId" class="form-control

input-sm" disabled="true"/>
                        </c:when>
                        <c:otherwise>
                            <form:input type="text" path="oneToOneBidirectional.name" id="ssoId" class="form-control

input-sm" />
                            <div class="has-error">
                                <form:errors path="oneToOneBidirectional.name" class="help-inline"/>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="oneToOneForiegnKey.name">Home Address</label>
                <div class="col-md-7">
                    <form:input path="oneToOneForiegnKey.name" id="password" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="oneToOneForiegnKey.name" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="manyUnidirectionals">Roles</label>
                <div class="col-md-7">
                    <form:select path="manyUnidirectionals" items="${techSkills}" multiple="true" itemValue="id"

itemLabel="name" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="manyUnidirectionals" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a

href="<c:url value='/list' />">Cancel</a>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a

href="<c:url value='/list' />">Cancel</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    </div>
</body>
</html>