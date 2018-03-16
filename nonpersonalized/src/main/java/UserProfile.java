import java.util.ArrayList;
import java.util.List;

public class UserProfile {
    private int userId;
    private List<Integer> profileArticles;
    private List<Integer> recommendations;

    public UserProfile(int userId) {
        this.userId = userId;
        this.profileArticles = new ArrayList<>();
    }

    public UserProfile(int userId, List<Integer> profileArticles) {
        this.userId = userId;
        this.profileArticles = profileArticles;
    }

    public List<Integer> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Integer> recommendations) {
        this.recommendations = recommendations;
    }

    public List<Integer> getProfileArticles() {
        return profileArticles;
    }

    public void setProfileArticles(List<Integer> profileArticles) {
        this.profileArticles = profileArticles;
    }

    public void addProfileArticle(int articleId) {
        this.profileArticles.add(articleId);
    }
}
