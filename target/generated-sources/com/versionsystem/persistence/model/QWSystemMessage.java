package com.versionsystem.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QWSystemMessage is a Querydsl query type for WSystemMessage
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWSystemMessage extends EntityPathBase<WSystemMessage> {

    private static final long serialVersionUID = -1812780759L;

    public static final QWSystemMessage wSystemMessage = new QWSystemMessage("wSystemMessage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdateDate = createDateTime("lastUpdateDate", java.sql.Timestamp.class);

    public final StringPath lastUpdateUser = createString("lastUpdateUser");

    public final StringPath msgContent = createString("msgContent");

    public final StringPath msgTitle = createString("msgTitle");

    public final StringPath msgType = createString("msgType");

    public QWSystemMessage(String variable) {
        super(WSystemMessage.class, forVariable(variable));
    }

    public QWSystemMessage(Path<? extends WSystemMessage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWSystemMessage(PathMetadata<?> metadata) {
        super(WSystemMessage.class, metadata);
    }

}

