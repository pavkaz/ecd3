<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h4>Statement of progress of BSEU students of the Faculty of Higher School of Tourism</h4>
  <br>
  <form action="/statement" method="post">
    <div class="row align-items-start">
      <div class="col-auto">
        <label for="year" class="col-form-label">Year</label>
      </div>
      <div class="col-auto">
        <input type="number" class="form-control" id="year" required name="year" <#if year??>value="${year}"</#if>>
      </div>
      <div class="col-auto">
        <label for="semester" class="col-form-label">Semester</label>
      </div>
      <div class="col-auto">
        <input type="number" class="form-control" id="semester" required name="semester"
               <#if semester??>value="${semester}"</#if>>
      </div>
      <div class="col-auto">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Search</button>
      </div>
    </div>
  </form>
  <br>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">Faculty</th>
      <th scope="col">Faculty average grade</th>
      <th scope="col">Group</th>
      <th scope="col">Group average grade</th>
      <th scope="col">Student full name</th>
      <th scope="col">State exam grade</th>
      <th scope="col">Thesis grade</th>
      <th scope="col">Average grade</th>
    </tr>
    </thead>

    <tbody>
    <#list facultyGroups as facultyGroup>
      <tr>
        <td>${facultyGroup.faculty}</td>
        <td>${facultyGroup.avgGradeByFaculty}</td>
        <td>${facultyGroup.groupName}</td>
        <td>${facultyGroup.avgGradeByGroup}</td>
        <td>${facultyGroup.studentFullName}</td>
        <td>${facultyGroup.stateExamGrade}</td>
        <td>${facultyGroup.thesisGrade}</td>
        <td>${facultyGroup.avgGrade}</td>
      </tr>
    </#list>
    </tbody>

  </table>
</@t.template>