<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<a href="${pageContext.request.contextPath}/edit">Add a book:</a>

<table class="listing">
	<tr>
		<th>Code
			<span class="sortLink">[
				<a href="${pageContext.request.contextPath}/listbooks?sort=asc&field=id">asc</a>|
				<a href="${pageContext.request.contextPath}/listbooks?sort=desc&field=id">desc</a>
		]	</span>
		</th>
		<th>Title
			<span class="sortLink">[
				<a href="${pageContext.request.contextPath}/listbooks?sort=asc&field=title">asc</a>|
				<a href="${pageContext.request.contextPath}/listbooks?sort=desc&field=title">desc</a>
		]	</span>
		</th>
		<th>Description</th>
		<th>Author
			<span class="sortLink">[
				<a href="${pageContext.request.contextPath}/listbooks?sort=asc&field=author">asc</a>|
				<a href="${pageContext.request.contextPath}/listbooks?sort=desc&field=author">desc</a>
		]	</span>
		</th>
		<th>Price
			<span class="sortLink">[
				<a href="${pageContext.request.contextPath}/listbooks?sort=asc&field=price">asc</a>|
				<a href="${pageContext.request.contextPath}/listbooks?sort=desc&field=price">desc</a>
		]	</span>
		</th>
		<th>Action</th>
	</tr>
	<c:forEach var="book" items="${books}">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath}/edit?id=${book.id}">${book.id}</a>
			</td>
			<td>${book.title}</td>
			<td>${book.description}</td>
			<td>${book.author}</td>
			<td>${book.price}</td>
			<td><a href="${pageContext.request.contextPath}/delete?id=${book.id}">Delete</a></td>
		</tr>
	</c:forEach>
</table>