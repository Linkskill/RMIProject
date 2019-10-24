package Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe responsável por converter as datas do padrão americano \"mês-dia-ano\" para o padrão
 * brasileiro \"dia-mês-ano\".
 */
public class DateHandler {
    /**
     * Transforma uma String em uma data do padrão brasileiro do tipo LocalDate.
     * @param dateString contém a data a ser convertida
     * @return data do tipo LocalDate com o padrão brasileiro
     */
    public static LocalDate stringToDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d-M-yyyy"));
    }

    /**
     * Transforma uma data do tipo LocalDate em uma String com formatação brasileira (dia/mês/ano).
     * @param date data do tipo LocalDate no padrão americano
     * @return date String na formatação brasileira
     */
    public static String dateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        return date.format(formatter);
    }
}