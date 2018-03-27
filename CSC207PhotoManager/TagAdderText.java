import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("serial")
public class TagAdderText extends JPanel implements ActionListener{
	
	JButton Searchfor = new JButton("Add Tag");
	JTextField theDirectory = new JTextField(20);
	
	/**
	 * A process of adding tags using textbox.
	 */
	TagAdderText(){
		Searchfor.addActionListener(this);
		add(Searchfor);
		add(theDirectory);
	}
	
	/**
	 * Actions performed when add tags using text
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = e.getActionCommand();
		if(buttonName.equals("Add Tag")){
			String theInformation = theDirectory.getText();
			ArrayList<Tag> newTagList = new ArrayList<Tag>();
			if(theInformation.contains(" ")){
				JOptionPane.showMessageDialog(null, "No Whitespace allowed between tags");
			}else if(theDirectory.getText() == ""){
				JOptionPane.showMessageDialog(null, "Error, Nothing Entered");
			}else{
				String[] Splited = theInformation.split("@");
				for (String item: Splited){
					try {
						newTagList.add(new Tag(item));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				newTagList.remove(0);
				try {
					Photo theChosenOne = DirectorySelector.theChosenPhoto.get(0);
					theChosenOne.addTags(newTagList);
				} catch (IndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "Error, No Photo Chosen");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error, No Photo Chosen");
				}
			}
		}
	}	
}