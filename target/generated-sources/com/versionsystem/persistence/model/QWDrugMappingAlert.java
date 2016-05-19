package com.versionsystem.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QWDrugMappingAlert is a Querydsl query type for WDrugMappingAlert
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWDrugMappingAlert extends EntityPathBase<WDrugMappingAlert> {

    private static final long serialVersionUID = 317059325L;

    public static final QWDrugMappingAlert wDrugMappingAlert = new QWDrugMappingAlert("wDrugMappingAlert");

    public final StringPath drugBrand = createString("drugBrand");

    public final StringPath drugCode = createString("drugCode");

    public final StringPath drugName = createString("drugName");

    public final NumberPath<java.math.BigDecimal> drugPrice = createNumber("drugPrice", java.math.BigDecimal.class);

    public final StringPath drugUnit = createString("drugUnit");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mapBy = createString("mapBy");

    public final DateTimePath<java.util.Date> mapDate = createDateTime("mapDate", java.util.Date.class);

    public final BooleanPath mapFlag = createBoolean("mapFlag");

    public final StringPath mappingBrand = createString("mappingBrand");

    public final StringPath mappingCode = createString("mappingCode");

    public final StringPath mappingName = createString("mappingName");

    public final NumberPath<java.math.BigDecimal> mappingPrice = createNumber("mappingPrice", java.math.BigDecimal.class);

    public final StringPath mappingUnit = createString("mappingUnit");

    public final StringPath orgCode = createString("orgCode");

    public final StringPath requestBy = createString("requestBy");

    public final DateTimePath<java.util.Date> requestDate = createDateTime("requestDate", java.util.Date.class);

    public QWDrugMappingAlert(String variable) {
        super(WDrugMappingAlert.class, forVariable(variable));
    }

    public QWDrugMappingAlert(Path<? extends WDrugMappingAlert> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWDrugMappingAlert(PathMetadata<?> metadata) {
        super(WDrugMappingAlert.class, metadata);
    }

}

