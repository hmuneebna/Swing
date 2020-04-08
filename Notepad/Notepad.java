
//Muneeb,Husam
// Homework #2
//April 25,2019
//cs-2450-02-sp19
//This Program implements the File,Format,and Help Menus with View and Edit as placeholders.
//Implemented JFileChooser, as well as JOption.showMessageDialog().
//Implements the Go To...function in which goes to a specific line. 
//Implement JFontChooser

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.*;
import javax.swing.text.Utilities;

import javax.swing.filechooser.FileNameExtensionFilter;

class Notepad implements ActionListener {

    static JLabel jlab;
    static JLabel status;
    static JFrame frame;
    static JTextArea jta;
    static JFileChooser j;
    static String save;
    static String fileName;
    static JRadioButton down = new JRadioButton("Down", true);
    static JRadioButton up = new JRadioButton("Up");
    static JTextField toFind = new JTextField(20);
    static JCheckBox matchCase = new JCheckBox("Match Case");
    static JLabel findWhat = new JLabel("Find What");
    static JButton cancel = new JButton("Cancel");

    static int findIndex = 0;
    static JButton findNextButton = new JButton("Find Next");

    boolean contentChanged = false;

    Notepad() {
	// Create a new JFrame container.
	frame = new JFrame("Untitled - Notepad");

	// Specify FlowLayout for the layout manager.
	frame.setLayout(new BorderLayout());
	frame.setLocationRelativeTo(null);
	// Give the frame an initial size.
	frame.setSize(640, 500);

	// Terminate the program when the user closes the application.
	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	frame.setIconImage(new ImageIcon("Notepad.png").getImage());

	// Create a label that will display the menu selection.
	jlab = new JLabel();

	// Create a scrollable TextArea
	jta = new JTextArea();
	jta.setInheritsPopupMenu(true);
	jta.setVisible(true);
	jta.setFont(new Font("Courier New", Font.PLAIN, 12));
	jta.setBackground(Color.WHITE);
	jta.setForeground(Color.black);
	jta.setLineWrap(true);
	jta.setWrapStyleWord(true);

	JScrollPane scroll = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	scroll.setPreferredSize(new Dimension(640, 450));

	// Create the menu bar.
	JMenuBar jmb = new JMenuBar();

	JMenu jm = new JMenu();

	// Create the File menu.
	JMenu jmFile = new JMenu("File");
	JMenuItem jmiOpen = new JMenuItem("Open");
	JMenuItem jmiSave = new JMenuItem("Save");
	JMenuItem jmiPrint = new JMenuItem("Print...");
	JMenuItem jmiPage = new JMenuItem("Page Setup...");
	JMenuItem jmiExit = new JMenuItem("Exit");
	JMenuItem jmiNew = new JMenuItem("New");
	JMenuItem jmiSaveAs = new JMenuItem("Save As...");

	jmiNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
	jmiNew.setMnemonic(KeyEvent.VK_N);
	jmiNew.setDisplayedMnemonicIndex(1);
	jmFile.add(jmiNew);

	jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
	jmiOpen.setMnemonic(KeyEvent.VK_O);
	jmiOpen.setDisplayedMnemonicIndex(1);
	jmFile.add(jmiOpen);

	jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
	jmiSave.setMnemonic(KeyEvent.VK_S);
	jmiSave.setDisplayedMnemonicIndex(1);
	jmFile.add(jmiSave);

	jmiSaveAs.setDisplayedMnemonicIndex(5);
	jmFile.add(jmiSaveAs);
	jmFile.addSeparator();
	jmFile.add(jmiPage);

	jmiPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
	jmiPrint.setMnemonic(KeyEvent.VK_P);
	jmiPrint.setDisplayedMnemonicIndex(1);
	jmFile.add(jmiPrint);
	jmFile.addSeparator();

	jmiExit.setMnemonic(KeyEvent.VK_X);

	jmiExit.setDisplayedMnemonicIndex(2);
	jmFile.add(jmiExit);

	jmFile.setMnemonic(KeyEvent.VK_F);
	jmFile.setDisplayedMnemonicIndex(1);
	jmb.add(jmFile);
	// Edit Menu
	JMenu jmiEdit = new JMenu("Edit");
	JMenuItem jmiUndo = new JMenuItem("Undo");
	JMenuItem jmiCut = new JMenuItem("Cut");
	JMenuItem jmiCopy = new JMenuItem("Copy");
	JMenuItem jmiPaste = new JMenuItem("Paste");
	JMenuItem jmiDelete = new JMenuItem("Delete");
	JMenuItem jmiFind = new JMenuItem("Find...");
	JMenuItem jmiFindNext = new JMenuItem("Find Next");
	JMenuItem jmiReplace = new JMenuItem("Replace...");
	JMenuItem jmiGo = new JMenuItem("Go To...");
	JMenuItem jmiSelectAll = new JMenuItem("Select All");
	JMenuItem jmiTimeDate = new JMenuItem("Time/Date");

	jmiUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
	jmiUndo.setMnemonic(KeyEvent.VK_U);
	jmiUndo.setDisplayedMnemonicIndex(1);

	jmiEdit.add(jmiUndo);
	jmiEdit.addSeparator();

	jmiCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
	jmiCut.setMnemonic(KeyEvent.VK_T);
	jmiCut.setDisplayedMnemonicIndex(1);
	jmiEdit.add(jmiCut);

	jmiCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
	jmiEdit.add(jmiCopy);

	jmiPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
	jmiPaste.setMnemonic(KeyEvent.VK_P);
	jmiPaste.setDisplayedMnemonicIndex(1);
	jmiEdit.add(jmiPaste);

	jmiDelete.setAccelerator(KeyStroke.getKeyStroke("DELETE"));
	jmiDelete.setMnemonic(KeyEvent.VK_L);
	jmiDelete.setDisplayedMnemonicIndex(1);
	jmiEdit.add(jmiDelete);
	jmiEdit.addSeparator();

	jmiFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
	jmiFind.setMnemonic(KeyEvent.VK_F);
	jmiFind.setDisplayedMnemonicIndex(1);
	jmiEdit.add(jmiFind);

	jmiFindNext.setMnemonic(KeyEvent.VK_N);
	jmiFindNext.setDisplayedMnemonicIndex(6);
	jmiEdit.add(jmiFindNext);

	jmiReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
	jmiReplace.setMnemonic(KeyEvent.VK_R);
	jmiReplace.setDisplayedMnemonicIndex(1);
	jmiEdit.add(jmiReplace);

	jmiGo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
	jmiGo.setMnemonic(KeyEvent.VK_G);
	jmiGo.setDisplayedMnemonicIndex(1);
	jmiEdit.add(jmiGo);

	jmiEdit.addSeparator();

	jmiSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
	jmiSelectAll.setMnemonic(KeyEvent.VK_A);
	jmiSelectAll.setDisplayedMnemonicIndex(1);
	jmiEdit.add(jmiSelectAll);

	jmiTimeDate.setAccelerator(KeyStroke.getKeyStroke("F5"));
	jmiTimeDate.setMnemonic(KeyEvent.VK_D);
	jmiTimeDate.setDisplayedMnemonicIndex(1);
	jmiEdit.add(jmiTimeDate);

	JMenu jmiView = new JMenu("View");
	JCheckBoxMenuItem jmiSB = new JCheckBoxMenuItem("Status Bar");
	// Create the Format menu.
	JMenu jmFormat = new JMenu("Format");

	// Create SubMenu's for Format menu
	JCheckBoxMenuItem jmWord = new JCheckBoxMenuItem("Word Wrap");
	JMenuItem jmFont = new JMenuItem("Font...");
	JMenu jmColor = new JMenu("Color");
	JMenuItem jmiForeground = new JMenuItem("Foreground");
	JMenuItem jmiBackground = new JMenuItem("Background");

	jmiForeground.setMnemonic(KeyEvent.VK_F);
	jmiForeground.setDisplayedMnemonicIndex(1);

	jmColor.add(jmiForeground);
	jmColor.add(jmiBackground);
	jmColor.setMnemonic(KeyEvent.VK_C);
	jmColor.setDisplayedMnemonicIndex(1);

	jmWord.setMnemonic(KeyEvent.VK_W);
	jmWord.setDisplayedMnemonicIndex(1);
	jmFormat.add(jmWord);
	jmFont.setMnemonic(KeyEvent.VK_F);
	jmFont.setDisplayedMnemonicIndex(1);
	jmFormat.add(jmFont);
	jmFormat.add(jmColor);
	jmiView.add(jmiSB);

	jmiEdit.setMnemonic(KeyEvent.VK_E);
	jmiEdit.setDisplayedMnemonicIndex(1);
	jmb.add(jmiEdit);

	jmFormat.setMnemonic(KeyEvent.VK_O);
	jmFormat.setDisplayedMnemonicIndex(1);
	jmb.add(jmFormat);

	jmiView.setMnemonic(KeyEvent.VK_V);
	jmiView.setDisplayedMnemonicIndex(1);
	jmb.add(jmiView);

	// Create the Help menu.
	JMenu jmHelp = new JMenu("Help");
	JMenuItem jmiAbout = new JMenuItem("About Notepad");
	JMenuItem jmiViewHelp = new JMenuItem("View Help");

	jmiViewHelp.setMnemonic(KeyEvent.VK_H);
	jmiViewHelp.setDisplayedMnemonicIndex(5);

	jmHelp.add(jmiViewHelp);
	jmHelp.addSeparator();

	jmiAbout.setMnemonic(KeyEvent.VK_A);
	jmiAbout.setDisplayedMnemonicIndex(1);
	jmHelp.add(jmiAbout);

	jmHelp.setMnemonic(KeyEvent.VK_H);
	jmHelp.setDisplayedMnemonicIndex(1);
	jmb.add(jmHelp);

	// Popup Menu'S

	JPopupMenu menu = new JPopupMenu();
	JMenuItem copyPopup = new JMenuItem("Copy", KeyEvent.VK_C);
	JMenuItem cutPopup = new JMenuItem("Cut", KeyEvent.VK_T);
	JMenuItem pastePopup = new JMenuItem("Paste", KeyEvent.VK_V);
	JMenuItem UndoPopup = new JMenuItem("Undo");
	JMenuItem DeletePopup = new JMenuItem("Delete");
	JMenuItem SelectAllPopup = new JMenuItem("Select All");

	menu.add(UndoPopup);
	menu.addSeparator();
	menu.add(cutPopup);
	menu.add(copyPopup);
	menu.add(pastePopup);
	menu.add(DeletePopup);
	menu.addSeparator();
	menu.add(SelectAllPopup);

	cutPopup.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ac) {
		jta.cut();
	    }
	});
	DeletePopup.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent a) {
		jta.replaceRange("", jta.getSelectionStart(), jta.getSelectionEnd());
	    }
	});
	copyPopup.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ad) {
		jta.copy();
	    }
	});
	pastePopup.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent fa) {
		jta.paste();
	    }
	});
	SelectAllPopup.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent fa) {
		jta.selectAll();
	    }
	});
	jta.addMouseListener(new MouseAdapter() {
	    public void mousePressed(MouseEvent me) {
		if (me.isPopupTrigger()) {
		    menu.show(me.getComponent(), me.getX(), me.getY());
		}
	    }

	    public void mouseReleased(MouseEvent me) {
		if (me.isPopupTrigger()) {
		    menu.show(me.getComponent(), me.getX(), me.getY());
		}
	    }
	});

	cutPopup.setMnemonic(KeyEvent.VK_T);
	pastePopup.setMnemonic(KeyEvent.VK_P);
	SelectAllPopup.setMnemonic(KeyEvent.VK_A);

	jmiCut.setEnabled(false);
	jmiCopy.setEnabled(false);
	jmiDelete.setEnabled(false);
	jmiFind.setEnabled(false);
	jmiFindNext.setEnabled(false);
	jmiUndo.setEnabled(false);
	copyPopup.setEnabled(false);
	UndoPopup.setEnabled(false);
	cutPopup.setEnabled(false);
	DeletePopup.setEnabled(false);
	jmiSelectAll.setEnabled(false);
	SelectAllPopup.setEnabled(false);

	jta.getDocument().addDocumentListener(new DocumentListener() {
	    public void insertUpdate(DocumentEvent e) {
		checkForInput();
	    }

	    public void removeUpdate(DocumentEvent e) {
		checkForInput();
	    }

	    public void changedUpdate(DocumentEvent e) {
		checkForInput();
	    }

	    public void checkForInput() {
		if (jta.getText().length() == 0) {

		    jmiCut.setEnabled(false);
		    jmiCopy.setEnabled(false);
		    jmiDelete.setEnabled(false);
		    jmiFind.setEnabled(false);
		    jmiFindNext.setEnabled(false);
		    jmiUndo.setEnabled(false);
		    copyPopup.setEnabled(false);
		    cutPopup.setEnabled(false);
		    DeletePopup.setEnabled(false);
		    jmiSelectAll.setEnabled(false);
		    SelectAllPopup.setEnabled(false);
		    UndoPopup.setEnabled(false);
		} else {
		    jmiCut.setEnabled(true);
		    jmiCopy.setEnabled(true);
		    jmiDelete.setEnabled(true);
		    jmiFind.setEnabled(true);
		    jmiFindNext.setEnabled(true);
		    jmiUndo.setEnabled(true);
		    copyPopup.setEnabled(true);
		    cutPopup.setEnabled(true);
		    DeletePopup.setEnabled(true);
		    jmiSelectAll.setEnabled(true);
		    SelectAllPopup.setEnabled(true);
		    UndoPopup.setEnabled(true);

		}
	    }
	});

	// Add action listeners for the menu items

	jmiCopy.addActionListener(this);
	jmiCut.addActionListener(this);
	jmiPaste.addActionListener(this);
	jmiOpen.addActionListener(this);
	jmiPrint.addActionListener(this);
	jmiGo.addActionListener(this);

	jmiEdit.addActionListener(this);
	jmiTimeDate.addActionListener(this);
	jmiAbout.addActionListener(this);
	jmiNew.addActionListener(this);
	jmiForeground.addActionListener(this);
	jmiSelectAll.addActionListener(this);
	jmiDelete.addActionListener(this);
	jmiSaveAs.addActionListener(this);
	jmiSave.addActionListener(this);
	jmFont.addActionListener(this);
	jmiBackground.addActionListener(this);
	jmiFind.addActionListener(this);
	jmiFindNext.addActionListener(this);

	jmiExit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ate) {
		SaveOnExit();
	    }

	});

	jta.addCaretListener(new CaretListener() {
	    public void caretUpdate(CaretEvent e) {
		findIndex = jta.getCaretPosition();
		JTextArea changedArea = (JTextArea) e.getSource();
		contentChanged = true;

	    }
	});

	frame.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent we) {
		SaveOnExit();
	    }
	});

	// Add the label to the content pane.
	frame.add(jlab);

	// Add the menu bar to the frame.
	frame.setJMenuBar(jmb);

	frame.add(scroll);
	// Display the frame.
	frame.setVisible(true);

	status = new JLabel();
	JPanel sb = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	sb.setVisible(false);
	sb.add(status);

	int line = jta.getLineCount();
	int column = jta.getColumns();

	frame.add(sb, BorderLayout.SOUTH);
	status.setText("Ln: " + line + ", " + "Col: " + column);

	jmWord.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent ie) {
		int response = ie.getStateChange();
		if (response == ItemEvent.SELECTED) {
		    jta.setLineWrap(true);
		    jta.setWrapStyleWord(true);

		}
		if (response == ItemEvent.DESELECTED) {
		    jta.setLineWrap(false);
		    jta.setWrapStyleWord(false);

		}

	    }

	});

	jmiSB.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent e) {
		AbstractButton check = (AbstractButton) e.getSource();
		boolean select = check.getModel().isSelected();
		if (select == true) {
		    sb.setVisible(true);

		} else {
		    sb.setVisible(false);
		}
	    }
	});

    }

    // Handle menu item action events.
    @Override
    public void actionPerformed(ActionEvent ae) {
	// Get the action command from the menu selection.

	String comStr = ae.getActionCommand();
	// ImageIcon for the dialog
	ImageIcon icon = new ImageIcon("Notepad.png");

	JTextField jtf = new JTextField(20);
	// If user chooses Exit, then exit the program.

	if (comStr.equals("About Notepad")) {
	    JOptionPane.showMessageDialog(frame, "Notepad\n \u00a9 2019 H.Muneeb", comStr,
		    JOptionPane.INFORMATION_MESSAGE, icon);
	} else if (comStr.equals("Print")) {
	    jta.setText("Printing...");
	} else if (comStr.equals("Font...")) {

	    Font fontSel = JFontChooser.showDialog(frame, null);

	    if (fontSel != null) {
		jta.setFont(fontSel);
	    }

	} else if (comStr.equals("Save As...")) {
	    j = new JFileChooser();
	    int result1 = j.showSaveDialog(frame);
	    if (result1 == JFileChooser.APPROVE_OPTION) {
		save = jta.getText();
		try {
		    FileWriter fw = new FileWriter(j.getSelectedFile() + ".txt");
		    fw.write(save);
		    fw.close();
		    int point = j.getSelectedFile().getName().lastIndexOf('.');
		    String f = j.getSelectedFile().getName().substring(0, point);
		    frame.setTitle(f + "- Notepad");

		} catch (Exception x) {
		    x.printStackTrace();
		}
	    }
	} else if (comStr.equals("Save")) {
	    j = new JFileChooser();

	    save = jta.getText();
	    int result = j.showSaveDialog(frame);
	    if (result == JFileChooser.APPROVE_OPTION) {

		try {
		    FileWriter fw = new FileWriter(j.getSelectedFile() + ".txt");
		    fw.write(save);
		    fw.close();
		    int point = j.getSelectedFile().getName().lastIndexOf('.');
		    String f = j.getSelectedFile().getName().substring(0, point);
		    frame.setTitle(f + "- Notepad");

		} catch (Exception x) {
		    x.printStackTrace();
		}
	    }

	    else {

		try {
		    PrintWriter pw = new PrintWriter(fileName);
		    pw.write(save);
		    pw.close();
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    }
	}

	else if (comStr.equals("Go To...")) {

	    JPanel panel = new JPanel();

	    Object[] buttons = { "Go To", "Cancel" };

	    panel.add(new JLabel("Line Number: "));
	    JTextField textFind = new JTextField(10);
	    panel.add(textFind);
	    int action = JOptionPane.showOptionDialog(null, panel, "Go To Line", JOptionPane.YES_NO_CANCEL_OPTION,
		    JOptionPane.PLAIN_MESSAGE, null, buttons, null);
	    if (action == JOptionPane.YES_OPTION) {
		try {
		    String content = textFind.getText();
		    int result1 = Integer.parseInt(content);
		    jta.setCaretPosition(
			    jta.getDocument().getDefaultRootElement().getElement(result1 - 1).getStartOffset());
		} catch (Exception exp) {
		    JOptionPane.showMessageDialog(null, "The line number is beyond the total number of lines",
			    "Go To Line", JOptionPane.PLAIN_MESSAGE);
		    exp.printStackTrace();
		}

	    }
	} else if (comStr.equals("Cut")) {
	    jta.cut();
	} else if (comStr.equals("New")) {
	    jta.setText("");
	    frame.setTitle("Untitled - Notepad");
	}

	else if (comStr.equals("Find...")) {
	    findDialog();

	} else if (comStr.contentEquals("Find Next")) {
	    if (findIndex == 0) {
		findDialog();
	    } else {
		find(findIndex + 1);
	    }
	}

	else if (comStr.equals("Time/Date")) {
	    Date date = new Date();
	    DateFormat dateFormat = new SimpleDateFormat("hh:mm a MM/dd/YYYY");
	    String formatteddateTime = dateFormat.format(date);
	    jta.insert(formatteddateTime, jta.getCaretPosition());
	} else if (comStr.equals("Copy")) {
	    jta.copy();
	} else if (comStr.equals("Paste")) {
	    jta.paste();
	}

	else if (comStr.equals("Delete")) {
	    jta.replaceRange("", jta.getSelectionStart(), jta.getSelectionEnd());
	} else if (comStr.equals("Select All")) {
	    jta.selectAll();
	} else if (comStr.equals("Foreground")) {
	    Color color = JColorChooser.showDialog(null, "Choose Color", Color.BLACK);

	    if (color != null) {
		jlab.setText("Selected color is " + color.toString());
		jta.setForeground(color);
	    } else {
		jlab.setText("Color selection was cancelled.");
	    }
	} else if (comStr.equals("Background")) {
	    Color color = JColorChooser.showDialog(null, "Choose Color", Color.WHITE);
	    if (color != null) {
		jlab.setText("Selected color is " + color.toString());
		jta.setBackground(color);
	    } else {
		jlab.setText("Color selection was cancelled.");
	    }
	} else if (comStr.equals("Open")) {
	    j = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("java", "txt", "java");
	    j.setFileFilter(filter);
	    j.setDialogTitle("Open");
	    @SuppressWarnings("unused")
	    int result2 = j.showOpenDialog(null);
	    File file = j.getSelectedFile();

	    if (result2 == JFileChooser.APPROVE_OPTION) {

		try {
		    FileReader reader = new FileReader(fileName);
		    BufferedReader br = new BufferedReader(reader);
		    jta.read(br, null);
		    br.close();
		    jta.requestFocus();

		} catch (Exception le) {
		    JOptionPane.showMessageDialog(null, le);
		}
	    }
	}
    }

    // This helper function updates the status bar with the line number and column
    // number.

    private void SaveOnExit() {
	if (contentChanged) {
	    String[] options = { "Save", "Don't Save", "Cancel" };
	    int response = JOptionPane.showOptionDialog(frame, "Do you want to save changes to Untitled?", "Notepad",
		    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, "Save");
	    switch (response) {
	    case 0:
		JFileChooser jfc = new JFileChooser();
		String name = frame.getTitle();
		String saveToFile = jta.getText();
		if (name.equals("Untitled - Notepad")) {
		    int result = jfc.showSaveDialog(frame);
		    fileName = jfc.getSelectedFile().getName() + ".txt";
		    if (result == JFileChooser.APPROVE_OPTION) {
			try {
			    FileWriter fw = new FileWriter(fileName);
			    fw.write(saveToFile);
			    fw.close();
			    int point = fileName.lastIndexOf('.');
			    String f = fileName.substring(0, point);
			    frame.setTitle(f + " - Notepad");
			} catch (Exception ex) {
			    ex.printStackTrace();
			}
		    }
		} else {
		    try {
			PrintWriter pw = new PrintWriter(fileName);
			// System.out.println(new File(fileName).exists());
			pw.write(saveToFile);
			pw.close();
		    } catch (Exception ex) {
			ex.printStackTrace();
		    }
		}
		break;

	    case 1:
		System.exit(0);
		break;

	    case 2:
		break;
	    }
	} else {
	    System.exit(0);
	}

    }

    private boolean find(int start) {

	down = new JRadioButton("Down", true);
	up = new JRadioButton("Up");
	toFind = new JTextField(20);
	matchCase = new JCheckBox("Match Case");
	String findWithin = jta.getText();
	String search = toFind.getText();
	boolean toReturn = true;
	int downIndex = 0;
	int upIndex = 0;
	if (down.isSelected()) {
	    if (matchCase.isSelected()) {
		downIndex = findWithin.indexOf(search, start);
		if (downIndex == -1) {
		    JOptionPane.showMessageDialog(frame, "Cannot find " + "\"" + search + "\"", "Notepad",
			    JOptionPane.INFORMATION_MESSAGE);
		    toReturn = false;
		} else {
		    jta.setCaretPosition(downIndex);
		    jta.moveCaretPosition(jta.getCaretPosition() + toFind.getText().length());
		    findIndex = downIndex;
		}
	    }
	    if (!matchCase.isSelected()) {
		downIndex = findWithin.toLowerCase().indexOf(search.toLowerCase(), start);
		if (downIndex == -1) {
		    JOptionPane.showMessageDialog(frame, "Cannot find " + "\"" + search + "\"", "Notepad",
			    JOptionPane.INFORMATION_MESSAGE);
		    toReturn = false;
		} else {
		    jta.setCaretPosition(downIndex);
		    jta.moveCaretPosition(jta.getCaretPosition() + toFind.getText().length());
		    findIndex = downIndex;
		}
	    }
	}

	if (up.isSelected()) {
	    if (matchCase.isSelected()) {
		upIndex = findWithin.lastIndexOf(search, start);
		if (upIndex == -1) {
		    JOptionPane.showMessageDialog(frame, "Cannot find " + "\"" + search + "\"", "Notepad",
			    JOptionPane.INFORMATION_MESSAGE);
		    toReturn = false;
		} else {
		    jta.setCaretPosition(upIndex);
		    jta.moveCaretPosition(jta.getCaretPosition() + toFind.getText().length());
		    findIndex = upIndex - toFind.getText().length();
		}
	    }
	    if (!matchCase.isSelected()) {
		upIndex = findWithin.toLowerCase().lastIndexOf(search.toLowerCase(), start);
		if (upIndex == -1) {
		    JOptionPane.showMessageDialog(frame, "Cannot find " + "\"" + search + "\"", "Notepad",
			    JOptionPane.INFORMATION_MESSAGE);
		    toReturn = false;
		} else {
		    jta.setCaretPosition(upIndex);
		    jta.moveCaretPosition(jta.getCaretPosition() + toFind.getText().length());
		    findIndex = upIndex - toFind.getText().length();
		}
	    }
	}
	jta.requestFocusInWindow();
	return toReturn;
    }

    private void findDialog() {
	JDialog jdlg = new JDialog(frame, "Find", false);
	jdlg.pack();
	jdlg.setLocationRelativeTo(null);
	jdlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	jdlg.setSize(400, 125);
	jdlg.getContentPane().setLayout(new FlowLayout());
	Container content = frame.getContentPane();
	content.setLayout(new BorderLayout());
	ButtonGroup bg = new ButtonGroup();
	bg.add(up);
	bg.add(down);
	findNextButton.setEnabled(false);

	toFind.getDocument().addDocumentListener(new DocumentListener() {
	    public void insertUpdate(DocumentEvent e) {
		checkForInput();
	    }

	    public void removeUpdate(DocumentEvent e) {
		checkForInput();
	    }

	    public void changedUpdate(DocumentEvent e) {
		checkForInput();
	    }

	    public void checkForInput() {
		if (toFind.getText().length() == 0) {
		    findNextButton.setEnabled(false);
		} else {
		    findNextButton.setEnabled(true);
		}
	    }
	});

	findNextButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (findIndex == 0) {
		    find(findIndex);
		} else {
		    find(findIndex + 1);
		}
	    }
	});

	cancel.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		jdlg.dispose();
	    }
	});

	jdlg.add(findWhat);
	jdlg.add(toFind);
	jdlg.add(findNextButton);
	jdlg.add(matchCase);
	jdlg.add(up);
	jdlg.add(down);
	jdlg.add(cancel);
	jdlg.setVisible(true);
    }

    public static void main(String args[]) {
	// Create the frame on the event dispatching thread.
	SwingUtilities.invokeLater(() -> {
	    new Notepad();
	});
    }
}
