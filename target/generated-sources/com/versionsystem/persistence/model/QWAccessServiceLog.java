package com.versionsystem.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QWAccessServiceLog is a Querydsl query type for WAccessServiceLog
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWAccessServiceLog extends EntityPathBase<WAccessServiceLog> {

    private static final long serialVersionUID = 1346968802L;

    public static final QWAccessServiceLog wAccessServiceLog = new QWAccessServiceLog("wAccessServiceLog");

    public final DateTimePath<java.util.Date> actionDate = createDateTime("actionDate", java.util.Date.class);

    public final StringPath actionIp = createString("actionIp");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath orgCode = createString("orgCode");

    public final StringPath serviceKey = createString("serviceKey");

    public QWAccessServiceLog(String variable) {
        super(WAccessServiceLog.class, forVariable(variable));
    }

    public QWAccessServiceLog(Path<? extends WAccessServiceLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWAccessServiceLog(PathMetadata<?> metadata) {
        super(WAccessServiceLog.class, metadata);
    }

}

