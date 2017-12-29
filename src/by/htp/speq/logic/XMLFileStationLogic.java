package by.htp.speq.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.htp.speq.entity.Client;
import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.Equipment.Type;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.station.AvailableEquipment;
import by.htp.speq.station.RentedEquipment;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLFileStationLogic implements StationLogic {

	private static final String XML_EQUIPMENT_PATH = "D://AvailableEquipment.xml";
	private static final String XML_UNITS_PATH = "D://RentedEquipment.xml";
	private double income;
	private double fine;

	@Override
	public RentedEquipment readRentedEquipment() throws FileNotFoundException {

		List<RentUnit> units = new ArrayList<RentUnit>();
		try {
			File fXmlFile = new File(XML_UNITS_PATH);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Unit");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				String title = eElement.getElementsByTagName("Title").item(0).getTextContent();
				Type type = Type.valueOf(eElement.getElementsByTagName("Type").item(0).getTextContent());
				Double hourRate = Double
						.parseDouble(eElement.getElementsByTagName("HourRate").item(0).getTextContent());
				Equipment eq = new Equipment(title, type, hourRate);
				String rendDate = eElement.getElementsByTagName("RentDate").item(0).getTextContent();
				SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
				Date date = formatter.parse(rendDate);
				Integer clientId = Integer.parseInt(eElement.getElementsByTagName("ClientId").item(0).getTextContent());
				Client client = new Client(clientId);
				RentUnit un = new RentUnit(eq, date, client);
				units.add(un);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RentedEquipment(units);
	}

	@Override
	public AvailableEquipment readAvailableEquipment() throws FileNotFoundException {

		List<Equipment> availableEquipment = new ArrayList<Equipment>();

		try {
			File fXmlFile = new File(XML_EQUIPMENT_PATH);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Equipment");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				String title = eElement.getElementsByTagName("Title").item(0).getTextContent();
				Type type = Type.valueOf(eElement.getElementsByTagName("Type").item(0).getTextContent());
				Double hourRate = Double
						.parseDouble(eElement.getElementsByTagName("HourRate").item(0).getTextContent());
				Equipment eq = new Equipment(title, type, hourRate);
				availableEquipment.add(eq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AvailableEquipment(availableEquipment);
	}

	@Override
	public void writeRentedEquipment(RentedEquipment rentedEquipment) throws FileNotFoundException {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("RentedEquipment");
			doc.appendChild(rootElement);

			for (RentUnit units : rentedEquipment.getUnits()) {
				Element unitsElement = doc.createElement("Unit");
				rootElement.appendChild(unitsElement);

				Element title = doc.createElement("Title");
				title.appendChild(doc.createTextNode(units.getEquipment().getTitle()));
				unitsElement.appendChild(title);

				Element type = doc.createElement("Type");
				type.appendChild(doc.createTextNode(units.getEquipment().getType().toString()));
				unitsElement.appendChild(type);

				Element hourRate = doc.createElement("HourRate");
				hourRate.appendChild(doc.createTextNode(String.valueOf(units.getEquipment().getHourRate())));
				unitsElement.appendChild(hourRate);

				Element rentDate = doc.createElement("RentDate");
				SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
				rentDate.appendChild(doc.createTextNode(formatter.format(units.getRentDate())));

				unitsElement.appendChild(rentDate);

				Element client = doc.createElement("ClientId");
				client.appendChild(doc.createTextNode(String.valueOf(units.getClient().id)));
				unitsElement.appendChild(client);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("D:\\RentedEquipment.xml"));

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}

	@Override
	public void writeAvailableEquipment(AvailableEquipment availableEquipment) throws FileNotFoundException {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("AvailableEquipment");
			doc.appendChild(rootElement);

			for (Equipment equipment : availableEquipment.getAvailableEquipment()) {
				Element equipmentElement = doc.createElement("Equipment");
				rootElement.appendChild(equipmentElement);

				Element title = doc.createElement("Title");
				title.appendChild(doc.createTextNode(equipment.getTitle()));
				equipmentElement.appendChild(title);

				Element type = doc.createElement("Type");
				type.appendChild(doc.createTextNode(equipment.getType().toString()));
				equipmentElement.appendChild(type);

				Element hourRate = doc.createElement("HourRate");
				hourRate.appendChild(doc.createTextNode(String.valueOf(equipment.getHourRate())));
				equipmentElement.appendChild(hourRate);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("D:\\AvailableEquipment.xml"));

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}

	public static void main(String[] args) {

		Equipment equipment = new Equipment("Skiing", Type.OUTFIT, 2.3);
		Equipment equipment1 = new Equipment("Helmet", Type.ACCESSORY, 2.7);
		Equipment equipment2 = new Equipment("Skiing", Type.OUTFIT, 2.3);
		AvailableEquipment availableEquipment = new AvailableEquipment();
		availableEquipment.addEquipment(equipment);
		availableEquipment.addEquipment(equipment1);
		availableEquipment.addEquipment(equipment2);
		XMLFileStationLogic xmlStationLogic = new XMLFileStationLogic();
		try {
			xmlStationLogic.writeAvailableEquipment(availableEquipment);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			xmlStationLogic.readAvailableEquipment();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(xmlStationLogic.readAvailableEquipment().getAvailableEquipment());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RentUnit rentUnit = new RentUnit(equipment, new Date(), new Client(1235));
		RentUnit rentUnit1 = new RentUnit(equipment1, new Date(), new Client(1235));
		RentUnit rentUnit2 = new RentUnit(equipment2, new Date(), new Client(147));
		RentedEquipment rentedEquipment = new RentedEquipment();
		rentedEquipment.addRentUnit(rentUnit);
		rentedEquipment.addRentUnit(rentUnit1);
		rentedEquipment.addRentUnit(rentUnit2);
		// XMLFileStationLogic xmlStationLogic = new XMLFileStationLogic();
		try {
			xmlStationLogic.writeRentedEquipment(rentedEquipment);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			System.out.println(xmlStationLogic.readRentedEquipment().getUnits());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
