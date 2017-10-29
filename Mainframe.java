import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class MainFrame extends JFrame
{
	Container c;
	JButton  btnAdd,btnView,btnUpdate,btnDelete;


	MainFrame()
	{

		c= getContentPane();
		c.setLayout(new FlowLayout());


		btnAdd=new JButton("Add");
		btnView=new JButton("View");
		btnUpdate=new JButton("Update");
		btnDelete=new JButton("Delete");


		c.add(btnAdd);
		c.add(btnView);
		c.add(btnUpdate);
		c.add(btnDelete);




		btnAdd.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent ae)
			{

				AddFrame a=new AddFrame();
				dispose();
			}
			


		});



		btnView.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent ae)
			{

				ViewFrame a=new ViewFrame();
				dispose();
			}

			


		});



		btnUpdate.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent ae)
			{

				UpdateFrame a =new UpdateFrame();
				dispose();
			}
			


		});


		btnDelete.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent ae)
			{
			
			DeleteFrame d =new DeleteFrame();
			dispose();

			}


		});








		setSize(500,150);
		setLocationRelativeTo(null);  //to centrize application
		setTitle("Employee Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}


	public static void main(String args[])
	{

	MainFrame m =new MainFrame();

	










	}//end of main


}//end of class



class DatabaseHandler
{


	static Connection con;		// for maintaining only 1 copy


	static void getCon()
	{

		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

		}
		catch(SQLException e)
		{

			System.out.println(e);
		}




	}


	public void addEmployee(int id,String name)
	{

		getCon();


		try
		{

			String sql ="insert into employee values(?,?)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1,id);
			pst.setString(2,name);
			int r =pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(),r +"records inserted");


			con.close();


		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(),"Insert Issue");
		}





	}
  

	public String viewEmployee()
	{


		getCon();

		StringBuffer sb=new StringBuffer();


		try
		{

			Statement s1=con.createStatement();
			String s2="select * from employee";
			ResultSet rs =s1.executeQuery(s2);

			while(rs.next())
			{


				int id =rs.getInt(1);
				String name= rs.getString(2);

				sb.append("Id: "+ id +"Name: "+name+"\n");
			}

			rs.close();
			con.close();


		}
		catch(SQLException e)
		{

			
		}


		return sb.toString();

	}

	public void updateEmployee(int i, String n)
	{

		getCon();



		try
		{	
			String s1 ="Update employee set ename=(?) where eid=(?)";

			PreparedStatement pst =con.prepareStatement(s1);
			pst.setInt(2,i);
			pst.setString(1,n);



			int r =pst.executeUpdate();
			

			JOptionPane.showMessageDialog(new JDialog(),r + " records updated");

			pst.close();
			con.close();


		}
		catch(SQLException e)
		{
			
			System.out.println(e);


		}









	}









	public void deleteEmployee(int id)
	{
		getCon();




		try
		{
			
			String s2="delete from employee where eid=?";

			PreparedStatement pst=con.prepareStatement(s2);
			pst.setInt(1,id);
				
			int r =pst.executeUpdate();
			

			JOptionPane.showMessageDialog(new JDialog(),r + " records deleted");

			pst.close();
			con.close();


		}
		catch(SQLException e)
		{
			System.out.println(e);
		}



		




	}
	
}



