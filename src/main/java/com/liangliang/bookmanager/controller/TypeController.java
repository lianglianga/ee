package com.liangliang.bookmanager.controller;


import com.liangliang.bookmanager.bean.Message;
import com.liangliang.bookmanager.bean.Type;
import com.liangliang.bookmanager.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping(value = "/getTypeList", method = RequestMethod.POST)
    @ResponseBody
    public Message getTypeList(){

        List<Type> typeList = new ArrayList<>();

        try {
            typeList = typeService.getTypeList();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(Message.ERROR,"获取失败！",null);
        }
        return new Message(Message.SUCCESS,"获取成功！",typeList);
    }


    @RequestMapping(value = "/getTypeById", method = RequestMethod.POST)
    @ResponseBody
    public Message getTypeById(@RequestParam("typeId") int typeId){

        Type type = new Type();

        try {
            type = typeService.getTypeById(typeId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(Message.ERROR,"获取信息失败！",null);
        }
        return new Message(Message.SUCCESS,"获取信息成功！",type);
    }


}
