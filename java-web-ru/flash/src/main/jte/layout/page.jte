@import exercise.dto.BasePage
@import exercise.util.NamedRoutes
@import gg.jte.Content
@param Content content
@param BasePage page = null

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/posts">Posts</a>
            </li>
        </ul>
    </div>
</nav>

@if(page != null && page.getFlash() != null)
        ${page.getFlash()}
@endif


<div class="mx-auto p-4 py-md-5">
    ${content}
</div>
</body>
</html>
