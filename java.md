
test

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
```

```java
// src/test/java
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
}
```

redis

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
```

```
spring.redis.host=127.0.0.1 #redis服务器地址
spring.redis.port=6379 #端口
spring.redis.timeout=6000 #连接超时时间 毫秒
spring.redis.pool.max-active=8 # 连接池的配置，最大连接激活数
spring.redis.pool.max-idle=8 # 连接池配置，最大空闲数
spring.redis.pool.max-wait=-1 #  连接池配置，最大等待时间
spring.redis.pool.min-idle=0 # 连接池配置，最小空闲活动连接数
```

```java
// code
@Autowired
StringRedisTemplate stringRedisTemplate;

public String getValue() {
    stringRedisTemplate.opsForValue().set("my-key", "Moonlight");
}
```
