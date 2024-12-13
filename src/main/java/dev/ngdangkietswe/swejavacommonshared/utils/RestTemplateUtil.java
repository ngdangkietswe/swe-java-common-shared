package dev.ngdangkietswe.swejavacommonshared.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author ngdangkietswe
 * @since 12/13/2024
 */

@RequiredArgsConstructor
@Log4j2
public class RestTemplateUtil {

    private final RestTemplate restTemplate;

    public <T> ResponseEntity<T> get(String apiUrl, Class<T> clazz) {
        return restTemplate.getForEntity(apiUrl, clazz);
    }

    public <T> ResponseEntity<T> post(String apiUrl, Object body, Class<T> clazz, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (StringUtils.hasText(token)) {
            headers.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));
        }

        var request = new HttpEntity<>(body, headers);

        return restTemplate.postForEntity(apiUrl, request, clazz);
    }

    public <T> ResponseEntity<T> postWithFormUrlEncoded(String apiUrl, MultiValueMap<String, String> body, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var request = new HttpEntity<>(body, headers);

        return restTemplate.postForEntity(apiUrl, request, clazz);
    }

    public <T> ResponseEntity<T> put(String apiUrl, Object body, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var request = new HttpEntity<>(body, headers);

        return restTemplate.exchange(apiUrl, HttpMethod.PUT, request, clazz);
    }
}
