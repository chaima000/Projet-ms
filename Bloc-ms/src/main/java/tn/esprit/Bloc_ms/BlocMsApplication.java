package tn.esprit.Bloc_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class BlocMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlocMsApplication.class, args);
	}

}
