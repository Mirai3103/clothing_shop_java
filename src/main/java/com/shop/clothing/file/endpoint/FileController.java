package com.shop.clothing.file.endpoint;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.file.command.uploadFile.UploadFileCommand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
@AllArgsConstructor
public class FileController {
    private final ISender sender;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        var command = new UploadFileCommand(file);
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }
}
