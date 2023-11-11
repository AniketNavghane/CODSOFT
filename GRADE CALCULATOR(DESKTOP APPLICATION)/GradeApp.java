import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class App extends JFrame
{
	Container c;
	JLabel labtitle,labname,labsub,labmath,labsci,labeng,labhin,labmar,labavg,labpercent,labgrade;
	JTextField txtname,txtmath,txtsci,txteng,txthin,txtmar,txtavg,txtpercent,txtgrade;
	JButton btnSubmit, btnClear;


	App()
	{
		c = getContentPane();
		c.setLayout(null);
		
		labtitle = new JLabel("Grade Calculator App");
		labname = new JLabel("Enter Name");
		txtname = new JTextField(20);
		labsub = new JLabel("Enter Marks according subjects :-");
		labmar = new JLabel("Marathi");
		txtmar = new JTextField(10);
		labhin = new JLabel("Hindi");
		txthin = new JTextField(10);
		labeng = new JLabel("English");
		txteng = new JTextField(10);
		labmath = new JLabel("Mathematics");
		txtmath = new JTextField(10);
		labsci = new JLabel("Science");
		txtsci = new JTextField(10);
		labavg = new JLabel("Avg tottal :- ");
		txtavg = new JTextField(10);
		labpercent = new JLabel("Percentage :-");
		txtpercent = new JTextField(10);
		labgrade = new JLabel("Grade :- ");
		txtgrade = new JTextField(10);		
		btnSubmit = new JButton("Submit");
		btnClear = new JButton("Clear");

		Font f = new Font("Arial", Font.BOLD, 30);
		labtitle.setFont(f);
		labname.setFont(f);
		txtname.setFont(f);
		labsub.setFont(f);
		labmar.setFont(f);
		txtmar.setFont(f);
		labhin.setFont(f);
		txthin.setFont(f);
		labeng.setFont(f);
		txteng.setFont(f);
		labmath.setFont(f);
		txtmath.setFont(f);
		labsci.setFont(f);
		txtsci.setFont(f);
		labavg.setFont(f);
		txtavg.setFont(f);
		labpercent.setFont(f);
		txtpercent.setFont(f);
		labgrade.setFont(f);
		txtgrade.setFont(f);
		btnSubmit.setFont(f);
		btnClear.setFont(f);

		labtitle.setBounds(450,20,700,40);
		labname.setBounds(10,70,200,40);
		txtname.setBounds(200,70,450,40);
		labsub.setBounds(10,120,500,30);
		labmar.setBounds(10,170,200,40);
		txtmar.setBounds(200,170,200,40);
		labhin.setBounds(10,220,200,40);
		txthin.setBounds(200,220,200,40);
		labeng.setBounds(10,270,200,40);
		txteng.setBounds(200,270,200,40);
		labmath.setBounds(10,320,200,40);
		txtmath.setBounds(200,320,200,40);
		labsci.setBounds(10,370,200,40);
		txtsci.setBounds(200,370,200,40);
		labavg.setBounds(650,170,200,40);
		txtavg.setBounds(870,170,200,40);
		labpercent.setBounds(650,270,200,40);
		txtpercent.setBounds(870,270,200,40);
		labgrade.setBounds(650,370,200,40);
		txtgrade.setBounds(870,370,200,40);
		btnSubmit.setBounds(200,450,200,40);
		btnClear.setBounds(870,450,200,40);

		c.add(labtitle);
		c.add(labname);
		c.add(txtname);
		c.add(labsub);
		c.add(labmar);
		c.add(txtmar);
		c.add(labhin);
		c.add(txthin);
		c.add(labeng);
		c.add(txteng);
		c.add(labmath);
		c.add(txtmath);
		c.add(labsci);
		c.add(txtsci);
		c.add(labavg);
		c.add(txtavg);
		c.add(labpercent);
		c.add(txtpercent);
		c.add(labgrade);
		c.add(txtgrade);
		c.add(btnSubmit);
		c.add(btnClear);


		ActionListener a = (ae) -> {
		
			String name = txtname.getText();
			int mar = Integer.parseInt(txtmar.getText());
			int hin = Integer.parseInt(txthin.getText());
			int eng = Integer.parseInt(txteng.getText());
			int math = Integer.parseInt(txtmath.getText());
			int sci = Integer.parseInt(txtsci.getText());

			try
			{
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				String url = "jdbc:mysql://localhost:3306/GCA";
				String un = "root";
				String pw = "abc123";
				Connection con = DriverManager.getConnection(url,un,pw);
				String sql = "insert into studentinfo values(?,?,?,?,?,?)";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1,name);
				pst.setInt(2,mar);
				pst.setInt(3,hin);
				pst.setInt(4,eng);
				pst.setInt(5,math);
				pst.setInt(6,sci);
				pst.executeUpdate();
				//JOptionPane.showMessageDialog(c, "Successfully stored");
				con.close();

				
				double avg = mar + hin + eng + math + sci;
				//String total = Integer.toString(avg); 				

				double percent = (avg/500)*100;
				//String percentage = Double.toString(percent);
				//System.out.println(percent);
				

				//set value 

				txtavg.setText(Double.toString(avg));
				txtpercent.setText(Double.toString(percent));
				
				String grade;
				if(percent > 90){
					grade = "A+";
				}
				else if(percent > 75){
					grade = "A";
				}
				else if(percent > 65){
					grade = "B";
				}
				else if(percent > 55){
					grade = "C";
				}
				else if(percent > 45){
					grade = "E";
				}
				else if(percent >= 35){
					grade = "Pass";
				}
				else{
					grade = "Fail";
				}
			
			txtgrade.setText(grade);

	
			}
			catch(SQLException er)
			{
				JOptionPane.showMessageDialog(c, "issue" +er);
			}
		}; 
		btnSubmit.addActionListener(a);
				
		
		ActionListener a2 = (ae) -> {
			
			txtname.setText("");
			txtmar.setText("");
			txthin.setText("");
			txteng.setText("");
			txtmath.setText("");
			txtsci.setText("");
			txtavg.setText("");
			txtpercent.setText("");
			txtgrade.setText("");		

		}; 
		btnClear.addActionListener(a2);
	
	setTitle("Grade Calculator App");
	setSize(1200,800);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	}
}

class GradeApp 
{
	public static void main(String args[])
	{
		App a = new App();
	}
}