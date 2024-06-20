package org.zerock.w2.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

// 생성자 관련 어노테이션 추가 후 ModelMapper의 설정을 변경하고 사용하기 위한 클래스 생성
public enum MapperUtil {
    INSTANCE;

    private ModelMapper moderlMapper;
    MapperUtil() {
        this.moderlMapper = new ModelMapper();
        this.moderlMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE).setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ModelMapper get() {
        return moderlMapper;
    }
}
