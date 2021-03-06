package turmina.nazareh.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import turmina.nazareh.spring5recipeapp.commands.NotesCommand;
import turmina.nazareh.spring5recipeapp.domain.Notes;

@Component
public class NotesToNotesCommand implements Converter<Notes,NotesCommand> {

    @Override
    public NotesCommand convert(Notes source) {
        if (source == null)
             return null;

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setRecipeNotes(source.getRecipeNotes());

        return notesCommand;
    }
}
