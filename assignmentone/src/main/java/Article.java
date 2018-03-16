public class Article {
    private int articleId;
    private String articleText;
    private String category;

    public Article(int articleId, String articleText, String category) {
        this.articleId = articleId;
        this.articleText = articleText;
        this.category = category;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
