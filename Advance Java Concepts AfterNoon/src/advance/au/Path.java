package advance.au;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class Path {

	public static void main(String[] args) {
		List<String> fileNames = new ArrayList<>();
	    try {
	      DirectoryStream<java.nio.file.Path> directoryStream = Files.newDirectoryStream(Paths.get("C:\\Users\\Subhash Mandal\\Desktop\\Accolite\\acc"));
	      for (java.nio.file.Path path : directoryStream) {
	        fileNames.add(path.toString());
	      }
	    } catch (IOException ex) {
	    }
	    System.out.println("File Count:"+fileNames.size());
	}

}
