import java.awt.*;

public class Point3D {

    //instance variables
    double x;
    double y;
    double z;

    //Point3D constructor
    public Point3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //getters for x,y,z
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    //gets perspective of x based on z and eye distance
    public double getPerspectiveX(double eyeDistance)
    {
        double newx = getX()/(1-(getZ()/eyeDistance));
        return newx;
    }

    //gets perspective of y based on z and eye distance
    public double getPerspectiveY(double eyeDistance)
    {
        double newy = getY()/(1-(getZ()/eyeDistance));
        return newy;
    }

    //the following functions were used for ray tracing, which is no longer implemented

    //point (vector) subtraction
    public Point3D subtract(Point3D point)
    {
        double newX = point.getX() - this.x;
        double newY = point.getY() - this.y;
        double newZ = point.getZ() - this.z;
        return new Point3D(newX, newY, newZ);
    }

    //point (vector) addition
    public Point3D add(Point3D point)
    {
        double newX = point.getX() + this.x;
        double newY = point.getY() + this.y;
        double newZ = point.getZ() + this.z;
        return new Point3D(newX, newY, newZ);
    }

    //magnitude function, treats point as a vector
    public double magnitude()
    {
        double magnitude = Math.sqrt(Math.pow(getX(),2) + Math.pow(getY(),2) + Math.pow(getZ(),2));
        return magnitude;
    }

    //normalize function, treats point as a vector
    public Point3D normalize()
    {
        double magnitude = magnitude();
        return new Point3D(this.x/magnitude, this.y/magnitude, this.z/magnitude);
    }

    //cross product function, treats point as a vector
    public Point3D crossProduct(Point3D point)
    {
        return new Point3D((this.y*point.getZ() - this.z*point.getY()), -(this.x*point.getZ() - this.z*point.getX()), (this.x*point.getY() - this.y*point.getX()));
    }

    //dotProduct function, treats point as a vector
    public double dotProduct(Point3D point)
    {
        return this.x*point.getX()+this.y*point.getY()+this.z*point.getZ();
    }

    //point (vector) multiplication
    public Point3D multiply(double constant)
    {
        return new Point3D(this.x*constant, this.y*constant, this.z*constant);
    }
}
