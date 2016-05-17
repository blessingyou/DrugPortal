package com.versionsystem.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMenusCtrlLocale is a Querydsl query type for MenusCtrlLocale
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMenusCtrlLocale extends EntityPathBase<MenusCtrlLocale> {

    private static final long serialVersionUID = 1401284145L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenusCtrlLocale menusCtrlLocale = new QMenusCtrlLocale("menusCtrlLocale");

    public final DateTimePath<java.sql.Timestamp> approveDate = createDateTime("approveDate", java.sql.Timestamp.class);

    public final StringPath approveUser = createString("approveUser");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath formLabel = createString("formLabel");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdateDate = createDateTime("lastUpdateDate", java.sql.Timestamp.class);

    public final StringPath lastUpdateUser = createString("lastUpdateUser");

    public final StringPath locale = createString("locale");

    public final QMenusCtrl menusCtrl;

    public QMenusCtrlLocale(String variable) {
        this(MenusCtrlLocale.class, forVariable(variable), INITS);
    }

    public QMenusCtrlLocale(Path<? extends MenusCtrlLocale> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMenusCtrlLocale(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMenusCtrlLocale(PathMetadata<?> metadata, PathInits inits) {
        this(MenusCtrlLocale.class, metadata, inits);
    }

    public QMenusCtrlLocale(Class<? extends MenusCtrlLocale> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menusCtrl = inits.isInitialized("menusCtrl") ? new QMenusCtrl(forProperty("menusCtrl")) : null;
    }

}

