package com.shop.clothing.stockReceipt.endpoint;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.common.dto.Paginated;
import com.shop.clothing.stockReceipt.command.stockReceipt.createStockReceipt.CreateStockReceiptCommand;
import com.shop.clothing.stockReceipt.command.stockReceipt.deleteStockReceipt.DeleteStockReceiptCommand;
import com.shop.clothing.stockReceipt.dto.StockReceiptBriefDto;
import com.shop.clothing.stockReceipt.query.getAllStockReceipts.GetAllStockReceiptsQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock-receipt")
@AllArgsConstructor
public class StockReceiptApiController {
    private final ISender sender;
    // delete and create receipt

    @PostMapping("/")
    public ResponseEntity<Integer> createReceipt(@Valid CreateStockReceiptCommand command) throws Exception {
        var result = sender.send(command);
        return ResponseEntity.created(new java.net.URI("/api/stock-receipt/" + result.orThrow())).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceipt( @PathVariable int id)  {
        var result = sender.send(new DeleteStockReceiptCommand(id)).orThrow();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/")
    public ResponseEntity<Paginated<StockReceiptBriefDto>> getAllReceipts(@ParameterObject GetAllStockReceiptsQuery query)  {
        var result = sender.send(query).orThrow();
        return ResponseEntity.ok(result);
    }
}
