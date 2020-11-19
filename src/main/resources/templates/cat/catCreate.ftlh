<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>

<@c.page>
    <@h.page/>

    <div class="container col-3 mt-3">
        <#if messages??>
            <div class="alert alert-danger" role="alert">
                ${message}
            </div>
        </#if>
        <form action="" method="post" accept-charset="UTF-8">
            <label>
                <#if cat??>
                    Id: ${cat.id}
                <#else>
                    Create cat
                </#if>
            </label>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" class="form-control" value="<#if cat??>${cat.name!}</#if>"
                       required
                       pattern="^[А-ЯА-яA-Za-z0-9]{1,15}$"
                       name="name">
            </div>
            <div class="form-group">
                <label for="catFather">Father</label>
                <label>
                    <select id="catFather" name="catFather" class="form-control">
                        <option selected value="">No father</option>
                        <#list fatherList as catF>
                            <#if cat?? && cat.catFather?? && cat.catFather.id == catF.id>
                                <option selected value="${catF.id}">${catF.name}(id:${catF.id})</option>
                            <#elseif cat??>
                                <#if cat.id != catF.id>
                                    <option value="${catF.id}">${catF.name}(id:${catF.id})</option>
                                </#if>
                            <#else>
                                <option value="${catF.id}">${catF.name}(id:${catF.id})</option>
                            </#if>
                        </#list>
                    </select>
                </label>
            </div>
            <div class="form-group">
                <label for="catMother">Mother</label>
                <label>
                    <select id="catMother" name="catMother" class="form-control">
                        <option value="">No mother</option>
                        <#list motherList as catM>
                            <#if cat?? && cat.catMother?? && cat.catMother.id == catM.id>
                                <option selected value="${catM.id}">${catM.name}(id:${catM.id})</option>
                            <#elseif cat??>
                                <#if cat.id != catM.id>
                                    <option value="${catM.id}">${catM.name}(id:${catM.id})</option>
                                </#if>
                            <#else>
                                <option value="${catM.id}">${catM.name}(id:${catM.id})</option>
                            </#if>
                        </#list>
                    </select>
                </label>
            </div>
            <div class="form-group">
                <label for="gender">Gender</label>
                <label>
                    <select id="gender" name="gender" class="form-control">
                        <#if cat??>
                            <#if cat.gender == 'M'>
                                <option selected value="M">Male</option>
                                <option value="F">Female</option>
                                <option value="">No gender</option>
                            <#elseif cat.gender == 'F'>
                                <option value="M">Male</option>
                                <option selected value="F">Female</option>
                                <option value="">No gender</option>
                            <#else>
                                <option value="M">Male</option>
                                <option value="F">Female</option>
                                <option selected value="">No gender</option>
                            </#if>
                        <#else>
                            <option value="M">Male</option>
                            <option value="F">Female</option>
                            <option selected value="">No gender</option>
                        </#if>
                    </select>
                </label>
            </div>
            <div class="form-group">
                <label for="owner">Owner</label>
                <label>
                    <select id="owner" name="owner" class="form-control">
                        <option value="">No owner</option>
                        <#list ownerList as owner>
                            <#if cat?? && cat.owner?? && cat.owner.id == owner.id>
                                <option selected value="${owner.id}">${owner.username}(id:${owner.id})</option>
                            <#else>
                                <option value="${owner.id}">${owner.username}(id:${owner.id})</option>
                            </#if>
                        </#list>
                    </select>
                </label>
            </div>
            <div class="form-group">
                <label for="color">Color</label>
                <input type="text" id="color" class="form-control" value="<#if cat??>${cat.color!}</#if>"
                       pattern="^[А-ЯА-яA-Za-z]{1,15}$"
                       name="color">
            </div>
            <div class="form-group">
                <label for="age">Age</label>
                <input type="number" id="age" class="form-control" value="<#if cat??>${cat.age!}"</#if> name="age"
                       min="-1" max="25">
            </div>
            <#if cat??>
                <input type="hidden" name="_method" value="PUT"/>
            </#if>
            <button type="submit" class="btn btn-success">Save</button>
            <a href="/" class="btn btn-secondary">Cancel</a>
        </form>
    </div>

</@c.page>