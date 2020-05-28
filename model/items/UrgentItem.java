package model.items;



public class UrgentItem extends Item {

    private boolean urgent;

    public UrgentItem() {
        super();
        urgent = false;
    }

    public boolean getUrgent() {
        return urgent;
    }


    public void setToRegularOrUrgent(String pinned) {
        if (pinned == "2") {
            urgent = true;
        } else if (pinned == "1") {
            urgent = false;
        }
    }
}

//// If item is pinned then put a * beside the item
//    @Override
//    public boolean checkIfUrgent() {
//        if (urgent == true)
//            return true;
//        else { return false;
////            System.out.println("You still need to complete your task");
//        }
//    }