package com.daalgae.daalgaeproject.admin.service;

import com.daalgae.daalgaeproject.admin.dao.AdminMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AdminMapper mapper;

    public AdminServiceImpl(AdminMapper mapper) {
        this.mapper = mapper;
    }
}
