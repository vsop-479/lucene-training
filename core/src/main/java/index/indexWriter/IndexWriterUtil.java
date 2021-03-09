package index.indexWriter;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IndexWriterUtil {
    public static final String bookPath = "/Users/zhouhui155/es0/data/indices/bfXeh4L2RJCyFRVyxqXG3Q/0/index";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String LIKE = "like";
    public static final String PROPERTY = "property";

    public static IndexWriter getIndexWriter(String indexPath) throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Path path = Path.of(indexPath);
//        Path path = Files.createTempDirectory(indexPath);
        FSDirectory directory = FSDirectory.open(path);
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        indexWriterConfig.setSoftDeletesField("__soft_deletes");

        return new IndexWriter(directory, indexWriterConfig);
    }
}
