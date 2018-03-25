<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
</head>
<body style="background-color: <@spring.theme 'background'/>">
<#include "header.ftl">
<h2><@spring.message "label.count"/> : ${count}</h2>
</body>
</html>