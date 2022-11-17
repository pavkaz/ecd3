<#import "../blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h1 style="text-align: center">Registration</h1>
  <hr>
  <form action="/users/registration" method="post">
    <div class="form-group">
      <input type="text" class="form-control <#if errorMessage??>is-invalid</#if>"
             id="username" name="username">
      <label class="form-label" for="username">Username</label>
      <div id="username" class="invalid-feedback">
        Invalid username
      </div>
    </div>

    <div class="form-group">
      <input type="password" class="form-control <#if errorMessage??>is-invalid</#if>"
             id="password" name="password">
      <label class="form-label" for="password">Password</label>
      <div id="password" class="invalid-feedback">
        Invalid password
      </div>
    </div>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" style="width: 100%" class="btn btn-dark">Create</button>
  </form>
</@t.template>