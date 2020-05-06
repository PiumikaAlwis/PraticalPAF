<%@page import="model.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="Views/css/bootstrap.min.css">
<script src="Component/jquery-3.5.0.min.js"></script>
<script src="Component/hospital.js"></script>

</head>
<body>
	<div class="container">
		<div class="form">
			<div class="colum-1">
			<br><br><br>
				<form id="formHospital" name="formHospital" method="post" action="hospital.jsp">
					Name of the Hospital: <input id="hospitalname" name="haspitalname" type="text" class="form-control form-control-sm">
				<br>
					Address:<input id="address" name="address" type="text" class="form-control form-control-sm"> 
				<br> 
					Emergency Contact: <input id="hotline" name="hotline" type="text" class="form-control form-control-sm"> 
				<br> 
					Regular Contact number: <input id="contact" name="contactNumber" type="text" class="form-control form-control-sm"> 
				<br> 
					Description: <input id="dis" name="discription" type="text" class="form-control form-control-sm"> 
				<br> <br>
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidIDSave" name="hidIDSave" value="">
				</form>

	<br>
	<div id="alert-success" class="alert alert-success">
		<%-- <%
			out.print(session.getAttribute("statusMsg"));
		%>  --%>
	</div>

	<div id="alert-error" class="alert alert-error"></div>

	<br>
	<div id="divHospitalGrid">
	    
	 <%-- <%
	    Hospital hosObj = new Hospital();
		out.print(hosObj.readHospital());
	 %> --%>
 		
		 
</div>

			</div>
		</div>
	</div>
</body>
</html>