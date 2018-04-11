package swing.exam07_project01;

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

public class Member extends JFrame implements ActionListener,MouseListener{
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
	JLabel name;
	JLabel age;
	JLabel gender1;
	JLabel addr;
	JLabel phone;
	JLabel email;

	CheckboxGroup gender; // ���� �׷�ó��
	Checkbox male, female;// ���� ����

	JTextField tname;
	JTextField tage;

	JTextField taddr;
	JTextField tphone1;
	JTextField tphone2;
	JTextField tphone3;

	JTextField temail1;
	JTextField temail2;
	JComboBox emailcb;

	JComboBox search;
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
	  
	  static int idx =-1;
	public Member() { // ������
		this.setTitle("�ڹ� �ּҷ� ���� ���α׷�");

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

		title = new JLabel("�������� ���߰��� �ּҷϰ��� ���α׷�");
		title.setBounds(70, 8, 600, 25);
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

		name = new JLabel("�� ��");
		tname = new JTextField(20);
		age = new JLabel("�� ��");
		tage = new JTextField(20);
		gender1 = new JLabel("�� ��");
		gender = new CheckboxGroup();
		male = new Checkbox("����", gender, true);
		female = new Checkbox("����", gender, false);

		addr = new JLabel("�� ��");
		taddr = new JTextField(20);

		phone = new JLabel("����ó");
		tphone1 = new JTextField();
		JLabel phone1 = new JLabel("   -");
		JLabel phone2 = new JLabel("   -");
		tphone2 = new JTextField();
		tphone3 = new JTextField();

		email = new JLabel("�̸���");
		temail1 = new JTextField();
		JLabel email1 = new JLabel("  @");
		temail2 = new JTextField();
		emailcb = new JComboBox();
		emailcb.addItem("�����Է�");
		emailcb.addItem("naver.com");
		emailcb.addItem("daum.net");
		emailcb.addItem("gmail.com");
		emailcb.addItem("nate.com");

		name.setBounds(30, 20, 35, 25);
		tname.setBounds(70, 20, 180, 25);
		age.setBounds(260, 20, 35, 25);
		tage.setBounds(300, 20, 100, 25);
		gender1.setBounds(410, 20, 30, 25);
		male.setBounds(450, 20, 40, 25);
		female.setBounds(500, 20, 80, 25);

		addr.setBounds(30, 55, 35, 25);
		taddr.setBounds(70, 55, 470, 25);

		phone.setBounds(25, 90, 40, 25);
		tphone1.setBounds(70, 90, 140, 25);
		phone1.setBounds(210, 90, 25, 25);
		tphone2.setBounds(235, 90, 140, 25);
		phone2.setBounds(375, 90, 25, 25);
		tphone3.setBounds(400, 90, 140, 25);
		email.setBounds(25, 125, 40, 25);
		temail1.setBounds(70, 125, 140, 25);
		email1.setBounds(210, 125, 25, 25);
		temail2.setBounds(235, 125, 160, 25);
		emailcb.setBounds(410, 125, 130, 25);
		// northp �г� ���
		northp.add(name);
		northp.add(tname);
		northp.add(age);
		northp.add(tage);
		northp.add(gender1);
		northp.add(male);
		northp.add(female);
		northp.add(addr);
		northp.add(taddr);
		northp.add(phone);
		northp.add(tphone1);
		northp.add(tphone2);
		northp.add(tphone3);
		northp.add(phone1);
		northp.add(phone2);
		northp.add(email);
		northp.add(email1);
		northp.add(temail1);
		northp.add(temail2);
		northp.add(emailcb);

		this.add(northp);

		// ����° �г� orthp(�˻��� ...ī�װ�)
		orthp = new JPanel();
		orthp.setLayout(null);
		orthp.setBounds(0, 160, 600, 80);
		orthp.setBackground(Color.BLUE);

		search = new JComboBox();
		search.addItem("�˻��� ī�װ��� �����ϼ���");
		search.addItem("name");
		search.addItem("age");
		search.addItem("gender");
		search.addItem("addr");
		search.addItem("phone");
		search.addItem("email");
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

		input = new JButton("�Է�");
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
				
				"<HTML><HEAD><H4><font color=red>�̸�</font></H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>����</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>����</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>�ּ�</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>����ó</H4></HEAD></HTML>",
				 "<HTML><HEAD><H4>�̸���</H4></HEAD></HTML>" ,
				 "<HTML><HEAD><H4>IDX</H4></HEAD></HTML>"
				 };
		Object[][] rowData= {};
		dtm=new DefaultTableModel(rowData,columnNames);
		table=new JTable(dtm);
		scroll=new JScrollPane(table);//���ڷѸ��ο� ���̺��߰�
		
		table.addMouseListener(this);
	
		
		ConnectionDB(); //DB����
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
		String url = "jdbc:mysql://127.0.0.1:3306/world?useSSL=true&verifyServerCertificate=false";//SSL �����߻���..
		
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
	
