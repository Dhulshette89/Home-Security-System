package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * The Class UserSelection.
 */
public class UserSelection extends JPanel{
	
	/** The view bill button. */
	private JButton viewBillButton;
	
	/** The new schedule button. */
	private JButton newScheduleButton;
	
	/** The manual button. */
	private JButton manualButton;
	
	/** The logout button. */
	private JButton logoutButton;
	
	/** The enable system button. */
	private JButton enableSystemButton;
	
	/** The disable system button. */
	private JButton disableSystemButton;
	
	/** The panel. */
	JPanel panel;
	
	/** The panel 1. */
	JPanel panel_1;
	/**
	 * Create the application.
	 */
	public UserSelection() {
		setBounds(0, 0,650,800);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		logoutButton = new JButton("");
		logoutButton.setBorder(BorderFactory.createEmptyBorder());
		logoutButton.setContentAreaFilled(false);
		logoutButton.setSize(60, 60);
		logoutButton.setIcon(new ImageIcon("logout.png"));
		panel.add(logoutButton);
		
		
		
		viewBillButton = new JButton("");
		viewBillButton.setBorder(BorderFactory.createEmptyBorder());
		viewBillButton.setContentAreaFilled(false);
		viewBillButton.setSize(60, 60);
		viewBillButton.setIcon(new ImageIcon("C:\\Users\\vaish\\workspace\\HomeSecuritySystem\\src\\bill.png"));
		panel.add(viewBillButton);
		
		newScheduleButton = new JButton("");
		newScheduleButton.setBorder(BorderFactory.createEmptyBorder());
		newScheduleButton.setContentAreaFilled(false);
		newScheduleButton.setSize(60, 60);
		newScheduleButton.setIcon(new ImageIcon("C:\\Users\\vaish\\workspace\\HomeSecuritySystem\\src\\newSchedule.png"));
		panel.add(newScheduleButton);
		
		manualButton = new JButton("");
		manualButton.setBorder(BorderFactory.createEmptyBorder());
		manualButton.setContentAreaFilled(false);
		manualButton.setSize(60, 60);
		manualButton.setIcon(new ImageIcon("C:\\Users\\vaish\\workspace\\HomeSecuritySystem\\src\\manual.png"));
		panel.add(manualButton);
		
		enableSystemButton = new JButton("");
		enableSystemButton.setBorder(BorderFactory.createEmptyBorder());
		enableSystemButton.setContentAreaFilled(false);
		enableSystemButton.setSize(60, 60);
		enableSystemButton.setIcon(new ImageIcon("C:\\Users\\vaish\\workspace\\HomeSecuritySystem\\src\\enable.png"));
		panel.add(enableSystemButton);
		
		disableSystemButton = new JButton("");
		disableSystemButton.setBorder(BorderFactory.createEmptyBorder());
		disableSystemButton.setContentAreaFilled(false);
		disableSystemButton.setSize(60, 60);
		disableSystemButton.setIcon(new ImageIcon("C:\\Users\\vaish\\workspace\\HomeSecuritySystem\\src\\disable.png"));
		panel.add(disableSystemButton);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		add(panel_1);
	}

	/**
	 * Gets the view bill button.
	 *
	 * @return the view bill button
	 */
	public JButton getViewBillButton() {
		return viewBillButton;
	}

	/**
	 * Gets the new schedule button.
	 *
	 * @return the new schedule button
	 */
	public JButton getNewScheduleButton() {
		return newScheduleButton;
	}

	/**
	 * Gets the manual button.
	 *
	 * @return the manual button
	 */
	public JButton getManualButton() {
		return manualButton;
	}

	/**
	 * Gets the logout button button.
	 *
	 * @return the logout button button
	 */
	public JButton getlogoutButtonButton() {
		return logoutButton;
	}

	/**
	 * Gets the enable system button.
	 *
	 * @return the enable system button
	 */
	public JButton getEnableSystemButton() {
		return enableSystemButton;
	}

	/**
	 * Gets the disable system button.
	 *
	 * @return the disable system button
	 */
	public JButton getDisableSystemButton() {
		return disableSystemButton;
	}

	/**
	 * Gets the save panel.
	 *
	 * @return the save panel
	 */
	public JPanel getSavePanel() {
		return panel_1;
	}

	/**
	 * Sets the save panel.
	 *
	 * @param panel_1 the new save panel
	 */
	public void setSavePanel(JPanel panel_1) {
		this.panel_1 = panel_1;
	}
	

}



