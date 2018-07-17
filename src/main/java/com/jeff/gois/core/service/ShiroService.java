package com.jeff.gois.core.service;

import java.util.Map;

public interface ShiroService {

    Map<String, String> loadFilterChainDefinitions() ;

    void updatePermission();
}
