package jeans.ko.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jeans.ko.Dto.PasswordDto;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/userinformation")
public class ChangeuserController {


    @PostMapping(value = "/password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody PasswordDto passwordDto, BindingResult result) throws Exception {
     //   System.out.println("password = " + password);

       // @Valid
       // PasswordDto passwordDto = new ObjectMapper().readValue(password, PasswordDto.class);

        System.out.println("passwordDto.getPs() = " + passwordDto.getPs());
        System.out.println("result.getErrorCount() = " + result.getErrorCount());
        System.out.println("result.hasGlobalErrors() = " + result.hasGlobalErrors());

        if (result.getFieldError("ps") != null) {
            System.out.println("Error! = " + result.getFieldError("ps").getDefaultMessage());
        }

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}