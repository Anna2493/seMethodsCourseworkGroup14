package BusinessLayer;

import DataLayer.DB_Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CapitalReport extends Report{



    protected String capital;
    protected String country;
    protected int population;

    public CapitalReport(){
        super();
    }

    @Override
    public void loadReport(){
        DB_Connection con = DB_Connection.getInstance();
        //If the connection is successful
        if (con.Connect()){
            String sqlStatement = "SELECT city.Name,city.District,city.Population,country.Name AS 'Country' FROM city JOIN country ON city.ID=country.Capital;";
            ResultSet result = con.getResult(sqlStatement);

            try {
                if (result.next()){
                    //Load data into our object
                    this.capital = result.getString("Name");
                    this.country = result.getString("Country");
                    this.population = result.getInt("Population");
                }else{
                    System.out.println("All results loaded");
                }
            }catch (SQLException sqlError){
                System.out.println("Invalid SQL statement222!");
            }


        }



    }

    @Override
    public void displayReport(){

       System.out.println("Capital City Report:" + this.capital + "\nCountryCode:" + this.country + "\nPopulation:" + this.population);
    }
}





