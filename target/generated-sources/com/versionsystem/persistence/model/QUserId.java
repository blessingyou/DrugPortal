package com.versionsystem.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUserId is a Querydsl query type for UserId
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserId extends EntityPathBase<UserId> {

    private static final long serialVersionUID = 86397902L;

    public static final QUserId userId1 = new QUserId("userId1");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final DateTimePath<java.sql.Timestamp> approveDate = createDateTime("approveDate", java.sql.Timestamp.class);

    public final StringPath approveUser = createString("approveUser");

    public final StringPath companyCode = createString("companyCode");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final DatePath<java.sql.Date> dob = createDate("dob", java.sql.Date.class);

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdateDate = createDateTime("lastUpdateDate", java.sql.Timestamp.class);

    public final StringPath lastUpdateUser = createString("lastUpdateUser");

    public final StringPath locale = createString("locale");

    public final StringPath mobileDeviceId = createString("mobileDeviceId");

    public final StringPath mobileDeviceType = createString("mobileDeviceType");

    public final StringPath password = createString("password");

    public final NumberPath<java.math.BigDecimal> roleId = createNumber("roleId", java.math.BigDecimal.class);

    public final StringPath securitycode = createString("securitycode");

    public final DateTimePath<java.sql.Timestamp> securitycodeGenTime = createDateTime("securitycodeGenTime", java.sql.Timestamp.class);

    public final StringPath telNo = createString("telNo");

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public QUserId(String variable) {
        super(UserId.class, forVariable(variable));
    }

    public QUserId(Path<? extends UserId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserId(PathMetadata<?> metadata) {
        super(UserId.class, metadata);
    }

}

