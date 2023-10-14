package com.shop.clothing;


import com.shop.clothing.file.IFileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ClothingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@ExtendWith(SpringExtension.class)

public class TestFileService {
    @Autowired
    private  IFileService fileService;

    @Test
    public void shouldDelete(){
       var image ="https://res.cloudinary.com/dkvga054t/image/upload/v1696080545/clothing/f7a3245e-b0ee-4d1f-87e8-0333bf80906e.png.png";
        Assertions.assertDoesNotThrow(() -> {
            fileService.deleteFile(image);
        }   );
    }
}
