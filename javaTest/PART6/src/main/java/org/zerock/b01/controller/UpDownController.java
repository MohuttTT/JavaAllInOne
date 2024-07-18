package org.zerock.b01.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.b01.dto.upload.UploadFileDTO;
import org.zerock.b01.dto.upload.UploadResultDTO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/*
    MultipartFile Interface?
    업로드된 파일을 나타내며, 파일의 메타데이터와 내용을 처리하는 다양한 메서드를 제공
    스프링 MVC에서 파일 업로드 요청을 처리할 때 주로 사용

    [주요 메서드]
        1. String getName() : 업로드된 파일 이름 반환 (HTML 폼에서 사용된 파일 입력 필드의 이름)
        2. String getOriginalFilename() : 업로드된 파일의 원본 이름 반환
            사용자가 파일을 업로드할 때의 파일 이름
        3. String getContentType() : 업로드된 파일의 콘텐츠 타입(MIME 타입)을 반환
            예를 들어, 이미지 파일이면 "image/jpeg"와 같은 값을 반환
        4. boolean imEmpty() : 업로드 파일이 비어 있는지 여부 반환
            파일이 없는 경우 true 반환, 파일이 존재하면 false 반환
        5. long getSize() : 업로드된 파일의 크기를 바이트 단위로 반환
        6. byte[] getBytes() : 업로드된 파일의 내용을 바이트 배열로 반환
        7. InputStream getInputStream() : 업로드된 파일의 내용을 읽을 수 있는 InputStream 반환
        8. void transferTo(File dest) : 업로드된 파일을 지정한 파일로 저장(전송)
            (실제 파일 저장을 하는 역할을 한다)
 */

/*
    Thumbnailator?
    자바에서 이미지를 처리하고 썸네일을 생성하는 작업을 단순화하는 오픈 소스 라이브러리
    해당 라이브러리는 썸네일 생성 작업을 간단하게 수행할 수 있도록 메서드를 제공

    Thumbnailator.createThumbnail?
        원본 이미지를 주어진 크기의 썸네일로 변환
    Thumbnailator.createThumbnail(File originalFile, File thumbnailFile, int width, int height)
        - 원본 이미지 파일 / - 생성될 썸네일 이미지 파일 / - 썸네일 너비 / - 썸네일 높이
 */

/*
    https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/MIME_types
    MIME 타입 -- 파일의 내용 유형을 나타내는 표준화된 방식
    MIME 타입은 주로 웹과 이메일에서 파일의 형식을 식별하고 처리하는 데 사용
    웹 서버와 클라이언트(웹 브라우저) 간의 파일 전송, 이메일 첨부 파일의 식별 등에 사용
    일반적으로 대분류/소분류 형식으로 나타냄
        예) JPEG 형식의 MIME 타입 -- image(대분류)/jepg(소분류)

    [대분류] -- 파일 유형
    "text", "image", "audio", "video", "application" 등
    [소분류] -- 파일 구체적 형식
    "plain"(텍스트 파일), "jpeg", "jpg", "mp3", "json" 등
 */

/*
    FileSystemResource?
    파일 시스템에서 특정 경로에 있는 파일을 참조하는 리소스를 생성
    파일의 내용을 읽거나 쓰는 등의 작업을 수행할 수 있음

    [주요 사용 사례]
    - 파일 다운로드 : 서버에서 클라이언트로 파일을 제공할 때 사용
    - 파일 읽기/쓰기 : 파일의 내용을 읽거나 쓸 때 사용
 */


@RestController
@Log4j2
public class UpDownController {

    // Spring에서 제공하는 @Value 어노테이션을 이용하면 properties에 선언한 값을 주입받을 수 있음
    @Value("${org.zerock.upload.path}")
    private String uploadPath; // 업로드 파일의 저장 경로를 담는 변수

    // API 문서화를 위한 설명
    @Operation(summary = "Upload POST", description = "POST 방식으로 파일 등록")
    // /upload 경로로 POST 요청이 오면 해당 메서드가 실행
    // 요청의 내용 타입이 MULTIPART_FORM_DATA_VALUE임을 명시
    /*
        @Parameter?
        Swagger에서 API 메서드의 매개변수를 설명하는 데 사용

        content =
        content 속성은 매개변수의 콘텐츠 유형을 지정하는 데 사용
        Swagger는 API 요청이 어떤 형식의 데이터를 기대하는지 알 수 있음

        @Content?
        특정 콘텐츠 타입을 정의하는 데 사용
            여기서는 mediaType 속성을 사용하여 콘텐츠 타입을 지정

        MediaType.MULTIPART_FORM_DATA_VALUE?
        multipart/form-data를 의미
        -> 파일 업로드와 같은 폼 데이터를 처리하는 데 사용
        -> 스프링에서는 MediaType 클래스로 다양한 미디어 타입 상수를 제공
            그 중 MULTIPART_FORM_DATA_VALUE는 파일 업로드에 사용되는 MIME 타입을 나타냄
     */

