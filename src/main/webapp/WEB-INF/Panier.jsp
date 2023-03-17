<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
<%@include file="/WEB-INF/fragments/header.html"%>
</header>
 <jsp:include page="/WEB-INF/fragments/head.jsp">
		 <jsp:param value="" name=""/>
 </jsp:include>
 <div class="col-12">
		<h2 class="my-5 text-center">Votre panier <small>Liste Bio</small></h2>

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
			<div class="col-lg-4"></div>
			<c:choose>
	    		<c:when test="${listeCourse.articles.size()>0}">
			        <ul class="list-group col col-lg-4">
			        	<c:forEach var="a" items="${listeCourse.articles}">
				            <li class="list-group-item d-flex justify-content-between align-items-center">
								<div>
									<form action="${pageContext.request.contextPath}/panier" id="form${a.id}" method="post">
										<label class="custom-control custom-checkbox">
											<input type="hidden" name="id" value="${listeCourse.id}"/>
											<input type="hidden" name="id_article" value="${a.id}"/>
									  		<input type="checkbox" class="custom-control-input" name="coche" onclick="document.forms['form${a.id}'].submit();" ${a.coche?"checked":""}/>
									  	
											  <span class="custom-control-indicator"></span>
											  <span class="custom-control-description">${a.nom}</span>
										</label>
									</form>
								</div>
							</li>
				      	</c:forEach>
			        </ul>
		        </c:when>
		        <c:otherwise>
		        	<p>Pas d'article actuellement.<p>
		        </c:otherwise>
	        </c:choose>
	        <div class="col-lg-4"></div>
			
		</div>
		<div class="row py-5"></div>
	</div>

	<!-- Footer -->
    <footer class="row bg-dark footer-demodule fixed-bottom py-1">
		      <div class="text-center col-2 offset-3">
		        <a class="btn" href="listes.html" title="Retour Ã  la liste des courses"><i class="material-icons">arrow_back</i></a>
		      </div>
		      <!-- /.container -->
		<div class="text-center col-2 offset-2">
			<a class="btn" href="panier.html" title="clear"><i class="material-icons">clear</i></a>
		</div>
	</footer>















<footer>
	<%@include file="/WEB-INF/fragments/footer.html" %>
</footer>
</body>
</html>