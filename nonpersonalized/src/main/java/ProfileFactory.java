import java.util.Random;

public class ProfileFactory {
    private static int lastUserId = 0;
    private ArticlesPool articlesPool;

    private Random random = new Random(Constants.RANDOM_SEED);

    public ProfileFactory(ArticlesPool articlesPool) {
        this.articlesPool = articlesPool;
    }

    /**
     * Generates a user profile assuming a distribution where half of the articles are in one category,
     * 25% in a second one and the rest is random
     * to ensure some level of predictability for the recommendations
     *
     * @return
     */
    public UserProfile generateUserProfile() {
        int articlesForUserCount = Constants.USER_MIN_ARTICLES_COUNT +
            random.nextInt(Constants.USER_MAX_ARTICLES_COUNT - Constants.USER_MIN_ARTICLES_COUNT);
        UserProfile profile = new UserProfile(lastUserId++);
        String firstCategoryName = articlesPool.getRandomCategory();
        String secondCategoryName = articlesPool.getRandomCategory();
        for (int ind = 0; ind < articlesForUserCount; ind++) {
            int articleId = selectArticle(firstCategoryName, secondCategoryName);
            profile.addProfileArticle(articleId);
        }
        return profile;
    }

    private int selectArticle(String firstCategoryName, String secondCategoryName) {
        float randomProbability = random.nextFloat();
        int resultArticleId;
        if (randomProbability < Constants.FIRST_GROUP_PROBABILITY) {
            resultArticleId = articlesPool.getRandomArticleIdForCategory(firstCategoryName);
        } else if (Constants.FIRST_GROUP_PROBABILITY < randomProbability
            && randomProbability < Constants.FIRST_GROUP_PROBABILITY + Constants.SECOND_GROUP_PROBABILITY
            ) {
            resultArticleId = articlesPool.getRandomArticleIdForCategory(secondCategoryName);
        } else {
            String categoryName = articlesPool.getRandomCategory();
            resultArticleId = articlesPool.getRandomArticleIdForCategory(categoryName);
        }
        return resultArticleId;
    }
}
