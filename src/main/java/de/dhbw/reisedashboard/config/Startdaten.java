package de.dhbw.reisedashboard.config;

import de.dhbw.reisedashboard.model.Lektion;
import de.dhbw.reisedashboard.model.Sprache;
import de.dhbw.reisedashboard.model.Vokabel;
import de.dhbw.reisedashboard.repository.LektionRepository;
import de.dhbw.reisedashboard.repository.SpracheRepository;
import de.dhbw.reisedashboard.repository.VokabelRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Legt beim ersten Start ein paar Startdaten an, damit die App nicht leer ist: drei Sprachen
 * (Russisch, Japanisch, Koreanisch) mit je einer Lektion aus Reise-Grundwoertern. Laeuft nur,
 * wenn noch keine Sprache in der Datenbank steht, damit sich bei jedem Neustart nichts verdoppelt.
 * Die Umschrift folgt den Systemen, die auch der Bericht nennt (ISO 9, Hepburn, Revised Romanization).
 */
@Component
public class Startdaten implements ApplicationRunner {

    private final SpracheRepository spracheRepository;
    private final LektionRepository lektionRepository;
    private final VokabelRepository vokabelRepository;

    public Startdaten(SpracheRepository spracheRepository, LektionRepository lektionRepository,
                      VokabelRepository vokabelRepository) {
        this.spracheRepository = spracheRepository;
        this.lektionRepository = lektionRepository;
        this.vokabelRepository = vokabelRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (spracheRepository.count() > 0) {
            return;
        }

        lektionMit("Russisch", "Erste Woerter", new String[][]{
                {"Hallo", "Здравствуйте", "Zdravstvujte"},
                {"Danke", "Спасибо", "Spasibo"},
                {"Bitte", "Пожалуйста", "Pozhalujsta"},
                {"Ja", "Да", "Da"},
                {"Nein", "Нет", "Net"},
                {"Entschuldigung", "Извините", "Izvinite"},
                {"Wasser", "Вода", "Voda"},
                {"Bahnhof", "Вокзал", "Vokzal"},
        });

        lektionMit("Japanisch", "Erste Woerter", new String[][]{
                {"Hallo", "こんにちは", "Konnichiwa"},
                {"Danke", "ありがとう", "Arigatou"},
                {"Ja", "はい", "Hai"},
                {"Nein", "いいえ", "Iie"},
                {"Entschuldigung", "すみません", "Sumimasen"},
                {"Wasser", "水", "Mizu"},
                {"Bahnhof", "駅", "Eki"},
                {"Wo ist", "どこですか", "Doko desu ka"},
        });

        lektionMit("Koreanisch", "Erste Woerter", new String[][]{
                {"Hallo", "안녕하세요", "Annyeonghaseyo"},
                {"Danke", "감사합니다", "Gamsahamnida"},
                {"Ja", "네", "Ne"},
                {"Nein", "아니요", "Aniyo"},
                {"Entschuldigung", "죄송합니다", "Joesonghamnida"},
                {"Wasser", "물", "Mul"},
                {"Bahnhof", "역", "Yeok"},
                {"Wo ist", "어디예요", "Eodiyeyo"},
        });
    }

    private void lektionMit(String spracheName, String lektionName, String[][] woerter) {
        Sprache sprache = spracheRepository.save(new Sprache(spracheName));
        Lektion lektion = lektionRepository.save(new Lektion(sprache, lektionName));
        List<Vokabel> vokabeln = new java.util.ArrayList<>();
        for (String[] w : woerter) {
            vokabeln.add(new Vokabel(lektion, w[0], w[1], w[2]));
        }
        vokabelRepository.saveAll(vokabeln);
    }
}
