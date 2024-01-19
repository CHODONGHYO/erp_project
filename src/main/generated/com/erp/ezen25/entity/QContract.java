package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContract is a Querydsl query type for Contract
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContract extends EntityPathBase<Contract> {

    private static final long serialVersionUID = -701096120L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContract contract = new QContract("contract");

    public final QBrand Brand;

    public final DateTimePath<java.time.LocalDateTime> contractDate = createDateTime("contractDate", java.time.LocalDateTime.class);

    public final StringPath contractFile = createString("contractFile");

    public final NumberPath<Long> contractId = createNumber("contractId", Long.class);

    public final QProduct_Info productInfo;

    public QContract(String variable) {
        this(Contract.class, forVariable(variable), INITS);
    }

    public QContract(Path<? extends Contract> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContract(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContract(PathMetadata metadata, PathInits inits) {
        this(Contract.class, metadata, inits);
    }

    public QContract(Class<? extends Contract> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.Brand = inits.isInitialized("Brand") ? new QBrand(forProperty("Brand")) : null;
        this.productInfo = inits.isInitialized("productInfo") ? new QProduct_Info(forProperty("productInfo"), inits.get("productInfo")) : null;
    }

}

