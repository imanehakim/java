package mv.view;

import mv.StudentListener;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentView extends JFrame implements StudentListener {

    private StudentListener studentListener;

    private JTextField yearText;
    private JTextField letterText;
    private JLabel yearLabel;
    private JLabel letterLabel;
    private JLabel fullNameLabel;
    private JTextField fullNameText;
    private JLabel aaa;

    public StudentView(StudentListener studentListener) {
        super("Student View");

        this.studentListener = studentListener;


        // Create graphic interface
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(500, 500));

        JPanel basePane = new JPanel(new BorderLayout());
        setContentPane(basePane);


        JPanel topPane = new JPanel();
        basePane.add(topPane, BorderLayout.NORTH);
        JPanel centerPane= new JPanel();
        basePane.add(centerPane);

        yearLabel=new JLabel("YEAR:----");
        letterLabel=new JLabel("CLASS:----");
        topPane.add(yearLabel);
        topPane.add(letterLabel);



        topPane.add(new Label("Year"));
        yearText = new JTextField(4);
        topPane.add(yearText);

        topPane.add(new Label("Class"));
        letterText = new JTextField(4);
        topPane.add(letterText);


        JButton updateButton = new JButton("Update");
        topPane.add(updateButton);


        fullNameLabel=new JLabel("Full Name ------");
        centerPane.add(fullNameLabel);
        fullNameText = new JTextField(10);
        centerPane.add(fullNameText);

        JButton updateButtonName = new JButton("UpdateName");
        centerPane.add(updateButtonName);

      


        // register button action

        updateButton.addActionListener(e -> {
                studentListener.onUpdateAge(Integer.parseInt(yearText.getText()));
          //  System.out.println(Integer.parseInt(yearText.getText()));
                if(!letterText.getText().isEmpty())
                studentListener.onUpdateLetter(letterText.getText().charAt(0));
                }
        );
        updateButtonName.addActionListener(e -> {
                   studentListener.onUpdatefullName(fullNameText.getText());
                }
                );





        setVisible(true);
    }

    @Override
    public void onUpdateAge(int age) {
        yearLabel.setText(Integer.toString(age));//castare l'in in una stringa
    }

    @Override
    public void onUpdateLetter(char letter) {
        letterLabel.setText(Character.toString(letter));
    }

    @Override
    public void onUpdateGrades(ArrayList<Integer> grades) {

    }

    @Override
    public void onUpdatefullName(String fullName) {
        fullNameLabel.setText(fullName);
    }
}
