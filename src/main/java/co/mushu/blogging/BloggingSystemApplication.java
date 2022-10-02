package co.mushu.blogging;

import co.mushu.blogging.models.Blogs;
import co.mushu.blogging.models.Users;
import co.mushu.blogging.repositories.UsersRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
public class BloggingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingSystemApplication.class, args);
	}

}
