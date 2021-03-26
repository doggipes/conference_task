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
<@spring.bind "roles" />
<@spring.bind "RoleForm" />
<#list users as user>
    <h3>${user.getId()}  ${user.getName()}  ${user.getRole()}</h3>
    <form method="post" action="/users/update/${user.getId()}">
        <input type="text" name="name" placeholder="Name"> <br>
        <button type="submit">Update</button>
    </form>

    <form method="post" action="/users/role/${user.getId()}">
        <@spring.formSingleSelect path="RoleForm.role" options = roles/>
        <button type="submit">Change role</button>
    </form>

    <form method="post" action="/users/delete/${user.getId()}">
        <button type="submit">Delete</button>
    </form> <br>
</#list>
</body>
</html>