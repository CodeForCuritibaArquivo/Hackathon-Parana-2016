<?php
/**
 * Created by PhpStorm.
 * User: Daniel
 * Date: 08/10/2016
 * Time: 19:10
 */

session_start();


?>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta http-equiv="content-type" content="text/html">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-COMPATIBLE" content="IE-edge">
        <meta name="viewport" content="widht=device-width, initial-scale-1">

        <title>Caps!</title>

        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/theme-style.css" rel="stylesheet">

    </head>
<body>
<div class="container">
    <div class="col-md-12 col-lg-6">
        <div class="panel">
            <div class="panel-heading">
                <div class="panel-control">
                    <div class="btn-group">
                        <button class="btn btn-default" type="button" data-toggle="collapse" data-target="#demo-cha-body"><i class="fa fa-chevron-down"></i></button>
                        <button type="button" class="btn btn-default" data-toggle="dropdown"><i class="fa fa-gear"></i></button>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li><a href="#">Disponivel</a> </li>
                            <li><a href="#">Disponivel</a> </li>
                            <li><a href="#">Disponivel</a> </li>
                            <li class="divider"></li>
                            <li><a id="demo-connect-chat" href="#" class="disabled-link" data-target="#demo-chat-body">Conectar</a> </li>
                            <li><a id="demo-disconnect-chat" href="#" data-target="#demo-chat-body">Desconectar</a> </li>
                        </ul>
                    </div>
                </div>
                <h3 class="panel-title">Caps</h3>
            </div>
            <div id="demo-chat-body" class="collapse in">
                <div class="nano has-scrollbar" style="height:380px">
                    <div class="nano-content pad-all" tabindex="0" style="right: -17px;">
                        <ul class="list-unstyled media-block">
                            <li class="mar-btm">
                                <div class="media-left">
                                    <img src="img/avatarCaps.png" class="img-circle img-sm" alt="Profile Picture">
                                </div>
                                <div class="media-body pad-hor">
                                    <div class="speech">
                                            <a href="#" class="media-heading">Caps</a>
                                        <p>Hey, você por acaso quer internet grátis?</p>
                                        <p class="speech-time">
                                            <i class="fa fa-clock-o fa-fw"></i>09:23AM
                                        </p>
                                    </div>
                                </div>
                            </li>
                            <li class="mar-btm">
                                <div class="media-right">
                                    <img src="http://bootdey.com/img/Content/avatar/avatar2.png" class="img-circle img-sm" alt="Profile Picture">
                                </div>
                                <div class="media-body pad-hor speech-right">
                                    <div class="speech">
                                        <a href="#" class="media-heading">Rick Astley</a>
                                        <p>Claro!</p>
                                        <p class="speech-time">
                                            <i class="fa fa-clock-o fa-fw"></i> 09:23AM
                                        </p>
                                    </div>
                                </div>
                            </li>
                            <li class="mar-btm">
                                <div class="media-left">
                                    <img src="img/avatarCaps.png" class="img-circle img-sm" alt="Profile Picture">
                                </div>
                                <div class="media-body pad-hor">
                                    <div class="speech">
                                        <a href="#" class="media-heading">Caps</a>
                                        <p>Legal! Você só precisa me responder algumas questões, preparado?</p>
                                        <p class="speech-time">
                                            <i class="fa fa-clock-o fa-fw"></i> 09:25
                                        </p>
                                    </div>
                                </div>
                            </li>
                            <li class="mar-btm">
                                <div class="media-right">
                                    <img src="http://bootdey.com/img/Content/avatar/avatar2.png" class="img-circle img-sm" alt="Profile Picture">
                                </div>
                                <div class="media-body pad-hor speech-right">
                                    <div class="speech">
                                        <a href="#" class="media-heading">Rick Astley</a>
                                        <p>Vamos nessa!</p>
                                        <p class="speech-time">
                                            <i class="fa fa-clock-o fa-fw"></i> 09:27
                                        </p>
                                    </div>
                                </div>
                            </li>
                            <li class="mar-btm">
                                <div class="media-left">
                                    <img src="img/avatarCaps.png" class="img-circle img-sm" alt="Profile Picture">
                                </div>
                                <div class="media-body pad-hor">
                                    <div class="speech">
                                        <a href="#" class="media-heading">Caps</a>
                                        <p>Para onde você está indo?</p>
                                        <p class="speech-time">
                                            <i class="fa fa-clock-o fa-fw"></i> 09:30
                                        </p>
                                    </div>
                                </div>
                            </li>
                            <li class="mar-btm">
                                <div class="media-right">
                                    <img src="http://bootdey.com/img/Content/avatar/avatar2.png" class="img-circle img-sm" alt="Profile Picture">
                                </div>
                                <div class="media-body pad-hor speech-right">
                                    <div class="speech">
                                        <a href="#" class="media-heading">Rick Astley</a>
                                        <p>Parque Barigui!</p>
                                        <p class="speech-time">
                                            <i class="fa fa-clock-o fa-fw"></i> 09:31
                                        </p>
                                    </div>
                                </div>
                            </li>
                            <li class="mar-btm">
                                <div class="media-left">
                                    <img src="img/avatarCaps.png" class="img-circle img-sm" alt="Profile Picture">
                                </div>
                                <div class="media-body pad-hor">
                                    <div class="speech">
                                        <a href="#" class="media-heading">Caps</a>
                                        <p>Wow! Adoro este parque, caso você encontre a minha amiga Capi, mande lembranças!</p>
                                        <p class="speech-time">
                                            <i class="fa fa-clock-o fa-fw"></i> 09:32
                                        </p>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="nano-pane"><div class="nano-slider" style="height: 141px; transform: translate(0px, 0px);"></div></div></div>

                <div class="panel-footer">
                    <div class="row">
                        <div class="col-xs-9">
                            <input type="text" placeholder="Digite aqui" id="chatInput" class="form-control chat-input">
                        </div>
                        <div class="col-xs-3">
                            <input type="button" class="btn btn-primary btn-block" value="Enviar" id="btnSend" />
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>

<script src="js/main.js"></script>


</body>
</html>