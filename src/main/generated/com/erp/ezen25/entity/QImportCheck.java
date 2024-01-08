package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QImportCheck is a Querydsl query type for ImportCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImportCheck extends EntityPathBase<ImportCheck> {

    private static final long serialVersionUID = -657211795L;

    public static final QImportCheck importCheck = new QImportCheck("importCheck");

    public final NumberPath<Long> importCheckId = createNumber("importCheckId", Long.class);

    public final StringPath importCheckStatus = createString("importCheckStatus");

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath requestCode = createString("requestCode");

    public final NumberPath<Long> requestNum = createNumber("requestNum", Long.class);

    public QImportCheck(String variable) {
        super(ImportCheck.class, forVariable(variable));
    }

    public QImportCheck(Path<? extends ImportCheck> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImportCheck(PathMetadata metadata) {
        super(ImportCheck.class, metadata);
    }

}

