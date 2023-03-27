import java.util.Random;

public class MyVehicle implements Vehicle {

    private char model;
    private int position;
    private int speed;
    private boolean isNervous;

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

    // move by speed to next position,
    // if nervous then decrese speed and move by that speed
    // if about to collide then move right behind the vehicle?
    public void move() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean nervous() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean willCrashWithOnMove(Vehicle other) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String toString() {
        return "MyVehicle [model=" + model + ", position=" + position + ", speed=" + speed + ", isNervous=" + isNervous
                + "]";
    }
    
}
