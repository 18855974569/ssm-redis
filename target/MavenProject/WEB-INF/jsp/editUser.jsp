<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="editUser" style="visibility: hidden;">
	<h2>修改用户</h2>
	<form action="updateUser" method="post">
		<table border="1">
			<tr id="add_hideen">
				<td>id</td>
				<td><input id="edit_id" type="text" name="id"
					value="${user.id }" /></td>
			</tr>
			<tr>
				<td>name</td>
				<td><input id="edit_name" type="text" name="name"
					value="${user.name }" /></td>
			</tr>
			<tr>
				<td>age</td>
				<td><input id="edit_age" type="text" name="age"
					value="${user.age }" /></td>
			</tr>
		</table>

	</form>
	<button id="submit">提交</button>
</div>