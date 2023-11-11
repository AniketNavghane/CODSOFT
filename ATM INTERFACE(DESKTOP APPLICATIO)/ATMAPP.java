import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class ATMAPP extends JFrame
{
	Container c;
	JLabel labtitle,labcard,labpin;
	JTextField txtcard,txtpin;
	JButton btnEnter;


	ATMAPP()
	{
		c = getContentPane();
		c.setLayout(null);
		
		labtitle = new JLabel("ABC BANK ATM");
		labcard = new JLabel("Enter Card No");
		txtcard = new JTextField(30);
		labpin = new JLabel("Enter PIN");
		txtpin = new JTextField(10);
		btnEnter = new JButton("Enter");

		Font f = new Font("Arial", Font.BOLD, 30);
		labtitle.setFont(f);
		labcard.setFont(f);
		txtcard.setFont(f);
		labpin.setFont(f);
		txtpin.setFont(f);
		btnEnter.setFont(f);

		labtitle.setBounds(350,20,700,40);
		labcard.setBounds(10,90,500,40);
		txtcard.setBounds(250,90,450,40);
		labpin.setBounds(10,160,200,40);
		txtpin.setBounds(250,160,200,40);
		btnEnter.setBounds(250,230,200,40);

		c.add(labtitle);
		c.add(labcard);
		c.add(txtcard);
		c.add(labpin);
		c.add(txtpin);
		c.add(btnEnter);


		ActionListener a = (ae) -> {
		
			int card = Integer.parseInt(txtcard.getText());
			int pin = Integer.parseInt(txtpin.getText());

			try
			{
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				String url = "jdbc:mysql://localhost:3306/CODSOFT";
				String un = "root";
				String pw = "abc123";
				Connection con = DriverManager.getConnection(url,un,pw);
				String sql = "select * from Atmuser where cardno = ? and pin = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1,card);
				pst.setInt(2,pin);
				//pst.executeUpdate();
				ResultSet rs = pst.executeQuery();
				//OptionPane.showMessageDialog(c, "Successfully login");
				//con.close();
				if(rs.next()){
					MainMenu m = new MainMenu();
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
		btnEnter.addActionListener(a);
				
		
	
	setTitle("Bank ATM App");
	setSize(1000,500);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	}
}

class App{
	public static void main(String args[]){
		ATMAPP a = new ATMAPP();
	}
}
