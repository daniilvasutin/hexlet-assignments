@import exercise.dto.articles.NewArticlePage
@param NewArticlePage page

@template.layout.page(
content = @`
        @if(page.getErrors() != null)
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif

        <form action="/articles" method="post" class="table table-striped">
            <div>
                <label>
                    Название
                    <input type="text" name="title" value="${page.getTitle()}" />
                </label>
            </div>
            <div>
                <label>
                    Контент
                    <input type="textarea" name="content" value="${page.getContent()}" />
                </label>
            </div>
            <input type="submit" value="Создать статью" />
        </form>
    `
)