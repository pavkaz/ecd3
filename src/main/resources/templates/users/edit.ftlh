<#import "../blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h1 style="text-align: center">Edit user ${user.username}</h1><hr>
  <form action="/users/current/edit" method="post">
    <div class="form-group">
      <label for="name">Name</label>
      <input type="text" class="form-control" id="name" required name="name" value="${user.username}">
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" class="btn btn-dark">Edit</button>
  </form>
</@t.template>

