package com.napkinstudio.manager;

import com.napkinstudio.dao.IProcessDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessManager {
    @Autowired
    private IProcessDao processDao;
}
