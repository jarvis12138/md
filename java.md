




```
页面：/resources/templates/
静态资源：/resources/static/
编写文件 src/main/java/Example.java


```

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.lynas</groupId>
    <artifactId>SpringMVCHibernate</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <name>SpringMVCHibernate</name>
    <description>SpringMVCHibernate</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.3.2.RELEASE</version>
        <relativePath /> <!-- lookup parent from repository -->
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>5.1.0.Final</version>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-entitymanager</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>


</project>
```

### 创建maven spring-boot项目

```java
// pom.xml
<parent>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-parent</artifactId>
     <version>1.4.3.RELEASE</version>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
	<optional>true</optional>
    </dependency>
    
</dependencies>
```

```java
import org.springframework.boot.SpringApplication;  
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;  

// 其中@SpringBootApplication申明让spring boot自动给程序进行必要的配置，等价于以默认属性使用@Configuration，@EnableAutoConfiguration和@ComponentScan
// @RestController返回json字符串的数据，直接可以编写RESTFul的接口；

@RestController  
@SpringBootApplication
// @SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class AppleApplication {  
 @RequestMapping("/hi")  
    String home() {  
        return "欢迎使用SpringBoot!";  
    }  
    public static void main(String[] args) {  
        SpringApplication.run(AppleApplication.class, args);  
    }  
}  
```

### index.html
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script
  src="http://code.jquery.com/jquery-1.12.4.js"
  integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="
  crossorigin="anonymous"></script>

</head>
<body>

<script type="text/javascript">
	$(function(){
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/login",
			data: 
				{
					"name": "zhansan",
					"age": "12"
				}
			,
			ContentType: "application/json",
			// dataType: "json",
			success: function(res){
				console.log(res);
			},
			error: function(error){
				console.log(error);
			}
		})
	});
</script>

</body>
</html>
```

### http请求
```java
@RequestMapping("/hi")
String home() {  
    return "欢迎使用SpringBoot!";  
}

// 访问形式：/hh/name
@RequestMapping(value="/hh/{name}", method=RequestMethod.GET)
public Map<String, String> map(@PathVariable("name") String name, Model model){
    Map<String, String> map = new HashMap<String, String>();
    map.put("hh", name);
    model.addAttribute("name", name);
    return map;
}

// 访问形式：/hh?names=zhangsan
@RequestMapping(value="/hh", method=RequestMethod.GET)
// @RequestParam(value="names", required=false, defaultValue="zhangsan")
public String findName(@RequestParam("names") String names){
    return names;
}

// 访问形式：/hh          json: {age: 20}
@RequestMapping(value="/login", method=RequestMethod.POST)
@ResponseBody  //后台以requestbody注解来获取请求对象参数
public ClassData getLogin(@RequestBody ClassData classData){
    return classData;
}

```


### 基于mysql数据库的JDBC操作
1 , 属性配置文件（application.properties）
```java
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/test?useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.max-idle=10
spring.datasource.max-wait=10000
spring.datasource.min-idle=5
spring.datasource.initial-size=5

server.port=8080
server.session.timeout=10
server.tomcat.uri-encoding=UTF-8


spring.datasource.validation-query=SELECT 1
spring.datasource.test-on-borrow=false
spring.datasource.test-while-idle=true
spring.datasource.time-between-eviction-runs-millis=18800
spring.datasource.jdbc-interceptors=ConnectionState;SlowQueryReport(threshold=0)
```
2，POM配置Maven依赖
```java
<dependency>  
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>  
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
<dependency>  
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

3 , 实体类
```java
package com.springboot.jdbc.entity;

/**
 * Created by nickzhang on 2016/9/1.
 */
public class UserInfo {

    private String name;
    private String age;
    private String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
```

4 , Service
```java
package com.springboot.jdbc.Service;

import com.springboot.jdbc.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by nickzhang on 2016/9/1.
 */
@Service
public class UserInfoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<UserInfo> query(){
        String sql = "select name,age,address from tb_userinfo";

        List<UserInfo> userinfoList = jdbcTemplate.query(sql, new RowMapper<UserInfo>() {
            @Override
            public UserInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                UserInfo userinfo = new UserInfo();
                userinfo.setName(resultSet.getString("name"));
                userinfo.setAge(resultSet.getString("age"));
                userinfo.setAddress(resultSet.getString("address"));
                return userinfo;
            }
        });

        return userinfoList;
    }

}
```

5 , Controller
```java
package com.springboot.jdbc;

import com.springboot.jdbc.Service.UserInfoService;
import com.springboot.jdbc.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Created by nickzhang on 2016/9/1.
 */
@RestController
@SpringBootApplication
public class SpringBootJDBCTest {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootJDBCTest.class);

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/query")
    public List<UserInfo> query(){
        logger.info(">>>>从数据库查询userinfo数据>>>>");
        return userInfoService.query();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJDBCTest.class,args);
    }
}
```

### java 枚举
[https://segmentfault.com/a/1190000012220863](https://segmentfault.com/a/1190000012220863)

### json
```java
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.1.1</version>
</dependency>


<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>fastjson</artifactId>
  <version>1.2.7</version>
