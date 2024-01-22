package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QImportCheck is a Querydsl query type for ImportCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImportCheck extends EntityPathBase<ImportCheck> {

    private static final long serialVersionUID = -657211795L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImportCheck importCheck = new QImportCheck("importCheck");

    public final NumberPath<Long> importCheckId = createNumber("importCheckId", Long.class);

    public final StringPath importCheckStatus = createString("importCheckStatus");

    public final QImport importId;

    public QImportCheck(String variable) {
        this(ImportCheck.class, forVariable(variable), INITS);
    }

    public QImportCheck(Path<? extends ImportCheck> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QImportCheck(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QImportCheck(PathMetadata metadata, PathInits inits) {
        this(ImportCheck.class, metadata, inits);
    }

    public QImportCheck(Class<? extends ImportCheck> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.importId = inits.isInitialized("importId") ? new QImport(forProperty("importId"), inits.get("importId")) : null;
    }

}

