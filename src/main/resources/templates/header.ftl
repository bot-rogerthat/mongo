<#import "/spring.ftl" as spring />
<span style="float: right">
	<div>
		<a href='<@spring.url "/users"/>'><@spring.message "label.home"/></a>
	</div>
	<div>
		<a href='<@spring.url "?lang=en"/>'>en</a>|<a href='<@spring.url "?lang=ru"/>'>ru</a>
	</div>
	<div>
		<a href='<@spring.url "?theme=theme1"/>'>GhostWhite</a>|<a href='<@spring.url "?theme=theme2"/>'>Beige</a>
	</div>
</span>