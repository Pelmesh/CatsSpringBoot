<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>

<@c.page>
    <@h.page/>

    <div class="container mt-3">
        <table class="table table-sm mt-3 border">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Father</th>
                <th scope="col">Mother</th>
                <th scope="col">Owner</th>
                <th scope="col">Gender</th>
                <th scope="col">Color</th>
                <th scope="col">Age</th>
                <th scope="col">&nbsp;</th>
                <th scope="col">&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <#list catList as cat>
                <tr>
                    <th scope="row">${cat.id}</th>
                    <td>${cat.name!}</td>
                    <td>
                        <#if cat.catFather??>
                            ${cat.catFather.id!}
                        </#if>
                     </td>
                    <td>
                        <#if cat.catMother??>
                            ${cat.catMother.id!}
                        </#if>
                    </td>
                    <td>
                        <#if cat.owner??>
                            ${cat.owner.username!}
                        </#if>
                    </td>
                    <td>${cat.gender!}</td>
                    <td>${cat.color!}</td>
                    <td>${cat.age!}</td>
                    <#if userAuth??>
                        <td><a href="/cat/create/${cat.id}" class="btn btn-secondary">Edit</a></td>
                    </#if>
                    <td><a href="/cat/${cat.id}" class="btn btn-secondary">Info</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

</@c.page>