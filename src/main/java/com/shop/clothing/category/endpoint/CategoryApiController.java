package com.shop.clothing.category.endpoint;

import com.shop.clothing.category.CategoryDto;
import com.shop.clothing.category.commands.createCategory.CreateCategoryCommand;
import com.shop.clothing.category.commands.deleteCategory.DeleteCategoryCommand;
import com.shop.clothing.category.commands.updateCategory.UpdateCategoryCommand;
import com.shop.clothing.category.queries.getAllCategoriesQueries.GetAllCategoriesQueries;
import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.common.dto.Paginated;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController()
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryApiController {
    private final ISender sender;

    @GetMapping()
    public Paginated<CategoryDto> getCategories(@Valid @ParameterObject GetAllCategoriesQueries paginationRequest) {
        return sender.send(paginationRequest).get();
    }

    @PostMapping("/create")
    @PostAuthorize("hasAuthority('MANAGE_CATEGORIES')")
    public ResponseEntity<Integer> createCategory(@Valid @RequestBody CreateCategoryCommand createCategoryRequest) {
        var result = sender.send(createCategoryRequest);
        return ResponseEntity.ok(result.orThrow());

    }

    @PutMapping("/update")
    @PostAuthorize("hasAuthority('MANAGE_CATEGORIES')")
    @Secured("MANAGE_CATEGORIES")
    public ResponseEntity<Boolean> createCategory(@Valid @RequestBody UpdateCategoryCommand updateCategoryRequest) {
        var result = sender.send(updateCategoryRequest);
        return ResponseEntity.ok(result.orThrow());

    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    @PostAuthorize("hasAuthority('MANAGE_CATEGORIES')")
    public void deleteCategory(@PathVariable String id) {
        if (id.isBlank()) throw new IllegalArgumentException("id is null");
        var result = sender.send(new DeleteCategoryCommand(Integer.parseInt(id)));
        ResponseEntity.ok(result.orThrow());
    }
}
