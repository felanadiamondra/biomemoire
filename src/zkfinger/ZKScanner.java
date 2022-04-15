package zkfinger;

import bio.dao.EmpreinteDAO;
import com.zkteco.biometric.FingerprintSensorErrorCode;
import com.zkteco.biometric.FingerprintSensorEx;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import dao.factory.DAOFactory;
import bio.models.Empreinte;


public class ZKScanner {
  private byte[] imgbuf = null;
  
  private byte[] template = new byte[2048];
  
  private int[] templateLen = new int[1];
  
  int fpWidth = 0;
  
  int fpHeight = 0;
  
  private long mhDevice = 0L;
  
  private long mhDB = 0L;
  
  private static zkfinger.ZKScanner scanner;
  
  private boolean openned = false;
  
  public static zkfinger.ZKScanner getInstance() {
    if (scanner == null)
      scanner = new zkfinger.ZKScanner(); 
    return scanner;
  }
  
  public String open() {
    this.openned = false;
    if (0L != this.mhDevice)
      return "Fermer ld'abord le scanner"; 
    if (FingerprintSensorErrorCode.ZKFP_ERR_OK != FingerprintSensorEx.Init())
      return "Erreur d'initiation du Scanner"; 
    if (FingerprintSensorEx.GetDeviceCount() < 0)
      return "Aucun n'appareil est connect"; 
    this.mhDevice = FingerprintSensorEx.OpenDevice(0);
    if (0L == this.mhDevice)
      return "Erreur pendant l'ouverture du scanner"; 
    this.mhDB = FingerprintSensorEx.DBInit();
    if (0L == this.mhDB)
      return "Erreur pendant l'initiation du DB"; 
    if (0 == FingerprintSensorEx.DBSetParameter(this.mhDB, 5010, 0)) {
      byte[] paramValue = new byte[4];
      int[] size = new int[1];
      size[0] = 4;
      FingerprintSensorEx.GetParameters(this.mhDevice, 1, paramValue, size);
      this.fpWidth = byteArrayToInt(paramValue);
      size[0] = 4;
      FingerprintSensorEx.GetParameters(this.mhDevice, 2, paramValue, size);
      this.fpHeight = byteArrayToInt(paramValue);
      this.imgbuf = new byte[this.fpWidth * this.fpHeight];
    } 
    this.openned = true;
    return "Scanner connectet bien activé";
  }
  
  public boolean isOpenned() {
    return this.openned;
  }
  
  public boolean capture(String doigt, String matricule) {
    this.templateLen[0] = 2048;
    if (0 == FingerprintSensorEx.AcquireFingerprint(this.mhDevice, this.imgbuf, this.template, this.templateLen))
      try {
        writeBitmap(this.imgbuf, this.fpWidth, this.fpHeight, doigt+"-fingerprint.bmp");
        return true;
      } catch (IOException ex) {
        Logger.getLogger(zkfinger.ZKScanner.class.getName()).log(Level.SEVERE, (String)null, ex);
      }  
    return false;
  }
  
  /*public boolean capture() {
      return this.capture("ALL", "0000");
  }*/
  
  public boolean capture(){
      this.templateLen[0] = 2048;
      if(0 == FingerprintSensorEx.AcquireFingerprint(this.mhDevice, this.imgbuf, this.template, this.templateLen))
          try{
              writeBitmap(this.imgbuf, this.fpWidth, this.fpHeight, "fingerprint.bmp");
              return true;
              
          } catch(IOException ex){
              Logger.getLogger(zkfinger.ZKScanner.class.getName()).log(Level.SEVERE, (String)null, ex);
          }
      return false;
  }
  
  /*public ZKScannerMatch match() {
    ZKScannerMatch match = new ZKScannerMatch(this, 0, null);
    byte[] temp0 = new byte[2048];
    byte[] template1 = new byte[2048];
    int[] tempLen0 = new int[1];
    int[] templateLen1 = new int[1];
    tempLen0[0] = 2048;
    FingerprintSensorEx.ExtractFromImage(this.mhDB, "fingerprint.bmp", 500, temp0, tempLen0);
    for (Empreinte empreinte : DAOFactory.getEmpreinteDAO().getAll()) {
      templateLen1[0] = 2048;
      try {
        InputStream is = empreinte.getImage().getBinaryStream(1L, (int)empreinte.getImage().length());
        BufferedImage imag = ImageIO.read(is);
        ImageIO.write(imag, "bmp", new File("fingerprint1.bmp"));
        FingerprintSensorEx.ExtractFromImage(this.mhDB, "fingerprint1.bmp", 500, template1, templateLen1);
        match.score = FingerprintSensorEx.DBMatch(this.mhDB, template1, temp0);
        File delete = new File("fingerprint1.bmp");
        delete.delete();
        if (match.score > 0) {
          match.empreinte = empreinte;
          break;
        } 
      } catch (IOException|java.sql.SQLException e) {
        Logger.getLogger(zkfinger.ZKScanner.class.getName()).log(Level.SEVERE, (String)null, e);
      } 
    } 
    return match;
  }*/
  
