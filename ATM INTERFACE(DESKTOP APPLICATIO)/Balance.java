import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class Balance extends JFrame
{
	Container c;
	JLabel labtitle,labpin;
	JTextField txtpin;
	JButton btnEnter,btnBack;

	Balance(){
	
		c = getContentPane();
		c.setLayout(null);

		labtitle = new JLabel("Enter Your Pin Here");
		labpin = new JLabel("Pin");
		txtpin = new JTextField(10);
		btnEnter = new JButton("Enter");
		btnBack = new JButton("Back");

		Font f = new Font("Arial", Font.BOLD, 30);
		labtitle.setFont(f);
		labpin.setFont(f);
		txtpin.setFont(f);
		btnEnter.setFont(f);
		btnBack.setFont(f);

		labtitle.setBounds(350,100,600,40);
		labpin.setBounds(350,200,100,40);
		txtpin.setBounds(450,200,200,40);
		btnEnter.setBounds(250,300,200,40);
		btnBack.setBounds(550,300,200,40);

		c.add(labtitle);
		c.add(labpin);
		c.add(txtpin);
		c.add(btnEnter);
		c.add(btnBack);

		ActionListener a1 = (ae) -> {

			int pin = Integer.parseInt(txtpin.getText());
			
			try
			{
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				String url = "jdbc:mysql://localhost:3306/CODSOFT";
				String un = "root";
				String pw = "abc123";
				Connection con = DriverManager.getConnection(url,un,pw);
				String sql = "select * from Atmuser where pin = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1,pin);
				//pst.executeUpdate();
				ResultSet rs = pst.executeQuery();
				//OptionPane.showMessageDialog(c, "Successfully login");
				//con.close();
				if(rs.next()){
					BalanceP bp = new BalanceP();
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(c, "invalid details" );
				}
	
			}
			catch(SQLException er)
			{
				JOptionPane.showMessageDialog(c, "issue" +er);
			}
		}; 
		btnEnter.addActionListener(a1);

		
		ActionListener a2 = (ae) -> {
			MainMenu m = new MainMenu();
			dispose();
		};
		btnBack.addActionListener(a2);

		setTitle("BANK ATM APP");
		setSize(1000,500);
		setLocationRelativeTo(null);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

class Bal{
	public static void main(String args[]){
		Balance B = new Balance();
	}
}