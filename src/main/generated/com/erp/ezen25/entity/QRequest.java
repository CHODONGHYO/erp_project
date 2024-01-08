package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRequest is a Querydsl query type for Request
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRequest extends EntityPathBase<Request> {

    private static final long serialVersionUID = -294107751L;

    public static final QRequest request = new QRequest("request");

    public final NumberPath<Long> brandId = createNumber("brandId", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath requestCode = createString("requestCode");

    public final DateTimePath<java.time.LocalDateTime> requestDate = createDateTime("requestDate", java.time.LocalDateTime.class);

    public final StringPath requestDescription = createString("requestDescription");

    public final NumberPath<Long> requestId = createNumber("requestId", Long.class);

    public final NumberPath<Long> requestNum = createNumber("requestNum", Long.class);

    public final DateTimePath<java.time.LocalDateTime> requestOutDate = createDateTime("requestOutDate", java.time.LocalDateTime.class);

    public final StringPath requestStatus = createString("requestStatus");

    public QRequest(String variable) {
        super(Request.class, forVariable(variable));
    }

    public QRequest(Path<? extends Request> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRequest(PathMetadata metadata) {
        super(Request.class, metadata);
    }

}

