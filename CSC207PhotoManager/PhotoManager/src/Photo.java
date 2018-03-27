import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Photo {
	/** The original name of the photo before tags. */
	private String originalName;
	/** The current name of the photo*/
	private String name;
	/** The current existing tags of the photo*/
	private ArrayList<Tag> tags;
	/** The history of the photo's name*/
	private ArrayList<String> names;
	/** The current location of the photo*/
	private File location;
	/** The record of the name change of the photos*/
	private static final File LOG = new File("log.txt");
	/** The extension of the image*/
	private String extension;
	
	/**
	 * A photo in the file.
	 * 
	 * @param location
	 * 			The file's location.
	 * @throws IOException 
	 */
	public Photo(File location) throws IOException{
		
		this.tags = new ArrayList<Tag>();
		this.location = location;
		this.names = new ArrayList<String>();
		this.extension = location.getName().substring(location.getName().length() - 4);
		String n = location.getName().substring(0, (location.getName().length() - 4));
		this.originalName = n;
		this.name = n;
		
		
		if (n.contains(" ")){
			ArrayList<Tag> a = new ArrayList<Tag>();
			String[] f = n.split(" ");
			this.originalName = f[0];
			this.names.add(this.originalName);
			for (int i = 1; i < f.length; i ++){
				a.add(new Tag(f[i].substring(1)));
			}
			this.addTags(a);
		}
	}
	
	/**
	 * Add tags to the photo and also change the name of the photo file.
	 * 
	 * @param newTags
	 * 			The ArrayList of selected tags to add. 
	 */
	public void addTags(ArrayList<Tag> newTags) throws IOException{
		for (Tag newTag : newTags){
			int flag = 0;
			for (Tag t: this.tags){
				if (newTag.getName() == t.getName()){
					flag = 1;
				}
			}
			if (flag == 0){
				this.tags.add(newTag);
			}
		}
		this.rename();
	}
	
	/**
	 * Add tags to the photo and also change the name of the photo file.
	 * 
	 * @param newExistingTags
	 * 			The index of tags from tagList to add.
	 * @throws IOException 
	 */
	public void addFromExistingTag(ArrayList<Integer> newExistingTags) throws IOException{
		ArrayList<Tag> n = new ArrayList<Tag>();
		for (int i: newExistingTags){
			if (i >= 0 & i < Tag.tagList.size()){
				n.add(new Tag(Tag.tagList.get(i)));
			}
		}
		this.addTags(n);
	}
	
	/**
	 * Remove tags of a photo that originally exist and rename the file.
	 * 
	 * @param Tags
	 * 			The ArrayList of selected tags to remove.
	 */ 
	public void removeTags(ArrayList<Tag> tags) throws IOException{
		ArrayList<Tag> before = new ArrayList<Tag>();
		for (Tag tag : tags){
			for(Tag t: this.tags){
				if (tag.getName() == t.getName()){
					before.add(t);
				}
			}
		}
		for (Tag delete: before){
			this.tags.remove(delete);
		}
		this.rename();
	}
	
	/**
	 * Return the string representation of the tags.
	 * 
	 * @return the tags of the photo in string representation.
	 */
	public ArrayList<Tag> getTags(){
		return this.tags;
	}
	
	/**
	 * Return the string representation of the original name.
	 * 
	 * @return the original name of the photo in string representation.
	 */
	public String getOriginalName(){
		return this.originalName;
	}
	
	/**
	 * Return the string representation of the name.
	 * 
	 * @return the name of the photo in string representation.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Return the string representation of the extension.
	 * 
	 * @return the extension of the photo in string representation.
	 */
	public String getExtension(){
		return this.extension;
	}
	
	/**
	 * Return the ArrayList representation of the history of the photo's names.
	 * 
	 * @return the names of the photo in ArrayList representation.
	 */
	public ArrayList<String> getNames(){
		System.out.println(this.names);
		return this.names;
	}
	
	/**
	 * Change the name of the photo in the system to include the current tags.
	 */
	public void rename() throws IOException{
		String n;
		n = this.originalName;
		for (int i = 0; i < this.tags.size(); i ++){
			n += " @" + (this.tags.get(i)).getName();
		}
		String oldName = this.name;
		this.name = n;
		this.names.add(this.name);
		this.changeName();
		this.logWriting(oldName);
	}
	
	/**
	 * Write the activity of name change of photos to the log.txt file in format : oldName newName time
	 */
	public void logWriting(String oldName) throws IOException{
		FileWriter writes = new FileWriter(LOG, true);
		Date times = new Date();
		writes.write(oldName + " " + this.name + " " + times.toString() + System.lineSeparator());
		writes.close();	
	}
	
	/**
	 * Change the name to names.get(index)
	 * 
	 * @param index
	 * 			The index of the old names that you want to change to.
	 * 			If not a valid index, then do nothing.
	 * @throws IOException 
	 */
	public void goToOldName(int index) throws IOException{
		String newName = "";
		ArrayList<Tag> tagsToInclude = new ArrayList<Tag>();
		if (index < this.names.size() & index >= 0){
			newName = this.names.get(index);
			String[] newNames = newName.split(" ");
			for (String n: newNames){
				if (n.startsWith("@")){
					tagsToInclude.add(new Tag(n.substring(1)));
				}
			}
			this.tags = tagsToInclude;
			this.rename();
		}
	}
	
	/**
	 * Return the location file
	 * 
	 * @return The file of the image location
	 */
	public File getLocation(){
		return this.location;
	}
	
	/**
	 * Change the name of the photo in the system.
	 * (The helper method of rename)
	 */
	public void changeName(){
		File newFile = new File(this.location.getParentFile().getAbsolutePath() + "\\" + this.name + this.extension);
		this.location.renameTo(newFile);
	}
}