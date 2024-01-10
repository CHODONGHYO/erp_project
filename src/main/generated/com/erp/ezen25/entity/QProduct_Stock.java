package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct_Stock is a Querydsl query type for Product_Stock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct_Stock extends EntityPathBase<Product_Stock> {

    private static final long serialVersionUID = 1903684400L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct_Stock product_Stock = new QProduct_Stock("product_Stock");

    public final QMember memberId;

    public final NumberPath<Long> pNumId = createNumber("pNumId", Long.class);

    public final QProduct_Info productId;

    public final NumberPath<Long> productNum = createNumber("productNum", Long.class);

    public final NumberPath<Long> totalPrice = createNumber("totalPrice", Long.class);

    public QProduct_Stock(String variable) {
        this(Product_Stock.class, forVariable(variable), INITS);
    }

    public QProduct_Stock(Path<? extends Product_Stock> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct_Stock(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct_Stock(PathMetadata metadata, PathInits inits) {
        this(Product_Stock.class, metadata, inits);
    }

    public QProduct_Stock(Class<? extends Product_Stock> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberId = inits.isInitialized("memberId") ? new QMember(forProperty("memberId")) : null;
        this.productId = inits.isInitialized("productId") ? new QProduct_Info(forProperty("productId"), inits.get("productId")) : null;
    }

}

