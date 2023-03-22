package com.boot3.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost:8888/naverapi")
class NaverClientControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void getNaverBlogListByResttemplateForRecordTest() throws Exception {
        // given
        String keyword = "여행";
        String sort = "accuracy";
        Integer page = 1;
        Integer size = 1;

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/blog/naver/search/resttemplate/record")
                        .param("query", keyword)
                        .param("sort", sort)
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // then
        resultActions
                .andDo(
                        document("{class-name}/{method-name}",
                                responseFields(
                                        fieldWithPath("code").type(JsonFieldType.STRING).description("결과 코드")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지")
                                        , fieldWithPath("data.total").type(JsonFieldType.NUMBER).description("전체 건수")
                                        , fieldWithPath("data.start").type(JsonFieldType.NUMBER).description("시작 페이지")
                                        , fieldWithPath("data.display").type(JsonFieldType.NUMBER).description("조회 건수")
                                        , fieldWithPath("data.items[0].title").type(JsonFieldType.STRING).description("제목")
                                        , fieldWithPath("data.items[0].description").type(JsonFieldType.STRING).description("요약정")
                                        , fieldWithPath("data.items[0].link").type(JsonFieldType.STRING).description("URL")
                                        , fieldWithPath("data.items[0].bloggername").type(JsonFieldType.STRING).description("블로그명")
                                        , fieldWithPath("data.items[0].postdate").type(JsonFieldType.VARIES).description("작성일자")
                                )
                        )
                );
    }

    @Test
    void getNaverBlogListByResttemplateTest() throws Exception {
        // given
        String keyword = "여행";
        String sort = "accuracy";
        Integer page = 1;
        Integer size = 50;

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/blog/naver/search/resttemplate")
                        .param("query", keyword)
                        .param("sort", sort)
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // then
        resultActions
                .andDo(
                        document("{class-name}/{method-name}",
                                responseFields(
                                        fieldWithPath("code").type(JsonFieldType.STRING).description("결과 코드")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지")
                                        , fieldWithPath("data.total").type(JsonFieldType.NUMBER).description("전체 건수")
                                        , fieldWithPath("data.start").type(JsonFieldType.NUMBER).description("시작 페이지")
                                        , fieldWithPath("data.display").type(JsonFieldType.NUMBER).description("조회 건수")
                                        , fieldWithPath("data.items[0].title").type(JsonFieldType.STRING).description("제목")
                                        , fieldWithPath("data.items[0].description").type(JsonFieldType.STRING).description("요약정")
                                        , fieldWithPath("data.items[0].link").type(JsonFieldType.STRING).description("URL")
                                        , fieldWithPath("data.items[0].bloggername").type(JsonFieldType.STRING).description("블로그명")
                                        , fieldWithPath("data.items[0].postdate").type(JsonFieldType.VARIES).description("작성일자")
                                )
                        )
                );

    }

    @Test
    void getNaverBlogListByWebclientTest() throws Exception {
        // given
        String keyword = "여행";
        String sort = "accuracy";
        Integer page = 1;
        Integer size = 50;

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/blog/naver/search/webclient")
                                .param("query", keyword)
                                .param("sort", sort)
                                .param("page", String.valueOf(page))
                                .param("size", String.valueOf(size))
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        // then
        resultActions
                .andDo(
                        document("{class-name}/{method-name}",
                                responseFields(
                                        fieldWithPath("code").type(JsonFieldType.STRING).description("결과 코드")
                                        , fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지")
                                        , fieldWithPath("data.total").type(JsonFieldType.NUMBER).description("전체 건수")
                                        , fieldWithPath("data.start").type(JsonFieldType.NUMBER).description("시작 페이지")
                                        , fieldWithPath("data.display").type(JsonFieldType.NUMBER).description("조회 건수")
                                        , fieldWithPath("data.items[0].title").type(JsonFieldType.STRING).description("제목")
                                        , fieldWithPath("data.items[0].description").type(JsonFieldType.STRING).description("요약정")
                                        , fieldWithPath("data.items[0].link").type(JsonFieldType.STRING).description("URL")
                                        , fieldWithPath("data.items[0].bloggername").type(JsonFieldType.STRING).description("블로그명")
                                        , fieldWithPath("data.items[0].postdate").type(JsonFieldType.VARIES).description("작성일자")
                                )
                        )
                );
    }
}