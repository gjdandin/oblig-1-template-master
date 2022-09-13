package no.oslomet.cs.algdat.Oblig1;

////// Løsningsforslag Oblig 1 ////////////////////////

import java.lang.UnsupportedOperationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Oblig1 {
    private Oblig1() {}

    // Metoder fra undervisningen //////////////////////////////////////
    public static void bytt(int[] a, int i, int j)
    {
        int temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    private static int parter0(int[] a, int v, int h, int skilleverdi)
    {
        while (true)                                  // stopper når v > h
        {
            while (v <= h && a[v] < skilleverdi) v++;   // h er stoppverdi for v
            while (v <= h && a[h] >= skilleverdi) h--;  // v er stoppverdi for h

            if (v < h) bytt(a,v++,h--);                 // bytter om a[v] og a[h]
            else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
    }
    static int sParter0(int[] a, int v, int h, int indeks)
    {
        bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter0(a, v, h - 1, a[h]);  // partisjonerer a[v:h - 1]
        bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
        return pos;                   // returnerer posisjonen til skilleverdien
    }

    public static void kvikksortering0(int[] a, int v, int h)  // en privat metode
    {
        if (v >= h) return;  // a[v:h] er tomt eller har maks ett element
        int k = sParter0(a, v, h, (v + h)/2);  // bruker midtverdien
        kvikksortering0(a, v, k - 1);     // sorterer intervallet a[v:k-1]
        kvikksortering0(a, k + 1, h);     // sorterer intervallet a[k+1:h]
    }
    ///// Metoder fra undervisningen //////////////////////////////////////


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
                kvikksortering0(a, 0, a.length - 1);

                /* Gamle måten jeg gjorde dette på, ineffektiv med O(n*n)
                for (int i = 0; i < a.length; i++) { //venstre
                    for (int j = i + 1; j < a.length; j++) { // høyre
                        if (a[i] > a[j]) { // hvis venstre er større enn høyre - bytt.
                            int temp = a[i];
                            a[i] = a[j];
                            a[j] = temp;
                        }
                    }
                } */
            }

            if (!bareoddetall && !barepartall) {
                int x = 0;
                int n = a.length - 1;

                int antallpartall = 0;
                int antalloddetall = 0;

                boolean funneteven = false;
                boolean funnetodd = false;


                for (int i = 0; i < a.length; i++) {
                    //Teller hvor mange oddetall det er for senere bruk (pivot)
                    if (a[i] % 2 != 0) {
                        antalloddetall++;
                    }
                }

                while (x < n) { //Stopp loopen når venstre og høyre pointer krysser hverandre.
                    // Start fra hver ende og stopp når en oddetall/partall blir funnet.
                    // x starter fra venstre og finner partall. N starter fra høyre og finner oddetall.
                    if (a[x] % 2 == 0) {
                        funneteven = true;
                    }
                    else {
                        x++;
                    }

                    if (a[n] % 2 != 0) {
                        funnetodd = true;
                    }
                    else {
                        n--;
                    }

                    if (funneteven && funnetodd) { // Når begge har funnet en partall/oddetall, switch de.
                        int temp = a[n];
                        a[n] = a[x];
                        a[x] = temp;
                        funneteven = false;
                        funnetodd = false;
                    }
                }

                kvikksortering0(a, 0, antalloddetall - 1); //Sorterer alle oddetallene.
                kvikksortering0(a, antalloddetall, a.length-1); //Sorterer alle partallene.
            }
        }
    }

    ///// Oppgave 5 //////////////////////////////////////
    public static void rotasjon(char[] a) {
        if (a.length < 2) {
            return;
        }
        int n = a.length;
        int d = 1;
        int siste = a[a.length-1];
        for (int i = a.length - 1; i > 0; i--) {
            a[i] = a[i-1];
        }
        a[0] = (char) siste;
    }

    ///// Oppgave 6 //////////////////////////////////////
    public static void rotasjon(char[] a, int k) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 7 //////////////////////////////////////
    /// 7a)
    public static String flett(String s, String t) {
        if (s.length() == 0 && t.length() == 0) { // Hvis det er to tomme strenger, returner "";
            return "";
        }

        if (s.length() == 0) {
            return t;
        }
        else if (t.length() == 0) {
            return s;
        }

        String [] r = new String[s.length() + t.length()]; // Dette skal returneres
        String [] first = s.split(""); //gjør om string til string array
        String [] second = t.split("");

        int i = 0, j = 0, k = 0;
        while (i < first.length && j < second.length) {
            r[k++] = first[i++]; // verdi fra s
            r[k++] = second[j++]; // verdi fra t
        }

        while (i < first.length) { //Hvis det er noe igjen, kopier til array.
            r[k++] = first[i++];
        }

        while (j < second.length) { //Hvis det er noe igjen, kopier til array.
            r[k++] = second[j++];
        }

        return String.join("", r); //Returner string arrayet som string.
    }

    /// 7b)
    public static String flett(String... s) {
        if (s.length == 0) { // Hvis det er tomt tabell, returner "";
            return "";
        };

        String [] r = new String[s.length]; //Hjelpetabell for å lagre return setningen.
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
