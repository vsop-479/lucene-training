package util;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LuceneUtil {
    public static IndexWriter getIndexWriter(String path) throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Path indexPath = Files.createTempDirectory(path);
        FSDirectory directory = FSDirectory.open(indexPath);

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        return new IndexWriter(directory, indexWriterConfig);
    }

}
