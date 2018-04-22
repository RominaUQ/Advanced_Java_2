////Author: Romina Sharifpour

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.io.BufferedReader;

////setting up the menu and all the options
public class MiniNet {
	public static void main(String[] args) {
		Boolean showMenu = true;
		Driver driver = new Driver();
		
		while (showMenu) {
			showMenu = createMenu(driver);
		}
	}

	private static Boolean createMenu(Driver driver) {
		System.out.println();
		System.out.println("        MiniNet Menu           ");
		System.out.println("*******************************");
		System.out.print(" Please select an option from 0-8\r\n");
		System.out.println("");
		System.out.println("0. Add a profile to the network");
		System.out.println("1. List all the profiles");
		System.out.println("2. Look up a person");
		System.out.println("3. View the Person's profile");
		System.out.println("4. Are this profiles connected");
		System.out.println("5. Add a friend");
		System.out.println("6. Remove a friend");
		System.out.println("7. Show relatives");
		System.out.println("8. Exit the menu");
		System.out.println();
		System.out.println();

		// creating the menu
		BufferedReader menu = new BufferedReader(new InputStreamReader(System.in));

		try {

			int input = Integer.parseInt(menu.readLine());
			System.out.println("You have entered " + input + "\r\n");

			Set<Profile> profiles;
			Profile profile = null;
			Profile profile2 = null;

			String firstname;
			String familyname;
			String firstname1;
			String familyname1;
			String firstname2;
			String familyname2;

			switch (input) {
			case 0:
				System.out.println("Enter the first name:");
				firstname = menu.readLine();

				System.out.println("Enter the surname:");
				familyname = menu.readLine();

				System.out.println("Enter the status:");
				String status = menu.readLine();

				System.out.println("Enter the age:");
				int age = Integer.parseInt(menu.readLine());
				
				Boolean success = false;
				if (age > 16) {
					success = driver.createProfile(firstname, familyname, status, age);
				} else {
					System.out.println("Enter the dad's first name");
					String firstnameDad = menu.readLine();

					System.out.println("Enter the dad's surname");
					String familynameDad = menu.readLine();
					Profile parent1 = driver.searchProfile(firstnameDad, familynameDad);

					if (parent1 != null) {
						System.out.println("Enter the mom's first name");
						String firstnameMom = menu.readLine();

						System.out.println("Enter the mom's surname");
						String familynameMom = menu.readLine();
						
						Profile parent2 = driver.searchProfile(firstnameMom, familynameMom);

						if (parent2 != null) {
							success = driver.createDependent(firstname, familyname, status, age, parent1, parent2);
						} else {
							System.out.println("Profile " + firstnameMom + " doesn't exist");
						}
					} else {
						System.out.println("Profile " + firstnameDad + " doesn't exist");
					}
				}
				if (success) {
					System.out.println("Successfully created new profile");
				} else {
					System.out.println("This profile already exists");
				}
				break;

			case 1: //// listing all the profiles
				profiles = driver.listMembers();
				for (Profile p : profiles) {
					System.out.println(p.toString());
				}
				break;

			case 2: ////Looking up a person in the network
				System.out.println("Enter the first name:");
				firstname = menu.readLine();

				System.out.println("Enter the surname:");
				familyname = menu.readLine();

				profile = driver.searchProfile(firstname, familyname);

				if (profile != null) {
					System.out.println( " ");
					System.out.println(profile.getname() + " " + profile.getsurname());
				} else {
					System.out.println( " ");
					System.out.println("Profile doesn't exist");
				}
				break;

			case 3://///viewing a persons profile
				System.out.println("Enter the first name:");
				firstname = menu.readLine();
				System.out.println("Enter the surname:");
				familyname = menu.readLine();
				String friendlist = "";
				profile = driver.searchProfile(firstname, familyname);
				if (profile != null) {
					System.out.println( " ");
					System.out.println(profile.toString());
					friendlist = driver.Diplayfriendlist(firstname, familyname);

					if (!friendlist.equals("")) {
						System.out.println(friendlist);
					} else {
						System.out.println( " ");
						System.out.println(firstname + " has no friends");
					}

				} else {
					System.out.println( " ");
					System.out.println("Profile doesn't exist");
				}
				break;

			case 4:///// are the profile connected?
				System.out.println("Enter the first name");
				firstname1 = menu.readLine();

				System.out.println("Enter the surname");
				familyname1 = menu.readLine();
				profile = driver.searchProfile(firstname1, familyname1);

				if (profile != null) {
					System.out.println("Enter the first name of a friend");
					firstname2 = menu.readLine();

					System.out.println("Enter the surname of a friend");
					familyname2 = menu.readLine();
					profile2 = driver.searchProfile(firstname2, familyname2);

					if (profile2 != null) {
						Boolean isConnected = driver.areProfilesConnected(profile, profile2);
						System.out.println( " ");
						System.out.println(profile.getname() + " and " + profile2.getname() + " are"
								+ (isConnected ? "" : " not") + " connected");
					} else {
						System.out.println( " ");
						System.out.println("Profile " + firstname2 + " doesn't exist");
					}
				} else {
					
					System.out.println("Profile " + firstname1 + " doesn't exist");
				}
				break;

			case 5://// Adding a profile to your network
				System.out.println("Enter your first name");
				firstname1 = menu.readLine();

				System.out.println("Enter your surname");
				familyname1 = menu.readLine();
				profile = driver.searchProfile(firstname1, familyname1);
				if (profile != null) {
					if (profile.getage() > 2) {

						System.out.println("Enter the first name of your friend");
						firstname2 = menu.readLine();

						System.out.println("Enter the surname of your friend");
						familyname2 = menu.readLine();

						profile2 = driver.searchProfile(firstname2, familyname2);

						if (profile2 != null) {
							success = driver.AddFriend(profile, profile2);

							if (success) {
								friendlist = driver.Diplayfriendlist(firstname1, familyname1);
								System.out.println(profile.getname() + " and " + profile2.getname()
										+ " are friends now, congratz!");
							}
						} else {
							System.out.println(
									"this profile doesn't exists. You can only connect the prfoles exisiting in the network");
						}
					} else {
						System.out.println("A two year or younger can not add any friends");
					}
				}

				else {
					System.out.println(
							"this profile doesn't exists. You can only connect the prfoles exisiting in the network");

				}
				break;

			case 6://// removing a friend from your network
				System.out.println("Enter your first name");
				firstname1 = menu.readLine();

				System.out.println("Enter your surname");
				familyname1 = menu.readLine();

				profile = driver.searchProfile(firstname1, familyname1);

				System.out.println("Enter the first name of the friend you want remove");
				firstname2 = menu.readLine();

				System.out.println("Enter the surname of the friend you want remove");
				familyname2 = menu.readLine();
				profile2 = driver.searchProfile(firstname2, familyname2);

				if (profile != null) {
					if (profile2 != null && profile.getfriendlist().contains(profile2)) {
						driver.removeFriend(profile, profile2);

						System.out.println(
								profile.getname() + " and " + profile2.getname() + " are not friends anymore, T_T");
					} else {
						System.out.println("Such profile doesn't exist in your friend list");
					}
				} else {
					System.out.println(
							"this profile doesn't exists. You can only search and remove the profiles existing in the network");

				}
				break;
			
			case 7://// showing one's parent and children
				System.out.println("Enter the first name");
				firstname1 = menu.readLine();

				System.out.println("Enter the surname");
				familyname1 = menu.readLine();
				profile = driver.searchProfile(firstname1, familyname1);

				if (profile != null) {
					Set<Profile> relatives = driver.showRelatives(profile);
					if (!relatives.isEmpty()) {
						for (Profile relative : relatives) {
							Boolean isChild = relative instanceof Child;
							System.out.println((isChild ? "Child: " : "Parent: ") + relative.getname() + " "
									+ relative.getsurname());
						}
					} else {
						System.out.println(firstname1 + " doesn't have any relatives in the network");
					}
				} else {
					System.out.println("Profile " + firstname1 + " doesn't exist");
				}
				break;
			
			
			case 8:///// exiting the menu
				System.out.println("You have exited the program\r\n");
				return false;
			default:
				System.out
						.println("You have entered an invalid selection, please choose from the following options\r\n");
				break;
			}

		} catch (IOException ioe) {
			System.out.println("IO error trying to read your input!\r\n");
			System.exit(1);
		}
		return true;
	}
}
