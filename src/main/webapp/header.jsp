<%@taglib prefix="f" uri="http://www.slim3.org/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>LessOr100</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le styles -->
    <link rel="stylesheet" href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css">
    <style type="text/css">
        body {
            padding-top: 60px;
        }
    </style>

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="images/favicon.ico">
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="images/apple-touch-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="images/apple-touch-icon-114x114.png">
</head>

<body>

<div class="topbar">
    <div class="topbar-inner">
        <div class="container-fluid">
            <a class="brand" href="#">LessOr100</a>
            <ul class="nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
            <p class="pull-right">Logged in as <a href="#">username</a></p>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="sidebar">
        <div class="well">
            <h5>Servers</h5>
            <ul>
                <li><a href="${f:url('df/servers')}">Servers</a></li>
                <li><a href="#">Disk Free</a></li>
            </ul>
            <h5>Emails</h5>
            <ul>
                <li><a href="../email/incoming">Incoming</a></li>
                <li><a href="../email/outgoing">Outgoing</a></li>
            </ul>
        </div>
    </div>
    <div class="content">