# Gra w Statki

## Spis Treści
1. [Opis Projektu](#opis-projektu)
2. [Zaimplementowane Funkcjonalności](#zaimplementowane-funkcjonalności)
3. [Instrukcje Obsługi](#instrukcje-obsługi)
4. [Biblioteki Zewnętrzne](#biblioteki-zewnętrzne)

## Opis Projektu
To jest prosta tekstowa implementacja klasycznej gry w Statki, gdzie dwóch graczy na zmianę umieszcza statki na siatce, a następnie próbuje zatopić statki przeciwnika, zgadując ich lokalizacje.

## Zaimplementowane Funkcjonalności
- **Tworzenie Planszy**: Inicjalizacja planszy 10x10 dla każdego gracza.
- **Umieszczanie Statków**: Gracze mogą umieszczać statki o różnych rozmiarach (4, 3, 2 i 1 jednostek) na swoich planszach.
- **Mechanizm Strzelania**: Gracze na zmianę zgadują lokalizację statków przeciwnika.
- **Warunek Wygranej**: Gra sprawdza, czy wszystkie statki danego gracza zostały zatopione.

## Instrukcje Obsługi
### Wymagania
- Zainstalowany Java Development Kit (JDK) na twoim komputerze.

### Jak Uruchomić
1. Sklonuj repozytorium:
   ```sh
   git clone https://github.com/twoja-nazwa-uzytkownika/battleship-game.git
2. Przejdź do katalogu projektu:
cd battleship-game
3. Skompiluj plik źródłowy Java:
javac Gra_w_statki.java
4. Uruchom aplikację:
java Gra_w_statki

## Biblioteki Zewnętrzne
Projekt nie wymaga żadnych zewnętrznych bibliotek.
