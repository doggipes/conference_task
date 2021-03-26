<!doctype html>
<html lang="en" xmlns:form="http://www.w3.org/1999/html">
<#import "spring.ftl" as spring />
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<@spring.bind "SignUpForm"/>
<form action="" method="post">
    Name: <br>
    <@spring.formInput path = "SignUpForm.name"/>
    <br><br>
    Surname: <br>
    <@spring.formInput "SignUpForm.surname"/>
    <br><br>
    Email: <br>
    <@spring.formInput "SignUpForm.email"/>
    <br><br>
    Password: <br>
    <@spring.formInput  path="SignUpForm.password" fieldType="password"/> <br>
    <input type="submit" value="Submit">
</form> <br>
<a href="/login">Login</a>
</body>
</html>