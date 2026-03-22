package com.cloudedge.github_access_report.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class GithubClient {

    @Value("${github.token}")
    private String token;

    @Value("${github.base.url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Accept", "application/vnd.github+json");
        return headers;
    }

    // ORG repos
    public List<Map<String, Object>> getRepos(String org) {
        String url = baseUrl + "/orgs/" + org + "/repos";

        HttpEntity<String> entity = new HttpEntity<>(getHeaders());

        ResponseEntity<List> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                List.class
        );

        return response.getBody();
    }

    // USER repos (fallback)
    public List<Map<String, Object>> getUserRepos(String username) {
        String url = baseUrl + "/users/" + username + "/repos";

        HttpEntity<String> entity = new HttpEntity<>(getHeaders());

        ResponseEntity<List> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                List.class
        );

        return response.getBody();
    }

    // contributors (NOT collaborators ❗)
    public List<Map<String, Object>> getContributors(String owner, String repo) {
        String url = baseUrl + "/repos/" + owner + "/" + repo + "/contributors";

        HttpEntity<String> entity = new HttpEntity<>(getHeaders());

        ResponseEntity<List> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                List.class
        );

        return response.getBody();
    }
}