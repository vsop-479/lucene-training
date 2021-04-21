package index.indexWriter;

import org.apache.lucene.document.*;
import org.apache.lucene.index.DocValuesType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.StringHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocValueTest {
    private static void addDocValues() throws IOException {
        IndexWriter indexWriter = IndexWriterUtil.getIndexWriter(IndexWriterUtil.bookPath);
        FieldType fType = new FieldType();
        fType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
        fType.setDocValuesType(DocValuesType.SORTED);
        fType.setStoreTermVectors(true);
        fType.setStoreTermVectorPositions(true);
        fType.setStoreTermVectorOffsets(true);
        fType.setStoreTermVectorPayloads(true);
        fType.setOmitNorms(true);
//        new Field();

        Document doc1 = new Document();
        String last = "wang";
        doc1.add(new StringField("last", last, Field.Store.YES));
        doc1.add(new SortedDocValuesField("last", new BytesRef(last.getBytes())));
        String name = "john wall";
        doc1.add(new TextField("name", name, Field.Store.YES));
        String like = "basketball, reading, writing";
        doc1.add(new TextField("like", like, Field.Store.YES));
        long age = 15;
        doc1.add(new LongPoint("age", age));
        doc1.add(new StoredField("age", age));
        doc1.add(new SortedNumericDocValuesField("age", age));
        String property = "car, house, dog, wife, children, parent";
        doc1.add(new TextField("property", property, Field.Store.YES));

        Document doc2 = new Document();
        last = "zhang";
        doc2.add(new StringField("last", last, Field.Store.YES));
        doc2.add(new SortedDocValuesField("last", new BytesRef(last.getBytes())));
        name = "lebron james";
        doc2.add(new TextField("name", name, Field.Store.YES));
        like = "basketball, writing, music";
        doc2.add(new TextField("like", like, Field.Store.YES));
        age = 12;
        doc2.add(new LongPoint("age", age));
        doc2.add(new StoredField("age", age));
        doc2.add(new SortedNumericDocValuesField("age", age));
        property = "wife, children, parent, bike";
        doc2.add(new TextField("property", property, Field.Store.YES));

        Document doc3 = new Document();
        last = "song";
        doc3.add(new StringField("last", last, Field.Store.YES));
        //doc3 has no last value.
//        doc3.add(new SortedDocValuesField("last", new BytesRef(last.getBytes())));
        name = "buss light year";
        doc3.add(new TextField("name", name, Field.Store.YES));
        like = "reading, writing, music";
        doc3.add(new TextField("like", like, Field.Store.YES));
        age = 19;
        doc3.add(new LongPoint("age", age));
        doc3.add(new StoredField("age", age));
        doc3.add(new SortedNumericDocValuesField("age", age));
        property = "wife, children, parent, bike, dog";
        doc3.add(new TextField("property", property, Field.Store.YES));

        Document doc4 = new Document();
        last = "long";
        doc4.add(new StringField("last", last, Field.Store.YES));
        doc4.add(new SortedDocValuesField("last", new BytesRef(last.getBytes())));
        name = "buss light year";
        doc4.add(new TextField("name", name, Field.Store.YES));
        like = "reading, writing, music";
        doc4.add(new TextField("like", like, Field.Store.YES));
        age = 19;
        doc4.add(new LongPoint("age", age));
        doc4.add(new StoredField("age", age));
        doc4.add(new SortedNumericDocValuesField("age", age));
        property = "wife, children, parent, bike, dog";
        doc4.add(new TextField("property", property, Field.Store.YES));

        List<Document> docs = new ArrayList<>();
        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);
        docs.add(doc4);
        indexWriter.updateDocuments(null, docs);
        indexWriter.flush();
        indexWriter.commit();
    }

    public static void main(String[] args) throws IOException {
        BytesRef prevTerm = new BytesRef("ball");
        BytesRef term = new BytesRef("banana");
        int i = StringHelper.sortKeyLength(prevTerm, term);
        addDocValues();
    }
}
