package com.example.demo.base.controller;
import com.example.demo.base.config.ControllerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import com.example.demo.base.utils.StateParameter;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseController {
    protected final String success = StateParameter.SUCCESS;
    protected final String fail = StateParameter.FAULT;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    private ControllerConfig controllerConfig;


    public ModelAndView viewModel(String viewName,  LinkedHashMap<String, Object> hashMap){

        ModelAndView viewModel = new ModelAndView();

        if (this.controllerConfig != null) {
            Map<String, String> map = this.controllerConfig.getConfig();
            map.forEach((k, v) -> {
                hashMap.put(k, v);
            });
        }

        viewModel.addAllObjects(hashMap);
        viewModel.setViewName(viewName);
        return viewModel;
    }

    public ModelAndView viewModel(String viewName) {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap();
        return viewModel(viewName, hashMap);
    }
    public ModelAndView viewModel(String viewName, String key1, Object val1) {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>() {
            {
                this.put(key1, val1);
            }
        };
        return this.viewModel(viewName, hashMap);
    }
}
