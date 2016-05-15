<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List" %>
<%@page import="br.com.hightechcursos.entidades.Usuario" %>

   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table border="1" >
<tr bgcolor="EAEAEA">
	<th> ID </th> <th> Nome </th> <th> Login </th> <th> Senha </th> <th> Acao </th>
</tr>

<%
List<Usuario> lista = (List<Usuario>) request.getAttribute("lista");

for(Usuario usu: lista){
%>	
	
	<tr>
	
	<td> <%= usu.getId()%> </td>
	<td> <% out.print(usu.getNome()); %> </td>
	<td> <%= usu.getLogin() %> </td>
	<td> <%= usu.getSenha() %> </td>
	
	<td>
	<a href="usucontroller.do?acao=exc&id=<%=usu.getId()%>"> Exluir </a>
	|
	<a href="usucontroller.do?acao=alt&id=<%=usu.getId()%>"> Alterar </a>
	|
	<a href="usucontroller.do?acao=cad"> Incluir </a>
	
	</td>
	

	</tr>

<% } %>



</table>
</body>
</html>