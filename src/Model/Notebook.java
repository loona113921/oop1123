package Model;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.notes;


public class Notebook extends Note {

    private List <Note> notes = new ArrayList<>();

    public Notebook(LocalDateTime dateTime, String description) {
        super(dateTime, description);
    }

    public void add(Note note) {
        notes.add(note);
    }
    public List<Note> getNotes() {
        return new ArrayList<>(notes);
    }
    public List<Note> getNotesForDay(LocalDateTime dateTime) {
        return notes.stream()
                .filter(note ->
                        note.getDateTime().toLocalDate().isEqual(dateTime.toLocalDate()))
                .sorted(Comparator.comparing(Note::getDateTime))
                .collect(Collectors.toList());
    }
    public List<Note> getNotesForWeek(LocalDateTime startOfWeek) {
        LocalDateTime endOfWeek = startOfWeek.plusWeeks(1);
        return notes.stream()
                .filter(note ->
                        !note.getDateTime().isBefore(startOfWeek) &&
                                !note.getDateTime().isAfter(endOfWeek))
                .sorted(Comparator.comparing(Note::getDateTime))
                .collect(Collectors.toList());
    }
    public void saveToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new
                FileWriter(fileName))) {
            for (Note note : notes) {
                writer.write(note.toString());
                writer.newLine();
            }
        }
    }
    public void loadFromFile(String fileName) throws IOException {

    }
//    public void clear(Note note) {
//        notes.clear();
//        try (BufferedReader reader = new BufferedReader(new
//                FileReader("File.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(": ", 2);
//                LocalDateTime dateTime =
//                        LocalDateTime.parse(parts[0]);
//                String description = parts[1];
//                notes.add(new Note(dateTime, description));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }}
    }


