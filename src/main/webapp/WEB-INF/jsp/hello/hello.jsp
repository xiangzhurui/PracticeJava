<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello World!</title>
<spring:url value="/resources/bootstrap/3.3.7/css/bootstrap.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
</head>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Project Name</a>
    </div>
  </div>
</nav>
<div class="jumbotron">
  <div class="container">
    <h1>${title}</h1>
    <p>
      <c:if test="${not empty msg}">
      Hello ${msg}
    </c:if>
      <c:if test="${empty msg}">
      Welcome Welcome!
    </c:if>
    </p>
    <p>
      <a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
    </p>
  </div>
</div>
<div class="container">
  <div class="row">
    <div class="col-md-4">
      <h2>Heading</h2>
      <p>ABC</p>
      <p>
        <a class="btn btn-default" href="#" role="button">View details</a>
      </p>
    </div>
    <div class="col-md-4">
      <h2>Heading</h2>
      <p>ABC</p>
      <p>
        <a class="btn btn-default" href="#" role="button">View details</a>
      </p>
    </div>
    <div class="col-md-4">
      <h2>Heading</h2>
      <p>ABC</p>
      <p>
        <a class="btn btn-default" href="#" role="button">View details</a>
      </p>
    </div>
  </div>
  <hr>
  <footer>
    <p>© xzr 2016</p>
  </footer>
</div>
<spring:url value="/resources/bootstrap/3.3.7/js/bootstrap.js" var="bootstrapJs" />
<spring:url value="/resources/jquery/3.1.0/jquery-3.1.0.js" var="jQueryJs"/>
<script src="${coreJs}"></script>
<script src="${jQueryJs}"></script>
</body>
</html>