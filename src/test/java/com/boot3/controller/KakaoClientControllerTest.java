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
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost:8888/kakaoapi")
class KakaoClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getKakaoBlogListByResttemplateForRecordTest() throws Exception {
        // given
        String keyword = "여행";
        String sort = "accuracy";
        Integer page = 1;
        Integer size = 1;

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/blog/kakao/search/resttemplate/record")
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
                                        , fieldWithPath("data.meta.total_count").type(JsonFieldType.NUMBER).description("전체 건수")
                                        , fieldWithPath("data.meta.pageable_count").type(JsonFieldType.NUMBER).description("조회건수")
                                        , fieldWithPath("data.meta.is_end").type(JsonFieldType.VARIES).description("마지막 페이지 여")
                                        , fieldWithPath("data.documents[0].title").type(JsonFieldType.STRING).description("제목")
                                        , fieldWithPath("data.documents[0].contents").type(JsonFieldType.STRING).description("요약정")
                                        , fieldWithPath("data.documents[0].url").type(JsonFieldType.STRING).description("URL")
                                        , fieldWithPath("data.documents[0].blogname").type(JsonFieldType.STRING).description("블로그명")
                                        , fieldWithPath("data.documents[0].thumbnail").type(JsonFieldType.STRING).description("썸네일URL")
                                        , fieldWithPath("data.documents[0].datetime").type(JsonFieldType.VARIES).description("작성일자")
                                )
                        )
                );
    }

    @Test
    void getKakaoBlogListByResttemplateTest() throws Exception {
        // given
        String keyword = "여행";
        String sort = "accuracy";
        Integer page = 1;
        Integer size = 1;

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/blog/kakao/search/resttemplate")
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
                                        , fieldWithPath("data.meta.total_count").type(JsonFieldType.NUMBER).description("전체 건수")
                                        , fieldWithPath("data.meta.pageable_count").type(JsonFieldType.NUMBER).description("조회건수")
                                        , fieldWithPath("data.meta.is_end").type(JsonFieldType.VARIES).description("마지막 페이지 여")
                                        , fieldWithPath("data.documents[0].title").type(JsonFieldType.STRING).description("제목")
                                        , fieldWithPath("data.documents[0].contents").type(JsonFieldType.STRING).description("요약정")
                                        , fieldWithPath("data.documents[0].url").type(JsonFieldType.STRING).description("URL")
                                        , fieldWithPath("data.documents[0].blogname").type(JsonFieldType.STRING).description("블로그명")
                                        , fieldWithPath("data.documents[0].thumbnail").type(JsonFieldType.STRING).description("썸네일URL")
                                        , fieldWithPath("data.documents[0].datetime").type(JsonFieldType.VARIES).description("작성일자")
                                )
                        )
                );
    }

    @Test
    void getKakaoBlogListByWebclientTest() throws Exception {
        // given
        String keyword = "여행";
        String sort = "accuracy";
        Integer page = 1;
        Integer size = 1;

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/blog/kakao/search/webclient")
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
                                        , fieldWithPath("data.meta.total_count").type(JsonFieldType.NUMBER).description("전체 건수")
                                        , fieldWithPath("data.meta.pageable_count").type(JsonFieldType.NUMBER).description("조회건수")
                                        , fieldWithPath("data.meta.is_end").type(JsonFieldType.VARIES).description("마지막 페이지 여")
                                        , fieldWithPath("data.documents[0].title").type(JsonFieldType.STRING).description("제목")
                                        , fieldWithPath("data.documents[0].contents").type(JsonFieldType.STRING).description("요약정")
                                        , fieldWithPath("data.documents[0].url").type(JsonFieldType.STRING).description("URL")
                                        , fieldWithPath("data.documents[0].blogname").type(JsonFieldType.STRING).description("블로그명")
                                        , fieldWithPath("data.documents[0].thumbnail").type(JsonFieldType.STRING).description("썸네일URL")
                                        , fieldWithPath("data.documents[0].datetime").type(JsonFieldType.VARIES).description("작성일자")
                                )
                        )
                );
    }

    @Test
    void getKakaoBlogListByFeignclientTest() throws Exception {
        // given
        String keyword = "여행";
        String sort = "accuracy";
        Integer page = 1;
        Integer size = 1;

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/blog/kakao/search/feign")
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
                                        , fieldWithPath("data.meta.total_count").type(JsonFieldType.NUMBER).description("전체 건수")
                                        , fieldWithPath("data.meta.pageable_count").type(JsonFieldType.NUMBER).description("조회건수")
                                        , fieldWithPath("data.meta.is_end").type(JsonFieldType.VARIES).description("마지막 페이지 여")
                                        , fieldWithPath("data.documents[0].title").type(JsonFieldType.STRING).description("제목")
                                        , fieldWithPath("data.documents[0].contents").type(JsonFieldType.STRING).description("요약정")
                                        , fieldWithPath("data.documents[0].url").type(JsonFieldType.STRING).description("URL")
                                        , fieldWithPath("data.documents[0].blogname").type(JsonFieldType.STRING).description("블로그명")
                                        , fieldWithPath("data.documents[0].thumbnail").type(JsonFieldType.STRING).description("썸네일URL")
                                        , fieldWithPath("data.documents[0].datetime").type(JsonFieldType.VARIES).description("작성일자")
                                )
                        )
                );
    }
}