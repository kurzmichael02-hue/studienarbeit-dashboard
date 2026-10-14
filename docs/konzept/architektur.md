# Grundgerüst

1. Client (Webbrowser)
- Benutzer öffnet Dashboard auf Laptop etc.

2. Webanwendung (Java und Spring Boot)
- Spring Boot verarbeitet Anfragen, führt Anwendungslogik aus und stellt benötigte Daten bereit

3. Frontend (Thymeleaf und Bootstrap)
- Thymeleaf erzeugt HTML-Seiten auf dem Server
- Bootstrap sorgt für die Gestaltung der Benutzeroberfläche

4. Datenbank H2
- H2 speichert die Daten lokal (Reiseziele, Tagespläne, Packlisten, Vokabeln, Lernfortschritt)


# Softwarearchitektur
- MVC (Model-View-Controller)


# Typischer Ablauf
1. Dashboard im Browser öffnen
2. Formular ausfüllen und "Speichern"
3. Browser sendet Eingaben an Spring Boot
4. Controller nimmt die Anfrage entgegen
5. Anwendungslogik verarbeitet und validiert die Eingaben
6. Datenzugriff speichert das Reiseziel in der H2-Datenbank
7. Spring Boot erzeugt die aktualisierte
8. Browser zeigt das gespeicherte Reiseziel an (View)

# Abbildung
       Laptop / Smartphone / Tablet
                    |
                    | HTTP-Anfrage
                    v
          +----------------------+
          |      Raspberry Pi    |
          |                      |
          |   Spring Boot        |
          |   +--------------+   |
          |   | Controller   |   |
          |   +--------------+   |
          |          |           |
          |   +--------------+   |
          |   | Fachlogik    |   |
          |   +--------------+   |
          |          |           |
          |   +--------------+   |
          |   | H2-Datenbank  |   |
          |   +--------------+   |
          |                      |
          | Thymeleaf + Bootstrap|
          +----------------------+
                    |
                    | HTML-Antwort
                    v
             Webbrowser des
                Benutzers


# Schichtenarchitektur Zusammenhang

+--------------------------------+
| Presentation Layer             |
| Controller + Thymeleaf         |
+--------------------------------+
                |
                v
+--------------------------------+
| Business Logic Layer           |
| Services                       |
+--------------------------------+
                |
                v
+--------------------------------+
| Data Access Layer              |
| Repositories                   |
+--------------------------------+
                |
                v
+--------------------------------+
| Database Layer                 |
| H2                             |
+--------------------------------+



# Rolle Raspberry Pi
- laufen der Spring-Boot-Anwendung
- stellt ihre Webseiten im lokalen Netzwerk bereit
- anderes Gerät öffnet das Dashboard über seinen Webbrowser
- Datenbank liegt lokal auf Raspberry Pi
- Betrieb soll keine Internetverbindung benötigen

               Lokales WLAN
              ohne Internet
                    |
          +---------+---------+
          |                   |
          v                   v
    Raspberry Pi          Smartphone
          |
          +-- Spring Boot
          |
          +-- Thymeleaf
          |
          +-- H2-Datenbank



# Jetziges Architekturdiagramm
![alt text](image.png)