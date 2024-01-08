package com.erp.ezen25.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 2129138904L;

    public static final QOrder order = new QOrder("order1");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final StringPath orderCode = createString("orderCode");

    public final DateTimePath<java.time.LocalDateTime> orderDate = createDateTime("orderDate", java.time.LocalDateTime.class);

    public final StringPath orderDescription = createString("orderDescription");

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    public final NumberPath<Long> orderNum = createNumber("orderNum", Long.class);

    public final DateTimePath<java.time.LocalDateTime> orderOutDate = createDateTime("orderOutDate", java.time.LocalDateTime.class);

    public final StringPath orderStatus = createString("orderStatus");

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public QOrder(String variable) {
        super(Order.class, forVariable(variable));
    }

    public QOrder(Path<? extends Order> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrder(PathMetadata metadata) {
        super(Order.class, metadata);
    }

}

