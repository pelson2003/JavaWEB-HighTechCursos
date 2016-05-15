<!DOCTYPE html>
	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>


<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuário com JSL</title>
</head>
<body>

		<form action="usucontroller.do" method="post" >
	
		<label> ID: </label>
		<input type="text" readonly="readonly" name="txtid" value="${requestScope.usuario.id}" size="5" />
	
		<label> Nome: </label>
		<input type="text" name="txtnome" value="${requestScope.usuario.nome}" size="20" />
		
		<label> Login: </label>
		<input type="text" name="txtlogin" value="${requestScope.usuario.login}" size="20" /> 
		
		<label> Senha: </label>
		<input type="password" name="txtsenha" value="${requestScope.usuario.senha}" size="20" />
		
		<input type="submit" name="txtlogin" value="Salvar" />
		
		<c:import url="includes/menu.jsp"></c:import>
	
	</form>


</body>
</html>