package no.oslomet.cs.algdat.Oblig1;

////// Løsningsforslag Oblig 1 ////////////////////////

import java.lang.UnsupportedOperationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Oblig1 {
    private Oblig1() {}

    ///// Oppgave 1 //////////////////////////////////////
    public static int maks(int[] a) {
        if (a.length < 1) { //hvis tabell er tomt, error
            throw new NoSuchElementException();
        }

        int maks = a[a.length-1];
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1) { //hvis det er siste tall, ikke sammenlign. Return maks.
                return maks;
            }
            if (a[i] > a[i+1]) { // Sjekker hvis venstre er større enn høyre i verdi
                int temp = a[i+1]; //lagrer høyre
                a[i+1] = a[i]; // gjør om høyre til venstre
                a[i] = temp; // gjør om venstre til den gamle høyre verdien.
                maks = a[i+1]; //lagrer maks
            }
        }
        return maks;
    };

    /* * Når blir det flest ombyttinger?
    * Hvis tallene er sortert omvendt med descending order, og starter fra 9 og slutter med 1, i en array fra 1-9
    * Når blir det færrest?
      * Hvis tallene er sortert "riktig" og starter fra 1 og slutter med 9, i en array fra 1-9
    * Hvor mange blir det i gjennomsnitt?
      * Det blir i gjennomsnitt log(n) – 0,423 ombyttinger, hvis n var antall tall i arrayet.
        * Naturlige logaritmen
      * For et random array med 10 tall, blir det log(10) - 0,423 = 2.3 - 0,423 = 1.9 ombyttinger. */
    public static int ombyttinger(int[] a) {
        if (a.length == 0) { //hvis tabell er tomt, error
            throw new NoSuchElementException();
        }

        int antallombyttinger = 0;
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1) {
                return antallombyttinger;
            }
            if (a[i] > a[i+1]) { // Sjekker hvis venstre er større enn høyre i verdi
                int temp = a[i+1]; //lagrer høyre
                a[i+1] = a[i]; // gjør om høyre til venstre
                a[i] = temp; // gjør om venstre til den gamle høyre verdien.
                antallombyttinger++; // Legger til i antall ombyttinger
            }
        }
        return antallombyttinger;
    }

    ///// Oppgave 2 //////////////////////////////////////
    public static int antallUlikeSortert(int[] a) {
        int i = 0;
        List<Integer> b = new ArrayList<Integer>(); //Lista der vi legger unike verdier.

        if (a.length == 0) { // Sjekker hvis arrayet er tomt
            return 0;
        }
        while (i < a.length) {
            if (i == a.length - 1) { //Hvis i er lik a sitt siste indeks,
                if (!b.contains(a[i])) { //Sjekk hvis den er der allerede, hvis ikke så legg til lista.
                    b.add(a[i]);
                    return b.size();
                }
                else {
                    return b.size(); //Ellers returnerer man antall ulike tall.
                }
            }

            if (a[i] > a[i+1]) { //Hvis venstre er større enn høyre, er den ikke sortert.
                throw new IllegalStateException("Tallene i arrayet er ikke sortert!");
            }

            if (!b.contains(a[i]) ) { // Hvis b-lista som inneholder unike verdier ikke har a[i], legg til.
                b.add(a[i]);
            }

            i++;
        }
        return b.size();
    }

    ///// Oppgave 3 //////////////////////////////////////
    public static int antallUlikeUsortert(int[] a) {
        if (a.length == 0) { // Sjekker hvis arrayet er tomt
            return 0;
        }
        else if (a.length == 1) { // Hvis arrayet er bare 1, da er det ingen duplikater.
            return 1;
        }

        int duplikater = 0;

        for (int i = 0; i < a.length; i++) { //For loop for i går gjennom hele arrayet
            for (int j = i+1; j < a.length; j++) {
                // for loop j looper gjennom hele arrayet og sjekker om det finnes duplikat for a[i]
                if (a[i] == a[j]) {
                    duplikater++; // legg til antall duplikater
                    break; //stopp å sjekk resten av arrayet, man teller ikke flere duplikater.
                }
            }
        }

        return a.length - duplikater;
    }

    ///// Oppgave 4 //////////////////////////////////////
    public static void delsortering(int[] a) {
        if (a.length > 1) { //lista må være lenger enn 1.
            boolean barepartall = true;
            boolean bareoddetall = true;

            for (int i = 0; i < a.length; i++) { //Sjekk hvis tabellen er bare oddetall
                if (a[i] % 2 == 0) {
                    bareoddetall = false;
                    break;
                }
            }

            for (int i = 0; i < a.length; i++) { //Sjekk hvis tabellen er bare partall
                if (a[i] % 2 != 0) {
                    barepartall = false;
                    break;
                }
            }

            //Hvis bare partall eller oddetall, sorter på vanlig måte.
            if (barepartall || bareoddetall) {
                for (int i = 0; i < a.length; i++) { //venstre
                    for (int j = i + 1; j < a.length; j++) { // høyre
                        if (a[i] > a[j]) { // hvis venstre er større enn høyre - bytt.
                            int temp = a[i];
                            a[i] = a[j];
                            a[j] = temp;
                        }
                    }
                }
            }

            //Sjekker hvis midten er partall
            boolean midtenpartall = false;
            if (a[a.length/2] % 2 == 0) { //hvis midten er partall
                midtenpartall = true;
            }

            if (!bareoddetall && !barepartall) {
                for (int i = 0; i < a.length; i++) {
                    // partisjonering hvis partall
                    if (a[i] % 2 == 0) { //Sjekk hvis a[i] er partall
                        int j = 0;
                        if (midtenpartall) {
                            j = a.length / 2;
                        } else {
                            j = a.length / 2 + 1;
                        }
                        for (; j < a.length; j++) { //finn neste oddetall så bytt
                            //starter fra midten(hvis mid er partall) eller +1(hvis mid er oddetall) til høyre
                            if (a[j] % 2 != 0) { //sjekk hvis oddetall
                                int temp = a[j]; //lagre oddetallet
                                a[j] = a[i]; //gjør oddetallet til partallet
                                a[i] = temp; //gjør original posisjon til partallet til oddetall (venstre for mid)
                                break;
                            }
                        }
                    }

                    //partisjonering hvis oddetall
                    if (a[i] % 2 != 0) { //Sjekk hvis a[i] er oddetall
                        int j = 0;
                        if (midtenpartall) {
                            j = a.length / 2;
                        } else {
                            j = a.length / 2 - 1;
                        }
                        for (; j > -1; j--) { //finn neste partall så bytt
                            //starter fra midten til venstre
                            if (a[j] % 2 == 0) { //sjekk hvis partall
                                int temp = a[j]; //lagre partallet
                                a[j] = a[i]; //gjør partallet til oddetallet
                                a[i] = temp; //gjør original posisjon til oddetallet til partallet (høyre for mid)
                                break;
                            }
                        }
                    }

                    if (a[0] % 2 == 0) { //sjekk hvis første tall i array er et partall, isåfall bytt med midten
                        int temp = 0;
                        temp = a[0];
                        a[0] = a[a.length / 2];
                        a[a.length / 2] = temp;
                    }

                    // Hvorfor blir ikke første og midten byttet av algoritmen?
                    // Pga. for loop rører ikke første indeks fra midten til venstre hvis a[0] er oddetall
                    // eller a[a.length/2] hvis midten er partall.
                }
            }

            // Sortering av partisjonene
            // Sorterer partallene
                for (int i = a.length/2 + 1; i < a.length; i++) { //venstre
                    for (int j = i + 1; j < a.length; j++) { // høyre
                        if (a[i] > a[j]) { // hvis venstre er større enn høyre - bytt.
                            int temp = a[j];
                            a[j] = a[i];
                            a[i] = temp;
                        }
                    }
                }


            // Sorterer oddetallene
            if (a[a.length/2] % 2 == 0) {
                for (int i = 0; i < a.length/2; i++) { //sorter oddetall til venstre
                    for (int j = i + 1; j < a.length/2; j++) { // høyre
                        if (a[i] > a[j]) { // hvis venstre er større enn høyre - bytt.
                            int temp = a[i];
                            a[i] = a[j];
                            a[j] = temp;
                        }
                    }
                }
            }
            else {
                for (int i = 0; i < a.length/2+1; i++) { //sorter oddetall til venstre
                    for (int j = i + 1; j < a.length/2+1; j++) { // høyre
                        if (a[i] > a[j]) { // hvis venstre er større enn høyre - bytt.
                            int temp = a[i];
                            a[i] = a[j];
                            a[j] = temp;
                        }
                    }
                }
            };

        }
    }

    ///// Oppgave 5 //////////////////////////////////////
    public static void rotasjon(char[] a) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 6 //////////////////////////////////////
    public static void rotasjon(char[] a, int k) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 7 //////////////////////////////////////
    /// 7a)
    public static String flett(String s, String t) {
        throw new UnsupportedOperationException();
    }

    /// 7b)
    public static String flett(String... s) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 8 //////////////////////////////////////
    public static int[] indekssortering(int[] a) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 9 //////////////////////////////////////
    public static int[] tredjeMin(int[] a) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 10 //////////////////////////////////////
    public static int bokstavNr(char bokstav) {
        throw new UnsupportedOperationException();
    }

    public static boolean inneholdt(String a, String b) {
        throw new UnsupportedOperationException();
    }

}  // Oblig1