</dependency>
```

### 热部署
devtools

```java
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-devtools</artifactId>
  <scope>provided</scope>
  <!--optional我没弄明白，都说必须为true，但我测试true，false，不加都可以-->
  <optional>true</optional>
</dependency>

// 通过项目主程序入口启动即可，改动以后重新编译就好。

```
springloaded
```java
<plugin>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-maven-plugin</artifactId>
  <dependencies>
    <!-- spring热部署 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>springloaded</artifactId>
      <version>1.2.6.RELEASE</version>
    </dependency>
  </dependencies>
  <configuration>
    <mainClass>cn.springboot.Mainspringboot</mainClass>
  </configuration>
</plugin>

```
运行：
```java
$ mvn clean spring-boot:run
```
[参考文章](http://blog.csdn.net/tengxing007/article/details/72675168)

### 配置fastjson
[http://blog.csdn.net/xuqingge/article/details/53561529](http://blog.csdn.net/xuqingge/article/details/53561529)

### ``` .properties ```   ``` .yml ``` 文件
```java
@PropertySource("classpath:global.properties")
public class GlobalProperties {
  @Value("${thread-pool}")
  private int threadPool;
}
```

### bean 配置
```java
// 加载多个Spring bean的配置文件
ApplicationContext context = 
    	new ClassPathXmlApplicationContext(new String[] {"Spring-Common.xml",
              "Spring-Connection.xml","Spring-ModuleA.xml"});
ApplicationContext context = 
    		new ClassPathXmlApplicationContext(Spring-All-Module.xml);


http://blog.csdn.net/vvhesj/article/details/47661001

https://www.cnblogs.com/bossen/p/5824067.html

//User.xml
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xmlns:p="http://www.springframework.org/schema/p"
xmlns:context="http://www.springframework.org/schema/context"
xmlns:aop="http://www.springframework.org/schema/aop"
xmlns:tx="http://www.springframework.org/schema/tx"
xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
      http://www.springframework.org/schema/context
         http://www.springframework.org/schema/context/spring-context-3.2.xsd
         http://www.springframework.org/schema/aop
         http://www.springframework.org/schema/aop/spring-aop-3.2.xsd
         http://www.springframework.org/schema/tx
      http://www.springframework.org/schema/tx/spring-tx-3.2.xsd">
	
	<bean id="UserBean" class="com.bean.User"></bean>

	<!-- 开启注解扫描 -->
	<context:component-scan base-package="com, com.test"></context:component-scan>

</beans>

```

### aop 配置
```java
// maven pom.xml
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-aop</artifactId>  
</dependency>
```
```java
<bean id="UserBean" class="com.bean.User">
</bean>
<bean id="MuUserBean" class="com.bean.MuUser"></bean>

<!-- id 切入点ID名 -->
<!-- method 增强类中前置方法 -->
<aop:config>
	<aop:pointcut expression="execution(* com.bean.User.*(..))" id="pointcut1" />
	<aop:aspect ref="MuUserBean">
		<aop:before method="add" pointcut-ref="pointcut1" />
	</aop:aspect>
</aop:config>

```

### 配置拦截器
```java
// AddInterceptor.java
@Configuration
public class AddInterceptor extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TestHandlerInterceptor()).addPathPatterns("/**");
		System.out.println("------------------ 拦截到请求 --------------------");
		super.addInterceptors(registry);
	}

}
```
```java
// TestHandlerInterceptor.java
//@Component
public class TestHandlerInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("----------------- function: afterCompletion ----------------------");
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("----------------- function: postHandle ----------------------");
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("----------------- function: preHandle ----------------------");
		
		return true;
	}

}
```
在主main类中加
```java
@Bean
public AddInterceptor addInterceptor() {
	return new AddInterceptor();
}
```

### 过滤器 Filter
```java
package m;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="testFilter2",urlPatterns="/*")
public class MyFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("filter destroy method");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest hsq = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestType = hsq.getHeader("X-Requested-With");
        
        chain.doFilter(req, resp);

		System.out.println("filter doFilter method");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("filter init method");
	}

}

```

### jpa

```java

public interface Dao extends JpaRepository<BeanClass, Integer> {
	
//	public List<BeanClass> findAll();
	
//	@Transactional
//	@Query(value = "select * from user_info  ", nativeQuery = true)
//	List<BeanClass> findByLastnameOrFirstname();
	

	@Transactional
	@Query(value = "select * from user_info u where u.name =?1 or u.age = ?2  ", nativeQuery = true)
	List<BeanClass> findByLastnameOrFirstname(String name, Integer age);

	@Transactional
    @Modifying
    @Query(value = "update user_info u set u.name=?1 where u.id=?2", nativeQuery = true)
    void updateUserImg(@Param(value = "name")String name,@Param(value = "id") Integer id);

}


@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {
	
	@Autowired
	Dao dao;

	@Test
	public void test() throws Exception{
//		BeanClass beanClass = new BeanClass();
//		beanClass.setAge(23);
//		beanClass.setName("zhang");
//		
		System.out.println("================test================");
		dao.updateUserImg("hahahha", 2);
//		System.out.println(list);
		System.out.println("================test================");
		
//		dao.findByLastnameOrFirstname(23, "zhagnsna");
	}

}

```













