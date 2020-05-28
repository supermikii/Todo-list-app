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

public class TodoListManager extends Observable {

    private HashMap<String, TodoList> todos;

    public TodoListManager() {
        todos = new HashMap<>();
    }


    public void addTodoItemInManager(String name, Item item) throws TooManyItemException {
        TodoList todoList = todos.get(name);
        todoList.addTodo(item);
//        getTodos().get(name).addTodo(item);

    }


    public void putUser(String username, TodoList todoList) {
        if (!todos.containsKey(username)) {
            todos.put(username, todoList);
        }
    }

    public HashMap<String, TodoList> getTodos() {
        return todos;
    }


    public void checkOffTodo(String username, Item item) {
        TodoList items = todos.get(username);
        items.checkOffTodo(item);
    }


    public TodoList getTodoList(String username) {
        return todos.get(username);
    }


    public ArrayList<Item> getOverDueItems(String user) {
        TodoList todoList = todos.get(user);
        return todoList.getOverDueItems();
    }


    public String getDueDateOfItem(String user, Item item) {
        TodoList todoList = todos.get(user);
        return todoList.getDueDateOfTodoItem(item);
    }


//    public void loadUser(User user) throws IOException {
//        List<String> lines = Files.readAllLines(Paths.get("users.txt"));
//        if (lines.size() < 1) {
//            user.createUser();
//        } else {
//            for (String line : lines) {
//                List<String> list = splitOnSpace(line);
//                if (list.get(0).equals(user.getUserName()) && list.get(1).equals(user.getPassword())) {
//                    getTodoList(user);
//                   System.out.println("Login yay!");
//                   System.out.print("We won");
//
//                }
//            }
//        }
//    }


//    //Require: user has successfully login producing true
//    //Modify:
//    //Effect: print list of Item names + dueDate + location of each item
//
//    public void printItemsOnUser(User user) {
//        TodoList items = todos.get(user);
//        for (Item i : items) {
//            if (i.getDaily() == true) {
//                System.out.println(i.getName() + " is due on " + i.getDueDateInString() + " at "
//                        + i.getLocation().getNameLocation());
//            } else if (i.getUrgent() == true) {
//                System.out.println(i.getName() + " is due on " + i.getDueDateInString() + " at "
//                        + i.getLocation().getNameLocation());
//            }
//        }
//        System.out.println("There is no items added at the moment");
//    }


//public static void main (String[] args) throws IOException {
//       TodoListManager todoListManager =  new TodoListManager();
//       User user = new User("Micky", "123456");
//       todoListManager.loadUser(user);
//}


    public void save() throws IOException {
        List<String> superLine = new ArrayList<>();
//        String line1;
        String line2;
        for (String key : todos.keySet()) {
            TodoList a = todos.get(key);
            String listString = "";
            for (Item z : a.getTodos()) {
                List<String> lines = new ArrayList<>();
                line2 = ("-/-/-@" + z.getName() + "@" + z.getDueDateInString());
                lines.add(line2);
                for (String s : lines) {
                    listString = s + listString;
                }
            }
           // String finalString = line1 + listString;
            String finalString = key + listString;
            superLine.add(finalString);
        }
        saveIntoFile(superLine);
    }


//    public void saveNewUser(String input) throws ParseException, TooManyItemException, IOException {
//        load();
//
//    }


    //EFFECT:for testing case only
    public void saveIntoFile(List<String> superLine) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter printWriter = new PrintWriter("data/input.txt", "UTF-8");
        for (String b : superLine) {
            printWriter.println(b);
        }
        printWriter.close();
    }


//    public createUserAndSave(String input) throws ParseException, TooManyItemException, IOException {
//        load();
//        TodoList todoList = todos.get(input);
//    }


    public void load() throws IOException, ParseException, TooManyItemException {
        List<String> lines = Files.readAllLines(Paths.get("data/input.txt"));
        for (String line : lines) {
//                if (!line.contains("@")) {
//                    TodoList todoList1 = new TodoList();
//                    List<String> list = new ArrayList<String>(Arrays.asList(line.split("-/-/-")));
//                    String username = list.get(0);
//                    putUser(username,todoList1);
//                } else {
            List<String> list = new ArrayList<String>(Arrays.asList(line.split("-/-/-")));
            String username = list.get(0);
            TodoList todoList = new TodoList();
            for (int i = 1; i < list.size(); i++) {
                String line2 = list.get(i);
                List<String> list2 = new ArrayList<String>(Arrays.asList(line2.split("@")));
                Date dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(list2.get(2));
                Item i1 = new RegularItem(list2.get(1), dueDate); //convert string into date
                todoList.addTodo(i1);
            }
            putUser(username, todoList);
        }
    }
}
