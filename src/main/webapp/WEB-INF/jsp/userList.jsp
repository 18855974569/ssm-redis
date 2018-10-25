<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<title>用户列表页面</title>
</head>
<body>
	<table class="layui-hide" id="test" lay-filter="test"></table>

	<div class="modal fade" id="addUserModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增用户</h4>
				</div>
				<div class="modal-body">
					<form id="form2">
						<table>
							<tr>
								<td>姓名：</td>
								<td><input id="add_name" type="text" name="name"
									class="layui-input" /></td>
							</tr>
							<tr>
								<td>地址：</td>
								<td><input id="add_adress" type="text" name="address"
									class="layui-input" /></td>
							</tr>
							<tr>
								<td>年龄：</td>
								<td><input id="add_age" type="number" name="age"
									class="layui-input" /></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" id="addSubmit" class="btn btn-primary">Save</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="editUserModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改用户</h4>
				</div>
				<div class="modal-body">
					<form id="form1">
						<table>
							<tr>
								<td>id：</td>
								<td><input id="edit_id" type="text" name="id"
									class="layui-input" readonly="readonly" /></td>
							</tr>
							<tr>
								<td>姓名：</td>
								<td><input id="edit_name" type="text" name="name"
									class="layui-input" /></td>
							</tr>
							<tr>
								<td>地址：</td>
								<td><input id="edit_address" type="text" name="address"
									class="layui-input" /></td>
							</tr>
							<tr>
								<td>年龄：</td>
								<td><input id="edit_age" type="number" name="age"
									class="layui-input" /></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" id="editSubmit" class="btn btn-primary">Save</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>


<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
<!-- <script type="text/javascript" src="statics/js/jquery-3.3.1.min.js"></script> -->


<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="add">新增</button>
    <button class="layui-btn layui-btn-sm" lay-event="edit">修改</button>
    <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
  </div>
</script>
<script type="text/javascript">
	var table;
	var layer;
	layui.use('table', function() {
		layer = layui.layer;
		table = layui.table;
		refresh();
		//头工具栏事件
		table.on('toolbar(test)', function(obj) {
			var checkStatus = table.checkStatus(obj.config.id);
			switch (obj.event) {
			case 'add':
				$('#addUserModel').modal('show');
				break;
			case 'edit':
				var data = checkStatus.data;
				if (data.length == 0) {
					layer.msg("请选择一行编辑");
					return;
				}
				if (data.length > 1) {
					layer.msg("只可以选择一行编辑");
					return;
				}
				$('#editUserModel').modal('show');

				$.ajax({
					url : "queryUserById?id=" + data[0].id,
					type : "GET",
					data : {},
					success : function(data) {
						$("#editUser").css("visibility", "visible");
						$("#edit_id").val(data.id);
						$("#edit_name").val(data.name);
						$("#edit_address").val(data.address);
						$("#edit_age").val(data.age);
					}
				});
				break;
			case 'delete':
				var data = checkStatus.data;
				if (data.length == 0) {
					layer.msg("请选择一行删除");
					return;
				}
				//询问框
				layer.confirm('确定删除吗？', {
					btn : [ '确定', '取消' ]
				//按钮
				}, function() {
					$.ajax({
						url : "deleteUserList",
						type : "post",
						contentType : 'application/json; charset=UTF-8',
						data : JSON.stringify(data),
						success : function(data) {
							refresh();
							layer.msg('删除成功!');
						},
						error : function(data) {
							console.log(data);
						}
					});
				}, function() {
					layer.msg("取消删除");
				});
				break;
			}
		});
	});

	//异步刷新页面
	function refresh() {
		table.render({
			elem : '#test',
			url : '/MavenProject/queryUser',
			toolbar : '#toolbarDemo',
			title : '用户数据表',
			cols : [ [ {
				type : 'checkbox',
				fixed : 'left'
			}, {
				field : 'id',
				title : 'ID',
				sort : true,
				ort : true
			}, {
				field : 'name',
				title : '用户名',
				edit : 'text'
			}, {
				field : 'age',
				title : '年龄',
				edit : 'text'
			}, {
				field : 'address',
				title : '地址',
				edit : 'text'
			} ] ],
			page : true
		});
	}

	/* 新增提交 */
	$("#addSubmit").click(function() {
		var data = $("#form2").serialize();
		data = changeFormData(data);

		$.ajax({
			url : "addUser", //你的路由地址
			type : "post",
			data : data,
			success : function(data) {
				$('#addUserModel').modal('hide');
				refresh();
			},
			error : function(data) {
				console.log(data);
			}
		});
	});

	//提交
	$("#editSubmit").click(function() {
		var d = $("#form1").serialize();
		$.ajax({
			url : "updateUser", //你的路由地址
			type : "post",
			data : d,
			success : function(data) {
				$('#editUserModel').modal('hide');
				refresh();
			},
			error : function(data) {
				console.log(data);
			}
		});
	});

	//把Form表单序列化的转换为json对象
	function changeFormData(data) {
		data = decodeURIComponent(data, true);//防止中文乱码  
		data = data.replace(/&/g, "','");
		data = data.replace(/=/g, "':'");
		data = "({'" + data + "'})";
		obj = eval(data);
		return obj;
	}
</script>

