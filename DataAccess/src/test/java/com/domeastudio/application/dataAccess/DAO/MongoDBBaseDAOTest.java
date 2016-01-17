package com.domeastudio.application.dataAccess.DAO;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by domea on 16-1-17.
 */
@Transactional
//@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mongo.xml"})
public class MongoDBBaseDAOTest {

//    @Resource
//    private MongoDBBaseDAO mongoDBBaseDAO;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTest1() throws Exception {

    }

    @Test
    public void testSaveFile() throws Exception {

    }

    @Test
    public void testRetrieveFileOne() throws Exception {

    }

    @Test
    public void testCreateCollection() throws Exception {

    }

    @Test
    public void testCreateCollection1() throws Exception {

    }

    @Test
    public void testCount() throws Exception {

    }

    @Test
    public void testInsert() throws Exception {

    }

    @Test
    public void testInsert1() throws Exception {

    }

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testDeleteById() throws Exception {

    }

    @Test
    public void testDeleteByCondition() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testUpdateById() throws Exception {

    }

    @Test
    public void testFind() throws Exception {

    }

    @Test
    public void testFindByCondition() throws Exception {

    }

    @Test
    public void testFindOne() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testGet1() throws Exception {

    }

    @Test
    public void testGetMongoTemplate() throws Exception {

    }

    @Test
    public void testGetEntityClass() throws Exception {

    }
}
