<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>

<@c.page>
    <@h.page/>

    <div class="container mt-5">
        <div class="row justify-content-center align-items-center">
            <form class="text-center border border-light p-5 col-4" action="" method="post">
                <label for="text">Create owner</label>
                <input type="text" class="form-control mb-4 ${(usernameError??)?string('is-invalid', '')}"
                       <#if owner?? && owner.username??>
                           value="${owner.username!}"
                       </#if>
                       placeholder="Username" name="username" required
                       pattern="^[А-ЯА-яA-Za-z0-9]{1,15}$">
                <span class=" invalid-feedback" id="form__error">Example name: User, user20</span>
                <button class="btn btn-outline-info btn-rounded btn-block my-4 waves-effect z-depth-0" type="submit">
                    Save
                </button>
                <#if message??>
                    <div class="alert alert-danger" role="alert">
                        ${message}
                    </div>
                </#if>
            </form>
        </div>
    </div>

</@c.page>