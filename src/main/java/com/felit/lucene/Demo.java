package com.felit.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * store: DataInput DataOutput Directory LockFactory
 * index: IndexReader,MergePolicy IndexDeletionPolicy
 */
public class Demo {
    public static void main(String args[]) throws IOException, ParseException {
        // 写入索引
        FieldType fieldType = new FieldType();
        fieldType.setStored(true);
        fieldType.setIndexOptions(IndexOptions.DOCS);
        Field field = new Field("remark", "测试 lucene　分词　还有elasticsearch", fieldType);
        Document doc = new Document();
        doc.add(field);

        Directory dir = FSDirectory.open(Paths.get("/data/lucene_data"));
        IndexWriter writer = new IndexWriter(dir, new IndexWriterConfig(new StandardAnalyzer()));
        writer.addDocument(doc);
        writer.flush();
        // System.in.read();
        writer.close();

        // 读取索引
        Analyzer analyzer = new StandardAnalyzer();
        Directory dirs = FSDirectory.open(Paths.get("/data/lucene_data"));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher is = new IndexSearcher(reader);
        QueryParser parser = new QueryParser("remark", analyzer);
        Query query = parser.parse("lucene");
        TopDocs topDocs = is.search(query, 1000);
        ScoreDoc[] hits = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : hits) {
            System.out.println("匹配得分：" + scoreDoc.score);
            System.out.println("文档索引ID：" + scoreDoc.doc);
            Document document = is.doc(scoreDoc.doc);
            System.out.println(document.get("remark"));
        }
        reader.close();
        dirs.close();
    }
}
