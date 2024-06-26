package com.project.compagnoserver.domain.SitterBoard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SitterBoardDTO {

    private int sitterBoardCode;

    private SitterCategory sitterCategory;

    private int animalCategoryCode;

    private int locationCode;

    private String sitterTitle;

    private String sitterContent;

    private String userNickname;

    private String sitterRegiDate;

    private String sitterUpdateDate;

    private List<MultipartFile> files;
    private List<SitterBoardImage> images;

}
