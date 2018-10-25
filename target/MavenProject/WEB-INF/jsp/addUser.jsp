<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="addUser" style="visibility: hidden;" draggable="true">
	<h2>新增用户</h2>
	<form action="addUser" method="post">
		<table border="1">
			<tr>
				<td>name</td>
				<td><input id="add_name" type="text" name="name" /></td>
			</tr>
			<tr>
				<td>age</td>
				<td><input id="add_age" type="text" name="age" /></td>
			</tr>
		</table>

	</form><jsp:include page="index.jsp" flush="true" />
	<button id="addSubmit">提交</button>
</div>