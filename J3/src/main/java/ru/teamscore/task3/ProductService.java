package ru.teamscore.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private final List<Product> productList;

    public ProductService(List<Product> productList) {
        this.productList = productList;
    }

    public List<SearchResult> search(String query) {
        List<SearchResult> results = new ArrayList<>();
        for (Product product : productList) {
            if (product.getArticle().equals(query) ||
                    product.getName().toLowerCase().contains(query.toLowerCase())) {
                results.add(new SearchResult(
                        product
                ));
            }
        }
        return results;
    }


    @Override
    public String toString() {
        return "ProductService{" +
                "productList=" + productList +
                '}';
    }
}
