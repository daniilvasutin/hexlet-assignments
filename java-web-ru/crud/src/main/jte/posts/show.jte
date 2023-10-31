@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h1>Вы открыли эту статью</h1>

            <%--            <div class="mb-3">--%>
            <%--                <a href="/articles/new">Создать новую статью</a>--%>
            <%--            </div>--%>

            <table class="table table-striped">
                    <tr>
                        <td>
                            ${page.getPost().getId()}
                        </td>
                        <td>
                            ${page.getPost().getName()}
                        </td>
                        <td>
                            ${page.getPost().getBody()}
                        </td>
                    </tr>
            </table>
            <div class="mb-3">
                <a href="/posts">Вернуться к списку всех постов</a>
            </div>
<%--            <table class="table table-striped">--%>
<%--                @for(var article : page.getPosts())--%>
<%--                    <tr>--%>
<%--                        <td>--%>
<%--                            ${article.getName()}--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            ${article.getBody()}--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                @endfor--%>
<%--            </table>--%>
        </main>
    </div>
`
)


