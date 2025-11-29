package br.com.resenhasociocultural.apiresenha.factories;

import br.com.resenhasociocultural.apiresenha.factories.dto.AttendanceFactoryResult;
import br.com.resenhasociocultural.apiresenha.features.attendance.Attendance;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static br.com.resenhasociocultural.apiresenha.features.attendance.builder.AttendanceBuilder.anAttendance;

public class AttendanceFactory {

    public static AttendanceFactoryResult generateAttendancesWithIds(List<Youth> youths) {
        var result = generateAttendances(youths);
        addIds(result);
        return result;
    }

    public static AttendanceFactoryResult generateAttendancesWithIds(List<Youth> youths, int absencesAmount, int excusedAbsencesAmount) {
        var result = generateAttendances(youths, absencesAmount, excusedAbsencesAmount);
        addIds(result);
        return result;
    }

    private static void addIds(AttendanceFactoryResult result) {
        for (long i = 0; i < result.generatedAttendances().size(); i++) {
            var attendance = result.generatedAttendances().get((int) i);
            attendance.setId(i + 1);
        }
    }

    public static AttendanceFactoryResult generateAttendances(List<Youth> youths) {
        return generateAttendances(youths, 0, 0);
    }

    public static AttendanceFactoryResult generateAttendances(List<Youth> youths, int absencesAmount, int excusedAbsencesAmount) {
        if (absencesAmount + excusedAbsencesAmount > youths.size()) {
            throw new IllegalArgumentException("The somatory of absence and excused absence can't be bigger than the amount of youths.");
        }

        List<Attendance> generatedAttendances = youths.stream().map(youth ->
            anAttendance()
                .withoutId()
                .withYouth(youth)
                .withStatus(AttendanceStatus.PRESENT)
                .build()
        ).collect(Collectors.toCollection(ArrayList::new));

        List<Integer> randomIndexes = getShuffledIndexes(youths.size());
        List<Integer> absentIndexes = randomIndexes.subList(0, absencesAmount);
        List<Integer> excusedIndexes = randomIndexes.subList(absencesAmount, absencesAmount + excusedAbsencesAmount);

        List<Attendance> statusAbsentAttendances = new ArrayList<>();
        List<Attendance> statusExcusedAbsenceAttendances = new ArrayList<>();

        for (Integer index : absentIndexes) {
            Attendance attendance = generatedAttendances.get(index);
            attendance.setAttendanceStatus(AttendanceStatus.ABSENT);
            statusAbsentAttendances.add(attendance);
        }

        for (Integer index : excusedIndexes) {
            Attendance attendance = generatedAttendances.get(index);
            attendance.setAttendanceStatus(AttendanceStatus.EXCUSED_ABSENCE);
            attendance.setAbsenceExcuse("Excuse for youth sequence " + index);
            statusExcusedAbsenceAttendances.add(attendance);
        }

        List<Attendance> statusPresentAttendances = generatedAttendances.stream()
            .filter(a -> a.getAttendanceStatus() == AttendanceStatus.PRESENT)
            .toList();

        return new AttendanceFactoryResult(
            generatedAttendances,
            statusPresentAttendances,
            statusAbsentAttendances,
            statusExcusedAbsenceAttendances
        );
    }

    private static List<Integer> getShuffledIndexes(int size) {
        List<Integer> indexes = IntStream.range(0, size)
            .boxed()
            .collect(Collectors.toList());
        Collections.shuffle(indexes);
        return indexes;
    }
}
