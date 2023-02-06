<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h4>Teachers</h4>
  <ul class="list-group">
      <#list teachers as teacher>
        <li class="list-group-item">
          <div class="btn-group">
            <a href="/teachers/${teacher.id}" class="btn btn-link">${teacher.fullName}</a>
            <form action="/teachers/${teacher.id}/delete" method="post">
              <input type="hidden" name="_csrf" value="${_csrf.token}">
              <input class="btn btn-link" type="submit" value="-" style="color: red; font-weight: bolder">
            </form>
          </div>
        </li>
      <#else>
        There is no teachers
      </#list>
    <li class="list-group-item">
      <b><a href="/teachers/add">+</a></b>
    </li>
  </ul>
</@t.template>