    /*
        multipart/form-data?
        웹 애플리케이션에서 여러 종류의 데이터를 포함하는 폼 데이터를 전송하기 위해 사용하는 HTTP 요청 종류
        특히, 파일 업로드와 같은 바이너리 데이터 전송할 때 지정해 주어야 함
        -> 하나의 요청 안에 여러 개의 파트(part)를 포함하여 여러 형식 데이터를 보낼 수 있도록 함
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(@Parameter(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) UploadFileDTO uploadFileDTO) {

        log.info(uploadFileDTO); // 업로드된 파일의 정보를 로그로 출력합니다.

        // 업로드된 파일이 존재할 경우에만 처리
        if (uploadFileDTO.getFiles() != null) {
            // 결과를 담을 리스트를 초기화 (파일 업로드 결과)
            final List<UploadResultDTO> list = new ArrayList<>();

            // 업로드된 파일 목록을 반복 처리 (파일 업로드가 여러 개일 수도 있으니까)
            uploadFileDTO.getFiles().forEach(multipartFile -> {
                // getOriginalFilename() : 파일 원본 이름 가져오는 메서드
                String originalName = multipartFile.getOriginalFilename();
                log.info(originalName);

                // UUID 생성
                String uuid = UUID.randomUUID().toString();

                // 파일 저장 경로 생성
                Path savePath = Paths.get(uploadPath, uuid + "_" + originalName);

                // 파일이 이미지인지 여부를 나타내는 변수 초기화
                boolean image = false;

                try {
                    // 실제 파일을 지정된 경로에 저장
                    multipartFile.transferTo(savePath);

                    // 해당 파일의 MIME 타입이 이미지인지 확인
                    if (Files.probeContentType(savePath).startsWith("image")) {
                        // 이미지 파일일 경우 true로 설정
                        image = true;
                        // 썸네일 파일 경로 생성
                        File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalName);
                        // 썸네일 이미지 생성
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                    }
                } catch (IOException e) {
                    // 예외 발생 처리
                    e.printStackTrace(); 
                }

                // 업로드 결과를 리스트에 추가
                list.add(UploadResultDTO.builder()
                        .uuid(uuid)
                        .fileName(originalName)
                        .img(image)
                        .build()
                );
            }); // end each

            // 업로드 결과 리스트를 반환
            return list;

        } // end if

        // 업로드된 파일이 없을 경우 null을 반환
        return null;
    }

    // API 문서화를 위한 설명
    @Operation(summary = "view 파일", description = "GET 방식으로 첨부파일 조회")
    // /view/{fileName} 경로로 GET 요청이 오면 해당 메서드가 실행
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName) {
        // 파일 시스템 리소스를 생성
        /*
            1. 파일 시스템의 경로를 생성 : uploadPath + File.separator + fileName
            2. 생성자의 해당 인자 값을 통해 파일 시스템 리소스 생성 : FileSystemResource(파일 전체 경로)
         */
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
        // 파일 이름 가져옴
        String resourceName = resource.getFilename(); // 파일 이름을 가져옵니다.
        // HTTP 헤더 초기화
        HttpHeaders headers = new HttpHeaders();

        /*
            HTTP 헤더 설정하는 이유
            클라이언트와 서버 간의 HTTP 요청과 응답에서 파일 다운로드와 같은 경우 올바른 헤더를 설정해 주어야 함
            파일의 MIME 타입이 무엇인지 지정하여 파일 형식을 올바르게 해석할 수 있도록 함
            MIME 타입을 통해 클라이언트는 응답 본문을 어떻게 처리할지 결정할 수 있음
                -> 예를 들어, 브라우저는 HTTP 응답 헤더에 image/jpeg라는 MIME 타입을 받은 경우 이미지를 표시하려고 동작하고, application/pdf MIME 타입을 받은 경우 PDF 뷰어를 통해 파일을 표시하려고 함
                즉, 올바른 Content-Type을 지정해 주지 않으면 클라이언트 측에서 응답 결과의 파일을 잘못 처리할 수 있음
         */
        try {
            // 파일의 MIME 타입을 HTTP 헤더에 추가
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            // 예외 발생 시 500 내부 서버 오류를 반환
            return ResponseEntity.internalServerError().build();
        }

        // 정상적으로 파일을 반환
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    // API 문서화를 위한 설명
    @Operation(summary = "remove 파일", description = "DELETE 방식으로 파일 삭제")
    // /remove/{fileName} 경로로 DELETE 요청이 오면 해당 메서드가 실행
    @DeleteMapping("/remove/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable String fileName) {
        // 파일 시스템 리소스 생성
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
        // 파일 이름 가져옴
        String resourceName = resource.getFilename();
        // 결과를 담을 맵을 초기화
        Map<String, Boolean> resultMap = new HashMap<>();
        // 파일 삭제 여부 나타내는 변수 초기화
        boolean removed = false;

        try {
            // 파일의 MIME 타입을 가져옴
            String contentType = Files.probeContentType(resource.getFile().toPath());
            // 파일 삭제
            removed = resource.getFile().delete();

            // 파일이 이미지 파일일 경우 -- 만들어진 썸네일 파일도 함께 삭제되어야 함
            if (contentType.startsWith("image")) {
                // 썸네일 파일 경로 생성
                File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);
                // 썸네일 파일 삭제
                thumbnailFile.delete();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        // 파일 삭제 결과를 맵에 추가
        resultMap.put("result", removed);

        // 파일 삭제 결과 맵을 반환합니다.
        return resultMap;
    }

}
