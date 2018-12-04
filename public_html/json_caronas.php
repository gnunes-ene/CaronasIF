<?php

	//$id = filter_input(INPUT_POST, "id");
//	transformarConteudoPaginaEmJson();
	mostrarUsuarios();

//function transformarConteudoPaginaEmJson(){
//	header('Content-Type: application/json');
//}


function getUsuariosDoBanco(){
	$pdo = conectaDB();
	$a =array();
	$sql = $pdo->query("select c.id,c.data,c.hora,c.origem,c.destino,c.vagas,u.nome,u.telefone
from user u join carona c on (u.id = c.id_user) order by c.data");
	$usuarios = array();
	while($usuario = $sql->fetch(PDO::FETCH_ASSOC)){
		$usuarios[] = $usuario;
	}
	return $usuarios;
}	

function mostrarUsuarios(){
	$dados = (object) array(); // Para evitar a mensagem de warning na página
	$dados->carona = getUsuariosDoBanco();
	echo json_encode($dados, JSON_PRETTY_PRINT);
}


function conectaDB(){
 	$pdo = null;
 	try{
 		$pdo = new PDO("mysql:host=mysql.hostinger.com.br;dbname=u766469038_iftm", "u766469038_iftm", "005515");
 	}catch(PDOException $e){
 		echo "Não foi possível conectar ao servidor de dados. <br>Erro: " . $e;
 	}
 	return $pdo;
 }


 ?>