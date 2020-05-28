package model.interfaces;

import java.io.IOException;
import java.text.ParseException;

public interface Load {

    public void load() throws IOException, ParseException, IndexOutOfBoundsException;
}
