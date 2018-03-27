import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Chooser{
	/** The directory of the file. */
	public File directory;
	/** The list of images */
	private ArrayList<Photo> images;

	/**
	 * A choosen directory.
	 * 
	 * @param selected
	 * 			The directory
	 */
	public Chooser(File selected){
		this.directory = selected;
		this.images = new ArrayList<Photo>();
	}
	
	/**
	 *Return the directory name.
	 *
	 *@return the string representation of the name of the directory.
	 */
	public String getDirectory(){
		return this.directory.getName();
	}
	
	public void setDirectory(File theone){
		this.directory = theone;
		try {
			this.listImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *Return the names of images under the directory.
	 *
	 *@return the string representation of the names of the images under the directory.
	 */
	public String getImages(){
		String out = "";
		for (Photo i: images){
			out += i.getName() + " ";
		}
		return out;
	}
	
	/**
	 *Return the list of images under the directory.
	 *
	 *@return the ArrayList representation of images under the directory.
	 */
	public ArrayList<Photo> getImageList(){
		return this.images;
	}
	
	/**
	 *Add image to list of images
	 * @throws IOException 
	 */
	public void listImages() throws IOException{
		this.recursive_find(this.directory);
	}
	
	/**
	 *select image from list of images.
	 *
	 *@param string of the image name
	 *
	 *@return the file of that image if exists or null
	 */
	public Object selectImage(String name){
		for (Photo i: images){
			if (name == i.getName()){
				return i;
			}
		}
		return null;
	}
	
	/**
	 *Add files with extension jpeg, jpg, png to images.
	 * @throws IOException 
	 */
	public void recursive_find(File directory) throws IOException{
		for (File n: directory.listFiles()){
			if (n.isDirectory()){
				this.recursive_find(n);
			}
			else{
				if (n.getAbsolutePath().endsWith(".jpg")){
					createPhoto(n);
				}
				else if (n.getAbsolutePath().endsWith(".png")){
					createPhoto(n);
				}
			}
		}
	}
	
	/**
	 *Create Photo using File
	 *
	 *@param File of image
	 * @throws IOException 
	 */
	public void createPhoto(File n) throws IOException{
		Photo newImage = new Photo(n);
		this.images.add(newImage);
	}
	
	/**
	 *Read tags.txt and create tags.
	 */
	public void readTags() throws IOException{
		File t = new File("tags.txt");
		Scanner scan = new Scanner(t);
		String stringToTags = scan.next();
		while (stringToTags != null){
			System.out.println(stringToTags);
			new Tag(stringToTags);
			if (scan.hasNext()){
				stringToTags = scan.next();
			}
			else{
				break;
			}
		}
		scan.close();
	}
}