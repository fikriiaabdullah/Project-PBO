package apkmanajemenaset;


public class APKManajemenAset {

    
    public static void main(String[] args) {
       koneksi k = new koneksi();
       k.connect();
       
       login l = new login();
       l.setVisible(true);
    }
    
}
