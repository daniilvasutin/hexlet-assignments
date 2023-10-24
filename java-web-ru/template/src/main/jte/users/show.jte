@import exercise.dto.users.UserPage
@param UserPage page

<html>
<head>
    <title>Хекслет</title>
</head>
<body>
        <div>
            <p>its ok</p>
            <p>${page.getUser().getId()} ${page.getUser().getFirstName()} ${page.getUser().getLastName()} ${page.getUser().getEmail()}</p>
        </div>
</body>
</html>
