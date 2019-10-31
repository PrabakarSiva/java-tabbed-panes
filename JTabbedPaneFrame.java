// Fig. 25.13: JTabbedPaneFrame.java
// Demonstrating JTabbedPane.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class JTabbedPaneFrame extends JFrame  
{
	private JPanel mousePanel; // panel in which mouse events will occur
	private JLabel statusBar; // label that displays event information
	private JButton yesButton1;
	private GamePanel gamePanel;	//for tab 2
	private PaintPanel paintPanel;	//for tab 3
	
   public JTabbedPaneFrame()
   {
      super( "JTabbedPane Demo " );

      JTabbedPane tabbedPane = new JTabbedPane(); // create JTabbedPane 

      // set up pane11 and add it to JTabbedPane 
      mousePanel = new JPanel(); // create panel
      mousePanel.setLayout(null);
      mousePanel.setBackground( Color.WHITE ); // set background color
      add( mousePanel, BorderLayout.CENTER ); // add panel to JFrame

      statusBar = new JLabel( "Mouse outside JPanel" ); 
      add( statusBar, BorderLayout.SOUTH ); // add label to JFrame

      // create and register listener for mouse and mouse motion events
      MouseHandler handler = new MouseHandler(); 
      mousePanel.addMouseListener( handler ); 
      mousePanel.addMouseMotionListener( handler ); 
   
      yesButton1 = new JButton ("Catch Me");
      yesButton1.setLocation(400, 100);
      yesButton1.setSize(100, 30);
      mousePanel.add(yesButton1);
      ButtonHandler handler2 = new ButtonHandler();
      yesButton1.addActionListener(handler2);
      
      tabbedPane.addTab( "Tab One", null, mousePanel, "First Panel" ); 
      
      //gamePanel is just checkboard image from Q9 in midterm
      gamePanel = new GamePanel(); 
      tabbedPane.addTab( "Tab Two", null, gamePanel, "Second Panel" ); 

      //allows you to draw
      paintPanel = new PaintPanel();
      tabbedPane.addTab( "Tab Three", null, paintPanel, "Third Panel" );

      add( tabbedPane ); // add JTabbedPane to frame
   } // end JTabbedPaneFrame constructor
   
   //button handler for Catch Me in case button is hit 
   private class ButtonHandler implements ActionListener 
   {
      // handle button event
      public void actionPerformed( ActionEvent event )
      {
        if (event.getActionCommand() == "Catch Me") {
        	JOptionPane.showMessageDialog( null, "You Cheated.");  	 
         } 
      } // end method actionPerformed

	

   } // end private inner class ButtonHandler
   
   private class MouseHandler implements MouseListener, 
   MouseMotionListener 
   {
	   
   public void mouseEntered( MouseEvent event )
   {
	   
   } // end method mouseEntered
   
   // handle event when user moves mouse
   public void mouseMoved( MouseEvent event )
   {
      
      int mouseX = event.getX();
      int mouseY = event.getY();
      int x = yesButton1.getX();
      int y = yesButton1.getY();
      int newX = (int) (Math.random()*900);
      int newY = (int) (Math.random()*900);
      
      statusBar.setText( String.format( "Moved at [%d, %d]", 
	            mouseX, mouseY ) );
      
      if((mouseX <= (x+110) && mouseX >= x-50) && (mouseY <= (y+80) && mouseY >= y-50)) {
     	 yesButton1.setLocation(newX, newY);
     	 mousePanel.setBackground(Color.PINK);
      }
   } // end method mouseMoved
	
   @Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
   } // end inner class MouseHandler
   
   //gamePanel class which is just Q9 from Midterm
   public class GamePanel extends JPanel implements ListSelectionListener{
		
		//initializing components
		private JList colorJList; 
		private JList colorJList2;
		private JLabel list1Label;
		private JLabel list2Label;
		private JButton incBtn;
		private JButton decBtn;
		
		//colorNames used to change colors
		private final String[] colorNames = { "Black", "Blue", "Cyan",
			      "Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
			      "Orange", "Pink", "Red", "White", "Yellow" };
		//colors array used to change colors
		private final Color[] colors = { Color.BLACK, Color.BLUE,
			      Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, 
			      Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, 
			      Color.RED, Color.WHITE, Color.YELLOW };
		
		//used for drawing original checkerboard
		int x = 100;
		int y = 100;
		int choice = 1; //to choose between colors
		int delta = 1;	//spacing
		int size = 20;	//size of each square so we can change it using button
		Color color1 = new Color(120, 120, 120); //initial color (grey)
		Color color2 = new Color(0, 255, 255);	//initial color2 (cyan)
		
		public GamePanel()
		   {
		      setLayout( null ); // set frame layout
		      colorJList = new JList( colorNames ); // create with colorNames
		      // do not allow multiple selections
		      colorJList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		      colorJList.setSize(100,250);
		      colorJList.setLocation(500,500);
		      add(colorJList);
		      colorJList.addListSelectionListener(
		    	         new ListSelectionListener() // anonymous inner class
		    	         {   
		    	            // handle list selection events
		    	            public void valueChanged( ListSelectionEvent event )
		    	            {
		    	            	color1 = colors[colorJList.getSelectedIndex()]; //sets color1 to selected color
		    	        		repaint();	//redraws 
		    	            } // end method valueChanged
		    	         } // end anonymous inner class
		    	      ); // end call to addListSelectionListener
		      
		      //label to identify color 1 list
		      list1Label = new JLabel("Set Color1");
		      list1Label.setSize(100, 30);
		      list1Label.setLocation(500, 470);
		      add(list1Label);
		      
		      
		      colorJList2 = new JList( colorNames ); // create with colorNames
		      // do not allow multiple selections
		      colorJList2.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		      colorJList2.setSize(100,250);
		      colorJList2.setLocation(700,500);
		      add(colorJList2);
		      colorJList2.addListSelectionListener(
		    	         new ListSelectionListener() // anonymous inner class
		    	         {   
		    	            // handle list selection events
		    	            public void valueChanged( ListSelectionEvent event )
		    	            {
		    	            	color2 = colors[colorJList2.getSelectedIndex()]; //sets color2 to selected color
		    	        		repaint();	//redraws
		    	            } // end method valueChanged
		    	         } // end anonymous inner class
		    	      ); // end call to addListSelectionListener
		      
		      //label to identify color2 list
		      list2Label = new JLabel("Set Color2");
		      list2Label.setSize(100, 30);
		      list2Label.setLocation(700, 470);
		      add(list2Label);
		      
		      //button to increase size
		      incBtn = new JButton("Inc. Size");
		      incBtn.setSize(100, 30);
		      incBtn.setLocation(500, 200);
		      add(incBtn);
		      
		      //button to decrease size
		      decBtn = new JButton("Dec. Size");
		      decBtn.setSize(100, 30);
		      decBtn.setLocation(700, 200);
		      add(decBtn);
		      
		      //ButtonHandler initialization
		      ButtonHandler handler = new ButtonHandler();
		      incBtn.addActionListener(handler);
		      decBtn.addActionListener(handler);
		      
		   } 
		
		public void paint(Graphics g) {
			
			
			super.paint(g);
			for (int i = 1; i < 65; i++)	//draw checker board 
			{
				if (choice == 1)	//choice 1 = color1
				{
					g.setColor(color1);
					g.fillRect(x + size*delta, y, size, size);
					choice = 2;
				}
				else if (choice == 2)	//choice2 = color2
				{
					g.setColor(color2);
					g.fillRect(x + size*delta, y, size, size);
					choice = 1;
				}
				if ((i % 8) == 0) {	//to start new row every 8 squares
					y = y + size;	//start new row by increasing y 
					if (choice == 1)	//change choice for checkerboard pattern
						choice = 2;
					else
						choice = 1;
					delta = 0;	//resetting delta each time we go to a new row
					
					
				}
				delta++;	//increment delta like i
				
			}
			x = 100;	//reset x and y after drawing
			y = 100;
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		private class ButtonHandler implements ActionListener 
		   {
		      // handle button event
		      public void actionPerformed( ActionEvent event )
		      {

		        if (event.getActionCommand() == "Inc. Size") {
		        	if (size < 40)
		        	{
		        		size = size + 5;
		        	}
		        	repaint();
		        }
		        else if (event.getActionCommand() == "Dec. Size") {	
		        	if (size > 5)
		        	{
		        		size = size - 5;
		        	}
		        	repaint();
		        }
		        	 
		      } // end method actionPerformed

			

		   } // end private inner class ButtonHandler

		

	}
   
   //paint panel that allows you to draw
   public class PaintPanel extends JPanel 
   {
      private int pointCount = 0; // count number of points

      // array of 10000 java.awt.Point references
      private Point[] points = new Point[ 100000 ];  

      // set up GUI and register mouse event handler
      public PaintPanel()
      {
         // handle frame mouse motion event
         addMouseMotionListener(

            new MouseMotionAdapter() // anonymous inner class
            {  
               // store drag coordinates and repaint
               public void mouseDragged( MouseEvent event )
               {
                  if ( pointCount < points.length ) 
                  {
                     points[ pointCount ] = event.getPoint(); // find point
                     ++pointCount; // increment number of points in array
                     repaint(); // repaint JFrame
                  } // end if
               } // end method mouseDragged
            } // end anonymous inner class
         ); // end call to addMouseMotionListener
      } // end PaintPanel constructor

      // draw ovals in a 4-by-4 bounding box at specified locations on window
      public void paintComponent( Graphics g )
      {
         super.paintComponent( g ); // clears drawing area

         // draw all points in array
         for ( int i = 0; i < pointCount; i++ )
            g.fillOval( points[ i ].x, points[ i ].y, 4, 4 );
      } // end method paintComponent
   } // end class PaintPanel

}
   
