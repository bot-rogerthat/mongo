<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
	<style>
		.error {
			color: #ff0000;
			font-style: italic;
			font-weight: bold;
		}
	</style>
</head>
<body style="background-color: <@spring.theme 'background'/>">
<#include "header.ftl">
<form action="add" method="post" name="user" enctype="multipart/form-data">
	<table>
		<tr>
		<@spring.bind "user.name"/>
			<td><@spring.message "prop.name"/></td>
			<td><@spring.formInput "user.name" "" "text"/></td>
			<td><@spring.showErrors '', "error"/></td>
		</tr>
		<tr>
		<@spring.bind "user.email"/>
			<td><@spring.message "prop.email"/></td>
			<td><@spring.formInput "user.email" "" "text"/></td>
			<td><@spring.showErrors '', "error"/></td>
		</tr>
		<tr>
		<@spring.bind "user.dayOfBirth"/>
			<td><@spring.message "prop.dayOfBirth"/></td>
			<td><@spring.formInput "user.dayOfBirth" "" "date"/></td>
			<td><@spring.showErrors '', "error"/></td>
		</tr>
		<tr>
		<@spring.bind "user.image"/>
			<td><@spring.message "prop.image"/></td>
			<td><input type="file" name="file" accept=".png,.jpg,.jpeg"/></td>
		</tr>
		<tr>
			<td>
				<button type="submit">
				<@spring.message "label.submit"/>
				</button>
			</td>
		</tr>
	</table>
</form>
</body>
</html>