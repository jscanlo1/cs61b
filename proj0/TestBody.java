public class TestBody{

    /**
     * Class to test body class
     * Creates two bodies and putputs their pairwise force
     * 
     * @param args
     */
    public static void main(String[] args) {

        Body b1 = new Body(10, 10, 5, -4, 10e7, null);
        Body b2 = new Body(30, 40, -1, 2, 1e10, null);

        double b1_force = b1.calcForceExertedBy(b2);
        double b2_force = b2.calcForceExertedBy(b1);

        System.out.println("Foce exerted on B1 by B2: " + b1_force);
        System.out.println("Foce exerted on B2 by B1: " + b2_force);

        //Check for equality
        if((b1_force - b2_force) < 0.000000000000001  ){
            System.out.println("Forces are correct and equal");
        } else{
            System.out.println("Forces ont equal. Something is going wrong!");
        }

    }
}