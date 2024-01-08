package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExport is a Querydsl query type for Export
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExport extends EntityPathBase<Export> {

    private static final long serialVersionUID = 1298413418L;

    public static final QExport export = new QExport("export");

    public final DateTimePath<java.time.LocalDateTime> exportDate = createDateTime("exportDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> exportId = createNumber("exportId", Long.class);

    public final NumberPath<Long> exportNum = createNumber("exportNum", Long.class);

    public final StringPath exportStatus = createString("exportStatus");

    public final StringPath orderCode = createString("orderCode");

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public QExport(String variable) {
        super(Export.class, forVariable(variable));
    }

    public QExport(Path<? extends Export> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExport(PathMetadata metadata) {
        super(Export.class, metadata);
    }

}

