import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ProfileFactoryTest {
    private ArticlesPool articlesPool;
    private ProfileFactory profileFactory;

    @Before
    public void setUp() {
        articlesPool = new ArticlesPool();
        articlesPool.fillArticlesPool(Constants.DATASET_PATH, Constants.TOTAL_ARTICLES_COUNT);
        profileFactory = new ProfileFactory(articlesPool);
    }

    @Test
    public void generatesUserProfileCorrectly() {
        UserProfile profile = profileFactory.generateUserProfile();
        assertTrue(profile.getProfileArticles().size() > 0);
    }
}