	//==========��� ����ƮAll============
	public void getListAll() {
		dtm.setRowCount(0); // ǥ���� ��index ����.
		String sql = "select * from person";
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
				while(rs.next()) {
					String name = rs.getString("name");
					String phone = rs.getString("phone");
					String email = rs.getString("email");
					String age = rs.getString("age");
					String gender = rs.getString("gender");
					String addr = rs.getString("addr");
					int idx = rs.getInt("idx");
				
				Object[] list = {name,age,gender,addr,phone,email,idx};
				dtm.addRow(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//==========[ �κ� �˻� ] ============
		public void getListOpt(String arg1, String arg2) { //�÷���, �� ���� �˻�
			dtm.setRowCount(0); // ǥ���� ��index ����.
			String sql = "select * from person";
					sql += " where "+ arg1 + " like  '%" + arg2 + "%'";
					
			try {
				
				System.out.println("�˻�:"+ sql);
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
					while(rs.next()) {
						String name = rs.getString("name");
						String phone = rs.getString("phone");
						String email = rs.getString("email");
						String age = rs.getString("age");
						String gender = rs.getString("gender");
						String addr = rs.getString("addr");
						int idx = rs.getInt("idx");
					
					Object[] list = {name,age,gender,addr,phone,email,idx};
					dtm.addRow(list);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	//============ ��� �Է��ϱ� ============
	public void addList(String[] data) {
		PreparedStatement ps =null;
		String sql = "insert into person (name,age,gender,addr,phone,email)";
				sql += "values(?,?,?,?,?,?)";
				
				//String[] data = {name,age,addr,phone1+"-"+phone2 +"-"+ phone3, email1 +email2,genderStr};
				
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
				System.out.println("text:"+ name.getText());
				
				
	}
	//============ ��� ������Ʈ ============
		public void UpdateList(String[] data,int idx) {
			PreparedStatement ps =null;
			String sql = "update person ";
					sql += " set name = ?, age=?, gender=?, addr=?, phone =?, email=? ";
					sql += " where idx ="+ idx;
					
					//String[] data = {name,age,addr,phone1+"-"+phone2 +"-"+ phone3, email1 +email2,genderStr};
					
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
					//System.out.println("text:"+ name.getText());
					
					
		}
		
		
	
	// TextField �ʱ�ȭ 
	public void ItemClean() {
		tname.setText("");
		tage.setText("");
		taddr.setText("");
		tphone1.setText("");
		tphone2.setText("");
		tphone3.setText("");
		temail1.setText("");
		temail2.setText("");
		//gender.setSelectedCheckbox(1);
	}
	
	// TextField �ʱ�ȭ 
	public void selectedDataView(String[] values) {
			tname.setText(values[0]);
			tage.setText(values[1]);
			
			if(values[2].equals("����"))
				gender.setSelectedCheckbox(male);
			else
				gender.setSelectedCheckbox(female);
			
			taddr.setText(values[3]);
			tphone1.setText(values[4]);//����ó
			tphone2.setText(values[5]);
			tphone3.setText(values[6]);
			temail1.setText(values[7]);
			temail2.setText(values[8]);
		//gender.setSelectedCheckbox(1);
	}
	
	
	//ActionListner
	public void actionPerformed(ActionEvent e) {
	
		
		String evt = e.getActionCommand();
		if(evt.equals("�Է�")) {
			String name = tname.getText();
			String age = tage.getText();
			String addr = taddr.getText();
			String phone1 = tphone1.getText();
			String phone2 = tphone2.getText();
			String phone3 = tphone3.getText();
			String email1 = temail1.getText();
			String email2 = temail2.getText();
			String genderStr = gender.getSelectedCheckbox().getLabel(); //��,��
			
			if(name.equals("") || age.equals("") || addr.equals("") || phone1.equals("") || email1.equals("")) {
				MessageDialog("�����͸� �Է��ϼ���.");
			}else {
				System.out.println("�Է�else"+name);
				String[] data = {name,age,genderStr,addr,phone1+"-"+phone2 +"-"+ phone3, email1 +email2};
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
			String name = tname.getText();
			String age = tage.getText();
			String addr = taddr.getText();
			String phone1 = tphone1.getText();
			String phone2 = tphone2.getText();
			String phone3 = tphone3.getText();
			String email1 = temail1.getText();
			String email2 = temail2.getText();
			String genderStr = gender.getSelectedCheckbox().getLabel(); //��,��			
			if(!name.equals(null)) {
				String[] data = {name,age,genderStr,addr,phone1+"-"+phone2 +"-"+ phone3, email1 +"@"+email2};
				UpdateList(data,idx); //�� �Էµ� textField���� DB�� Insert�ϴ� �Լ� ȣ��
				getListAll(); //��ü ����Ʈ�� �ݿ�.
				
			}else {
				MessageDialog("������ ���̺��� �����ϼ���.");
			}
			
			ItemClean();//�ʱ�ȭ.
			
		}else if (o== delete) {
			if(idx == -1) {
				MessageDialog("������ ���̺��� �����ϼ���.");
			}else {
				deleteMember(idx);
			}
		}
		
	}
		
		
	private void deleteMember(int idx) {
		PreparedStatement ps =null;
		String sql = "delete  from  person ";
				sql += " where idx ="+ idx;
				
				//String[] data = {name,age,addr,phone1+"-"+phone2 +"-"+ phone3, email1 +email2,genderStr};
				
				try {
					ps =  conn.prepareStatement(sql);

					System.out.println("sql:"+ps.toString());
					
					if(ps.executeUpdate() > 0) {
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
				//System.out.println("text:"+ name.getText());
		
	}

	public static void main(String[] args) {
		new Member();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
			 int iRow =table.getSelectedRow();
	
			 String val0 = (String) table.getValueAt(iRow,0);//�̸�
			 String val1 = (String) table.getValueAt(iRow,1);//����
			 String val2 = (String) table.getValueAt(iRow,2);//����
			 String val3 = (String) table.getValueAt(iRow,3);//�ּ�
			 String val4 = (String) table.getValueAt(iRow,4);//����ó
			 String val5 = (String) table.getValueAt(iRow,5); //�̸���
			 idx = (int) table.getValueAt(iRow,6);//index 
			 String[] phone = val4.split("-");
			 String[] email = val5.split("@");

			System.out.println(val0 +","+val1 +","+val2+","+val3+","+phone[2]+","+val5);
			String[] values= {val0,val1,val2,val3,phone[0],phone[1],phone[2],email[0],email[1]};
			
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


	
