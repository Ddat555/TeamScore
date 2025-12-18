package ru.teamscore.task3;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private final ProductService productService = new ProductService(List.of(
            new Product("A1", "N1", BigDecimal.valueOf(500),
                    new Manufacturer("111", "N1", "A1"),
                    new Manufacturer("222", "N2", "A2")),
            new Product("A2", "N2", BigDecimal.valueOf(250),
                    new Manufacturer("111", "N1", "A1"),
                    new Dealer("222", "N2", "A2",new Manufacturer("111", "N1", "A1"), 25.0)),
            new Product("A3", "N2_1", BigDecimal.valueOf(390),
                    new Manufacturer("111", "N1", "A1"),
                    new Manufacturer("222", "N2", "A2")),
            new Product("A4", "N4", BigDecimal.valueOf(100),
                    new Manufacturer("111", "N1", "A1"),
                    new Manufacturer("222", "N2", "A2"))
    ));

    @Test
    void searchByName() {
        productService.search("2");
        assertEquals("[SearchResult{article='A2', nameProduct='N2', finalPrice=312.50, supplierName='N2', address='A2', manufacturerName='N1'}, SearchResult{article='A3', nameProduct='N2_1', finalPrice=390, supplierName='N2', address='A2', manufacturerName='N1'}]", productService.search("2").toString());
    }

    @Test
    void searchByArticle(){
        assertEquals("[SearchResult{article='A2', nameProduct='N2', finalPrice=312.50, supplierName='N2', address='A2', manufacturerName='N1'}]", productService.search("A2").toString());

    }
}