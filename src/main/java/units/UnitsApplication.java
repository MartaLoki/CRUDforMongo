package units;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class UnitsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnitsApplication.class, args);
    }
}
