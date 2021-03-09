package index.indexWriter;

import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocValueTest {
    private static void addDocValues() throws IOException {
        IndexWriter indexWriter = IndexWriterUtil.getIndexWriter(IndexWriterUtil.bookPath);
        Document doc1 = new Document();
        String name = "wang";
        doc1.add(new StringField("name", name, Field.Store.YES));
        doc1.add(new SortedDocValuesField("name.value", new BytesRef(name.getBytes())));
        String like = "basketball, reading, writing";
        doc1.add(new TextField("like", like.toString(), Field.Store.YES));
        int age = 15;
        doc1.add(new IntPoint("age", age));
        doc1.add(new StoredField("age.store", age));
        doc1.add(new SortedNumericDocValuesField("age.value", age));
        String property = "car, house, dog, wife, children, parent";
        doc1.add(new TextField("property", property, Field.Store.YES));

        Document doc2 = new Document();
        name = "zhang";
        doc2.add(new StringField("name", name, Field.Store.YES));
        doc2.add(new SortedDocValuesField("name.value", new BytesRef(name.getBytes())));
        like = "basketball, writing, music";
        doc1.add(new TextField("like", like.toString(), Field.Store.YES));
        age = 12;
        doc2.add(new IntPoint("age", age));
        doc2.add(new StoredField("age.store", age));
        doc2.add(new SortedNumericDocValuesField("age.value", age));
        property = "wife, children, parent, bike";
        doc2.add(new TextField("property", property, Field.Store.YES));

        Document doc3 = new Document();
        name = "song";
        doc3.add(new StringField("name", name, Field.Store.YES));
        doc3.add(new SortedDocValuesField("name.value", new BytesRef(name.getBytes())));
        like = "reading, writing, music";
        doc3.add(new TextField("like", like.toString(), Field.Store.YES));
        age = 12;
        doc3.add(new IntPoint("age", age));
        doc3.add(new StoredField("age.store", age));
        doc3.add(new SortedNumericDocValuesField("age.value", age));
        property = "wife, children, parent, bike, dog";
        doc3.add(new TextField("property", property, Field.Store.YES));

        List<Document> docs = new ArrayList<>();
        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);
        indexWriter.updateDocuments(null, docs);
        indexWriter.flush();
    }
}
