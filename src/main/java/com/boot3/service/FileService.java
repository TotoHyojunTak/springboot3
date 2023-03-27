package com.boot3.service;

import com.boot3.data.dto.response.FruitDTO;
import com.boot3.data.entity.FruitEntity;
import com.boot3.data.mapstruct.FruitMapper;
import com.boot3.data.repository.FruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final FruitRepository fruitRepository;

    public List<FruitDTO> downloadExcel() {
        return FruitMapper.INSTANCE.toDtoList(fruitRepository.findAll());
    }

    public List<FruitEntity> uploadExcel(MultipartFile file) throws Exception {
        List<FruitEntity> result = new ArrayList<>();

        try {
            OPCPackage opcPackage = OPCPackage.open(file.getInputStream()); // 파일 읽어옴
            XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int resultCnt = 0; // DB에 반영된 결과 수 체크용

            // 입력된 행의 수만큼 반복
            for(int i = 1; i <= sheet.getLastRowNum(); i++) {

                XSSFRow row = sheet.getRow(i); // i번째 행 가져옴
                XSSFCell cell = null;

                Integer seq = null;
                String name = null;
                String season = null;
                Integer price = null;
                String region = null;

                // 행 존재 여부 확인
                if(row == null) continue; // 행이 존재하지 않으면 돌지 않는다

                // 열 존재 여부 확인
                cell = row.getCell(0);
                if (null != cell){
                    seq = (int) cell.getNumericCellValue();
                }

                cell = row.getCell(1);
                if (null != cell){
                    name = cell.getStringCellValue();
                }

                cell = row.getCell(2);
                if (null != cell){
                    season = cell.getStringCellValue();
                }

                cell = row.getCell(3);
                if (null != cell){
                    price = (int) cell.getNumericCellValue();
                }

                cell = row.getCell(4);
                if (null != cell){
                    region = cell.getStringCellValue();
                }



                FruitEntity temp = FruitEntity.builder()
                        .seq(seq)
                        .name(name)
                        .season(season)
                        .price(price)
                        .region(region)
                        .build();

                result.add(temp);
                resultCnt += 1;

                log.debug(resultCnt + " / " + temp);
            }
        }
        catch (Exception e) {
            throw e;
        }
        return result;
    }

    public void saveData(List<FruitEntity> result) {
        fruitRepository.saveAll(result);
    }

    public void deleteAllData() {
        fruitRepository.deleteAll();
    }


}
