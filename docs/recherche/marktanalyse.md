# Marktanalyse

Betrachtet wurden bestehende Reiseplaner, Sprachlern-Apps und lokale, self-hosted Tools, und ob eines davon beides abdeckt.

Ergebnis: keines kombiniert Reiseplanung und Sprachenlernen in einem Tool, komplett offline und lokal. Jede App deckt nur einen Teil ab. Das ist die Lücke, die unser Prototyp füllt.

## Wonach wir vergleichen
Reise-Funktionen, Sprach-Funktionen, Kombination aus beidem, Offline-Fähigkeit, wo die Daten liegen, und Kosten.

Die Kriterien lehnen sich an zwei Normen an: ISO/IEC 25010 (Software-Qualität) und ISO 9241-11 (Usability). Beide stehen in `literatur.md`.

## Vergleich (Stand 02.11.2026, nur Herstellerangaben, kein eigener Test)
| System | Reise | Sprache | Kombi | Offline | Daten lokal | Kosten |
|---|---|---|---|---|---|---|
| Wanderlog | ja | nein | nein | nur Pro | nein | gratis, Pro 39,99 USD/Jahr |
| TripIt | teilw. (Plan aus Buchungen) | nein | nein | ja (Abruf) | nein | gratis, Pro 48,99 EUR/Jahr |
| Anki | nein | ja | nein | ja, voll | ja, Sync optional | Desktop gratis, iOS 29,99 EUR einmalig |
| LingoDeer | nein | ja | nein | ja, vorab laden | nein | erste Lektionen gratis, 14,99 USD/Monat oder 95,99 USD/Jahr |
| AdventureLog | ja | nein | nein | nur im eigenen Netz | ja (self-hosted) | gratis (GPL-3.0), eigene Hardware |
| Unser Dashboard | ja | ja | ja | voll | ja (Pi) | eigen |

Quellen für die Zahlen (alle am 02.11.2026 abgerufen, stehen so auch in der Bib des Berichts):
- Wanderlog Pro: https://wanderlog.com/pro
- TripIt (App Store DE): https://apps.apple.com/de/app/tripit-travel-planner/id311035142
- Anki Desktop: https://apps.ankiweb.net/ , AnkiMobile (App Store DE): https://apps.apple.com/de/app/ankimobile-flashcards/id373493387
- LingoDeer Preise: https://www.lingodeer.com/de/pricing , Offline-Modus (App Store DE): https://apps.apple.com/de/app/lingodeer-sprachen-lernen/id1261193709
- AdventureLog: https://adventurelog.app/

Das ausformulierte Kapitel steht im Bericht (Kapitel 3).
