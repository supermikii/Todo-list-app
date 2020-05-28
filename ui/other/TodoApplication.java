//package ui.other;
//
//import model.exception.PastDueException;
//import model.exception.TooManyItemException;
//import model.items.Item;
//import model.items.Location;
//import model.items.RegularItem;
//import model.items.UrgentItem;
//import model.todolist.TodoList;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
////learn to save the item as urgent item or regular item
////learn to pull the item as urgent item or regular item and construct it as urgent item or regular item
//// The reason why the program kept printing duplicating items is that it goes through load each time it finishes a
////function in 1,2,3,4,5 and because I used a while loop so it load more from the input onto the app
//public class TodoApplication {
//    Scanner scanner;
//    TodoList task1 = new TodoList();
//    TodoList completedItem = new TodoList();
//
//    public TodoApplication() throws IOException, ParseException {
//        scanner = new Scanner(System.in);
//        String operation = "";
//        ArrayList<Location> locations = new ArrayList<>();
//
//
//
//
//
//        ArrayList<Item> passDueItems = new ArrayList<>(); //case 1
//        // If the type is defined like this then I can only use method of Type
//        // case 2 if i define passDueItem as TodoList then I can use method of TodoList but I cannot use contains, add
//        task1.load();
//        while (true) {
//
//
//
//            System.out.println("Welcome to Task Manager!");
//            System.out.println("Please choose from one of the following");
//            System.out.println("[1] To add a task");
//            System.out.println("[2] To check off a completed task");
//            System.out.println("[3] Display all on-going tasks");
//            System.out.println("[4] Display all completed tasks");
//            System.out.println("[5] Display all pass-due tasks");
//            System.out.println("[6] Display all urgent tasks");
//            System.out.println("[7] Exit to Save");
//            System.out.println("[8] Add a location");
//            System.out.println("[9] Look up items by location");
//
//
//            operation = this.scanner.nextLine();
//
//            if (operation.equals("1")) {
//                System.out.println("Do you want to add a normal task or urgent task?");
//                System.out.println("[1] Urgent Task-- ONE TIME ONLY");
//                System.out.println("[2] Normal Task-- DAILY ROUTINE REPETITIVE");
//                operation = this.scanner.nextLine();
//                if (operation.equals("1")) {
//                    Item a1 = new UrgentItem();
//                    a1.setToRegularOrUrgent("1");
//                    System.out.println("What do you need to do?");
//                    operation = this.scanner.nextLine();
//                    a1.setName(operation);
//                    System.out.println("When is the due Date? (Please Enter in [yyyy/MM/dd] format)");
//                    operation = this.scanner.nextLine();
//                    while (operation.length() != 10) {
//                        try {
//                            a1.setDueDate(operation);
//                        } catch (ParseException e) {
//                            System.out.println("Please type date in the correct format [yyyy/MM/dd]");
//                            operation = this.scanner.nextLine();
//                        }
//                    }
//                    try {
//                        a1.setDueDate(operation);
//                    } catch (PastDueException e) {
//                        System.out.println("you cannot enter a date that's before today");
//                    }
//                    try {
//                        task1.addTodo(a1);
//                    } catch (TooManyItemException e) {
//                        System.out.println("Too many items not completed");
//                    } catch (PastDueException e) {
//                        System.out.println("This todo is overdue already");
//                    }
//                    System.out.println("Remember " + a1.getName() + " is due on " + a1.getDueDateInString()
//                            + " and has been added! ");
//                    operation = this.scanner.nextLine();
//                } else if (operation.equals("2")) {
//                    Item a1 = new RegularItem();
//                    a1.setToRegularOrUrgent("2");
//                    System.out.println("What do you need to do?");
//                    operation = this.scanner.nextLine();
//                    a1.setName(operation);
//                    System.out.println("When is the due Date? (Please Enter in [yyyy/MM/dd] format)");
//                    operation = this.scanner.nextLine();
//
//                    while (operation.length() != 10) {
//                        try {
//                            a1.setDueDate(operation);
//                        } catch (ParseException e) {
//                            System.out.println("Please type date in the correct format [yyyy/MM/dd]");
//                            operation = this.scanner.nextLine();
//                        }
//                    }
//                    try {
//                        a1.setDueDate(operation);
//                    } catch (PastDueException e) {
//                        System.out.println("you cannot enter a date that's before today");
//                        reStart();
//
//                    }
//                    System.out.println("How many times do you expect to do");
//                    operation = this.scanner.nextLine();
//                    a1.initalizeComplete(operation);
//
//                    try {
//                        task1.addTodo(a1);
//                    } catch (TooManyItemException e) {
//                        System.out.println("Too many Item is not yet completed.");
//                    }
//                    System.out.println("Remember " + a1.getName() + " is due on "
//                            + a1.getDueDateInString() + " and has been added! ");
//                    operation = this.scanner.nextLine();
//                }
//            } else if (operation.equals("2")) {
//                ArrayList<Integer> index = new ArrayList<>();
//                ArrayList<Integer> input = new ArrayList<>();
//
//
//                System.out.println("Which kind of item would you like to check?");
//                System.out.println("[1] Daily [2] Urgent");
//                operation = this.scanner.nextLine();
//                if (operation.equals("1")) {
//                    int a = 0;
//                    for (Item i: task1.getTodos()) {
//                        if (i.getDaily() == true) {
//                            System.out.println("[" + a + "] " + i.getName() + " is due on " + i.getDueDateInString());
//                            index.add(task1.getTodos().indexOf(i));
//                            a++;
//                        }
//                    }
//
//                    System.out.println("Select which of the above to check off");
//                    operation = this.scanner.nextLine();
//                    for (int i : index) {
//                        if (i == Integer.parseInt(operation)) {
//                            try {
//                                completedItem.addTodo(task1.getTodos().get(i));
//                            } catch (TooManyItemException e) {
//                                System.out.println("Doesn't work");
//                            }
//                            task1.checkOffTodo(task1.getTodos().get(i));
//                            System.out.println("You only got "
//                                    + task1.getTodos().get(i).getNumNotCompleted() + " more to go");
//                        }
//                    }
//                }
//
//
//                if (operation.equals("2")) {
//
//                    int i = 0;
//                    for (Item a: task1.getTodos()) {
//                        if (a.getUrgent() == true) {
//                            System.out.println("[" + i + "] " + a.getName() + " is due on " + a.getDueDateInString());
//                            index.add(task1.getTodos().indexOf(a)); //Find the index of each daily item
//                            i++;
//                        }
//                    }
//                }
//                System.out.println("Select which of the above to check off");
//                operation = this.scanner.nextLine();
//                for (int i : index) {
//                    if (i == Integer.parseInt(operation)) {
//                        try {
//                            completedItem.addTodo(task1.getTodos().get(i));
//                        } catch (TooManyItemException e) {
//                            System.out.println("Doesn't work");
//                        }
//                        task1.checkOffTodo(task1.getTodos().get(i));
//                    }
//
//                }
////                input = new ArrayList<>();
////                    for (Item a: task1.getTodos()) {
////                        if (a.getDaily()== true && a.getName() == operation) {
////                            System.out.println("Successfully Checked-off " + a.getName() + " Complete");
////                        }
//            } else if (operation.equals("4")) {
//                for (Item a: completedItem) {
//                    System.out.println(a.getName() + " has been successfully completed before "
//                            + a.getDueDateInString() + " Congratulation ");
//                    operation = scanner.nextLine();
//                }
//            } else if (operation.equals("3")) {
//                for (Item a : task1) {
//                    a.setIfPastDue();
//                    if (a.getpastDue() == false) {
//                        System.out.println(a.getName() + " is due on " + a.getDueDateInString());
//                    }
//                }
//                //operation = scanner.nextLine();
//                //Something is wrong with operation 5
//
//            } else if (operation.equals("4")) {
//                for (Item a: completedItem.getTodos()) {
//                    System.out.println(a.getName() + "has been successfully completed on " + a.getDueDateInString());
//                }
//            } else if (operation.equals("5")) {
////                    for (Item a: task1.getTodos()) {
////                        if (a.getDaily() == true || a.getUrgent() == true) {
////                            System.out.println(a.getName());
////                        }
////                }
//// Something is wrong with operation equals 5
//
//                for (Item a : task1.getTodos()) {
//                    a.setIfPastDue();
//                    if (a.getpastDue() == true) {
//                        System.out.println(a.getName() + " Due Date: " + a.getDueDateInString());
//                    }
//                }
//            } else if (operation.equals("6")) {
//                for (Item i : task1.getTodos()) {
//                    if (i.getUrgent() == true) {
//                        int a = 1;
//                        System.out.println("[" + a + "] " + i.getName() + " is due on " + i.getDueDateInString());
//                        a++;
//                    }
//                }
//
//            } else if (operation.equals("7")) {
//                task1.save();
//                break;
//            } else if (operation.equals("8")) {
//                System.out.println("Add a location to your item?");
//                System.out.println("Which one of the above would you want to add locations to");
//                int a = 0;
//                for (Item i : task1.getTodos()) {
//                    System.out.println("[" + a + "] " + i.getName() + " is due on "
//                            + i.getDueDateInString());
//                    a++;
//                }
//                operation = scanner.nextLine();
//
//                int index = Integer.parseInt(operation);
//                Item z = task1.getTodos().get(index);
//
//
//                System.out.println("You choose" + z.getName() + " is that correct?");
//                operation = scanner.nextLine();
//                if (operation.equals("yes")) {
//                    System.out.println("Where is it due at?");
//                    operation = scanner.nextLine();
//                    z.setLocationName(operation);
//                    Location location1 = z.getLocation();
//                    locations.add(location1);
//                }
//            } else if (operation.equals("9")) {
//                System.out.println("Look up a Item by location ");
//                for (Location a: locations) {
//                    System.out.println(a.getNameLocation());
//                }
//                operation = scanner.nextLine();
//                if (operation.equals("richmond")) {
//                    Location location2 = locations.get(0);
//                    ArrayList<Item> items1 = location2.getItems();
//                    for (Item item: items1) {
//                        System.out.println(item.getName() + item.getDueDateInString());
//                    }
//                }
//
//
//            }
//
//        }
//
//    }
//
//    public void reStart() throws IOException, ParseException {
//        new TodoApplication();
//    }
//
//    public static void main(String[] args) throws IOException, ParseException {
//        new TodoApplication();
//    }
//}


