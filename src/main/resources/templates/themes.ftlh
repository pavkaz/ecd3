<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h4>Themes</h4>
  <ul class="list-group">
    <#list teachers as teacher>
      <#list teacher.themes as theme>
        <li class="list-group-item">
          <div class="btn-group">
            <a href="/teachers/${teacher.code}/themes/${theme.id}"
               class="btn btn-link">${theme.name}</a>
            <form action="/teachers/${teacher.code}/themes/${theme.id}/delete" method="post">
              <input type="hidden" name="_csrf" value="${_csrf.token}">
              <input class="btn btn-link" type="submit" value="-" style="color: red; font-weight: bolder">
            </form>
          </div>
        </li>
      <#else>
        There is no themes
      </#list>
    <#else>
      There is no teachers
    </#list>
    <li class="list-group-item">
      <b><a href="/teachers/themes/add">+</a></b>
    </li>
  </ul>
</@t.template>