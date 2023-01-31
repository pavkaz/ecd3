<#macro template authorise>
  <!doctype html>
  <html lang="ru">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/static/css/style.css">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
          crossorigin="anonymous">

    <title>Themes Configurer</title>
  </head>

  <body>
  <nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
      <div>
        <a href="/info">
          <span class="navbar-brand mb-0 h1">Students themes</span>
        </a>

        <a href="/progress">
          <span class="navbar-brand mb-0 h1">Students progress</span>
        </a>
      </div>
        <#if authorise>
          <div class="dropdown text-end">
            <a href="/users/current/info">
              <img src="/images/avatar.png" alt="user"
                   width="32" height="32" class="rounded-circle">
            </a>
          </div>
        <#else>
          <button class="btn btn-light my-2 my-sm-0" type="button"
                  onclick="window.location.href = '/users/login'">
            Login
          </button>
        </#if>
    </div>
  </nav>

  <div class="container mt-4">
      <#nested/>
      <#if errorMessage??>
        <h10 style="color: red">${errorMessage}</h10>
      </#if>
  </div>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
          integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
          crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
          integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
          crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
          integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
          crossorigin="anonymous"></script>
  </body>
  </html>
</#macro>