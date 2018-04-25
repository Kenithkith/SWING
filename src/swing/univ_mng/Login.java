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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Login extends JFrame implements ActionListener,MouseListener{
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
	
	JLabel majorcode,majorname,jumin,major2,label1,label2;// �����ڵ�, �кθ�, ������
	JTextField tmajorcode,tmajorname,tjumin;//�� input box
	

	JComboBox search,majorselect;
	JTextField tsearch;
	JButton bsearch;
	JButton tbsearch;
	JRadioButton r1,r2;
	

/*	JButton input;
	JButton edit;
	JButton delete;*/
	JButton exit;

	Font f1, f2;

	  Connection conn=null;
	  Statement stmt =null;
	  PreparedStatement pstmt =null;
	  ResultSet rs = null;
	  
	  static int idx =-1;
	public Login() { // ������
		ConnectionDB(); //DB����
		this.setTitle("�α��� ");

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

		title = new JLabel("���� ����");
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

		majorcode = new JLabel("���̵�");
		tmajorcode = new JTextField(20);
		
		majorname = new JLabel("���");
		tmajorname = new JTextField(20);
		
		
		jumin = new JLabel("�̸�");
		tjumin = new JTextField(20);
		
		label1 = new JLabel("����");
		label2 = new JLabel("�л�");
		
		r1 = new JRadioButton("����");
		r2 = new JRadioButton("�л�");
		
		ButtonGroup group = new ButtonGroup();
		group.add(r1);
		group.add(r2);

		
		majorcode.setBounds(20, 20, 60, 25);
		tmajorcode.setBounds(80, 20, 100, 25);
		
		majorname.setBounds(190, 20, 50, 25);
		tmajorname.setBounds(230, 20, 120, 25);
		
		jumin.setBounds(360, 20, 60, 25);
		tjumin.setBounds(420, 20, 150, 25);
		
		label1.setBounds(20,50,30,25);
		label2.setBounds(80,50,30,25);
		r1.setBounds(50,50,20,25);
		r2.setBounds(110,50,20,25);

		// northp �г� ���
		northp.add(majorcode);
		northp.add(tmajorcode);
		northp.add(majorname);
		northp.add(tmajorname);
		northp.add(jumin);
		northp.add(tjumin);
		northp.add(label1);
		northp.add(label2);
		northp.add(r1);
		northp.add(r2);
		
	
		this.add(northp);

		// ����° �г� orthp(�˻��� ...ī�װ�)
		orthp = new JPanel();
		orthp.setLayout(null);
		orthp.setBounds(0, 160, 600, 80);
		orthp.setBackground(Color.BLUE);

		bsearch = new JButton("Ȯ��");
		bsearch.setBounds(250, 45, 74, 25);

		bsearch.setBackground(Color.GRAY);
		bsearch.setForeground(Color.RED);
		orthp.add(bsearch);
		add("Center",orthp);

		

		// �ټ���° southp �г�(��ư��)
		southp = new JPanel();
		southp.setLayout(null);
		southp.setBounds(5, 500, 600, 200);

		exit = new JButton("����");

		exit.setBounds(438, 70, 138, 40);
		exit.setFont(f2);
		exit.setBackground(Color.MAGENTA);
		exit.setForeground(Color.WHITE);

		southp.add(exit);
		exit.addActionListener(this);//����

		
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
	
		
		
	
	//==========[ ���� �α��� ]============
	public boolean getLoginCheck(String id, String password , String type) {
		boolean result = false;
	
		String sql =null;
		if (type.equals("����")) {
			sql = "select * from professor"; //�α��� DataBase Table ������ �ش� db�� �α��� ó�� �ʿ���..
			
		}else {
			
			sql = "select * from student";
		}
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
				while(rs.next()) {
					int prof_code = rs.getInt("prof_code");
					String prof_name = rs.getString("prof_name");
					String prof_jumincode = rs.getString("prof_jumincode");
					String prof_majorcode = rs.getString("prof_majorcode");
					
				
				Object[] list = {prof_code,prof_name,prof_jumincode,prof_majorcode};
				dtm.addRow(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	

	//ActionListner
	public void actionPerformed(ActionEvent e) {
	
		
		String evt = e.getActionCommand();
			
		Object o = e.getSource();
		
		if(o ==exit) {
			System.exit(0);
		}else if(o == bsearch) {//�˻�
			boolean opt1 = r1.isSelected(); //����
			boolean opt2 = r2.isSelected(); //�л�
			String type=null;
			
			if(opt1 || opt2) {
				if(opt1) type="����";
				if(opt2) type ="�л�";
				String id = tmajorcode.getText();
				String password = tmajorname.getText();
				String name = tjumin.getText();
				
				if(id.equals("") || password.equals("")) {
					MessageDialog("�˻������� �Է��ϼ���.");//�˾��޽���.
				}else {
					getLoginCheck(id, password, type);//
				}
				
				
			}else {
				MessageDialog("�ɼ��� �����ϼ���.");//�˾��޽���.
			}
			
		
		}
		
	}
		
		
	public void MessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	

	public static void main(String[] args) {
		new Login();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}


	
