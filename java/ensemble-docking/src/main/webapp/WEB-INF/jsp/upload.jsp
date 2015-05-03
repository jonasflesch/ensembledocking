<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Ensemble Docking</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body role="document">

    <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Ensemble Docking</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Página Inicial</a></li>
                    <li><a href="#about">Sobre Nós</a></li>
                    <li><a href="#contact">Contato</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>

    <form method="POST" enctype="multipart/form-data"
          action="/upload">
        <div class="container theme-showcase" role="main">

            <!-- Main jumbotron for a primary marketing message or call to action -->
            <div class="jumbotron">
                <h1>Ensemble Docking</h1>
                <p>Através desta aplicação é possível realizar Ensemble Docking. Para contribuir, visite nossa página no <a href="https://github.com/jonasflesch/ensembledocking">Github</a></p>
            </div>

            <div class="page-header">
                <h1>Seleção de Arquivos</h1>
            </div>

            <div class="form-group">
                <label for="fileLigand">Arquivo PDB Ligante: </label>
                <input type="file" name="fileLigand" id="fileLigand" >
                <p class="help-block">Selecione o arquivo PDB na sua máquina.</p>
            </div>
            <div class="form-group">
                <label for="fileReceptor">Arquivo PDB Receptor: </label>
                <input type="file" name="fileReceptor" id="fileReceptor" />
                <p class="help-block">Selecione o arquivo PDB na sua máquina.</p>
            </div>

            <div class="page-header">
                <h1>Parâmetros</h1>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="molecularDynamicsSteps">Passos da dinâmica molecular</label>
                        <input type="number" class="form-control" id="molecularDynamicsSteps" name="molecularDynamicsSteps" placeholder="Passos da dinâmica molecular" value="50000">
                        <p class="help-block">O número de passos a serem executados na dinâmica molecular. Casa passo tem 0.002 picosegundos.</p>
                    </div>
                    <div class="form-group">
                        <label for="molecularDynamicsOut">Output a cada</label>
                        <input type="number" class="form-control" id="molecularDynamicsOut" name="molecularDynamicsOut" placeholder="Output a cada" value="50">
                        <p class="help-block">A cada quantos passos deve ser executada uma docagem.</p>
                    </div>
                </div>
            </div>

            <p>
                <input type="submit" value="Iniciar Docagem"> Este processo pode levar algumas horas dependendo dos parâmetros
            </p>

        </div>
    </form>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>