<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h4>Add student</h4>
  <form action="/students/add" method="post">
    <div class="form-group">
      <label for="fullName">Full name</label>
      <input type="text" class="form-control" id="fullName" required name="fullName">
    </div>
    <div class="form-group">
      <label for="faculty">Faculty</label>
      <input type="text" class="form-control" id="faculty" required name="faculty">
    </div>
    <div class="form-group">
      <label for="groupName">Group name</label>
      <input type="text" class="form-control" id="groupName" required name="groupName">
    </div>
    <div class="form-group">
      <select class="form-select" id="themeName" name="themeName">
        <option disabled>Select theme</option>
          <#list themes as theme>
            <option value="${theme.name}">${theme.name}</option>
          </#list>
      </select>
    </div>
    <#if themes?size != 0>
      <input type="hidden" name="_csrf" value="${_csrf.token}">
      <button type="submit" style="width: 100%" class="btn btn-dark">Add</button>
    <#else>
      There is no themes for students
    </#if>
  </form>
</@t.template>