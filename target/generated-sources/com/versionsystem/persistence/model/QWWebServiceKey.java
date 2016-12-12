package com.versionsystem.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QWWebServiceKey is a Querydsl query type for WWebServiceKey
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWWebServiceKey extends EntityPathBase<WWebServiceKey> {

    private static final long serialVersionUID = -643335121L;

    public static final QWWebServiceKey wWebServiceKey = new QWWebServiceKey("wWebServiceKey");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath orgCode = createString("orgCode");

    public final StringPath serviceKey = createString("serviceKey");

    public QWWebServiceKey(String variable) {
        super(WWebServiceKey.class, forVariable(variable));
    }

    public QWWebServiceKey(Path<? extends WWebServiceKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWWebServiceKey(PathMetadata<?> metadata) {
        super(WWebServiceKey.class, metadata);
    }

}

