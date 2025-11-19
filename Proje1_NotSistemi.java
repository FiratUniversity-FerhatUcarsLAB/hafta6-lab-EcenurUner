Ad Soyad:Ecenur Üner
Öğrenci Numarası:250541078
Tarih:19.11.2025
Açıklama:Görev 1 - Not Listesi

  import java.util.Scanner;
  public class NotSistemi {
      public static double calculateAverage(int vize_notu, int final_notu, int odev_notu) {
          return vize_notu * 0.3 + final_notu * 0.4 + odev_notu * 0.3;
      }

      public static boolean isPassingGrade(double ortalama) {
          if(ortalama >= 50){
              return true;
          }else{
              return false;
          }
      }

      public static String getLetterGrade(double ortalama) {
          String harf_notu = "GEÇERSİZ";
          if(ortalama >= 90 && ortalama <= 100){
              harf_notu = "A";
          }else if (ortalama >= 80 && ortalama <= 90) {
              harf_notu = "B";
          }else if (ortalama >= 70 && ortalama <= 80) {
              harf_notu = "C";
          }else if (ortalama >= 60 && ortalama <= 70) {
              harf_notu = "D";
          }else { //60dan küçük ise
              harf_notu = "F";
          }
          return harf_notu;
      }

      public static boolean isHonorList(double ortalama, int vize_notu, int final_notu, int odev_notu){
          if(ortalama >= 85 && vize_notu >= 70 && final_notu >= 70 && odev_notu >= 70){
              return true;
          }else{
              return false;
          }
      }

      public static boolean hasRetakeRight(double ortalama) {
          if(ortalama >= 40 && ortalama < 50){
              return true;
          }else{
              return false;
          }
      }

      public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);
          int vize_notu, final_notu, odev_notu;

          System.out.print("Vize notunu girin: ");
          vize_notu = scanner.nextInt();

          System.out.print("Final notunu girin: ");
          final_notu = scanner.nextInt();

          System.out.print("Ödev notunu girin:");
          odev_notu = scanner.nextInt();


          System.out.println("=== OGRENCİ NOT RAPORU===");
          System.out.println("Vize notu:" + vize_notu);
          System.out.println("Final notu:" + final_notu);
          System.out.println("Ödev notu:" + odev_notu);
          System.out.println("------------------");
          double ortalama = calculateAverage(vize_notu, final_notu, odev_notu);
          System.out.println("Ortalama: " + ortalama);
          System.out.println("Harf Notu: " + getLetterGrade(ortalama));
          if(isPassingGrade(ortalama)){
              System.out.println("Durum: GECTİ");
          }else{
              System.out.println("Durum: KALDI");
          }

          System.out.println("Onur Listesi: " + (isHonorList(ortalama, vize_notu, final_notu, odev_notu) == true ? "EVET" : "HAYIR"));
      }
  }
