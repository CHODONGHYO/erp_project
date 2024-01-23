package com.erp.ezen25.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "exportcheck")
public class ExportCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exportCheckId")
    private Long exportCheckId;

    @ManyToOne(targetEntity = Export.class, fetch = FetchType.LAZY)
    @JoinColumn(name="exportId", nullable = false)
    private Export exportId;

    @Column(length = 1000, nullable = false)
    @ColumnDefault("'미정'")
    private String exportCheckStatus;

    public void changeExportCheckStatus(String exportCheckStatus) {
        this.exportCheckStatus = exportCheckStatus;
    }

}
