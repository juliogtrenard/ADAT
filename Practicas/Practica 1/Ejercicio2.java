package ejercicios;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.nio.file.Paths;

public class Ejercicio2 {

    public static void main(String[] args) {
        try {
            /*
             * Clave AES de 128 bits (16 bytes) que es
             * lo más común en el cifrado AES
             */
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // Clave de 128 bits
            SecretKey secretKey = keyGen.generateKey();
            
            /*
             * Generar un vector de inicialización aleatorio
             */
            byte[] iv = new byte[16]; // AES usa un IV de 16 bytes
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            /*
             * Conseguimos la ruta absoluta del directorio donde se ejecuta este 
             * programa, por lo que el archivo que intentamos cifrar (palabras.txt)
             * y en el que las descifraremos están en el mismo directorio
             */
            String currentDir = Paths.get("").toAbsolutePath().toString()+"/Practicas/Practica 1";

            /*
             * Ruta de los 3 archivos a leer y escribir, los cuales se encuentran
             * en el mismo directorio del programa
             */
            File inputFile = new File(currentDir + "/palabras.txt");
            File encryptedFile = new File(currentDir + "/encriptadas2.bin");
            File decryptedFile = new File(currentDir + "/desencriptadas2.txt");

            /*
             * Comprobamos que el archivo de entrada existe para poder
             * leer las palabras que queremos cifrar
             */
            if (!inputFile.exists()) {
                System.out.println("El archivo de entrada no se encuentra: " + inputFile.getAbsolutePath());
                return;
            }

            /*
             * Ciframos el archivo palabras.txt, usando la clave y
             * escribe el contenido en encriptadas2.bin
             */
            encryptFile(secretKey, ivSpec, inputFile, encryptedFile);

            /*
             * Método para desencriptar el archivo, mismos parámetros que el anterior
             * y misma funcionalidad
             */
            decryptFile(secretKey, ivSpec, encryptedFile, decryptedFile);
            
            System.out.println("Cifrado y descifrado completado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para cifrar un fichero
    public static void encryptFile(SecretKey key, IvParameterSpec iv, File inputFile, File outputFile) throws Exception {
        /*
         * Configuramos el cifrado AES con el modo CBC y le metemos de relleno
         * PKCS5Padding, iniciamos el Cipher en modo de encriptación 
         * con la clave secreta y el IV.
         */
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        /*
         * Abrimos el FileInputStream para leer el fichero y el 
         * FileOutputStream para escribir en otro el resultado de la encriptación
         */
        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);

        /*
         * Leemos el archivo en bloques de 64 bytes, lo ciframos y después
         * lo escribimos en el fichero del OutputStream
         */
        byte[] buffer = new byte[64];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) {
                outputStream.write(output);
            }
        }
        
        //El contenido restante del fichero lo escribimos en el fichero de salida
        byte[] outputBytes = cipher.doFinal();
        if (outputBytes != null) {
            outputStream.write(outputBytes);
        }

        inputStream.close();
        outputStream.close();
    }

    //Método para descifrar un fichero
    public static void decryptFile(SecretKey key, IvParameterSpec iv, File inputFile, File outputFile) throws Exception {
        /*
         * Configuramos el cypher en modo desencriptacion, tenemos el mismo proceso
         * que en el anterior proceso de cifrado
         */
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        /*
         * Lee el archivo encriptado y se escribe el texto desencriptado en otro
         */
        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        
        /*
         * Lee el archivo en bloques de 64 bytes, lo ciframos y después
         * lo escribimos en el fichero del OutputStream
         */
        byte[] buffer = new byte[64];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) {
                outputStream.write(output);
            }
        }
        
        //Escribe lo que falta y cierra los streams
        byte[] outputBytes = cipher.doFinal();
        if (outputBytes != null) {
            outputStream.write(outputBytes);
        }

        inputStream.close();
        outputStream.close();
    }
}
