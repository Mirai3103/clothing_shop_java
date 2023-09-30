package com.shop.clothing.file.command.uploadFile;

import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.file.CloudinaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UploadFileCommandHandler implements IRequestHandler<UploadFileCommand, String> {
    private final CloudinaryService cloudinaryService;

    @Override
    public HandleResponse<String> handle(UploadFileCommand uploadFileCommand) {
        try {
            return HandleResponse.ok(cloudinaryService.uploadFile(uploadFileCommand.getFile()));
        } catch (Exception e) {
            return HandleResponse.error(e.getMessage());
        }
    }
}
