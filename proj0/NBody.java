public class NBody {
    

    public static int readNumBodies(String file_address) {
        In in = new In(file_address);

        int number_planets = in.readInt();

        return number_planets;
    }

    public static double readRadius(String file_address) {
        In in = new In(file_address);

        int number_planets = in.readInt();
        double radius = in.readDouble();

        return radius;
    }


    public static Body[] readBodies(String file_address) {
        In in = new In(file_address);

        int number_planets = in.readInt();
        double radius = in.readDouble();

        double xxPos;
        double yyPos;
        double xxVel;
        double yyVel;
        double mass;
        String imgFileName;

        Body[] b_array = new Body[number_planets];

        for(int i = 0; i < number_planets; i++){
            
            xxPos = in.readDouble();
            yyPos = in.readDouble();
            xxVel = in.readDouble();
            yyVel = in.readDouble();
            mass = in.readDouble();
            imgFileName = in.readString();


            b_array[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }

        return b_array;
    }

    public static void drawBackground(double radius){
		
        StdDraw.picture(0, 0, "images/starfield.jpg");
    }
    
    public static void draw(Body b) {
        
        StdDraw.picture(b.xxPos, b.yyPos, "images/" + b.imgFileName);
    }
    
    public static void main(String[] args) {

        if (args.length != 3) {
			System.out.println("Wrong number of arguments arguments");
			System.exit(0);
		}	

        double T, dT;
        String filename;

        T = Double.parseDouble(args[0]);
        dT = Double.parseDouble(args[1]);
        filename = args[2];

        int number_planets = readNumBodies(filename);
        double radius = readRadius(filename);
        Body[] planet_array = readBodies(filename);

        
        //Play Audio
        /*int SAMPLING_RATE = 44100;
        double hz = 440.0;
        double duration = 10.0;
        int n = (int) (SAMPLING_RATE * duration);
        double[] a = new double[n+1];
        for (int i = 0; i <= n; i++) {
        a[i] = Math.sin(2 * Math.PI * i * hz / SAMPLING_RATE); 
        }
        StdAudio.play(a);
        */

        StdAudio.play("audio/2001.mid");
        System.out.println("PLAY THIS AUDIO!!!");


        //Start Drawing
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius , radius );
        StdDraw.clear();
        drawBackground(radius);
        for(int i = 0; i < planet_array.length ; i += dT){
            draw(planet_array[i]);
        }
        StdDraw.show();

        //Loop to update forces and redraw planets
        for(int i = 0; i <= T; i++){

            //Calculate forces
            double[] xForces = new double[number_planets];
            double[] yForces = new double[number_planets];

            for(int j = 0 ; j < number_planets; j++){
                xForces[j] = planet_array[j].calcNetForceExertedByX(planet_array);
                yForces[j] = planet_array[j].calcNetForceExertedByY(planet_array);
            }

            // Update bodies
            for(int j = 0; j < number_planets; j++) {
                planet_array[j].update(dT, xForces[j], yForces[j]);
            }


            //Draw new bodies

            StdDraw.enableDoubleBuffering();
            StdDraw.clear();
            drawBackground(radius);
            for(int j = 0; j < planet_array.length ; j++){
                draw(planet_array[j]);
            }
            StdDraw.show();
            StdDraw.pause(10);
        }


        //Print out final state of the universe
        StdOut.printf("%d\n", number_planets);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planet_array.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planet_array[i].xxPos, planet_array[i].yyPos, planet_array[i].xxVel,
            planet_array[i].yyVel, planet_array[i].mass, planet_array[i].imgFileName);   
        }
        
    }
    
}
