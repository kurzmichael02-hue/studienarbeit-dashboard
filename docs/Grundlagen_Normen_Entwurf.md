# Grundlagen: Normen und Literatur fuer die Kriterien (Entwurf)

Stand 08.10.2026. Entwurf fuer das Grundlagenkapitel, damit die Vergleichskriterien wissenschaftlich fundiert sind statt frei erfunden. Die Inhalte stammen aus serioesen Quellen zu den Normen. Den genauen Wortlaut und die Seitenzahlen muessen wir am Original der Normen pruefen (ueber die DHBW-Bibliothek bzw. Beuth/DIN), bevor wir final zitieren.

## 1. Softwarequalitaet nach ISO/IEC 25010:2023
Die Norm beschreibt ein Produktqualitaetsmodell mit neun Qualitaetsmerkmalen (2. Ausgabe 2023; loest die Fassung von 2011 ab). Gegenueber 2011 wurde erweitert: aus acht wurden neun Merkmale, "Usability" heisst jetzt "Interaction capability", "Portability" heisst jetzt "Flexibility", und "Safety" kam neu dazu.

Die neun Merkmale (mit Unter-Merkmalen):
1. Functional suitability (funktionale Eignung): functional completeness, correctness, appropriateness
2. Performance efficiency: time behavior, resource utilization, capacity
3. Compatibility: co-existence, interoperability
4. Interaction capability (frueher Usability): appropriateness recognizability, learnability, operability, user error protection, user engagement, inclusivity, user assistance, self-descriptiveness
5. Reliability: faultlessness, availability, fault tolerance, recoverability
6. Security: confidentiality, integrity, non-repudiation, accountability, authenticity, resistance
7. Maintainability: modularity, reusability, analyzability, modifiability, testability
8. Flexibility (frueher Portability): adaptability, installability, replaceability, scalability
9. Safety: operational constraint, risk identification, fail-safe behavior, hazard warning, safe integration

Fuer uns am wichtigsten: functional suitability (deckt die Funktionen Reise/Sprache), interaction capability (Bedienbarkeit), reliability/availability (laeuft es stabil offline), security/confidentiality (lokale Datenhaltung, Datenschutz), flexibility (laeuft es auf dem Pi).

## 2. Gebrauchstauglichkeit nach ISO 9241-11:2018
Die Norm definiert Usability als das Ausmass, in dem ein System von bestimmten Nutzern in einem bestimmten Nutzungskontext genutzt werden kann, um bestimmte Ziele zu erreichen, und zwar mit:
- Effektivitaet (Genauigkeit und Vollstaendigkeit der Zielerreichung),
- Effizienz (dafuer aufgewendete Ressourcen, z.B. Zeit), und
- Zufriedenheit (Komfort und Akzeptanz).

Das ist die Grundlage fuer unseren Usability-Test in der Validierung: Effektivitaet ueber die Aufgaben-Erfolgsquote, Effizienz ueber Zeit/Fehler, Zufriedenheit ueber einen Fragebogen.

## 3. Nielsen und der SUS-Fragebogen
- Nielsen nennt fuenf Usability-Komponenten: Erlernbarkeit, Effizienz, Einpraegsamkeit, Fehler, Zufriedenheit (Nielsen 1993). Nuetzlich, um die Bestands-Apps kritisch zu bewerten.
- Die heuristische Evaluation (Nielsen 1994) ist eine Experten-Methode, um Usability-Probleme zu finden, ergaenzend zum Nutzer-Test.
- Fuer die Zufriedenheit nutzen wir im Test die System Usability Scale (SUS), einen etablierten standardisierten Fragebogen (Brooke 1996).

## 4. Anforderungserhebung nach Pohl
Fuer die Erhebung der Nutzeranforderungen (Umfrage + Interviews) stuetzen wir uns auf die Standardliteratur zum Requirements Engineering (Pohl 2010): Interviews (offen/geschlossen) fuer Tiefe, Fragebogen/Umfrage fuer Breite (viele Teilnehmer, guenstig, aber Qualitaet haengt am Design der Fragen).

