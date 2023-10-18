import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

class Student {
    private String name;
    private int id;
    private int age;

    public Student(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ", Age: " + age + ")";
    }
}

public class StudentManagementSystem extends JFrame {
    private DefaultListModel<Student> studentListModel;
    private JList<Student> studentList;
    private JTextField nameField;
    private JTextField idField;
    private JTextField ageField;

    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField();

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);

        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(studentList), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addStudent() {
        String name = nameField.getText();
        int id;
        int age;

        try {
            id = Integer.parseInt(idField.getText());
            age = Integer.parseInt(ageField.getText());

            Student student = new Student(name, id, age);
            studentListModel.addElement(student);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID and Age must be valid numbers.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeStudent() {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex != -1) {
            studentListModel.remove(selectedIndex);
        }
        // show student
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", 1, 20));
        students.add(new Student("Jane", 2, 21));
        students.add(new Student("Bob", 3, 22));
        students.add(new Student("Alice", 4, 23));
        students.add(new Student("Jack", 5, 24));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementSystem().setVisible(true);
            }
        });
    }
}