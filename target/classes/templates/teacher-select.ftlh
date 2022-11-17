<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h4>Select the teacher to add the theme</h4>
  <ul class="list-group">
      <#list teachers as teacher>
        <li class="list-group-item">
          <a href="/teachers/${teacher.code}/themes/add">${teacher.fullName}</a>
        </li>
      <#else>
        There is no teacher
      </#list>
  </ul>
</@t.template>