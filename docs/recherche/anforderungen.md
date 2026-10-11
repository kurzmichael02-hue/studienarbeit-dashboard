# Anforderungen

Stand 11.10.2026, abgeleitet aus der Online-Umfrage (12 Teilnehmer, anonym). Rohdaten: `OneDrive/Dokumente/Studium/5 Semester/Studienarbeit/Umfrage_Rohdaten_1-12.xlsx`. Priorisierung aus Bewertungen (quantitativ) + Freitext (qualitativ).

## Überblick der Stichprobe
- Reisehäufigkeit: überwiegend "mehrmals im Jahr" (8), dazu "etwa 1x im Jahr" (3), "selten" (1). Also Vielreisende.
- Sprache lernen vor Reisen: 3x immer, 6x manchmal, 3x nie.
- Genutzte Reise-Apps: vor allem Google Maps + Notizen-App (dazu Wanderlog, Oruxmaps, Get Your Guide).
- Genutzte Sprach-Apps: vor allem Duolingo (dazu Anki, Babbel); 4x keine.
- Würde die App nutzen: 6x Ja, 5x Vielleicht, 1x Nein. Also 11 von 12 grundsätzlich interessiert.
- Altersgruppen: überwiegend 18-25 (7), dazu 26-35 (2), 36-50 (1), über 50 (2).

## Wichtigkeit der Funktionen (Durchschnitt 1-5, n=12)
| Funktion | Schnitt |
|---|---|
| Reiseziele | 4,3 |
| Offline-Nutzung (ohne Internet) | 4,2 |
| Packlisten | 3,6 |
| Tagespläne | 3,5 |
| Budget | 3,4 |
| Daten lokal (nicht auf Server) | 3,4 |
| Vokabeln | 3,1 |
| Reisephrasen | 3,1 |
| Lernfortschritt | 3,1 |
| Notizen | 2,8 |
| Quiz | 2,5 |

Hinweis: Vokabeln/Reisephrasen liegen im Schnitt nur bei 3,1, weil 3 Teilnehmer nie Sprache lernen (und dort 1 gaben). Bei den Sprachlernenden werden Reisephrasen klar hoch bewertet, und im Freitext ist der Sprachteil ein Hauptargument. Darum nicht am Schnitt allein festmachen.

## Kernaussagen aus dem Freitext
- Hauptschmerz, mehrfach genannt: alles auf mehrere Apps verteilt, ständiges Wechseln. "reiseplan in der einen, sprache in der anderen, und offline geht duolingo kaum" / "ständig zwei apps parallel offen".
- Lücke bei nicht-europäischen Sprachen: "duolingo bringt mir für russisch oder japanisch kaum was praktisches" / "die meisten apps können nur die standard-europa-sprachen richtig". Genau unser Scope (Russland, Japan, Korea/China).
- Gewünscht: Reisephrasen nach Situation sortiert (Flughafen, Restaurant, Taxi) statt zufälliger Vokabeln.
- Gewünscht: offline + alles an einem Ort + ohne Account ("dass es ohne account läuft").
- Kritik an bestehenden Sprach-Apps: Push-Spam, Kosten, Grammatik falsch oder "derbe trocken", zu viel Spielkram.
- Zusatzwünsche (nicht geplant): gemeinsam/sozial planen, Reisepläne/Dateien importieren.

## Funktionale Anforderungen (FA), priorisiert
Muss:
- Reiseziele verwalten (höchste Bewertung)
- Reisephrasen, nach Situation sortiert (Flughafen, Restaurant, Taxi)
- Vokabeln lernen
- Voll offline nutzbar
- Lokale Datenhaltung, Nutzung ohne Account

Soll:
- Tagespläne, Packlisten, Budget
- Lernfortschritt

Kann:
- Notizen, Quiz (niedrig bewertet)
- Gemeinsam / sozial planen (Zusatzwunsch)
- Reisepläne / Dateien importieren (Zusatzwunsch)

## Nicht-funktionale Anforderungen (NFA)
- Offlinefähig (laut Daten eine der wichtigsten Eigenschaften, Schnitt 4,2)
- Lokale Datenhaltung, kein Account nötig (Datenschutz)
- Bedienbarkeit: kein Push-Spam, nicht "trocken"/kein Spielkram, praktische Sätze statt zufälliger Vokabeln
- Günstig / ohne Abo-Zwang
- Sprach-Fokus auf schwierige Sprachen (Russisch, Japanisch, dazu Chinesisch/Koreanisch), wo bestehende Apps schwach sind

## Genannte Zielländer / -sprachen
Russland, Japan, China, Korea (mehrfach als Lücke genannt), dazu Italien, Spanien, Frankreich, Norwegen, Thailand, Georgien, "Länder um Deutschland". Bestätigt den geplanten Fokus RU/JA/KO.

## Offen / Limitationen
- Stichprobe klein (n=12) und nicht repräsentativ (convenience/snowball über SurveyCircle). In der Arbeit als Limitation benennen.
- Ein Teilnehmer merkte an, dass Pflichtfragen zu Antworten zwingen, auch ohne passende Option. Mitten im Lauf nicht geändert, als Limitation notieren.
- Interviews: Michael dagegen (12 Antworten reichen), Schayan wollte welche. Team-Entscheidung offen.
- Nächster Schritt: Use Cases (UML) + Konzeption (Architektur, ER-Datenmodell).
