package com.productcatalogservice.clients.fakestoreclient;

import com.productcatalogservice.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreApiClient {
    private final String endpoint = "https://fakestoreapi.com";

    private RestTemplateBuilder restTemplateBuilder ;

    public FakeStoreApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> response= requestForEntity(endpoint + "/products",HttpMethod.GET,null, FakeStoreProductDto[].class);
        return Arrays.stream(response.getBody()).toList();
    }

    public FakeStoreProductDto addProduct(FakeStoreProductDto productDto) {
        ResponseEntity<FakeStoreProductDto> response= requestForEntity(endpoint + "/products",HttpMethod.POST,productDto, FakeStoreProductDto.class);
        return response.getBody();
    }

    public FakeStoreProductDto getProductById(Long productId) {

        ResponseEntity<FakeStoreProductDto> response = requestForEntity(endpoint + "/products/{productId}", HttpMethod.GET, null, FakeStoreProductDto.class, productId);
        return response.getBody();
    }

    public FakeStoreProductDto updateProduct(Long productId,FakeStoreProductDto productDto) {
        ResponseEntity<FakeStoreProductDto> response= requestForEntity(endpoint+"/products/{productId}",HttpMethod.PATCH, productDto,FakeStoreProductDto.class,productId);
        return response.getBody();
    }

    public FakeStoreProductDto replaceProduct(Long productId,FakeStoreProductDto productDto) {
        ResponseEntity<FakeStoreProductDto> response= requestForEntity(endpoint+"/products/{productId}",HttpMethod.PUT, productDto,FakeStoreProductDto.class,productId);
        return response.getBody();
    }

    public FakeStoreProductDto deleteProduct(Long productId) {
        ResponseEntity<FakeStoreProductDto> response=requestForEntity(endpoint+"/products/{productId}",HttpMethod.DELETE,null,FakeStoreProductDto.class,productId);
        return response.getBody();
    }

    public List<String> getCategories() {
        ResponseEntity<String[]> response= requestForEntity(endpoint+"/products/categories",HttpMethod.GET,null,String[].class);
        return Arrays.asList(response.getBody());
    }

    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod method, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, method, requestCallback, responseExtractor, uriVariables);
    }
}
