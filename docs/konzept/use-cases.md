# Use Cases

Stand 11.10.2026. Textuelle Basis, abgeleitet aus den Anforderungen (`docs/recherche/anforderungen.md`). Das eigentliche UML-Use-Case-Diagramm wird daraus gezeichnet.

## Akteur
- Reisende/r: ein einzelner Nutzer. Die App läuft lokal auf dem Raspberry Pi, ohne Account und ohne Login. Es gibt also nur einen Akteur.

## Use Cases im Überblick
Reisebereich:
- UC1 Reise anlegen und verwalten (Reiseziele)
- UC2 Tagesplan erstellen
- UC3 Packliste verwalten
- UC4 Budget verwalten
- UC5 Notizen erfassen

Sprachbereich:
- UC6 Vokabeln lernen
- UC7 Reisephrasen nachschlagen (nach Situation: Flughafen, Restaurant, Taxi)
- UC8 Quiz durchführen
- UC9 Lernfortschritt ansehen

Alle Use Cases funktionieren offline.

## Detaillierte Beschreibungen (Kern-Use-Cases)

### UC1 Reise anlegen und verwalten
- Akteur: Reisende/r
- Vorbedingung: App ist auf dem Pi erreichbar (WLAN)
- Ablauf:
  1. Nutzer öffnet den Reisebereich
  2. Nutzer legt eine neue Reise an (Ziel, Zeitraum)
  3. System speichert die Reise lokal (H2)
  4. Nutzer kann die Reise später bearbeiten oder löschen
- Nachbedingung: Reise ist lokal gespeichert und in der Übersicht sichtbar

### UC7 Reisephrasen nachschlagen
- Akteur: Reisende/r
- Vorbedingung: eine Zielsprache ist ausgewählt
- Ablauf:
  1. Nutzer wählt eine Situation (z.B. Flughafen, Restaurant, Taxi)
  2. System zeigt die passenden Phrasen in der Zielsprache
  3. Nutzer kann eine Phrase als gelernt markieren
- Nachbedingung: Phrasen sind offline abrufbar, Markierung ist gespeichert
- Hinweis: aus der Umfrage, Nutzer wollen situative Sätze statt zufälliger Vokabeln

### UC6 Vokabeln lernen
- Akteur: Reisende/r
- Vorbedingung: Vokabelsatz zur Zielsprache vorhanden
- Ablauf:
  1. Nutzer startet eine Lerneinheit
  2. System zeigt Vokabeln, Nutzer bewertet sein Wissen
  3. System aktualisiert den Lernfortschritt (UC9)
- Nachbedingung: Lernfortschritt ist lokal gespeichert

## Offen
- UML-Diagramm zeichnen (Akteur + die 9 Use Cases, gruppiert in Reise und Sprache)
- Restliche Use Cases (UC2-UC5, UC8, UC9) bei Bedarf ausführlich beschreiben
- Priorität folgt den Anforderungen: UC1, UC6, UC7 sind Muss
