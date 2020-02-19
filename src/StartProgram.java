//Purpose of StartProgram is to find/create the item and pass it to ListItemHelper
//Left off on updating an item (page 11)

import java.util.List;
import java.util.Scanner;

import controller.ListItemHelper;
import model.ListPet;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static ListItemHelper lih = new ListItemHelper();

		private static void addAnItem() {
			System.out.print("Enter a Breed: ");
			String type = in.nextLine();
			System.out.print("Enter a name: ");
			String name = in.nextLine();
			System.out.println("Enter the owner: ");
			String owner = in.nextLine();
			
			ListPet toAdd = new ListPet(type, name, owner);
			lih.insertPet(toAdd);
		}

		private static void deleteAnItem() {
			System.out.print("Enter the breed to delete: ");
			String type = in.nextLine();
			System.out.print("Enter the pet's name to delete: ");
			String name = in.nextLine();
			System.out.println("Enter the owner to delete: ");
			String owner = in.nextLine();
			
			ListPet toDelete = new ListPet(type, name, owner);
			lih.deletePet(toDelete);

		}

		private static void editAnItem() {
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Breed");
			System.out.println("2 : Search by Name");
			int searchBy = in.nextInt();
			in.nextLine();
			List<ListPet> foundPet;
			if (searchBy == 2) {
				System.out.print("Enter the pet name: ");
				String petName = in.nextLine();
				foundPet = lih.searchForPetByName(petName);				
			} else {
				System.out.print("Enter the breed: ");
				String typeName = in.nextLine();
				foundPet = lih.searchForPetByType(typeName);
			}

			if (!foundPet.isEmpty()) {
				System.out.println("Found Results.");
				for (ListPet l : foundPet) {
					System.out.println(l.getId() + " : " + l.getName());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				ListPet toEdit = lih.searchForPetById(idToEdit);
				System.out.println("Retrieved " +  toEdit.getName() + " from " + toEdit.getType());
				System.out.println("1 : Update Breed");
				System.out.println("2 : Update Name");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Breed: ");
					String newBreed = in.nextLine();
					((ListPet) toEdit).setType(newBreed);
				}
				
				if (update == 2) {
					System.out.print("New Name: ");
					String newName = in.nextLine();
					((ListPet) toEdit).setName(newName);
				}

				lih.updatePet((ListPet) toEdit);

			} else {
				System.out.println("---- No results found");
			}
		}

		public static void main(String[] args) {
			runMenu();
		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to the pet daycare ---");
			while (goAgain) {
				System.out.println("*  Select an option:");
				System.out.println("*  1 -- Add a pet");
				System.out.println("*  2 -- Edit a pet");
				System.out.println("*  3 -- Delete a pet");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnItem();
				} else if (selection == 2) {
					editAnItem();
				} else if (selection == 3) {
					deleteAnItem();
				} else if (selection == 4) {
					viewTheList();
				} else {
					lih.cleanUp();
					System.out.println("   ending program   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			List<ListPet> allPets = lih.showAllPets();
			for(ListPet singlePet : allPets) {
				System.out.println(singlePet.returnPetDetails());
			}
		}
	}