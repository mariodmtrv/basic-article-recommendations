import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class ArticlesPool {
    private Random random = new Random(Constants.RANDOM_SEED);
    private Map<String, List<Integer>> articlesForCategory = new HashMap<>();
    private Map<Integer, Article> articlesPool = new HashMap<>();

    //used for limiting articles count to quickly test during development
    private int poolSize = 0;

    public String getRandomCategory() {
        Set<String> categories = articlesForCategory.keySet();
        int size = categories.size();
        int item = random.nextInt(size);
        int i = 0;
        for (String categoryName : categories) {
            if (i == item) {
                return categoryName;
            }
            i++;
        }
        return null;
    }

    public void addArticleToPool(String category, int articleId, String articleText) {
        Article article = new Article(articleId, articleText, category);
        List<Integer> articlesForCurrentCategory = articlesForCategory.get(category);
        if (articlesForCurrentCategory == null) {
            articlesForCurrentCategory = new ArrayList<>();
        }
        articlesForCurrentCategory.add(articleId);
        articlesForCategory.put(category, articlesForCurrentCategory);
        articlesPool.put(articleId, article);
        poolSize++;
    }

    public Article getRandomArticleForCategory(String category) {
        int selectedArticleId = getRandomArticleIdForCategory(category);
        return getArticleById(selectedArticleId);
    }

    public int getRandomArticleIdForCategory(String category) {
        List<Integer> articleIds = articlesForCategory.get(category);
        int selectedId = random.nextInt(articleIds.size());
        int selectedArticleId = articleIds.get(selectedId);
        return selectedArticleId;
    }

    public Article getArticleById(Integer articleId) {
        return articlesPool.get(articleId);
    }

    public void fillArticlesPool(String rootPath, int articlesCount) {
        File rootDirectory = new File(rootPath);
        String[] directories = rootDirectory.list((current, name) -> new File(current, name).isDirectory());

        for (String directoryName : directories) {
            extractArticlesInDirectory(rootPath, directoryName, articlesCount);

        }
    }

    private void extractArticlesInDirectory(String rootPath, String directoryName, int maxArticlesCount) {
        String directoryPath = rootPath + File.separator + directoryName;
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (poolSize > maxArticlesCount) {
                break;
            }
            try {
                String content = new Scanner(file).useDelimiter("\\Z").next();
                Integer articleId = Integer.parseInt(file.getName());
                this.addArticleToPool(directoryName, articleId, content);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
