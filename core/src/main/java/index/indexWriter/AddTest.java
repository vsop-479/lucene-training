package index.indexWriter;

import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.util.BytesRef;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddTest {


    public void bulk(IndexWriter indexWriter, List<byte[]> dataList) throws Exception{

    }


    private Document getDoc(Map<String, Object> data){
        Document document = new Document();
        Object o = data.get("name");
        document.add(new StringField("name", o.toString(), Field.Store.YES));
        document.add(new SortedDocValuesField("name.value", new BytesRef(o.toString().getBytes())));
        o = data.get("like");
        document.add(new TextField("like", o.toString(), Field.Store.YES));
        o = data.get("age");
        document.add(new IntPoint("age", Integer.parseInt(o.toString())));
        document.add(new StoredField("age.store", Integer.parseInt(o.toString())));
        document.add(new SortedNumericDocValuesField("age.value", Integer.parseInt(o.toString())));
        o = data.get("property");
        document.add(new TextField("property", o.toString(), Field.Store.YES));
        return document;
    }

    private void initFields(){
        Map<String, Field> fieldMap = new HashMap<>();
        StringField name = new StringField("name", "", Field.Store.YES);
        SortedDocValuesField nameValue = new SortedDocValuesField("name.value", new BytesRef(""));
        TextField like = new TextField("like", "", Field.Store.YES);
        ((FieldType)like.fieldType()).setStoreTermVectors(true);
        ((FieldType)like.fieldType()).setStoreTermVectorPositions(true);
        ((FieldType)like.fieldType()).setStoreTermVectorOffsets(true);
        ((FieldType)like.fieldType()).setStoreTermVectorPayloads(true);
        ((FieldType)like.fieldType()).setOmitNorms(true);

        StandardTokenizer standardTokenizer = new StandardTokenizer();
    }

    public static void main(String[] args){
    }

}
