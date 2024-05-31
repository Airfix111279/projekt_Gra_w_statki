import java.io.IOException;
import java.util.Scanner;

public class Gra_W_Statki {

    public static void main(String[] args) throws IOException {
        char[][] statki1 = utworzPlansze();
        char[][] strzaly1 = utworzPlansze();

        char[][] statki2 = utworzPlansze();
        char[][] strzaly2 = utworzPlansze();

        System.out.println("Gracz 1 rozmieszcza statki");
        rozmiescStatki(statki1);

        wyczyscKonsole();

        System.out.println("Gracz 2 rozmieszcza statki");
        rozmiescStatki(statki2);

        while(true) {
            wyczyscKonsole();
            System.out.println("Gracz 1 strzela");
            System.out.println("Naciśnij dowolny klawisz aby kontynuować");
            System.in.read();
            wykonajRuch(statki1, strzaly1, statki2);

            if (sprawdzCzyWygrana(statki2)) {
                System.out.println("Gracz 1 wygrywa!");
                break;
            }

            wyczyscKonsole();
            System.out.println("Gracz 2 strzela");
            System.out.println("Naciśnij dowolny klawisz aby kontynuować");
            System.in.read();
            wykonajRuch(statki2, strzaly2, statki1);

            if (sprawdzCzyWygrana(statki1)) {
                System.out.println("Gracz 2 wygrywa!");
                break;
            }
        }

    }

    private static void wykonajRuch(char[][] statki, char[][] strzaly, char[][] statkiPrzeciwnika) {
        rysujPlansze(statki);
        rysujPlansze(strzaly);

        System.out.println("Wybierz gdzie chcesz strzelić");
        int[] wspolrzedne = wczytajWspolrzedne();

        if(statkiPrzeciwnika[wspolrzedne[1]][wspolrzedne[0]] == 'X' || statkiPrzeciwnika[wspolrzedne[1]][wspolrzedne[0]] == 'D') {
            statki[wspolrzedne[1]][wspolrzedne[0]] = 'X';
            statkiPrzeciwnika[wspolrzedne[1]][wspolrzedne[0]] = 'D';
        } else {
            statki[wspolrzedne[1]][wspolrzedne[0]] = 'O';
            statkiPrzeciwnika[wspolrzedne[1]][wspolrzedne[0]] = 'O';
        }
    }