## 5. Zuordnung: unsere Kriterien zu den Quellen
- Reise-Funktionen, Sprach-Funktionen, Kombination, Zielsprachen-Abdeckung -> functional suitability (ISO 25010:2023)
- Offline-Faehigkeit -> reliability/availability + Nutzung ohne Internet im Nutzungskontext (ISO 25010:2023; ISO 9241-11:2018)
- Datenhaltung lokal / Datenschutz -> security/confidentiality (ISO 25010:2023)
- Lauffaehigkeit auf dem Pi -> flexibility/installability (ISO 25010:2023)
- Bedienbarkeit / Usability (Bestands-Apps und Prototyp) -> interaction capability (ISO 25010:2023); Usability (ISO 9241-11:2018); Nielsen; SUS (Brooke 1996)
- Kosten / Lizenz -> kein ISO-Merkmal, sondern ein praktisch-wirtschaftliches Kriterium (passt zur wirtschaftlichen Bewertung).

## Quellenverzeichnis (zu pruefen + ggf. ergaenzen)
- ISO/IEC 25010:2023: Systems and software engineering - Systems and software Quality Requirements and Evaluation (SQuaRE) - Product quality model. 2. Ausgabe, 2023.
- ISO 9241-11:2018: Ergonomics of human-system interaction - Part 11: Usability: Definitions and concepts. 2. Ausgabe, 2018.
- Nielsen, J. (1993): Usability Engineering. Academic Press, Boston.
- Nielsen, J. (1994): Heuristic Evaluation. In: Nielsen, J.; Mack, R. L. (Hrsg.): Usability Inspection Methods. Wiley, New York.
- Brooke, J. (1996): SUS - A quick and dirty usability scale. In: Jordan, P. W. et al. (Hrsg.): Usability Evaluation in Industry. Taylor & Francis, London.
- Pohl, K. (2010): Requirements Engineering. Fundamentals, Principles, and Techniques. Springer, Berlin/Heidelberg.

## Kurz erklaert (zum selber sagen, falls Juergen fragt)
- ISO 9241-11: Usability heisst, ob der Nutzer sein Ziel erreicht, und zwar effektiv (schafft er es ueberhaupt), effizient (wie schnell, mit wie viel Aufwand) und zufrieden.
- ISO 25010: ein internationaler Standard, der Softwarequalitaet in Merkmale einteilt (z.B. Funktionalitaet, Bedienbarkeit, Zuverlaessigkeit, Sicherheit). Wir nehmen daraus die Merkmale, die fuer unser Tool zaehlen.
Das sind nur die Kurzfassungen zum Erklaeren. Die Normen selbst sind ganze Dokumente, nicht nur diese zwei Saetze:
- ISO 9241-11 dreht sich im Kern genau um diese Definition (effektiv, effizient, zufrieden) plus Erlaeuterungen dazu. Der eine Satz ist also schon ziemlich die Essenz.
- ISO 25010 ist groesser: ein Katalog aus 9 Qualitaetsmerkmalen mit Unter-Merkmalen (komplett oben unter Punkt 1 aufgelistet). Der eine Satz ist nur die Kurzfassung. Fuer die Arbeit ziehen wir aus 25010 nur die paar Merkmale heraus, die zu unserem Tool passen, mehr brauchen wir nicht.

## Ehrlich: was noch zu tun ist
- Die Normen ISO/IEC 25010:2023 und ISO 9241-11:2018 im Original beschaffen (DHBW-Bibliothek / Beuth) und Wortlaut + Seiten fuer die Zitate pruefen. Normen stehen nicht frei im Netz, die obigen Inhalte stammen aus serioesen Sekundaerquellen.
- Zitierweise mit dem festlegen, was die DHBW verlangt (z.B. deutsche Zitierweise oder IEEE), dann einheitlich durchziehen.
- Nielsen/Brooke/Pohl ueber die Bibliothek oder Google Scholar beschaffen.
