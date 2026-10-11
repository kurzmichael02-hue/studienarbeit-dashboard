# ER-Datenmodell

Verantwortlich: Schayan. Stand: zu erstellen. Grundlage: Anforderungen (`docs/recherche/anforderungen.md`) + Use Cases (`docs/konzept/use-cases.md`).

Ziel: festlegen, welche Daten die App speichert und wie sie zusammenhängen, am Ende als ER-Diagramm.

## Entitäten (Startvorschlag, bitte ergänzen / anpassen)
Reisebereich:
- Reise (Titel, Zeitraum)
- Reiseziel (gehört zu einer Reise)
- Tagesplan (gehört zu einer Reise)
- Packliste / Packlisten-Eintrag
- Ausgabe (fürs Budget, gehört zu einer Reise)
- Notiz

Sprachbereich:
- Sprache
- Vokabel (gehört zu einer Sprache)
- Reisephrase (gehört zu einer Sprache + Situation: Flughafen, Restaurant, Taxi)
- Lernfortschritt (pro Vokabel/Phrase)

## Beziehungen (ausarbeiten)
Beispiele:
- eine Reise hat viele Reiseziele
- eine Reise hat viele Tagespläne, Packlisten-Einträge, Ausgaben, Notizen
- eine Sprache hat viele Vokabeln und viele Reisephrasen
- zu jeder Vokabel/Phrase gibt es einen Lernfortschritt

## Attribute
Pro Entität die Felder festlegen (z.B. Reise: id, titel, startdatum, enddatum).

## ER-Diagramm
Als Diagramm zeichnen (z.B. draw.io). Datei hier ablegen.

## Offen
- Entitäten + Attribute final festlegen
- Beziehungen (1:n, n:m) klären
- Diagramm zeichnen
