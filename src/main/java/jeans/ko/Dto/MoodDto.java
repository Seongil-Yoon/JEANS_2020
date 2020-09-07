package jeans.ko.Dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MoodDto {
    int look_num;//외래키 글의 AutoIncrement값
    String mood;//그 글의 무드
}
