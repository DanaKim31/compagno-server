package com.project.compagnoserver.service;

import com.project.compagnoserver.domain.Animal.AnimalCategory;
import com.project.compagnoserver.domain.NeighborBoard.*;
import com.project.compagnoserver.repo.Animal.AnimalCategoryDAO;
import com.project.compagnoserver.repo.NeighborBoard.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NeighborBoardService {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private NeighborBoardDAO neighborBoardDAO;
    private final QNeighborBoard qNeighborBoard = QNeighborBoard.neighborBoard;

    @Autowired
    private AnimalCategoryDAO animalCategoryDAO;

    @Autowired
    private NeighborBoardImageDAO neighborBoardImageDAO;
    private final QNeighborBoardImage qNeighborBoardImage = QNeighborBoardImage.neighborBoardImage;

    @Autowired
    private NeighborBoardBookmarkDAO neighborBoardBookmarkDAO;
    private final QNeighborBoardBookmark qNeighborBoardBookmark = QNeighborBoardBookmark.neighborBoardBookmark;

    @Autowired
    private NeighborBoardCommentDAO neighborBoardCommentDAO;
    private final QNeighborBoardComment qNeighborBoardComment = QNeighborBoardComment.neighborBoardComment;

    @Autowired
    private NeighborBoardLocationDAO neighborBoardLocationDAO;
    private final QNeighborBoardLocation qNeighborBoardLocation = QNeighborBoardLocation.neighborBoardLocation;


    // 동물 카테고리 전체보기
    public List<AnimalCategory> animalCategoryView() {
        return animalCategoryDAO.findAll();
    }


    // 전체 보기
    public Page<NeighborBoard> neighborViewAll(Pageable pageable, BooleanBuilder builder) {
        return neighborBoardDAO.findAll(builder,pageable);
    }
    public List<NeighborBoardImage> neighborViewAllImg(int code) {
        return neighborBoardImageDAO.findByBoardCode(code);
    }


    // 상세 페이지
    public NeighborBoard neighborView(int code) {
        return neighborBoardDAO.findById(code).orElse(null);
    }
    public List<NeighborBoardImage> neighborViewImg(int code) {
        return queryFactory.selectFrom(qNeighborBoardImage)
                .where(qNeighborBoardImage.neighborBoard.neighborBoardCode.eq(code))
                .fetch();
    }


    // 조회수
    @Transactional
    public void neighborViewCount(int code) {
        queryFactory.update(qNeighborBoard)
                .set(qNeighborBoard.neighborBoardViewCount, qNeighborBoard.neighborBoardViewCount.add(1))
                .where(qNeighborBoard.neighborBoardCode.eq(code))
                .execute();
    }


    // 게시글 등록
    public NeighborBoard neighborCreate(NeighborBoard neighborBoardVo) {
        return neighborBoardDAO.save(neighborBoardVo);
    }
    public void neighborCreateImg(NeighborBoardImage neighborBoardImageVo) {
        neighborBoardImageDAO.save(neighborBoardImageVo);
    }


    // 게시글 수정
    public NeighborBoard neighborUpdate(NeighborBoard neighborBoardVo) {
        if(neighborBoardDAO.existsById(neighborBoardVo.getNeighborBoardCode())) {
            return neighborBoardDAO.save(neighborBoardVo);
        }
        return null;
    }


    // 게시글 삭제
    public void neighborDeleteImg(int code) {
        if(neighborBoardImageDAO.existsById(code)) {
            neighborBoardImageDAO.deleteById(code);
        }
    }
    public void neighborDelete(int code) {
        if(neighborBoardDAO.existsById(code)) {
            neighborBoardDAO.deleteById(code);
        }
    }


// ====================================== 북마크 ======================================

    // 게시글 북마크 확인
    public Integer neighborChkBookmark(NeighborBoardBookmark neighborBoardBookmarkVo) {
        return queryFactory.select(qNeighborBoardBookmark.neighborBookmarkCode)
                .from(qNeighborBoardBookmark)
                .where(qNeighborBoardBookmark.neighborBoard.neighborBoardCode.eq(neighborBoardBookmarkVo.getNeighborBoard().getNeighborBoardCode()))
                .where(qNeighborBoardBookmark.user.userId.eq(neighborBoardBookmarkVo.getUser().getUserId()))
                .fetchOne();
    }
    // 게시글 북마크
    public void neighborBookmark(NeighborBoardBookmark neighborBoardBookmarkVo) {
        if(neighborChkBookmark(neighborBoardBookmarkVo)==null) {
            neighborBoardBookmarkDAO.save(neighborBoardBookmarkVo);
        } else {
            neighborBoardBookmarkDAO.deleteById(neighborChkBookmark(neighborBoardBookmarkVo));
        }
    }




//    ========================================== 댓글 ==========================================

    // 댓글 하나 보기
    public NeighborBoardComment neighborCommentview(int code){
        return neighborBoardCommentDAO.findById(code).orElse(null);
    }

    // 댓글 추가
    public NeighborBoardComment neighborCommentCreate(NeighborBoardComment neighborBoardCommentVo) {
        return neighborBoardCommentDAO.save(neighborBoardCommentVo);
    }

    // 댓글 수정
    @Transactional
    public void neighborCommentUpdate(NeighborBoardComment neighborBoardCommentVo) {
        if(neighborBoardCommentDAO.existsById(neighborBoardCommentVo.getNeighborCommentCode())) {
//            neighborBoardCommentDAO.save(neighborBoardCommentVo);
            queryFactory.update(qNeighborBoardComment)
                    .set(qNeighborBoardComment.neighborCommentContent, neighborBoardCommentVo.getNeighborCommentContent())
                    .where(qNeighborBoardComment.neighborCommentCode.eq(neighborBoardCommentVo.getNeighborCommentCode()))
                    .execute();
        }
    }

    // 댓글 삭제
    @Transactional
    public void neighborCommentDelete(int commentCode) {
        NeighborBoardComment target = neighborBoardCommentDAO.findById(commentCode).orElse(null);
        if(target != null) {
            neighborBoardCommentDAO.delete(target);
        }
//        if(neighborBoardCommentDAO.existsById(commentCode)) {
//            queryFactory.update(qNeighborBoardComment)
//                    .set(qNeighborBoardComment.neighborCommentStatus, 'N')
//                    .where(qNeighborBoardComment.neighborCommentCode.eq(commentCode))
//                    .execute();
//        }
    }

    // 원 댓글만 조회
    public List<NeighborBoardComment> getTopComments(int code) {
        return queryFactory.selectFrom(qNeighborBoardComment)
                .where(qNeighborBoardComment.neighborCommentParentCode.eq(0))
                .where(qNeighborBoardComment.neighborBoard.neighborBoardCode.eq(code))
                .orderBy(qNeighborBoardComment.neighborCommentCode.desc())
                .fetch();
    }

    // 대댓글만 조회
    public List<NeighborBoardComment> getReplyComments(int parent, int code) {
        return queryFactory.selectFrom(qNeighborBoardComment)
                .where(qNeighborBoardComment.neighborCommentParentCode.eq(parent))
                .where(qNeighborBoardComment.neighborBoard.neighborBoardCode.eq(code))
                .orderBy(qNeighborBoardComment.neighborCommentRegiDate.asc())
                .fetch();
    }


    // ====================================== Location ======================================

    // 시도 조회
    public List<NeighborBoardLocation> neighborGetProvinces() {
        return queryFactory.selectFrom(qNeighborBoardLocation)
                .where(qNeighborBoardLocation.locationParentCode.eq(0))
                .fetch();
    }

    // 시도별 시군구 조회
    public List<NeighborBoardLocation> neighborGetDistricts(int code) {
        return queryFactory.selectFrom(qNeighborBoardLocation)
                .where(qNeighborBoardLocation.locationParentCode.eq(code))
                .fetch();
    }

}
