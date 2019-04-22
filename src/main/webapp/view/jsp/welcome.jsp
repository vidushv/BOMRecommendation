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
   <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    
  </head>
  <body>
    <div class="s002">
      <form:form  action="welcome"  method="post"  modelAttribute ="bomSearchForm" >
        <fieldset>
          <legend>BOM Recommendation System</legend>
        </fieldset>
        <div class="inner-form">
          <div class="input-field first-wrap">
            <form:input class="form-control" id="selectedIDNRK" path="selectedIDNRK"  placeholder="Please enter material name to search" list="materials"/>
            <form:input id="selectedIdentity" style="display:none" path="selectedIdentity"></form:input>
            <form:input id="loadedByPost" style="display:none" path="loadedByPost"></form:input>
          </div>
          <div class="input-field fifth-wrap">
            <button class="btn-search" type="submit">SEARCH</button>
          </div>
        </div>
      </form:form>
    </div>
  
  <script>
  
  if (document.getElementById('loadedByPost').value == 'true'){
	  document.getElementById('bomSearchForm').style = "position: absolute;top: 50px;";
  }
  
  $("#selectedIDNRK").autocomplete({
      source: function( request, response ) { 
        $.ajax({
          url: "/api/stpo",
          dataType: "json",
          data: {
            featureClass: "P",
            style: "full",
            maxRows: 12,
            name_startsWith: request.term
          },
          success: function (data) {
        	    response($.map(data, function (item) {
        	        return {
        	            label: item.IDNRK,
        	            value: item.IDNRK,
        	            identity: JSON.stringify(item.stpoIdentity)
        	        }
        	    }));
        	},
            error: function(jqXHR, textStatus, errorThrown){
                alert(jqXHR);                        
            },
        });
      },
      minLength: 2,
      select: function( event, ui ) {
    	console.log( ui.item ?
          "Selected: " + ui.item.IDNRK :
          "Nothing selected, input was " + this.value);
          document.getElementById('selectedIdentity').value = ui.item.identity;
      },
      open: function() {
        console.log('open');
      },
      close: function() {
    	  console.log('close');
      }
      
    });

  </script>
   </body>
</html>
