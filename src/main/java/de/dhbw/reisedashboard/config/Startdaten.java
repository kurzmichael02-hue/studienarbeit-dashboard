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
                {"Guten Morgen", "Доброе утро", "Dobroe utro"},
                {"Tschuess", "Пока", "Poka"},
                {"Danke", "Спасибо", "Spasibo"},
                {"Bitte", "Пожалуйста", "Pozhalujsta"},
                {"Ja", "Да", "Da"},
                {"Nein", "Нет", "Net"},
                {"Entschuldigung", "Извините", "Izvinite"},
                {"Eins", "Один", "Odin"},
                {"Zwei", "Два", "Dva"},
                {"Drei", "Три", "Tri"},
                {"Wasser", "Вода", "Voda"},
                {"Essen", "Еда", "Jeda"},
                {"Bahnhof", "Вокзал", "Vokzal"},
                {"Toilette", "Туалет", "Tualet"},
                {"Was kostet das", "Сколько это стоит", "Skolko eto stoit"},
        });

        lektionMit("Japanisch", "Erste Woerter", new String[][]{
                {"Hallo", "こんにちは", "Konnichiwa"},
                {"Guten Morgen", "おはよう", "Ohayou"},
                {"Tschuess", "さようなら", "Sayounara"},
                {"Danke", "ありがとう", "Arigatou"},
                {"Ja", "はい", "Hai"},
                {"Nein", "いいえ", "Iie"},
                {"Entschuldigung", "すみません", "Sumimasen"},
                {"Eins", "一", "Ichi"},
                {"Zwei", "二", "Ni"},
                {"Drei", "三", "San"},
                {"Wasser", "水", "Mizu"},
                {"Essen", "たべもの", "Tabemono"},
                {"Bahnhof", "駅", "Eki"},
                {"Toilette", "トイレ", "Toire"},
                {"Wo ist", "どこですか", "Doko desu ka"},
                {"Wie viel", "いくら", "Ikura"},
        });

        lektionMit("Koreanisch", "Erste Woerter", new String[][]{
                {"Hallo", "안녕하세요", "Annyeonghaseyo"},
                {"Guten Morgen", "좋은 아침", "Joeun achim"},
                {"Tschuess", "안녕히 가세요", "Annyeonghi gaseyo"},
                {"Danke", "감사합니다", "Gamsahamnida"},
                {"Ja", "네", "Ne"},
                {"Nein", "아니요", "Aniyo"},
                {"Entschuldigung", "죄송합니다", "Joesonghamnida"},
                {"Eins", "하나", "Hana"},
                {"Zwei", "둘", "Dul"},
                {"Drei", "셋", "Set"},
                {"Wasser", "물", "Mul"},
                {"Essen", "음식", "Eumsik"},
                {"Bahnhof", "역", "Yeok"},
                {"Toilette", "화장실", "Hwajangsil"},
                {"Wo ist", "어디예요", "Eodiyeyo"},
                {"Wie viel", "얼마예요", "Eolmayeyo"},
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
