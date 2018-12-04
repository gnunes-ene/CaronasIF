<?php

	getCarona();

function getCarona(){
	$pdo = conectaDB();
	$sql = $pdo->query("SELECT * FROM carona");
	while($linha = $sql->fetch(PDO::FETCH_ASSOC)){
		echo "".$linha["id"]."&&";
		echo "".$linha["data"]."&&";
		echo "".$linha["hora"]."&&";
		echo "".$linha["origem"]."&&";
		echo "".$linha["destino"]."&&";
		echo "".$linha["vagas"]."&&";
		echo "".$linha["id_user"]."//";	
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