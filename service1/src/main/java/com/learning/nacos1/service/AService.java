package com.learning.nacos1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hongyu
 */
@Service
@RequiredArgsConstructor
public class AService {

    @Autowired
    private BService bService;
}
