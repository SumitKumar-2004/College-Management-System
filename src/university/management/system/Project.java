package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Project extends JFrame implements ActionListener {

    JLabel dateTimeLabel;
    Timer timer;
    JLabel lbl;
//  Timer timer; // Timer to update label's position
    int xPos = 100; // Initial x position of the label
    int speed = 5; // Speed at which the label moves

    Project() {
        setSize(1540, 850);

        Image i = new ImageIcon(this.getClass().getResource("/icons/MainLogo.png")).getImage();
        this.setIconImage(i);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

// Create the label
        lbl = new JLabel("Welcome to Shri Khandelwal Vaish P.G. Mahavidhyalaya");
        lbl.setForeground(Color.BLUE);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 40));
        lbl.setBounds(xPos, 4, 1300, 40);  // Set initial position and size
        lbl.setOpaque(false); // To display the background color
        image.add(lbl);

// Create a timer to update the label's position
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Move the label
                xPos -= speed;  // Move the label to the left
                if (xPos < -lbl.getWidth()) {
                    xPos = getWidth(); // Reset position to start from the right again
                }
                lbl.setBounds(xPos, 4, 1300, 40);  // Update the label's position
            }
        });
        timer.start();  // Start the timer

        JMenuBar mb = new JMenuBar();

        // New Information
        JMenu newInformation = new JMenu("New Information");
        newInformation.setForeground(Color.DARK_GRAY);
        mb.add(newInformation);

        JMenuItem facultyInfo = new JMenuItem("New Faculty Information");
        facultyInfo.setBackground(Color.WHITE);
        facultyInfo.addActionListener(this);
        newInformation.add(facultyInfo);

        JMenuItem studentInfo = new JMenuItem("New Student Information");
        studentInfo.setBackground(Color.WHITE);
        studentInfo.addActionListener(this);
        newInformation.add(studentInfo);

        JMenuItem newNotice = new JMenuItem("New Notice");
        newNotice.setBackground(Color.WHITE);
        newNotice.addActionListener(this);
        newInformation.add(newNotice);

        // Details
        JMenu details = new JMenu("View Details");
        details.setForeground(Color.DARK_GRAY);
        mb.add(details);

        JMenuItem facultydetails = new JMenuItem("View Faculty Details");
        facultydetails.setBackground(Color.WHITE);
        facultydetails.addActionListener(this);
        details.add(facultydetails);

        JMenuItem studentdetails = new JMenuItem("View Student Details");
        studentdetails.setBackground(Color.WHITE);
        studentdetails.addActionListener(this);
        details.add(studentdetails);

        // Leave
        JMenu leave = new JMenu("Apply Leave");
        leave.setForeground(Color.DARK_GRAY);
        mb.add(leave);

        JMenuItem facultyleave = new JMenuItem("Faculty Leave");
        facultyleave.setBackground(Color.WHITE);
        facultyleave.addActionListener(this);
        leave.add(facultyleave);

        JMenuItem studentleave = new JMenuItem("Student Leave");
        studentleave.setBackground(Color.WHITE);
        studentleave.addActionListener(this);
        leave.add(studentleave);

        // Leave Details
        JMenu leaveDetails = new JMenu("Leave Details");
        leaveDetails.setForeground(Color.DARK_GRAY);
        mb.add(leaveDetails);

        JMenuItem facultyleavedetails = new JMenuItem("Faculty Leave Details");
        facultyleavedetails.setBackground(Color.WHITE);
        facultyleavedetails.addActionListener(this);
        leaveDetails.add(facultyleavedetails);

        JMenuItem studentleavedetails = new JMenuItem("Student Leave Details");
        studentleavedetails.setBackground(Color.WHITE);
        studentleavedetails.addActionListener(this);
        leaveDetails.add(studentleavedetails);

         // Previous Year Paper Details
        JMenu previousPaper = new JMenu("Papers");
        previousPaper.setForeground(Color.DARK_GRAY);
        mb.add(previousPaper);
        
        JMenuItem papers = new JMenuItem("Previous Year Papers");
        papers.setBackground(Color.WHITE);
        papers.addActionListener(this);
        previousPaper.add(papers);
        
        
        
        // Exams
        JMenu exam = new JMenu("Examination");
        exam.setForeground(Color.DARK_GRAY);
        mb.add(exam);

        JMenuItem examinationdetails = new JMenuItem("Examination Results");
        examinationdetails.setBackground(Color.WHITE);
        examinationdetails.addActionListener(this);
        exam.add(examinationdetails);

        JMenuItem entermarks = new JMenuItem("Enter Marks");
        entermarks.setBackground(Color.WHITE);
        entermarks.addActionListener(this);
        exam.add(entermarks);

        // Notice
        JMenu notice = new JMenu("Notice");
        notice.setForeground(Color.DARK_GRAY);
        mb.add(notice);

        JMenuItem addNotice = new JMenuItem("Add Notice");
        addNotice.setBackground(Color.WHITE);
        addNotice.addActionListener(this);
        notice.add(addNotice);

        // UpdateInfo
        JMenu updateInfo = new JMenu("Update Details");
        updateInfo.setForeground(Color.DARK_GRAY);
        mb.add(updateInfo);

        JMenuItem updatefacultyinfo = new JMenuItem("Update Faculty Details");
        updatefacultyinfo.setBackground(Color.WHITE);
        updatefacultyinfo.addActionListener(this);
        updateInfo.add(updatefacultyinfo);

        JMenuItem updatestudentinfo = new JMenuItem("Update Student Details");
        updatestudentinfo.setBackground(Color.WHITE);
        updatestudentinfo.addActionListener(this);
        updateInfo.add(updatestudentinfo);

        // fee
        JMenu fee = new JMenu("Fee Details");
        fee.setForeground(Color.DARK_GRAY);
        mb.add(fee);

        JMenuItem feestructure = new JMenuItem("Fee Structure");
        feestructure.setBackground(Color.WHITE);
        feestructure.addActionListener(this);
        fee.add(feestructure);

        JMenuItem feeform = new JMenuItem("Student Fee Form");
        feeform.setBackground(Color.WHITE);
        feeform.addActionListener(this);
        fee.add(feeform);

        //infrastructure
        JMenu infra = new JMenu("Infrastructure");
        infra.setForeground(Color.DARK_GRAY);
        mb.add(infra);

        JMenuItem library = new JMenuItem("Library");
        library.setBackground(Color.WHITE);
        library.addActionListener(this);
        infra.add(library);

        // Utility
        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.DARK_GRAY);
        mb.add(utility);

        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setBackground(Color.WHITE);
        notepad.addActionListener(this);
        utility.add(notepad);

        JMenuItem calc = new JMenuItem("Calculator");
        calc.setBackground(Color.WHITE);
        calc.addActionListener(this);
        utility.add(calc);

        JMenuItem web = new JMenuItem("Browser");
        web.setBackground(Color.WHITE);
        web.addActionListener(this);
        utility.add(web);

        JMenuItem raj = new JMenuItem("Rajasthan University");
        raj.setBackground(Color.WHITE);
        raj.addActionListener(this);
        utility.add(raj);

        JMenuItem yt = new JMenuItem("YouTube");
        yt.setBackground(Color.WHITE);
        yt.addActionListener(this);
        utility.add(yt);

        // about
        JMenu about = new JMenu("About");
        about.setForeground(Color.DARK_GRAY);
        mb.add(about);

        JMenuItem ab = new JMenuItem("About");
        ab.setBackground(Color.WHITE);
        ab.addActionListener(this);
        about.add(ab);

        JMenuItem reach = new JMenuItem("Reach Us");
        reach.setBackground(Color.WHITE);
        reach.addActionListener(this);
        about.add(reach);

        // exit
        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.RED);
        mb.add(exit);

        JMenuItem ex = new JMenuItem("Exit");
        ex.setBackground(Color.WHITE);
        ex.addActionListener(this);
        exit.add(ex);

        // Date & Time Label
        dateTimeLabel = new JLabel();
        dateTimeLabel.setForeground(Color.MAGENTA);
        mb.add(Box.createHorizontalGlue()); // Push label to the right
        mb.add(dateTimeLabel);

        updateDateTime();
        timer = new Timer(1000, e -> updateDateTime());
        timer.start();

        setJMenuBar(mb);
        setTitle("Shri Khandelwal Vaish P.G. Mahavidhyalaya");
        setVisible(true);
    }

    private void updateDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a dd/MM/yyyy");
        dateTimeLabel.setText(sdf.format(new Date()).toUpperCase());
    }

    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();

        if (msg.equals("Exit")) {
            setVisible(false);
        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {

            }
        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {

            }
        } else if (msg.equals("Browser")) {
            try {
                URI uri = new URI("https://www.google.com/");
//                        Runtime.getRuntime().exec("");
                Desktop.getDesktop().browse(uri);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Rajasthan University")) {
            try {
                URI uri = new URI("https://www.uniraj.ac.in/");
//                        Runtime.getRuntime().exec("");
                Desktop.getDesktop().browse(uri);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        } else if (msg.equals("YouTube")) {
            try {
                URI uri = new URI("https://www.youtube.com/");
//                        Runtime.getRuntime().exec("");
                Desktop.getDesktop().browse(uri);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Reach Us")) {
            try {
                URI uri = new URI("https://maps.app.goo.gl/JY2JZ2xqpcF6yWD49");
//                        Runtime.getRuntime().exec("");
                Desktop.getDesktop().browse(uri);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }

        } else if (msg.equals("New Faculty Information")) {
            new AddTeacher();
        } else if (msg.equals("New Student Information")) {
            new AddStudent();
        } else if (msg.equals("View Faculty Details")) {
            new TeacherDetails();
        } else if (msg.equals("New Notice")) {
            new NewNotice();
        } else if (msg.equals("View Student Details")) {
            new StudentDetails();
        } else if (msg.equals("Faculty Leave")) {
            new TeacherLeave();
        } else if (msg.equals("Student Leave")) {
            new StudentLeave();
        } else if (msg.equals("Faculty Leave Details")) {
            new TeacherLeaveDetails();
        } else if (msg.equals("Student Leave Details")) {
            new StudentLeaveDetails();
        } else if (msg.equals("Update Faculty Details")) {
            new UpdateTeacher();
        }else if (msg.equals("Previous Year Papers")) {
            new PDFManager().setVisible(true);
        }else if (msg.equals("Update Student Details")) {
            new UpdateStudent();
        } else if (msg.equals("Enter Marks")) {
            new EnterMarks();
        } else if (msg.equals("Examination Results")) {
            new ExaminationDetails();
        } else if (msg.equals("Add Notice")) {
            new AddNoticeForm();
        } else if (msg.equals("Fee Structure")) {
            new FeeStructure();
        } else if (msg.equals("About")) {
            new About();
        } else if (msg.equals("Student Fee Form")) {
            new StudentFeeForm();
        } else if (msg.equals("Library")) {
            new Library();
        }
    }

    public static void main(String[] args) {
        new Project();
    }
}
