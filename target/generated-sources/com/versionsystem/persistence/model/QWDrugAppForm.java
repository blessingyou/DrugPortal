package com.versionsystem.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QWDrugAppForm is a Querydsl query type for WDrugAppForm
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWDrugAppForm extends EntityPathBase<WDrugAppForm> {

    private static final long serialVersionUID = -614335146L;

    public static final QWDrugAppForm wDrugAppForm = new QWDrugAppForm("wDrugAppForm");

    public final StringPath appBy = createString("appBy");

    public final StringPath appCode = createString("appCode");

    public final DateTimePath<java.util.Date> appDate = createDateTime("appDate", java.util.Date.class);

    public final NumberPath<Long> appId = createNumber("appId", Long.class);

    public final NumberPath<java.math.BigDecimal> appPrice = createNumber("appPrice", java.math.BigDecimal.class);

    public final StringPath appStatus = createString("appStatus");

    public final StringPath doctorCode = createString("doctorCode");

    public final StringPath drugBrand = createString("drugBrand");

    public final StringPath drugCode = createString("drugCode");

    public final NumberPath<java.math.BigDecimal> drugDuration = createNumber("drugDuration", java.math.BigDecimal.class);

    public final StringPath drugName = createString("drugName");

    public final NumberPath<java.math.BigDecimal> drugPrice = createNumber("drugPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> drugQty = createNumber("drugQty", java.math.BigDecimal.class);

    public final StringPath drugUnit = createString("drugUnit");

    public final DatePath<java.util.Date> incurredDate = createDate("incurredDate", java.util.Date.class);

    public final StringPath orgCode = createString("orgCode");

    public final StringPath requestBy = createString("requestBy");

    public final DateTimePath<java.util.Date> requestDate = createDateTime("requestDate", java.util.Date.class);

    public final StringPath voucherNo = createString("voucherNo");

    public QWDrugAppForm(String variable) {
        super(WDrugAppForm.class, forVariable(variable));
    }

    public QWDrugAppForm(Path<? extends WDrugAppForm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWDrugAppForm(PathMetadata<?> metadata) {
        super(WDrugAppForm.class, metadata);
    }

}

