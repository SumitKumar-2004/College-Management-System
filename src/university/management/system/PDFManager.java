
package university.management.system;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.sql.*;

public class PDFManager extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private Connection conn;

    public PDFManager() {
        setTitle("PDF Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Database Connection
        connectToDatabase();

        // Table for PDF Names
        tableModel = new DefaultTableModel(new String[]{"ID", "PDF Name"}, 0);
        table = new JTable(tableModel);
        loadTableData();

        // Buttons
        JButton uploadBtn = new JButton("Upload PDF");
        JButton downloadBtn = new JButton("Download PDF");
        JButton removeBtn = new JButton("Remove PDF");
        JButton openBtn = new JButton("Open PDF");

        uploadBtn.addActionListener(this::uploadPDF);
        downloadBtn.addActionListener(this::downloadPDF);
        removeBtn.addActionListener(this::removePDF);
        openBtn.addActionListener(this::openPDF);

        JPanel panel = new JPanel();
        panel.add(uploadBtn);
        panel.add(downloadBtn);
        panel.add(openBtn);
        panel.add(removeBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Connection Failed!");
        }
    }

    private void loadTableData() {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name FROM pdf_files")) {
            tableModel.setRowCount(0);
            while (rs.next()) {
                tableModel.addRow(new Object[]{rs.getInt("id"), rs.getString("name")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void uploadPDF(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop")); // Default to Desktop
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (FileInputStream fis = new FileInputStream(selectedFile);
                 PreparedStatement ps = conn.prepareStatement("INSERT INTO pdf_files (name, file_data) VALUES (?, ?)")) {

                ps.setString(1, selectedFile.getName());
                ps.setBinaryStream(2, fis, (int) selectedFile.length());
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "PDF Uploaded Successfully!");
                loadTableData();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error Uploading PDF");
            }
        }
    }

    private void downloadPDF(ActionEvent event) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a PDF to download.");
            return;
        }

        int pdfId = (int) table.getValueAt(selectedRow, 0);
        String pdfName = (String) table.getValueAt(selectedRow, 1);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop")); // Default to Desktop
        fileChooser.setSelectedFile(new File(pdfName));
        int returnValue = fileChooser.showSaveDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File saveFile = fileChooser.getSelectedFile();
            try (PreparedStatement ps = conn.prepareStatement("SELECT file_data FROM pdf_files WHERE id = ?")) {
                ps.setInt(1, pdfId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    try (FileOutputStream fos = new FileOutputStream(saveFile)) {
                        InputStream is = rs.getBinaryStream("file_data");
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                        JOptionPane.showMessageDialog(this, "PDF Downloaded Successfully!");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error Downloading PDF");
            }
        }
    }

    private void removePDF(ActionEvent event) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a PDF to remove.");
            return;
        }

        int pdfId = (int) table.getValueAt(selectedRow, 0);
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM pdf_files WHERE id = ?")) {
            ps.setInt(1, pdfId);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "PDF Removed Successfully!");
            loadTableData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Removing PDF");
        }
    }

    private void openPDF(ActionEvent event) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a PDF to open.");
            return;
        }

        int pdfId = (int) table.getValueAt(selectedRow, 0);
        String pdfName = (String) table.getValueAt(selectedRow, 1);

        try (PreparedStatement ps = conn.prepareStatement("SELECT file_data FROM pdf_files WHERE id = ?")) {
            ps.setInt(1, pdfId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                File tempFile = File.createTempFile("pdf_", ".pdf");
                tempFile.deleteOnExit();

                try (FileOutputStream fos = new FileOutputStream(tempFile);
                     InputStream is = rs.getBinaryStream("file_data")) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }

                    // Open the PDF
                    Desktop.getDesktop().open(tempFile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Opening PDF");
        }
    }

    public static void main(String[] args) {
        new PDFManager().setVisible(true);
    }
}
