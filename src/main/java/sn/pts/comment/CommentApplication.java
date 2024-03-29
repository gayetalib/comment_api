package sn.pts.comment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class CommentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CommentApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("...CommentApplication running...");
	}
}
