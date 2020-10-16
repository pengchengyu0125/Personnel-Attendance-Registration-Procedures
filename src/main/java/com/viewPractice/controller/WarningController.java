package com.viewPractice.controller;

import com.viewPractice.dto.ErrorDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/*
错误页面提示Controller
 */

@Controller
public class WarningController {

    @GetMapping("/warning/{errorType}")
    public String warning(Model model,
                          @PathVariable(name = "errorType") String type,
                          @RequestParam(name = "error", defaultValue = "0") String error){
        //根据错误类型获取错误信息
        ErrorDTO errorDTO=new ErrorDTO();
        //是否是完成结点数错误
        if (type.equals("1")){
            error=errorDTO.getError1();
        }
        //是否是总结点数错误
        else if (type.equals("2")){
            error=errorDTO.getError2();
        }
        //是否是页面信息错误
        else if (type.equals("3")){
            error=errorDTO.getError3();
        }
        model.addAttribute("error", error);
        return "warning";
    }
}
