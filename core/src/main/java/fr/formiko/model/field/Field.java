package fr.formiko.model.field;

import fr.formiko.utils.CSVReader;

import java.util.LinkedList;
import java.util.ArrayList;

/**
 * The Field class represents the board on which the Players will move their pawns
 * and have the ability to buy properties.
 */
public class Field {

    /**
     * Array of FieldElements that compose the board.
     */
    private FieldElement[] field;

    /**
     * The length of the board aka the number of FieldElements on the board.
     * It is initialized to 40.
     */
    private static final int length = 40;

    /**
     * Instantiates an object It initialize the field attribute
     * with a FieldElement array of length length and calls fieldInitialize()
     */
    public Field(Dice dice)
    {
      this.field = new FieldElement[length];
      fieldInitialize(dice);
    }

    /**
     * Sets the object of each tile in the field attribute
     */
    public void fieldInitialize(Dice dice)
    {
	//reset id attribution
	District.resetDistrictID();

	//START
	field[0] = new Start(0,"START");

	//Districts
	District brown = new District();
	District light_blue = new District();
	District purple = new District();
	District orange = new District();
	District red = new District();
	District yellow = new District();
	District green = new District();
	District blue = new District();

	//Domains
	CSVReader reader = new CSVReader("./src/main/resources/csv/monopoly_classic/domains.csv");
	reader.readData();
	ArrayList<Domain> domainsFromCSV = reader.getDomainArrayFromData();

	//adds the domains to their district
	brown.addDomain(domainsFromCSV.get(0));
	brown.addDomain(domainsFromCSV.get(1));
	light_blue.addDomain(domainsFromCSV.get(2));
	light_blue.addDomain(domainsFromCSV.get(3));
	light_blue.addDomain(domainsFromCSV.get(4));
	purple.addDomain(domainsFromCSV.get(5));
	purple.addDomain(domainsFromCSV.get(6));
	purple.addDomain(domainsFromCSV.get(7));
	orange.addDomain(domainsFromCSV.get(8));
	orange.addDomain(domainsFromCSV.get(9));
	orange.addDomain(domainsFromCSV.get(10));
	red.addDomain(domainsFromCSV.get(11));
	red.addDomain(domainsFromCSV.get(12));
	red.addDomain(domainsFromCSV.get(13));
	yellow.addDomain(domainsFromCSV.get(14));
	yellow.addDomain(domainsFromCSV.get(15));
	yellow.addDomain(domainsFromCSV.get(16));
	green.addDomain(domainsFromCSV.get(17));
	green.addDomain(domainsFromCSV.get(18));
	green.addDomain(domainsFromCSV.get(19));
	blue.addDomain(domainsFromCSV.get(20));
	blue.addDomain(domainsFromCSV.get(21));

	//place the domains on the field
	for(Domain d : domainsFromCSV){
	    field[d.getLocation()] = d;
	}


	field[2] = new FortuneWheel(2);
	field[7] = new FortuneWheel(7);
	field[17] = new FortuneWheel(17);
	field[22] = new FortuneWheel(22);
	field[33] = new FortuneWheel(33);
	field[36] = new FortuneWheel(36);

	//TrainStations
	field[5] = new TrainStation("GARE MONTPARNASSE", 200, 5, 100);
	field[15] = new TrainStation("GARE DE LYON", 200, 15, 100);
	field[25] = new TrainStation("GARE DU NORD", 200, 25, 100);
	field[35] = new TrainStation("GARE SAINT-LAZARE", 200, 35, 100);

	//Taxes
	field[4] = new LuxuryTaxe(4, "IMPÔTS SUR LE REVENU", 200);
	field[38] = new LuxuryTaxe(38, "TAXE DE LUXE", 100);

	//Jail
	field[30] = new GoToJail(30, "GO TO JAIL");
	field[10] = new Jail(10, "EN PRISON/SIMPLE VISITE");

	//Public Companies
  field[12] = new ElectricityProvider("COMPAGNIE DE DISTRIBUTION D'ÉLECTRICITÉ", 150, 12, dice, 75);
	field[28] = new WaterProvider("COMPAGNIE DE DISTRIBUTION DES EAUX", 150, 28, dice, 75);

	//Free Parking
	field[20] = new FreeParking(20, "PARC GRATUIT");
    }

    /**
     * Computes the number of domains in this field
     * @return the total number of domains in this field
     */
    private int numberOfDomains(){
	int numberOfDomains = 0;
	for(int i = 0; i < length; i++){
	    if(field[i] instanceof Domain) numberOfDomains++;
	}
	return numberOfDomains;
    }

    /**
     * Getter of the domains in this field
     * @return a linked list containing all the domains in this field
     * in order
     */
    private LinkedList<Domain> getOnlyDomains(){
	LinkedList<Domain> domainLocations = new LinkedList<>();
	for(int i = 0; i < length; i++){
	    if(field[i] instanceof Domain) domainLocations.add((Domain) field[i]);
	}
	return domainLocations;
    }

    /**
     * Getter for the field
     * @return the field of elements
     */
    public FieldElement[] getFieldElements()
    {
      return this.field;
    }

    /**
     * Getter for the length of Field
     * @return the length of Field
     */
    public static int getLength()
    {
      return length;
    }


public FieldElement get(int i)
{
        if (i >= 0 && i < length)
        {
return (field[i]);
        }
        return (field[0]);
}
}
