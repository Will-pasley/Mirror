import java.awt.geom.Path2D;
import java.util.ArrayList;

public class FrontWall {

    //instance variables
    private Canvas canvas;
    private ArrayList<Point3D> vertices;
    private Point3D topRight;
    private Point3D topLeft;
    private Point3D bottomLeft;
    private Point3D bottomRight;
    private Point3D normal;
    private Path2D.Double frontWall;

    //constructor for the frontWall
    //The front wall is a fraction of the canvas size, and most other things in the room (walls) are built based off this wall
    //If the size of the canvas changes, so will the front wall and thus the rest of the room
    public FrontWall(Canvas canvas)
    {
        this.canvas = canvas;
        vertices = getFrontWallCoords();
        topRight = new Point3D(vertices.get(0).getX(), vertices.get(0).getY(), vertices.get(0).getZ());
        topLeft = new Point3D(vertices.get(1).getX(), vertices.get(1).getY(), vertices.get(1).getZ());
        bottomLeft = new Point3D(vertices.get(2).getX(), vertices.get(2).getY(), vertices.get(2).getZ());
        bottomRight = new Point3D(vertices.get(3).getX(), vertices.get(3).getY(), vertices.get(3).getZ());
        normal = (topRight.subtract(topLeft).crossProduct(bottomRight.subtract(topRight))).normalize();
        getCenter();
        frontWall = new Path2D.Double();
    }

    //gets the coordinates for the wall based on the canvas
    public ArrayList<Point3D> getFrontWallCoords()
    {
        double topLeftX = canvas.getDimW()/6;
        double topRightX = canvas.getDimW()-topLeftX;
        double bottomLeftX = topLeftX;
        double bottomRightX = topRightX;
        double topLeftY = canvas.getDimH()/6;
        double topRightY = topLeftY;
        double bottomLeftY = canvas.getDimH()-topLeftY;
        double bottomRightY = bottomLeftY;
        ArrayList<Point3D> vertices = new ArrayList<Point3D>();
        vertices.add(new Point3D(topRightX, topRightY, 0));
        vertices.add(new Point3D(topLeftX, topLeftY, 0));
        vertices.add(new Point3D(bottomLeftX, bottomLeftY, 0));
        vertices.add(new Point3D(bottomRightX, bottomRightY, 0));
        return vertices;
    }

    //gets the path 2D
    public Path2D.Double getFrontWall()
    {
        frontWall.moveTo(topLeft.getX(), topLeft.getY());
        frontWall.lineTo(topRight.getX(), topRight.getY());
        frontWall.lineTo(bottomRight.getX(), bottomRight.getY());
        frontWall.lineTo(bottomLeft.getX(), bottomLeft.getY());
        frontWall.lineTo(topLeft.getX(), topLeft.getY());
        return frontWall;
    }

    //gets the center of the wall
    public Point3D getCenter()
    {
        int x = (int) (getTopLeft().getX() + (getTopRight().getX()-getTopLeft().getX()/2));
        int y = (int) (getTopLeft().getY() + (getBottomLeft().getX()-getTopLeft().getX()/2));
        return new Point3D(x,y,0);
    }

    //getters for the vertices
    public Point3D getTopRight() {
        return topRight;
    }

    public Point3D getTopLeft() {
        return topLeft;
    }

    public Point3D getBottomLeft() {
        return bottomLeft;
    }

    public Point3D getBottomRight() {
        return bottomRight;
    }

}

