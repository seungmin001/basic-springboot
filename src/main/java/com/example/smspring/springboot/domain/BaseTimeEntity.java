package com.example.smspring.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 공통매핑정보가 필요할 때 / JPA Entity들이 상속할 경우 JPA가 여기 필드들도 column으로 인식
@EntityListeners(AuditingEntityListener.class) // Auditing 기능 
public abstract class BaseTimeEntity { // Entity들의 createdDate, modifiedDate 자동 관리

    @CreatedDate // Entity가 생성되어 저장될 때 시간 자동 저장
    private LocalDateTime createdDate;
    
    @LastModifiedDate //조회한 Entity의 값을 변경할 때 시간 자동 저장
    private LocalDateTime modifiedDate;
}
