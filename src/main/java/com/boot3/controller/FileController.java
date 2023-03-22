package com.boot3.controller;

import com.boot3.data.dto.response.FruitRecordDTO;
import com.boot3.data.entity.FruitEntity;
import com.boot3.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
@Slf4j
@Tag(name="File Controller", description="File Controller 구현하기")
public class FileController {

    private final FileService fileService;

    @GetMapping("/temp/{n}")
    public void temp(@PathVariable int n){
        //IntStream.rangeClosed(1, n).forEach(System.out::println);
        System.gc();

        if("1".equals("1")){
            System.out.println("1");
        }


        /*
        기존의 GC 알고리즘으로는 큰 메모리에서 좋은 성능(짧은 STW)을 낼 수 없기 때문에 이를 개선하기 위해 등장하였음.
        G1 GC는 큰 Heap 메모리에서 짧은 GC 시간을 보장하는데 그 목적을 가지고 있다

        기존 GC와는 다른 알고리즘을 가지고 있으며, 전체 heap 메모리 영역을 Region 이라는
        */

    }

    // 엑셀 다운로드
    @GetMapping("/excel/download")
    @Operation(description = "Excel Download")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        log.debug("Excel Download 기능");
        List<FruitRecordDTO> data = fileService.downloadExcel();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("첫번째 시트");
        XSSFRow row = null;
        XSSFCell cell = null;
        int rowNum = 0;

        // Header
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("연번");
        cell = row.createCell(1);
        cell.setCellValue("이름");
        cell = row.createCell(2);
        cell.setCellValue("계절");
        cell = row.createCell(3);
        cell.setCellValue("가격");
        cell = row.createCell(4);
        cell.setCellValue("지역");
        cell = row.createCell(5);
        cell.setCellValue("생성일자");

        // Body
        for (int i = 0; i < data.size(); i++) {
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue(data.get(i).seq());
            cell = row.createCell(1);
            cell.setCellValue(data.get(i).name());
            cell = row.createCell(2);
            cell.setCellValue(data.get(i).season());
            cell = row.createCell(3);
            cell.setCellValue(data.get(i).price());
            cell = row.createCell(4);
            cell.setCellValue(data.get(i).region());
            cell = row.createCell(5);
            cell.setCellValue(data.get(i).createdDate());
        }

        data.forEach(e -> {
            log.debug(e.toString());
        });

        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=exceldownload.xlsx");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();

    }

    // 엑셀 업로드
    @PostMapping("/excel/upload")
    @Operation(description = "Excel Upload")
    public void uploadExcel(MultipartHttpServletRequest request, HttpServletResponse response) {
        log.debug("Excel Upload 기능");

        response.setCharacterEncoding("UTF-8");
        try {
            MultipartFile file = null;
            Iterator<String> iterator = request.getFileNames();

            // Excel 파일 가져오기
            if (iterator.hasNext()) {
                file = request.getFile(iterator.next());
            }
            List<FruitEntity> result = fileService.uploadExcel(file);

            log.debug("##### result #####");
            log.debug(result.toString());

            fileService.saveData(result);
        }
        catch (Exception e)
        {
            log.debug(e.toString());
        }

    }

    // 데이터 전체 삭제
    @DeleteMapping("/delete")
    @Operation(description = "Data All Deleted")
    public void deleteAllData(){
        fileService.deleteAllData();
    }

}
