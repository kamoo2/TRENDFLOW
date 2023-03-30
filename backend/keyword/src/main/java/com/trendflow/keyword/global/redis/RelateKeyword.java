package com.trendflow.keyword.global.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RelateKeyword {
    private Integer rank;
    private String keyword;
    private String type;
    private Integer step;
    private Long relatedCount;
}
