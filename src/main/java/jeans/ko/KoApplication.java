package jeans.ko;


import jeans.ko.Dao.IUserDao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.time.LocalDateTime;

import java.awt.*;

@SpringBootApplication
public class KoApplication {
	public static void main(String[] args) {
		SpringApplication.run(KoApplication.class, args);
	}
}
