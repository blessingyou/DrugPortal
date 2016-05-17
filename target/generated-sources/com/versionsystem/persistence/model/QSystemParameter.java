package com.versionsystem.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSystemParameter is a Querydsl query type for SystemParameter
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSystemParameter extends EntityPathBase<SystemParameter> {

    private static final long serialVersionUID = 1173551538L;

    public static final QSystemParameter systemParameter = new QSystemParameter("systemParameter");

    public final DateTimePath<java.sql.Timestamp> approveDate = createDateTime("approveDate", java.sql.Timestamp.class);

    public final StringPath approveUser = createString("approveUser");

    public final StringPath companyCode = createString("companyCode");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdateDate = createDateTime("lastUpdateDate", java.sql.Timestamp.class);

    public final StringPath lastUpdateUser = createString("lastUpdateUser");

    public final StringPath moduleName = createString("moduleName");

    public final StringPath parameterDesc = createString("parameterDesc");

    public final StringPath parameterDescCn = createString("parameterDescCn");

    public final StringPath parameterDescHk = createString("parameterDescHk");

    public final StringPath parameterKey = createString("parameterKey");

    public final StringPath parameterType = createString("parameterType");

    public final StringPath parameterValue = createString("parameterValue");

    public QSystemParameter(String variable) {
        super(SystemParameter.class, forVariable(variable));
    }

    public QSystemParameter(Path<? extends SystemParameter> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSystemParameter(PathMetadata<?> metadata) {
        super(SystemParameter.class, metadata);
    }

}

