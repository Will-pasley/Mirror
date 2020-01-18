import java.awt.geom.Path2D;
import java.util.ArrayList;

public class BehindWall {

    private FrontWall frontWall;
    private double z;
    private Point3D topRight;
    private Point3D topLeft;
    private Point3D bottomLeft;
    private Point3D bottomRight;
    private ArrayList<Point3D> frontWallVertices;
    private double eyeDistance;
    private Path2D.Double behindWall;
    private double[][] backWallVertices;

    //Constructor for the back wall in the mirror
    public BehindWall(FrontWall frontWall)
    {
        this.frontWall = frontWall;
        z = -1200;
        eyeDistance = 600;
        frontWallVertices = getFrontWallCoords();
        topRight = new Point3D(frontWallVertices.get(0).getX(), frontWallVertices.get(0).getY(), frontWallVertices.get(0).getZ());
        topLeft = new Point3D(frontWallVertices.get(1).getX(), frontWallVertices.get(1).getY(), frontWallVertices.get(1).getZ());
        bottomLeft = new Point3D(frontWallVertices.get(2).getX(), frontWallVertices.get(2).getY(), frontWallVertices.get(2).getZ());
        bottomRight = new Point3D(frontWallVertices.get(3).getX(), frontWallVertices.get(3).getY(), frontWallVertices.get(3).getZ());
        backWallVertices = getPerspectiveWall(frontWallVertices);
        behindWall = new Path2D.Double();
    }

    //gets the coordinates
    public ArrayList<Point3D> getFrontWallCoords()
    {
        double topLeftX = frontWall.getTopLeft().getX();
        double topRightX = frontWall.getTopRight().getX();
        double bottomLeftX = frontWall.getBottomLeft().getX();
        double bottomRightX = frontWall.getBottomRight().getX();
        double topLeftY = frontWall.getTopLeft().getY();
        double topRightY = frontWall.getTopRight().getY();
        double bottomLeftY = frontWall.getBottomLeft().getY();
        double bottomRightY = frontWall.getBottomRight().getY();

        ArrayList<Point3D> vertices = new ArrayList<Point3D>();
        vertices.add(new Point3D(topRightX, topRightY, z));
        vertices.add(new Point3D(topLeftX, topLeftY, z));
        vertices.add(new Point3D(bottomLeftX, bottomLeftY, z));
        vertices.add(new Point3D(bottomRightX, bottomRightY, z));

        return vertices;
    }

    //gets the wall coordinates in perspective
    public double[][] getPerspectiveWall(ArrayList<Point3D> vertices)
    {
        double xShift = (topRight.getX()-topLeft.getX())/2;
        double yShift = (bottomRight.getY() - topRight.getY())/2;


        double yAdjust = 22;

        double topRightX = vertices.get(0).getPerspectiveX(eyeDistance) + xShift;
        double topRightY = vertices.get(0).getPerspectiveY(eyeDistance) + yShift;

        double topLeftX = vertices.get(1).getPerspectiveX(eyeDistance) + xShift;
        double topLeftY = vertices.get(1).getPerspectiveY(eyeDistance) + yShift;

        double bottomLeftX = vertices.get(2).getPerspectiveX(eyeDistance) + xShift;
        double bottomLeftY = vertices.get(2).getPerspectiveY(eyeDistance) + yShift;

        double bottomRightX = vertices.get(3).getPerspectiveX(eyeDistance) + xShift;
        double bottomRightY = vertices.get(3).getPerspectiveY(eyeDistance) + yShift;

        double[][] perspectiveWall = new double[][]{{topRightX, topRightY}, {topLeftX,topLeftY}, {bottomLeftX, bottomLeftY}, {bottomRightX, bottomRightY}};
        return perspectiveWall;
    }

    //gets the path of the mirror wall
    public Path2D.Double getBehindWall()
    {
        behindWall.moveTo(backWallVertices[0][0], backWallVertices[0][1]);
        behindWall.lineTo(backWallVertices[1][0], backWallVertices[1][1]);
        behindWall.lineTo(backWallVertices[2][0], backWallVertices[2][1]);
        behindWall.lineTo(backWallVertices[3][0], backWallVertices[3][1]);
        behindWall.lineTo(backWallVertices[0][0], backWallVertices[0][1]);
        return behindWall;
    }


    //getters for the vertices
    public Point3D getTopRight() {
        return new Point3D(backWallVertices[0][0], backWallVertices[0][1], 0);
    }

    public Point3D getTopLeft() {
        return new Point3D(backWallVertices[1][0], backWallVertices[1][1], 0);
    }

    public Point3D getBottomLeft() {
        return new Point3D(backWallVertices[2][0], backWallVertices[2][1], 0);
    }

    public Point3D getBottomRight() {
        return new Point3D(backWallVertices[3][0], backWallVertices[3][1], 0);
    }
}
