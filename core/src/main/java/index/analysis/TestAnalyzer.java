package index.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.*;

import java.io.IOException;
import java.io.StringReader;

public class TestAnalyzer {
    public static void test() throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("myfield", new StringReader("some text goes here some"));
        OffsetAttribute offsetAttribute = tokenStream.getAttribute(OffsetAttribute.class);
        TermToBytesRefAttribute termToBytesRefAttribute = tokenStream.getAttribute(TermToBytesRefAttribute.class);
        TermFrequencyAttribute termFrequencyAttribute = tokenStream.getAttribute(TermFrequencyAttribute.class);
        PositionLengthAttribute positionLengthAttribute = tokenStream.getAttribute(PositionLengthAttribute.class);
        PositionIncrementAttribute positionIncrementAttribute = tokenStream.getAttribute(PositionIncrementAttribute.class);
        try {
//            reset this stream to the beginning.
            tokenStream.reset();
            while (tokenStream.incrementToken()){
                System.out.println("token: " + tokenStream.reflectAsString(true));
                System.out.println("term: " + termToBytesRefAttribute);
                System.out.println("termFreq: " + termFrequencyAttribute.getTermFrequency());
                System.out.println("positionIncrement: " + positionIncrementAttribute.getPositionIncrement());
                System.out.println("positionLength: " + positionLengthAttribute.getPositionLength());
                System.out.println("startOffset: " + offsetAttribute.startOffset());
                System.out.println("endOffset: " + offsetAttribute.endOffset());
            }
            tokenStream.end();
        }finally {
            tokenStream.close();
        }
    }

    public static void main(String[] args) throws IOException {
        test();
    }
}
