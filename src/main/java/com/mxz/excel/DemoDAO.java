package com.mxz.excel;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoDAO {
    public static int s = 0;
    // 省， 市， id
//    public static ArrayList<TbWechatExtraBranchBank> s = new ArrayList<>(200000);
    public void save(List<DemoData> list) {
        // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
//        InputStream inputStream = null;
//        try {
//            inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        SqlSessionFactory factory = builder.build(inputStream);
//        // DefaultSqlSessionFactory
//        SqlSession sqlSession = factory.openSession();

        for (DemoData demoData : list) {
            s ++;
//            s.add(tbWechatExtraBranchBank);
//            sqlSession.insert("com.mxz.mapper.EmployeesMapper.insert2",tbWechatExtraBranchBank);
        }
//        sqlSession.commit();
    }

}