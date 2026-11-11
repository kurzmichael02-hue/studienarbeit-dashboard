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
                {"Hallo", "\u0417\u0434\u0440\u0430\u0432\u0441\u0442\u0432\u0443\u0439\u0442\u0435", "Zdravstvujte"},
                {"Guten Morgen", "\u0414\u043e\u0431\u0440\u043e\u0435 \u0443\u0442\u0440\u043e", "Dobroe utro"},
                {"Tschuess", "\u041f\u043e\u043a\u0430", "Poka"},
                {"Danke", "\u0421\u043f\u0430\u0441\u0438\u0431\u043e", "Spasibo"},
                {"Bitte", "\u041f\u043e\u0436\u0430\u043b\u0443\u0439\u0441\u0442\u0430", "Pozhalujsta"},
                {"Ja", "\u0414\u0430", "Da"},
                {"Nein", "\u041d\u0435\u0442", "Net"},
                {"Entschuldigung", "\u0418\u0437\u0432\u0438\u043d\u0438\u0442\u0435", "Izvinite"},
                {"Eins", "\u041e\u0434\u0438\u043d", "Odin"},
                {"Zwei", "\u0414\u0432\u0430", "Dva"},
                {"Drei", "\u0422\u0440\u0438", "Tri"},
                {"Wasser", "\u0412\u043e\u0434\u0430", "Voda"},
                {"Essen", "\u0415\u0434\u0430", "Jeda"},
                {"Bahnhof", "\u0412\u043e\u043a\u0437\u0430\u043b", "Vokzal"},
                {"Toilette", "\u0422\u0443\u0430\u043b\u0435\u0442", "Tualet"},
                {"Was kostet das", "\u0421\u043a\u043e\u043b\u044c\u043a\u043e \u044d\u0442\u043e \u0441\u0442\u043e\u0438\u0442", "Skolko eto stoit"},
        });

        lektionMit("Japanisch", "Erste Woerter", new String[][]{
                {"Hallo", "\u3053\u3093\u306b\u3061\u306f", "Konnichiwa"},
                {"Guten Morgen", "\u304a\u306f\u3088\u3046", "Ohayou"},
                {"Tschuess", "\u3055\u3088\u3046\u306a\u3089", "Sayounara"},
                {"Danke", "\u3042\u308a\u304c\u3068\u3046", "Arigatou"},
                {"Ja", "\u306f\u3044", "Hai"},
                {"Nein", "\u3044\u3044\u3048", "Iie"},
                {"Entschuldigung", "\u3059\u307f\u307e\u305b\u3093", "Sumimasen"},
                {"Eins", "\u4e00", "Ichi"},
                {"Zwei", "\u4e8c", "Ni"},
                {"Drei", "\u4e09", "San"},
                {"Wasser", "\u6c34", "Mizu"},
                {"Essen", "\u305f\u3079\u3082\u306e", "Tabemono"},
                {"Bahnhof", "\u99c5", "Eki"},
                {"Toilette", "\u30c8\u30a4\u30ec", "Toire"},
                {"Wo ist", "\u3069\u3053\u3067\u3059\u304b", "Doko desu ka"},
                {"Wie viel", "\u3044\u304f\u3089", "Ikura"},
        });

        lektionMit("Koreanisch", "Erste Woerter", new String[][]{
                {"Hallo", "\uc548\ub155\ud558\uc138\uc694", "Annyeonghaseyo"},
                {"Guten Morgen", "\uc88b\uc740 \uc544\uce68", "Joeun achim"},
                {"Tschuess", "\uc548\ub155\ud788 \uac00\uc138\uc694", "Annyeonghi gaseyo"},
                {"Danke", "\uac10\uc0ac\ud569\ub2c8\ub2e4", "Gamsahamnida"},
                {"Ja", "\ub124", "Ne"},
                {"Nein", "\uc544\ub2c8\uc694", "Aniyo"},
                {"Entschuldigung", "\uc8c4\uc1a1\ud569\ub2c8\ub2e4", "Joesonghamnida"},
                {"Eins", "\ud558\ub098", "Hana"},
                {"Zwei", "\ub458", "Dul"},
                {"Drei", "\uc14b", "Set"},
                {"Wasser", "\ubb3c", "Mul"},
                {"Essen", "\uc74c\uc2dd", "Eumsik"},
                {"Bahnhof", "\uc5ed", "Yeok"},
                {"Toilette", "\ud654\uc7a5\uc2e4", "Hwajangsil"},
                {"Wo ist", "\uc5b4\ub514\uc608\uc694", "Eodiyeyo"},
                {"Wie viel", "\uc5bc\ub9c8\uc608\uc694", "Eolmayeyo"},
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
