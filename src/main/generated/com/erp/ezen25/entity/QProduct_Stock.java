package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduct_Stock is a Querydsl query type for Product_Stock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct_Stock extends EntityPathBase<Product_Stock> {

    private static final long serialVersionUID = 1903684400L;

    public static final QProduct_Stock product_Stock = new QProduct_Stock("product_Stock");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> pNumId = createNumber("pNumId", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final NumberPath<Long> productNum = createNumber("productNum", Long.class);

    public final NumberPath<Long> totalPrice = createNumber("totalPrice", Long.class);

    public QProduct_Stock(String variable) {
        super(Product_Stock.class, forVariable(variable));
    }

    public QProduct_Stock(Path<? extends Product_Stock> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct_Stock(PathMetadata metadata) {
        super(Product_Stock.class, metadata);
    }

}

