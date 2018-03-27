import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.util.*;

@SuppressWarnings("serial")
public class DirectorySelector extends JPanel implements ActionListener{
	/** initial directory*/
	String theDirectory = new String();
	/** initial directory chooser*/
	JFileChooser choosenDirectory;
	/** initial directory chooser button*/
	JButton ChooseDirectoryButton;
	/** empty file use for initialization*/
	static File testfile = null;
	/** chosen directory*/
	public static Chooser theChosenDirectory = new Chooser(testfile);
	/** list of images of the chosen directory*/
	public static ArrayList<Photo> theImageList = new ArrayList<Photo>();
	/** name of label*/
	JLabel theName = new JLabel("");
	/** group of buttons*/
	ButtonGroup group = new ButtonGroup();
	/**checkboxes*/
	public static ArrayList<JCheckBox> Checkboxes = new ArrayList<JCheckBox>();
	/**the photo chosen for further action*/
	public static ArrayList<Photo> theChosenPhoto = new ArrayList<Photo>();
	/**initial display*/
	BufferedImage imageDisplay = null;
	/**initial display image label*/
	JLabel imageLabel = new JLabel();
	
	ArrayList<String> abc = new ArrayList<String>();
	/**text of the label*/
	JLabel theTitle = new JLabel("Choose Image here: ");
	/**initial of panel*/
	JPanel thispanel = new JPanel();
	/**get Image*/
	JButton getImage = new JButton("Get Image From this Directory");
	/**
	 * A chosen directory
	 */
	DirectorySelector(){
		ChooseDirectoryButton = new JButton("Choose Directory");
		ChooseDirectoryButton.addActionListener(this);
		thispanel.setLayout(new BoxLayout(thispanel, BoxLayout.Y_AXIS));
		add(ChooseDirectoryButton);
		add(theName);
		add(theTitle);
		add(thispanel);
		JButton getImage = new JButton(new AbstractAction("Refresh List"){
			public void actionPerformed( ActionEvent arg1 ) {
				theImageList = theChosenDirectory.getImageList();
				int theLength = DirectorySelector.theImageList.size();
				thispanel.removeAll();
				theChosenPhoto.clear();
				Checkboxes.clear();
				for (int i=0; i < theLength; i++){
					String newname = (DirectorySelector.theImageList.get(i)).getName();
					JCheckBox box = new JCheckBox(newname);
					Checkboxes.add(box);
					box.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							String selectedName = new String();
							for (JCheckBox thisone : Checkboxes){
								if (thisone.isSelected()){
									selectedName = thisone.getText();
								}
							}
							
							for (Photo thisImage : DirectorySelector.theImageList){
								String name = thisImage.getName();
								if(name == selectedName){
									theChosenPhoto.clear();
									theChosenPhoto.add(thisImage);
									System.out.println(name);
									System.out.println(theChosenPhoto.get(0).getLocation());
								}
							}
							try {
								imageDisplay = ImageIO.read(theChosenPhoto.get(0).getLocation());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							ImageIcon imageIcon = new ImageIcon(new ImageIcon(imageDisplay).getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
							imageLabel.setIcon(imageIcon);
						}
					});
					thispanel.add(box);
					group.add(box);
			}
			thispanel.revalidate();
			imageLabel.revalidate();
	        }
	    });
		add(getImage);
		add(imageLabel);
	}
	
	/**
	 * Actions performed when choosing directory
	 */
	public void actionPerformed(ActionEvent e) {
		choosenDirectory = new JFileChooser();
		choosenDirectory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (choosenDirectory.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			choosenDirectory.getCurrentDirectory();
			File thisfile = choosenDirectory.getSelectedFile();
			theChosenDirectory.setDirectory(thisfile);
			theName.setText("Current Directory:   " + thisfile.toString());
			theImageList = theChosenDirectory.getImageList();
		}else{
			JOptionPane.showMessageDialog(null, "You did not choose any directory\n Please choose one");
		}
		
//		int theLength = DirectorySelector.theImageList.size();
//		System.out.println(theLength);
//
//		for (int i=0; i < theLength; i++){
//			String newname = (DirectorySelector.theImageList.get(i)).getName();
//			
//			System.out.println(newname);
//			JCheckBox box = new JCheckBox(newname);
//			Checkboxes.add(box);
//			box.addActionListener(new ActionListener(){
//				@Override
//				public void actionPerformed(ActionEvent agr0) {
//
//					String selectedName = new String();
//					for (JCheckBox thisone : Checkboxes){
//						if (thisone.isSelected()){
//							selectedName = thisone.getText();
//						}
//					}
//					
//					for (Photo thisImage : DirectorySelector.theImageList){
//						String name = thisImage.getName();
//						if(name == selectedName){
////							theChosenPhoto.clear();
//							theChosenPhoto.add(thisImage);
//						}
//					}
//					try {
//						System.out.println(theChosenPhoto.get(0).getLocation());
//						imageDisplay = ImageIO.read(theChosenPhoto.get(0).getLocation());
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//					ImageIcon imageIcon = new ImageIcon(new ImageIcon(imageDisplay).getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
//					imageLabel.setIcon(imageIcon);
//				}
//			});
//			this.thispanel.add(box);
//			group.add(box);
//	}
//		thispanel.revalidate();
//		imageLabel.revalidate();
	}
}
