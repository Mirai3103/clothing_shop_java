package com.shop.clothing.rating.endpoint;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.common.dto.Paginated;
import com.shop.clothing.rating.dto.RatingDto;
import com.shop.clothing.rating.query.getAllRatingOfProductId.GetAllRatingOfProductIdQuery;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rating")
public class RatingApiController {
    private final ISender sender;

    @GetMapping()
    public ResponseEntity<Paginated<RatingDto>> getAllRatingOfProduct(@ParameterObject GetAllRatingOfProductIdQuery query) {
        var result = sender.send(query);
        return ResponseEntity.ok(result.orThrow());
    }
}
