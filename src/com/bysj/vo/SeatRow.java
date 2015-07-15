package com.bysj.vo;

import java.util.List;

public class SeatRow {
	private String RowName;
	private Integer RowNo;
	private List<?> RowSeats;
	private String SectionId;

	public String getRowName() {
		return RowName;
	}

	public void setRowName(String rowName) {
		RowName = rowName;
	}

	public Integer getRowNo() {
		return RowNo;
	}

	public void setRowNo(Integer rowNo) {
		RowNo = rowNo;
	}

	public List<?> getRowSeats() {
		return RowSeats;
	}

	public void setRowSeats(List<?> rowSeats) {
		RowSeats = rowSeats;
	}

	public String getSectionId() {
		return SectionId;
	}

	public void setSectionId(String sectionId) {
		SectionId = sectionId;
	}

	public SeatRow() {
		super();
	}

	public SeatRow(String rowName, Integer rowNo, List<?> rowSeats, String sectionId) {
		super();
		RowName = rowName;
		RowNo = rowNo;
		RowSeats = rowSeats;
		SectionId = sectionId;
	}

}
