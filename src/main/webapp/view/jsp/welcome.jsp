<!DOCTYPE HTML>
<%@page import="java.net.http.HttpResponse"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html lang="en">
 
<body>

<div class="container">
	<h2 class="text-info">Enter Material Name to Search</h2>
	
	
	<form:form  action="welcome"  method="post"  modelAttribute ="bomSearchForm" >
	
		<div class="form-group row">
			<div class="col-sm-9">
				<form:input class="form-control" id="selectedIDNRK" path="selectedIDNRK"  placeholder="Please enter material name to search"/>
			</div>
			<div class="col-sm-1">
				<button id="search-material" name="search-material" class="btn btn-info">List Recommended Replacements</button>
			</div>
		</div>
				
	</form:form>	         


</div>  
 

</body>