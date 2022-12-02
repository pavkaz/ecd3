<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
    <#if errorMessage??>
        ${errorMessage}
    <#else>
      <h4>Add marks for student: <a href="/students/${student.recordBookNumber}"
                                    class="btn btn-link">${student.fullName}</a></h4></h4>
      <form action="/students/${student.recordBookNumber}/marks/add" method="post">
        <div class="form-group">
          <label for="stateExamGrade">State exam grade</label>
          <input type="text" class="form-control" id="stateExamGrade" required name="stateExamGrade">
        </div>
        <div class="form-group">
          <label for="thesisGrade">Thesis grade</label>
          <input type="text" class="form-control" id="thesisGrade" required name="thesisGrade">
        </div>
        <div class="form-group">
          <label for="year">Year</label>
          <input type="text" class="form-control" id="year" name="year">
        </div>
        <div class="form-group">
          <label for="semester">Semester</label>
          <input type="text" class="form-control" id="semester" name="semester">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Add</button>
      </form>
    </#if>
</@t.template>