//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
//import com.mongodb.client.ListIndexesIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//
///**
// * @Description
// * @Date 2019/12/11 16:11
// * @Author mxz
// */
//public class MongoDb {
//    public static void main(String[] args) {
//
//        MongoClientURI uri = new MongoClientURI(
//                "mongodb+srv://root:root@cluster0-tpq1p.mongodb.net/test?retryWrites=true&w=majority");
//
//        MongoClient mongoClient = new MongoClient(uri);
//        MongoDatabase database = mongoClient.getDatabase("test");
////        database.createCollection("bbb");
//        MongoCollection<Document> coll = database.getCollection("bbb");
////              coll.createIndex(new Document("validata",1));//创建索引
////             coll.createIndex(new Document("id",1));
////              coll.createIndex(new Document("ut_wos",1),new IndexOptions().unique(true));//创建唯一索引
//                ListIndexesIterable<Document> list = coll.listIndexes();//查询所有索引
//                for (Document document : list) {
//                        System.out.println(document.toJson());
//                    }
//        System.out.println("database.getName() = " + database.getName());
//
//    }
//}
