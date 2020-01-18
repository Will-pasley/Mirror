import java.awt.*;
import java.awt.image.BufferedImage;

public class Sphere {

    private int radius;
    private BufferedImage sphere;
    private int[] viewVector;
    private double ambientLight;
    private String colorInput;

    //Sphere constructor sets ambient light, the sphere buffered image, and the default view vector
    public Sphere(int radius)
    {
        ambientLight = 100;
        viewVector = new int[]{0, -400, 200};
        this.radius = radius;
        sphere = new BufferedImage(radius*2,radius*2,BufferedImage.TYPE_INT_ARGB);
        colorInput = "Gray";
    }

    //formula for getting the correct coordinates for the circle
    public BufferedImage getSphere()
    {
        for (int y = -radius; y < radius; y++)
        {
            int x = (int) Math.sqrt(Math.pow(radius,2) - Math.pow(y,2));
            for (int x1 = -x; x1 < x; x1++)
            {
                int shade = getShade(x1,y);
                Color col = new Color(shade, shade, shade);
                if (colorInput == "Red")
                {
                    col = new Color(shade, (int) ambientLight-40, (int) ambientLight-40);
                }
                if (colorInput == "Green")
                {
                    col = new Color((int) ambientLight-40, shade, (int) ambientLight-40);
                }
                if (colorInput == "Blue")
                {
                    col = new Color((int) ambientLight-40, (int) ambientLight-40, shade);
                }
                if (colorInput == "Yellow")
                {
                    col = new Color(shade, shade, (int) ambientLight-40);
                }
                if (colorInput == "Magenta")
                {
                    col = new Color(shade, (int) ambientLight-40, shade);
                }
                if (colorInput == "Teal")
                {
                    col = new Color((int) ambientLight-40, shade, shade);
                }
                int color = col.getRGB();
                this.sphere.setRGB(x1+radius,y+radius, color);
            }
        }
        return this.sphere;
    }

    //gets the z coordinate
    public int getShade(int x, int y)
    {
        int z = (int)Math.sqrt(Math.pow(radius,2)-Math.pow(y,2)-Math.pow(x,2));
        double cosTheta = getCosTheta(x, y, z);
        if (cosTheta <= 0)
        {
            return (int) ambientLight;
        }
        else
        {
            double shade = cosTheta*(255.0-ambientLight) + ambientLight;
            int ret = (int) shade;
            return ret;
        }
    }

    //gets the cosine of the angle between the normal and the view vector
    public double getCosTheta(int x, int y, int z)
    {
        double viewLength = Math.sqrt(Math.pow(viewVector[0]-x,2) + Math.pow(viewVector[1]-y,2) + Math.pow(viewVector[2]-z,2));
        double normalLength = Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
        double dot = x*(viewVector[0]-x) + y*(viewVector[1]-y) + z*(viewVector[2]-z);
        double cosTheta = dot/(viewLength*normalLength);
        return cosTheta;
    }

    //sets x value for view vector, triggered by slider
    public void setViewX(int x)
    {
        viewVector[0] = x;
    }
    //sets y value for view vector, triggered by slider
    public void setViewY(int y)
    {
        viewVector[1] = y;
    }
    //sets z value for view vector, triggered by slider
    public void setViewZ(int z)
    {
        viewVector[2] = z;
    }
    //sets colorInput, triggered by combo box
    public void setColorInput(String colorInput)
    {
        this.colorInput = colorInput;
    }

    public double getRadius()
    {
        return this.radius;
    }
}
