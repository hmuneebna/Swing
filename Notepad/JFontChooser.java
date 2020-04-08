
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

import javafx.scene.layout.Border;


public class JFontChooser {
	
	 static String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	 static String[] styles = {"Normal", "Italic", "Bold", "Bold Italic"};
	 static javax.swing.border.Border blackline,loweredbevel;
	
	 static JList fontNames = new JList(fonts);
	 static JScrollPane chooseFont = new JScrollPane(fontNames);
	
	 static JComboBox fontStyles = new JComboBox(styles);
	
	 static SpinnerNumberModel sizes = new SpinnerNumberModel(12, 6, 120, 1);
	 static JSpinner spn = new JSpinner(sizes);
	
	  static JLabel preview = new JLabel("AaBbYyZz");
	  static Font returnFont = null;
	
	public static Font showDialog(JFrame parent, Font font) {
		// TODO Auto-generated method stub
		JDialog fontDlg = new JDialog(parent, "Select Font", true);
		JButton select = new JButton("Select");
		fontDlg.setResizable(false);
		fontDlg.getContentPane().setLayout(new FlowLayout());
		
		
		fontNames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if (fontNames.getSelectedValue() == null ) {
			fontNames.setSelectedValue("Courier New", true);
		}
		else {
			fontNames.setSelectedValue(fontNames.getSelectedValue(), true);
		}	
		
		fontNames.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				updatePreview();
			}	
		});
		
		fontStyles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updatePreview();
			}
		});
		
		spn.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				updatePreview();
			}	
		});	
		
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (fontNames.getSelectedIndex() != -1 && fontStyles.getSelectedIndex() != -1) {
					returnFont = preview.getFont();
					fontDlg.setVisible(false);
				}
			}
		});
		
		fontDlg.add(chooseFont);
		fontDlg.add(fontStyles);
		fontDlg.add(spn);
		fontDlg.add(preview);
		fontDlg.add(select);
		blackline=BorderFactory.createLineBorder(Color.black);
		JLabel lab=new JLabel();
		TitledBorder title;
		title = BorderFactory.createTitledBorder("title");
		

		title = BorderFactory.createTitledBorder(
		                       blackline, "title");
		
		title = BorderFactory.createTitledBorder(
		                       loweredbevel, "title");
		title.setTitlePosition(TitledBorder.ABOVE_TOP);
		lab.setBorder(title);
		
		fontDlg.add(lab);
		
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - fontDlg.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - fontDlg.getHeight()) / 2);
	    fontDlg.setLocation(x, y);
		fontDlg.setSize(750, 450);
		fontDlg.setLocationRelativeTo(null);
		
		fontDlg.setVisible(true);
		
		return returnFont;
	}	
	
	private static void updatePreview() {
		chooseFont.setColumnHeaderView(new JLabel((String) fontNames.getSelectedValue()));	
		int currentStyle = fontStyles.getSelectedIndex();
		if (fontNames.getSelectedIndex() != -1 && currentStyle != -1) {
			switch (currentStyle) {
			case 0:
				preview.setFont(new Font(fonts[fontNames.getSelectedIndex()], Font.PLAIN, (Integer) spn.getValue()));
				break;
			case 1:
				preview.setFont(new Font(fonts[fontNames.getSelectedIndex()], Font.ITALIC, (Integer) spn.getValue()));
				break;
			case 2:
				preview.setFont(new Font(fonts[fontNames.getSelectedIndex()], Font.BOLD, (Integer) spn.getValue()));
				break;
			case 3:
				preview.setFont(new Font(fonts[fontNames.getSelectedIndex()], Font.BOLD + Font.ITALIC, (Integer) spn.getValue()));
				break;
			}
		}
	}
}