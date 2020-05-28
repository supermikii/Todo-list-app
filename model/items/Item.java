package model.items;


import model.exception.PastDueException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public abstract class Item {

    protected String name;
    protected Date dueDate;
    protected boolean pastDue;
    protected Location location;




// This abstract class has commonality between a urgent item
// that's one time versus a RegularTodoList that is daily


    public Item() {
        this.name = "";
        this.dueDate = new Date();
        pastDue = false;
        location = new Location(".");
    }

    public Item(String name, Date dueDate) {
        this.name = name;
        this.dueDate = dueDate;
        pastDue = false;
        location = new Location();
    }


//    public Item(String name, Date dueDate, Location location) {
//        this.name = name;
//        this.dueDate = dueDate;
//        pastDue = false;
//        this.location = location;
//    }


    public void setLocation(Location i) {
        if (!i.equals(location)) {
            this.location = i;
            this.location.addItems(this);
        }
    }

    public void setLocationName(String input) {
        location.setName(input);
    }

    public Location getLocation() {
        return location;
    }


    public String getName() {
        return name;
    }


    public Date getDueDate() {
        return dueDate;
    }


    public void setName(String input) {
        this.name = input;
    }


    // Require: To make sure the date is after today
    // Require: the patter is in dd/MM/yyyy
    public void setDueDate(String date1) throws PastDueException, ParseException {
        Date today = Calendar.getInstance().getTime();
        this.dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
        if (this.dueDate.compareTo(today) < 0) {
            throw new PastDueException();
        }
    }

    // abstract method
    // check if the item is an urgent item
    public abstract void setToRegularOrUrgent(String input);



    public boolean getDaily() {
        return false;
    }

    public boolean getUrgent() {
        return false;
    }




    //EFFECTS:Dummy holder for forcefully setting past due to false b/c using it as testing case
    //Instead of waiting for time to be passed due if not checked off
    public void setPastDue() {
        pastDue = true;
    }

    public void setIfPastDue() {
        Date today = Calendar.getInstance().getTime();
        if (dueDate.compareTo(today) > 0) {
            pastDue = false;
        } else if (dueDate.compareTo(today) <= 0) {
            pastDue = true;
        }
    }


    public boolean getpastDue() {
        return pastDue;
    }


    public String getDueDateInString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String todayasString = df.format(getDueDate());

        return todayasString;
    }

//These method below are corresponding to RegularItem class only, and the implementation does not matter
    public void setCompleted(){
    }

    //Perhaps this is the substituting rule
    //The sub class initializeComplete changes numCompleted
    //The LSP say that the require clause cannot get narrower & the effect cannot be wider


    //change string a
    //make super class effect have a wider effects
    //change more than numCompleted
    //but i cannot access numCompleted from super class
    public void initalizeComplete(String a) {
    }

    public boolean isCompleted() {
        return true;
    }

    public int getNumNotCompleted() {
        return 0;
    }

    // These are my equals and hashcode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(name, item.name)
                &&
                Objects.equals(dueDate, item.dueDate)
                &&
                Objects.equals(location, item.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dueDate, location);
    }
}
