<#import "../blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h1 style="text-align: center">Student registration</h1>
  <hr>
  <form action="/users/registration/2" method="post">
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

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" style="width: 100%" class="btn btn-dark">Finish</button>
  </form>
</@t.template>