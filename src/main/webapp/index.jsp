<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>BLOG AI</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="stylesheet" href="public/assets/css/graindashboard.css">
</head>

<body>
<main class="main">
    <div class="content">
        <div class="container-fluid pb-5">
            <div class="row justify-content-md-center">
                <div class="card-wrapper col-12 col-md-4 mt-5">
                    <div class="brand text-center mb-3">
                        <img src="public/assets/img/logo.png">
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Login</h4>
                            <form action="<%=request.getContextPath()%>/Connecter" method="post">
                            <div class="form-group">
                                    <label >Nom</label>
                                    <input type="text" class="form-control" name="nom" value="Administrateur">
                                </div>

                                <div class="form-group">
                                    <label for="password">Mot de passe</label>
                                    <input id="password" type="password" class="form-control" name="mdp" value="Administrateur">
                                </div>
                                <button type="submit" class="btn btn-secondary">Se connecter</button>
                            </form>
                        </div>
                    </div>
                    <footer class="footer mt-3">
                        <div class="container-fluid">
                            <div class="footer-content text-center small">
                                <span class="text-muted">&copy; 2023 Graindashboard. Jennifer.</span>
                            </div>
                        </div>
                    </footer>
                </div>
            </div>
        </div>
    </div>
</main>

<script src="public/assets/js/graindashboard.js"></script>
<script src="public/assets/js/graindashboard.vendor.js"></script>
</body>
</html>