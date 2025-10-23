package com.productcatalogservice.clients;

import com.productcatalogservice.dtos.FakeStoreProductResponseDto;
import com.productcatalogservice.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.naming.spi.ResolveResult;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;

public class FakeStoreApiClient {
    private final String endpoint = "https://fakestoreapi.com";
    private RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

    public List<ProductDto> getAllProducts() {
        ResponseEntity<ProductDto[]> response= requestForEntity(endpoint + "/products",HttpMethod.GET,null, ProductDto[].class);
        return Arrays.stream(response.getBody()).toList();
    }

    public ProductDto addProduct(ProductDto productDto) {
        ResponseEntity<ProductDto> response= requestForEntity(endpoint + "/products",HttpMethod.POST,productDto, ProductDto.class);
        return response.getBody();
    }

    public ProductDto getProductById(Long productId) {

        ResponseEntity<ProductDto> response = requestForEntity(endpoint + "/products/{productId}", HttpMethod.GET, null, ProductDto.class, productId);
        return response.getBody();
    }

    public ProductDto updateProduct(Long productId,ProductDto productDto) {
        ResponseEntity<ProductDto> response= requestForEntity(endpoint+"/products/{productId}",HttpMethod.PUT, productDto,ProductDto.class,productId);
        return response.getBody();
    }

    public ProductDto deleteProduct(Long productId) {
        ResponseEntity<ProductDto> response=requestForEntity(endpoint+"/products/{productId}",HttpMethod.DELETE,null,ProductDto.class,productId);
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
