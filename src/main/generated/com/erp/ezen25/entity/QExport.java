package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExport is a Querydsl query type for Export
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExport extends EntityPathBase<Export> {

    private static final long serialVersionUID = 1298413418L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExport export = new QExport("export");

    public final StringPath exportDate = createString("exportDate");

    public final NumberPath<Long> exportId = createNumber("exportId", Long.class);

    public final NumberPath<Long> exportNum = createNumber("exportNum", Long.class);

    public final StringPath orderCode = createString("orderCode");

    public final QProduct_Info productId;

    public QExport(String variable) {
        this(Export.class, forVariable(variable), INITS);
    }

    public QExport(Path<? extends Export> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExport(PathMetadata metadata, PathInits inits) {
        this(Export.class, metadata, inits);
    }

    public QExport(Class<? extends Export> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productId = inits.isInitialized("productId") ? new QProduct_Info(forProperty("productId"), inits.get("productId")) : null;
    }

}

