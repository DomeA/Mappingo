package com.domeastudio.application.dataAccess.Services;

import com.domeastudio.application.dataAccess.DAO.MongoDBBaseDAO;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.io.File;
import java.io.Serializable;
import java.util.List;


/**
 * Created by domea on 16-1-17.
 */
public class MongoDBBaseService<T extends Serializable> {
    private MongoDBBaseDAO<T> mongoDBBaseDAO;

    public MongoDBBaseDAO<T> getMongoDBBaseDAO() {
        return mongoDBBaseDAO;
    }

    public void setMongoDBBaseDAO(MongoDBBaseDAO<T> mongoDBBaseDAO) {
        this.mongoDBBaseDAO = mongoDBBaseDAO;
    }

    /**
     * MongoDB链接测试
     */
    public void test() {
        mongoDBBaseDAO.test();
    }

    /**
     * 保存大型文件
     *
     * @param file
     * @param fileUrl
     */
    public void saveFile(File file, String fileUrl) {
        mongoDBBaseDAO.saveFile(file, fileUrl);
    }

    /**
     * 检索gridFSDB文件
     *
     * @param filename
     * @return
     */
    public GridFSDBFile retrieveFileOne(String filename) {
        return mongoDBBaseDAO.retrieveFileOne(filename);
    }

    /**
     * 根据存储的对象创建集合
     *
     * @param t
     */
    public void createCollection(T t) {
        mongoDBBaseDAO.createCollection(t);
    }

    /**
     * 根据集合名称创建集合
     *
     * @param collectionName
     */
    public void createCollection(String collectionName) {
        mongoDBBaseDAO.createCollection(collectionName);
    }

    /**
     * 统计查询后数据的总数
     *
     * @param query
     * @return
     */
    public Long count(Query query) {
        return mongoDBBaseDAO.count(query);
    }

    /**
     * 插入对象到mongodb中
     *
     * @param t
     */
    public void insert(T t) {
        mongoDBBaseDAO.insert(t);
    }

    /**
     * 插入对象到mongodb中,并且指定了集合名(表的意思)
     *
     * @param t
     * @param collectionName
     */
    public void insert(T t, String collectionName) {
        mongoDBBaseDAO.insert(t,collectionName);
    }

    /**
     * 保存对象到mongodb中
     *
     * @param t
     * @return
     */
    public T save(T t) {
        return mongoDBBaseDAO.save(t);
    }

    /**
     * 根据id删除对象
     *
     * @param t
     */
    public void deleteById(T t) {
        mongoDBBaseDAO.deleteById(t);
    }

    /**
     * 根据对象的属性删除
     *
     * @param t
     */
    public void deleteByCondition(T t) {
        mongoDBBaseDAO.deleteByCondition(t);
    }

    /**
     * 通过条件查询更新数据
     *
     * @param query
     * @param update
     */
    public void update(Query query, Update update) {
        mongoDBBaseDAO.update(query,update);
    }

    /**
     * 根据id进行更新
     *
     * @param id
     * @param t
     */
    public void updateById(String id, T t) {
        mongoDBBaseDAO.updateById(id,t);
    }

    /**
     * 通过条件查询实体(集合)
     *
     * @param query
     * @return
     */
    public List<T> find(Query query) {
        return mongoDBBaseDAO.find(query);
    }

    /**
     * 根据对象的属性查询实体(集合)
     *
     * @param t
     * @return
     */
    public List<T> findByCondition(T t) {
        return mongoDBBaseDAO.findByCondition(t);
    }

    /**
     * 通过一定的条件查询一个实体
     *
     * @param query
     * @return
     */
    public T findOne(Query query) {
        return mongoDBBaseDAO.findOne(query);
    }

    /**
     * 通过id获取记录
     *
     * @param id
     * @return
     */
    public T get(String id) {
        return mongoDBBaseDAO.get(id);
    }

    /**
     * 通过id获取记录,并且指定了集合名(表的意思)
     *
     * @param id
     * @param collectionName
     * @return
     */
    public T get(String id, String collectionName) {
        return mongoDBBaseDAO.get(id, collectionName);
    }

    /**
     * 获取Mongo数据库模板
     *
     * @return
     */
    public MongoTemplate getMongoTemplate() {
        return mongoDBBaseDAO.getMongoTemplate();
    }

}
