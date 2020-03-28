package com.mxz;

import com.mxz.model.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Date 2019/12/10 21:03
 * @Author mxz
 */
public class MainTest {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        // DefaultSqlSessionFactory
        SqlSession sqlSession = factory.openSession();

        //3.使用SqlSession查询
        Map<String,Object> params = new HashMap<String,Object>();

        params.put("employeeId","100");
        //a.查询工资低于10000的员工11
        // DefaultSqlSession
        List<Employee> result = sqlSession.selectList("com.mxz.mapper.EmployeesMapper.selectByPrimaryKey2",params);

        System.out.println("薪资低于10000的员工数："+result.size());
    }
}
