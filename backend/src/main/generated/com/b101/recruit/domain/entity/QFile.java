package com.b101.recruit.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFile is a Querydsl query type for File
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFile extends EntityPathBase<File> {

    private static final long serialVersionUID = 1205488958L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFile file = new QFile("file");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QActivity activity;

    public final QCertificate certificate;

    public final StringPath filepath = createString("filepath");

    public final QFinalEducation finaleducation;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath originName = createString("originName");

    public final QPersonalInfo personalInfo;

    public final StringPath saveName = createString("saveName");

    public final StringPath sortation = createString("sortation");

    public QFile(String variable) {
        this(File.class, forVariable(variable), INITS);
    }

    public QFile(Path<? extends File> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFile(PathMetadata metadata, PathInits inits) {
        this(File.class, metadata, inits);
    }

    public QFile(Class<? extends File> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activity = inits.isInitialized("activity") ? new QActivity(forProperty("activity"), inits.get("activity")) : null;
        this.certificate = inits.isInitialized("certificate") ? new QCertificate(forProperty("certificate"), inits.get("certificate")) : null;
        this.finaleducation = inits.isInitialized("finaleducation") ? new QFinalEducation(forProperty("finaleducation"), inits.get("finaleducation")) : null;
        this.personalInfo = inits.isInitialized("personalInfo") ? new QPersonalInfo(forProperty("personalInfo"), inits.get("personalInfo")) : null;
    }

}

