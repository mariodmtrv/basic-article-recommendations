import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ArticlesPoolTest {
    private ArticlesPool pool = new ArticlesPool();

    @Before
    public void setUp() {
        pool.fillArticlesPool(Constants.DATASET_PATH, Constants.TOTAL_ARTICLES_COUNT);
    }

    @Test
    public void readsDatasetCorrectlyAndCreatesArticlesPool() {
        Article article = pool.getArticleById(51060);
        assertNotNull(article);
    }

    @Test
    public void retrievesRandomCategoryAndArticle() {
        String actualCategory = pool.getRandomCategory();
        String expectedCategory = "alt.atheism";
        assertEquals(actualCategory, expectedCategory);
        Article actualArticle = pool.getRandomArticleForCategory(expectedCategory);
        assertEquals(actualArticle.getArticleId(), 49960);
    }
}
