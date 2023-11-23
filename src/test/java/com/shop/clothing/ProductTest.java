package com.shop.clothing;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.order.entity.enums.OrderStatus;
import com.shop.clothing.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ClothingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@ExtendWith(SpringExtension.class)
public class ProductTest {
    @Autowired
    private ISender sender;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testGetAllProducts() {
        assertDoesNotThrow(() -> {
            var query = new com.shop.clothing.product.query.getAllProducts.GetAllProductsQuery();
            query.setMaxPrice(200000);
            var result = sender.send(query).get();
            assertTrue(result.getData().stream().allMatch(product -> product.getPrice() < 200000));

        });
    }
    @Test
    @Transactional
    public void updateProductTotalSold() {
        assertDoesNotThrow(() -> {
            productRepository.updateTotalSold(new OrderStatus[]{OrderStatus.DELIVERED, OrderStatus.SHIPPING});
            productRepository.updateTotalToZeroWhereSoldIsNull();
        });
    }
}
