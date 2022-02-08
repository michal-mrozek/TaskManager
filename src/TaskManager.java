import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    public static String NAZWA_PLIKU = "tasks.csv";
    public static String[][] tabZadan;
    public static String[] opcje = {"List", "Add", "Remove", "Exit"};

    public static void wydrukOpcji(String[] arr) {
        for (String opcja : arr) {
            System.out.println(ConsoleColors.WHITE + opcja);
        }
    }

    public static int iloscWierszy(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scan = new Scanner(file);

        int lines = 0;
        while (scan.hasNextLine()) {
            scan.nextLine();
            lines++;
        }
        scan.close();
        return lines;
    }

    public static String[][] pobieranie(String fileName) throws FileNotFoundException {
        int ilosc = iloscWierszy(NAZWA_PLIKU);
        String[][] zPliku = new String[ilosc][3];
        String[] temp = new String[ilosc];
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        for (int i = 0; i < ilosc; i++) {
            temp[i] = (scan.nextLine());
            String[] split = temp[i].split(",");
            for (int j = 0; j < temp[0].split(",").length; j++) {
                zPliku[i][j] = split[j];
            }
        }
        return zPliku;
    }

    public static void wydrukListy(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();

        }
    }

    public static String[][] dodaj() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj tresc");
        String tresc = (scan.nextLine()).toLowerCase();
        System.out.println("podaj date");
        String data = (scan.nextLine()).toLowerCase();
        System.out.println("czy wazne?");
        String wazne = (scan.nextLine());
        tabZadan = Arrays.copyOf(tabZadan, tabZadan.length + 1);
        tabZadan[tabZadan.length - 1] = new String[3];
        tabZadan[tabZadan.length - 1][0] = tresc;
        tabZadan[tabZadan.length - 1][1] = data;
        tabZadan[tabZadan.length - 1][2] = wazne;
        return tabZadan;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ConsoleColors color = new ConsoleColors();
        pobieranie(NAZWA_PLIKU);
        tabZadan = pobieranie(NAZWA_PLIKU);


        System.out.println(color.BLUE + "Wybierz opcje:");
        wydrukOpcji(opcje);

        Scanner scan = new Scanner(System.in);


        while (scan.hasNextLine()) {

            String zKonsoli = (scan.nextLine()).toLowerCase();
            switch (zKonsoli) {
                case "list":
                    wydrukListy(tabZadan);
                    break;
                case "add":
                    dodaj();
                    break;
                case "remove":
                    break;
                case "exit":
                    scan.close();
                default:
                    System.out.println("Wybierz poprawna opcje");
            }
            System.out.println(color.BLUE + "Wybierz opcje:");
            wydrukOpcji(opcje);

        }
    }
}
