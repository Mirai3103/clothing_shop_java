package com.shop.clothing.file.endpoint;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.file.command.uploadFile.UploadFileCommand;
import com.shop.clothing.file.command.uploadFiles.UploadFilesCommand;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/api/file")
public class FileController {
    private final ISender sender;

    public FileController(ISender sender) {
        this.sender = sender;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file) {
        var command = new UploadFileCommand(file);
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }
    @PostMapping("/uploads")
    public ResponseEntity<Collection<String>> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        var command = new UploadFilesCommand(files);
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @GetMapping("/{name}")
    public void  getFile(@PathVariable String name, HttpServletResponse response) throws IOException {
        var file = new java.io.File(name);
        if (!file.exists()) {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND);
        }

        response.setContentLength((int) file.length());
        response.setContentType("image/jpeg");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        org.springframework.util.FileCopyUtils.copy(new java.io.FileInputStream(file), response.getOutputStream());
    }
}
