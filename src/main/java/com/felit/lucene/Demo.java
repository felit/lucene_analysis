package com.felit.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 */
public class Demo {
    public static void main(String args[]) throws IOException {
        FieldType fieldType = new FieldType();
        fieldType.setStored(true);
        fieldType.setIndexOptions(IndexOptions.DOCS);
        Field field = new Field("remark", "测试 lucene　分词", fieldType);
        Document doc = new Document();
        doc.add(field);
        Directory dir = FSDirectory.open(Paths.get("/data/lucene_data"));
        IndexWriter writer = new IndexWriter(dir, new IndexWriterConfig(new StandardAnalyzer()));
        writer.addDocument(doc);
        writer.close();
    }
}
