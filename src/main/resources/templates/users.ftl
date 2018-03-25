<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
	<style>
		table {
			border-collapse: collapse;
		}

		table, td, th {
			border: 1px solid black;
		}

		.info {
			margin: 0 0 0 20px;
		}
	</style>
</head>
<body style="background-color: <@spring.theme 'background'/>">
<#include "header.ftl">
<table>
	<tr>
		<input type="submit" value='<@spring.message "label.addUser"/>' onclick="location.href='users/add'"/>
	</tr>
	<tr>
		<form action="find" method="POST">
			<label><@spring.message "prop.name"/></label>:
			<input type="text" name="name"/>
			<input type="submit" value="<@spring.message "label.findUser"/>"/>
		</form>
	</tr>
	<tr>
		<a class="info" href='<@spring.url "/loggers"/>'>loggers</a>
	</tr>
	<tr>
		<a class="info" href='<@spring.url "/heapdump"/>'>heapdump</a>
	</tr>
</table>
<#if users?has_content>
<h2><@spring.message "label.users"/> (<a href='<@spring.url "/count"/>'><@spring.message "label.count"/></a>)</h2>
<table>
	<thead>
	<tr>
		<th>
			<@spring.message "prop.name"/>
			<a href="<@spring.url "users?sort=name"/>">^</a>
		</th>
		<th>
			<@spring.message "prop.email"/>
			<a href='<@spring.url "users?sort=email"/>'>^</a>
		</th>
		<th><@spring.message "prop.dayOfBirth"/></th>
		<th><@spring.message "prop.image"/></th>
		<th><@spring.message "prop.action"/></th>
	</tr>
	</thead>
	<tbody>
	<#list users as user>
	<tr>
		<td>${user.name}</td>
		<td>${user.email}</td>
		<td>${user.dayOfBirth?string("dd-MM-yyyy")}</td>
		<td>
			<#if user.base64?has_content>
			<img src="data:image/jpg;base64,${user.base64}" width="50" height="50"/>
			<#else>
			<@spring.message "label.notImage"/>
		</#if>
		</td>
		<td>
			<input type="submit" value='<@spring.message "label.edit"/>'
				   onclick="location.href='<@spring.url "users/${user.id}/update"/>'"/>
			<input type="submit" value='<@spring.message "label.delete"/>'
				   onclick="location.href='<@spring.url "users/${user.id}/delete"/>'"/>
		</td>
	</tr>
	</#list>
	</tbody>
</table>
<#else>
	<h2><@spring.message "label.notFound"/></h2>
</#if>
</body>
</html>