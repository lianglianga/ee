package com.liangliang.bookmanager.service.impl;

import com.liangliang.bookmanager.bean.Type;
import com.liangliang.bookmanager.mapper.TypeMapper;
import com.liangliang.bookmanager.repository.TypeRepository;
import com.liangliang.bookmanager.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TypeServiceImpl implements TypeService{


    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<Type> getTypeList() throws Exception {
        List<Type> typeList = new ArrayList<>();
        try {
//            typeList = typeMapper.getTypeList();
            typeList = typeRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return typeList;
    }


    @Override
    public Type getTypeById(int typeId) throws Exception {
       Type type = new Type();

        try {
//            type = typeMapper.getTypeById(typeId);
            type = typeRepository.findOne(typeId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return type;
    }
}
