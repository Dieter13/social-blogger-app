package J.M.microblogging.projectBlogging;

import J.M.microblogging.projectBlogging.dao.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class ProjectBloggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBloggingApplication.class, args);

	}

}
