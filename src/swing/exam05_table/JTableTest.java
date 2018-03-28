package swing.exam05_table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JTableTest extends JFrame implements ActionListener{
		DefaultTableModel dtm;
		JTable table;
		JScrollPane scroll;
		JPanel north, south;
		JLabel label;
		JButton btn1,btn2,btn3,btn4;
		
		
		public JTableTest() {
			setTitle("�ȳ� ���̺�~^^");
			
			//setSize(500,500);
			label = new JLabel("< Job Table >");
			north = new JPanel();
			north.add(label);
			add("North",north);
			
			
			String[] columnNames = {"�̸�","����","����"};
			Object[][] rowData = {
					{"ȫ�浿",13,"�л�"},
					{"������",14,"ȭ��"},
					{"�����",16,"����"}
			};
			
			dtm = new DefaultTableModel(rowData,columnNames);
			table = new JTable(dtm);
			scroll = new JScrollPane(table);
			add(scroll);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(400,300,300,300);
			
			south = new JPanel();
			btn1 = new JButton("�߰�");
			btn2 = new JButton("����");
			btn3 = new JButton("����");
			btn4 = new JButton("����");
			
			south.add(btn1);
			south.add(btn2);
			south.add(btn3);
			south.add(btn4);
			
				
			add("South",south);
			setVisible(true);
			
			btn1.addActionListener(this);
			btn2.addActionListener(this);
			btn3.addActionListener(this);
			btn4.addActionListener(this);
			
		}
	
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//Object evt = e.getSource();
			String evt = e.getActionCommand();
			
			switch(evt) {
			case "�߰�":
				new Form().setTitle("�Է���");
				break;
			case "����":
				new Form().setTitle("������");
				break;
			case "����":
				
				break;
			case "����":
				System.exit(0);
				break;
				
			}
		}
		
		class Form extends JFrame{
			JLabel lb1,lb2,lb3;
			JTextField tf1,tf2,tf3;
			JPanel p1,p2,p3;
			JButton btn1, btn2;
			
			public Form() {
				setTitle("");
				setBounds(300,300,220,300);
				lb1 = new JLabel("�̸�");
				lb2 = new JLabel("����");
				lb3 = new JLabel("����");
				
				tf1 = new JTextField();
				tf2 = new JTextField();
				tf3 = new JTextField();
				
				btn1 = new JButton("�Է�");
				btn2 = new JButton("���");
				
				setLayout(null);
				
				
				lb1.setBounds(30,30,30,30);
				lb2.setBounds(30,80,30,30);
				lb3.setBounds(30,130,60,30);
				
				tf1.setBounds(80,30,80,30);
				tf2.setBounds(80,80,80,30);
				tf3.setBounds(80,130,80,30);
				
				btn1.setBounds(30,190,60,30);
				btn2.setBounds(100,190,60,30);
				
				add(lb1);
				add(lb2);
				add(lb3);
				
				add(tf1);
				add(tf2);
				add(tf3);
				
				add(btn1);
				add(btn2);
				
				setBounds(300,300,220,300);
				setResizable(false);
				setVisible(true);
			
				
			}
			
		}

}


