<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h4>Add theme</h4>
  <form action="/teachers/${code}/themes/add" method="post">
    <#if errorMessage??>
      ${errorMessage}
    <#else>
      <div class="form-group">
        <label for="name">Theme name</label>
        <input type="text" class="form-control" id="name" required name="name">
      </div>
      <input type="hidden" name="_csrf" value="${_csrf.token}">
      <button type="submit" style="width: 100%" class="btn btn-dark">Add</button>
    </#if>
  </form>
</@t.template>