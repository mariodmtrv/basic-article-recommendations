public class Constants {
    /**
     * To ensure consistent results
     */
    public static final int RANDOM_SEED = 1750;

    /**
     * The percentage of articles that the user will receive as recommendations, based on the size of
     * their read articles
     */
    public static final float RECOMMENDATIONS_PROPORTION = 0.2F;

    public static final float FIRST_GROUP_PROBABILITY = 0.5F;
    public static final float SECOND_GROUP_PROBABILITY = 0.25F;
    public static final int USER_MIN_ARTICLES_COUNT = 5;
    public static final int USER_MAX_ARTICLES_COUNT = 50;
    public static final int TOTAL_ARTICLES_COUNT = 100;

    public static final String DATASET_PATH = "resources/20_newsgroups";
}
