<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>

<@c.page>
    <@h.page/>

    <#if cat??>
        <div class="form-group container col-3 mt-5">
            <ul class="list-group">
                <li class="list-group-item">Id: ${cat.id}</li>
                <li class="list-group-item">Name: ${cat.name}</li>
                <li class="list-group-item">Father:
                    <#if cat.catFather??>
                        <a href="/cat/${cat.catFather.id}">
                            ${cat.catFather.name}(id:${cat.catFather.id})
                        </a>
                    </#if>
                </li>
                <li class="list-group-item">Mother:
                    <#if cat.catMother??>
                        <a href="/cat/${cat.catMother.id}">
                            ${cat.catMother.name}(id:${cat.catMother.id})
                        </a>
                    </#if>
                </li>
                <li class="list-group-item">Children:
                    <#list children as child>
                        <a href="/cat/${child.id}">${child.name}(id:${child.id})</a>
                    </#list>
                </li>
                <li class="list-group-item">Owner:
                    <#if cat.owner??>
                    <a href="/owners/${cat.owner.id}">${cat.owner.username}(${cat.owner.id})</a>
                    </#if>
                </li>
                <li class="list-group-item">Gender: ${cat.gender!}</li>
                <li class="list-group-item">Color: ${cat.color!}</li>
                <li class="list-group-item">Age: ${cat.age!}</li>
                <#if userAuth??>
                    <form method="post"  action="" class="text-center mt-3">
                        <input type="hidden" name="_method" value="DELETE"/>
                        <button type="submit" class="btn btn-success">Delete</button>
                    </form>
                </#if>
            </ul>
        </div>
    <#else>
        <div class="container text-center col-3">
            <div class="alert alert-danger container mt-5" role="alert">
                Сat not found
            </div>
            <a href="/" class="btn btn-secondary">Cancel</a>
        </div>
    </#if>

</@c.page>