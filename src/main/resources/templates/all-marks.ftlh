<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h4>All marks of student: <a href="/students/${student.recordBookNumber}" class="btn btn-link">${student.fullName}</a></h4>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">Id</th>
      <th scope="col">State exam grade</th>
      <th scope="col">Thesis grade</th>
      <th scope="col">Year</th>
      <th scope="col">Semester</th>
    </tr>
    </thead>
    <tbody>
    <#list allMarks as marks>
      <tr>
        <td>${marks.id}</td>
        <td>${marks.stateExamGrade}</td>
        <td>${marks.thesisGrade}</td>
        <td>${marks.year}</td>
        <td>${marks.semester}</td>
      </tr>
    </#list>
    </tbody>
  </table>
</@t.template>