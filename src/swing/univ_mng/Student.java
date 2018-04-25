package swing.univ_mng;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Student extends JFrame implements ActionListener,MouseListener{
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scroll;

	Toolkit tk; // �̹��� ������
	Image logo;

	JPanel nnorthp; // �ڹ� ���̺�...
	JPanel northp; // �̸� ���̺�...
	JPanel orthp; // ī�װ�
	JPanel tablep; // ���̺�
	JPanel southp; // ��ư

	
	JLabel title;
	
	JLabel label1,label2,label3,label3_1,label4,label5,label6,label7;//��(�й�,�̸�,�ֹ�,�ּ�,���г⵵,����,��������)
	JTextField tlabel1,tlabel2,tlabel3,tlabel3_1,tlabel4,tlabel5,tlabel6,tlabel7;//�� input box
	
	

	JComboBox search,majorselect;
	JTextField tsearch;
	JButton bsearch;
	JButton tbsearch;

	JButton input;
	JButton edit;
	JButton delete;
	JButton exit;

	Font f1, f2;

	  Connection conn=null;
	  Statement stmt =null;
	  PreparedStatement pstmt =null;
	  ResultSet rs = null;
	  
	  static int std_idx =-1;
	  
	  
	public Student() { // ������
		ConnectionDB(); //DB����
		this.setTitle("�л� ��� ����");

		Toolkit tk = Toolkit.getDefaultToolkit(); // �̹��� ���ͼ� ���
		logo = tk.getImage("c:\\�̹���/5.jpg"); // �����ӿ� �̹��� ���
		this.setIconImage(logo); // JFrame�� �ø���

		f1 = new Font("����", Font.BOLD, 23);
		f2 = new Font("serif", Font.BOLD, 20);

		this.setLayout(null);
		this.setBounds(50, 50, 600, 650);

		// ù��° nnorthp �г�(�������� ���̺�...)
		nnorthp = new JPanel();
		nnorthp.setLayout(null);
		nnorthp.setBounds(0, 0, 600, 40);

		title = new JLabel("�л� ���");
		title.setBounds(220, 8, 600, 25);
		title.setFont(f1);
		title.setForeground(Color.WHITE);
		nnorthp.setBackground(Color.BLACK);
		// nnorthp �г� ���
		nnorthp.add(title);
		this.add(nnorthp);

		// �ι�° northp �г�(�̸� ���̺�...)
		northp = new JPanel();
		northp.setLayout(null);
		northp.setBounds(0, 30, 600, 160);

		label1 = new JLabel("�й�");
		tlabel1 = new JTextField(20);
		
		label2 = new JLabel("�̸�");
		tlabel2 = new JTextField(20);
		
		
		label3 = new JLabel("�ֹι�ȣ");
		label3_1 = new JLabel("-");
		tlabel3 = new JTextField(20);
		tlabel3_1 = new JTextField(20);
		
		label4 = new JLabel("�ּ�");
		tlabel4 = new JTextField(20);
		
		label5 = new JLabel("���г⵵");
		tlabel5 = new JTextField(20);
		
		label6 = new JLabel("����");
		tlabel6 = new JTextField(20);
		
		label7 = new JLabel("��������");
		tlabel7 = new JTextField(20);
		

		label1.setBounds(20, 20, 60, 25);
		tlabel1.setBounds(60, 20, 80, 25);
		
		label2.setBounds(150, 20, 50, 25);
		tlabel2.setBounds(180, 20, 80, 25);
		
		label3.setBounds(270, 20, 60, 25);//�ֹι�ȣ
		tlabel3.setBounds(330, 20, 80, 25);
		label3_1.setBounds(420, 20, 10, 25);//�ֹι�ȣ
		tlabel3_1.setBounds(430,20,80,25);

		label4.setBounds(20, 60, 60, 25); //�ּ�
		tlabel4.setBounds(60, 60, 300, 25);

		label5.setBounds(20, 90, 80, 25);//���г⵵
		tlabel5.setBounds(60, 90, 150, 25);
		
		label6.setBounds(20, 120, 60, 25);//����
		tlabel6.setBounds(60, 120, 150, 25);
		
		label7.setBounds(270, 120, 60, 25); //����
		tlabel7.setBounds(340, 120, 150, 25);		

	

		// northp �г� ���
		northp.add(label1);
		northp.add(tlabel1);
		northp.add(label2);
		northp.add(tlabel2);
		northp.add(label3);
		northp.add(tlabel3);
		northp.add(label3_1);
		northp.add(tlabel3_1);
		northp.add(label4);
		northp.add(tlabel4);
		northp.add(label5);
		northp.add(tlabel5);
		northp.add(label6);
		northp.add(tlabel6);
		northp.add(label7);
		northp.add(tlabel7);		
	
		this.add(northp);

		// ����° �г� orthp(�˻��� ...ī�װ�)
		orthp = new JPanel();
		orthp.setLayout(null);
		orthp.setBounds(0, 160, 600, 80);
		orthp.setBackground(Color.BLUE);

		search = new JComboBox();
		search.addItem("�˻��� ī�װ��� �����ϼ���");
		search.addItem("std_code");
		search.addItem("std_name");
		search.addItem("std_jumin");

		tsearch = new JTextField();
		bsearch = new JButton("��ȸ");
		tbsearch = new JButton("��ü��ȸ");

		search.setBounds(40, 42, 170, 25);
		tsearch.setBounds(230, 42, 140, 25);
		bsearch.setBounds(380, 42, 74, 25);
		tbsearch.setBounds(470, 42, 90, 25);

		bsearch.setBackground(Color.GREEN);
		bsearch.setForeground(Color.RED);
		tbsearch.setBackground(Color.GREEN);
		tbsearch.setForeground(Color.RED);

		orthp.add(search);
		orthp.add(tsearch);
		orthp.add(bsearch);
		orthp.add(tbsearch);

		this.add(orthp);

		

		// �ټ���° southp �г�(��ư��)
		southp = new JPanel();
		southp.setLayout(null);
		southp.setBounds(5, 500, 600, 200);

		input = new JButton("���");
		edit = new JButton("����");
		delete = new JButton("����");
		exit = new JButton("����");

		input.setBounds(0, 70, 142, 40);
		edit.setBounds(146, 70, 142, 40);
		delete.setBounds(292, 70, 142, 40);
		exit.setBounds(438, 70, 138, 40);

		input.setFont(f2);
		input.setBackground(Color.BLUE);
		input.setForeground(Color.YELLOW);
		edit.setFont(f2);
		edit.setBackground(Color.YELLOW);
		edit.setForeground(Color.BLACK);
		delete.setFont(f2);
		delete.setBackground(Color.GREEN);
		delete.setForeground(Color.RED);
		exit.setFont(f2);
		exit.setBackground(Color.MAGENTA);
		exit.setForeground(Color.WHITE);

		southp.add(input);
		southp.add(edit);
		southp.add(delete);
		southp.add(exit);

		// this.add(southp, "SOUTH");

		//AcitionListener ���
		input.addActionListener( this); //�Է¹�ư.
		edit.addActionListener(this);
		bsearch.addActionListener(this); //��ȸ
		tbsearch.addActionListener(this); //��ü ��ȸ
		exit.addActionListener(this);//����
		delete.addActionListener(this);//����
		
		search.addItemListener(new ItemListener(){
			String category=null;
			@Override
			public void itemStateChanged(ItemEvent e) {
				category = (String)e.getItem();
				tsearch.requestFocus(); //��Ŀ�� �̵�.
				
			}
			
		});
		
		
		
		
		//�׹�° �г�.���̺� �г�
		
		String columnNames[]= {
				
				"<HTML><HEAD><H4><font color=red>�й�</font></H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>�̸�</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>�ֹι�ȣ</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>�ּ�</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>���г⵵</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>�����ڵ�</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>����</H4></HEAD></HTML>"
			 };
		Object[][] rowData= {};
		dtm=new DefaultTableModel(rowData,columnNames);
		table=new JTable(dtm);
		scroll=new JScrollPane(table);//���ڷѸ��ο� ���̺��߰�
		
		table.addMouseListener(this);
	
		
		
		getListAll(); //����Ʈ ��������.
		//addList();
		
		scroll.setBounds(0,230,600,250);
		
		add("Center",scroll);
				
		
		add("South",southp);
		
		this.setVisible(true);

	} // ������ ��

	// �˻�â ���� ó��.
	

	//========== DB ����==========
	public void ConnectionDB() {
		
		
		String driverName ="com.mysql.jdbc.Driver";
		//String url ="jdbc:mysql://localhost:3306/world";
		String url = "jdbc:mysql://127.0.0.1:3306/univ_mng?useSSL=true&verifyServerCertificate=false";//SSL �����߻���..
		
		String user="root";
		String password ="0000";
		
		try{
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("JDBC Driver Connection Success");
				
		}catch (ClassNotFoundException e){
			System.out.println("mysql jdbc driver can't loading..");
			//e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Ŀ�ؼ��� �������� ���Ͽ����ϴ�.");
		} 
	
	}
	
		
		
	
	//==========�л��Ѹ���ƮAll============
	public void getListAll() {
		dtm.setRowCount(0); // ǥ���� ��index ����.
		String sql = "select * from student";
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
				while(rs.next()) {
					std_idx = rs.getInt("std_idx"); //�л����� �Ϸù�ȣ
					String std_code = rs.getString("std_code");
					String std_name = rs.getString("std_name");
					String std_jumin = rs.getString("std_jumin");
					String std_addr = rs.getString("std_addr");
					String std_entrance = rs.getString("std_entrance_date");
					String std_major = rs.getString("std_major_code");
					String std_profcode = rs.getString("std_prof_code");
					
					
				
				Object[] list = {std_code,std_name,std_jumin,std_addr,std_entrance,std_major,std_profcode};
				dtm.addRow(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//==========[ �κ� �˻� ] ============
		public void getListOpt(String arg1, String arg2) { //�÷���, �� ���� �˻�
			dtm.setRowCount(0); // ǥ���� ��index ����.
			String sql = " select * from univ_mng.student ";
					sql += " where "+ arg1 + " like  '%" + arg2 + "%'";
					
			try {
				
				System.out.println("�˻�:"+ sql);
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
					while(rs.next()) {

						String std_code = rs.getString("std_code");
						String std_name = rs.getString("std_name");
						String std_jumin = rs.getString("std_jumin");
						String std_addr = rs.getString("std_addr");
						String std_entrance = rs.getString("std_entrance_date");
						String std_major = rs.getString("std_major_code");
						String std_profcode = rs.getString("std_prof_code");
						
						
					
					Object[] list = {std_code,std_name,std_jumin,std_addr,std_entrance,std_major,std_profcode};
					dtm.addRow(list);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	//============ ��� �Է��ϱ� ============
	public void addList(String[] data) {
		PreparedStatement ps =null;
		String sql = "insert into student (std_code,std_name,std_jumin,std_addr,std_entrance_date,std_major_code,std_prof_code)";
				sql += "values(?,?,?,?,?,?,?)";
				
						
				try {
					ps =  conn.prepareStatement(sql);
					for(int i =0 ; i < data.length; i++) {
						ps.setString(i+1, data[i]);	
					}
					
					System.out.println("sql:"+ps.toString());
					
					if(ps.executeUpdate() > 0) {
						System.out.println("insert success");
					}else {
						System.out.println("insert fail..");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("text:"+ label1.getText());
				
				
	}
	//============ ��� ������Ʈ ============
		public void UpdateList(String[] data,int std_idx) {
			PreparedStatement ps =null;
			String sql = "update student ";
					sql += " set std_name=?, std_jumin=?, std_addr=?, std_entrance_date=? , std_major_code=?,std_prof_code=? ";
					sql += " where std_code ="+ std_idx;
					
					
					try {
						ps =  conn.prepareStatement(sql);
						for(int i =0 ; i < data.length; i++) {
							ps.setString(i+1, data[i]);	
						}
						
						System.out.println("sql:"+ps.toString());
						
						if(ps.executeUpdate() > 0) {
							System.out.println("update success");
						}else {
							System.out.println("update fail..");
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println("text:"+ label1.getText());
					
					
		}
		
		
	
	// TextField �ʱ�ȭ 
	public void ItemClean() {
		tlabel1.setText("");
		tlabel2.setText("");
		tlabel3.setText("");
		tlabel3_1.setText("");
		tlabel4.setText("");
		tlabel5.setText("");
		tlabel6.setText("");
		tlabel7.setText("");
		

	}
	
	// TextField �ʱ�ȭ 
	public void selectedDataView(String[] values) {
			tlabel1.setText(values[0]);
			tlabel2.setText(values[1]);
			tlabel3.setText(values[2]);
			tlabel3_1.setText(values[3]);
			tlabel4.setText(values[4]);
			tlabel5.setText(values[5]);
			tlabel6.setText(values[6]);
			tlabel7.setText(values[7]);
			
	}
	
	
	//ActionListner
	public void actionPerformed(ActionEvent e) {
	
		
		String evt = e.getActionCommand();
		if(evt.equals("���")) {
			String val1 = tlabel1.getText();
			String val2 = tlabel2.getText();
			String val3 = tlabel3.getText();
			String val3_1 = tlabel3_1.getText();
			
			String val4 = tlabel4.getText();
			String val5 = tlabel5.getText();
			String val6 = tlabel6.getText();
			String val7 = tlabel7.getText();

			
			if(val1.equals("") || val2.equals("") || val3.equals("") ) {
				MessageDialog("�����͸� �Է��ϼ���.");
			}else {
				//System.out.println("item"+val1);
				String[] data = {val1,val2,val3+"-"+val3_1,val4,val5,val6,val7};
				addList(data); //�� �Էµ� textField���� DB�� Insert�ϴ� �Լ� ȣ��
				getListAll(); //��ü ����Ʈ�� �ݿ�.
				ItemClean();//�ʱ�ȭ.
			}
		}
		
		Object o = e.getSource();
		
		if(o ==exit) {
			System.exit(0);
		}else if(o == bsearch) {//�˻�
			
			String item = (String) search.getSelectedItem();
			
			String value = tsearch.getText();
			
			if(item.contains("�˻�") || value.equals("")) {
				MessageDialog("�˻������� �Է��ϼ���.");//�˾��޽���.
			}else {
				getListOpt(item,value);//
			}
			
		}else if(o == tbsearch) {//��ü �˻�.
			getListAll();
		}else if(o == edit) {
			//System.out.println("������� �Դϴ�.");
			String val1 = tlabel1.getText();//�й�
			String val2 = tlabel2.getText();
			String val3 = tlabel3.getText();
			String val4 = tlabel3_1.getText();
			String val5 = tlabel4.getText();
			String val6 = tlabel5.getText();
			String val7 = tlabel6.getText();
			String val8 = tlabel7.getText();
			
			
			//String item = (String) majorselect.getSelectedItem();
			
			//String[] items = item.split("]");
			//String code = items[0].replace("[", "");
			
			if(!val1.equals(null)) {
				String[] data = {val2,val3+"-"+val4,val5,val6,val7,val8};
				UpdateList(data,Integer.parseInt(val1)); //�� �Էµ� textField���� DB�� Insert�ϴ� �Լ� ȣ��
				getListAll(); //��ü ����Ʈ�� �ݿ�.
				
			}else {
				MessageDialog("������ ���̺��� �����ϼ���.");
			}
			
			ItemClean();//�ʱ�ȭ.
			
		}else if (o== delete) {
			if(std_idx == -1) {
				MessageDialog("������ ���̺��� �����ϼ���.");
			}else {
				deleteMember(std_idx);
			}
		}
		
	}
		
		
	private void deleteMember(int idx) {
		PreparedStatement ps =null;
		String sql = "delete  from  student ";
				sql += " where std_idx ="+ idx;
				
				//String[] data = {label1,age,addr,phone1+"-"+phone2 +"-"+ phone3, email1 +email2,genderStr};
				
				try {
					ps =  conn.prepareStatement(sql);

					System.out.println("sql:"+ps.toString());
					
					if(ps.executeUpdate() > 0) {
						MessageDialog("["+ std_idx + "] �����Ǿ����ϴ�.");
						getListAll(); //��ü ����Ʈ�� �ݿ�.
						ItemClean();//�ʱ�ȭ.
						System.out.println("delete success");
					}else {
						System.out.println("delete fail..");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("text:"+ label1.getText());
		
	}

	public static void main(String[] args) {
		new Student();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
			 int iRow =table.getSelectedRow();
	
			 String val0 = (String) table.getValueAt(iRow,0).toString();//�����ڵ�
			 String val1 = (String) table.getValueAt(iRow,1);//����
			 String val2_Data = (String) table.getValueAt(iRow,2);//�ֹ�
			 String[] val2 = val2_Data.split("-");
			 String val3 = (String) table.getValueAt(iRow,3);//������
			 String val4 = (String) table.getValueAt(iRow,4);//������
			 String val5 = (String) table.getValueAt(iRow,5);//������
			 String val6 = (String) table.getValueAt(iRow,6);//������
			 //String val7 = (String) table.getValueAt(iRow,7);//������

			//System.out.println(val0 +","+val1 +","+val2+","+val3+","+phone[2]+","+val5);
			String[] values= {val0,val1,val2[0],val2[1],val3,val4,val5,val6};
			//System.out.println(values.toString());
			
			selectedDataView(values);//�Է� box�� ���̱�.
	}

	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		
		
	public void MessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
}


	
