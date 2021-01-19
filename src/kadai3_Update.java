import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class JUpdate1{


    static Connection conn = null;
    static Statement stmt = null;
    static ResultSet rset = null;

    //接続文字列
    static String url = "jdbc:postgresql://localhost:5432/kaiin";
    static String user = "postgres";
    static String password = "postgres";

	public static void main(String args[]){

		//自身のインスタンス化

		JUpdate1 test =new JUpdate1();
		//test.Upfunction();



	}

	public void Upfunction(String ai) {
		JFrame frame = new JFrame();
		frame.setLayout(null);
		frame.setBounds(10, 10, 680, 750);
		frame.setTitle("更新画面");
		frame.setVisible(true);


	//ラベル
		//NO_見出し用
		JLabel LabNo = new JLabel("No.");
		LabNo.setBounds(30,50,90,100);
		LabNo.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
		LabNo.setVisible(true);
		frame.add(LabNo);

		//NO_表示用
		JLabel LabNo2 = new JLabel(ai);

		LabNo2.setBounds(170,50,400,100);
		LabNo2.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
		LabNo2.setVisible(true);
		frame.add(LabNo2);

		//性別
		JLabel LabGen = new JLabel("性別");
		LabGen.setBounds(30,150,90,100);
		LabGen.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
		LabGen.setVisible(true);
		frame.add(LabGen);

		//職業
		JLabel LabJob = new JLabel("職業");
		LabJob.setBounds(30,250,90,100);
		LabJob.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
		LabJob.setVisible(true);
		frame.add(LabJob);

		//名前
		JLabel LabName = new JLabel("名前");
		LabName.setBounds(30,350,90,100);
		LabName.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
		LabName.setVisible(true);
		frame.add(LabName);


		JUpdate1 JUpdate1 = new JUpdate1();
		JMain1 main1 = new JMain1();

    	//LabNo2.setText("1");


	//コンボボックス

			//性別項目
			JMain1 upd = new JMain1();
	        ArrayList<String> hoge_1 = new ArrayList<>();
            //SELECT文の実行
            try {
	            //PostgreSQLへ接続
	            conn = DriverManager.getConnection(url, user, password);

	            //自動コミットOFF
	            conn.setAutoCommit(false);

	            //SELECT文の実行
	            stmt = conn.createStatement();
	            String sql = "select * from gender order by id asc";
	            rset = stmt.executeQuery(sql);

	            int i = 0;
	            hoge_1.add(main1.gen);
	            while(rset.next()) {
	            	hoge_1.add(rset.getString(2));
	            }
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
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
			//性別
			JComboBox cmbGen = new JComboBox(hoge_1.toArray());
			cmbGen.setBounds(170,150,400,100);
			cmbGen.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
			cmbGen.setVisible(true);
			frame.add(cmbGen);

			cmbGen.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

				}
			});


			//性別項目
	        ArrayList<String> hoge = new ArrayList<>();
	        //SELECT文の実行
            try {
	            //PostgreSQLへ接続
	            conn = DriverManager.getConnection(url, user, password);

	            //自動コミットOFF
	            conn.setAutoCommit(false);

	            //SELECT文の実行
	            stmt = conn.createStatement();
	            String sql_2 = "select * from job order by id asc";
	            rset = stmt.executeQuery(sql_2);
	            hoge.add(main1.job);

	            while(rset.next()) {

	            	hoge.add(rset.getString(2));
	            }
			} catch (SQLException e1) {
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
			//職業
			JComboBox cmbJob = new JComboBox(hoge.toArray());
			cmbJob.setBounds(170,250,400,100);
			cmbJob.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
			cmbJob.setVisible(true);
			frame.add(cmbJob);


	//テキストボックス

			//名前
			JTextField tbName = new JTextField(main1.name);
			tbName.setBounds(170,350,400,100);
			tbName.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
			tbName.setVisible(true);
			frame.add(tbName);

	//ボタン配列
			//更新する
			JButton btn_update = new JButton("更新する");
			btn_update.setBounds(210,450, 180,70);
			btn_update.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 30));
			frame.add(btn_update);
			btn_update.setVisible(true);
			btn_update.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JMain1 test1 =new JMain1();


					try {
			            conn = DriverManager.getConnection(url, user, password);

						conn.setAutoCommit(false);

						int delno = test1.tMainTable.getSelectedRow();

						String sql_string = "UPDATE kaiin SET gender =?, job = ?, name = ? WHERE id =? ";

						PreparedStatement updater = conn.prepareStatement(sql_string);

						updater.setInt(4, Integer.parseInt(LabNo2.getText()));

						updater.setString(1, (String)cmbGen.getSelectedItem());

						updater.setString(2, (String)cmbJob.getSelectedItem());

						updater.setString(3, tbName.getText());

						updater.execute();

						conn.commit();

						updater.close();

					} catch (SQLException e1) {

						e1.printStackTrace();
					}

					JOptionPane.showMessageDialog(null, "更新完了");

				}
			});

			//メイン画面へ
			JButton btn_delete = new JButton("メインへ");
			btn_delete.setBounds(390,450, 180,70);
			btn_delete.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 30));
			frame.add(btn_delete);
			btn_delete.setVisible(true);
			btn_delete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//upd.tMainTable.setRowCount(0);
					frame.dispose();
				}
			});

	}
}
