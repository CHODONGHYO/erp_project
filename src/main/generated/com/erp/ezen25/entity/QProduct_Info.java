package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduct_Info is a Querydsl query type for Product_Info
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct_Info extends EntityPathBase<Product_Info> {

    private static final long serialVersionUID = 338199892L;

    public static final QProduct_Info product_Info = new QProduct_Info("product_Info");

    public final NumberPath<Long> brandId = createNumber("brandId", Long.class);

    public final StringPath image = createString("image");

    public final StringPath mCategory = createString("mCategory");

    public final NumberPath<Long> originalPrice = createNumber("originalPrice", Long.class);

    public final StringPath productDescription = createString("productDescription");

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath productName = createString("productName");

    public final StringPath sCategory = createString("sCategory");

    public final NumberPath<Long> sellPrice = createNumber("sellPrice", Long.class);

    public QProduct_Info(String variable) {
        super(Product_Info.class, forVariable(variable));
    }

    public QProduct_Info(Path<? extends Product_Info> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct_Info(PathMetadata metadata) {
        super(Product_Info.class, metadata);
    }

}

