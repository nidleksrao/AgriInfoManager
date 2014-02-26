package com.rao.agri.desktop.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.rao.agri.desktop.AimConstants;

public class AimApplication extends JFrame{

	private static final long serialVersionUID = -3511878237183448072L;

	private static AimApplication AIM_APPLICATION = null;
	
	private static Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static final Dimension preffredWindowSize = new Dimension(750, 600);
	public static final Dimension minimumWindowSize  = new Dimension(400, 400);
	
	
	private AimApplication(){
		
		setTitle(AimConstants.APP_TITLE);

		// Lets save the changes, release resources and then close the application later
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		class ext extends WindowAdapter
		{
			public void windowClosing(WindowEvent e)
			{
				exit();
			}
		}
		addWindowListener(new ext());

		init();
		
		
	}
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);

		//WinSplash.start();
		AIM_APPLICATION = new AimApplication();

		//WinSplash.stop();
		AIM_APPLICATION.setVisible(true);

	}
	
	
	private void init()
	{
		// Add menu bar
		setJMenuBar(new AimMenuBar());

		// Intialize the container
		Container mRootPanel = getContentPane();
		mRootPanel.setLayout(new BorderLayout());

		// Set the preffered size for this window.
		if(ScreenSize.width < preffredWindowSize.width) preffredWindowSize.width = ScreenSize.width;
		if(ScreenSize.height < preffredWindowSize.height) preffredWindowSize.height = ScreenSize.height;
		
		setSize(preffredWindowSize);		
		setMinimumSize(minimumWindowSize);

		// get coordinates for location
		int w = ScreenSize.width - preffredWindowSize.width;
		int h = ScreenSize.height - preffredWindowSize.height;
		
		setLocation(w/2, h/2);
		setResizable(true);
	}
	
	
	public void exit(){
		int opt = JOptionPane.showConfirmDialog(this, AimConstants.CLOSE_MESSAGE);
		
		if (opt == JOptionPane.OK_CANCEL_OPTION) return;
		if (opt == JOptionPane.NO_OPTION) return;
		if (opt == JOptionPane.CLOSED_OPTION) return;
		
		if(opt == JOptionPane.YES_OPTION){
			// TODO, Release of resource
		}
		dispose();
	}
	
	
	class AimMenuBar extends JMenuBar implements ActionListener
	{
		private static final long serialVersionUID = 1L;
		private JMenuItem exitMenuItem = new JMenuItem("Exit");
		private JMenuItem configMenuItem  = new JMenuItem("Config");
		private JMenuItem aboutMenuItem = new JMenuItem("About");

		AimMenuBar()
		{
			JMenu  fileMenu = new JMenu("File");
			JMenu  editMenu = new JMenu("Edit");
			JMenu  viewMenu = new JMenu("View");
			JMenu  helpMenu = new JMenu("Help");

			exitMenuItem.addActionListener(this);
			configMenuItem.addActionListener(this);
			aboutMenuItem.addActionListener(this);

			fileMenu.add(exitMenuItem);
			editMenu.add(configMenuItem);
			helpMenu.add(aboutMenuItem);

			add(fileMenu);
			add(editMenu);
			add(viewMenu); 
			viewMenu.setEnabled(false);
			add(helpMenu);
		}
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == exitMenuItem)
			{
				exit();
			}
			else if(e.getSource() == configMenuItem)
			{
				//TODO
			}
			else if(e.getSource() == aboutMenuItem)
			{
				//TODO 
			}
		}
	}
}
