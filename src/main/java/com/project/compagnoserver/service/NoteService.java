package com.project.compagnoserver.service;

import com.project.compagnoserver.domain.Note.Note;
import com.project.compagnoserver.domain.Note.NoteFIle;

import com.project.compagnoserver.repo.Note.NoteDAO;
import com.project.compagnoserver.repo.Note.NoteFileDAO;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NoteService {

    @Autowired
    private NoteDAO noteDAO;

    @Autowired
    private NoteFileDAO fileDAO;

    // [추가] ------------------------------------------------------------------------------
    public Note create(Note note) {return noteDAO.save(note);}
    public NoteFIle createFile(NoteFIle files){return fileDAO.save(files);}


    // [보기] -----------------------------------------------------------------------------
    // List_viewAll(전체보기 : 전체 쪽지 리스트 확인)
    public Page<Note> viewAll(Pageable pageable, BooleanBuilder builder){return noteDAO.findAll(builder, pageable);}

    public int delCount(String nickName){return noteDAO.delCount(nickName);}
    public int delReceiverCount(String nickName){return noteDAO.delReceiverCount(nickName);}
    public int delSenderCount(String nickName){return noteDAO.delSenderCount(nickName);}
    public int starCount(String nickName){return noteDAO.starCount(nickName);}




    // List_viewSendBox(보낸 쪽지함 : 쪽지 리스트로 확인)
    public Page<Note> viewSendBox(Pageable pageable, BooleanBuilder builder){return noteDAO.findAll(builder, pageable);}

    // List_viewReceiveBox(받은 쪽지함 : 쪽지 리스트로 확인)
    public Page<Note> viewReceiveBox(Pageable pageable, BooleanBuilder builder){return noteDAO.findAll(builder, pageable);}

    // view(보기 : 개별 쪽지 내용 확인)
    public Note view(int noteCode) {return noteDAO.findById(noteCode).orElse(null);}

    // [검색 기능] - 특정 검색 따른 쪽지 조회
    public Page<Note> findBySearch(Pageable pageable, BooleanBuilder builder){
        return noteDAO.findAll(builder, pageable);
    }

    // 중요 쪽지함
    public void updateStarSender(int noteCode){noteDAO.updateStarSender(noteCode);}
    public void updateStarReceiver(int noteCode){noteDAO.updateStarReceiver(noteCode);}


    // [수정] ------------------------------------------------------------------------------
    // update(수정) : 보낸 이가 삭제 원할 때 deletedSender을 true로 변경
    public Note updateDeleteSender(int noteCode) {
        if(noteDAO.existsById(noteCode)){
            Note vo = noteDAO.findById(noteCode).orElse(null);
            vo.setDeletedBySender(true);
            return noteDAO.save(vo);
        }
        return null;
    }
    // update(수정) : 받은 이가 삭제 원할 때 deletedReceiver을 true로 변경
    public Note updateDeleteReceiver(int noteCode){
        if(noteDAO.existsById(noteCode)){
            Note vo = noteDAO.findById(noteCode).orElse(null);
            vo.setDeletedByReceiver(true);
            return noteDAO.save(vo);
        }
        return null;
    }


    // [삭제] ------------------------------------------------------------------------------
    // delete(삭제) : controller에서 각 update 안에 추가!
    public void delete(int noteCode){
        Note vo = noteDAO.findById(noteCode).orElse(null);
        if(vo!=null){
            noteDAO.delete(vo);
        }
        //return vo;
    }




}
