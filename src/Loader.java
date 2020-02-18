import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Loader
{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String fileName = reader.readLine();
            if(fileName.compareToIgnoreCase("exit") == 0){
                break;
            }
            else {
                File folder = new File(fileName);

                if (!folder.exists()) {
                    System.out.println("Invalid file name! Enter another name.");
                    continue;
                } else if (folder.isDirectory()) {
                    String size = getDirectorySize(folder);
                    System.out.println("The size of the contents of the folder is - " + size);
                }
                else if(folder.isFile()){
                    String dir = folder.getParent();
                    File direct = new File(dir);
                    String size = getDirectorySize(direct);
                    System.out.println("The size of the contents of the folder containing this file is - " + size);
                }
            }
        }
        reader.close();
    }

    private static String getDirectorySize(File file){
        File[] files = file.listFiles();
        long sum = 0;
        for (File f : files) {
            sum += f.length();
        }
        System.out.println(sum);

        if(sum > 1073741824){
            double res = round(sum/1073741824.0);
            return res + " GB";
        }
        else if(sum > 1048576){
            double res = round(sum/1048576.0);
            return res + " MB";
        }
        else if(sum > 1024){
            double res = round(sum/1024.0);
            return res + " kB";
        }
        else{
            return sum + " B";
        }
    }

    private static double round(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }
}
