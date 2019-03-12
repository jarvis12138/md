
pom.xml:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.springframework</groupId>
    <artifactId>gs-rest-service</artifactId>
    <version>0.1.0</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.5.RELEASE</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.jayway.jsonpath</groupId>
            <artifactId>json-path</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <properties>
        <java.version>1.8</java.version>
    </properties>


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

Application:

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

jpa:

```xml
<!-- JPA Data (We are going to use Repositories, Entities, Hibernate, etc...) -->

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Use MySQL Connector-J -->

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

src/main/resources/application.properties :

```java
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/test?useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

@RequestMapping : 

```java
// 处理url:
@RequestMapping("/home")
// 处理多个url：
@RequestMapping(value = {"", "/page*", "view/*", "**/msg"}
// 处理http请求
@RequestMapping(method = RequestMethod.GET)
// 处理生产和消费对象
@RequestMapping(value = "/prod", produces = {"application/JSON"})
@RequestMapping(value = "/cons", consumes = {"application/JSON", "application/XML"})
// 处理消息头
@RequestMapping(value = "/head", headers = {"content-type=text/plain"})
// 处理请求参数
@RequestMapping(value = "/fetch", params = {"personId=10"})
// 处理动态 URI
@RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
@RequestMapping(value = "/fetch/{id:[a-z]+}/{name}", method = RequestMethod.GET)



// @PathVaribale 获取url中的数据
@RequestMapping(value="/hello/{id}",method= RequestMethod.GET)
public String sayHello(@PathVariable("id") Integer id){}
// @RequestParam 获取请求参数的值
// localhost:8080/hello?id=98
@RequestMapping(value="/hello",method= RequestMethod.GET)
public String sayHello(@RequestParam("id") Integer id){}
// required=false 表示url中可以不穿入id参数，此时就使用默认参数
@RequestMapping(value="/hello",method= RequestMethod.GET)
public String sayHello(@RequestParam(value="id",required = false,defaultValue = "1") Integer id){}
// @RequestBody能把简单json结构参数转换成实体类
@RequestMapping(value = "/testUser", method = RequestMethod.POST)
public String testUser(@RequestBody User user){}
```

热部署: 

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>
</dependencies>
```

mybatis、sql : 

```xml

<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.2</version>
</dependency>
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>2.0.2</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

```java
@Mapper
public interface BeanClass {

	@Select("select * from user where id=#{id}")
	public User getBeanById(Integer id);
}
```

```java
// 插入：（数据库 表id一定要设置AI(Auto Incr)(自增)）
@Insert("INSERT INTO user(username,password,age) VALUES( #{username}, #{password}, #{age})")
@Options(useGeneratedKeys=true, keyProperty="id") // 插入keyProperty=“id”，getId就不会为null
public void insert(User user) ;


User user = new User();
// user.setId(6);
user.setAge(10);
user.setUsername("haha");
user.setPassword("333333");
mapperBean.insert(user);
System.out.println(user.getId());
```

```java
// update
@Update("Update user set username=#{username}, password=#{password}, age=#{age} where id=#{id}")
```

```java
// delete
@Delete("delete from user where id=#{id}")
```

index.html

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

分页插件 : 

```xml
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.5</version>
</dependency>
```

```java
// PageHelper 使用非常简单，只需要设置页码和每页显示笔数即可
PageHelper.startPage(2, 2);

// 设置分页查询条件
// Example example = new Example(User.class);
PageInfo<User> pageInfo = new PageInfo<>(mapperBean.findAll());

// 获取查询结果
List<User> tbUsers = pageInfo.getList();
for (User tbUser : tbUsers) {
	System.out.println(tbUser.getUsername());
}
```

alibaba > fastjson : 

```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.47</version>
</dependency>
```

```javascript
// JSONObject json = new JSONObject();
```

HandlerInterceptor 拦截器

```java

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PassportInterceptor implements HandlerInterceptor {

	@Autowired
	UserDao userDao;
	
	@Autowired
	TicketDao ticketDao;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// User user = userDao(ticketDao.getId());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// User.add()
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		// User.clear();
	}

}
```

```java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	PassportInterceptor passportInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(passportInterceptor);
		super.addInterceptors(registry);
	}

}
```

拦截器 自定义注解式

```java

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireSignature {
	
	String key();
	
	boolean lock() default false;
}
```

```java

import java.lang.reflect.Method;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Component
public class ExternalServiceInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Method method = ((HandlerMethod) handler).getMethod();
		RequireSignature requireSignature = method.getAnnotation(RequireSignature.class);

		System.out.println(requireSignature);

		return super.preHandle(request, response, handler);
	}
}
```

```java
public class WebConfiguration extends WebMvcConfigurerAdapter { }
```

```java
@RequireSignature(key="test")
@RequestMapping("/hi")
```



















