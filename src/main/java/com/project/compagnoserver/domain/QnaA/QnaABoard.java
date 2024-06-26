package com.project.compagnoserver.domain.QnaA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.compagnoserver.domain.QnaQ.QnaQBoard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Table(name="qna_a_board")
public class QnaABoard {
    @Id @Column(name="qna_a_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qnaACode;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name="qna_q_code")
    private QnaQBoard qnaQCode;

    @Column(name="user_id")
    private String userId;

    @Column(name="qna_a_title")
    private String qnaATitle;

    @Column(name="qna_a_content")
    private String qnaAContent;

    @CreationTimestamp
    @Column(name="qna_a_date")
    private Timestamp qnaADate;

    @UpdateTimestamp
    @Column(name="qna_a_date_update")
    private Timestamp qnaADateUpdate;

}
