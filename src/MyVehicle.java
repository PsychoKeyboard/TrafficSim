import java.util.Random;

public class MyVehicle implements Vehicle {

    private char model;
    private int position;
    private int speed;
    private boolean isNervous;

    private Vehicle next;

    public MyVehicle(){

        //check if its a nervous driver
        Random nev = new Random();
        int upperbound = 9;

        int nevOrNot = nev.nextInt(upperbound);

        if(nevOrNot == 0){
            isNervous = true;
        }

        //get random character model based on whether nervous or not

        String models = "";

        if(isNervous){
            models = "@#$%^&*";
        }
        else{
            models = "abcdefghijklmnopqrstuvwxyz0123456789";
        }

        Random rand = new Random();
        upperbound = models.length() - 1;

        int modelPos = rand.nextInt(upperbound);

        model = models.charAt(modelPos);


        //position & speed is initially 0
        position = 0;
        speed = 0;

        this.next = null;
    }

    @Override
    public char asChar() {
        return model;
    }

    @Override
    public int currentSpeed() {
        return speed;
    }

    @Override
    public void decreaseSpeed(int by) {
        int calc = speed - by;

        if(calc < 0)
            speed = 0;
        else
            speed = calc;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void increaseSpeed(int by) {
        speed = speed + by;
        
    }


    @Override
    public boolean willCrashWithOnMove(Vehicle other) {

        boolean flag = false;
        
        int otherPos = other.getPosition();

        int newPos = position + speed;

        if(newPos > otherPos){
            flag = true;
        }

        return flag;
    }

    // move by speed to next position,
    // if nervous then decrese speed and move by that speed
    // if about to collide then move right behind the vehicle?
    public void move() {


        // not implementing nervous behaviour right now
        // just a simple move method right now. Not checking collisions here
        
        position = position + speed;


    }

    @Override
    public boolean nervous() {
        
        //current state is represented by isNervous

        boolean slowDown = false;

        //slow down randomly if nervous

        if(isNervous){
            //not implemeting the actual slowing right now
            slowDown = true;
        }

        //slow down if about to crash

        if(willCrashWithOnMove(next)){
            // not implementing the actual slowing right now
            //position = next.getPosition() - 1;
            //speed = 0;
            slowDown = true;
        }
        return slowDown;
    }

    

    @Override
    public String toString() {
        return "MyVehicle [model=" + model + ", position=" + position + ", speed=" + speed + ", isNervous=" + isNervous
                + "]";
    }
    
}
