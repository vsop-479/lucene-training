package index.token;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.PayloadAttribute;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;

public class MyFilter extends TokenFilter {
    private PayloadAttribute attr;

    protected MyFilter(TokenStream input) {
        super(input);
    }


    @Override
    public final boolean incrementToken() throws IOException {
        if (input.incrementToken()) {
            attr.setPayload(new BytesRef("payload"));

        } else {
            attr.setPayload(null);
        }
        return input.incrementToken();
    }
}
