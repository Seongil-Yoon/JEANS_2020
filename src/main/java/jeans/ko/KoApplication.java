package jeans.ko;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.time.LocalDateTime;

@SpringBootApplication
//@MapperScan(basePackages = "jeans.ko.Dao")
public class KoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoApplication.class, args);
	}

}
