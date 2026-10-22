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
- `/src` - Quellcode (kommt noch)

## Fortschritt
- [x] Daten sammeln (Umfrage, 12 Antworten)
- [x] Anforderungen
- [x] Use Cases
- [ ] Konzeption - aktuell
- [ ] Implementierung
- [ ] Test
