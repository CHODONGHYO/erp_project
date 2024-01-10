package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct_Info is a Querydsl query type for Product_Info
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct_Info extends EntityPathBase<Product_Info> {

    private static final long serialVersionUID = 338199892L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct_Info product_Info = new QProduct_Info("product_Info");

    public final QBrand brandId;

    public final StringPath image = createString("static/image");

    public final StringPath mCategory = createString("mCategory");

    public final NumberPath<Long> originalPrice = createNumber("originalPrice", Long.class);

    public final StringPath productDescription = createString("productDescription");

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath productName = createString("productName");

    public final StringPath sCategory = createString("sCategory");

    public final NumberPath<Long> sellPrice = createNumber("sellPrice", Long.class);

    public QProduct_Info(String variable) {
        this(Product_Info.class, forVariable(variable), INITS);
    }

    public QProduct_Info(Path<? extends Product_Info> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct_Info(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct_Info(PathMetadata metadata, PathInits inits) {
        this(Product_Info.class, metadata, inits);
    }

    public QProduct_Info(Class<? extends Product_Info> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.brandId = inits.isInitialized("brandId") ? new QBrand(forProperty("brandId")) : null;
    }

}

