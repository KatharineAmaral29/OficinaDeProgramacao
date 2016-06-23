package a371839.katharine.mynotes;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MyNotes extends Activity {
    private NoteDAO noteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        noteDAO = new NoteDAO(this);
        getNotes();
    }

    public void addNote(View view) {
        Calendar c = Calendar.getInstance();

        EditText titleText = (EditText) findViewById(R.id.editTextTitle);
        EditText contentText = (EditText) findViewById(R.id.editTextContent);
        Note note = new Note();
        note.setTitle(titleText.getText().toString());
        note.setContent(contentText.getText().toString());
        note.setData(c.getTime());
        noteDAO.create(note);

        getNotes();
    }

    private void getNotes(){
        List<Note> notes = noteDAO.list();
        Iterator<Note> it = notes.iterator();
        StringBuffer buffer = new StringBuffer();
        while (it.hasNext()) {
            Note note = it.next();
            buffer.append(note.toString());
            buffer.append("\n");
        }
        TextView list = (TextView) findViewById(R.id.textViewNotes);
        list.setText(buffer.toString());
    }
}
