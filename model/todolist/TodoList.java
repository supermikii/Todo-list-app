package model.todolist;

import model.exception.TooManyItemException;
import model.items.Item;
import model.items.RegularItem;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TodoList implements Iterable<Item> {

    private ArrayList<Item> todos;

    //HashMap
    // you can access the value from String, and store the TodoList class inside then use grab key to retrieve
    // then use for loop on the TodolistClass.getTodo for each item


    public TodoList() {
        todos = new ArrayList<>();
    }



    public void addTodo(Item a) throws TooManyItemException {
        if (getTodos().size() > 20) {
            throw new TooManyItemException();
        } else if (!getTodos().contains(a)) {
            todos.add(a);
//            assert (todos.size()<= 20); //Assertion
        }
    }

    public void checkOffTodo(Item a) {
        if (a.getDaily() == true) {
            a.setCompleted();
            if (a.isCompleted() == true) {
                todos.remove(a);
            }
        } else if (a.getUrgent()) {
            todos.remove(a);
        } else if (a.getDaily() == false) {
            todos.remove(a);
        }
    }

    public ArrayList<Item> getTodos() {
        return todos;
    }


    public String getDueDateOfTodoItem(Item item) {
        String c = "";
        for (Item i : todos) {
            if (item.equals(i)) {
                c = i.getDueDateInString();
            }
        }
        return c;
    }

    public String getNameTodoItem(int index) {
        String c = "";
        Item a = getTodos().get(index);
        c = a.getName();
        return c;
    }



    public ArrayList<Item> getOverDueItems() {
        ArrayList<Item> overDueItems = new ArrayList<>();
        for (Item a : todos) {
            a.setIfPastDue();
            if (a.getpastDue()) {
                overDueItems.add(a);
            }
        }
        return overDueItems;
    }


//    public void save() throws FileNotFoundException, UnsupportedEncodingException {
//        List<String> lines = new ArrayList<>();
//        for (Item a : todos) {
//            if (a.getDaily() == true) {
//                lines.add(a.getName() + " -/-/-" + a.getDueDateInString() + " -/-/-" + a.getNumNotCompleted());
//            }
//            if (a.getUrgent() == true) {
//                lines.add(a.getName() + " -/-/-" + a.getDueDateInString());
//            }
//        }
//
//        PrintWriter writer = new PrintWriter("input.txt", "UTF-8");
//        for (String b : lines) {
//            writer.println(b);
//        }
//        writer.close();
//    }
////
//// here it could throw parse exception if the loading cannot comprehend the date inside the save file
//    // but because we have a parse exception before we save so all saved files will be in the correct format
//
//    public void load() throws IOException, ParseException, IndexOutOfBoundsException {
//        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
////        if (lines.size() == 0) {throw new IndexOutOfBoundsException();
////        }
//
//        if (lines.size() < 1) {
//            getTodos();
//        } else {
//            for (String line : lines) {
////            ArrayList<String> a1 = new ArrayList<>();
////            a1.add(line);
//                List<String> list = new ArrayList<String>(Arrays.asList(line.split("-/-/-")));
//                Date dueDate = new SimpleDateFormat("yyyy/MM/dd").parse(list.get(2));
//                Item i1 = new RegularItem(list.get(1), dueDate); //convert string into date
//                todos.add(i1);
//            }
//
//        }
//    }

    @Override
    public Iterator<Item> iterator() {
        return todos.iterator();
    }
}


