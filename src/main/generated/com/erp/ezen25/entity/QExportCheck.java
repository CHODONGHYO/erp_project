package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExportCheck is a Querydsl query type for ExportCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExportCheck extends EntityPathBase<ExportCheck> {

    private static final long serialVersionUID = 73977790L;

    public static final QExportCheck exportCheck = new QExportCheck("exportCheck");

    public final NumberPath<Long> exportCheckId = createNumber("exportCheckId", Long.class);

    public final StringPath exportCheckStatus = createString("exportCheckStatus");

    public final StringPath orderCode = createString("orderCode");

    public final NumberPath<Long> orderNum = createNumber("orderNum", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public QExportCheck(String variable) {
        super(ExportCheck.class, forVariable(variable));
    }

    public QExportCheck(Path<? extends ExportCheck> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExportCheck(PathMetadata metadata) {
        super(ExportCheck.class, metadata);
    }

}

