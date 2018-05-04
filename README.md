# Monster Maths

# CI Status

| Branch      | Status |
| ----------- | ------ |
| master      | [![build status](https://gitlab.in.htwg-konstanz.de/mobile-ss18/mobile-ss18-13/badges/master/build.svg)](https://gitlab.in.htwg-konstanz.de/mobile-ss18/mobile-ss18-13/commits/master) |
| dev      | [![build status](https://gitlab.in.htwg-konstanz.de/mobile-ss18/mobile-ss18-13/badges/dev/build.svg)](https://gitlab.in.htwg-konstanz.de/mobile-ss18/mobile-ss18-13/commits/dev) |

In dem Spiel Monster Maths geht es darum mathematische Gleichung auf eine spielerische Art zu lösen.  
Der Spieler steuert einen Avatar mit Hilfe des Beschleunigungssensors am Handy durch eine kleine Spielwelt.  
Ziel eines Levels ist es auf ein vorgegebenes Ergebnis zu kommen.   
Die einzelnen Teile des Lösungswegs sind auf den Bildschirm verteilt und müssen in der korrekten Reihenfolge eingesammelt werden.   
Damit das Spiel nicht ganz zu einfach ist, bewegen sich verschiedene Monster auch über den Bildschirm und blockieren den Benutzer.   
Ein Timer misst, wie lange der User für die Lösung benötigt.   
Anhand des Timers kann für jedes Level eine Rangliste erstellt werden, in der User sich vergleichen können.  

## Getting Started
Gehe in den Google Play Store und lade dir die App herunter. (Hier Link einfügen)  
Oder benutze alternativ die git clone Funktion um das Projekt in Android Studio zu importieren. (Link einfügen)

## Genaue Beschreibung, Persona, Use Cases etc...
kommt später noch hinzu

## Planung
- __Woche 1__ [ ] Initiale App mit Ansteuerung des Sensors und Verarbeitung der Sensordaten.
- __Woche 2__ [ ] Auswahl der Buttons, Icons, Spielelemente und deren Animation. Design und Anpassung an ausgewählte Geräte. 
- __Woche 3__ [ ] Implementierung der Businesslogik + Unit Tests 
- __Woche 4__ [ ] Level Design (20+ angestrebt) 
- __Woche 5__ [ ] Highscore Listen inklusive Server, Social Media Features etc.
- __Woche 6__ [ ] Vorbereitung für das Deployment (Ziel: Play Store), Buffer, Performance

## Running the tests

Um eine hohe Codequalität zu erreichen ist es sinnvoll Tests zu schreiben.  
Mit Gradle kann dies automatisiert werden.

Alle Unit Tests lassen sich mit einen Befehl ausführen

```
gradle test
```

Komponenten Tests sowie UI Tests sind noch unklar ob diese benötigt werden.

### And coding style tests

Über Code Style tests muss auch noch diskutiert werden

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Android Studio](https://developer.android.com/studio/install) - The IDE used
* [Gradle](https://gradle.org) - Build Tool

## Authors

* **Markus Büchler** - *Concept, Coding, Testing ,etc..* -


## License

This project is NOT YET licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
