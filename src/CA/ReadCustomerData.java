package CA;

import java.io.File;
import java.util.*;

public class ReadCustomerData {

    public static void main(String[] args) throws Exception {
        //parsing and reading the CSV file data into the customer (object) array
        // provide the path here...
        File directory = new File("./");
        String region = directory.getAbsolutePath() + "//customers.csv";
        Scanner sc = new Scanner(new File(region));
        Customer myCustomer = new Customer();
        Customer[] customers = new Customer[10000];

        // this will just print the header in CSV file
        System.out.println(sc.nextLine());

        int i = 0;
        String st = "";

        while (sc.hasNextLine()) //returns a boolean value
        {
            st = sc.nextLine();
            String[] data = st.split(",");
            customers[i++] = new Customer(Integer.parseInt(data[0]), data[1], data[2], data[3], Integer.parseInt(data[4]), Float.parseFloat(data[5]));
        }
        sc.close();  //closes the scanner

        /**
         * *********************************************************************************
         */
        //Part1
        /*
        *the process of printing arrays has been commented out for more convienient review
        *please uncomment if needed to check the array 
         */
        //Print the array before sorting - uncommnet to print
        /*
        System.out.println("Customers before sorting:");
        for (Customer element : customers) {
            System.out.println(element);
        }
        System.out.println();
         */
        //1.1 bubbleSort
        myCustomer.bubbleSort(customers);

        //1.2 quickSort
        myCustomer.quickSort(customers, 0, 9999);

        //print the array after sort - uncomment to print
        /*
        System.out.println("Customers after sorting by region (ascending) and ID (ascending) if the region is the same:");
        for (Customer element : customers) {
            System.out.println(element);
        }
        System.out.println();
         */
        //1.3 analyse time comlexity of bubble and quick sorts
        //taking the average elapsed time for 10, 100, 1000, 5000 and 10000 records
        myCustomer.analyseTimeComlexity(customers);

        //1.4 binary search (region) - input enter by user
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter the region of the customer(s) to be searched for: ");
        String targetRegion = myScanner.nextLine();
        myCustomer.binarySearch(customers, targetRegion);

        //Part2
        /*
        * Please comment out part1 to better review part2
        * Because after part1, data has sorted by region 
        * And the last record is not the one has greatest custID anymore
        * So, adding a new consecutive id number to the end may cause repeated custID
        * Part2 has an independent scanner, so comment out part1 doesn't affect part2
         */
        //2.1 accept a new record and add it to the end with a new consecutive id number  
        //2.2 exceptions
        try {
            //accept a new record
            Scanner myScanner2 = new Scanner(System.in);
            System.out.println("Please enter the new customer information:");
            System.out.print("Region: ");
            String newRegion = myScanner2.nextLine();
            System.out.print("Country: ");
            String newCountry = myScanner2.nextLine();
            System.out.print("Item: ");
            String newItem = myScanner2.nextLine();
            System.out.print("Sold: ");
            int newSold = Integer.parseInt(myScanner2.nextLine());
            System.out.print("Price: ");
            float newPrice = Float.parseFloat(myScanner2.nextLine());

            //create a new object
            Customer newCustomer = new Customer(newRegion, newCountry, newItem, newSold, newPrice);

            //validate the new customer
            Customer.validateCustomerData(newCustomer);

            //add the new customer to the array
            customers = newCustomer.addRecord(customers, newCustomer);

            //print new customer infomation
            System.out.println("New customer has been added");
            System.out.println(newCustomer);

            //print the new customers array - uncomment to print
            /*
            System.out.println("Customers after adding the new record:");
            for (Customer element : customers) {
                System.out.println(element);
            }
             */
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. Please enter digits only for sold and price.");
        } catch (CustomerRecordException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
