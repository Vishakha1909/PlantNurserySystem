package com.amdocs.plant.main;

import java.util.List;
import java.util.Scanner;

import com.amdocs.plant.dao.PlantDAO;
import com.amdocs.plant.model.Plant;

public class PlantMain {
	/**
	 * 
	 */
	public static void displayMenu() {
		System.out.println("Welcome to Shy's Plant Nursery!\nWhat would you like to do today?");
		System.out.print("\n\nMENU\n1. Add a new plant.\n2. Update a plant cost.\n3. Delete a plant.\n");
		System.out.print(
				"4. View all plants.\n5. Search plant by origin country.\n6. Search outdoor plant with sunlight.\n");
		System.out.print("7. Count plants with water supply frequency.\n8. Exit.");
		System.out.println("\n\nEnter the number of choice: ");
	}

	public static void main(String args[]) {
		int choice = 0;
		int mch = 0;
		Scanner sc = new Scanner(System.in);
		do {
			displayMenu();
			choice = sc.nextInt();
			switch (choice) {
			case 1: {
				System.out.println("\n\nPlease enter details of the plant to be added.");
				Plant newPlant = new Plant();
				System.out.print("\nPlantID: ");
				newPlant.setPlantID(sc.nextInt());
				System.out.print("\nPlant Name: ");
				newPlant.setPlantName(sc.next());
				System.out.print("\nOrigin Country: ");
				newPlant.setOriginCountryName(sc.next());
				System.out.print("\nSunlight Required((1)True/(0)False): ");
				newPlant.setSunlightRequired(sc.nextInt());
				System.out.print("\nWater Supply Frequency: ");
				newPlant.setWaterSupplyFrequency(sc.next());
				System.out.print("\nPlant Type: ");
				newPlant.setPlantType(sc.next());
				System.out.print("\nPlant Cost: ");
				newPlant.setCost(sc.nextFloat());
				int result = PlantDAO.addPlant(newPlant);
				if (result == 1) {
					System.out.println("Plant has been successfully added to the system.");
				} else {
					System.out.println("Plant could not be added, try again.");
				}
				break;
			}
			case 2: {
				System.out.print("Enter the plant id to update: ");
				int pid = sc.nextInt();
				System.out.print("\nEnter the new updated cost: ");
				double newCost = sc.nextDouble();
				System.out.println(pid + " " + newCost);
				boolean result = PlantDAO.updatePlantCost(pid, newCost);
				if (result) {
					System.out.println("\nUpdated successfully.");
				} else {
					System.out.println("\nNot updated.");
				}
				break;
			}
			case 3: {
				System.out.print("\nEnter the plant if of the plant to be deleted: ");
				int delPlant = sc.nextInt();
				int result = PlantDAO.deletePlant(delPlant);
				if (result == 1) {
					System.out.println("Plant has been deleted.");
				} else {
					System.out.println("Plant has not been deleted due to some error.");
				}
				break;
			}
			case 4: {
				System.out.println("\nPLANTS");
				List<Plant> plants = PlantDAO.showAllPlants();

				for (int i = 0; i < plants.size(); i++) {
					Plant p = plants.get(i);
					System.out.println("\n\n");
					System.out.println("Plant ID is -> " + p.getPlantID());
					System.out.println("Plant Name is -> " + p.getPlantName());
					System.out.println("Plant Origin Country Name is -> " + p.getOriginCountryName());
					System.out.println("Plant Sunlight Requirement is -> " + p.getSunlightRequired());
					System.out.println("Plant Water Suppy Frequency is -> " + p.getWaterSupplyFrequency());
					System.out.println("Plant Type is -> " + p.getPlantType());
					System.out.println("Plant Cost is -> " + p.getCost());
				}
				break;
			}
			case 5: {
				System.out.print("\nEnter the origin country to find the plants: ");
				String countryName = sc.next();
				List<Plant> plantResult = PlantDAO.searchByOriginCountryName(countryName);
				for (int i = 0; i < plantResult.size(); i++) {
					Plant p = plantResult.get(i);
					System.out.println("\n\n");
					System.out.println("Plant ID is -> " + p.getPlantID());
					System.out.println("Plant Name is -> " + p.getPlantName());
					System.out.println("Plant Origin Country Name is -> " + p.getOriginCountryName());
					System.out.println("Plant Sunlight Requirement is -> " + p.getSunlightRequired());
					System.out.println("Plant Water Suppy Frequency is -> " + p.getWaterSupplyFrequency());
					System.out.println("Plant Type is -> " + p.getPlantType());
					System.out.println("Plant Cost is -> " + p.getCost());
				}
				break;
			}
			case 6: {
				List<Plant> plantResult = PlantDAO.searchOutdoorPlantsWithSunlight();
				System.out.println("The outdoor plants with sunlight requirement are: ");
				for (int i = 0; i < plantResult.size(); i++) {
					Plant p = plantResult.get(i);
					System.out.println("\n\n");
					System.out.println("Plant ID is -> " + p.getPlantID());
					System.out.println("Plant Name is -> " + p.getPlantName());
					System.out.println("Plant Origin Country Name is -> " + p.getOriginCountryName());
					System.out.println("Plant Sunlight Requirement is -> " + p.getSunlightRequired());
					System.out.println("Plant Water Suppy Frequency is -> " + p.getWaterSupplyFrequency());
					System.out.println("Plant Type is -> " + p.getPlantType());
					System.out.println("Plant Cost is -> " + p.getCost());
				}
				break;
			}
			case 7: {
				System.out.println("\nEnter the water supply frequency to find the count of plants of: ");
				String supply = sc.next();
				int plantCount = PlantDAO.countPlantsByWaterSupplyFrequency(supply);
				System.out.println("The plants with water supply frequency of " + supply + " are: " + plantCount);
				break;
			}
			case 8:
				return;
			default:
				System.err.println("Invalid Input");
			}

			System.out.println("\nWould you like to go back to main menu (1) or exit (2) ? : ");
			mch = sc.nextInt();
		} while (mch != 2);
		sc.close();
	}
}
