package com.openlabs.shoppingmall.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    /** 생성자 */
    @CreatedBy
    @Column(name = "CRTR_ID", updatable = false)
    private String crtrId;
    /** 생성일시 */
    @CreatedDate
    @Column(name = "CRTE_TMSP", columnDefinition = "TIMESTAMP", updatable = false)
    private LocalDateTime crteTmsp;
    /** 수정자 */
    @LastModifiedBy
    @Column(name = "EDIR_ID")
    private String edirId;
    /** 수정일시 */
    @LastModifiedDate
    @Column(name = "EDIT_TMSP", columnDefinition = "TIMESTAMP")
    private LocalDateTime editTmsp;
    // 임시 생성, 수정자 생성
    @PrePersist
    public void onPreCreate() {
        if (this.crtrId == null) this.crtrId = "tempId";
        if (this.edirId == null) this.edirId = "tempId";
    }
    /**  */
    @PreUpdate
    public void onPreUpdate(){
        // 임시 수정자 수정
        if (this.edirId == null) this.edirId = "tempEditId";
        if (this.editTmsp == null) this.editTmsp = LocalDateTime.now();
    }
}
