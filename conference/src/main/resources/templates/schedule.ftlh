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
<@spring.bind "rooms" />
<@spring.bind "presentations" />
<@spring.bind "ScheduleForm"/>
<form action="/schedule" method="post">
    Room: <br>
    <@spring.formSingleSelect  path = "ScheduleForm.room" options = rooms/>
    <@spring.showErrors "<br>"/>
    <br><br>
    Presentation: <br>
    <@spring.formSingleSelect  path = "ScheduleForm.presentation" options = presentations/>
    <@spring.showErrors "<br>"/>
    <br><br>
    Start time: <br>
    <@spring.formInput  path = "ScheduleForm.startDate"  fieldType="datetime-local"/>
    <@spring.showErrors "<br>"/>
    <br><br>
    End time: <br>
    <@spring.formInput  path = "ScheduleForm.endDate"  fieldType="datetime-local"/>
    <@spring.showErrors "<br>"/>
    <input type="submit" value="Submit">
</form>
<#if error??>
        ${error}
</#if>
    <a href="/">Main</a>
    <a href="/presentation">Presentation</a>
</body>
</html>