import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class JMain1{

	String[] columnNames = {"id","gen","job","name"};

	public static String id =null;
	public static String gen = null;
	public static String job = null;
	public static String name = null;


	DefaultTableModel mode = new DefaultTableModel(null, columnNames);

	JTable tMainTable = new JTable(mode);


	public void maintable() {

	     Connection conn = null;
	     Statement stmt = null;
	     ResultSet rset = null;

	    //接続文字列
	     String url = "jdbc:postgresql://localhost:5432/kaiin";
	     String user = "postgres";
	     String password = "postgres";

		//自身のインスタンス化
		JFrame frame = new JFrame();
		frame.setLayout(null);
		frame.setBounds(10, 10, 680, 750);
		frame.setTitle("メイン画面");
		frame.setVisible(true);

		//テーブル
			//DB表示画面

		    //読込
	        try{

	            //PostgreSQLへ接続
	            conn = DriverManager.getConnection(url, user, password);

	            //自動コミットOFF
	            conn.setAutoCommit(false);

	            //SELECT文の実行
	            stmt = conn.createStatement();
	            String sql = "select * from kaiin order by id asc";
	            rset = stmt.executeQuery(sql);

	            String tess[]= new String[5];

	            //SELECT結果の受け取り
	            while(rset.next()){

	            	for(int i = 1; i < 5; i++) {

	            		tess[i-1] = rset.getString(i);

	            	}

	            	mode.addRow(tess);
	            }

				frame.add(tMainTable);

				tMainTable.setVisible(true);
			    JScrollPane  scroll_table = new JScrollPane(tMainTable);            // add table to scroll panel
				scroll_table.setBounds(50,50,540,400);
			    scroll_table.setVisible(true);
			    frame.add(scroll_table);



	        }
	        catch (SQLException e){
	            e.printStackTrace();
	        }
	        finally {
	            try {
	                if(rset != null)rset.close();
	                if(stmt != null)stmt.close();
	                if(conn != null)conn.close();
	            }
	            catch (SQLException e){
	                e.printStackTrace();
	            }
	        }

	    //ボタン配列
			//追加画面

			JButton btn_Add = new JButton("追加画面");
			btn_Add.setBounds(50,450, 180,70);
			btn_Add.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 30));
			frame.add(btn_Add);
			btn_Add.setVisible(true);
			btn_Add.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JAdd1 JUpdate1 = new JAdd1();

					JUpdate1.Addfunction();


				}
			});

			//テーブル更新
			JButton btn_tup = new JButton("テーブル更新");
			btn_tup.setBounds(360,0, 230,50);
			btn_tup.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 30));
			frame.add(btn_tup);
			btn_tup.setVisible(true);
			btn_tup.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JMain1 main1 = new JMain1();

					main1.mode.setRowCount(0);

			 		   Connection conn = null ;

			 		   Statement stmt = null;

			 		   ResultSet rset = null;

				    //読込
			        try{
			            //PostgreSQLへ接続
			            conn = DriverManager.getConnection(url, user, password);

			            //自動コミットOFF
			            conn.setAutoCommit(false);

			            //SELECT文の実行
			            stmt = conn.createStatement();

			            String sql = "select * from kaiin order by id asc";

			            rset = stmt.executeQuery(sql);

			            String tess[]= new String[5];

			            //SELECT結果の受け取り
			            while(rset.next()){

			            	for(int i = 1; i < 5; i++) {

			            		tess[i-1] = rset.getString(i);

			            	}

			            	main1.mode.addRow(tess);
			            }

						frame.add(main1.tMainTable);

						main1.tMainTable.setVisible(true);

					    JScrollPane  scroll_table = new JScrollPane(main1.tMainTable);            // add table to scroll panel

					    scroll_table.setBounds(50,50,540,400);

					    scroll_table.setVisible(true);

					    frame.add(scroll_table);

			        }
			        catch (SQLException e1){

			            e1.printStackTrace();

			        }
			        finally {

			            try {
			                if(rset != null)rset.close();

			                if(stmt != null)stmt.close();

			                if(conn != null)conn.close();
			            }
			            catch (SQLException e1){

			                e1.printStackTrace();

			            }
			        }


				}
			});

			//更新画面
			JButton btn_update = new JButton("更新画面");
			btn_update.setBounds(230,450, 180,70);
			btn_update.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 30));
			frame.add(btn_update);
			btn_update.setVisible(true);
			btn_update.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
			   	     Connection conn = null;
				     Statement stmt = null;
				     ResultSet rset = null;

				    //接続文字列
				     String url = "jdbc:postgresql://localhost:5432/kaiin";
				     String user = "postgres";
				     String password = "postgres";
					JUpdate1 JUpdate1 = new JUpdate1();
					JMain1 test =new JMain1();


			    	int delno = tMainTable.getSelectedRow();
			    //	System.out.println(String.valueOf(String.valueOf(delno)));
			    	id = tMainTable.getValueAt(delno, 0).toString();
			    	gen = tMainTable.getValueAt(delno, 1).toString();
			    	job = tMainTable.getValueAt(delno, 2).toString();
			    	name = tMainTable.getValueAt(delno, 3).toString();
					JUpdate1.Upfunction(String.valueOf(id));
	//				 tMainTable.getSelectedRow()

				}
			});

			//削除
			JButton btn_delete = new JButton("削除");
			btn_delete.setBounds(410,450, 180,70);
			btn_delete.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 30));
			frame.add(btn_delete);
			btn_delete.setVisible(true);
			btn_delete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				    //読込
			        try{
			   	     Connection conn = null;
				     Statement stmt = null;
				     ResultSet rset = null;

				    //接続文字列
				     String url = "jdbc:postgresql://localhost:5432/kaiin";
				     String user = "postgres";
				     String password = "postgres";
			        	int delno = tMainTable.getSelectedRow();

			        	String sql = "delete from kaiin where id = " + tMainTable.getValueAt(delno, 0).toString();

			        	//PostgreSQLへ接続
			            conn = DriverManager.getConnection(url, user, password);

			            //自動コミットOFF
			            conn.setAutoCommit(false);
			            PreparedStatement deleter = conn.prepareStatement(sql);
			            deleter.execute();
			            deleter.close();
			            conn.commit();

			        	tMainTable.setModel(mode);


			        	mode.setRowCount(0);

			            //SELECT文の実行
			            stmt = conn.createStatement();
			            sql = "select * from kaiin order by id asc";
			            rset = stmt.executeQuery(sql);

			            String tess[]= new String[5];

			            //SELECT結果の受け取り
			            while(rset.next()){

			            	for(int i = 1; i < 5; i++) {

			            		tess[i-1] = rset.getString(i);

			            	}

			            	mode.addRow(tess);
			            }

						frame.add(tMainTable);

						tMainTable.setVisible(true);
					    JScrollPane  scroll_table = new JScrollPane(tMainTable);            // add table to scroll panel
						scroll_table.setBounds(50,50,540,400);
					    scroll_table.setVisible(true);
					    frame.add(scroll_table);


			        }
			        catch (Exception e1){
			            e1.printStackTrace();
			        }

			        JOptionPane.showMessageDialog(null, "削除間完了");
				}
			});

			//閉じる
			JButton btn_close = new JButton("閉じる");
			btn_close.setBounds(410,520, 180,70);
			btn_close.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 30));
			frame.add(btn_close);
			btn_close.setVisible(true);
			btn_close.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					frame.dispose();

				}
			});
	}



	public static void main(String args[]){
		JMain1 main1 = new JMain1();
		main1.maintable();
	}





}
