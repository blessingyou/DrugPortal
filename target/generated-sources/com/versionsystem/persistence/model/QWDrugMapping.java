package com.versionsystem.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QWDrugMapping is a Querydsl query type for WDrugMapping
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWDrugMapping extends EntityPathBase<WDrugMapping> {

    private static final long serialVersionUID = 1017582495L;

    public static final QWDrugMapping wDrugMapping = new QWDrugMapping("wDrugMapping");

    public final StringPath drugBrand = createString("drugBrand");

    public final StringPath drugCode = createString("drugCode");

    public final StringPath drugName = createString("drugName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mappingBrand = createString("mappingBrand");

    public final StringPath mappingCode = createString("mappingCode");

    public final StringPath orgCode = createString("orgCode");

    public QWDrugMapping(String variable) {
        super(WDrugMapping.class, forVariable(variable));
    }

    public QWDrugMapping(Path<? extends WDrugMapping> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWDrugMapping(PathMetadata<?> metadata) {
        super(WDrugMapping.class, metadata);
    }

}

