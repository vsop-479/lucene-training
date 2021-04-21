package index.indexSearcher;

import index.indexWriter.IndexWriterUtil;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.SearcherManager;

import java.io.IOException;

public class TestSearcherManager {
    public static SearcherManager getSearcherManager() throws IOException {
        IndexWriter indexWriter = IndexWriterUtil.getIndexWriter(IndexWriterUtil.bookPath);
        return new SearcherManager(indexWriter, false, false, null);
    }

    public static void search() throws IOException {
        SearcherManager searcherManager = getSearcherManager();
        IndexSearcher indexSearcher = searcherManager.acquire();
        try{
//            使用searcher中的readers, visit document.
//            es的fetch使用query阶段的searchContext中的readers, visit document.
            indexSearcher.doc(1);

        }finally {
            searcherManager.release(indexSearcher);
        }
    }

}
