package exercise.domain;

import io.ebean.Model;

import javax.persistence.*;

import io.ebean.annotation.NotNull;

@Entity
public class Article extends Model {

    @Id
    private long id;

    private String title;

    @Lob
    private String body;

    @ManyToOne
    @NotNull
    private Category category;

    // BEGIN

    public Article(String title, String body, Category category) {
        this.title = title;
        this.body = body;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Category getCategory() {
        return category;
    }

    // END
}
