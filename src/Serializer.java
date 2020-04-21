/**
 *Serializer class allows to save a full root project into a file and load it 
 *later
 *Has two methods: one for input (inputSerializer) and one 
 *for output (outputSerializer).
 *Both use the same text file: serializer.txt
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Serializer {

  static Logger logger = LoggerFactory.getLogger("Serializer");

  /*
   * Gets the saved root object from serializer.txt and returns it.
   */
  public Activity inputSerializer() {
  
    Activity root = null;
  
    try {
      /**
       * Creates the input stream, gets the object from file and deletes 
       * the input stream
       */
      FileInputStream file = new FileInputStream(new File("serializer.txt"));
      ObjectInputStream object = new ObjectInputStream(file);
    
      root = (Project) object.readObject();

      object.close();
      file.close();
    
    } catch (IOException e) {
      logger.debug("IOException error");
    } catch (ClassNotFoundException e) {
      logger.debug("Class not found error");
    }
    return root;
  }

  /**
   * Loads the root object into serializer.txt
   */
  public void outputSerializer(Activity root) {
    try {
      /**
       * Creates the output stream, saves the object into file and deletes 
       * the output stream
       */
      FileOutputStream file = new FileOutputStream(new File("serializer.txt"));
      ObjectOutputStream object = new ObjectOutputStream(file);
        
      object.writeObject(root);
  
      object.close();
      file.close();
        
    } catch (IOException e) {
      logger.debug("IOException error");
    }
  }
}
