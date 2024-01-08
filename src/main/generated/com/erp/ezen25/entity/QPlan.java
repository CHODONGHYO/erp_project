package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlan is a Querydsl query type for Plan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlan extends EntityPathBase<Plan> {

    private static final long serialVersionUID = 1731273823L;

    public static final QPlan plan = new QPlan("plan");

    public final NumberPath<Long> brandId = createNumber("brandId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> completeDate = createDateTime("completeDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> planId = createNumber("planId", Long.class);

    public final NumberPath<Long> planNumber = createNumber("planNumber", Long.class);

    public final StringPath planStatus = createString("planStatus");

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public QPlan(String variable) {
        super(Plan.class, forVariable(variable));
    }

    public QPlan(Path<? extends Plan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlan(PathMetadata metadata) {
        super(Plan.class, metadata);
    }

}

