<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%@ include file="../head/head.jsp"%>

<div class="layui-body" style="margin: 20px 20px 0 20px">


	<c:forEach var="article" items="${list }">
		<div>
			<div class="articleTitle">${article.title }</div>
			<span>Last Modified : ${article.updated_at } </span> | <span>主题1</span>
			<%-- <div class="articleBody">${article.body }</div>  --%>
			<div>
				<a>阅读全文 >></a>
			</div>
			<hr>
		</div>

	</c:forEach>

</div>

<%@ include file="../footer/foot.jsp"%>




