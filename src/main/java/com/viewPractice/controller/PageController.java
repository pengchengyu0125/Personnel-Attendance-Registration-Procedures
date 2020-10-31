package com.viewPractice.controller;

import com.viewPractice.dto.ErrorTypeDTO;
import com.viewPractice.dto.PageDTO;
import com.viewPractice.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 *分页详情Controller
 */

@Controller
public class PageController {
    @Autowired
    private PageService pageService;

    @GetMapping("/Manage")
    public String page(Model model,
                       @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @RequestParam(name = "size", defaultValue = "10") Integer size,
                       @RequestParam(name="visitName", required = false) String visitName,
                       @RequestParam(name="visitPhone", required = false) String visitPhone,
                       @RequestParam(name="startTime", required = false) String startTime,
                       @RequestParam(name="endTime", required = false) String endTime,
                       @RequestParam(name="status", defaultValue = "2") String status){
        //获取分页显示的信息
        PageDTO pages = pageService.list1(page, size, visitName, visitPhone, startTime, endTime, status);
        //数据为空错误
        ErrorTypeDTO errorTypeDTO = new ErrorTypeDTO();
        if (pages.getInfoDTOS() == null)
            return "redirect:/warning/"+ errorTypeDTO.getType3();
        //设置前端数据
        model.addAttribute("pages", pages);
        return "Manage";
    }

    @RequestMapping("/Manage/search")
    public String search(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name="visitName", required = false) String visitName,
            @RequestParam(name="visitPhone", required = false) String visitPhone,
            @RequestParam(name="startTime", required = false) String startTime,
            @RequestParam(name="endTime", required = false) String endTime,
            @RequestParam(name = "checkPhone", required = false) String checkPhone,
            @RequestParam(name = "status", defaultValue = "2") String status,
            Model model){
        if (checkPhone != null){
            checkPhone.split(",");
            String phoneList[] = checkPhone.split(",");
            pageService.updateStatus(phoneList);
        }
        //获取分页显示的信息
        PageDTO pages = pageService.list(page, size, visitName, visitPhone, startTime, endTime, status);
        //数据为空错误
        ErrorTypeDTO errorTypeDTO = new ErrorTypeDTO();
        if (pages.getInfoDTOS() == null)
            return "redirect:/warning/"+ errorTypeDTO.getType3();
        //设置前端数据
        model.addAttribute("pages", pages);
        return "search";
    }
}
