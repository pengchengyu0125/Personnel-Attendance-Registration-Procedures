package com.viewPractice.controller;

import com.viewPractice.dto.ErrorTypeDTO;
import com.viewPractice.dto.OneDTO;
import com.viewPractice.service.OneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

/*
 *横版物流状态Controller
 */

@Controller
public class oneController {
    @Autowired
    private OneService oneService;

    @GetMapping("/one")
    public String one(Model model,
                      //已经完成的节点数
                      @RequestParam(name = "fnum", defaultValue = "2") Integer fnum,
                      //总结点数
                      @RequestParam(name = "total", defaultValue = "4") Integer total){
        List<OneDTO> list = new ArrayList<>();
        //将查询到的数据根据总结点数赋值
        for (int i = 0; i < total; i++) {
            list.add(oneService.list().get(i));
        }
        //判断错误类型
        ErrorTypeDTO errorTypeDTO = new ErrorTypeDTO();
        //完成节点数错误
        if (fnum < 0 || fnum > total)
            return "redirect:/warning/"+ errorTypeDTO.getType1();
        //总结点数错误
        else if (total < 1)
            return "redirect:/warning/"+ errorTypeDTO.getType2();
        //数据为空错误
        else if (list == null)
            return "redirect:/warning/"+ errorTypeDTO.getType3();

        model.addAttribute("fnum", fnum);
        model.addAttribute("total", total);
        model.addAttribute("locations", list);
        return "one";
    }
}
