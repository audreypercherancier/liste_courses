<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listes de Courses</title>
</head>
<jsp:include page="/WEB-INF/fragments/head.jsp" >
		<jsp:param value="" name=""/>
</jsp:include>
<body>
<header>
<%@include file="./fragments/header.html" %>
</header>
 
 
<div class="col-12">
   <h2 class="my-5 text-center">Listes prédéfinies</h2>
	
		<c:if test="${!empty listeCodesErreur}">
			<div class="alert alert-danger" role="alert">
			  <strong>Erreur!</strong>
			  <ul>
			  	<c:forEach var="code" items="${listeCodesErreur}">
			  		<li>${LecteurMessage.getMessageErreur(code)}</li>
			  	</c:forEach>
			  </ul>
			</div>
		</c:if>

    <div class="row">
    <c:choose>
	    <c:when test="${listesCourse.size()>0}">
        <ul class="list-group col-12">
       		<c:forEach var="c" items="${listesCourse}">
        	
            <li class="list-group-item d-flex justify-content-between align-items-center">Liste bio
                <div>
                    <a href="${pageContext.request.contextPath}/Panier?id=${c.id}"  class="badge" title="selectionnerListe"><i class="material-icons">shopping_cart</i></a>
                    <a href="${pageContext.request.contextPath}/supprimer?id=${c.id}" class="badge text-danger" title="supprimer"><i class="material-icons">delete</i></a>
                </div>

            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center">Liste grande surface
                <div>
                    <a href="${pageContext.request.contextPath}/Panier?id=${c.id}" class="badge" title="selectionnerListe"><i class="material-icons">shopping_cart</i></a>
                </div>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center">Liste surgelÃ©s
                <div>
                    <a href="${pageContext.request.contextPath}/Panier?id=${c.id} class="badge" title="selectionnerListe"><i class="material-icons">shopping_cart</i></a>
                    <a href="${pageContext.request.contextPath}/supprimer?id=${c.id} class="badge text-danger" title="supprimer"><i class="material-icons">delete</i></a>
                </div>
            </li>
        </c:forEach>
	 </ul>
</c:when>

<c:otherwise>
<p>Pas de liste actuellement.<p>
 </c:otherwise>
 </c:choose>
    </div>
  </div>

    <!-- Footer -->
    <footer class="row bg-dark footer-demodule fixed-bottom py-1">
            <div class="col-lg-4 offset-lg-4 text-center">
                <a class="btn" href="${pageContext.request.contextPath}/Nouvelle.jsp" title="Creer une nouvelle liste"><i class="material-icons">add</i></a>
            </div>
            <%@include file="./fragments/footer.html" %>
        <!-- /.container -->
    </footer>
</body>
</html>