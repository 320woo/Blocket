package com.b101.recruit.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompanyInfo is a Querydsl query type for CompanyInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCompanyInfo extends EntityPathBase<CompanyInfo> {

    private static final long serialVersionUID = -978888503L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCompanyInfo companyInfo = new QCompanyInfo("companyInfo");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath address = createString("address");

    public final StringPath email = createString("email");

    public final StringPath employees = createString("employees");

    public final StringPath founded = createString("founded");

    public final StringPath homePage = createString("homePage");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath industry = createString("industry");

    public final StringPath logo = createString("logo");

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final QUser user;

    public QCompanyInfo(String variable) {
        this(CompanyInfo.class, forVariable(variable), INITS);
    }

    public QCompanyInfo(Path<? extends CompanyInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCompanyInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCompanyInfo(PathMetadata metadata, PathInits inits) {
        this(CompanyInfo.class, metadata, inits);
    }

    public QCompanyInfo(Class<? extends CompanyInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

