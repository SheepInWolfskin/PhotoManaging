import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("serial")
public class TagChooser extends JPanel implements ActionListener {
	ArrayList<String> Taglist = new ArrayList<String>();
	ArrayList<JCheckBox> checkboxs = new ArrayList<JCheckBox>();
	JButton Addtag = new JButton("Add tag");
	JButton RemoveTag = new JButton("Remove Tag");
	JPanel theTaglist = new JPanel();
	JLabel theName = new JLabel("Choose existing Tags from here");
	JButton refresh = new JButton("Refresh");
	
	/**
	 * A process of adding or removing tags from existing tags.
	 */
	TagChooser(){
		Addtag.addActionListener(this);
		RemoveTag.addActionListener(this);
		refresh.addActionListener(this);
		add(refresh);
		add(Addtag);
		add(RemoveTag);
		add(theName);
	}
	
	/**
	 * Actions performed when choosing tags to add or remove
	 */
	public void actionPerformed(ActionEvent e) {
		ArrayList<String> theCollection = new ArrayList<String>();
		String buttonName = e.getActionCommand();
		for(JCheckBox theBox : checkboxs){
			if(theBox.isSelected() & !theCollection.contains((theBox.getText()))){
				theCollection.add(theBox.getText());
			}
		}
		if (buttonName.equals("Add tag")){
			ArrayList<Tag> taglist1 = new ArrayList<Tag>();
			for(String theString : theCollection){
				try {
					taglist1.add(new Tag(theString));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			Photo theChosenOne = DirectorySelector.theChosenPhoto.get(0);
			try {
				theChosenOne.addTags(taglist1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(buttonName.equals("Remove Tag")){
			ArrayList<Tag> taglist2 = new ArrayList<Tag>();
			for(String theString : theCollection){
				try{
					taglist2.add(new Tag(theString));
				}catch (IOException e1){
					e1.printStackTrace();
				}
			}
			System.out.println(taglist2);
			Photo theChosenOne = DirectorySelector.theChosenPhoto.get(0);
			try{
				theChosenOne.removeTags(taglist2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if (buttonName.equals("Refresh")){
			theTaglist.setLayout(new BoxLayout(theTaglist, BoxLayout.Y_AXIS));
			if (DirectorySelector.theChosenDirectory == null){
				JOptionPane.showMessageDialog(null, "No Photo Chosen Yet!");
			}else{
				for (Tag item : (DirectorySelector.theChosenPhoto.get(0)).getTags()){
					String newname = item.getName();
					if (!Taglist.contains(newname)){
						Taglist.add(newname);
					}
					}
				int size = Taglist.size();
				theTaglist.removeAll();
				for(int i=0; i < size; i++){
					String newname = Taglist.get(i);
					JCheckBox box = new JCheckBox(newname);
					if (!(box.getParent() == theTaglist)){
						theTaglist.add(box);
						checkboxs.add(box);
					}
				}
			}
			add(theTaglist);
		}
	theTaglist.revalidate();
	}
}
