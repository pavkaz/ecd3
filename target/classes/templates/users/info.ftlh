<#import "../blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h1 style="text-align: center">User info</h1>
<hr>
  <div style="text-align: center"><h2>${user.name}</h2></div><br>

  <div class="row align-items-center">
    <div class="col">
      <form action="/users/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" class="btn btn-warning" value="Exit">
      </form>
    </div>
    <div class="row align-items-center">
      <div class="col">
        <a class="btn btn-dark " href="/users/current/edit">Edit</a>
      </div>
      <div class="col">
        <form action="/users/current/delete" method="post">
          <input type="hidden" name="_csrf" value="${_csrf.token}">
          <button type="submit" class="btn btn-danger">Delete</button>
        </form>
      </div>
    </div>
  </div>
</@t.template>