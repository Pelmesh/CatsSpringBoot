<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>

<@c.page>
    <@h.page/>

<div class="container mt-3 col-4">
    <table class="table table-sm mt-3 border">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Username</th>
            <th scope="col">&nbsp;</th>
        </tr>
        </thead>
        <tbody>
        <#list ownerList as owner>
            <tr>
                <th scope="row">${owner.id}</th>
                <td>${owner.username}</td>
                <td><a href="/owners/${owner.id}" class="btn btn-secondary">Info</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

</@c.page>