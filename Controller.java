import javax.swing.*;
import java.awt.*;

public class Controller extends JFrame
{

    private static Canvas canvas;

    //main method initializes frame
    public static void main(String[] args)
    {
        canvas = new Canvas();
        JFrame frame = new JFrame();
        frame.setTitle("Mirror");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.lightGray);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
