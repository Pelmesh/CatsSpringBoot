<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>

<@c.page>
    <@h.page/>
    <meta http-equiv="Refresh" content="5; url=/">
    <div class="container">
        <div class="alert alert-success text-center" role="alert">
            ${message}
        </div>
    </div>

</@c.page>