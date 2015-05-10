<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css"
	href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<title>Generate Housie Sheets</title>

<script>
	function allnumeric(sheet) {
		var numbers = /^[0-9]+$/;
		var result = false;
		if (sheet.value.match(numbers)) {
			document.generate.sheet.focus();
			result = true;
		} else {
			alert('Please input numeric characters only');
			document.generate.sheet.focus();
			return false;
		}
		return result;
	}
</script>
</head>

<body>
	<h2>Generate Housie Sheets</h2>
	<img src="images/fb_housie.jpg" alt="Mountain View"
		style="width: 304px; height: 228px">
	<form method="POST" action='HousieController' name="generate">
		No Of Players : <input type="text" name="sheet"
			value="<c:out value="${inputData.sheet}" />" /> <br /> <br /> <input
			type="submit" value="Submit"
			onclick="return allnumeric(document.generate.sheet)" />
	</form>

</body>
</html>