<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form:form method="post" modelAttribute="editora"
	action="${url_base}${acao}">

	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="id">ID</form:label>
		<form:input path="id" type="number" cssClass="form-control" />
		<form:errors path="id" />
	</div>

	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="nome">Nome</form:label>
		<form:input path="nome" type="text" cssClass="form-control" />
		<form:errors path="nome" />
	</div>

	<button type="submit" class="btn btn-primary">Salvar</button>
</form:form>