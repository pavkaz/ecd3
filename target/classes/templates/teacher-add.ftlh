<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h4>Add teacher</h4>
  <form action="/teachers/add" method="post">
    <div class="form-group">
      <label for="fullName">Full name</label>
      <input type="text" class="form-control" id="fullName" required name="fullName">
    </div>
    <div class="form-group">
      <label for="degree">Degree</label>
      <input type="text" class="form-control" id="degree" required name="degree">
    </div>
    <div class="form-group">
      <label for="title">Title</label>
      <input type="text" class="form-control" id="title" name="title">
    </div>
    <div class="form-group">
      <label for="department">Department</label>
      <input type="text" class="form-control" id="department" name="department">
    </div>
    <div class="form-group">
      <label for="phoneNumber">Phone number</label>
      <input type="text" class="form-control" id="phoneNumber" required name="phoneNumber">
    </div>
    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" class="form-control" id="email" required name="email">
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" style="width: 100%" class="btn btn-dark">Add</button>
  </form>
</@t.template>