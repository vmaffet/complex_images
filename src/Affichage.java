import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Affichage extends JFrame implements ActionListener{

	JPanel aff, control;
	JTextField fileName;
	JButton[] boutons;
	JButton appliq0, appliq1, appliq2, appliq3, appliq4, appliq5;
	BufferedImage img, transf;
	Point centre, ancrage;
	Graphics gFond;
	
	public static void main (String[] args) {
		new Affichage();
	}
	
	public Affichage () {
		super("Traitement d'images avec complexes");
		setSize(1800, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(200, 500));
		
		control= new JPanel();
		aff= new JPanel(){
			public void paint (Graphics g) {
				g.drawImage(transf, 0, 0, this);
			}
		};
		
		boutons= new JButton[14];
		
		for (int i= 0; i<boutons.length; i++) {
			boutons[i]= new JButton();
			boutons[i].addActionListener(this);
			boutons[i].setActionCommand(String.format("%d", i));
			control.add(boutons[i]);
		}
		
		boutons[0].setText("f(z)= z");
		boutons[1].setText("f(z)= (z/30)^2");
		boutons[2].setText("f(z)= z^2");
		boutons[3].setText("f(z)= 100000/z");
		boutons[4].setText("f(z)= (0.5+0.2i)z");
		boutons[5].setText("f(z)= (500z)^0.5");
		boutons[6].setText("f(z)= (z/50)^3");
		boutons[7].setText("f(z)= (z/70)^4");
		boutons[8].setText("f(z)= exp(z/80)");
		boutons[9].setText("f(z)= cos(z/50)");
		boutons[10].setText("f(z)= tan(z/280)");
		boutons[11].setText("f(z)= exp(2000/z)");
		boutons[12].setText("julia 50, 0.285+0.01i");
		boutons[13].setText("julia 20, -0.038088+0.9754633i");
		
		fileName= new JTextField("combodo.jpg");
		
		add(fileName, BorderLayout.NORTH);
		add(aff, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);	
	
		setVisible(true);
		try {
			transf= ImageIO.read(new File("accueil.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		aff.repaint();
	}
	
	@Deprecated
	public void transformeeDirecte () {
		Point d;
		Complexe z;
		gFond.setColor(Color.white);
		gFond.fillRect(0, 0, aff.getWidth(), aff.getHeight());
		for (double i= 0; i<img.getHeight(); i+= 0.05) {
			for (double j= 0; j<img.getWidth(); j+= 0.05) {
				z= new Complexe(repereCartesien(new Point2D.Double(j+ancrage.x, i+ancrage.y)));
				z= z.f1();
				d= repereFenetre(z.toPoint());
				gFond.setColor(new Color(img.getRGB((int)j, (int)i)));
				gFond.drawLine(d.x, d.y, d.x, d.y);
			}
		}
		aff.repaint();
	}
	
	public void transformeeReciproque (int n) {
		Point d;
		Complexe z;
		for (int i= 0; i<aff.getHeight(); i++) {
			for (int j= 0; j<aff.getWidth(); j++) {
				z= new Complexe(repereCartesien(new Point2D.Double(j, i)));
				
				switch (n) {
				case 0:
					z= z.f0();
					break;
				case 1:
					z= z.f1();
					break;
				case 2:
					z= z.f2();
					break;
				case 3:
					z= z.f3();
					break;
				case 4:
					z= z.f4();
					break;
				case 5:
					z= z.f5();
					break;
				case 6:
					z= z.f6();
					break;
				case 7:
					z= z.f7();
					break;
				case 8:
					z= z.f8();
					break;
				case 9:
					z= z.f9();
					break;
				case 10:
					z= z.f10();
					break;
				case 11:
					z= z.f11();
					break;
				case 12:
					z= z.f12(50, new Complexe(0.285, 0.01));
					break;
				case 13:
					z= z.f12(20, new Complexe(-0.038088, 0.9754633));
					break;
				default :
					return;
				}
				
				d= z.toPoint();
				d.x-= ancrage.x;
				d.y+= ancrage.y;
				d= repereFenetre(d);
				d.x%= img.getWidth();
				d.y%= img.getHeight();
				if (d.x < 0) {
					d.x+= img.getWidth();
				}
				if (d.y < 0) {
					d.y+= img.getHeight();
				}
				gFond.setColor(new Color(img.getRGB(d.x, d.y)));
				gFond.drawLine(j, i, j, i);
			}
		}
		aff.repaint();
	}
	
	public void actionPerformed (ActionEvent e) {
		try {
			img= ImageIO.read(new File(fileName.getText()));
		} 
		catch(Exception err) {
			System.out.println("Image introuvable !");
		}
		centre= new Point(aff.getWidth()/2, aff.getHeight()/2);
		transf= new BufferedImage(aff.getWidth(), aff.getHeight(), BufferedImage.TYPE_INT_RGB);
		gFond= transf.getGraphics();
		ancrage= repereFenetre(new Point (-img.getWidth()/2, img.getHeight()/2));
		transformeeReciproque(Integer.parseInt(e.getActionCommand()));
	}
	
	public Point2D.Double repereCartesien (Point2D.Double a) {
		return new Point2D.Double(a.x-centre.x, centre.y-a.y);
	}
	
	public Point repereFenetre (Point a) {
		return new Point(a.x+centre.x, centre.y-a.y);
	}
	
}
