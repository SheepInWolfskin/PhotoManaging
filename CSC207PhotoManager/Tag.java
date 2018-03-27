import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Tag {
	/** All the tags the user created*/
	static ArrayList<String> tagList = new ArrayList<String>();
	/** The storage of tags name that the user created*/
	private static final File TAGS = new File("tags.txt");
	/** The tag's name */
	private String name;
	
	/**
	 *Initialize and add tag to the tag list if the tag does not exist originally.
	 *
	 *@param name
	 *			The tag's name.
	 */
	public Tag(String name) throws IOException{
		this.name = name;
		if (!tagList.contains(name)){
			tagList.add(name);
			this.writeToFile();
		}
	}
	
	/**
	 *Write tags to file.
	 */
	public void writeToFile() throws IOException{
		FileWriter writes = new FileWriter(TAGS);
		for (String tag: tagList){
			writes.write(tag + " ");
		}
		writes.close();	
	}
	
	/**
	 *Return the name of the tag.
	 *
	 *@return string representation of the tag's name.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 *Return all the tags user created.
	 *
	 *@return string representation of tags.
	 */
	public String getTags(){
		String n = "";
		for (String t: tagList){
			n += t + " ";
		}
		return n;
	}
	
	/**
	 *Return all the tags user created.
	 *
	 *@return tagList.
	 */
	public ArrayList<String> getTagList(){
		return Tag.tagList;
	}
}