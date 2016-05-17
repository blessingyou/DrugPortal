package com.versionsystem.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QLastestMenuList is a Querydsl query type for LastestMenuList
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLastestMenuList extends EntityPathBase<LastestMenuList> {

    private static final long serialVersionUID = -2134105755L;

    public static final QLastestMenuList lastestMenuList = new QLastestMenuList("lastestMenuList");

    public final NumberPath<java.math.BigDecimal> displaySeq = createNumber("displaySeq", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> menuId = createNumber("menuId", Long.class);

    public final StringPath userId = createString("userId");

    public QLastestMenuList(String variable) {
        super(LastestMenuList.class, forVariable(variable));
    }

    public QLastestMenuList(Path<? extends LastestMenuList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLastestMenuList(PathMetadata<?> metadata) {
        super(LastestMenuList.class, metadata);
    }

}

