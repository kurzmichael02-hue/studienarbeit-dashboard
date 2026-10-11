# Anforderungen

Abgeleitet aus der Umfrage (12 Teilnehmer, anonym). Rohdaten im Studium-Ordner (Umfrage_Rohdaten_1-12.xlsx). Priorisierung aus den Bewertungen (quantitativ) und den Freitext-Antworten (qualitativ).

## Überblick der Stichprobe
- Reisehäufigkeit: überwiegend mehrmals im Jahr (8), dazu etwa 1x im Jahr (3), selten (1).
- Sprache vor der Reise lernen: 3x immer, 6x manchmal, 3x nie.
- Genutzte Apps: Reise vor allem Google Maps und Notizen-App, Sprache vor allem Duolingo (dazu Anki, Babbel).
- 11 von 12 würden so eine App grundsätzlich nutzen.
- Altersgruppen: vor allem 18-25 (7), dazu 26-35 (2), 36-50 (1), über 50 (2).

## Wichtigkeit der Funktionen (Durchschnitt 1-5, n=12)
| Funktion | Schnitt |
|---|---|
| Reiseziele | 4,3 |
| Offline-Nutzung | 4,2 |
| Packlisten | 3,6 |
| Tagespläne | 3,5 |
| Budget | 3,4 |
| Daten lokal | 3,4 |
| Vokabeln | 3,1 |
| Reisephrasen | 3,1 |
| Lernfortschritt | 3,1 |
| Notizen | 2,8 |
| Quiz | 2,5 |

Vokabeln und Reisephrasen liegen im Schnitt nur bei 3,1, weil 3 Teilnehmer nie Sprache lernen und dort 1 gaben. Bei den Sprachlernenden sind Reisephrasen klar wichtig, und im Freitext ist der Sprachteil ein Hauptargument.

## Was die Teilnehmer im Freitext sagen
- Hauptschmerz, mehrfach genannt: alles auf mehrere Apps verteilt, ständiges Wechseln ("reiseplan in der einen, sprache in der anderen, und offline geht duolingo kaum").
- Lücke bei nicht-europäischen Sprachen ("duolingo bringt mir für russisch oder japanisch kaum was praktisches", "die meisten apps können nur die standard-europa-sprachen richtig").
- Wunsch: Reisephrasen nach Situation (Flughafen, Restaurant, Taxi) statt zufälliger Vokabeln.
- Wunsch: offline, alles an einem Ort, ohne Account.
- Kritik an Sprach-Apps: Push-Spam, Kosten, Grammatik falsch oder trocken, zu viel Spielkram.
- Zusatzwünsche: gemeinsam planen, Reisepläne oder Dateien importieren.

## Funktionale Anforderungen (priorisiert)
- Muss: Reiseziele verwalten, Reisephrasen nach Situation, Vokabeln lernen, offline nutzbar, lokale Daten ohne Account
- Soll: Tagespläne, Packlisten, Budget, Lernfortschritt
- Kann: Notizen, Quiz, gemeinsam planen, Reisepläne/Dateien importieren

## Nicht-funktionale Anforderungen
- Offlinefähig (laut Daten eine der wichtigsten Eigenschaften)
- Lokale Datenhaltung, kein Account nötig
- Keine Push-Spam, nicht trocken, praktische Sätze statt zufälliger Vokabeln
- Günstig
- Sprach-Fokus auf schwierige Sprachen (Russisch, Japanisch, Chinesisch, Koreanisch)

## Zielsprachen
Mehrfach genannt: Russland, Japan, China, Korea (genau die Lücke), dazu Italien, Spanien, Frankreich, Norwegen. Passt zum geplanten Fokus RU/JA/KO.

## Offen
- Kleine Stichprobe (12) und nicht repräsentativ, in der Arbeit als Limitation nennen.
- Ein Teilnehmer merkte an, dass einige Pflichtfragen zu Antworten zwingen, auch das als Limitation.
- Nächster Schritt: Konzeption (Architektur und Datenmodell).
