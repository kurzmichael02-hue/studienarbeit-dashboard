# Vergleichskriterien + Vergleichstabelle (Entwurf)

Stand 08.10.2026. Entwurf. Die Kriterien sind an anerkannte Normen/Literatur angelehnt, die Tabellen-Inhalte stammen noch aus Web-Recherche. Beides muss vor der Abgabe von uns geprueft werden (Normen lesen und zitieren, Apps selbst testen).

## Woher die Kriterien kommen (Literatur)
Damit die Kriterien nicht willkuerlich sind, lehnen wir sie an etablierte Normen und Standardliteratur an:

- **ISO/IEC 25010:2023** (Produktqualitaetsmodell fuer Software, 2. Ausgabe, Nov 2023). Liefert Qualitaetsmerkmale wie funktionale Eignung, Benutzbarkeit, Kompatibilitaet, Portabilitaet/Flexibilitaet. Daraus leiten wir die Funktions- und Qualitaetskriterien ab.
  Hinweis: die genauen Unter-Merkmale der 2023-Fassung muessen wir direkt am Standard nachschlagen, die Bezeichnungen haben sich gegenueber 2011 teils geaendert.
- **ISO 9241-11:2018** (Gebrauchstauglichkeit/Usability). Definiert Usability als Effektivitaet, Effizienz und Zufriedenheit in einem Nutzungskontext. Das ist die Grundlage fuer unseren spaeteren Usability-Test (Validierung).
- **Nielsen** (Usability-Komponenten: Erlernbarkeit, Effizienz, Einpraegsamkeit, Fehler, Zufriedenheit; sowie die heuristische Evaluation, Nielsen 1994). Nuetzlich fuer eine Experten-Bewertung der Bestands-Apps.
- **System Usability Scale (SUS)** als standardisierter Fragebogen fuer die Zufriedenheit im Usability-Test.
- **Klaus Pohl: Requirements Engineering. Fundamentals, Principles, and Techniques** (Springer). Grundlage fuer die Anforderungserhebung (Interviews, Fragebogen/Umfrage).

Diese Quellen sind echt und Standard fuer eine Informatik-Arbeit. Wir muessen sie aber selbst lesen und richtig zitieren, nicht nur den Namen nennen.

## Vergleichskriterien (abgeleitet)
1. Reise-Funktionen (Ziele, Tagesplaene, Packlisten, Budget, Notizen)
2. Sprach-Funktionen (Vokabeln, Reisephrasen, Quiz, Lernfortschritt)
3. Kombination Reise + Sprache in einem System
4. Offline-Faehigkeit (voll / nur nach Download-Sync / nein)
5. Datenhaltung (lokal vs. Cloud, Account noetig)
6. Kosten / Lizenz
7. Abdeckung Russisch / Japanisch / Koreanisch

## Vergleichstabelle (Entwurf, Zellen noch zu pruefen)
Legende: ja / teilw. / nein / ? = unklar, selbst testen.

| System | Reise | Sprache | Kombi | Offline | Daten lokal | Kosten | RU/JA/KO |
|---|---|---|---|---|---|---|---|
| Wanderlog | ja | nein | nein | Ansicht ja, Bearbeitung meist Pro/online | nein (Cloud) | Basis gratis, Pro-Abo | n/a |
| TripIt | teilw. | nein | nein | ja (Abruf) | nein (Cloud) | freemium | n/a |
| LingoDeer | nein | ja | nein | ja (vorab laden) | nein (Account) | freemium | JA ja, KO ja, RU ? |
| Anki | nein | ja | nein | ja (voll) | teilw. (lokal + Sync) | PC/Android gratis, iOS ~30 EUR | alle (eigene Decks) |
| AdventureLog | ja | nein | nein | teilw. (Sync) | ja (self-hosted) | open source | n/a |
| **Unser Prototyp (Ziel)** | **ja** | **ja** | **ja** | **voll** | **ja (Pi, kein Account)** | **eigen/open** | **RU/JA/KO (exemplarisch)** |

## Was die Tabelle zeigt
Nur unser Prototyp hat gleichzeitig: Kombination Reise + Sprache, volle Offline-Faehigkeit und komplett lokale Datenhaltung ohne Account. Jedes Bestandssystem faellt bei mindestens einer dieser drei Spalten durch. Das ist die Luecke, sauber dargestellt.

