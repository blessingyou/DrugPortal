package com.versionsystem.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QWSecurityLog is a Querydsl query type for WSecurityLog
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWSecurityLog extends EntityPathBase<WSecurityLog> {

    private static final long serialVersionUID = -2097360267L;

    public static final QWSecurityLog wSecurityLog = new QWSecurityLog("wSecurityLog");

    public final StringPath browserType = createString("browserType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath loginIp = createString("loginIp");

    public final DateTimePath<java.sql.Timestamp> loginTime = createDateTime("loginTime", java.sql.Timestamp.class);

    public final StringPath loginUser = createString("loginUser");

    public QWSecurityLog(String variable) {
        super(WSecurityLog.class, forVariable(variable));
    }

    public QWSecurityLog(Path<? extends WSecurityLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWSecurityLog(PathMetadata<?> metadata) {
        super(WSecurityLog.class, metadata);
    }

}

