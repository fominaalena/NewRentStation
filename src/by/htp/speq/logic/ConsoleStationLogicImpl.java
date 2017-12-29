package by.htp.speq.logic;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import by.htp.speq.entity.Client;
import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.station.AvailableEquipment;
import by.htp.speq.station.RentedEquipment;

public class ConsoleStationLogicImpl implements StationLogic {
	
	private static AvailableEquipment availableEquipment;
	private static RentedEquipment rentedEquipment;
	private double income;
	private double fine;
	
//	static {
//		AvailableEquipment availableEquipment = new AvailableEquipment();
//		Equipment eq1 = new Equipment("Bike", Equipment.Type.OUTFIT, 8.5);
//		Equipment eq2 = new Equipment("Skiing", Equipment.Type.OUTFIT, 3.5);
//		Equipment eq3 = new Equipment("Helmet", Equipment.Type.ACCESSORY, 2.5);
//		Equipment eq4 = new Equipment("Bicycle", Equipment.Type.ACCESSORY, 2.5);
//		Equipment eq5 = new Equipment("Lifeline", Equipment.Type.ACCESSORY, 2.5);
//		Equipment eq6 = new Equipment("Lifeline11", Equipment.Type.ACCESSORY, 2.5);
//		Equipment eq7 = new Equipment("Skiing11", Equipment.Type.OUTFIT, 3.5);
//		Equipment eq8 = new Equipment("Skiing22", Equipment.Type.OUTFIT, 3.5);
//		availableEquipment.addEquipment(eq1);
//		availableEquipment.addEquipment(eq2);
//		availableEquipment.addEquipment(eq3);
//		availableEquipment.addEquipment(eq4);
//		availableEquipment.addEquipment(eq5);
//		availableEquipment.addEquipment(eq6);
//		availableEquipment.addEquipment(eq7);
//		availableEquipment.addEquipment(eq8);
//		ConsoleStationLogicImpl.availableEquipment = availableEquipment;
//		RentedEquipment rentedEquipment = new RentedEquipment();
//		ConsoleStationLogicImpl.rentedEquipment = rentedEquipment;
//	}

	@Override
	public RentedEquipment readRentedEquipment() {
		
//		Equipment eq1 = new Equipment("Eq1", Equipment.Type.OUTFIT, 2.5);
//		Equipment eq2 = new Equipment("Eq2", Equipment.Type.ACCESSORY, 4.5);
//		Equipment eq3 = new Equipment("Eq3", Equipment.Type.OUTFIT, 2.5);
//		Client client = new Client(1234);
//		RentUnit re1 = new RentUnit(eq1, Utils.createDate(2017, 12, 01, 15, 30), client);
//		RentUnit re2 = new RentUnit(eq2, Utils.createDate(2017, 12, 01, 16, 15), client);
//		RentUnit re3 = new RentUnit(eq3, Utils.createDate(2017, 12, 01, 17, 00), client);
//		RentedEquipment catalog = new RentedEquipment();
//		catalog.addRentUnit(re1);
//		catalog.addRentUnit(re2);
//		catalog.addRentUnit(re3);

		return rentedEquipment;
	}

	@Override
	public AvailableEquipment readAvailableEquipment() throws FileNotFoundException {
		return availableEquipment;
	}

	@Override
	public void writeRentedEquipment(RentedEquipment rentedEquipment) throws FileNotFoundException {
		ConsoleStationLogicImpl.rentedEquipment = rentedEquipment;
	}

	@Override
	public void writeAvailableEquipment(AvailableEquipment availableEquipment) throws FileNotFoundException {
		ConsoleStationLogicImpl.availableEquipment = availableEquipment;
	}

	@Override
	public void addIncome(double income){
		this.income += income;
	}

	@Override
	public void addFines(double fine){
		this.fine += fine;
	}

	@Override
	public double income() {
		return income;
	}

	@Override
	public double fine() {
		return fine;
	}

}
