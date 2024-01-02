package Admin;

import Resources.SocketWrapper;
import Resources.restaurant;
import Resources.food;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;


public class Admin {

    static final String INPUT_FILE_NAME = "restaurant.txt";
    static final String INPUT_FILE_NAME2 = "menu.txt";

    static ArrayList<restaurant> restaurants = new ArrayList<restaurant>();
    static ArrayList<food> menus = new ArrayList<food>();

    static ArrayList<String> restaurantNamelist = new ArrayList<String>();
    static void FileLoad() throws IOException {

        restaurantNamelist.add("Empty");

        // Dummy Restaurant add

        ArrayList<String> cat = new ArrayList<String>();
        cat.add("Byriani");

        restaurant res = new restaurant("1" , "Omi" , "5" , "$$$" , "1234" , cat );
        restaurants.add(res);

        // Done

        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));

        while (true) {
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (line == null) break;

            String[] array = line.split(",", -1);

            restaurantNamelist.add(array[1]);

            ArrayList<String> category = new ArrayList<String>();

            for (int i = 5; i < array.length; i++) {
                category.add(array[i]);
            }

            res = new restaurant(array[0], array[1], array[2], array[3], array[4], category);
            restaurants.add(res);

        }
        br.close();

        br = new BufferedReader(new FileReader(INPUT_FILE_NAME2));
        while (true) {
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line == null) break;
            String[] array = line.split(",", -1);


            food fd = new food(array[0], restaurantNamelist.get(Integer.parseInt(array[0])), array[1], array[2], array[3]);
            menus.add(fd);

            restaurants.get(Integer.parseInt(array[0])).addfoods( fd);

        }

        br.close();


    }

    static void showDetailsofRestaurant() {

        for (int i = 1; i < restaurants.size(); i++) {

            System.out.println("Restaurant Id: " + restaurants.get(i).getRestaurantId());
            System.out.println("Restaurant Name: " + restaurants.get(i).getName());
            System.out.println("Restaurant Score: " + restaurants.get(i).getScore());
            System.out.println("Restaurant Price: " + restaurants.get(i).getPrice());
            System.out.println("Restaurant Zip Code: " + restaurants.get(i).getZipcode());
            System.out.println("Restaurant Categories: "  );
            ArrayList<String> Category = restaurants.get(i).getCategory();

            for(int j=0; j<Category.size(); j++) {
                System.out.println("   " + Category.get(j));
            }

            System.out.println();

            System.out.println("Restaurants Food List:- ");

            ArrayList<food> fd = restaurants.get(i).getfoods();

            for(int k=0; k<fd.size(); k++) {
                System.out.println("    Food Category: " + fd.get(k).getFoodcategory());
                System.out.println("    Food Name: " + fd.get(k).getFoodName());
                System.out.println("    Food Price: " + fd.get(k).getFoodPrice());
                System.out.println();
            }

            System.out.println();

        }
    }
    static void addRestaurant() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("You have to provide all information of restaurant in order to add restaurant in restaurant database.");
        System.out.println("Existing Name: ");

        for(int i=1; i<restaurants.size(); i++) {
            System.out.println("    " + restaurants.get(i).getName());
        }
        System.out.println("You cannot add these as restaurant name.Remind!");

        String resName;

        do {

            System.out.print("Enter Restaurant Name: " );
            resName = scanner.nextLine();

            int flag = 0;

            for(int i=1; i<restaurants.size(); i++) {
                if ( Objects.equals(restaurants.get(i).getName(),resName)) {
                    System.out.println(  resName +" already exists.You cannot add.");
                    flag = 1;
                }
            }

            if(flag == 0) {
                break;
            }

        }while (true);

        System.out.print("Enter Restaurant Score: ");
        String resScore = scanner.nextLine();
        System.out.print("Enter Restaurant Price($/$$/$$$): ");
        String resPrice = scanner.nextLine();

        String resZipCode ;

        do {

            System.out.print("Enter Restaurant ZipCode: ");
            resZipCode = scanner.nextLine();

            int flag = 0;

            for(int i=1; i<restaurants.size(); i++) {
                if ( Objects.equals(restaurants.get(i).getZipcode(),resZipCode)) {
                    System.out.println( resZipCode + " already exists.You cannot add.");
                    flag = 1;
                }
            }

            if(flag == 0) {
                break;
            }

        }while (true);

        System.out.println("How many categories you want to add in " + resName + "(1-3): ");
        String categoryLen = scanner.nextLine();
        System.out.print("Enter Unique Category Name successively: ");

        ArrayList<String> categories = new ArrayList<String>();

        for(int i=0; i< Integer.parseInt(categoryLen); i++) {
            String categoryname = scanner.nextLine();
            categories.add(categoryname);
        }

        int lastresId = Integer.parseInt(restaurants.get(restaurants.size() - 1).getRestaurantId());
        lastresId++;
        restaurantNamelist.add(resName);

        restaurant res = new restaurant (Integer.toString(lastresId) , resName,resScore , resPrice, resZipCode, categories );

        restaurants.add(res);

        System.out.println("Restaurant added Successfully!");
        System.out.println();

    }

    static void ServingClient(ArrayList<restaurant> restaurantsInfo , ArrayList<food> menuInfo) {

            ServerSocket serverSocket;
            HashMap<String, SocketWrapper> clientMap;

            clientMap = new HashMap<>();

            try {
                serverSocket = new ServerSocket(33333);
                while (true) {
                    System.out.println("Server is waiting for Client...");
                    Socket clientSocket = serverSocket.accept();
                    serve(clientSocket , clientMap, restaurantsInfo , menuInfo);
                }
            } catch (Exception e) {
                System.out.println("Server starts:" + e);
            }

        }

        static void serve(Socket clientSocket, HashMap<String, SocketWrapper> clientMap, ArrayList<restaurant> restaurantsInfo, ArrayList<food> menuInfo ) throws IOException, ClassNotFoundException {
            SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
            String ClientName = (String) SocketWrapper.read();

            System.out.println(ClientName + " is Connected.");

            clientMap.put(ClientName, socketWrapper);

            restaurant res = null;

            for(int i=0; i<restaurantsInfo.size(); i++) {
                if( restaurantsInfo.get(i).getName().equals(ClientName)) {
                    res = restaurantsInfo.get(i);
                    SocketWrapper.write(res);
                }
            }

            if( res == null) {

                SocketWrapper.write(restaurantsInfo);
                SocketWrapper.write(menuInfo);

                new AdminReadThread( socketWrapper,clientMap);
            }
    }


    public static void main(String[] args) throws IOException {


        while (true) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Do you want to exit now? (YES/NO)");
            String exitStatus = scanner.nextLine();

            if(exitStatus.equalsIgnoreCase("NO")) {


                System.out.println("Enter User Name & Password!!!");
                System.out.println();

                System.out.print("UserName:- ");
                String userName = scanner.nextLine();

                System.out.print("Password:- ");
                String password = scanner.nextLine();

                if (userName.equals("Omi") && password.equals("1")) {
                    System.out.println("Successful Login!!!");

                    FileLoad();

                    ServingClient(restaurants , menus);

                    int Input;
                    do {
                        System.out.println("1. Details of all Restaurants which exist in database.");
                        System.out.println("2. Add Restaurant.");
                        System.out.println("3. Back");

                        String input = scanner.nextLine();

                        boolean isAllDigits = input.matches("\\d+");

                        Input = Integer.parseInt(input);

                        if (isAllDigits) {

                            switch (Input) {

                                case 1:
                                    showDetailsofRestaurant();
                                    break;
                                case 2:
                                    addRestaurant();
                                    break;
                                case 3:
                                    break;
                                default:
                                    System.out.println("Invalid Choice!!!");
                                    break;

                            }

                        }

                        else {
                            System.out.println("Invalid Choice!!!");
                        }

                    } while (Input != 3);


                    System.out.println();

                }

                else {
                    System.out.println("Incorrect UserName & Password!!!");
                }

            }

            else {
                break;
            }


        }

    }
}

