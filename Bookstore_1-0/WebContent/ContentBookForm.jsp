<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form class="form" method="post" action="${pageContext.request.contextPath}/save">
	<fieldset>
		<legend>
			<c:choose>
				<c:when test="${not empty book.id}">
					Edit book
				</c:when>
				<c:otherwise>
				Add book
				</c:otherwise>
			</c:choose>
		</legend>
		<c:choose>
			<c:when test="${not empty book.id }">
				<input type="hidden" name="id" value="${book.id}"/>
				<input type="hidden" name="edition" value="true"/>
			</c:when>
			<c:otherwise>
				<label for="title">Code</label>
				<input type="text" name="id" id="id" value="${book.id}"/>
				<input type="hidden" name="edition" value="false"/>
			</c:otherwise>
		</c:choose>
		<div>
			<label for="title">Title</label>
			<input type="text" name="title" id="title" value="${book.title}"/>
		</div>
		<div>
			<label for="description">Description</label>
			<textarea name="description" id="description" rows="2" cols="60">
				${book.description}
			</textarea>
		</div>
		<div>
			<label for="author">Author</label>
			<input name="author" id="title" value="${book.author}"/>
		</div>
		<div>
			<label for="price">Price</label>
			<input name="price" id="price" value="${book.price }"/>
		</div>
	</fieldset>
	<div class="button-row">
		<a href="${pageContext.request.contextPath}/listbooks">Cancel</a> ou <input type="submit" value="Submit"/>
	</div>
</form>