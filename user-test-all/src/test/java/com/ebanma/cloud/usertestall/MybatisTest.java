package com.ebanma.cloud.usertestall;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ebanma.cloud.usertestall.domain.entity.gen.TbUser;
import com.ebanma.cloud.usertestall.domain.entity.gen.TbUserExample;
import com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

/**
 * @author 肖露
 * @version $ Id: MybatisTest, v 0.1 2023/03/17 10:45 banma-0241 Exp $
 */
public class MybatisTest {

    @Test
    public void test1() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        TbUser user = sqlSession.selectOne("com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.selectByPrimaryKey",
            1l);
        System.out.println(user);
        TbUser user2 = sqlSession.selectOne("com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.selectByPrimaryKey",
            1l);
        System.out.println(user2);

        sqlSession.close();

    }

    @Test
    public void test2() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = factory.openSession();

        TbUserMapper mapper = sqlSession.getMapper(TbUserMapper.class);
        List<TbUser> all = mapper.selectByExample(new TbUserExample());
        for (TbUser user : all) {
            System.out.println(user);
        }

        TbUser user = sqlSession.selectOne("com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.selectByPrimaryKey",
            1l);
        System.out.println(user);
        TbUser user2 = sqlSession.selectOne("com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.selectByPrimaryKey",
            1l);
        System.out.println(user2);
    }

    @Test
    public void test3() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession1 = factory.openSession();
        SqlSession sqlSession2 = factory.openSession();
        TbUser user1 = sqlSession1.selectOne("com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.selectByPrimaryKey",
            1l);
        System.out.println(user1);
        sqlSession1.commit();
        TbUser user = new TbUser();
        user.setId(1l);
        user.setUsername("jack");
        sqlSession1.update("com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.updateByPrimaryKey", user);
        TbUser user2 = sqlSession2.selectOne("com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.selectByPrimaryKey",
            1l);
        System.out.println(user2);

    }

}
