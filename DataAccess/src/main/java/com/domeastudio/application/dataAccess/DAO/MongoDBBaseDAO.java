package com.domeastudio.application.dataAccess.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Type;
import java.util.List;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import java.util.Set;
import java.io.File;
import java.io.IOException;
/**
 * Created by domea on 16-1-16.
 */
public class MongoDBBaseDAO<T extends Serializable> {
    private static final Logger logger = LoggerFactory.getLogger(MongoDBBaseDAO.class);

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoTemplate mongoTemplate;
    public static final String FILEURL="imgRespository";

    //当前泛型类
    @SuppressWarnings("rawtypes")
    private Class<T> entityClass;

    public MongoDBBaseDAO() {
        Type type = this.getClass().getGenericSuperclass();
        if (type.toString().indexOf("MongoDBBaseDAO") != -1) {
            ParameterizedType type1 = (ParameterizedType) type;
            Type[] types = type1.getActualTypeArguments();
            setEntityClass((Class<T>) types[0]);
        }else{
            type = ((Class)type).getGenericSuperclass();
            ParameterizedType type1 = (ParameterizedType) type;
            Type[] types = type1.getActualTypeArguments();
            setEntityClass((Class<T>) types[0]);
        }
    }

    /**
     * DAO链接测试
     */
    public void test(){
        Set<String> colls = this.mongoTemplate.getCollectionNames();
        for (String coll : colls) {
            logger.info("CollectionName=" + coll);
        }
        DB db = this.mongoTemplate.getDb();
        logger.info("db=" + db.toString());
    }

    /**
     * 保存大型文件
     * @param file
     * @param fileUrl
     */
    public void saveFile(File file,String fileUrl){
        try {
            DB db = mongoTemplate.getDb();
            GridFS fs = new GridFS(db, FILEURL);
            GridFSInputFile inputFile = fs.createFile(file);
            inputFile.setFilename(fileUrl);
            inputFile.setContentType(fileUrl.substring(fileUrl.lastIndexOf(".")));
            inputFile.save();
        } catch (IOException e) {
            logger.trace("MongoDBBaseDAO<"+getEntityClass().getSimpleName()+">.saveFile:",e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 检索gridFSDB文件
     * @param filename
     * @return
     */
    public GridFSDBFile retrieveFileOne(String filename){
        try {
            DB db = mongoTemplate.getDb();
            // 获取fs的根节点
            GridFS gridFS = new GridFS(db, FILEURL);
            GridFSDBFile dbfile = gridFS.findOne(filename);
            if (dbfile != null) {
                return dbfile;
            }
        } catch (Exception e) {
            logger.trace("MongoDBBaseDAO<"+getEntityClass().getSimpleName()+">.retrieveFileOne:",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据存储的对象创建集合
     * @param t
     */
    public void createCollection(T t){
        if (!mongoTemplate.collectionExists(this.getEntityClass())) {
            mongoTemplate.createCollection(this.getEntityClass());
        }
    }

    /**
     * 根据集合名称创建集合
     * @param collectionName
     */
    public void createCollection(String collectionName){
        if (!mongoTemplate.collectionExists(collectionName)) {
            mongoTemplate.createCollection(collectionName);
        }
    }
    /**
     * 统计查询后数据的总数
     * @param query
     * @return
     */
    public Long count(Query query){
//        Query query = new Query();
//        if ((params != null) && (!(params.isEmpty()))) {
//            for (String key : params.keySet()) {
//                query.addCriteria(new Criteria(key).is(params.get(key)));
//            }
//        }
        return (long)mongoTemplate.find(query, this.getEntityClass()).size();
    }

    /**
     * 插入对象到mongodb中
     * @param t
     */
    public void insert(T t){
        mongoTemplate.insert(t);
    }

    /**
     * 插入对象到mongodb中,并且指定了集合名(表的意思)
     * @param t
     * @param collectionName
     */
    public void insert(T t,String collectionName){
        mongoTemplate.insert(t,collectionName);
    }
    /**
     * 保存对象到mongodb中
     * @param t
     * @return
     */
    public T save(T t){
        mongoTemplate.save(t);
        return t;
    }

    /**
     * 根据id删除对象
     * @param t
     */
    public void deleteById(T t){
        Query query = buildBaseQuery(t);
        mongoTemplate.remove(query, this.getEntityClass());
    }
    /**
     * 根据对象的属性删除
     * @param t
     */
    public void deleteByCondition(T t){
        Query query = buildBaseQuery(t);
        mongoTemplate.remove(query, this.getEntityClass());
    }

    /**
     * 通过条件查询更新数据
     * @param query
     * @param update
     */
    public void update(Query query, Update update){
        mongoTemplate.updateMulti(query, update, this.getEntityClass());
    }

    /**
     * 根据id进行更新
     * @param id
     * @param t
     */
    public void updateById(String id, T t){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = buildBaseUpdate(t);
        update(query, update);
    }

    /**
     * 通过条件查询实体(集合)
     * @param query
     * @return
     */
    public List<T> find(Query query){
        return mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     *根据对象的属性查询实体(集合)
     * @param t
     * @return
     */
    public List<T> findByCondition(T t){
        Query query = buildBaseQuery(t);
        return mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     * 通过一定的条件查询一个实体
     * @param query
     * @return
     */
    public T findOne(Query query){
        return mongoTemplate.findOne(query, this.getEntityClass());
    }

    /**
     * 通过id获取记录
     * @param id
     * @return
     */
    public T get(String id){
        return mongoTemplate.findById(id, this.getEntityClass());
    }

    /**
     * 通过id获取记录,并且指定了集合名(表的意思)
     * @param id
     * @param collectionName
     * @return
     */
    public T get(String id, String collectionName){
        return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
    }

    /**
     * 获取Mongo数据库模板
     * @return
     */
    public MongoTemplate getMongoTemplate(){
        return mongoTemplate;
    }

    /**
     * 根据vo构建查询条件query
     * @param t
     * @return
     */
    private Query buildBaseQuery(T t) {
        Query query = new Query();

        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(t);
                if (value != null) {
                    QueryField queryfield = field.getAnnotation(QueryField.class);
                    if (queryfield != null) {
                        query.addCriteria(queryfield.type().buildCriteria(queryfield, field, value));
                    }
                }
            } catch (IllegalArgumentException e) {
                logger.trace("MongoDBBaseDAO<"+getEntityClass().getSimpleName()+">.buildBaseQuery:",e.getMessage());
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                logger.trace("MongoDBBaseDAO<"+getEntityClass().getSimpleName()+">.buildBaseQuery:",e.getMessage());
                e.printStackTrace();
            }
        }
        return query;
    }
    /**
     * 根据vo构建更新条件update
     * @param t
     * @return
     */
    private Update buildBaseUpdate(T t) {
        Update update = new Update();

        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(t);
                if (value != null) {
                    update.set(field.getName(), value);
                }
            } catch (Exception e) {
                logger.trace("MongoDBBaseDAO<"+getEntityClass().getSimpleName()+">.buildBaseUpdate:",e.getMessage());
                e.printStackTrace();
            }
        }
        return update;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    /**
     * 获取需要操作的实体类class
     * @return
     */
//    @SuppressWarnings("unchecked")
//    private Class<T> getEntityClass() {
//        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
//    }
}
