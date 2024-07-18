package org.zerock.b01.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/* 업로드 결과 반환 처리 DTO */
public class UploadResultDTO {

    private String uuid;

    private String fileName;

    private boolean img;

    public String getLink() {

        if(img) {
            return "s_" + uuid + "_" + fileName;    // 이미지인 경우 썸네일 이미지 링크 반환
        } else {
            return uuid + "_" + fileName;
        }
    }
}
