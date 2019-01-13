package Painter;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.geom.*;

@SuppressWarnings("serial")

// By extending JFrame we have our applet

public class Lesson47 extends JFrame{
	
	public static void main(String[] args){
		
		new Lesson47();
		
	}
	
	public Lesson47(){
		
		this.setSize(500, 500);
		
		this.setTitle("Drawing Shapes");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(new DrawStuff(), BorderLayout.CENTER);
		
		this.setVisible(true);
		
	}
	
	// Creating my own component by extending JComponent
	// JComponent is the base class for all swing components. Even custom ones
	
	private class DrawStuff extends JComponent{
		
		// Graphics is the base class that allows for drawing on components
		
		public void paint(Graphics g){
			
			// Extends graphics so you can draw dimensional shapes and images
			
			Graphics2D graph2 = (Graphics2D)g;
			
			// Sets preferences for rendering
			// KEY_ANTIALIASING reduces artifacts on shapes
			// VALUE_ANTIALIAS_ON will clean up the edges
			
			graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			// The Shape interface knows how to draw many different shapes
			/* Arc2D, Arc2D.Double, Arc2D.Float, Area, BasicTextUI.BasicCaret, 
			 * CubicCurve2D, CubicCurve2D.Double, CubicCurve2D.Float, DefaultCaret, 
			 * Ellipse2D, Ellipse2D.Double, Ellipse2D.Float, GeneralPath, Line2D, 
			 * Line2D.Double, Line2D.Float, Path2D, Path2D.Double, Path2D.Float, 
			 * Polygon, QuadCurve2D, QuadCurve2D.Double, QuadCurve2D.Float, 
			 * Rectangle, Rectangle2D, Rectangle2D.Double, Rectangle2D.Float, 
			 * RectangularShape, RoundRectangle2D, RoundRectangle2D.Double, 
			 * RoundRectangle2D.Float
			 */
			
			// A line that goes from x1, y1 to x2, y2
			
			Shape drawLine = new Line2D.Float(20, 90, 55, 250);
			
			// Start x, start y, width, height, start angle degrees, angular extent, OPEN, CHORD, PIE
			// Angular extent refers to how many degrees the arc continues from the start angle
			
			Shape drawArc2D = new Arc2D.Double(5, 150, 100, 100, 45, 180, Arc2D.OPEN);
			
			Shape drawArc2D2 = new Arc2D.Double(5, 200, 100, 100, 45, 45, Arc2D.CHORD);
			
			Shape drawArc2D3 = new Arc2D.Double(5, 250, 100, 100, 45, 45, Arc2D.PIE);
			
			// Draw ellipse in a rectangle defined x1, y1, x2, y2
			
			Shape drawEllipse = new Ellipse2D.Float(10, 10, 100, 100);
			
			// Round off the rectangle be defining arc height then arc width
			
			Shape drawRoundRec = new RoundRectangle2D.Double(25, 25, 50, 50, 45, 45);
			
			// Draw a curve with 4 points
			
			CubicCurve2D cubicCurve = new CubicCurve2D.Double();
			
			// You can also set the curve outside of the definition
			// x1, y1, ctrlx1, ctrly1, ctrlx2, ctrly2, x2, y2
			
			cubicCurve.setCurve(110, 50, 300,
					200, 200, 200, 90, 263);
			
			// Draw rectangle by defining upper left x, y and width then height
			
			Shape drawRect = new Rectangle2D.Float(300, 300, 150, 100);
			
			// // Draw a curve with 3 points
			// x1, y1, ctrlx1, ctrly1, x2, y2
			
			Shape drawQuadCurve = new QuadCurve2D.Float(300, 100, 400, 200, 150, 300);
			
			Shape drawTransRect = new Rectangle2D.Double(300, 300, 75, 50);
			
			// Paint object defines the color used for rendering
			
			graph2.setPaint(Color.BLACK);
			
			// Draws a shape based on the preferences that have been set
			
			graph2.draw(drawLine);
			
			graph2.draw(drawArc2D);
			
			graph2.draw(drawArc2D2);
			
			graph2.draw(drawArc2D3);
			
			graph2.draw(drawEllipse);
			
			// Set the fill color
			
			graph2.setColor(Color.GREEN);
			
			// Draw a shape with a fill
			
			graph2.fill(drawRoundRec);
			
			graph2.fill(drawRect);
			
			graph2.setPaint(Color.BLACK);
			
			graph2.draw(cubicCurve);
			
			graph2.draw(drawRect);
			
			graph2.draw(drawQuadCurve);
			
			// This makes everything drawn after to be 60% transparent
			
			graph2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.40F));
			
			// This eliminates transparency
			
			graph2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
			
			// starting x point, starting y point, start color, end x, end y, end color
			// You can use hex color codes 0x66ffff equals color.CYAN
			// VERTICAL GRADIENT
			
			GradientPaint theGradient = new GradientPaint(0,0, Color.BLUE, 0,60, new Color(0x66ffff));
			
			// HORIZONTAL GRADIENT
			// GradientPaint theGradient = new GradientPaint(0,0, Color.BLUE, 75,0, new Color(0x66ffff));
			
			// To make the last color start in the center
			// GradientPaint theGradient = new GradientPaint(0,0, Color.BLUE, 0,60, new Color(0x66ffff), true);
			
			graph2.setPaint(theGradient);
			
			graph2.fill(new Rectangle2D.Float(10, 10, 150, 100));
			
			graph2.fill(drawTransRect);

			
		}
		
	}
	
}