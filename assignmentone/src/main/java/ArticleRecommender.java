import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ArticleRecommender {
    public void recommendArticles(ArticlesPool articlesPool, UserProfile profile) {
        PriorityQueue<RecommendationCandidate> selection = new PriorityQueue<>();
        Map<Integer, Article> articlesDatabase = articlesPool.getArticlesPool();
        List<Integer> profileArticles = profile.getProfileArticles();
        int preferredRecommendationsSize = (int) (profileArticles.size() *
            Constants.RECOMMENDATIONS_PROPORTION);
        for (Article candidate : articlesDatabase.values()) {
            RecommendationCandidate rc = createRecommendationCandidate(articlesPool,
                candidate, profileArticles);
            selection.add(rc);
            if (selection.size() > preferredRecommendationsSize) {
                selection.poll();
            }
        }
        List<Integer> resultRecommendations = selection.stream()
            .map(candidate -> candidate.getArticleId()).collect(Collectors.toList());
        profile.setRecommendations(resultRecommendations);
    }

    private RecommendationCandidate createRecommendationCandidate
        (ArticlesPool articlesPool,
         Article candidate, List<Integer> profileArticles) {
        double score = Double.POSITIVE_INFINITY;
        for (Integer articleId : profileArticles) {
            if (articleId.equals(candidate.getArticleId())) {
                continue;
            }
            Article profileArticle = articlesPool.getArticleById(articleId);
            score += compareArticles(candidate, profileArticle);
        }
        score /= profileArticles.size();
        RecommendationCandidate recommendationCandidate =
            new RecommendationCandidate(candidate.getArticleId(), score);
        return recommendationCandidate;
    }

    private double compareArticles(Article candidate, Article existingPreference) {
        try {
            return CosineDocumentSimilarity.getCosineSimilarity(
                candidate.getArticleText(), existingPreference.getArticleText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Double.POSITIVE_INFINITY;
    }
}
