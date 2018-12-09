package hla.rti1516.jlc;

import hla.rti1516.RTIinternalError;

import java.util.Properties;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class RtiFactoryFactory
{
   private static final String SETTINGS_FILE = "RTI1516-list.properties";

   public static RtiFactory getRtiFactory(String factoryClassName)
      throws RTIinternalError
   {
      try {
         Class cls = Class.forName(factoryClassName);
         return (RtiFactory)cls.newInstance();
      } catch (ClassNotFoundException e) {
         throw new RTIinternalError("Cannot find class " + factoryClassName);
      } catch (InstantiationException e) {
         throw new RTIinternalError("Cannot instantiate class " + factoryClassName);
      } catch (IllegalAccessException e) {
         throw new RTIinternalError("Cannot access class " + factoryClassName);
      }
   }

   public static RtiFactory getRtiFactory() throws RTIinternalError
   {
      String userHomeDir = System.getProperty("user.home");
      File propertiesFile = new File(userHomeDir, SETTINGS_FILE);

      if (propertiesFile.exists()) {
         Properties properties = new Properties();
         try {
            InputStream is = new FileInputStream(propertiesFile);
            properties.load(is);
            is.close();
         } catch (IOException e) {
            throw new RTIinternalError("Error reading Link Compatibility settings file");
         }

         String defaultRTI = properties.getProperty("Default");
         if (defaultRTI != null) {
            String factoryClassName = properties.getProperty(defaultRTI + ".factory");
            if (factoryClassName == null) {
               throw new RTIinternalError("Cannot find factory class setting for default RTI");
            }

            return getRtiFactory(factoryClassName);
         }
      }

      // Provide a reasonable default if no setting found
      return getRtiFactory("se.pitch.prti.RtiFactoryImpl");
   }

   public static Map getAvailableRtis() throws RTIinternalError
   {
      String userHomeDir = System.getProperty("user.home");
      File propertiesFile = new File(userHomeDir, SETTINGS_FILE);

      if (!propertiesFile.exists()) {
         throw new RTIinternalError("Cannot find file " + propertiesFile);
      }

      Properties properties = new Properties();
      try {
         InputStream is = new FileInputStream(propertiesFile);
         properties.load(is);
         is.close();
      } catch (IOException e) {
         throw new RTIinternalError("Error reading Link Compatibility settings file");
      }

      Map map = new HashMap();
      int index = 1;
      while (true) {
         String rtiName = properties.getProperty(index + ".name");
         String rtiFactory = properties.getProperty(index + ".factory");
         if (rtiName == null || rtiFactory == null) {
            break;
         }
         map.put(rtiName, rtiFactory);
         index++;
      }

      return map;
   }
}
