package jeans.ko.Dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Data
@Component
public class PictureDto {
    private BigInteger pictureId;
    private int lookNum;
    private String pictureName;
    private long pictureSize;
}
