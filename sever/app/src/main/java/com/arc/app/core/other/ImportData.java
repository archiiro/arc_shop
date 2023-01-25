package com.arc.app.core.other;

import com.arc.app.core.dto.AdministrativeImport;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImportData {
    // Import Administrative
    public static List<AdministrativeImport> getDataAdministrativeUnit(InputStream is) throws Exception {
        List<AdministrativeImport> listData = new ArrayList<AdministrativeImport>();
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            int rowIndex = 1;
            int number = datatypeSheet.getLastRowNum();
            while (rowIndex < number) {
                Row currentRow = datatypeSheet.getRow(rowIndex);
                Cell currentCell = null;
                if(currentRow != null) {
                    AdministrativeImport dto = new AdministrativeImport();
                    // Tên tỉnh
                    Integer index = 0;
                    currentCell = currentRow.getCell(index); // Lấy giá trị của ô đầu tiên trong dòng tương ứng với vòng lặp
                    if(currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String nameProvince =  String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setNameProvince(nameProvince);
                    } else if(currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getStringCellValue() != null) {
                        String nameProvince = currentCell.getStringCellValue().trim();
                        dto.setNameProvince(nameProvince);
                    }
                    // Mã tỉnh
                    index = 1;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String codeProvince = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setCodeProvince(codeProvince);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String codeProvince = currentCell.getStringCellValue().trim();
                        dto.setCodeProvince(codeProvince);
                    }
                    // Tên quận huyện
                    index = 2;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String nameDistrict = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setNameDistrict(nameDistrict);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String nameDistrict = currentCell.getStringCellValue().trim();
                        dto.setNameDistrict(nameDistrict);
                    }
                    // Mã quận huyện
                    index = 3;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String codeDistrict = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setCodeDistrict(codeDistrict);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String codeDistrict = currentCell.getStringCellValue().trim();
                        dto.setCodeDistrict(codeDistrict);
                    }
                    // Tên phường, xã
                    index = 4;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String nameCommune = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setNameCommune(nameCommune);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String nameCommune = currentCell.getStringCellValue().trim();
                        dto.setNameCommune(nameCommune);
                    }
                    // Mã phường, xã
                    index = 5;
                    currentCell = currentRow.getCell(index);
                    if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        String codeCommune = String.valueOf((int)currentCell.getNumericCellValue());
                        dto.setCodeCommune(codeCommune);
                    } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                            && currentCell.getStringCellValue() != null) {
                        String codeCommune = currentCell.getStringCellValue().trim();
                        dto.setCodeCommune(codeCommune);
                    }
                    listData.add(dto);
                }
                rowIndex++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listData;
    }
}
