<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form:form method="post" modelAttribute="livro"
	action="${url_base}${acao}">
	<form:input path="idMongo" type="hidden" />
	
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="isbn">ISBN</form:label>
		<form:input path="isbn" type="number" cssClass="form-control" />
		<form:errors path="isbn" />
	</div>



	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="titulo">Título</form:label>
		<form:input path="titulo" type="text" cssClass="form-control" />
		<form:errors path="titulo" />
	</div>



	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="ano_publicacao">Ano publicação</form:label>
		<form:input path="ano_publicacao" type="text" cssClass="form-control" />
		<form:errors path="ano_publicacao" />
	</div>


	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="qtd_estoque">Quantidade Estoque</form:label>
		<form:input path="qtd_estoque" type="number" cssClass="form-control" />
		<form:errors path="qtd_estoque" />
	</div>



	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="valor">Valor</form:label>
		<form:input path="valor" type="number" cssClass="form-control" />
		<form:errors path="valor" />
	</div>



	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="id_editora">Editora</form:label>
		<form:select path="id_editora" cssClass="form-control">
			<form:option value="">Selecione</form:option>
			<c:forEach items="${editoras}" var="editora">
				<form:option value="${editora.id}">${editora.nome}</form:option>
			</c:forEach>
		</form:select>
		<form:errors path="id_editora" />
	</div>



	<button type="submit" class="btn btn-primary">Salvar</button>
</form:form>