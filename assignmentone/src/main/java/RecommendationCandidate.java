public class RecommendationCandidate implements Comparable {
    private Integer articleId;
    private double score;

    public RecommendationCandidate(Integer articleId, double score) {
        this.articleId = articleId;
        this.score = score;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        RecommendationCandidate other = (RecommendationCandidate) o;

        if (this.articleId == other.articleId) {
            return 0;
        }

        return (int) ((this.score - other.score) * 100);
    }

}
