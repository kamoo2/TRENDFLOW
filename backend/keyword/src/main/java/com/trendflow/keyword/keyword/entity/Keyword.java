package com.trendflow.keyword.keyword.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "keyword")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long keywordId;
    @Column(name = "source_id")
    private Long sourceId;
    @Column(name = "platform_code")
    private String platformCode;
    @Column(name = "keyword")
    private String keyword;
    @Column(name = "count")
    private Long count;
    @Column(name = "reg_dt")
    private LocalDate regDt;
}
