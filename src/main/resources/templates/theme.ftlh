<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h4>Theme info</h4>
  Name: ${theme.name}<br>
  Teacher: <a href="/teachers/${teacher.code}">${teacher.fullName}</a><br>
</@t.template>