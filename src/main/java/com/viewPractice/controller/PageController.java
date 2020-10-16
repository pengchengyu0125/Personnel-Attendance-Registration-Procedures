package com.viewPractice.controller;

import com.viewPractice.dto.ErrorTypeDTO;
import com.viewPractice.dto.PageDTO;
import com.viewPractice.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/*
 *分页详情Controller
 */

@Controller
public class PageController {
    @Autowired
    private PageService pageService;

    @GetMapping("/paging")
    public String page(Model model,
                       @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @RequestParam(name = "size", defaultValue = "10") Integer size,
                       @RequestParam(name="visitName",required = false) String visitName,
                       @RequestParam(name="visitPhone",required = false) String visitPhone,
                       @RequestParam(name="startTime",required = false) String startTime,
                       @RequestParam(name="endTime",required = false) String endTime){
        //获取分页显示的信息
        PageDTO pages = pageService.list(page, size, visitName, visitPhone, startTime, endTime);
        //数据为空错误
        ErrorTypeDTO errorTypeDTO = new ErrorTypeDTO();
        if (pages.getInfoDTOS() == null)
            return "redirect:/warning/"+ errorTypeDTO.getType3();
        model.addAttribute("pages", pages);
        return "paging";
    }

    @PostMapping("/paging")
    public String checkInfo(
            @RequestParam("visitPhone") String visitPhone,
            Model model){
        model.addAttribute("visitPhone",visitPhone);
        visitPhone.split(",");
        String phoneList[] = visitPhone.split(",");
        pageService.updateStatus(phoneList);
        return "redirect:/paging";
    }
}
