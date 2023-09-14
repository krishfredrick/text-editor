import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // Declaring the properties fo TextEditor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;
    TextEditor(){
        this.frame = new JFrame();
        this.menuBar = new JMenuBar();
        this.file = new JMenu("File");
        this.edit = new JMenu("Edit");
        this.textArea = new JTextArea();

        /* Initialize file menu items*/
        this.newFile = new JMenuItem("New Window");
        this.openFile = new JMenuItem("Open File");
        this.saveFile = new JMenuItem("Save File");

        /* Add action listener to file menu items*/
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        /* Add file items to Men Items */
        this.file.add(this.newFile);
        this.file.add(this.openFile);
        this.file.add(this.saveFile);

        /* Initialize edit menu items */
        this.cut = new JMenuItem("cut");
        this.copy = new JMenuItem("copy");
        this.paste = new JMenuItem("paste");
        this.selectAll = new JMenuItem("selectAll");
        this.close = new JMenuItem("close");

        /* Add action listener to edit menu items*/
        this.cut.addActionListener(this);
        this.copy.addActionListener(this);
        this.paste.addActionListener(this);
        this.selectAll.addActionListener(this);
        this.close.addActionListener(this);

        /* Add file items to Menu Items */
        this.edit.add(this.cut);
        this.edit.add(this.copy);
        this.edit.add(this.paste);
        this.edit.add(this.selectAll);
        this.edit.add(this.close);

        /* Add Menu to BAR*/
        this.menuBar.add(this.file);
        this.menuBar.add(this.edit);

        /* ADD MENU BAR HERE*/
        frame.setJMenuBar(menuBar);

        /* Create Content Panel */
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5,5));
        panel.setLayout(new BorderLayout(0, 0));
        //        frame.add(textArea);
        panel.add(textArea, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(this.textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        frame.setTitle("Text Editor");
        frame.setBounds((int)(Math.random()*100), (int)(Math.random()*100), 400, 400);
        frame.setVisible(true);
        frame.setLayout(null);

    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cut){
            textArea.cut();
        }
        if(e.getSource() == copy){
            textArea.copy();
        }

        if(e.getSource() == paste){
            textArea.paste();
        }
        if(e.getSource() == selectAll){
            textArea.selectAll();
        }

        if(e.getSource() == close){
           System.exit(200);
        }
        if(e.getSource()==openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if( chooseOption == JFileChooser.APPROVE_OPTION){
                // getting selected file
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();

                try {
                    /* Initialize file reader */
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    // Read contnets of file line by line
                    while ((intermediate = bufferedReader.readLine())!=null){
                        output+=intermediate+'\n';
                    }

                }catch (FileNotFoundException err){
                    err.printStackTrace();
                }catch (IOException err){
                    err.printStackTrace();
                }
            }


        }

        if (e.getSource() == saveFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            //Check if we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file  = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try{
                    FileWriter fileWriter = new FileWriter(file);
                    // Initialize Buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);
                }catch (IOException err){
                    err.printStackTrace();
                }
            }
        }

        if(e.getSource() == newFile){
            TextEditor textEditor = new TextEditor();
        }

    }
}
