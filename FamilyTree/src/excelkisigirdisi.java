

import org.apache.poi.xssf.usermodel.*;

import java.io.*;

import java.time.LocalDateTime;



public class excelkisigirdisi {
    public static int sayfanumarasi=0;
    public static void girdiOku() throws IOException {
        String excelPath = "C:\\Users\\yalnd\\Downloads\\TestDosyasi.xlsx";
        FileInputStream excelfile = new FileInputStream(excelPath);

        XSSFWorkbook excel = new XSSFWorkbook(excelfile);

        XSSFSheet sheet1 = excel.getSheetAt(sayfanumarasi);

        int rows = sheet1.getLastRowNum();
        int cols = sheet1.getRow(0).getLastCellNum();



        for (int i = 1; i <= rows ; i++) {
            XSSFRow row = sheet1.getRow(i);
            Kisi yenikisi = new Kisi();
            XSSFCell cell = row.getCell(0);
            yenikisi.id = (int) cell.getNumericCellValue();
            cell = row.getCell(1);
            yenikisi.adi = cell.getStringCellValue();
            cell = row.getCell(2);
            yenikisi.soyadi = cell.getStringCellValue();
            cell = row.getCell(3);
            yenikisi.dogumtarihi = cell.getStringCellValue();
            cell = row.getCell(4);
            String esininadi = cell.getStringCellValue();
            if (esininadi != "") {
                String arr[] = esininadi.split(" ", 2);
                yenikisi.esininAdi = arr[0];
            } else
                yenikisi.esininAdi = null;
            cell = row.getCell(5);
            yenikisi.esID = (int) cell.getNumericCellValue();
            cell = row.getCell(6);
            yenikisi.annesininAdi = cell.getStringCellValue();
            cell = row.getCell(7);
            yenikisi.babasininAdi = cell.getStringCellValue();
            cell = row.getCell(8);
            yenikisi.kangrubu = cell.getStringCellValue();
            cell = row.getCell(9);
            yenikisi.meslegi = cell.getStringCellValue();
            cell = row.getCell(10);
            if (cell.getStringCellValue().equals("Evli"))
                yenikisi.evliMi = true;
            else
                yenikisi.evliMi = false;
            cell = row.getCell(11);
            yenikisi.kizliksoyadi = cell.getStringCellValue();
            cell = row.getCell(12);
            if (cell.getStringCellValue().equals("Erkek") || cell.getStringCellValue().equals("erkek"))
                yenikisi.cinsiyet = Cinsiyet.ERKEK;
            else
                yenikisi.cinsiyet = Cinsiyet.KIZ;
            FamilyTree.herkes.add(yenikisi);
        }
    }
    public static void main(String[] args) throws IOException {
        girdiOku();
        FamilyTree.main();
    }}

