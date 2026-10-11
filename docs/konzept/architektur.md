# Architektur

Verantwortlich: Michael. Stand: Entwurf, Inhalt wird noch ausgefüllt. Grundlage: Anforderungen (`docs/recherche/anforderungen.md`) + Use Cases.

## Überblick
Kurz beschreiben, was das System ist: ein lokal auf dem Raspberry Pi betriebener Spring-Boot-Webserver, erreichbar im WLAN, funktioniert offline, ohne Account.

## Schichten (Spring Boot)
- Controller: nimmt die Anfragen vom Browser an (Klicks, Formulare)
- Service: die Logik (z.B. Reise anlegen, Lernfortschritt berechnen)
- Repository: Zugriff auf die Datenbank
- View (Thymeleaf + Bootstrap): die HTML-Seiten, die der Nutzer sieht

## Technik
- Backend: Java + Spring Boot
- Datenbank: H2 (dateibasiert, lokal)
- Betrieb: Raspberry Pi als Webserver im WLAN, offline

## Architektur-Diagramm
Kästchen-Diagramm: Browser -> Controller -> Service -> Repository -> H2, dazu Thymeleaf-Views, das Ganze auf dem Pi. Kommt als Bild (z.B. `architektur-diagramm.svg`).

## Offen
- Text ausformulieren
- Diagramm zeichnen
