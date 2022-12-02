<#import "blocks/login-template.ftlh" as t>
<@t.template authorise>
  <h4>Student info</h4>
  Full name: ${student.fullName}<br>
  Faculty: ${student.faculty}<br>
  Group name: ${student.groupName}<br>
  Theme: <a href="/teachers/${teacher.code}/themes/${student.theme.id}">${student.theme.name}</a><br>
  <a href="/students/${student.recordBookNumber}/marks">Marks</a>
</@t.template>