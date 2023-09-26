package com.amdocs.plant.dao;
import java.sql.*;
import com.amdocs.plant.model.*;
import com.amdocs.plant.exception.*;
import com.amdocs.plant.dao.*;
import java.util.*;

public class PlantDAO {
	static DBConnection dbc = new DBConnection();
	static Connection con = dbc.getCon();
	public static int addPlant(Plant p) {
		String addquery = "INSERT INTO plant (plantID, plantName, originCountryName, sunlightRequired, waterSupplyFrequency, plantType, plantCost) "
				+ "VALUES (?,?,?,?,?,?,?)";
		try (PreparedStatement pst = con.prepareStatement(addquery)) 
		{
			pst.setInt(1, p.getPlantID());
			pst.setString(2,p.getPlantName());
			pst.setString(3, p.getOriginCountryName());
			pst.setInt(4, p.getSunlightRequired());
			pst.setString(5, p.getWaterSupplyFrequency());
			pst.setString(6, p.getPlantType());
			pst.setDouble(7, p.getCost());
			
			int result = pst.executeUpdate();
			if(result > 0) {
				return 1;
			}
			else {
				return 0;
			}
			
		}	catch(SQLException e)	{
			e.printStackTrace();
			}
		
		return 0;
	}
	
	public static int deletePlant(int plantid) {
		try {
			Statement st = con.createStatement();
			String selquery = "SELECT * FROM plant WHERE plantID=" + plantid;
			ResultSet res = st.executeQuery(selquery);
			if(res.next()) {
				String delquery = "DELETE FROM plant WHERE plantID=" + plantid;
				st.executeQuery(delquery);
				return 1;
			}
			else {
				throw new PlantIDNotFoundException();
			} 
		} catch (SQLException | PlantIDNotFoundException p) {
			System.out.println("\nPlantIDNotFoundException");
		}
		return 0;
	}
	
	public static boolean updatePlantCost(int id, double newcost) {
		try {
			
			Statement st = con.createStatement();
			if(newcost < 0) {
				throw new InvalidCostException();
			}
			String updatequery = "UPDATE plant SET plantcost = " + newcost + " WHERE plantid = " + id;
			
			int res = st.executeUpdate(updatequery);
			
			if(res > 0) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException | InvalidCostException i) {
			System.out.println("InvalidCostException");
		}
		return false;
	}
	
	public static ArrayList<Plant> showAllPlants() {
		ArrayList<Plant> plants = new ArrayList<Plant>();		
		try {
			Statement st = con.createStatement();
			ResultSet r = st.executeQuery("Select * from plant");
			
			while(r.next()) {
				int pid = r.getInt("plantID");
				String name = r.getString("plantName");
				String country = r.getString("originCountryName");
				int sunlightRequired = r.getInt("sunlightRequired");
				String water = r.getString("WaterSupplyfrequency");
				String type = r.getString("planttype");
				float co = r.getFloat("plantcost");
				Plant pnew = new Plant(pid, name, country, sunlightRequired, water,type,co);
				plants.add(pnew);
			}
		}	catch(SQLException e){}
		return plants;
	}
	
	public static ArrayList<Plant> searchByOriginCountryName(String searchName) {
		ArrayList<Plant> resultPlants = new ArrayList<Plant>();
		try {
			Statement st = con.createStatement();
			String searchquery = "SELECT * FROM plant WHERE originCountryName = '" + searchName + "'";
			ResultSet res = st.executeQuery(searchquery);
			while(res.next()) {
				int id = res.getInt("plantID");
				String name = res.getString("plantName");
				String country = res.getString("originCountryName");
				int sunlightRequired = res.getInt("sunlightRequired");
				String water = res.getString("WaterSupplyfrequency");
				String type = res.getString("planttype");
				double co = res.getFloat("plantcost");
				Plant pnew = new Plant(id, name, country, sunlightRequired, water, type, co);
				resultPlants.add(pnew);
			}
			if(resultPlants.isEmpty()) {
				throw new PlantIDNotFoundException();
			}
		} catch(SQLException | PlantIDNotFoundException p) {
			System.out.println("\nPlantIDNotFoundException");
		}
		return resultPlants;
	}
	
	public static ArrayList<Plant> searchOutdoorPlantsWithSunlight() {
		ArrayList<Plant> resultPlants = new ArrayList<Plant>();
		try {
			Statement st = con.createStatement();
			String searchquery = "SELECT * FROM plant WHERE plantType = 'Outdoor' AND sunlightRequired = 1";
			ResultSet res = st.executeQuery(searchquery);
			while(res.next()) {
				int id = res.getInt("plantID");
				String name = res.getString("plantName");
				String country = res.getString("originCountryName");
				int sunlightRequired = res.getInt("sunlightRequired");
				String water = res.getString("WaterSupplyfrequency");
				String type = res.getString("planttype");
				double co = res.getFloat("plantcost");
				Plant pnew = new Plant(id, name, country, sunlightRequired, water, type, co);
				resultPlants.add(pnew);
			}
		} catch (SQLException e) {
			
		}
		return resultPlants;
	}
	
	public static int countPlantsByWaterSupplyFrequency(String supply) {
		int count = 0;
		String countquery = "select count(*) from plant where watersupplyfrequency='" + supply + "'";
		try {
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery(countquery);
			if(res.next()) {
				return res.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
