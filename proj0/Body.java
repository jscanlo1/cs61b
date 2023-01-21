public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    static final double G = 6.67e-11; 


    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    
    public double calcDistance(Body b) {
        double distance;
        distance = (this.xxPos - b.xxPos) * (this.xxPos - b.xxPos) + (this.yyPos - b.yyPos) * (this.yyPos - b.yyPos);
        return Math.sqrt(distance);
    }

    public double calcForceExertedBy(Body b) {
        double force;   
        double distance = calcDistance(b);
        force = (G * this.mass * b.mass) / (distance * distance);
        return force;
    }

    public double calcForceExertedByX(Body b) {
        double force, force_X;   
        double distance = calcDistance(b);
        force = (G * this.mass * b.mass) / (distance * distance);
        force_X = force * (b.xxPos - this.xxPos) / distance;
        return force_X;
    }

    public double calcForceExertedByY(Body b) {
        double force, force_Y;   
        double distance = calcDistance(b);
        force = (G * this.mass * b.mass) / (distance * distance);
        force_Y = force * (b.yyPos - this.yyPos) / distance;
        return force_Y;
    }

    public double calcNetForceExertedByX(Body[] allBodies) {
        double force, force_X, net_Force_X, distance;   

        net_Force_X = 0;
        for(int i = 0; i < allBodies.length; i++) {

            if(this.equals(allBodies[i])) {
                continue;
            }

            distance = calcDistance(allBodies[i]);
            force = (G * this.mass * allBodies[i].mass) / (distance * distance);
            force_X = force * (allBodies[i].xxPos - this.xxPos) / distance;
            net_Force_X += force_X;
        }

        return net_Force_X;
    }

    public double calcNetForceExertedByY(Body[] allBodies) {
        double force, force_Y, net_Force_Y, distance;   

        net_Force_Y = 0;
        for(int i = 0; i < allBodies.length; i++) {

            if(this.equals(allBodies[i])) {
                continue;
            }

            distance = calcDistance(allBodies[i]);
            force = (G * this.mass * allBodies[i].mass) / (distance * distance);
            force_Y = force * (allBodies[i].yyPos - this.yyPos) / distance;
            net_Force_Y += force_Y;
        }

        return net_Force_Y;
    }

    public void update (double time, double fX, double fY ) {
        double acceleration_x, acceleration_y;

        acceleration_x = fX / this.mass;
        acceleration_y = fY/ this.mass;

        this.xxVel = this.xxVel + (time * acceleration_x);
        this.yyVel = this.yyVel + (time * acceleration_y);

        this.xxPos = this.xxPos + (time * this.xxVel);
        this.yyPos = this.yyPos + (time * this.yyVel);
    }


}
