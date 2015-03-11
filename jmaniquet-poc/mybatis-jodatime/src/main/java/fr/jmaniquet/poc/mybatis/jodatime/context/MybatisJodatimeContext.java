package fr.jmaniquet.poc.mybatis.jodatime.context;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.ResourceTransactionManager;

import fr.jmaniquet.poc.mybatis.jodatime.mapper.GenericMapper;
import fr.jmaniquet.poc.tools.context.ToolsContext;

@Configuration
@Import(ToolsContext.class)
@EnableTransactionManagement(order = 1)
@MapperScan(
		basePackages = {"fr.jmaniquet.poc.mybatis.jodatime.mapper"},
		markerInterface=GenericMapper.class,
		sqlSessionFactoryRef="sqlSessionFactory")
@ComponentScan("fr.jmaniquet.poc.mybatis.jodatime")
public class MybatisJodatimeContext {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setTypeHandlersPackage("fr.jmaniquet.poc.mybatis.jodatime.handler");
		
		// TODO : vérifier pourquoi ce n'est plus nécessaire
		/*Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:fr/jmaniquet/poc/*Mapper.xml");
		sqlSessionFactoryBean.setMapperLocations(resources);*/
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public ResourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}
