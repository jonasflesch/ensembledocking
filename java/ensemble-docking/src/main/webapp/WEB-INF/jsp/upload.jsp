<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>
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
                    <li class="active"><a href="#"><spring:message code="menu.home" /></a></li>
                    <li><a href="#about"><spring:message code="menu.aboutus" /></a></li>
                    <li><a href="#contact"><spring:message code="menu.contact" /></a></li>
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
                <p> <spring:message code="upload.description" /> <a href="https://github.com/jonasflesch/ensembledocking">Github</a></p>
            </div>

            <div class="page-header">
                <h1><spring:message code="upload.file.selection" /></h1>
            </div>

            <div class="form-group">
                <label for="fileLigand"><spring:message code="upload.file.ligand" /></label>
                <input type="file" name="fileLigand" id="fileLigand" >
                <p class="help-block"><spring:message code="upload.file.select.ligand" /></p>
            </div>
            <div class="form-group">
                <label for="fileReceptor"><spring:message code="upload.file.receptor" /></label>
                <input type="file" name="fileReceptor" id="fileReceptor" />
                <p class="help-block"><spring:message code="upload.file.select.receptor" /></p>
            </div>

            <div class="page-header">
                <h1><spring:message code="upload.parameters" /></h1>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="molecularDynamicsSteps"><spring:message code="upload.moleculardynamics.steps" /></label>
                        <input type="number" class="form-control" id="molecularDynamicsSteps" name="molecularDynamicsSteps" placeholder="<spring:message code="upload.moleculardynamics.steps" />" value="50000">
                        <p class="help-block"><spring:message code="upload.moleculardynamics.steps.help" /></p>
                    </div>
                    <div class="form-group">
                        <label for="molecularDynamicsOut"><spring:message code="upload.moleculardynamics.outputeach" /></label>
                        <input type="number" class="form-control" id="molecularDynamicsOut" name="molecularDynamicsOut" placeholder="<spring:message code="upload.moleculardynamics.outputeach" />" value="50">
                        <p class="help-block"><spring:message code="upload.moleculardynamics.outputeach.help" /></p>
                    </div>
                </div>
            </div>

            <p>
                <input type="submit" value="<spring:message code="upload.start.docking" />"> <spring:message code="upload.start.docking.description" />
            </p>

        </div>
    </form>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>