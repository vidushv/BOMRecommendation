<!DOCTYPE HTML>
<%@page import="java.net.http.HttpResponse"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="author" content="colorlib.com">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500" rel="stylesheet" />
    <link href="css/main.css" rel="stylesheet" />
  </head>
  <body>
    <div class="s002">
      <form:form  action="welcome"  method="post"  modelAttribute ="bomSearchForm" >
        <fieldset>
          <legend>BOM Recommendation System</legend>
        </fieldset>
        <div class="inner-form">
          <div class="input-field first-wrap">
            <form:input class="form-control" id="selectedIDNRK" path="selectedIDNRK"  placeholder="Please enter material name to search"/>
          </div>
          <div class="input-field fifth-wrap">
            <button class="btn-search" type="button">SEARCH</button>
          </div>
        </div>
      </form:form>
    </div>
    <script src="js/extention/choices.js"></script>
    <script src="js/extention/flatpickr.js"></script>
    <script>
      flatpickr(".datepicker",
      {});

    </script>
    <script>
      const choices = new Choices('[data-trigger]',
      {
        searchEnabled: false,
        itemSelectText: '',
      });

    </script>
  </body>
</html>
