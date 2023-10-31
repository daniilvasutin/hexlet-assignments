@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h1>Все статьи</h1>

<%--            <div class="mb-3">--%>
<%--                <a href="/articles/new">Создать новую статью</a>--%>
<%--            </div>--%>

            <table class="table table-striped">
                @for(var article : page.getPosts())
                    <tr>
                        <td>
                            <a href="/posts/${article.getId()}">${article.getName()}</a>
                        </td>
                        <td>
                            ${article.getBody()}
                        </td>
                    </tr>
                @endfor
            </table>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="/posts?page=${page.getNumPage()-1}">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="/posts?page=${page.getNumPage()+1}">Next</a></li>
                </ul>
            </nav>
        </main>
    </div>
`
)

