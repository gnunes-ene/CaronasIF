<?php
include_once 'connection.php';

	class User{

		private $db;
		private $connection;

		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}

		public function cadastrar_usuario($nome,$telefone,$email,$senha)
		{
			$query = "Select * from user where email='$email'";
			$result = mysqli_query($this->connection, $query);
			if (mysqli_num_rows($result)>0) 
			{
				$json['success'] = ' Esse e-mail ja possui um cadastro '.$email;
				
			}else{
				$query = "insert into user (nome, telefone, email, senha) values ( '$nome','$telefone','$email','$senha')";
				$inserted = mysqli_query($this -> connection, $query);
				if($inserted == 1 ){
					$json['success'] = 'Usuario cadastrado com sucesso';
				}else{
					$json['error'] = 'Erro ao cadastrar Usuario' .$nome;
				}
				echo json_encode($json);
				mysqli_close($this->connection);


			}
						
			
		}
	}

		$user = new User();
	if(isset($_POST['nome'],$_POST['telefone'],$_POST['email'],$_POST['senha'])) {
		$nome = $_POST['nome'];
		$telefone = $_POST['telefone'];
		$email = $_POST['email'];
		$senha = $_POST['senha'];
		
		
		if(!empty($nome) && !empty($telefone) && !empty($email) && !empty($senha)){
			$encrypted_password = sha1($senha);
			$user-> cadastrar_usuario($nome,$telefone,$email,$encrypted_password);
			
		}else{
			echo json_encode("you must type both inputs");
		}
		
	}
?>