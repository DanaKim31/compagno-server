package com.project.compagnoserver.domain.ProductBoard;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductBoardBookmark is a Querydsl query type for ProductBoardBookmark
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductBoardBookmark extends EntityPathBase<ProductBoardBookmark> {

    private static final long serialVersionUID = -1777430387L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductBoardBookmark productBoardBookmark = new QProductBoardBookmark("productBoardBookmark");

    public final QProductBoard productBoard;

    public final NumberPath<Integer> productBookmarkCode = createNumber("productBookmarkCode", Integer.class);

    public final com.project.compagnoserver.domain.user.QUser user;

    public QProductBoardBookmark(String variable) {
        this(ProductBoardBookmark.class, forVariable(variable), INITS);
    }

    public QProductBoardBookmark(Path<? extends ProductBoardBookmark> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductBoardBookmark(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductBoardBookmark(PathMetadata metadata, PathInits inits) {
        this(ProductBoardBookmark.class, metadata, inits);
    }

    public QProductBoardBookmark(Class<? extends ProductBoardBookmark> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productBoard = inits.isInitialized("productBoard") ? new QProductBoard(forProperty("productBoard"), inits.get("productBoard")) : null;
        this.user = inits.isInitialized("user") ? new com.project.compagnoserver.domain.user.QUser(forProperty("user")) : null;
    }

}

