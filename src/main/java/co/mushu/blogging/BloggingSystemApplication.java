package co.mushu.blogging;

import co.mushu.blogging.models.Blogs;
import co.mushu.blogging.models.Users;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {Blogs.class, Users.class})
public class BloggingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingSystemApplication.class, args);
	}

}
