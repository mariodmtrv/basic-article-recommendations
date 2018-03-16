import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ArticleProcessor {
        public List<String> tokenizeString(String str) {
          Analyzer analyzer = new StandardAnalyzer();
            List<String> result = new ArrayList<>();
            try {
                TokenStream stream  = analyzer.tokenStream(null, new StringReader(str));
                stream.reset();
                while (stream.incrementToken()) {
                    result.add(stream.getAttribute(CharTermAttribute.class).toString());
                }
            } catch (IOException e) {
                // not thrown b/c we're using a string reader...
                throw new RuntimeException(e);
            }
            return result;
        }

}
