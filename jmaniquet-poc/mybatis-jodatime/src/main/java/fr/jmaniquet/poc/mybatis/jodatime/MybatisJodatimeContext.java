package fr.jmaniquet.poc.mybatis.jodatime;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fr.jmaniquet.poc.mybatis.jodatime.mapper.GenericMapper;
import fr.jmaniquet.poc.tools.core.ToolsCoreContext;

@Configuration
@Import(ToolsCoreContext.class)
@MapperScan(
		basePackages = {"fr.jmaniquet.poc.mybatis.jodatime.mapper"},
		markerInterface=GenericMapper.class,
		sqlSessionFactoryRef="sqlSessionFactory")
@ComponentScan
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
}
