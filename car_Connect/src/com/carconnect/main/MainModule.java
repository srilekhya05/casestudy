package com.carconnect.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.carconnect.Service.*;

import com.carconnect.dao.*;
import com.carconnect.entity.*;


public class MainModule {
	Scanner scm = new Scanner(System.in);

	public static void main(String[] args) {
		int choice;
		MainModule mn = new MainModule();
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1. Admin");
			System.out.println("2. Customer");
			System.out.println("3. Reservation");
			System.out.println("4. Vechile");
			System.out.println("0. Exit");
			System.out.println("Plese Enter your choice");

			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				System.out.println("You selected Admin");
				mn.adminview();
				// Code for case 1
				break;
			case 2:
				System.out.println("You selected Customer");
				mn.customerview();
				break;
			case 3:
				System.out.println("You selected ReserVation");
				mn.reserVationview();
				break;
			case 4:
				System.out.println("You selected Vechile");
				mn.vehicleview();
				break;
			case 0:
				System.out.println("You Exited CarConnect");
				// Code for case 5
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				// Code for default case
				break;
			}
		} while (choice != 0);
		sc.close();
	}
   
	// Vehicle entity
	void vehicleview() {
		int innerChoice=-1;
		IVehicleService iv=new VehicleServiceImpl();
		while (innerChoice != 0) {
			System.out.println("1. Update Vehicle");
			System.out.println("2. Register Vehicle");
			System.out.println("3. Delete Vehicle");
			System.out.println("4. Get Vehicle By ID");
			System.out.println("5. Get Vehicle By Make");
			System.out.println("0. Exit");
			System.out.println("Please Enter your choice");

			innerChoice = scm.nextInt();
			

			switch (innerChoice) {
			case 1:
				System.out.println("You have chosen Update Vehicle");
				int success = 0;

				String newModel = null;
				String newMake = null;
				int newYear = 0;
				String newColor = null;
				String newRegNo = null;
				boolean newAvailability = false;
				double newDailyRate = 0;

				System.out.println("Enter Vehicle ID to update:");
				int vehicleId = scm.nextInt();
				scm.nextLine();

				System.out.println("Update model: ");
				newModel = scm.nextLine();

				System.out.println("Update make: ");
				newMake = scm.nextLine();

				System.out.println("Update year: ");
				newYear = scm.nextInt();
				scm.nextLine();

				System.out.println("Update color: ");
				newColor = scm.nextLine();

				System.out.println("Update registration number:");
				newRegNo = scm.nextLine();

				System.out.println("Update Availability(True/False):");
				newAvailability = scm.nextBoolean();
				scm.nextLine();

				System.out.println("Update daily rate:");
				newDailyRate = scm.nextDouble();
				scm.nextLine();

				System.out.println("Exiting update.");

				Vehicle vehicle = new Vehicle(newModel, newMake, newYear, newColor, newRegNo, newAvailability, newDailyRate);
				vehicle.setVehicleID(vehicleId);

				success = iv.updateVehicle(vehicle);

				if(success == 1 ) {
					System.out.println("Record updated successfully...");
				} else {
					System.out.println("Record was NOT updated...");
				}
				break;
			case 2:
				System.out.println("You have chosen Register Vehicle");
				int success2 = 0;


				System.out.println("Enter model name: ");
				String model = scm.nextLine();

				System.out.println("Enter make: ");
				String make = scm.nextLine();

				System.out.println("Enter year: ");
				int year = scm.nextInt();
				scm.nextLine();

				System.out.println("Enter color: ");
				String color = scm.nextLine();

				System.out.println("Enter registration number:");
				String registrationNumber = scm.nextLine();

				System.out.println("Enter Availability(True/False):");
				boolean availability = scm.nextBoolean();

				System.out.println("Enter daily rate:");
				int dailyRate = scm.nextInt();
				scm.nextLine();

				Vehicle vehicle1 = new Vehicle(model, make, year, color,registrationNumber, availability, dailyRate);
				success2 = iv.registerVehicle(vehicle1);

				if(success2 == 1 ) {
					System.out.println("Record inserted successfully...");
				}
				break;

			case 3: 
				System.out.println("You have chosen Delete Vehicle");
				int success1 = 0;

				
				System.out.println("Enter vehicle ID: ");
				int vehicleId1 = scm.nextInt();
				scm.nextLine();

				success1 = iv.deleteVehicle(vehicleId1);

				if(success1 == 0) {
					System.out.println("No such customer");
				}else {
					System.out.println("Record is deleted successfully...");
				}
				break; 

			case 4: 
				System.out.println("You have chosen Get Vehicle By ID");
				System.out.print("Enter vehicle ID: ");
				int vehicleId11 = scm.nextInt();
				scm.nextLine();

				Vehicle vehicle11 = iv.getVehicleById(vehicleId11);

				if(vehicle11 == null) {
					System.out.println("No such vehicle");
				}else {
					System.out.println("The given vehicle: ");
					System.out.println(vehicle11);
				}
				break;

			case 5:
				System.out.println("You have chosen Get Vehicle By Make");
				System.out.print("Enter Vehicle Make to retrieve:");
				String vehicleMake = scm.nextLine();

				Vehicle vehicle2 = iv.getVehicleByMake(vehicleMake);

				if(vehicle2 == null) {
					System.out.println("No such vehicle");
				}else {
					System.out.println("The given vehicle: ");
					System.out.println(vehicle2);
				}
				break;

			case 0:
				System.out.println("Exiting Vechile");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
			
		}
	}
    
	
	//Reservation Entity
	
	Date parseDate(String dateStr) throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.parse(dateStr);
    }
	void reserVationview() {
		 int innerchoice;
		 ReservationServicedao reservationDao = new ReservationServicedao();
			ReservationServiceImpl reservationService = new ReservationServiceImpl(reservationDao);
	        do {
	            System.out.println("\n*** Reservation System Menu ***");
	            System.out.println("1. View Reservation by ID");
	            System.out.println("2. View Reservations by Customer ID");
	            System.out.println("3. Create Reservation");
	            System.out.println("4. Update Reservation");
	            System.out.println("5. Cancel Reservation");
	            System.out.println("0. Exit");
	            System.out.print("Enter your innerchoice: ");
	            
	            
	            innerchoice = scm.nextInt();
	            scm.nextLine();  // Consume the newline character

	            
	            switch (innerchoice) {
	                case 1:
	                	System.out.print("Enter Reservation ID: ");
	                    int reservationId = scm.nextInt();
	                    try {
	                        Reservation reservation = reservationService.getReservationById(reservationId);
	                        if (reservation != null) {
	                            System.out.println("Reservation Details:");
	                            System.out.println(reservation.toString());
	                        } else {
	                            System.out.println("Reservation not found with ID: " + reservationId);
	                        }
	                    } catch (Exception e) {
	                        System.err.println("Error: " + e.getMessage());
	                    }
	                    
	                    break;
	                case 2:
	                	System.out.print("Enter Customer ID: ");
	                    int customerId = scm.nextInt();
	                    try {
	                        List<Reservation> reservations = reservationService.getReservationsByCustomerId(customerId);
	                        if (!reservations.isEmpty()) {
	                            System.out.println("Reservations for Customer ID " + customerId + ":");
	                            for (Reservation reservation : reservations) {
	                                System.out.println(reservation.toString());
	                            }
	                        } else {
	                            System.out.println("No reservations found for Customer ID " + customerId);
	                        }
	                    } catch (Exception e) {
	                        System.err.println("Error: " + e.getMessage());
	                    }
	                    break;
	                case 3:
	                	try {
	                	System.out.println("Enter Reservation Details:");
	                	
	                	System.out.print("Reservation ID: ");
	                    int reservationId1 = scm.nextInt();


	                    System.out.print("Customer ID: ");
	                    int customerId1 = scm.nextInt();

	                    System.out.print("Vehicle ID: ");
	                    int vehicleId = scm.nextInt();
	                    scm.nextLine();
	                    System.out.print("Start Date (yyyy-MM-dd HH:mm:ss): ");
	                    String startDateStr = scm.nextLine();
	                    Date startDate = parseDate(startDateStr);  

	                    System.out.print("End Date (yyyy-MM-dd HH:mm:ss): ");
	                    String endDateStr = scm.nextLine();
	                    Date endDate = parseDate(endDateStr); 
	                    
	                    System.out.print("Status (e.g., Pending): ");
	                    String status = scm.next();

	                    System.out.print("Total Cost: ");
	                    double totalCost = scm.nextDouble();

	                    
	                    Reservation newReservation = new Reservation();
	                    newReservation.setReservationID(reservationId1);
	                    newReservation.setCustomerID(customerId1);
	                    newReservation.setVehicleID(vehicleId);
	                    newReservation.setStartDate(startDate);
	                    newReservation.setEndDate(endDate);
	                    newReservation.setStatus(status);
	                    newReservation.setTotalCost(totalCost);

	                   
	                    reservationService.createReservation(newReservation);
	                    System.out.println("Reservation created successfully!");
	                } catch (Exception e) {
	                    System.err.println("Error: " + e.getMessage());
	                }
	                    break;
	                case 4:
	                	 try {
	                         System.out.print("Enter Reservation ID to update: ");
	                         int reservationId1 = scm.nextInt();

	                         
	                         Reservation existingReservation = reservationService.getReservationById(reservationId1);
	                         if (existingReservation == null) {
	                             System.out.println("Reservation not found with ID: " + reservationId1);
	                             return;
	                         }

	                         System.out.println("Enter updated details:");
	                         scm.nextLine();
	                         System.out.print("Start Date (yyyy-MM-dd HH:mm:ss): ");
	                         String startDateStr = scm.nextLine();
	                         Date updatedStartDate = parseDate(startDateStr);  
	                         System.out.print("End Date (yyyy-MM-dd HH:mm:ss): ");
	                         String endDateStr = scm.nextLine();
	                         Date updatedEndDate = parseDate(endDateStr); 
	                         
	                         System.out.print("Customer ID: ");
	                         int customerId2 = scm.nextInt();

	                         System.out.print("Vehicle ID: ");
	                         int vehicleId2 = scm.nextInt();
	                         
	                         System.out.print("Status (e.g., Pending): ");
	                         String status2 = scm.next();

	                         System.out.print("Total Cost: ");
	                         double totalCost2 = scm.nextDouble();
	                        
	                         Reservation updatedReservation = new Reservation();
	                         updatedReservation.setReservationID(reservationId1);
	                         updatedReservation.setStartDate(updatedStartDate);
	                         updatedReservation.setEndDate(updatedEndDate);
	                         updatedReservation.setCustomerID(customerId2);
	                         updatedReservation.setVehicleID(vehicleId2);
	                         updatedReservation.setStatus(status2);
	                         updatedReservation.setTotalCost(totalCost2);

	                         
	                         reservationService.updateReservation(updatedReservation);
	                         System.out.println("Reservation updated successfully!");
	                     } catch (Exception e) {
	                         System.err.println("Error: " + e.getMessage());
	                     }
	                    break;
	                case 5:
	                	try {
	                        System.out.print("Enter Reservation ID to cancel: ");
	                        int reservationId1 = scm.nextInt();

	                        
	                        Reservation existingReservation = reservationService.getReservationById(reservationId1);
	                        if (existingReservation == null) {
	                            System.out.println("Reservation not found with ID: " + reservationId1);
	                            return;
	                        }
	                        if (!"Pending".equals(existingReservation.getStatus())) {
	                            System.out.println("Reservation is not cancellable.");
	                            return;
	                        }

	                       
	                        try {
	                            reservationService.cancelReservation(reservationId1);
	                            System.out.println("Reservation cancelled successfully!");
	                        } catch (Exception e) {
	                            System.err.println("Error: " + e.getMessage());
	                        }
	                    } catch (Exception e) {
	                        System.err.println("Error: " + e.getMessage());
	                    }
	                    break;
	                case 0:
	                    System.out.println("Exiting the application. Goodbye!");
	                    break;
	                default:
	                    System.out.println("Invalid innerchoice. Please enter a valid option.");
	            }
	        } while (innerchoice != 0);

	}

	//Customer Entity 
	
	void customerview() {
	ICustomerService customerService = new CustomerServiceImpl();
		
		Customer customer = null;
		
		String firstName = null;
		String lastName = null;
		String email = null;
		String phoneNumber = null;
		String address = null;
		String username = null;
		String password = null;
		String registrationDate = null;
		
		
		int year = 0 ;
		int month = 0 ;
		int dayOfMonth = 0;
		
		LocalDate startDate = null;
		LocalDate endDate = null;
		
		int success = 0;
		
		int customerID = 0;
		int innerChoice = -1;
		
		while (innerChoice != 0) {
			System.out.println("Following are the options:");
			System.out.println("1. Insert Customer");
			System.out.println("2. Update Customer");
			System.out.println("3. Delete Customer");
			System.out.println("4. View customer by ID");
			System.out.println("5. View all customers");
			System.out.println("0. Exit");
			System.out.print("Please enter your choice: ");

			innerChoice = scm.nextInt();
			scm.nextLine();
			
			switch (innerChoice) {
			case 1:
				System.out.print("Enter first name: ");
				firstName = scm.nextLine();
				
				System.out.print("Enter last name: ");
				lastName = scm.nextLine();
				
				System.out.print("Enter email: ");
				email = scm.nextLine();
				
				System.out.print("Enter phone number: ");
				phoneNumber = scm.nextLine();
				
				customer = new Customer(firstName, lastName, email, phoneNumber, address, username, password, registrationDate);
				
				success = customerService.addCustomer(customer);
				
				if(success == 1 ) {
					System.out.println("Record inserted successfully...");
				}
				break;
			case 3:
				System.out.print("Enter customer ID: ");
				customerID = scm.nextInt();
				scm.nextLine();
				
				success = customerService.deleteCustomer(customerID);
				
				if(success == 0) {
					System.out.println("No such customer");
				}else {
					System.out.println("Record is deleted successfully...");
				}
				break;
			case 4:
				System.out.print("Enter customer ID: ");
				customerID = scm.nextInt();
				scm.nextLine();
				
				customer = customerService.viewCustomer(customerID);
				
				if(customer == null) {
					System.out.println("No such customer");
				}else {
					System.out.println("The given customer: ");
					System.out.println(customer.toString());
				}
				break;
			case 5:
				List<Customer>customerList = customerService.viewCustomers();
				
				if(customerList == null) {
					System.out.println("No customers in table.");
				}else {
					System.out.println("Following are the customers:");
					
					for (Customer customer2 : customerList) {
						System.out.println(customer2.toString());
					}	
				}
				
				break;
			default:
				System.out.println("Incorrect option");
				break;
			}
		}

		
	}

	// Admin Entity 
	void adminview() {
		int innerChoice;
		IAdminService adsrvc = new AdminServiceImpl();
		do {
			System.out.println("1. Update Admin");
			System.out.println("2. Register Admin");
			System.out.println("3. Delete Admin");
			System.out.println("4. Get Admin By Id");
			System.out.println("5. Get Admin By User Name");
			System.out.println("0. Exit");
			System.out.println("Plese Enter your choice");

			innerChoice = scm.nextInt();
			scm.nextLine();

			int adminId, year, month, date;
			String firstName, lastName, email, phoneNumber, userName, password, role;
			LocalDate joindate;

			switch (innerChoice) {
			case 1:
				System.out.println("You selected Update Admin");
				System.out.println("Enter the adminId");
				int adminId1 = scm.nextInt();
				scm.nextLine();
				System.out.println("Enter the Password");
				String password1 = scm.nextLine();

				System.out.print("Enter the firstName : ");
				String firstName1 = scm.nextLine();//
				// Taking the firstName of the admin

				System.out.print("Enter the LastName : ");
				String lastName1 = scm.nextLine();// Taking
				// the LastName of the admin

				System.out.print("Enter the email : ");
				String email1 = scm.nextLine();// Taking the
				// Email of the admin

				System.out.print("Enter the Phone Number : ");
				String phoneNumber1 = scm.nextLine();//
				/* Taking the phone number of the admin */

				System.out.print("Enter the userName : ");
				String userName1 = scm.nextLine();// Taking
				// the userName of the admin

				System.out.print("Enter the Role : ");
				String role1 = scm.nextLine();

				System.out.print("Enter the Joining Date : ");
				System.out.println("Enter the year ");
				int year1 = scm.nextInt();
				scm.nextLine();
				System.out.println("Enter the month ");
				int month1 = scm.nextInt();
				scm.nextLine();
				System.out.println("Enter the date ");
				int date1 = scm.nextInt();
				scm.nextLine();

				LocalDate joindate1 = LocalDate.of(year1, month1, date1);

				Admin admin1 = new Admin(adminId1,firstName1, lastName1, email1, phoneNumber1, userName1, password1, role1, joindate1);
				adsrvc.updateAdmin(admin1);
				break;
			case 2:
				System.out.println("You selected Register Admin");
				System.out.println("Enter the Password");
				password1 = scm.nextLine();

				System.out.print("Enter the firstName : ");
				firstName1 = scm.nextLine();//
				// Taking the firstName of the admin

				System.out.print("Enter the LastName : ");
				lastName1 = scm.nextLine();// Taking
				// the LastName of the admin

				System.out.print("Enter the email : ");
				email1 = scm.nextLine();// Taking the
				// Email of the admin

				System.out.print("Enter the Phone Number : ");
				phoneNumber1 = scm.nextLine();//
				/* Taking the phone number of the admin */

				System.out.print("Enter the userName : ");
				userName1 = scm.nextLine();// Taking
				// the userName of the admin

				System.out.print("Enter the Role : ");
				role1 = scm.nextLine();

				System.out.print("Enter the Joining Date : ");
				System.out.println("Enter the year ");
				year1 = scm.nextInt();
				scm.nextLine();
				System.out.println("Enter the month ");
				month1 = scm.nextInt();
				scm.nextLine();
				System.out.println("Enter the date ");
				date1 = scm.nextInt();
				scm.nextLine();

				joindate1 = LocalDate.of(year1, month1, date1);

				Admin admin2 = new Admin(firstName1, lastName1, email1, phoneNumber1, userName1, password1, role1, joindate1);
				adsrvc.registerAdmin(admin2);
				break;
			case 3:
				System.out.println("You selected Delete Admin");
				System.out.print("Enter the Id of admin you want to delete: ");
				adminId1 = scm.nextInt();
				scm.nextLine();
				System.out.print("Enter the password of given admin id admin");
				String temppassword = scm.nextLine();
				adsrvc.deleteAdmin(adminId1, temppassword);
				break;
			case 4:
				System.out.println("You selected Get Admin By Id");
				System.out.print("Enter the Id to search for admin: ");
				int id = scm.nextInt();
				scm.nextLine();
				adsrvc.getAdminById(id);
				break;
			case 5:
				System.out.println("You selected Get Admin By User Name");
				System.out.print("Enter the username to search for ");
				String username = scm.nextLine();
				adsrvc.getAdminByUsername(username);
				break;
			case 0:
				System.out.println("You Exited from Admin");

				break;
			default:
				System.out.println("Invalid choice. Please try again.");

				break;
			}
		} while (innerChoice != 0);
	}
}
