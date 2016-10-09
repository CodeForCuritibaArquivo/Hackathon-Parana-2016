<?php
/**
 * Created by PhpStorm.
 * User: Daniel
 * Date: 09/10/2016
 * Time: 03:55
 */

$id = intval($_GET['lastTimeID']);
$jsonData = chatClass::getRestChatLines($id);
print $jsonData;