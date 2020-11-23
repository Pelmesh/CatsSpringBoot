<#macro page>
    <nav class="navbar navbar-expand-lg navbar-light bg-light mb-5">
        <a class="navbar-brand" href="/">
            <img src="https://svgsilh.com/svg/1775543.svg" width="30" height="30" alt="">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <#if userAuth??>
                    <li class="nav-item active">
                        <a class="nav-link" href="/cat/create">Create cat<span class="sr-only">(current)</span></a>
                    </li>
                </#if>
                <li class="nav-item active">
                    <a class="nav-link" href="/owners">Owners<span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
        <#if userAuth??>
            <a href="/logout" class="btn btn-outline-info btn-rounded waves-effect z-depth-0 my-2 my-sm-0 mr-1" type="submit">Logout</a>
        <#else>
            <a href="/registration" class="btn btn-outline-info btn-rounded waves-effect z-depth-0 my-2 my-sm-0 mr-1" type="submit">Sign Up</a>
            <a href="/login" class="btn btn-outline-info btn-rounded waves-effect z-depth-0 my-2 my-sm-0" type="submit">Sign In</a>
        </#if>
    </nav>
</#macro>

