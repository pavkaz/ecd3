<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h4>Info</h4>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">№</th>
          <th scope="col"><a href="/teachers">Teacher</a></th>
          <th scope="col"><a href="/teachers/themes">Theme</a></th>
          <th scope="col"><a href="/students">Student</a></th>
          <th scope="col"><a href="/students/marks/add">Marks(Exam, Thesis)</a></th>
        </tr>
      </thead>
      <tbody>
        <#list entities as entity>
          <tr>
            <td>${entity_index + 1}</td>
            <td><a href="/teachers/${entity.teacher.code}">${entity.teacher.fullName}</a></td>
            <td><a href="/teachers/${entity.teacher.code}/themes/${entity.theme.id}">${entity.theme.name}</a></td>
            <td><a href="/students/${entity.student.recordBookNumber}">${entity.student.fullName}</a></td>
            <td>
                <#list entity.marks as marks>
                  <div>
                      ${marks.year}-${marks.semester} (${marks.stateExamGrade}, ${marks.thesisGrade})<#sep><br></#sep>
                  </div>
                </#list>
            </td>
          </tr>
        </#list>
      </tbody>
    </table>
</@t.template>