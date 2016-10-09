<?php
/**
 * Created by PhpStorm.
 * User: Daniel
 * Date: 09/10/2016
 * Time: 03:55
 */
$headers= array('Accept: application/json','Content-Type: application/json');

$entrada = $_POST['text'];

$data = array();

$data["msg"] = $entrada;
$data["msg_type"] = "text";
$data["id"] = "null";
$data["network_name"] = "facebook";
$data["network_data"] = "openid";
$data["action"] = "inserir";
$data["action_data"] = "destino";


$jsonRequest = json_encode($data);

$ch = curl_init();

curl_setopt($ch, CURLOPT_URL,"http://bluebaby.mybluemix.net/chat2");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonRequest);
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

// in real life you should use something like:
// curl_setopt($ch, CURLOPT_POSTFIELDS,
//          http_build_query(array('postvar1' => 'value1')));

// receive server response ...
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

$server_output = curl_exec ($ch);

curl_close ($ch);

echo $server_output;

?>
