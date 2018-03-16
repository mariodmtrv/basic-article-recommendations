import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArticleProcessorTest {
    @Test
    public void givenShortTextTokenizesIt() {
        ArticleProcessor processor = new ArticleProcessor();
        String input = "The quick brown fox jumps over the lazy dog near the bank of the river";
        List<String> tokens = processor.tokenizeString(input);
        assertEquals(tokens.size(), 10);
    }
}
