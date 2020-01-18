import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Mirror {

    // instance variables
    private FrontWall frontWall;
    private Point3D topRight;
    private Point3D topLeft;
    private Point3D bottomLeft;
    private Point3D bottomRight;
    private ArrayList<Point3D> vertices;
    private Canvas canvas;

    //constructor for the mirror
    public Mirror(FrontWall frontWall, Canvas canvas) {
        this.frontWall = frontWall;
        this.vertices = getMirrorCoords();
        this.topLeft = this.topRight = new Point3D(this.vertices.get(0).getX(), this.vertices.get(0).getY(),
                this.vertices.get(0).getZ());
        this.topLeft = new Point3D(this.vertices.get(1).getX(), this.vertices.get(1).getY(),
                this.vertices.get(1).getZ());
        this.bottomLeft = new Point3D(this.vertices.get(2).getX(), this.vertices.get(2).getY(),
                this.vertices.get(2).getZ());
        this.bottomRight = new Point3D(this.vertices.get(3).getX(), this.vertices.get(3).getY(),
                this.vertices.get(3).getZ());
        this.canvas = canvas;
    }

    //gets the mirror coordinates
    public ArrayList<Point3D> getMirrorCoords() {
        double bottomLeftX = this.frontWall.getBottomLeft().getX() + (this.frontWall.getBottomRight().getX() / 6);
        double bottomLeftY = this.frontWall.getBottomLeft().getY();

        double bottomRightX = this.frontWall.getBottomRight().getX() - (this.frontWall.getBottomRight().getX() / 6);
        double bottomRightY = this.frontWall.getBottomRight().getY();

        double topLeftX = bottomLeftX;
        double topLeftY = this.frontWall.getBottomLeft().getY() - (this.frontWall.getBottomLeft().getY() / 2);

        double topRightX = bottomRightX;
        double topRightY = topLeftY;

        ArrayList<Point3D> vertices = new ArrayList<Point3D>();
        vertices.add(new Point3D(topRightX, topRightY, 0));
        vertices.add(new Point3D(topLeftX, topLeftY, 0));
        vertices.add(new Point3D(bottomLeftX, bottomLeftY, 0));
        vertices.add(new Point3D(bottomRightX, bottomRightY, 0));
        return vertices;
    }

    //gets the buffered image of the mirror
    public BufferedImage getMirror() {
        BufferedImage mirrorImg = new BufferedImage((int) (getTopRight().getX() - getTopLeft().getX()),
                (int) (getBottomLeft().getY() - getTopLeft().getY()), BufferedImage.TYPE_INT_ARGB);
        return mirrorImg;
    }

    //getters for the vertices
    public Point3D getTopRight() {
        return this.topRight;
    }

    public Point3D getTopLeft() {
        return this.topLeft;
    }

    public Point3D getBottomLeft() {
        return this.bottomLeft;
    }

    public Point3D getBottomRight() {
        return this.bottomRight;
    }
}