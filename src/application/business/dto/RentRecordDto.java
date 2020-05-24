package application.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentRecordDto {
	
	private int idRecord;
	private String driver;
	private String car;
	
	private String rentDate;
	private String plannedReturnDate;
	
	private String returnDate;
	private double fuelInTankPercent;
	private double damagesRepairPrice;
	
	private double total;
	private boolean recordClosed;
}
