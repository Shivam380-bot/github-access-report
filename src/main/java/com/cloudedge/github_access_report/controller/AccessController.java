package com.cloudedge.github_access_report.controller;

import com.cloudedge.github_access_report.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccessController {

    @Autowired
    private AccessService accessService;

    @GetMapping("/access-report")
    public Map<String, List<String>> getReport(
            @RequestParam String org) {

        return accessService.getAccessReport(org);
    }
}