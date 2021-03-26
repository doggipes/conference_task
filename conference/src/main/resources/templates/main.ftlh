<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<dl>
<#list rooms as room>
    <dt> <h2>Room ${room.getName()} </dt>
        <#list schedules as schedule>
            <#if room.getName() == schedule.getRoom().getName()>
                <dd>
                    Name: ${schedule.getPresentation().getName()} &ensp; Time: ${schedule.getStartDate()?string["dd.MM.yyyy, HH:mm"]} - ${schedule.getEndDate()?string["dd.MM.yyyy, HH:mm"]}
                    <form method="post" action="/join/${schedule.getPresentation().getId()}">
                        <button type="submit">Join</button>
                    </form>
                </dd>
            </#if>
        </#list>
    <#else>
    <h2>No rooms
</#list>
</dl>
<#if error??>
    ${error}
</#if>
<#if presentationPage??>
    <a href="/presentation">Presentation</a>
</#if>
<#if schedulePage??>
    <a href="/schedule">Schedule</a>
</#if>
</body>
</html>