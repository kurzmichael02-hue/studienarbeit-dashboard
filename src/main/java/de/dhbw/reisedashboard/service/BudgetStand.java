package de.dhbw.reisedashboard.service;

import java.math.BigDecimal;

/**
 * Der Budgetstand einer Reise: wie viel geplant war, wie viel schon ausgegeben wurde
 * und was davon uebrig ist. Ein eigenes kleines Objekt, damit die Oberflaeche die drei
 * Werte zusammen bekommt und nicht jede Zahl einzeln ausrechnen muss. "verbleibend" kann
 * negativ sein, wenn das Budget ueberzogen ist.
 */
public record BudgetStand(BigDecimal budget, BigDecimal ausgegeben, BigDecimal verbleibend) {
}
