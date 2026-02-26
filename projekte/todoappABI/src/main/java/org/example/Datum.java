package org.example;

public record Datum(int day,int month, int year) {

    public boolean smallerOrEqual(Datum other) {
        return this.year < other.year ||
               (this.year == other.year && this.month < other.month) ||
               (this.year == other.year && this.month == other.month && this.day < other.day) ||
                (this.year == other.year && this.month == other.month && this.day == other.day);
    }

    public String anzeigen() {
        return day + "." + month + "." + year;
    }
}
