package model;

public class Vacancy {
    private String title;
    private String tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Vacancy(String title, String tags) {
        this.title = title;
        this.tags = tags;
    }

    public Vacancy() {
    }
}
