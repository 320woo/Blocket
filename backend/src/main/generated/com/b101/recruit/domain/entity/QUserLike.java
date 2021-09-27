package com.b101.recruit.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserLike is a Querydsl query type for UserLike
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserLike extends EntityPathBase<UserLike> {

    private static final long serialVersionUID = 661575876L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLike userLike = new QUserLike("userLike");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QCompanyInfo companyinfo;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QUser user;

    public QUserLike(String variable) {
        this(UserLike.class, forVariable(variable), INITS);
    }

    public QUserLike(Path<? extends UserLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserLike(PathMetadata metadata, PathInits inits) {
        this(UserLike.class, metadata, inits);
    }

    public QUserLike(Class<? extends UserLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.companyinfo = inits.isInitialized("companyinfo") ? new QCompanyInfo(forProperty("companyinfo"), inits.get("companyinfo")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

