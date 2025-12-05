package br.com.resenhasociocultural.apiresenha.features.attendance.builder;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceFilter;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public final class AttendanceFilterDtoBuilder {
    private String youthNameSubstring;
    private @PastOrPresent LocalDate date;
    private @PastOrPresent LocalDate initialDate;
    private @PastOrPresent LocalDate finalDate;

    private AttendanceFilterDtoBuilder() {
    }

    public static AttendanceFilterDtoBuilder anAttendanceFilterDto() {
        return new AttendanceFilterDtoBuilder();
    }

    public AttendanceFilterDtoBuilder withYouthNameSubstring(String youthNameSubstring) {
        this.youthNameSubstring = youthNameSubstring;
        return this;
    }

    public AttendanceFilterDtoBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public AttendanceFilterDtoBuilder withInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
        return this;
    }

    public AttendanceFilterDtoBuilder withFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
        return this;
    }

    public AttendanceFilter build() {
        return new AttendanceFilter(
            youthNameSubstring,
            date,
            initialDate,
            finalDate
        );
    }
}
