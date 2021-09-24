package com.b101.recruit.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJobPosting is a Querydsl query type for JobPosting
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJobPosting extends EntityPathBase<JobPosting> {

    private static final long serialVersionUID = 888502023L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJobPosting jobPosting = new QJobPosting("jobPosting");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.util.Date> closingDate = createDateTime("closingDate", java.util.Date.class);

    public final QCompanyInfo companyinfo;

    public final StringPath employmentForm = createString("employmentForm");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath introduction = createString("introduction");

    public final StringPath occupiedLocations = createString("occupiedLocations");

    public final StringPath recruitmentPart = createString("recruitmentPart");

    public final DateTimePath<java.util.Date> registrationDate = createDateTime("registrationDate", java.util.Date.class);

    public final StringPath requiredAcademicBackground = createString("requiredAcademicBackground");

    public final NumberPath<Integer> salary = createNumber("salary", Integer.class);

    public final ComparablePath<Enum<TechstackEnum>> technologyStack = createComparable("technologyStack", Enum.class);

    public final StringPath title = createString("title");

    public final ListPath<UserRecruit, QUserRecruit> users = this.<UserRecruit, QUserRecruit>createList("users", UserRecruit.class, QUserRecruit.class, PathInits.DIRECT2);

    public QJobPosting(String variable) {
        this(JobPosting.class, forVariable(variable), INITS);
    }

    public QJobPosting(Path<? extends JobPosting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJobPosting(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJobPosting(PathMetadata metadata, PathInits inits) {
        this(JobPosting.class, metadata, inits);
    }

    public QJobPosting(Class<? extends JobPosting> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.companyinfo = inits.isInitialized("companyinfo") ? new QCompanyInfo(forProperty("companyinfo"), inits.get("companyinfo")) : null;
    }

}