## Details zu den selbst angeschauten Apps (Anki + Wanderlog)

### Anki (Karteikarten / Vokabeltrainer)
Basiert auf wissenschaftlich fundierter Spaced Repetition (SRS), weltweiter Standard bei Medizinstudenten, Juristen und fortgeschrittenen Sprachlernern.
Bewertungen (zu verifizieren): App Store ca. 4,8/5 (AnkiMobile), Play Store ca. 4,7/5 (AnkiDroid), Capterra ca. 4,6/5.
Pro: sehr hoher Langzeit-Lernerfolg durch den Wiederhol-Algorithmus; riesige Community mit tausenden fertigen Kartensaetzen; Desktop (Win/Mac) und Android (AnkiDroid) komplett gratis und werbefrei; stark anpassbar ueber Add-ons.
Contra: steile Lernkurve, altbackenes Design, Einrichtung fuer Anfaenger kompliziert; iOS-App kostet einmalig ca. 30 EUR (Vorsicht vor Klonen wie "AnkiApp"/"Anki Pro" mit Abofallen); keine Gamification.
Fuer uns wichtig: voll offline nutzbar, lokale Datenhaltung mit optionalem Sync, eigene Vokabeln/Sprachen moeglich.

### Wanderlog (Reise- und Routenplaner)
Eine der staerksten All-in-One-Apps fuer Urlaube, Roadtrips und Gruppenreisen.
Bewertungen (zu verifizieren): App Store ca. 4,9/5, Play Store ca. 4,7/5, Trustpilot ca. 1,9/5 (dort v.a. Beschwerden ueber versehentliche Pro-Testabos und Drittanbieter-Ticketpreise).
Pro: automatisches Auslesen von Buchungsbestaetigungen (z.B. Gmail) in den Reiseplan; interaktive Karten mit Distanz-/Routenoptimierung; Echtzeit-Zusammenarbeit fuer Gruppen; integrierter Budgetrechner mit Kostenteilung.
Contra: Performance-Probleme bei sehr langen Routen oder schlechter Verbindung; Interface anfangs ueberladen; viele Komfort-Funktionen (Routenoptimierung, Offline-Bearbeitung) nur in der Pro-Version.
Fuer uns wichtig: Ansicht offline moeglich, aber zuverlaessige Bearbeitung meist nur online/Pro; Daten liegen in der Cloud.

### Direktvergleich (Kurzform)
- Hauptzweck: Anki = Langzeit-Memorierung (Vokabeln, Fakten); Wanderlog = Detailplanung von Reisen.
- Bedienung: Anki komplex, braucht Einarbeitung; Wanderlog funktional, etwas Klick-Geduld.
- Kosten: Anki PC/Android gratis, iOS ~30 EUR einmalig; Wanderlog Basis gratis, erweitertes Pro-Abo.
- Offline: Anki voll offline; Wanderlog Ansicht ja, Bearbeitung oft nur online verlaesslich.

Hinweis: die Stern-Bewertungen stammen aus den Store-/Portal-Angaben und sollten vor dem Zitieren selbst geprueft werden (Datum notieren, Werte aendern sich).

## Methode (wie wir vergleichen)
Kriteriengestuetzter Vergleich: die wichtigsten frei verfuegbaren Systeme (Anki, Wanderlog) selbst ausprobieren, die uebrigen anhand von Herstellerangaben, Dokumentation und Rezensionen bewerten und nach den Kriterien gewichten. So koennen wir ehrlich sagen, die zentralen Systeme selbst angesehen zu haben.

## Ehrlich, was noch fehlt
1. Normen selbst lesen und die Unter-Merkmale (v.a. ISO 25010:2023) korrekt uebernehmen + zitieren.
2. Anki und Wanderlog selbst ausprobieren und die "?"-Zellen fuellen; den Rest ueber Doku und Rezensionen.
3. [erledigt] Finale Tabelle enthaelt: Wanderlog, TripIt (Reise), Anki, LingoDeer (Sprache), AdventureLog (self-hosted/Pi) + unser Prototyp. Nur in der Recherche betrachtet, nicht in der Matrix: Stippl, Memrise, Drops, Rosetta Stone, Busuu, Taalhammer, Google Translate/iTranslate, Surmai, Wanderer, Dashboards. Grund: pro Klasse die aussagekraeftigsten, Redundanz vermeiden.
