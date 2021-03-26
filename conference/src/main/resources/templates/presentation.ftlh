<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring />
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<@spring.bind "PresentationForm"/>
<form action="" method="post">
    Name: <br>
    <@spring.formInput "PresentationForm.name"/>
    <@spring.showErrors "<br>"/> <br> <br>
    <input type="submit" value="Submit">
</form>
        <#list presentations as presentation>
                    <form method="post" action="/presentation/${presentation.getId()}/update">
                        <input type="text" name="name" value="${presentation.getName()}"> <br>
                        <button type="submit">Edit</button>
                    </form>
                    <form method="post" action="/presentation/${presentation.getId()}/presenter">
                        <button type="submit">Become a presenter</button>
                    </form>
                    <form method="post" action="/presentation/${presentation.getId()}/delete">
                        <button type="submit">Delete</button>
                    </form> <br>
        </#list>

<a href="/">Main</a>
<a href="/schedule">Schedule</a>
</body>
</html>