  public ZKScannerMatch match(){
        ZKScannerMatch match= new ZKScannerMatch(0, null);
        byte[] temp0 = new byte[2048];
        byte[] template1 = new byte[2048];
        int[] tempLen0 = new int[1];
        int[] templateLen1 = new int[1];
        tempLen0[0]= 2048;
        FingerprintSensorEx.ExtractFromImage(this.mhDB, "fingerprint.bmp", 500, temp0, tempLen0);
        EmpreinteDAO empDAO = DAOFactory.getEmpreinteDAO();
        for(Empreinte empreinte : empDAO.getAll()){
            templateLen1[0] = 2048;
            try{
                InputStream is = empreinte.getImage().getBinaryStream(1L, (int)empreinte.getImage().length());
                BufferedImage imag = ImageIO.read(is);
                ImageIO.write(imag, "bmp", new File("fingerprint1.bmp"));
                FingerprintSensorEx.ExtractFromImage(this.mhDB, "fingerprint1.bmp", 500, template1, templateLen1);
                match.score = FingerprintSensorEx.DBMatch(this.mhDB, template1, temp0);
                File delete = new File("fingerprint1.bmp");
                delete.delete();
                if (match.score > 0) {
                    match.empreinte = empreinte;
                    System.out.println("There are correspondance");
                    break;
                } 
            }
            catch(IOException| java.sql.SQLException e){
                Logger.getLogger(ZKScanner.class.getName()).log(Level.SEVERE, (String)null, e);
            }
        }
        return match;
    }
  
    public ZKScannerMatch match(String matr){
        ZKScannerMatch match = new ZKScannerMatch(0, null);
        byte[] temp0 = new byte[2048];
        byte[] template1 = new byte[2048];
        int[] tempLen0 = new int[1];
        int[] templateLen1 = new int[1];
        tempLen0[0] = 2048;
        FingerprintSensorEx.ExtractFromImage(this.mhDB, "fingerprint.bmp", 500, temp0, tempLen0);
        for (Empreinte empreinte : DAOFactory.getEmpreinteDAO().getAllEmpreinte(matr)) {
            templateLen1[0] = 2048;
            try {
                InputStream is = empreinte.getImage().getBinaryStream(1L, (int)empreinte.getImage().length());
                BufferedImage imag = ImageIO.read(is);
                ImageIO.write(imag, "bmp", new File("fingerprint1.bmp"));
                FingerprintSensorEx.ExtractFromImage(this.mhDB, "fingerprint1.bmp", 500, template1, templateLen1);
                match.score = FingerprintSensorEx.DBMatch(this.mhDB, template1, temp0);
                File delete = new File("fingerprint1.bmp");
                delete.delete();
                if (match.score > 60) {
                    System.out.println("Oui");
                    match.empreinte = empreinte;
                    break;
                } 
                } catch (IOException|java.sql.SQLException e) {
                    Logger.getLogger(ZKScanner.class.getName()).log(Level.SEVERE, (String)null, e);
                } 
            } 
        return match;
    }
  
  public static void writeBitmap(byte[] imageBuf, int nWidth, int nHeight, String path) throws IOException {
    FileOutputStream fos = new FileOutputStream(path);
    DataOutputStream dos = new DataOutputStream(fos);
    int w = (nWidth + 3) / 4 * 4;
    int bfType = 16973;
    int bfSize = 1078 + w * nHeight;
    int bfReserved1 = 0;
    int bfReserved2 = 0;
    int bfOffBits = 1078;
    dos.writeShort(bfType);
    dos.write(changeByte(bfSize), 0, 4);
    dos.write(changeByte(bfReserved1), 0, 2);
    dos.write(changeByte(bfReserved2), 0, 2);
    dos.write(changeByte(bfOffBits), 0, 4);
    int biSize = 40;
    int biWidth = nWidth;
    int biHeight = nHeight;
    int biPlanes = 1;
    int biBitcount = 8;
    int biCompression = 0;
    int biSizeImage = w * nHeight;
    int biXPelsPerMeter = 0;
    int biYPelsPerMeter = 0;
    int biClrUsed = 0;
    int biClrImportant = 0;
    dos.write(changeByte(biSize), 0, 4);
    dos.write(changeByte(biWidth), 0, 4);
    dos.write(changeByte(biHeight), 0, 4);
    dos.write(changeByte(biPlanes), 0, 2);
    dos.write(changeByte(biBitcount), 0, 2);
    dos.write(changeByte(biCompression), 0, 4);
    dos.write(changeByte(biSizeImage), 0, 4);
    dos.write(changeByte(biXPelsPerMeter), 0, 4);
    dos.write(changeByte(biYPelsPerMeter), 0, 4);
    dos.write(changeByte(biClrUsed), 0, 4);
    dos.write(changeByte(biClrImportant), 0, 4);
    for (int i = 0; i < 256; i++) {
      dos.writeByte(i);
      dos.writeByte(i);
      dos.writeByte(i);
      dos.writeByte(0);
    } 
    byte[] filter = null;
    if (w > nWidth)
      filter = new byte[w - nWidth]; 
    for (int j = 0; j < nHeight; j++) {
      dos.write(imageBuf, (nHeight - 1 - j) * nWidth, nWidth);
      if (w > nWidth)
        dos.write(filter, 0, w - nWidth); 
    } 
    dos.flush();
    dos.close();
    fos.close();
  }
  
  public static byte[] changeByte(int data) {
    return intToByteArray(data);
  }
  
  public static byte[] intToByteArray(int number) {
    byte[] abyte = new byte[4];
    abyte[0] = (byte)(0xFF & number);
    abyte[1] = (byte)((0xFF00 & number) >> 8);
    abyte[2] = (byte)((0xFF0000 & number) >> 16);
    abyte[3] = (byte)((0xFF000000 & number) >> 24);
    return abyte;
  }
  
  public static int byteArrayToInt(byte[] bytes) {
    int number = bytes[0] & 0xFF;
    number |= bytes[1] << 8 & 0xFF00;
    number |= bytes[2] << 16 & 0xFF0000;
    number |= bytes[3] << 24 & 0xFF000000;
    return number;
  }
}
