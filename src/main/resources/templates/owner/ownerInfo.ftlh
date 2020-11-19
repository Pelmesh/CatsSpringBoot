<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>

<@c.page>
    <@h.page/>

<#if owner??>
    <div class="form-group container col-3 mt-5">
        <ul class="list-group">
            <li class="list-group-item">Id: ${owner.id}</li>
            <li class="list-group-item">Name: ${owner.username}</li>
            <li class="list-group-item">Cats:
                <#list owner.cats as cat>
                    <a href="/cat/${cat.id}">${cat.name}(id=${cat.id})</a>
                </#list>
            </li>
        </ul>
    </div>
<#else>
    <div class="container text-center col-3">
        <div class="alert alert-danger container mt-5" role="alert">
            Owner not found
        </div>
        <a href="/owners" class="btn btn-secondary">Cancel</a>
    </div>
</#if>

</@c.page>