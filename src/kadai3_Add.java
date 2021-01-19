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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class JAdd1{

    static Connection conn = null;
    static Statement stmt = null;
    static ResultSet rset = null;
    static ResultSet rset1 = null;

    //接続文字列
    static String url = "jdbc:postgresql://localhost:5432/kaiin";
    static String user = "postgres";
    static String password = "postgres";
	JMain1 main1 = new JMain1();

	public static void main(String args[]){

		//自身のインスタンス化
		JAdd1 test =new JAdd1();
		test.Addfunction();



	}
    public JLabel LabNo2 = null;
    public JComboBox cmbGen = null;
    public JComboBox cmbJob = null;
    public JTextField tbName = null;

	public void Addfunction() {

		JDialog frame = new JDialog();
		frame.setLayout(null);
		frame.setBounds(10, 10, 680, 750);
		frame.setTitle("追加画面");
		frame.setVisible(true);


	//ラベル
		//NO_見出し用
		JLabel LabNo = new JLabel("No.");
		LabNo.setBounds(30,50,90,100);
		LabNo.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
		LabNo.setVisible(true);
		frame.add(LabNo);

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

		//インスタンス生成
		JMain1 upd = new JMain1();

//ラベルNo表示
	    //読込
        int maxval = 0;
        try{
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql0 = "select count(id) as teee from kaiin";
            String sql1 = "select max(id) as teee1 from kaiin";

            ResultSet ttt = stmt.executeQuery(sql0);
            ttt.next();
            if(ttt.getInt("teee") != 0) {
                ResultSet ttt1 = stmt.executeQuery(sql1);
                ttt1.next();

            	maxval = ttt1.getInt("teee1")+1;
        		//NO_表示用
        		LabNo2 = new JLabel(String.valueOf(maxval));
        		LabNo2.setBounds(170,50,400,100);

        		LabNo2.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
        		LabNo2.setVisible(true);
        		frame.add(LabNo2);

        		}else {

            		LabNo2 = new JLabel("1");
            		LabNo2.setBounds(170,50,400,100);

            		LabNo2.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
            		LabNo2.setVisible(true);
            		frame.add(LabNo2);
        		}

        }catch(Exception e1) {
        	System.out.print(e1);
        }

	//コンボボックス

			//性別項目
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
	            hoge_1.add("");
	            int i = 0;
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
			cmbGen = new JComboBox(hoge_1.toArray());
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
	            hoge.add("");

	            while(rset.next()) {

	            	hoge.add(rset.getString(2));
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
			//職業
			cmbJob = new JComboBox(hoge.toArray());
			cmbJob.setBounds(170,250,400,100);
			cmbJob.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
			cmbJob.setVisible(true);
			frame.add(cmbJob);


	//テキストボックス

			//名前
			tbName = new JTextField();
			tbName.setBounds(170,350,400,100);
			tbName.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 40));
			tbName.setVisible(true);
			frame.add(tbName);

	//ボタン配列
			//追加する
			JButton btn_update = new JButton("追加する");
			btn_update.setBounds(210,450, 180,70);
			btn_update.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 30));
			frame.add(btn_update);
			btn_update.setVisible(true);
			btn_update.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

			        try{
			            conn = DriverManager.getConnection(url, user, password);
			            conn.setAutoCommit(false);
			            String sql0 = "INSERT INTO kaiin VALUES(?, ?, ?, ?) ";

			            PreparedStatement inserter = conn.prepareStatement(sql0);

				           inserter.setInt(1, Integer.parseInt(LabNo2.getText()));

				           inserter.setString(2, (String)cmbGen.getSelectedItem());

				           inserter.setString(3, (String)cmbJob.getSelectedItem());

				           inserter.setString(4, tbName.getText());

//Updateと同じ設定の場合
//				       String sql0 = "insert into kaiin(id, gender, job, name) "
//				       + "values(" + Integer.parseInt(LabNo2.getText()) + "," + (String)cmbGen.getSelectedItem() + "," + (String)cmbJob.getSelectedItem() + "," + tbName.getText() + ")";

			            inserter.execute();

			            conn.commit();
			            inserter.close();

			        }catch(Exception e1) {

			        	System.out.print("同一番号があるか，入力不備です");
			        }

			        JOptionPane.showMessageDialog(null, "追加完了");

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

			        frame.dispose();

				}
			});

	}
}
