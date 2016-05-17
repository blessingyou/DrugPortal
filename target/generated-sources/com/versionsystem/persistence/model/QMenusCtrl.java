package com.versionsystem.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMenusCtrl is a Querydsl query type for MenusCtrl
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMenusCtrl extends EntityPathBase<MenusCtrl> {

    private static final long serialVersionUID = -633880329L;

    public static final QMenusCtrl menusCtrl = new QMenusCtrl("menusCtrl");

    public final StringPath accessLock = createString("accessLock");

    public final StringPath allowedAction = createString("allowedAction");

    public final DateTimePath<java.sql.Timestamp> approveDate = createDateTime("approveDate", java.sql.Timestamp.class);

    public final StringPath approveUser = createString("approveUser");

    public final StringPath companyCode = createString("companyCode");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final NumberPath<java.math.BigDecimal> dispalySeqNo = createNumber("dispalySeqNo", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdateDate = createDateTime("lastUpdateDate", java.sql.Timestamp.class);

    public final StringPath lastUpdateUser = createString("lastUpdateUser");

    public final StringPath leaf = createString("leaf");

    public final StringPath menuName = createString("menuName");

    public final ListPath<MenusCtrlLocale, QMenusCtrlLocale> menusCtrlLocales = this.<MenusCtrlLocale, QMenusCtrlLocale>createList("menusCtrlLocales", MenusCtrlLocale.class, QMenusCtrlLocale.class, PathInits.DIRECT2);

    public final StringPath seqNo = createString("seqNo");

    public final StringPath sysRole = createString("sysRole");

    public QMenusCtrl(String variable) {
        super(MenusCtrl.class, forVariable(variable));
    }

    public QMenusCtrl(Path<? extends MenusCtrl> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenusCtrl(PathMetadata<?> metadata) {
        super(MenusCtrl.class, metadata);
    }

}

