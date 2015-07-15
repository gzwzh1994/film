package com.bysj.vo;
/**
 * 
 * @author gzwzh1994
 *
 */
public class SeatCol {
	private Integer ColumnId;
	private String ColumnNo;
	private Integer RowId;
	private String RowNo;
	private String SeatNo;
	private Integer SeatType;
	private Integer Status;

	public Integer getColumnId() {
		return ColumnId;
	}

	public void setColumnId(Integer columnId) {
		ColumnId = columnId;
	}

	public String getColumnNo() {
		return ColumnNo;
	}

	public void setColumnNo(String columnNo) {
		ColumnNo = columnNo;
	}

	public Integer getRowId() {
		return RowId;
	}

	public void setRowId(Integer rowId) {
		RowId = rowId;
	}

	public String getRowNo() {
		return RowNo;
	}

	public void setRowNo(String rowNo) {
		RowNo = rowNo;
	}

	public String getSeatNo() {
		return SeatNo;
	}

	public void setSeatNo(String seatNo) {
		SeatNo = seatNo;
	}

	public Integer getSeatType() {
		return SeatType;
	}

	public void setSeatType(Integer seatType) {
		SeatType = seatType;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public SeatCol() {
		super();
	}

	public SeatCol(Integer columnId, String columnNo, Integer rowId,
			String rowNo, String seatNo, Integer seatType, Integer status) {
		super();
		ColumnId = columnId;
		ColumnNo = columnNo;
		RowId = rowId;
		RowNo = rowNo;
		SeatNo = seatNo;
		SeatType = seatType;
		Status = status;
	}

}
