# Anforderungen

aus der umfrage abgeleitet (12 antworten, anonym). rohdaten liegen im Studium-ordner (Umfrage_Rohdaten_1-12.xlsx). priorisierung aus den bewertungen + den freitext-antworten.

## kurz zur stichprobe
meistens vielreisende (8x mehrmals im jahr), altersmaessig vor allem 18-25. sprache vor der reise lernen: 3x immer, 6x manchmal, 3x nie. genutzt werden vor allem Google Maps + Notizen-app fuer die reise, Duolingo fuers lernen. 11 von 12 wuerden so eine app grundsaetzlich nutzen.

## wie wichtig sind die funktionen (schnitt 1-5, n=12)
| funktion | schnitt |
|---|---|
| reiseziele | 4,3 |
| offline | 4,2 |
| packlisten | 3,6 |
| tagesplaene | 3,5 |
| budget | 3,4 |
| daten lokal | 3,4 |
| vokabeln | 3,1 |
| reisephrasen | 3,1 |
| lernfortschritt | 3,1 |
| notizen | 2,8 |
| quiz | 2,5 |

vokabeln/reisephrasen liegen im schnitt nur bei 3,1, aber das liegt an den 3 leuten die nie sprache lernen (die haben da 1 gegeben). bei den anderen sind reisephrasen klar wichtig, und im freitext ist der sprachteil ein hauptargument. also nicht nur am schnitt festmachen.

## was die leute im freitext sagen
- hauptschmerz, kam mehrfach: alles auf mehrere apps verteilt, staendig wechseln. "reiseplan in der einen, sprache in der anderen, und offline geht duolingo kaum".
- luecke bei nicht-europaeischen sprachen: "duolingo bringt mir fuer russisch oder japanisch kaum was praktisches" / "die meisten apps koennen nur die standard-europa-sprachen richtig". genau unser scope.
- wunsch: reisephrasen nach situation (flughafen, restaurant, taxi) statt random vokabeln.
- wunsch: offline + alles an einem ort + ohne account.
- kritik an sprach-apps: push-spam, kosten, grammatik falsch oder "derbe trocken", zu viel spielkram.
- zusatzwuensche: gemeinsam planen, reiseplaene/dateien importieren.

## funktionale anforderungen (priorisiert)
- muss: reiseziele verwalten, reisephrasen (nach situation), vokabeln lernen, offline nutzbar, daten lokal ohne account
- soll: tagesplaene, packlisten, budget, lernfortschritt
- kann: notizen, quiz, gemeinsam planen, reiseplaene/dateien importieren

## nicht-funktionale anforderungen
- offlinefaehig (laut daten eins der wichtigsten)
- daten lokal, kein account noetig
- kein push-spam, nicht trocken, praktische saetze statt random vokabeln
- guenstig
- sprach-fokus auf die schwierigen (russisch, japanisch, dazu chinesisch/koreanisch), wo die bestehenden apps schwach sind

## zielsprachen
mehrfach genannt: russland, japan, china, korea (genau die luecke), dazu italien, spanien, frankreich, norwegen. passt zum geplanten RU/JA/KO-fokus.

## offen / limitationen
- kleine stichprobe (12), nicht repraesentativ (ueber SurveyCircle gestreut). in der arbeit als limitation nennen.
- einer meinte die pflichtfragen zwingen zu antworten wo man keine passende hat. auch als limitation.
- naechster schritt: use cases + konzept (architektur, datenmodell).
