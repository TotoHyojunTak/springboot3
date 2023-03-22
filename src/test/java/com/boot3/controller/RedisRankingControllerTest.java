package com.boot3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost:8888/apidocs")
class RedisRankingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisRankingController redisRankingController;

    @BeforeEach
    @Disabled
    public void setUpData(){
        redisRankingController.generateData();
    }

    @Test
    @Disabled
    public void getTopRankTest() throws Exception {

        //given
        MultiValueMap<String, String> p = new LinkedMultiValueMap<>();
        p.add("limit", "50");

        HttpHeaders headers = new HttpHeaders();
        headers.add("region_language", "KO_KR");

        // when
        ResultActions resultActions = mockMvc.perform(get("/redis/rank")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParams(p)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print());

        // then
        resultActions
                .andDo(
                        document("{class-name}/{method-name}",
//                                responseFields(
//                                        List.of(
//                                              fieldWithPath("data").type(JsonFieldType.ARRAY).description("DATA").ignored()
//                                        )
//                                )
                                responseFields(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터")
                                )
                        )
                )
                .andExpect(status().isOk());

    }

    @Test
    @Disabled
    public void getUserRankingTest() throws Exception {

        //given
        MultiValueMap<String, String> p = new LinkedMultiValueMap<>();
        p.add("userId", "user_59659");

        HttpHeaders headers = new HttpHeaders();
        headers.add("region_language", "KO_KR");

        // when
        ResultActions resultActions = mockMvc.perform(get("/redis/rank/{userId}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParams(p)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        // then
        resultActions
                .andDo(
                        document("{class-name}/{method-name}",
//                                responseFields(
//                                        List.of(
//                                              fieldWithPath("data").type(JsonFieldType.ARRAY).description("DATA").ignored()
//                                        )
//                                )
                                responseFields(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터")
                                )
                        )
                )
                .andExpect(status().isOk());

    }

    @Test
    @Disabled
    public void getUserInfo() throws Exception {
        //given
        String userId = "thj0309";

        //given
        HttpHeaders headers = new HttpHeaders();
        headers.add("region_language", "KO_KR");


        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/user/user/{userId}", userId)
                        .headers(headers)
                        //.queryParams(p)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // restDocs
        resultActions.andDo(document("{class-name}/{method-name}",
                        pathParameters(
                                parameterWithName("userId").description("사용자 ID")
                        ),
                        responseFields(
                                // 개별
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("사용자 아이디")
                                , fieldWithPath("name").type(JsonFieldType.STRING).description("아이디")
                                , fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
                                , fieldWithPath("pwd").type(JsonFieldType.STRING).description("비밀번호")
                                , fieldWithPath("createdDate").type(JsonFieldType.VARIES).description("생성일자")
                        )
                )
        );

    }


//        ResultActions result = this.mockMvc.perform(
//                RestDocumentationRequestBuilders.get("/user/{userId}", 1L)
//        );
//
//        result.andExpect(status().isOk())
//                .andDo(
//                        document("select-user"
//                                , getDocumentRequest()
//                                , getDocumentResponse()
//                                , pathParameters(
//                                        parameterWithName("userId").description("사용자 아이디")
//                                )
//                                , responseFields(
//                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("사용자 아이디")
//                                        , fieldWithPath("name").type(JsonFieldType.STRING).description("아이디")
//                                        , fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
//                                        , fieldWithPath("createdDate").type(JsonFieldType.STRING).description("생성일자")
//                                )
//                        ))
//                .andDo(print());


//    @Test
//    public void saveUser() throws Exception{
//        mockMvc.perform(
//                        post("/users")
//                )
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
}