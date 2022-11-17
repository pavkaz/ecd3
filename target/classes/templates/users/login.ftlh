<#import "../blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h1 style="text-align: center">Authorize</h1>
  <hr>
  <form action="/users/login" method="post">
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

    <div class="row align-items-center">
      <div class="col"></div>
      <div class="row align-items-center">
        <div class="col">
          <a class="btn btn-dark " href="/users/registration">Registration</a>
        </div>
        <div class="col">
          <button type="submit" class="btn btn-dark">Login</button>
        </div>
      </div>
    </div>

  </form>
</@t.template>