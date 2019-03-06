
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
spring.jpa.hibernate.ddl-auto=create
spring.datasource.url=jdbc:mysql://localhost:3306/db_example
spring.datasource.username=springuser
spring.datasource.password=ThePassword
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



















