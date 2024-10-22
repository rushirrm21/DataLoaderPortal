package com.patitent_induction.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.patitent_induction.demo.entities.Pinventity;
import com.patitent_induction.demo.entities.Piventity;
import com.patitent_induction.demo.repositories.InvalidRepository;
import com.patitent_induction.demo.repositories.Validrepository;

@Service
public class Patientinductionservice {

	Logger logger = LoggerFactory.getLogger(Patientinductionservice.class);

	@Autowired
	private Validrepository validRepo;

	@Autowired
	public InvalidRepository invalidRepo;

	@Autowired
	public ModelMapper modelMapper;

	private boolean saveFlag = false;

	public boolean save(MultipartFile file) {
		logger.info("save() invoked");
		try {
			List<Pinventity> listPatients = convertExcelToListOfPatients(file.getInputStream());
			logger.info("received a list from convertExcelToListOfPatients()");
			if (listPatients.isEmpty()) {
				return saveFlag;
			}
			listPatients.stream().forEach(e -> {
				try {
					Piventity convertedentity = this.modelMapper.map(e, Piventity.class);
					logger.info("Passed modelMapper successfully");
					Date date = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
					String currDate = formatter.format(date);
					String date1 = e.getPatientDateofBirth();
					Date dd = formatter.parse(date1);
					Date currdd = formatter.parse(currDate);
					if (dd.compareTo(currdd) > 0) {
						saveFlag = true;
						logger.info("Date is invalid record saving to Invalid table");
						this.invalidRepo.save(e);
					} else {
						saveFlag = true;
						logger.info("Date is valid saving to valid table");
						this.validRepo.save(convertedentity);
					}
				} catch (Exception ee) {
					logger.info("Catch block of modelMapper Exception");
					saveFlag = true;
					logger.info("saving record to Invalid table from catch block");
					this.invalidRepo.save(e);				
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Completed save() execution");
		return saveFlag;
	}

	public List<Pinventity> convertExcelToListOfPatients(InputStream is) throws IOException {
		logger.info("In convertExcelToListOfPatients()");
		List<Pinventity> list = new ArrayList<>();
		DataFormatter formatter = new DataFormatter(Locale.US);

			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(is);

			XSSFSheet sheet = workbook.getSheet("data");
			logger.info("Succesfully got the inputstream into sheet object of XSSFSheet");
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {
				Row row = iterator.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cells = row.iterator();

				int cid = 0;

				Pinventity p = new Pinventity();

				while (cells.hasNext()) {
					Cell cell = cells.next();

					switch (cid) {
					case 0:
//						No need to set patient id as we are autogenerating it
						break;
					case 1:
						p.setPatientName(cell.getStringCellValue());
						break;
					case 2:
						p.setPatientAddress(cell.getStringCellValue());
						break;
					case 3:
						setDate(cell, p);
						break;
					case 4:
						p.setPatientEmail(cell.getStringCellValue());
						break;
					case 5:
						p.setPatientContactNumber(formatter.formatCellValue(cell));
						break;
					case 6:
						p.setPatientDrugId(cell.getStringCellValue());
						break;
					case 7:
						p.setPatientDrugName(cell.getStringCellValue());
						break;
					default:
						break;
					}
					cid++;
				}
				list.add(p);
			}
		logger.info("Completed Excecution of  convertExcelToListOfPatients()");
		return list;
	}

	public void setDate(Cell cell, Pinventity p) {
		try {
			logger.info("Executing setDate()");
			Date date;
			date = cell.getDateCellValue();
			SimpleDateFormat formatterr = new SimpleDateFormat("MM-dd-yyyy");
			String toStringDatee = formatterr.format(date);
			p.setPatientDateofBirth(toStringDatee);
		} catch (Exception excp) {
			p.setPatientDateofBirth(null);
		}
	}

}
