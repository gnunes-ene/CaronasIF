<?php
include_once 'connection.php';
@session_start(); // inicia ou pega a sessao existente
	class Carona{


		private $db;
		private $connection;

		function __construct() {
			
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}

		public function cadastrar_carona($data,$hora,$origem,$destino,$vagas,$id_user)
		{
			  // pega o id do cara logado e passa pra sql
				$query = "insert into carona (data, hora, origem, destino, vagas, id_user) 
				values ( '$data','$hora','$origem','$destino','$vagas', '$id_user')";
				$inserted = mysqli_query($this -> connection, $query);
				if($inserted == 1 ){
					echo '1';
					//$json['success'] = 'Carona cadastrada com sucesso';
				}else{
					echo 'erro';
				}
				echo json_encode($json);
				mysqli_close($this->connection);		
			
		}
	}

		$carona = new Carona();
	if(isset($_POST['data'],$_POST['hora'],$_POST['origem'],$_POST['destino'],$_POST['vagas'],$_POST['id_user'])) {
		$data = $_POST['data'];
		$hora = $_POST['hora'];
		$origem = $_POST['origem'];
		$destino = $_POST['destino'];
		$vagas = $_POST['vagas'];
		$id_user = $_POST['id_user'];
		
		if(!empty($data) && !empty($hora) && !empty($origem) && !empty($destino) && !empty($vagas) && !empty($id_user)){
			$carona-> cadastrar_carona($data,$hora,$origem,$destino,$vagas,$id_user);
			
		}else{
			echo json_encode("you must type both inputs");
		}
		
	}
?>