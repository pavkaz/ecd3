<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h4>Students</h4>
  <ul class="list-group">
    <#list students as student>
      <li class="list-group-item">
        <div class="btn-group">
          <a href="/students/${student.id}" class="btn btn-link">${student.fullName}</a>
          <form action="/students/${student.id}/delete" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input class="btn btn-link" type="submit" value="-" style="color: red; font-weight: bolder">
          </form>
        </div>
      </li>
    <#else>
      There is no students
    </#list>
    <li class="list-group-item">
      <b><a href="/students/add">+</a></b>
    </li>
  </ul>
</@t.template>