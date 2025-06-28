package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class FeeStructure extends JFrame {
    
    FeeStructure() {
        setSize(1000, 450);
        setLocation(250, 80);
        setLayout(null);
         setTitle("Shri Khandelwal Vaish P.G. Mahavidhyalaya");
        Image i = new ImageIcon(this.getClass().getResource("/icons/MainLogo.png")).getImage();
        this.setIconImage(i);
        
        getContentPane().setBackground(Color.WHITE);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/feees.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 265, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1000, 265);
//        setContentPane(image);
        add(image);
        
        
        
        JLabel heading = new JLabel("Fee Structure");
        heading.setBounds(50, 10, 400, 30);
        heading.setForeground(Color.white);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 30));
        image.add(heading);
        
        JTable table = new JTable();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from fee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 265, 1000, 1000);
        add(jsp);
        
        setVisible(true);
        
    }
    
    public static void main(String[] args) {
        new FeeStructure();
    }
}
