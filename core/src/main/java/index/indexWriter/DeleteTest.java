package index.indexWriter;

import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermInSetQuery;
import org.apache.lucene.util.BytesRef;
import java.io.IOException;
import java.util.Arrays;

public class DeleteTest {
    private static void deleteDocuments() throws IOException {
        IndexWriter indexWriter = IndexWriterUtil.getIndexWriter(IndexWriterUtil.bookPath);
        Term likeBasketball = new Term(IndexWriterUtil.LIKE, "basketball");
        Term likeReading = new Term(IndexWriterUtil.LIKE, "reading");
//        delete by terms, 直接删除包含exact terms的docs.
//        long l1 = indexWriter.deleteDocuments(likeBasketball, likeReading);
        Query likeQuery = new TermInSetQuery(IndexWriterUtil.LIKE, new BytesRef("basketball"), new BytesRef("likeReading"));
        Query propertyQuery = new TermInSetQuery(IndexWriterUtil.PROPERTY, new BytesRef("wife"), new BytesRef("child"));
//      delete by queries, 走query流程，PhraseQuery等会analyze, normalize.
//        long l2 = indexWriter.deleteDocuments(likeQuery, propertyQuery);

        Document doc1 = new Document();
        doc1.add(new IntPoint("age", 20));
        doc1.add(new StoredField("age.store", 20));
        doc1.add(new SortedNumericDocValuesField("age.value", 20));
        doc1.add(new TextField("property", "car, house, dog, wife, children, parent, bike, motobike", Field.Store.YES));
        Document doc2 = new Document();
        doc2.add(new IntPoint("age", 22));
        doc2.add(new StoredField("age.store", 22));
        doc2.add(new SortedNumericDocValuesField("age.value", 22));
        doc2.add(new TextField("property", "car, house, dog, wife, children, parent, bike, motobike", Field.Store.YES));
        indexWriter.updateDocuments(likeReading, Arrays.asList(doc1, doc2));
        indexWriter.flush();
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        System.out.println();
        deleteDocuments();
    }
}
