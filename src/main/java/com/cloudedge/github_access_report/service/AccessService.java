package com.cloudedge.github_access_report.service;

import com.cloudedge.github_access_report.client.GithubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccessService {

    @Autowired
    private GithubClient githubClient;

    public Map<String, List<String>> getAccessReport(String org) {

    List<Map<String, Object>> repos;

  
    if (org.contains("-") || org.equalsIgnoreCase("Shivam380-bot")) {
        repos = githubClient.getUserRepos(org);
    } else {
        repos = githubClient.getRepos(org);
    }

    Map<String, List<String>> userRepoMap = new HashMap<>();

    for (Map<String, Object> repo : repos) {
        String repoName = (String) repo.get("name");

        List<Map<String, Object>> users =
                githubClient.getContributors(org, repoName);

        if (users == null) continue;

        for (Map<String, Object> user : users) {
            String username = (String) user.get("login");

            userRepoMap
                    .computeIfAbsent(username, k -> new ArrayList<>())
                    .add(repoName);
        }
    }

    return userRepoMap;
}
}