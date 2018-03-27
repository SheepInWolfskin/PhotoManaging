import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("serial")
public class GetOldName extends JPanel implements ActionListener {
	int maxSelection = 1;
	JButton refresh = new JButton("Get old names");
	JButton changeName = new JButton("Go back to this name");
	JPanel theNamelist = new JPanel();
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> ButtonList = new ArrayList<JRadioButton>();
	String theName = "";
	
	/**
	 * A process to go back to an older name
	 */
	GetOldName(){
		theNamelist.setLayout(new BoxLayout(theNamelist, BoxLayout.Y_AXIS));
		refresh.addActionListener(this);
		changeName.addActionListener(this);
		add(refresh);
		add(changeName);
		add(theNamelist);
	}
	
	/**
	 * Actions performed when click on either of the two buttons.
	 * get old names and go back to this name
	 */
	public void actionPerformed(ActionEvent e) {
		String buttonName = e.getActionCommand();
		if(buttonName == "Get old names"){
			theNamelist.removeAll();
			Photo theOne = (DirectorySelector.theChosenPhoto).get(0);
			for(String item : (theOne.getNames())){
				JRadioButton box = new JRadioButton(item);
				theNamelist.add(box);
				ButtonList.add(box);
				group.add(box);
			}
		theNamelist.revalidate();
		}
		if(buttonName == "Go back to this name"){
			for (JRadioButton button : ButtonList){
				if (button.isSelected()){
					theName = button.getText();
				}
			}
			Photo theOne = (DirectorySelector.theChosenPhoto).get(0);
			System.out.println(theOne.getName());
			System.out.println(theOne.getLocation());
			int theIndex = (theOne.getNames()).indexOf(theName);
	        try {
				theOne.goToOldName(theIndex);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	        System.out.println(theOne.getName());
		}
	}
}
