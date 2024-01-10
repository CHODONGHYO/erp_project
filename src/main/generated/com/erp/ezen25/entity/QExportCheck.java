package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExportCheck is a Querydsl query type for ExportCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExportCheck extends EntityPathBase<ExportCheck> {

    private static final long serialVersionUID = 73977790L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExportCheck exportCheck = new QExportCheck("exportCheck");

    public final NumberPath<Long> exportCheckId = createNumber("exportCheckId", Long.class);

    public final StringPath exportCheckStatus = createString("exportCheckStatus");

    public final StringPath orderCode = createString("orderCode");

    public final NumberPath<Long> orderNum = createNumber("orderNum", Long.class);

    public final QProduct_Info productId;

    public QExportCheck(String variable) {
        this(ExportCheck.class, forVariable(variable), INITS);
    }

    public QExportCheck(Path<? extends ExportCheck> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExportCheck(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExportCheck(PathMetadata metadata, PathInits inits) {
        this(ExportCheck.class, metadata, inits);
    }

    public QExportCheck(Class<? extends ExportCheck> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productId = inits.isInitialized("productId") ? new QProduct_Info(forProperty("productId"), inits.get("productId")) : null;
    }

}

