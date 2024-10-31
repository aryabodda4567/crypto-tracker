package org.project.sih.core.utility;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class WebClientService {

    private final WebClient webClient;

    public WebClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://anapioficeandfire.com").build();
    }

    public <T> Mono<T> get(String path, Class<T> responseType) {
        return webClient.get()
                .uri(path)
                .retrieve()
                .bodyToMono(responseType);
    }

    public <T> Mono<T> post(String path, Object body, Class<T> responseType) {
        return webClient.post()
                .uri(path)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(responseType);
    }

    // For unknown response types, use Map
    public Mono<Map> get(String path) {
        return webClient.get()
                .uri(path)
                .retrieve()
                .bodyToMono(Map.class);
    }

    // For unknown response types, use Map
    public Mono<Map> post(String path, Object body) {
        return webClient.post()
                .uri(path)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class);
    }
}