package com.ebanma.cloud.usertestall;

import com.ebanma.cloud.usertestall.domain.entity.gen.TbUser;
import com.ebanma.cloud.usertestall.domain.entity.gen.TbUserExample;
import com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    /**
     * 传统方式
     */
    @Test
    public void test1() throws IOException {
        // 1. 读取配置文件，读成字节输入流，注意：现在还没解析
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");

        // 2. 解析配置文件，封装Configuration对象   创建DefaultSqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 3. 生产了DefaultSqlsession实例对象   设置了事务不自动提交  完成了executor对象的创建
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4.(1)根据statementid来从Configuration中map集合中获取到了指定的MappedStatement对象
        //   (2)将查询任务委派了executor执行器
        TbUser user = sqlSession.selectOne("com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.selectByPrimaryKey", 1L);
        System.out.println(user);
        TbUser user2 = sqlSession.selectOne("com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.selectByPrimaryKey", 1L);
        System.out.println(user2);

        // 5.释放资源
        sqlSession.close();
    }

    /**
     * mapper代理方式
     */
    @Test
    public void test2() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TbUserMapper mapper = sqlSession.getMapper(TbUserMapper.class);
        List<TbUser> all = mapper.selectByExample(new TbUserExample());
        for (TbUser user : all) {
            System.out.println(user);
        }
    }

    /**
     * mybatis二级缓存测试效果
     */
    @Test
    public void test3() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        TbUser user1 = sqlSession1.selectOne(
                "com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.selectByPrimaryKey",
                1L);
        System.out.println(user1);
        sqlSession1.commit();

        TbUser user = new TbUser();
        user.setId(1L);
        user.setUsername("张三");
        //增删改会清空二级缓存
        sqlSession1.update(
                "com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.selectByPrimaryKey",
                1L);
        TbUser user2 = sqlSession2.selectOne(
                "com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.selectByPrimaryKey",
                1L);
        System.out.println(user2);
    }

}