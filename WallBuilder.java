import java.awt.geom.Path2D;

public class WallBuilder {

    private Canvas canvas;

    //WallBuilder Constructor
    //WallBuilder uses the canvas and the frontWall to generate the room
    public WallBuilder(Canvas canvas)
    {
        this.canvas = canvas;
    }

    //path for left wall
    public Path2D.Double getLeftWall()
    {
        Path2D.Double leftWall = new Path2D.Double();
        leftWall.moveTo(canvas.getFrontWall().getTopLeft().getX(), canvas.getFrontWall().getTopLeft().getY());
        leftWall.lineTo(0,0);
        leftWall.lineTo(0,canvas.getDimH());
        leftWall.lineTo(canvas.getFrontWall().getBottomLeft().getX(), canvas.getFrontWall().getBottomLeft().getY());
        leftWall.lineTo(canvas.getFrontWall().getTopLeft().getX(), canvas.getFrontWall().getTopLeft().getY());
        return leftWall;
    }

    //path for the right wall
    public Path2D.Double getRightWall()
    {
        Path2D.Double rightWall = new Path2D.Double();
        rightWall.moveTo(canvas.getFrontWall().getTopRight().getX(), canvas.getFrontWall().getTopRight().getY());
        rightWall.lineTo(canvas.getDimW(),0);
        rightWall.lineTo(canvas.getDimW(),canvas.getDimH());
        rightWall.lineTo(canvas.getFrontWall().getBottomRight().getX(), canvas.getFrontWall().getBottomRight().getY());
        rightWall.lineTo(canvas.getFrontWall().getTopRight().getX(), canvas.getFrontWall().getTopRight().getY());
        return rightWall;
    }

    //path for the floor
    public Path2D.Double getFloor()
    {
        Path2D.Double floor = new Path2D.Double();
        floor.moveTo(canvas.getFrontWall().getBottomLeft().getX(), canvas.getFrontWall().getBottomLeft().getY());
        floor.lineTo(0,canvas.getDimH());
        floor.lineTo(canvas.getDimW(),canvas.getDimH());
        floor.lineTo(canvas.getFrontWall().getBottomRight().getX(), canvas.getFrontWall().getBottomRight().getY());
        floor.lineTo(canvas.getFrontWall().getBottomLeft().getX(), canvas.getFrontWall().getBottomLeft().getY());
        return floor;
    }

    //path for the ceiling
    public Path2D.Double getCeiling()
    {
        Path2D.Double ceiling = new Path2D.Double();
        ceiling.moveTo(canvas.getFrontWall().getTopLeft().getX(), canvas.getFrontWall().getTopLeft().getY());
        ceiling.lineTo(0,0);
        ceiling.lineTo(canvas.getDimW(),0);
        ceiling.lineTo(canvas.getFrontWall().getTopRight().getX(), canvas.getFrontWall().getTopRight().getY());
        ceiling.lineTo(canvas.getFrontWall().getTopLeft().getX(), canvas.getFrontWall().getTopLeft().getY());
        return ceiling;
    }

    //path for the mirror border
    public Path2D.Double getMirrorBorder()
    {
        Path2D.Double mirrorBorder = new Path2D.Double();
        mirrorBorder.moveTo(canvas.getMirror().getTopLeft().getX()-1, canvas.getMirror().getTopLeft().getY()-1);
        mirrorBorder.lineTo(canvas.getMirror().getTopRight().getX(), canvas.getMirror().getTopRight().getY()-1);
        mirrorBorder.lineTo(canvas.getMirror().getBottomRight().getX(), canvas.getMirror().getBottomRight().getY());
        mirrorBorder.lineTo(canvas.getMirror().getBottomLeft().getX()-1, canvas.getMirror().getBottomLeft().getY());
        mirrorBorder.lineTo(canvas.getMirror().getTopLeft().getX()-1, canvas.getMirror().getTopLeft().getY()-1);
        return mirrorBorder;
    }

    //path for the left wall in the mirror
    public Path2D.Double getBackLeft()
    {
        Path2D.Double backLeftWall = new Path2D.Double();
        backLeftWall.moveTo(canvas.getBehindWall().getBottomLeft().getX(), canvas.getBehindWall().getBottomLeft().getY());
        backLeftWall.lineTo(canvas.getBehindWall().getTopLeft().getX(), canvas.getMirror().getTopLeft().getY()-1);
        backLeftWall.lineTo(canvas.getMirror().getTopLeft().getX()-1, canvas.getMirror().getTopLeft().getY()-1);
        backLeftWall.lineTo(canvas.getMirror().getTopLeft().getX()-1, canvas.getMirror().getTopLeft().getY()+((canvas.getMirror().getBottomLeft().getY())/3));
        backLeftWall.lineTo(canvas.getBehindWall().getBottomLeft().getX(), canvas.getBehindWall().getBottomLeft().getY());
        return backLeftWall;
    }

    //path for the right wall in the mirror
    public Path2D.Double getBackRight()
    {
        Path2D.Double backRightWall = new Path2D.Double();
        backRightWall.moveTo(canvas.getBehindWall().getBottomRight().getX(), canvas.getBehindWall().getBottomRight().getY());
        backRightWall.lineTo(canvas.getBehindWall().getTopRight().getX(), canvas.getMirror().getTopRight().getY()-1);
        backRightWall.lineTo(canvas.getMirror().getTopRight().getX(), canvas.getMirror().getTopRight().getY()-1);
        backRightWall.lineTo(canvas.getMirror().getTopRight().getX(), canvas.getMirror().getTopRight().getY()+((canvas.getMirror().getBottomRight().getY())/3));
        backRightWall.lineTo(canvas.getBehindWall().getBottomRight().getX(), canvas.getBehindWall().getBottomRight().getY());
        return backRightWall;
    }

    //path for the floor in the mirror
    public Path2D.Double getBackFloor()
    {
        Path2D.Double backFloor = new Path2D.Double();
        backFloor.moveTo(canvas.getBehindWall().getBottomLeft().getX(), canvas.getBehindWall().getBottomLeft().getY());
        backFloor.lineTo(canvas.getBehindWall().getBottomRight().getX(), canvas.getBehindWall().getBottomRight().getY());
        backFloor.lineTo(canvas.getMirror().getTopRight().getX(), canvas.getMirror().getTopRight().getY()+((canvas.getMirror().getBottomRight().getY())/3));
        backFloor.lineTo(canvas.getMirror().getBottomRight().getX(), canvas.getMirror().getBottomRight().getY());
        backFloor.lineTo(canvas.getMirror().getBottomLeft().getX(), canvas.getMirror().getBottomLeft().getY());
        backFloor.lineTo(canvas.getMirror().getTopLeft().getX(), canvas.getMirror().getTopLeft().getY()+((canvas.getMirror().getBottomLeft().getY())/3));
        backFloor.lineTo(canvas.getBehindWall().getBottomLeft().getX(), canvas.getBehindWall().getBottomLeft().getY());
        return backFloor;
    }
}
