package com.project.compagnoserver.domain.NoticeBoard;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNoticeBoardComment is a Querydsl query type for NoticeBoardComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNoticeBoardComment extends EntityPathBase<NoticeBoardComment> {

    private static final long serialVersionUID = 28117704L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNoticeBoardComment noticeBoardComment = new QNoticeBoardComment("noticeBoardComment");

    public final QNoticeBoard noticeBoard;

    public final NumberPath<Integer> noticeCommentCode = createNumber("noticeCommentCode", Integer.class);

    public final StringPath noticeCommentContent = createString("noticeCommentContent");

    public final ComparablePath<Character> noticeCommentDelete = createComparable("noticeCommentDelete", Character.class);

    public final DateTimePath<java.util.Date> noticeCommentRegiDate = createDateTime("noticeCommentRegiDate", java.util.Date.class);

    public final NumberPath<Integer> noticeParentCode = createNumber("noticeParentCode", Integer.class);

    public final QNoticeBoardComment parent;

    public final com.project.compagnoserver.domain.user.QUser user;

    public QNoticeBoardComment(String variable) {
        this(NoticeBoardComment.class, forVariable(variable), INITS);
    }

    public QNoticeBoardComment(Path<? extends NoticeBoardComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNoticeBoardComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNoticeBoardComment(PathMetadata metadata, PathInits inits) {
        this(NoticeBoardComment.class, metadata, inits);
    }

    public QNoticeBoardComment(Class<? extends NoticeBoardComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.noticeBoard = inits.isInitialized("noticeBoard") ? new QNoticeBoard(forProperty("noticeBoard"), inits.get("noticeBoard")) : null;
        this.parent = inits.isInitialized("parent") ? new QNoticeBoardComment(forProperty("parent"), inits.get("parent")) : null;
        this.user = inits.isInitialized("user") ? new com.project.compagnoserver.domain.user.QUser(forProperty("user")) : null;
    }

}

