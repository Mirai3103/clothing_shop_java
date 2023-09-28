package com.shop.clothing.controller.api;

import com.shop.clothing.category.CategoryDto;
import com.shop.clothing.category.commands.createCategory.CreateCategoryCommand;
import com.shop.clothing.category.commands.updateCategory.UpdateCategoryCommand;
import com.shop.clothing.category.queries.getAllCategoriesQueries.GetAllCategoriesQueries;
import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.common.dto.Paginated;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController()
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryApiController {
    private final ISender sender;
    private final ModelMapper modelMapper;

    @GetMapping()
    public Paginated<CategoryDto> getCategories(@Valid GetAllCategoriesQueries paginationRequest) {
        return sender.send(paginationRequest).get();
    }

    @PostMapping("/create")
    public ResponseEntity createCategory(@Valid @RequestBody CreateCategoryCommand createCategoryRequest) {
        var result = sender.send(createCategoryRequest);
        if (result.isOk()) {
            return ResponseEntity.ok(result.get());
        }
        return  ResponseEntity.badRequest().body(result.getError());
    }
    @PutMapping("/update")
    @PostAuthorize("hasAuthority('MANAGE_CATEGORIES')")
    public ResponseEntity createCategory(@Valid @RequestBody UpdateCategoryCommand updateCategoryRequest) {
        var result = sender.send(updateCategoryRequest);
        if (result.isOk()) {
            return ResponseEntity.ok(modelMapper.map(result.get(), CategoryDto.class));
        }
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("error", result.getError());
        return ResponseEntity.badRequest().body(responseBody);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    @PostAuthorize("hasAuthority('MANAGE_CATEGORIES')")
    public void deleteCategory(@Param("id") int id) {
//        sender.send(new UpdateCategoryCommand(id, true));


    }
}
