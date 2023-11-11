import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class MainMenu extends JFrame
{
	Container c;
	JLabel labtitle;
	JButton btnWidraw, btnDeposite, btnBalance, btnLogout;

	MainMenu()
	{
		c = getContentPane();
		c.setLayout(null);
		
		labtitle = new JLabel("WELCOME ABC BANK ATM");
		btnWidraw = new JButton("Withdrawal");
		btnDeposite = new JButton("Deposite");
		btnBalance = new JButton("Balance");
		btnLogout = new JButton("Cancle");
		


		Font f = new Font("Arial", Font.BOLD, 30);
		labtitle.setFont(f);
		btnWidraw.setFont(f);
		btnDeposite.setFont(f);
		btnBalance.setFont(f);
		btnLogout.setFont(f);	
			

		labtitle.setBounds(300,20,700,40);
		btnWidraw.setBounds(145,150,200,40);
		btnDeposite.setBounds(650,150,200,40);
		btnBalance.setBounds(145,300,200,40);
		btnLogout.setBounds(650,300,200,40);

		c.add(labtitle);
		c.add(btnWidraw);
		c.add(btnDeposite);
		c.add(btnBalance);
		c.add(btnLogout);

		ActionListener a1 = (ae) -> {
			Widraw w  = new Widraw();
			dispose();
		};
		btnWidraw.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			Deposite d = new Deposite();
			dispose();
		};
		btnDeposite.addActionListener(a2);
		
		ActionListener a3 = (ae) -> {
			Balance b = new Balance();
			dispose();
		};
		btnBalance.addActionListener(a3);

		ActionListener a4 = (ae) -> {
			ATMAPP a = new ATMAPP();
			dispose();
		};
		btnLogout.addActionListener(a4);
		

	
		setTitle("Bank ATM App");
		setSize(1000,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
