<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h4>Select the student to add the marks</h4>
  <ul class="list-group">
      <#list students as student>
        <li class="list-group-item">
          <a href="/students/${student.recordBookNumber}/marks/add">${student.fullName}</a>
        </li>
      <#else>
        There is no students
      </#list>
  </ul>
</@t.template>