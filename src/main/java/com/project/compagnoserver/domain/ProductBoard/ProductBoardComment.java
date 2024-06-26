package com.project.compagnoserver.domain.ProductBoard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.compagnoserver.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Table(name="product_board_comment")
public class ProductBoardComment {

    @Id
    @Column(name = "product_comment_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productCommentCode;

    @ManyToOne
    @JoinColumn(name="product_board_code")
    @JsonIgnore
    private ProductBoard productBoard;

    @Column(name = "product_comment_content")
    private String productCommentContent;

    @Column(name = "product_comment_regi_date")
    private Date productCommentRegiDate;

    @Column(name = "product_comment_delete")
    private Character productCommentDelete;

    @Column(name = "product_parent_code")
    private int productParentCode;

    @JsonIgnore
    @ManyToOne
    @JoinColumn (name = "product_parent_code", referencedColumnName = "product_comment_code", insertable = false, updatable = false)
    private ProductBoardComment parent;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