    private static char[][] utworzPlansze() {
        char[][] rezultat = new char[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10 ; j++) {
                rezultat[i][j] = ' ';
            }
        }
        return rezultat;
    }

    private static void rozmiescStatki(char[][] plansza) {
        rysujPlansze(plansza);

        while(true) {
            System.out.println("Umieść czteromasztowiec");
            System.out.println("Podaj współrzędne początku");
            int[] wspolrzedneStart = wczytajWspolrzedne();
            System.out.println("Podaj współrzędne końca");
            int[] wspolrzedneKoniec = wczytajWspolrzedne();
            if (sprawdzIUmiescStatekNaPlanszy(4, wspolrzedneStart, wspolrzedneKoniec, plansza))
                break;
        }

        rysujPlansze(plansza);

        for (int i = 0; i < 2; i++) {
            while(true) {
                System.out.println("Umieść trójmasztowiec");
                System.out.println("Podaj współrzędne początku");
                int[] wspolrzedneStart = wczytajWspolrzedne();
                System.out.println("Podaj współrzędne końca");
                int[] wspolrzedneKoniec = wczytajWspolrzedne();
                if (sprawdzIUmiescStatekNaPlanszy(3, wspolrzedneStart, wspolrzedneKoniec, plansza))
                    break;
            }

            rysujPlansze(plansza);
        }

        for (int i = 0; i < 3; i++) {
            while(true) {
                System.out.println("Umieść dwumasztowiec");
                System.out.println("Podaj współrzędne początku");
                int[] wspolrzedneStart = wczytajWspolrzedne();
                System.out.println("Podaj współrzędne końca");
                int[] wspolrzedneKoniec = wczytajWspolrzedne();
                if (sprawdzIUmiescStatekNaPlanszy(2, wspolrzedneStart, wspolrzedneKoniec, plansza))
                    break;
            }

            rysujPlansze(plansza);
        }

        for (int i = 0; i < 4; i++) {
            while(true) {
                System.out.println("Umieść jednomasztowiec");
                System.out.println("Podaj współrzędne");
                int[] wspolrzedne = wczytajWspolrzedne();
                if (sprawdzIUmiescStatekNaPlanszy(1, wspolrzedne, wspolrzedne, plansza))
                    break;
            }

            rysujPlansze(plansza);
        }

    }

    private static boolean sprawdzIUmiescStatekNaPlanszy(int wielkosc, int[] wspolrzedneStart, int[] wspolrzednieKoniec, char[][] plansza) {
        int dlugosc = wspolrzedneStart[0] - wspolrzednieKoniec[0];
        if (dlugosc < 0)
            dlugosc = -dlugosc;

        int wysokosc = wspolrzedneStart[1] - wspolrzednieKoniec[1];
        if (wysokosc < 0)
            wysokosc = -wysokosc;

        if (dlugosc != 0 && wysokosc != 0) {
            System.out.println("Statek ma zły kształt");
            return false;
        }

        wielkosc = wielkosc - 1;
        if (dlugosc != wielkosc && wysokosc != wielkosc) {
            System.out.println("Statek ma zły kształt");
            return false;
        }

        // sprawdź czy nie ma kolizji z innym statkiem
        int startKolumna;
        int startWiersz;
        if (wspolrzedneStart[0] < wspolrzednieKoniec[0]) {
            startKolumna = wspolrzedneStart[0];
        } else {
            startKolumna = wspolrzednieKoniec[0];
        }

        if (wspolrzedneStart[1] < wspolrzednieKoniec[1]) {
            startWiersz = wspolrzedneStart[1];
        } else {
            startWiersz = wspolrzednieKoniec[1];
        }

        int startStrefaWiersz = startWiersz - 1;
        if (startStrefaWiersz < 0) {
            startStrefaWiersz = 0;
        }

        int startStrefaKolumna = startKolumna - 1;
        if (startStrefaKolumna < 0) {
            startStrefaKolumna = 0;
        }

        int koniecStrefaWiersz = startWiersz + wysokosc + 1;
        if (koniecStrefaWiersz > 9) {
            koniecStrefaWiersz = 9;
        }

        int koniecStrefaKolumna = startKolumna + dlugosc + 1;
        if (koniecStrefaKolumna > 9) {
            koniecStrefaKolumna = 9;
        }

        for (int i = startStrefaWiersz; i <= koniecStrefaWiersz; i++) {
            for (int j = startStrefaKolumna; j <= koniecStrefaKolumna; j++) {
                if (plansza[i][j] == 'X') {
                    System.out.println("Statek koliduje z innym statkiem");
                    return false;
                }
            }
        }

        for (int i = startWiersz; i <= startWiersz + wysokosc; i++) {
            for (int j = startKolumna; j <= startKolumna + dlugosc; j++) {
                plansza[i][j] = 'X';
            }
        }
        return true;
    }

    private static int[] wczytajWspolrzedne() {
        while (true) {
            System.out.print("Podaj współrzędne w formie kolumna-wiersz (np. D7): ");

            Scanner scanner = new Scanner(System.in);
            String wspolrzedne = scanner.nextLine();

            if (wspolrzedne.length() > 3 || wspolrzedne.length() < 2) {
                System.out.println("Wprowadzony tekst jest złej długości");
                continue;
            }

            char kolumna = wspolrzedne.charAt(0);
            int[] rezultat = new int[2];
            if (kolumna >= 'A' && kolumna <= 'J') {
                rezultat[0] =  kolumna - 'A';
            } else if (kolumna >= 'a' && kolumna <= 'j') {
                rezultat[0] = kolumna - 'a';
            } else {
                System.out.println("Zła wartość kolumny");
                continue;
            }

            if (wspolrzedne.length() == 3) {
                if (wspolrzedne.substring(1).equals("10")) {
                    rezultat[1] = 9;
                    return rezultat;
                } else {
                    System.out.println("Zła wartość wiersza");
                    continue;
                }
            }

            char wiersz = wspolrzedne.charAt(1);
            if (wiersz >= '1' && wiersz <= '9') {
                rezultat[1] = wiersz - '1';
                return rezultat;
            } else {
                System.out.println("Zła wartość wiersza");
            }
        }
    }

    private static void rysujPlansze(char[][] plansza) {
        System.out.println();
        System.out.println("  |ABCDEFGHIJ|");
        System.out.println("--+----------+");
        for (int i = 0; i < 10; i++) {
            if (i != 9) {
                System.out.print(" ");
            }
            System.out.print((i + 1) + "|");
            for (int j = 0; j < 10; j++) {
                System.out.print(plansza[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("--+----------+");
        System.out.println();
    }

    private static void wyczyscKonsole() {
        for (int i = 0; i < 77; i++) {
            System.out.println();
        }
    }

    private static boolean sprawdzCzyWygrana(char[][] statki) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (statki[i][j] == 'X') {
                    return false;
                }
            }
        }
        return true;
    }
}
