<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
</head>
<body style="background-color: <@spring.theme 'background'/>">
<#include "header.ftl">
<h2><@spring.message "label.errorPage"/></h2>
<hr>
<h3>${message}</h3>
</body>
</html>