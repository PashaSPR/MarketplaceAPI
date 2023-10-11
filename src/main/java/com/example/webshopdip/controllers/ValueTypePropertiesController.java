package com.example.webshopdip.controllers;

import com.example.webshopdip.enums.ValueTypePropertiesEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/valuetypeproperties")
@CrossOrigin
public class ValueTypePropertiesController {
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ValueTypePropertiesEnum.ValueType> valueTypeList = Arrays.asList(ValueTypePropertiesEnum.ValueType.values());
//        for (ValueTypePropertiesEnum.ValueType valueType : valueTypeList){
//            System.out.println("valueType: " + valueType);
//        }
        return new ResponseEntity<>(valueTypeList, HttpStatus.OK);
    }
}
