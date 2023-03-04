package com.boot3.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost:8888/apidocs")
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Excel Upload Test")
    // @TestDesc("MockMultipartFile 동작 테스트")
    public void getMockExcelUploadTest() throws IOException {

        /*MockMultipartHttpServletRequest multipartHttpServletRequest = new MockMultipartHttpServletRequest();*/ // controller test 시 사용
        String fileName = "exceluploaddata";
        String contentType = "xlsx";
        String filePath = "src/test/resources/excel/exceluploaddata.xlsx";
        MockMultipartFile mockMultipartFile = getMockMultipartFile(fileName, contentType, filePath);

        String getFileName = mockMultipartFile.getOriginalFilename().toLowerCase();

        //assertThat(getFileName, is(fileName.toLowerCase() + "." + contentType));
        System.out.println(getFileName);
    }

    private MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
    }
}