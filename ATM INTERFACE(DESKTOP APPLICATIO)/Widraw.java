import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;

class Widraw extends JFrame
{
	Container c;
	JLabel labtitle,labrs;
	JTextField txtrs;
	JButton btnEnter,btnBack;

	Widraw(){
	
		c = getContentPane();
		c.setLayout(null);

		labtitle = new JLabel("Enter Withdrawal Amount here");
		labrs = new JLabel("RS");
		txtrs = new JTextField(10);
		btnEnter = new JButton("Proceed");
		btnBack = new JButton("Back");

		Font f = new Font("Arial", Font.BOLD, 30);
		labtitle.setFont(f);
		labrs.setFont(f);
		txtrs.setFont(f);
		btnEnter.setFont(f);
		btnBack.setFont(f);

		labtitle.setBounds(250,100,600,40);
		labrs.setBounds(300,200,100,40);
		txtrs.setBounds(420,200,200,40);
		btnEnter.setBounds(250,300,200,40);
		btnBack.setBounds(500,300,200,40);

		c.add(labtitle);
		c.add(labrs);
		c.add(txtrs);
		c.add(btnEnter);
		c.add(btnBack);
		
		ActionListener a1 = (ae) -> {

			int Rs = Integer.parseInt(txtrs.getText());

			try
			{
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				String url = "jdbc:mysql://localhost:3306/CODSOFT";
				String un = "root";
				String pw = "abc123";
				Connection con = DriverManager.getConnection(url,un,pw);
				String sql = "select * from Atmuser";
				PreparedStatement pst = con.prepareStatement(sql);
				//pst.executeUpdate();
				ResultSet rs = pst.executeQuery();
				JOptionPane.showMessageDialog(c,"Withdraw Transaction Successfull");
				//con.close();
				 while (rs.next()) {
					String Name = rs.getString("name");
					int Balance = rs.getInt("balance");
					int cardNo = rs.getInt("cardno");
					int updateBalance = Balance - Rs;
					String updateQuery = "UPDATE Atmuser SET balance=? WHERE cardno=?";
					PreparedStatement update = con.prepareStatement(updateQuery);
					update.setInt(1,updateBalance);
					update.setInt(2,cardNo);
					update.executeUpdate();
					
				
				//mail kaha se ayega

						Properties p = System.getProperties();
						p.put("mail.smtp.host","smtp.gmail.com");
						p.put("mail.smtp.port",587);
						p.put("mail.smtp.auth",true);
						p.put("mail.smtp.starttls.enable",true);

				//aapka un and pw ka authentication

						Session ms = Session.getInstance(p, new Authenticator(){
						public PasswordAuthentication getPasswordAuthentication(){
						String un = "ak.aniketnavghane1@gmail.com";
						String pw = "rnauxvurjegoxhgz";
						return new PasswordAuthentication(un,pw);
						}
						});
						

					try
					{					
						//mail ko draft krke bhejo

						MimeMessage msg = new MimeMessage(ms);
						String subject = "Transaction Mail From ABC Bank To Dear Account Holder " + Name;
						msg.setSubject(subject);
						String txt = "Your Withdrawal Amount is :- "+Rs+"\n Your Balance Now :- "+updateBalance+"\n Thank you!";
						msg.setText(txt);
						msg.setFrom(new InternetAddress("ak.aniketnavghane01@gmail.com"));
						msg.addRecipient(Message.RecipientType.TO, new InternetAddress("anavghanecompdse2022@gmail.com"));
						Transport.send(msg);

					}catch(Exception e){
							JOptionPane.showMessageDialog(c, "issue" +e);
					}
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

class Wid{
	public static void main(String args[]){
		Widraw w = new Widraw();
	}
}