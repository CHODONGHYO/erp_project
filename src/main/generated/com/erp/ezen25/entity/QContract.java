package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QContract is a Querydsl query type for Contract
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContract extends EntityPathBase<Contract> {

    private static final long serialVersionUID = -701096120L;

    public static final QContract contract = new QContract("contract");

    public final NumberPath<Long> brandId = createNumber("brandId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> contractDate = createDateTime("contractDate", java.time.LocalDateTime.class);

    public final StringPath contractFile = createString("contractFile");

    public final NumberPath<Long> contractId = createNumber("contractId", Long.class);

    public final NumberPath<Long> contractTotalMoney = createNumber("contractTotalMoney", Long.class);

    public final StringPath contractType = createString("contractType");

    public QContract(String variable) {
        super(Contract.class, forVariable(variable));
    }

    public QContract(Path<? extends Contract> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContract(PathMetadata metadata) {
        super(Contract.class, metadata);
    }

}

