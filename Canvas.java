import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel implements MouseMotionListener {
    //Instance Variables
    private FrontWall frontWall;
    private Mirror mirror;
    private BehindWall behindWall;
    private WallBuilder wallBuilder;
    private Sphere sphere;
    private Sphere sphereReflection;
    private Sphere backSphere;
    private int dimW;
    private int dimH;
    private int sphereX;
    private int sphereY;
    private int sphereZ;
    private int sphereReflectionX;
    private int sphereReflectionY;
    private int backSphereX;
    private int backSphereY;
    private int radius;
    private int sphereReflectionRadius;
    private int backSphereRadius;
    private int initialSphereX;
    private int initialSphereY;
    private int initialSphereReflectionX;
    private int initialSphereReflectionY;
    private int sphereReflectionShift;
    private double avg;
    private double scale;

    //Canvas Constructor, initializes variables
    //Change dimW and dimH variables to change the size of the room
    public Canvas()
    {
        //These are the width and height dimensions of the room:
        dimW = 800;
        dimH = 800;

        avg = (dimH+dimW)/2;
        scale = avg /800;

        //spheres will scale based on the size of the room
        radius = (int) (50 * scale);
        sphereReflectionRadius = (int) (33 * scale);
        backSphereRadius = (int) (20 * scale);

        sphere = new Sphere(radius);
        sphereReflection = new Sphere(sphereReflectionRadius);
        backSphere = new Sphere(backSphereRadius);

        frontWall = new FrontWall(this);
        behindWall = new BehindWall(frontWall);
        mirror = new Mirror(frontWall, this);
        wallBuilder = new WallBuilder(this);

        sphereX = dimW/2 - radius;
        sphereY = dimH - 2*radius- dimH/12;
        sphereZ = 300;
        initialSphereX = sphereX;
        initialSphereY = sphereY;
        sphereReflectionX = dimW/2 - sphereReflectionRadius;
        sphereReflectionY = dimH - 2*radius - sphereReflectionRadius - dimH/9;
        initialSphereReflectionX = sphereReflectionX;
        initialSphereReflectionY = sphereReflectionY;
        backSphereX = dimW/2 - backSphereRadius;
        backSphereY = (int) (behindWall.getBottomRight().getY() + (mirror.getBottomLeft().getY() - behindWall.getBottomRight().getY())/12);

        sphereReflectionShift = radius - sphereReflectionRadius;

        sphereReflection.setViewZ(-300);
        backSphere.setViewZ(300);

        setPreferredSize(new Dimension(dimW,dimH));
        addMouseMotionListener(this);
    }


    //paints the room including the spheres
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Path2D.Double frontWallPath = frontWall.getFrontWall();

        g2d.setPaint(new Color(154, 204, 255));
        g2d.fill(frontWallPath);

        g2d.setPaint(new Color(150,50,50));
        g2d.fill(wallBuilder.getBackLeft());

        g2d.setPaint(new Color(50,150,50));
        g2d.fill(wallBuilder.getBackRight());

        Path2D behindWallPath = behindWall.getBehindWall();
        g2d.setPaint(new Color(154, 204, 255));
        g2d.fill(behindWallPath);

        g2d.setPaint(new Color(206,206,206));
        g2d.fill(wallBuilder.getBackFloor());

        g2d.drawImage(backSphere.getSphere(), backSphereX, backSphereY, null);

        g2d.drawImage(sphereReflection.getSphere(), sphereReflectionX, sphereReflectionY, null);

        g2d.setPaint(new Color(150,50,50));
        g2d.fill(wallBuilder.getLeftWall());

        g2d.setPaint(new Color(50,150,50));
        g2d.fill(wallBuilder.getRightWall());

        g2d.setPaint(new Color(204,204,153));
        g2d.fill(wallBuilder.getCeiling());

        g2d.setPaint(new Color(206,206,206));
        g2d.fill(wallBuilder.getFloor());

        BufferedImage mirrorImg = mirror.getMirror();
        g2d.drawImage(mirrorImg, (int) mirror.getTopLeft().getX(), (int) mirror.getTopLeft().getY(), null);

        g2d.setPaint(new Color(154, 204, 255));
        g2d.fillRect((int) frontWall.getTopLeft().getX(), (int) mirror.getTopLeft().y -1,
                (int) ((frontWall.getBottomRight().getX() / 6) -1 ),
                (int) (mirror.getBottomLeft().getY() - mirror.getTopLeft().getY() + 2));
        g2d.fillRect((int)  mirror.getTopRight().getX()+1, (int) mirror.getTopRight().getY() -1,
                (int) (frontWall.getBottomRight().getX() / 6),
                (int) (mirror.getBottomLeft().getY() - mirror.getTopLeft().getY() + 2));
        g2d.fillRect((int)  frontWall.getTopLeft().getX(), (int) frontWall.getTopLeft().getY(),
                (int) (frontWall.getBottomRight().getX() - frontWall.getBottomLeft().getX()),
                (int) ((mirror.getTopLeft().getY()) - frontWall.getTopLeft().getY())-1);

        g2d.setPaint(Color.BLACK);
        Path2D.Double mirrorBorder = wallBuilder.getMirrorBorder();
        g2d.draw(mirrorBorder);

        BufferedImage sphereImg = sphere.getSphere();
        g2d.drawImage(sphereImg, sphereX, sphereY, null);
    }

    //getters for the canvas dimensions
    public int getDimW() {
        return dimW;
    }

    public int getDimH() {
        return dimH;
    }

    //gets the center of a sphere
    public Point3D getSphereCenter(Sphere sphere)
    {
        double centerX = sphereX + sphere.getSphere().getWidth()/2;
        double centerY = sphereY + sphere.getSphere().getHeight()/2;
        double centerZ = sphereZ;
        return new Point3D(centerX, centerY,centerZ);
    }

    //getters for the walls
    public FrontWall getFrontWall()
    {
        return this.frontWall;
    }

    public Mirror getMirror()
    {
        return this.mirror;
    }

    public BehindWall getBehindWall()
    {
        return this.behindWall;
    }

    //moves the spheres around when the mouse is dragged
    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getX() > (this.getWidth()/8) && e.getX() < (this.getWidth() - this.getWidth()/8)) {
            sphereX = e.getX() - radius;
            if (sphereX + radius > this.getWidth()/2) {
                sphereReflectionX = sphereX + sphereReflectionShift;
                sphereReflectionX -= 0.2 * ((sphereReflectionX + sphereReflectionRadius) - this.getWidth()/2);
            }
            else{
                sphereReflectionX = sphereX + sphereReflectionShift;
                sphereReflectionX += 0.2 * ((this.getWidth()/2) - (sphereReflectionX + sphereReflectionRadius));
            }
            sphereReflection.setViewX(-(sphereReflectionX - initialSphereReflectionX));
            sphere.setViewX(-(sphereX - initialSphereX));
        }
        if (e.getY() < this.getHeight() - this.getHeight()/8 && e.getY() > this.getHeight()/8) {
            sphereY = e.getY() - radius;
            sphereReflectionY = sphereY;
            sphereReflectionY += 0.2 * ((mirror.getTopLeft().getY()) - (sphereReflectionY));
            sphere.setViewY((int) ((-(sphereY - initialSphereY)) - (frontWall.getBottomLeft().getY()-frontWall.getTopLeft().getY())+100));
            sphereReflection.setViewY((int) ((-(sphereReflectionY - initialSphereReflectionY)) - (frontWall.getBottomLeft().getY()-frontWall.getTopLeft().getY())+100));
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
