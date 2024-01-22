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
@Table(name = "importcheck")
public class ImportCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "importCheckId")
    private Long importCheckId;

    @ManyToOne(targetEntity = Import.class, fetch = FetchType.LAZY)
    @JoinColumn(name="importId", nullable = false)
    private Import importId;

    @Column(length = 1000, nullable = false)
    @ColumnDefault("'미완'")
    private String importCheckStatus;

    public void changeImportCheckStatus(String importCheckStatus) {
        this.importCheckStatus = importCheckStatus;
    }

}
