import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

public class ArticleRecommenderTest {
    private ArticlesPool articlesPool;
    private ProfileFactory profileFactory;
    private List<UserProfile> userProfiles;
    private ArticleRecommender articleRecommender;
    private int usersCount = 50;
    private int articlesCount = 100;

    @Before
    public void setUp() {
        articlesPool = new ArticlesPool();
        articlesPool.fillArticlesPool(Constants.DATASET_PATH, articlesCount);
        IntStream.of(usersCount).forEach(i -> userProfiles.add(profileFactory.generateUserProfile()));
        articleRecommender = new ArticleRecommender();
    }

    @Test
    public void generateRecommendationsForSetOfUsers() {
        for (UserProfile userProfile : userProfiles) {
            articleRecommender.recommendArticles(articlesPool, userProfile);
        }

    }
}
