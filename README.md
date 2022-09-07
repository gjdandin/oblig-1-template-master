# Obligatorisk oppgave 1 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Gilhan Dandin, S360903, s360903@oslomet.no


# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Per har hatt hovedansvar for oppgave 1, 3, og 5. 
* Else har hatt hovedansvar for oppgave 2, 4, og 6. 
* Fatima har hatt hovedansvar for oppgave 7 og 8. 
* Vi har i fellesskap løst oppgave 10. 

# Oppgavebeskrivelse

1. I oppgave 1 så gikk vi frem ved å bruke metodene maks og bytt som vi har lært
på forelesningen i denne maks-metoden. Ombyttinger-metoden er samme prinsipp,
bare med et return av et int antall istedet.

* Når blir det flest ombyttinger? 
  * Hvis tallene er sortert omvendt med descending order, og starter fra 9 og slutter med 1, i en array fra 1-9
* Når blir det færrest?
  * Hvis tallene er sortert "riktig" og starter fra 1 og slutter med 9, i en array fra 1-9
* Hvor mange blir det i gjennomsnitt?
  * Det blir i gjennomsnitt log(n) – 0,423 ombyttinger, hvis n var antall tall i arrayet.
    * Naturlige logaritmen
  * For et random array med 10 tall, blir det log(10) - 0,423 = 2.3 - 0,423 = 1.9 ombyttinger.

Kan du på grunnlag av dette si om metoden maks er bedre (eller dårligere)
enn de maks-metodene vi har sett på tidligere?
* Denne maks metoden er dårligere pga. ekstra operasjoner som å bytte posisjoner mens O(n) fortsatt gjelder.


2. I oppgave 2 så brukte jeg en hjelpe-arraylist og arraylist metoden contain for å sjekke om et tall
hadde et duplikat i arrayet. Resten er gjort med if-statements for å sjekke om arrayet er sortert riktig, og en while
loop for å loope gjennom arrayet og telle unike integers (lineær-O notasjon). 

3. I oppgave 3 så måtte jeg bruke en nested for loop for å sammenligne og finne duplikater. Dette er annerledes
  fra oppgave 2 fordi her så kan man ikke bruke hjelpe-tabeller. O(n*n) gjør algoritmen tregere men jeg fant ikke andre løsninger.
  Trikset er å loope gjennom hele arrayet og sjekke for duplikat for hver eneste tall og telle duplikater. 
  Return statement blir da a.length - antallduplikater.

4. 
