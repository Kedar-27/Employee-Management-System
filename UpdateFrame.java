import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;






class UpdateFrame extends JFrame
{

	Container c;
	JLabel lblId,lblName;
	JButton btnSave,btnBack;
	JTextField txt1,txt2;
	JPanel p1,p2;




	UpdateFrame()
	{
		c=getContentPane();

		

		p1=new JPanel();

		lblId=new   JLabel("Id:");
		lblName=new JLabel("Name:");
		txt1 =new	JTextField(6);
		txt2 =new   JTextField(10);

		
		p1.add(lblId);
		p1.add(txt1);
		p1.add(lblName);
		p1.add(txt2);

		c.add(p1);


		p2=new JPanel();

		btnSave=new JButton("Save");
		btnBack=new JButton("Back");

		p2.add(btnSave);
		p2.add(btnBack);


		c.add("South",p2);



		btnSave.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent ae)
			{	


				String i =txt1.getText();
				String n=txt2.getText();

				if(i.length()==0)
				{

					JOptionPane.showMessageDialog(new JDialog(),"Id is Empty");
					txt1.requestFocus();
					return;



				}


				if(n.length()==0)
				{
					JOptionPane.showMessageDialog(new JDialog(),"Name is Empty");

					txt2.requestFocus();
					return;
				}

				DatabaseHandler d =new DatabaseHandler();
				d.updateEmployee(Integer.parseInt(i),n);





				txt1.setText("");
				txt2.setText("");
				txt1.requestFocus();
			}	


			

		});



		btnBack.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent ae)
			{

				MainFrame a =new MainFrame();
				dispose();
			}
			

		});


		setSize(500,150);
		setTitle("Update Employee");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}



		
	








}