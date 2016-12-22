package base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Junit测试基类 继承AbstractTransactionalJUnit4SpringContextTests 可实现事务回滚
 * 
 * @author javaw
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:/spring.xml", "classpath*:/spring-mvc.xml", "classpath*:/spring-mybatis.xml" })
@Transactional
public abstract class BaseTransactionTestCase extends AbstractTransactionalJUnit4SpringContextTests {

}