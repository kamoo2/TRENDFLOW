package com.trendflow.analyze.analyze.controller;

import com.trendflow.analyze.analyze.dto.request.*;
import com.trendflow.analyze.analyze.dto.response.*;
import com.trendflow.analyze.analyze.dto.vo.Payload;
import com.trendflow.analyze.analyze.service.AnalyzeService;
import com.trendflow.analyze.global.config.SseEmitters;
import com.trendflow.analyze.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/analyze")
public class AnalyzeController {
    private final AnalyzeService analyzeService;
    private final SseEmitters sseEmitters;

    @GetMapping("/social")
    public ResponseEntity<List<FindSocialResponse>> findSocial(@RequestParam String keyword,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){

        log.info("findSocial - Call");

        try {
            List<FindSocialResponse> findSocialResponseList
                    = analyzeService.findSocial(FindSocialRequest.builder()
                                                .keyword(keyword)
                                                .startDate(startDate)
                                                .endDate(endDate)
                                                .build());

            return ResponseEntity.ok().body(findSocialResponseList);
        } catch (NotFoundException e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e){
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/related")
    public ResponseEntity<List<FindRelationContentResponse>> findRelationContent(@RequestParam String keyword,
                                                                                 @RequestParam String code,
                                                                                 @RequestParam Integer page,
                                                                                 @RequestParam Integer perPage,
                                                                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        log.info("findRelationContent - Call");

        try {
            List<FindRelationContentResponse> findRelationContentResponseList
                    = analyzeService.findRelationContent(FindRelationContentRequest.builder()
                                                        .keyword(keyword)
                                                        .code(code)
                                                        .page(page)
                                                        .perPage(perPage)
                                                        .startDate(startDate)
                                                        .endDate(endDate)
                                                        .build());

            return ResponseEntity.ok().body(findRelationContentResponseList);
        } catch (NotFoundException e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/youtube")
    public ResponseEntity<FindYoutubeResponse> findYoutube(@RequestParam String link){
        log.info("findYoutube - Call");

        try {
            FindYoutubeResponse findYoutubeResponse = analyzeService.findYoutube(link);
            return ResponseEntity.ok().body(findYoutubeResponse);
        } catch (NotFoundException e){
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e){
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/youtube/comment")
    public ResponseEntity<List<FindYoutubeCommentResponse>> findYoutubeComment(@RequestParam String link,
                                                            @RequestParam Integer code,
                                                            @RequestParam Integer page,
                                                            @RequestParam Integer perPage){
        log.info("findYoutubeComment - Call");

        try {
            List<FindYoutubeCommentResponse> findYoutubeCommentResponseList
                    = analyzeService.findYoutubeComment(FindYoutubeCommentRequest.builder()
                                                            .link(link)
                                                            .code(code)
                                                            .page(page)
                                                            .perPage(perPage)
                                                            .build());

            return ResponseEntity.ok().body(findYoutubeCommentResponseList);
        } catch (NotFoundException e){
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e){
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }
    @GetMapping("/compare")
    public ResponseEntity<FindCompareKeywordResponse> findCompareKeyword(@RequestParam String keyword1,
                                                            @RequestParam String keyword2,
                                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        log.info("findYoutubeComment - Call");

        try {
            FindCompareKeywordResponse findCompareKeywordResponse
                    = analyzeService.findCompareKeyword(FindCompareKeywordRequest.builder()
                                                            .keywordA(keyword1)
                                                            .keywordB(keyword2)
                                                            .startDate(startDate)
                                                            .endDate(endDate)
                                                            .build());
            return ResponseEntity.ok().body(findCompareKeywordResponse);
        } catch (NotFoundException e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    // feign
    @PostMapping("/relate")
    public ResponseEntity<List<FindRelationKeywordResponse>> findRelationKeyword(@RequestBody List<Long> keywordIdList){
        log.info("findRelationKeyword - Call");

        try {
            List<FindRelationKeywordResponse> findLocalCodeResponseList = analyzeService.findRelationKeyword(keywordIdList);
            return ResponseEntity.ok().body(findLocalCodeResponseList);
        } catch (NotFoundException e){
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping("/relate/wordcloud")
    public ResponseEntity<List<FindWordCloudKeywordResponse>> findWordCloudKeyword(@RequestBody List<Long> keywordIdList){
        log.info("findWordCloudKeyword - Call");

        try {
            List<FindWordCloudKeywordResponse> findWordCloudKeywordResponseList = analyzeService.findWordCloudKeyword(keywordIdList);
            return ResponseEntity.ok().body(findWordCloudKeywordResponseList);
        } catch (NotFoundException e){
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
