package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QImport is a Querydsl query type for Import
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImport extends EntityPathBase<Import> {

    private static final long serialVersionUID = 1402771291L;

    public static final QImport import$ = new QImport("import$");

    public final DateTimePath<java.time.LocalDateTime> importDate = createDateTime("importDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> importId = createNumber("importId", Long.class);

    public final NumberPath<Long> importNum = createNumber("importNum", Long.class);

    public final StringPath importStatus = createString("importStatus");

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath requestCode = createString("requestCode");

    public QImport(String variable) {
        super(Import.class, forVariable(variable));
    }

    public QImport(Path<? extends Import> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImport(PathMetadata metadata) {
        super(Import.class, metadata);
    }

}

