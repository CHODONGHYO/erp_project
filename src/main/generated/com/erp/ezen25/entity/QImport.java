package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QImport is a Querydsl query type for Import
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImport extends EntityPathBase<Import> {

    private static final long serialVersionUID = 1402771291L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImport import$ = new QImport("import$");

    public final DateTimePath<java.time.LocalDateTime> importDate = createDateTime("importDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> importId = createNumber("importId", Long.class);

    public final NumberPath<Long> importNum = createNumber("importNum", Long.class);

    public final StringPath importStatus = createString("importStatus");

    public final QProduct_Info productId;

    public final StringPath requestCode = createString("requestCode");

    public QImport(String variable) {
        this(Import.class, forVariable(variable), INITS);
    }

    public QImport(Path<? extends Import> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QImport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QImport(PathMetadata metadata, PathInits inits) {
        this(Import.class, metadata, inits);
    }

    public QImport(Class<? extends Import> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productId = inits.isInitialized("productId") ? new QProduct_Info(forProperty("productId"), inits.get("productId")) : null;
    }

}

