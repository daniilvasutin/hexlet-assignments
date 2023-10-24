@import exercise.dto.users.UsersPage
@param UsersPage page

<html>
    <head>
        <title>Хекслет</title>
    </head>
    <body>
        @if(page.getUsers().isEmpty())
            <p>Пока не добавлено ни одного пользовтаеля</p>
        @else
            @for(var user : page.getUsers())
               <div>
                   <h2><a href="/users/${user.getId()}">${user.getId()}</a></h2>
                   <p>${user.getId()} ${user.getFirstName()} ${user.getLastName()} ${user.getEmail()}</p>
               </div>
            @endfor
        @endif
    </body>
</html>
