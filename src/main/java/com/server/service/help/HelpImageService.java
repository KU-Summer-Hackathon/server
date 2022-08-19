package com.server.service.help;

import com.server.common.exception.ValidationException;
import com.server.common.type.FileType;
import com.server.domain.help.Help;
import com.server.domain.help.HelpImage;
import com.server.domain.help.repository.HelpImageRepository;
import com.server.service.image.provider.S3Provider;
import com.server.service.image.provider.dto.request.ImageUploadFileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.server.common.exception.ErrorCode.VALIDATION_EXCEPTION;

@RequiredArgsConstructor
@Service
public class HelpImageService {

    private final HelpImageRepository helpImageRepository;
    private final S3Provider s3Provider;

    @Transactional
    public void addHelpImages(Help help, List<MultipartFile> images) {
        if (images.isEmpty()) {
            throw new ValidationException("저장할 이미지 파일이 없습니다.", VALIDATION_EXCEPTION);
        }

        helpImageRepository.saveAll(
                images.stream()
                        .map(imageFile -> s3Provider.uploadFile(ImageUploadFileRequest.of(FileType.ROOM_PRIVATE_IMAGE), imageFile))
                        .map(imageUrl -> HelpImage.of(imageUrl, help))
                        .collect(Collectors.toList())
        );
    }
}
