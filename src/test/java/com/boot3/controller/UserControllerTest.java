package com.boot3.controller;

import com.boot3.service.UserService;
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

import java.util.List;

import static com.boot3.util.ApiDocumentUtils.getDocumentRequest;
import static com.boot3.util.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost:8888/apidocs")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

//    @BeforeEach
//    public void setUpData(){
//        userService.saveTestUser();
//    }

    @Test
    @Disabled
    public void getUserList() throws Exception {

        // when
        ResultActions resultActions = mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        // then
        resultActions
                .andDo(
                        document("{class-name}/{method-name}",
                                responseFields(
                                        List.of(
                                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("DATA").ignored()
                                            , fieldWithPath("data[].email").type(JsonFieldType.STRING).description("이메일").ignored()
                                            , fieldWithPath("data[].name").type(JsonFieldType.STRING).description("아이디").ignored()
                                            , fieldWithPath("data[].pwd").type(JsonFieldType.STRING).description("암호화된 비밀번호").ignored()
                                            , fieldWithPath("data[].userId").type(JsonFieldType.STRING).description("사용자 아이디").ignored()
                                            , fieldWithPath("data[].createdDate").type(JsonFieldType.VARIES).description("생성일자").ignored()
                                        )
                                )
                        )
                )
                .andExpect(status().isOk());

    }

    @Test
    public void getUserInfo() throws Exception {
        //given
        String userId = "thj0309";

        //given
        HttpHeaders headers = new HttpHeaders();
        headers.add("region_language", "KO_KR");


        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/user/{userId}", userId)
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