package CA;

import java.util.Arrays;

class Customer implements Comparable<Object> {

    private int custID;
    private String region;
    private String country;
    private String item;
    private int sold;
    private float price;

    // constructor
    public Customer(int custID, String region, String country, String item, int sold, float price) {
        super();
        this.custID = custID;
        this.region = region;
        this.country = country;
        this.item = item;
        this.sold = sold;
        this.price = price;
    }

    public Customer(String region, String country, String item, int sold, float price) {
        this.region = region;
        this.country = country;
        this.item = item;
        this.sold = sold;
        this.price = price;
    }

    public Customer() {
    }

    // setters and getters
    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    // so the Customer objects can be compared when sorting/searching
    // NOTE: this will only allow comparisons based on the title of the film
    @Override
    public int compareTo(Object obj) {

        /*
		Edit this section so it compares the appropriate
		column you wish to sort by
         */
        Customer cst = (Customer) obj;
        return custID - (cst.getCustID());
    }

    @Override
    public String toString() {
        return "Customer [custID = " + custID + ", region = " + region + ", country = " + country + ", item = "
                + item + ", sold = " + sold + ", price = " + price + "]";
    }

    //*********************************************************************************
    //Part 1
    //1.1 bubble sort using column 2 (region) - in ascending order
    public static void bubbleSort(Customer[] array) {
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {//traverse from index 0 - 9998 (n = 10000)
            swapped = false;//initialize the swapped to false

            //swap the data if necessary
            for (int j = 0; j < n - i - 1; j++) {
                //if the previous data less than the latter one in region column
                //or the data in region column is the same, and the previous custID is greater than the latter one
                int compareReigion = array[j].getRegion().compareTo((array[j + 1]).getRegion());
                boolean compareID = array[j].getCustID() > ((array[j + 1]).getCustID());//the previous number is greater than the latter one

                if (compareReigion > 0 || (compareReigion == 0 && compareID)) {
                    // Swap array[j] and array[j+1]
                    Customer temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }

            }

            // if there's no elements swapped, the array is sorted
            if (!swapped) {
                break;
            }
        }
    }

    //1.2 quick sort using column 2 (region) - in ascending order
    public static int partition(Customer[] array, int start, int end) {
        Customer temp;
        Customer pivot = array[end];
        int partitionIndex = start;

        //loop that traverse the array
        for (int i = start; i < end; i++) {
            //compare current region with pivot region, and save it in an int value
            int compareReigion = array[i].getRegion().compareTo(pivot.getRegion());
            //current custID is less than pivot custID
            boolean compareID = array[i].getCustID() <= pivot.getCustID();

            //if current region is less than pivot region, or the region is the same and current custID is less than pivot custID
            if (compareReigion < 0 || (compareReigion == 0 && compareID)) {
                // swap elements
                temp = array[i];
                array[i] = array[partitionIndex];
                array[partitionIndex] = temp;

                partitionIndex++;
            }
        }
        // finally swap pivot with element at the partition index
        temp = array[partitionIndex];
        array[partitionIndex] = array[end];
        array[end] = temp;
        return partitionIndex;
    }

    public static void quickSort(Customer[] array, int start, int end) {
        if (start < end) {
            int partitionIndex = partition(array, start, end);
            quickSort(array, start, partitionIndex - 1); //sort the left side of the partitionIndex
            quickSort(array, partitionIndex + 1, end); //sort the right side of the partitionIndex
        }
    }

