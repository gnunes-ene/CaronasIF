
  <?php
  // inicia uma sessao
  @session_start();

  $email = filter_input(INPUT_POST, "email");
  $senha = filter_input(INPUT_POST, "senha");

 // if($email == "teste" && $senha == "123")
 // {
 //   echo '1';

 // }else{
 //   echo '0';
 // }

    
  $mysqli = new mysqli("mysql.hostinger.com.br", "u766469038_iftm", "005515", "u766469038_iftm");
  $encrypted_password = sha1($senha);

  $result = mysqli_query($mysqli, "select * from user where email = '".$email."' and senha = '".$encrypted_password."'");

  if($data = mysqli_fetch_array($result)){
  	// armazena o user na sessao
  	$_SESSION['user'] = $data;
    $id_user = $_SESSION['user']['id'];
    echo $id_user; 
    //echo $id_user;
    //session_destroy(); // para fazer o logout
  }
?>
    