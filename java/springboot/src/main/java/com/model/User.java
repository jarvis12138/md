package com.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "user", schema = "SIMULATE")

public class User implements Serializable {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", age=" + age + "]";
	}

	// @Id注释指定表的主键，
	// 1、TABLE：容器指定用底层的数据表确保唯一；
	// 2、SEQUENCE：使用数据库德SEQUENCE列莱保证唯一（Oracle数据库通过序列来生成唯一ID）；
	// 3、IDENTITY：使用数据库的IDENTITY列莱保证唯一；
	// 4、AUTO：由容器挑选一个合适的方式来保证唯一；
	// 5、NONE：容器不负责主键的生成，由程序来完成。
	// @GeneratedValue注释定义了标识字段生成方式
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	// @Temporal注释用来指定java.util.Date或java.util.Calender属性
	// 与数据库类型date、time或timestamp中的那一种类型进行映射。
//	@Temporal(value = TemporalType.TIME)
//	private Date birthday;

	// @Column注释定义了将成员属性映射到关系表中的哪一列和该列的结构信息
	// 1、name：映射的列名。如：映射tbl_user表的name列，可以在name属性的上面或getName方法上面加入；
	// 2、unique：是否唯一；
	// 3、nullable：是否允许为空；
	// 4、length：对于字符型列，length属性指定列的最大字符长度；
	// 5、insertable：是否允许插入；
	// 6、updatetable：是否允许更新；
	// 7、columnDefinition：定义建表时创建此列的DDL；
	// 8、secondaryTable：从表名。如果此列不建在主表上（默认是主表），该属性定义该列所在从表的名字。

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "age")
	private Integer age;

}