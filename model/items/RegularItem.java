package model.items;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegularItem extends Item {


    private boolean daily;
    private int numNotCompleted;
    private boolean completed;

    public RegularItem() {
        super();
        daily = false;
    }

    public RegularItem(String name, Date dueDate) {
        super(name, dueDate);
        daily = false;
    }

    public RegularItem(String name, String dueDate) {
        this.name = name;
        try {
            this.dueDate = new SimpleDateFormat("yyyy/MM/dd").parse(dueDate);
        } catch (ParseException e) {
            System.out.println("This date is incorrect");
        }
    }


//    public RegularItem(String name, Date dueDate, int completion) {
//        super(name, dueDate);
//        this.numNotCompleted = completion;
//    }

    //Effects:change String into an numNotCompleted;

    @Override
    public void initalizeComplete(String a) {
        int b = Integer.parseInt(a);
        numNotCompleted = b;
    }

    //Require:numNotCompleted ==0
    //Effect: completed if numNotCompleted has reached zero
    //Since it just returns zero
    @Override
    public boolean isCompleted() {
        if (numNotCompleted == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int getNumNotCompleted() {
        return numNotCompleted;
    }

    //Modify: set completion minus one upon on completion once
    //Effect: subtract number completed by 1 if completed once

    @Override
    public void setCompleted() {
        if (getpastDue() == false && numNotCompleted > 0) {
            this.numNotCompleted--;
        }
    }






    //Effects: set pastDue as true if it dueDate > today's date and false if dueDate is < today's date
    // set false if dueDate is equal to today's date


    @Override
    public void setToRegularOrUrgent(String input) {
        if (input == "1") {
            daily = true;
        } else {
            daily = false;
        }
    }

    @Override
    public boolean getDaily() {
        return daily;
    }
}
