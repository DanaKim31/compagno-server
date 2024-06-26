package com.project.compagnoserver.domain.NoticeBoard;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNoticeBoard is a Querydsl query type for NoticeBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNoticeBoard extends EntityPathBase<NoticeBoard> {

    private static final long serialVersionUID = 1793561495L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNoticeBoard noticeBoard = new QNoticeBoard("noticeBoard");

    public final ListPath<NoticeBoardComment, QNoticeBoardComment> comments = this.<NoticeBoardComment, QNoticeBoardComment>createList("comments", NoticeBoardComment.class, QNoticeBoardComment.class, PathInits.DIRECT2);

    public final ListPath<NoticeBoardImage, QNoticeBoardImage> images = this.<NoticeBoardImage, QNoticeBoardImage>createList("images", NoticeBoardImage.class, QNoticeBoardImage.class, PathInits.DIRECT2);

    public final NumberPath<Integer> noticeBoardCode = createNumber("noticeBoardCode", Integer.class);

    public final StringPath noticeBoardContent = createString("noticeBoardContent");

    public final DateTimePath<java.util.Date> noticeBoardRegiDate = createDateTime("noticeBoardRegiDate", java.util.Date.class);

    public final StringPath noticeBoardTitle = createString("noticeBoardTitle");

    public final NumberPath<Integer> noticeBoardViewCount = createNumber("noticeBoardViewCount", Integer.class);

    public final com.project.compagnoserver.domain.user.QUser user;

    public QNoticeBoard(String variable) {
        this(NoticeBoard.class, forVariable(variable), INITS);
    }

    public QNoticeBoard(Path<? extends NoticeBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNoticeBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNoticeBoard(PathMetadata metadata, PathInits inits) {
        this(NoticeBoard.class, metadata, inits);
    }

    public QNoticeBoard(Class<? extends NoticeBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.project.compagnoserver.domain.user.QUser(forProperty("user")) : null;
    }

}

