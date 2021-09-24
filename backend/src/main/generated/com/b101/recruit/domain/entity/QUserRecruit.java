package com.b101.recruit.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserRecruit is a Querydsl query type for UserRecruit
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserRecruit extends EntityPathBase<UserRecruit> {

    private static final long serialVersionUID = 310653681L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserRecruit userRecruit = new QUserRecruit("userRecruit");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QJobPosting jobPosting;

    public final QUser user;

    public QUserRecruit(String variable) {
        this(UserRecruit.class, forVariable(variable), INITS);
    }

    public QUserRecruit(Path<? extends UserRecruit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserRecruit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserRecruit(PathMetadata metadata, PathInits inits) {
        this(UserRecruit.class, metadata, inits);
    }

    public QUserRecruit(Class<? extends UserRecruit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.jobPosting = inits.isInitialized("jobPosting") ? new QJobPosting(forProperty("jobPosting"), inits.get("jobPosting")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