    //1.3 analyse the time complexity of sorting algorithms above
    //taking the average elapsed time for 10, 100, 1000, 5000 and 10000 records
    public static void analyseTimeComlexity(Customer[] array) {
        int[] sizes = {10, 100, 1000, 5000, 10000};//different data sizes

        for (int size : sizes) {
            //copy data for different sizes of arrays
            Customer[] subArray1 = Arrays.copyOf(array, size);
            Customer[] subArray2 = Arrays.copyOf(array, size);

            //print the subArrays - uncomment to print the subArrays
            /*
            System.out.println("Original arrays (Size: " + size + "):");
            System.out.println("subArray1");
            for (Customer customer : subArray1) {
                System.out.println(customer);
            }
            System.out.println("subArray2");
            for (Customer customer : subArray2) {
                System.out.println(customer);
            }
             */
            //calculate average elapsedTime for algorithms
            long startTime, endTime, elapsedTime, averageElapsedTime;

            //bubble sort
            startTime = System.nanoTime();
            bubbleSort(subArray1);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            averageElapsedTime = elapsedTime / size;

            //print the array, elapsed time and average elapsed time
            //uncomment to print the sorted subArrays
            /*
            System.out.println("Bubble Sort - Sorted array (Size: " + size + "): ");
            for (Customer customer : subArray1) {
                System.out.println(customer);
            }
             */
            System.out.println("Bubble Sort - Record Size: " + size);
            System.out.println("Bubble Sort - Elapsed Time: " + elapsedTime + " nanoseconds");
            System.out.println("Bubble Sort - Average Elapsed Time: " + averageElapsedTime + " nanoseconds");

            //quick sort
            startTime = System.nanoTime();
            bubbleSort(subArray2);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            averageElapsedTime = elapsedTime / size;

            //print the array, elapsed time and average elapsed time
            //uncomment to print the sorted subArrays
            /*
            System.out.println("Quick Sort - Sorted array (Size: " + size + "): ");
            for (Customer customer : subArray1) {
                System.out.println(customer);
            }
             */
            System.out.println("Quick Sort - Record Size: " + size);
            System.out.println("Quick Sort - Elapsed Time: " + elapsedTime + " nanoseconds");
            System.out.println("Quick Sort - Average Elapsed Time: " + averageElapsedTime + " nanoseconds");

        }

    }

    //1.4 binary search using column 2 (region)
    public static void binarySearch(Customer[] array, String targetRegion) {
        //initialize pointers and variables
        int left = 0;
        int right = array.length - 1;

        while (left <= right) { //the range is not empty
            int middle = (left + right) / 2;
            int compareReigion = targetRegion.compareTo(array[middle].getRegion());

            if (compareReigion < 0) { //the target is in the left side of the array
                right = middle - 1;
            } else if (compareReigion > 0) { //the target is in the right side of the array
                left = middle + 1;
            } else { //the target is found

                //Search for all matches
                //find the left side boundary of the same target region
                int start = middle;
                while (start >= 0 && array[start].getRegion().equals(targetRegion)) {
                    start--;
                }
                start++; //move the start back by 1 when the loop ends, because the loop moves the start 1 left at the end

                //find the right side boundary of the same target region
                int end = middle;
                while (end < array.length && array[end].getRegion().equals(targetRegion)) {
                    end++;
                }
                end--; // Move the end back to the right position

                // Print all matches
                for (int i = start; i <= end; i++) {
                    System.out.println(array[i]);
                }
                return;
            }
        }
        //have not found anything through the loop
        System.out.println("Not an existing region!");
    }

    //Part 2
    //2.1 accept a new record and add it to the end with a new consecutive id number
    public static Customer[] addRecord(Customer[] customers, Customer newRecord) {
        //create a new array with 1 size increased because the size of array cannot be altered
        Customer[] newCustomers = Arrays.copyOf(customers, customers.length + 1);

        //add the new record to the end
        newCustomers[customers.length] = newRecord;

        //update the custID to make it consecutive
        int lastID = customers[customers.length - 1].getCustID();
        int currentID = lastID + 1;
        newRecord.setCustID(currentID);

        return newCustomers;
    }

    //2.2 throw exceptions
    public static void validateCustomerData(Customer customer) throws CustomerRecordException {
        if (customer.getRegion().isEmpty() || customer.getRegion().matches("\\d+")) {
            throw new CustomerRecordException("Invalid region. Region cannot be empty. It cannot have only digits! Please correct this.");
        }

        if (customer.getCountry().isEmpty() || customer.getCountry().matches("\\d+")) {
            throw new CustomerRecordException("Invalid country. Country cannot be empty. It cannot have only digits! Please correct this.");
        }

        if (String.valueOf(customer.getSold()).isEmpty() || !String.valueOf(customer.getSold()).matches("\\d+")) {
            throw new CustomerRecordException("Invalid sold value. Sold value cannot be empty and must be a digit.");
        }

        if (String.valueOf(customer.getPrice()).isEmpty() || !String.valueOf(customer.getPrice()).matches("\\d+(\\.\\d+)?")) {
            throw new CustomerRecordException("Invalid price value. Price value cannot be empty and must be a digit.");
        }
    }

}
