package com.project.compagnoserver.controller;

import com.project.compagnoserver.domain.Animal.AnimalBoard;
import com.project.compagnoserver.domain.Animal.AnimalBoardComment;
import com.project.compagnoserver.domain.Animal.AnimalBoardCommentDTO;
import com.project.compagnoserver.domain.user.User;
import com.project.compagnoserver.domain.user.UserDTO;
import com.project.compagnoserver.service.AnimalBoardCommentService;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/compagno/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class AnimalBoardCommentController {

    @Autowired
    private AnimalBoardCommentService animalBoardCommentService;

    public Date currentDate (){
        LocalDateTime localDateTime = LocalDateTime.now();
        return  java.sql.Timestamp.valueOf(localDateTime);
    }
    public  Object Authentication(){
        // 시큐리티에 담은 로그인한 사용자 정보 가져오기
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return authentication.getPrincipal();
    }
    // 게시글 댓글쓰기 - 권한필요
    @PostMapping("/animal-board/comment")
    public ResponseEntity<AnimalBoardComment> writeComment(@RequestBody AnimalBoardCommentDTO dto) {

//        log.info("dto : " + dto);
        Date nowDate = currentDate ();
        Object principal = Authentication();
        if(principal instanceof  User){
            User user =(User) principal;
//            log.info("user : " + user);
            AnimalBoardComment comment =AnimalBoardComment.builder()
                    .animalCommentContent(dto.getAnimalCommentContent())
                    .animalCommentDate(nowDate)
                    .animalParentCode(dto.getAnimalParentCode())
                    .animalCommentTag(dto.getAnimalCommentTag())
                    .animalBoard(AnimalBoard.builder()
                            .animalBoardCode(dto.getAnimalBoardCode())
                            .build())
                    .user(User.builder()
                            .userId(user.getUserId())
                            .build())
                    .build();
//            comment.setAnimalCommentContent(dto.getAnimalCommentContent());
//            comment.setAnimalCommentDate(nowDate);
//            comment.setAnimalParentCode(dto.getAnimalParentCode());
//            AnimalBoard board = new AnimalBoard();
//            board.setAnimalBoardCode(dto.getAnimalBoardCode()); // 어떤 글에 쓴 댓글
//            comment.setAnimalBoard(board);
////            comment.setUser(user);
//            User user = new User();
//            user.setUserNickname(dto.getUser().getUserNickname());
//            user.setUserId(dto.getUser().getUserId());
//            comment.setUser(user);

            AnimalBoardComment writtenComment = animalBoardCommentService.writeComment(comment);
//            log.info("writtenComment : " + writtenComment);
            return ResponseEntity.ok().build(); // 댓글추가
        }
    return ResponseEntity.badRequest().build();
    }

    // 댓글 수정 - 권한필요
    @PutMapping("/animal-board/comment")
    public ResponseEntity<AnimalBoardComment> updateComment(@RequestBody AnimalBoardCommentDTO dto){
//        log.info("dto : " + dto);
        Object principal = Authentication();
        if(principal instanceof  User) {
            User user = (User) principal;
            AnimalBoardComment updateComment = AnimalBoardComment.builder()
                    .animalCommentCode(dto.getAnimalCommentCode())
                    .animalParentCode(dto.getAnimalParentCode())
                    .animalCommentContent(dto.getAnimalCommentContent())
                    .animalCommentDate(dto.getAnimalCommentDate())
                    .animalCommentTag(dto.getAnimalCommentTag())
                    .animalBoard(AnimalBoard.builder()
                            .animalBoardCode(dto.getAnimalBoardCode())
                            .build())
                    .user((User.builder()
                            .userId(dto.getUser().getUserId())
                            .userNickname(dto.getUser().getUserNickname())
                            .build()))
                    .build();
            animalBoardCommentService.writeComment(updateComment);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    // 댓글삭제 - 권한필요
    @Transactional
    @PostMapping("/animal-board/deleteComment")
    public ResponseEntity<?> deleteComment (@RequestBody AnimalBoardCommentDTO commentCodes){
//        log.info("부모자식 : " + commentCodes);
        Object principal = Authentication();
        if(principal instanceof  User) {
            if(commentCodes.getAnimalParentCode()!=0){ // 부모코드가 0이 아니면, 즉 자식댓글일때
                animalBoardCommentService.deleteComment(commentCodes.getAnimalCommentCode());
            } else{ // 부모 코드가 0일때 - 즉 부모 댓글일때, 부모의 코드는 자식의 parentcode
                animalBoardCommentService.deleteParentChildren(commentCodes.getAnimalCommentCode());
            }

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    // 댓글 불러오기 -권한 미필요
    @GetMapping("/public/animal-board/{animalBoardCode}/comment")
    public ResponseEntity<List<AnimalBoardCommentDTO>> getAnimalBoardComments(@PathVariable(name = "animalBoardCode") int boardCode){
        List<AnimalBoardComment> topList = animalBoardCommentService.topLevelComments(boardCode);
        List<AnimalBoardCommentDTO> comments = new ArrayList<>();

        for(AnimalBoardComment topReply : topList){
            List<AnimalBoardComment> replies = animalBoardCommentService.bottomLevelComments(boardCode, topReply.getAnimalCommentCode());
            List<AnimalBoardCommentDTO> repliesDTO = new ArrayList<>();
            for(AnimalBoardComment bottomReply : replies){
                AnimalBoardCommentDTO dto = AnimalBoardCommentDTO.builder()
                        .animalBoardCode(bottomReply.getAnimalBoard().getAnimalBoardCode())
                        .animalCommentCode(bottomReply.getAnimalCommentCode())
                        .animalCommentContent(bottomReply.getAnimalCommentContent())
                        .animalCommentDate(bottomReply.getAnimalCommentDate())
                        .animalParentCode(bottomReply.getAnimalParentCode())
                        .animalCommentTag(bottomReply.getAnimalCommentTag())
                        .user(UserDTO.builder()
                                .userId(bottomReply.getUser().getUserId())
                                .userNickname(bottomReply.getUser().getUserNickname())
                                .userImg(bottomReply.getUser().getUserImg())
                                .build())
                        .build();
                repliesDTO.add(dto); // 대댓글 dto
            }
            AnimalBoardCommentDTO dto = AnimalBoardCommentDTO.builder()
                    .animalBoardCode(topReply.getAnimalBoard().getAnimalBoardCode())
                    .animalCommentCode(topReply.getAnimalCommentCode())
                    .animalCommentContent(topReply.getAnimalCommentContent())
                    .animalCommentDate(topReply.getAnimalCommentDate())
                    .user(UserDTO.builder()
                            .userId(topReply.getUser().getUserId())
                            .userNickname(topReply.getUser().getUserNickname())
                            .userImg(topReply.getUser().getUserImg())
                            .build())
                    .replies(repliesDTO)
                    .build();
            comments.add(dto); //상위댓글DTO
        }
        log.info("comments : " + comments);
        return ResponseEntity.ok(comments);
    }
    // 댓글 개수
    @GetMapping("/public/animal-board/{animalBoardCode}/countComment")
    public ResponseEntity<Integer> countComment (@PathVariable(name = "animalBoardCode") int boardCode){
        Integer count = animalBoardCommentService.countComment(boardCode);
        return ResponseEntity.ok(count);
    }
}
