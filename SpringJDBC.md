# About Spring JDBC

- Learn about Java Database Connectivity [here](https://github.com/codophilic/LearnWebDevInJAVA/blob/main/Theory.md#jdbc-java-database-connectivity).
- JDBC (Java Database Connectivity) is an application programming interface (API) that defines how a client may access a database. It is a data access technology used for Java database connectivity. It provides methods to query and update data in a database and is oriented toward relational databases.
- Spring Data JDBC, part of the larger Spring Data family, makes it easy to implement JDBC based repositories. This module deals with enhanced support for JDBC based data access layers. It makes it easier to build Spring powered applications that use data access technologies.
- Spring JDBC provides additional module along with JDBC.

## Problems when used only JDBC
- Requires to write lot of codes. For example we need to open connections , close connections multiple times, write statements and everything. Change in a table involes changes in code wherever the table used.
- It involved multiple try and SQLException block.
- Repeating of all these codes from one to another database is time consuming.
- Solution to such problems are provided by JDBC.

## JdbcTemplate
- Spring provides a **JdbcTemplate** which has all the important methods to perform operations to database.
- To execute sql queries using JdbcTemplate we require one object of JdbcTemplate,. Now JdbcTemplate depends on dataSource object, but here dataSource is an interface.
- DriverManagerDataSource is a class which implements dataSource interface. So basically DriverManagerDataSource object can be used for JdbcTemplate.
- So we will inject DriverManagerDataSource bean into JdbcTemplate.

- Lets say we create a schema with name springJdbc which has a table student and one data entry.

```
create table student(
id int primary key,
name varchar(100) not null,
city varchar(100) not null);

insert into student(id,name,city) values(1,'Harsh','Mumbai');
```

- Below is the Student class

```
package Spring.JDBC;

public class Student {

	private int id;
	private String name;
	private String city;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", city=" + city + "]";
	}

}

```

- Below is the configuration required to with to start with Spring JDBC.

```
<?xml version="1.0" encoding="UTF-8"?>


<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/util
                           http://www.springframework.org/schema/util/spring-util.xsd" >	
	
	<!-- 
	Define JdbcTemplate and its class
	org.springframework.jdbc.core.JdbcTemplate is a class which has DataSource as one of its argument in the
	constructor , so the org.springframework.jdbc.core.JdbcTemplate class requires class of DataSource.
	Now DataSource is an interface, but we have DriverMangerDataSource which implements DataSource
	
	In DriverManagerDataSource , we need to give database details like
	1. URL
	2. Username
	3. DB name
	4. password
	 -->
	 
	 <bean name="driverManagerDataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
	 	<property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
	 	<property name="url" value="jdbc:mysql://localhost:3306/springjdbc"></property>
	 	<property name="username" value="root"></property>
	 	<property name="password" value="Meetpandya40@"></property>
	 
	 </bean>
	<bean name="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate" >
		<property name="dataSource" ref="driverManagerDataSource"/>
	</bean>
</beans>
```

- Post running an insert query in main method we can see result

```
package Spring.JDBC;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Hello world!
 *
 */
public class SpringJDBCMainMethod 
{
    public static void main( String[] args )
    {
		ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("Spring/JDBC/springjdbc.xml");
		JdbcTemplate jdbcTemplate=factory.getBean("jdbcTemplate",JdbcTemplate.class);
		
		/**
		 * Insert Operation
		 */
		String sql="insert into student(id,name,city) values (?,?,?)";
		int result=jdbcTemplate.update(sql,3,"Meet","Mumbai");
		System.out.println("Rows affected - "+result);
    }
}

Output:
Rows affected - 1
```

![alt text](image.png)



























