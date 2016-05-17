package com.versionsystem.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QWDrugList is a Querydsl query type for WDrugList
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWDrugList extends EntityPathBase<WDrugList> {

    private static final long serialVersionUID = 332468077L;

    public static final QWDrugList wDrugList = new QWDrugList("wDrugList");

    public final StringPath drugBrand = createString("drugBrand");

    public final StringPath drugCode = createString("drugCode");

    public final NumberPath<java.math.BigDecimal> drugCost = createNumber("drugCost", java.math.BigDecimal.class);

    public final StringPath drugName = createString("drugName");

    public final NumberPath<java.math.BigDecimal> drugPrice = createNumber("drugPrice", java.math.BigDecimal.class);

    public final DatePath<java.util.Date> effectiveFrom = createDate("effectiveFrom", java.util.Date.class);

    public final StringPath genericName = createString("genericName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> lastUpdateDate = createDateTime("lastUpdateDate", java.util.Date.class);

    public final StringPath lastUpdateUser = createString("lastUpdateUser");

    public final StringPath unit = createString("unit");

    public final NumberPath<java.math.BigDecimal> unitQty = createNumber("unitQty", java.math.BigDecimal.class);

    public QWDrugList(String variable) {
        super(WDrugList.class, forVariable(variable));
    }

    public QWDrugList(Path<? extends WDrugList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWDrugList(PathMetadata<?> metadata) {
        super(WDrugList.class, metadata);
    }

}

