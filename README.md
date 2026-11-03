# Studienarbeit - Offlinefähiges Reise- und Sprachlern-Dashboard

DHBW Mannheim, Studiengang Informatik. Große Studienarbeit, 5. und 6. Semester (2026/27).

## Worum geht es
Ein lokal auf einem Raspberry Pi betriebenes, offlinefähiges Dashboard, das Reiseplanung und Sprachenlernen in einer Webanwendung verbindet. Konzeption, prototypische Umsetzung und Evaluation. Exemplarische Zielregionen: Russland, Japan, Korea.

## Team
- Michael Kurz
- Schayan Goudarzi
- Betreuer: Jürgen Schultheis (Kontakt über Discord)

Aufgabenteilung:
- Michael: Systemarchitektur, Backend (Controller, Services, API), Geschäftslogik, Security
- Schayan: Frontend (Thymeleaf + Bootstrap), Datenbankanbindung (Entities, Repositories, H2), Raspberry-Pi-Einrichtung, Deployment, Usability-Tests
- Gemeinsam: Konzept, Marktanalyse, Umfrage, Wireframes, Evaluation, Dokumentation

## Tech-Stack
- Backend: Java + Spring Boot
- Frontend: Thymeleaf + Bootstrap
- Datenbank: H2 (dateibasiert, lokal persistent)
- Betrieb: Raspberry Pi als lokaler Webserver im WLAN, offline

## Struktur
- `/docs/recherche` - Marktanalyse, Literaturrecherche, Anforderungen
- `/docs/konzept` - Use Cases, Architektur, Datenmodell, Wireframes
- `/docs/bericht` - der Bericht (`main.tex` als Quelle, `Studienarbeit.pdf` als aktueller Stand)
- `/src` - Spring-Boot-Anwendung

## Arbeitsweise
- Zwei Branches: `dev` (da wird gearbeitet) und `main` (stabil, immer lauffähig, da liest der Betreuer).
- Code geht direkt auf `dev`. Vor dem Push `git pull`, dann `mvn test`, dann pushen. Ist ein Stand fertig und getestet, geht er als Pull Request von `dev` nach `main`, der andere schaut kurz drüber.
- Der Bericht liegt direkt auf `main`. Overleaf hat für unsere Größe nicht mehr gereicht (Compile-Timeout im Free-Plan), deshalb wird lokal mit `latexmk -pdf main.tex` gebaut (MiKTeX oder TeX Live, biber muss dabei sein).
- Pro Funktion erst die Oberfläche (Schayan), dann die Logik dahinter (Michael).
- Bootstrap und Icons kommen als WebJars aus dem Jar, kein CDN, weil die App offline auf dem Pi läuft.

## Fortschritt
- [x] Umfrage (12 Antworten) + Anforderungen
- [x] Use Cases
- [x] Konzeption (Architektur, Datenmodell, Wireframes)
- [ ] Implementierung - aktuell
- [ ] Test / Evaluation
