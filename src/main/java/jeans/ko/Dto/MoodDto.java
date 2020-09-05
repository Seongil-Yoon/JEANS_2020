package jeans.ko.Dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MoodDto {
    int look_num;
    String mood;
}
