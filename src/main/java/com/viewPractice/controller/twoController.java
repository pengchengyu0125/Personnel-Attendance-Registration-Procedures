package com.viewPractice.controller;

import com.viewPractice.dto.ErrorTypeDTO;
import com.viewPractice.dto.TwoDTO;
import com.viewPractice.service.TwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

/*
 *竖版物流状态Controller
 */

@Controller
public class twoController {
    @Autowired
    private TwoService twoService;

    @GetMapping("/two")
    public String two(Model model,
                      @RequestParam(name = "fnum",defaultValue = "2") Integer fnum,
                      @RequestParam(name = "total",defaultValue = "4") Integer total){
        List<TwoDTO> list = new ArrayList<>();
        //根据总结点数获取页面二信息放入list中
        for (int i = 0; i < total; i++) {
            list.add(twoService.list().get(i));
            //给已完成的结点添加背景颜色
            if (i < fnum){
                list.get(i).setColor("green");
            }
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

        model.addAttribute("lists", list);
        model.addAttribute("fnum", fnum);
        model.addAttribute("total", total);
        return "two";
    }
}
