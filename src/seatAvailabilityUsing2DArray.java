import java.util.*;

public class seatAvailabilityUsing2DArray {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        int rows = 5;
        int columns  = 6;

        //using HashMap
        HashMap<Integer, ArrayList<Integer>> myPattern = new HashMap<>();

        myPattern.put(1, new ArrayList<Integer>(Arrays.asList(30,30,30,30,30,30)));
        myPattern.put(2, new ArrayList<Integer>(Arrays.asList(10,10,10,10,10,10)));
        myPattern.put(3, new ArrayList<Integer>(Arrays.asList(10,10,10,10,10,10)));
        myPattern.put(4, new ArrayList<Integer>(Arrays.asList(10,10,20,20,20,20)));
        myPattern.put(5, new ArrayList<Integer>(Arrays.asList(10,10,20,20,20,20)));

        int[][] myArray;
        int nRow = 0;
        int nColumn = 0;
        int[][] originalArray = createPattern(myPattern, rows, columns);
        displayPattern(originalArray);
        myArray = createPattern(myPattern, rows, columns);
        char choice = ' ';
        int[][] confirmedSeats = myArray;

        do {
            int[][] myNewArray;
            myNewArray = myArray;
            int conditionFlag = 0;
            int[][] updatedSeats = bookTickets(myNewArray,nRow, nColumn, conditionFlag );
            do{
                System.out.println("Enter choice\n" +
                        "S : displaySeatInfo\n" +
                        "T : seatBooking\n" +
                        "E : editSeatNumber\n" +
                        "X : Exit\n");
                choice = sc.next().charAt(0);

                switch(choice)
                {
                    case 'T' :
                        conditionFlag = 1;
                        boolean ex = false;
                            nRow = rowInput() - 1;
                            nColumn = colInput() - 1;
                            ex = bookTickets(myArray, nRow, nColumn);
                            System.out.println(ex);
                            myArray = updatedSeats;
                            confirmedSeats = bookTickets(myArray,nRow, nColumn, conditionFlag );
                        break;
                    case 'S' :
                        displayPattern(confirmedSeats);
                        break;
                    case 'E' :
                        conditionFlag = conditionFlag;
                        if(conditionFlag < 1)
                        {
                            System.out.println("No ticket history found !");
                        }
                        else
                        {
                            nRow = rowInput() - 1;
                            nColumn = colInput() - 1;
                            confirmedSeats = cancelTickets(confirmedSeats, nRow, nColumn, conditionFlag, originalArray);
                        }
                    case 'X' :
                        break;
                    default:
                        System.out.println("Invalid Selection. Please Try Again !");
                }
            }while(choice != 'X');
        }while(choice != 'X');
    }

    public static int[][] cancelTickets(int[][] confirmedSeats, int nRow, int nColumn, int flag, int[][] originalArray)
    {
        int[][] myNewArray;
        myNewArray = confirmedSeats;
        boolean checkLocation = false;
        if(flag == 0)
        {
            myNewArray = originalArray;
        }
        else
        {
                int tempData = confirmedSeats[nRow][nColumn];
                if(tempData == 0)
                {
                    myNewArray[nRow][nColumn] = originalArray[nRow][nColumn];
                    myNewArray = confirmedSeats;
                }
                else
                {
                    System.out.println("Invalid Seat Selection");
                }
        }
        return myNewArray;
    }


    public static int[][] bookTickets(int[][] myArray, int nRow, int nColumn, int flag)
    {
        int[][] myNewArray;
        myNewArray = myArray;
        boolean checkLocation = false;
        if(flag == 0)
        {
            myNewArray = myArray;
        }
        else {
                checkLocation = false;
                int tempData = myNewArray[nRow][nColumn];

                if(tempData == 30 || tempData == 20 || tempData == 10)
                {
                    myNewArray[nRow][nColumn] = 0;
                }
                else {
                    checkLocation = true;
                }
            }

        return myNewArray;
    }

    public static boolean bookTickets(int[][] myArray, int nRow, int nColumn)
    {
        int[][] myNewArray;
        myNewArray = myArray;
        boolean checkLocation = false;
        int tempData = myNewArray[nRow][nColumn];
        if(tempData == 30 || tempData == 20 || tempData == 10)
        {
                    checkLocation = false;
        }
        else
        {
            System.out.println("The seat is already booked");
            checkLocation = true;
        }
        return checkLocation;
    }
    public static int rowInput()
    {
        int nRow ;
        do{
            System.out.println("Enter the row :");
            nRow = sc.nextInt();
            if(nRow > 5 || nRow < 1)
            {
                System.out.println(" Please enter the value between 1 to 5");
            }
        }while(nRow > 5 || nRow < 1);
        return nRow;
    }

    public static int colInput()
    {
        int nColumn = 1;
        do{
            System.out.println("Enter the Column :");
            nColumn = sc.nextInt();

            if(nColumn > 6 || nColumn < 1)
            {
                System.out.println(" Please enter the value between 1 to 6");
            }
        }while(nColumn > 6 || nColumn < 1);
        return nColumn;
    }
    public static int[][] createPattern(HashMap<Integer, ArrayList<Integer>> map, int rows, int columns)
    {
        HashMap<Integer, ArrayList<Integer>> myPattern = map;
        int i = 1;
        int[][] myArray = new int[rows][columns];

        ArrayList<Integer> listOfLists;
        Integer listOfIntArrays = null;

        int count = 1;
        int j = 0;

        do
        {
            listOfLists = myPattern.get(i);
            for(int mm = 0 ; mm < listOfLists.size() ; mm++)
            {
                listOfIntArrays = listOfLists.get(mm);
                myArray[j][mm] = listOfIntArrays;
            }
            j++;
            i++;
        }while( j < 5);
        return myArray;
    }

    public static void displayPattern(int[][] myArray)
    {
        for(int j = 0; j < 5 ; j++)
        {
            for(int mm = 0 ; mm < 6 ; mm++)
            {
                System.out.print(myArray[j][mm]+"\t");
            }
            System.out.println();
        }
    }
}

