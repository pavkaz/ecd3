<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h4>Teacher info</h4>
  Full name: ${teacher.fullName}<br>
  Degree: ${teacher.degree}<br>
  Academic rank: ${teacher.academicRank}<br>
  Department: ${teacher.department}<br>
  Phone number: ${teacher.phoneNumber}<br>
  Email: ${teacher.email}<br>
  Themes: <#list teacher.themes as theme><a href="/teachers/${teacher.code}/themes/${theme.id}">${theme.name}</a><#sep>, </#list><br>
</@t.template>