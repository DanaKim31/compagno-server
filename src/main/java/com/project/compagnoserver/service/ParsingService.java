package com.project.compagnoserver.service;

import com.project.compagnoserver.domain.Parsing;
import com.project.compagnoserver.domain.ParsingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParsingService {
    @Autowired
    private ParsingDAO dao;
    public Parsing create(Parsing vo){
        return dao.save(vo);
    }



}