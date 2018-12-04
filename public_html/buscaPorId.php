<?php
@session_start();
	$id_user = $_SESSION['user']['id'];
	getUser();
	

function getUser(){
	$pdo = conectaDB();
	$sql = $pdo->query("SELECT * FROM user where id = '".$id_user."'");
	while($linha = $sql->fetch(PDO::FETCH_ASSOC)){
		echo "".$linha["id"]."&&";
		echo "".$linha["nome"]."&&";
		echo "".$linha["telefone"]."&&";
		echo "".$linha["email"]."//";
		
	}
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