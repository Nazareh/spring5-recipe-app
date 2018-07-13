package turmina.nazareh.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import turmina.nazareh.spring5recipeapp.commands.NotesCommand;
import turmina.nazareh.spring5recipeapp.domain.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand,Notes> {

    @Override
    public Notes convert(NotesCommand source) {
        if(source == null)
            return null;

        Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());

        return notes;
    }
}
