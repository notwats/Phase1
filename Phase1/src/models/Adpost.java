package models;

import java.time.LocalDate;
import java.util.HashMap;

public class Adpost extends Post{

    public HashMap<LocalDate, Integer> likesPerday = new HashMap<>();
    public HashMap<LocalDate, Integer> viewsPerday = new HashMap<>();

}
