import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class BalanceP extends JFrame
{
	Container c;
	JLabel labtitle,labbal;
	JTextField txtbal;
	JButton btnEnter,btnBack;

	BalanceP(){
	
		c = getContentPane();
		c.setLayout(null);

		labtitle = new JLabel("Balance In Your Account");
		labbal = new JLabel("RS");
		txtbal = new JTextField(10);
		btnBack = new JButton("Back");

		Font f = new Font("Arial", Font.BOLD, 30);
		labtitle.setFont(f);
		labbal.setFont(f);
		txtbal.setFont(f);
		btnBack.setFont(f);

		labtitle.setBounds(330,100,600,40);
		labbal.setBounds(350,200,100,40);
		txtbal.setBounds(450,200,200,40);
		btnBack.setBounds(380,300,200,40);

		c.add(labtitle);
		c.add(labbal);
		c.add(txtbal);
		c.add(btnBack);
			
			try
			{
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				String url = "jdbc:mysql://localhost:3306/CODSOFT";
				String un = "root";
				String pw = "abc123";
				Connection con = DriverManager.getConnection(url,un,pw);
				String sql = "select balance from Atmuser";
				PreparedStatement pst = con.prepareStatement(sql);
				//pst.executeUpdate();
				ResultSet rs = pst.executeQuery();
				//OptionPane.showMessageDialog(c, "Successfully login");
				//con.close();
				 while (rs.next()) {
					int Balance = rs.getInt("balance");
					txtbal.setText(Integer.toString(Balance));
					txtbal.setEditable(false);
				}
	
			}
			catch(SQLException er)
			{
				JOptionPane.showMessageDialog(c, "issue" +er);
			}

			ActionListener a = (ae) -> {
			MainMenu m = new MainMenu();
			dispose();
		};
		btnBack.addActionListener(a);

		setTitle("BANK ATM APP");
		setSize(1000,500);
		setLocationRelativeTo(null);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
}

class Bala
{
	public static void main(String args[]){
		BalanceP bp = new BalanceP();
	}
}