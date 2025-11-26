/**
Ad-Soyad:Ecenur Üner
Öğrenci Numarası:250541078
Proje:Restoran Sipariş
Tarih:26.11.2025
  */
import java.util.Scanner;

public class RestoranSipariş {

    public static double getMainDishPrice(int secim) {
        return switch (secim) {
            case 1 -> 85.0; // Izgara Tavuk
            case 2 -> 120.0; // Adana Kebap
            case 3 -> 110.0; // Levrek
            case 4 -> 65.0; // Mantı
            default -> 0.0;
        };
    }

    public static double getAppetizerPrice(int secim) {
        return switch (secim) {
            case 1 -> 25.0; // Çorba
            case 2 -> 45.0; // Humus
            case 3 -> 55.0; // Sigara Böreği
            default -> 0.0;
        };
    }

    public static double getDrinkPrice(int secim) {
        return switch (secim) {
            case 1 -> 15.0; // Kola
            case 2 -> 12.0; // Ayran
            case 3 -> 35.0; // Taze Meyve Suyu
            case 4 -> 25.0; // Limonata
            default -> 0.0;
        };
    }


    public static double getDessertPrice(int secim) {
        return switch (secim) {
            case 1 -> 65.0; // Künefe
            case 2 -> 55.0; // Baklava
            case 3 -> 35.0; // Sütlaç
            default -> 0.0;
        };
    }

    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat < 17;
    }

    private static boolean isWeekday(int gun) {
        return gun >= 1 && gun <= 5;
    }

    public static double calculateDiscount(double araToplam, boolean combo, boolean ogrenci, int saat, double icecekFiyati, int gun) {
        double sonTutar = araToplam;

        System.out.println("\n--- İndirimler (Uygulama Sırası) ---");

        if (combo) {
            double indirimMiktari = sonTutar * 0.15;
            sonTutar -= indirimMiktari;
            System.out.printf("  > Combo (&&15): -%.2f TL\n", indirimMiktari);
        }

        if (isHappyHour(saat)) {
            double indirimMiktari = icecekFiyati * 0.20;
            sonTutar -= indirimMiktari;
            System.out.printf("  > Happy Hour (İçeçek %%20): -%.2f TL\n", indirimMiktari);
        }

        if (ogrenci) {
            if (isWeekday(gun)) {
                double indirimMiktarı = sonTutar * 0.10;
                sonTutar -= indirimMiktarı;
                System.out.printf("  > Öğrenci (Hafta İçi %%10): -%.2f TL\n", indirimMiktarı);
            }
        }

        if (sonTutar > 200.0) {
            double indirimMiktarı = sonTutar * 0.10;
            sonTutar -= indirimMiktarı;
            System.out.printf("  > 200 TL Üzeri (%%10): -%.2f TL\n", indirimMiktarı);
        }
        return sonTutar;
    }

    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---Restotan Sipariş Sistemi  ---");

        System.out.print("Ana Yemek Seçimi (1-4) [0:Yok]: ");
        int anaYemekSecim = scanner.nextInt();
        System.out.print("Başlangıç Seçimi (1-3) [0:Yok]: ");
        int baslangicSecim = scanner.nextInt();
        System.out.print("İçecek Seçimi (1-4) [0:Yok]: ");
        int icecekSecim = scanner.nextInt();
        System.out.print("Tatlı Seçimi (1-3) [0:Yok]:");
        int tatliSecim = scanner.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = scanner.nextInt();
        System.out.print("Öğrenci misiniz?(E/H): ");
        boolean ogrenci = scanner.next().trim().equalsIgnoreCase("E");
        System.out.print("Hangi gün? (1:Pzt, 7:Paz): ");
        int gun = scanner.nextInt();

        double anaYemekFiyat = getMainDishPrice(anaYemekSecim);
        double baslangicFiyat = getAppetizerPrice(baslangicSecim);
        double icecekFiyat = getDrinkPrice(icecekSecim);
        double tatliFiyat = getDessertPrice(tatliSecim);

        double araToplam = anaYemekFiyat + baslangicFiyat + icecekFiyat + tatliFiyat;

        boolean anaVar = anaYemekFiyat > 0;
        boolean icecekVar = icecekFiyat > 0;
        boolean tatliVar = tatliFiyat > 0;
        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);

        System.out.println("\n--- Hesap Özeti ---");
        System.out.printf("Ara Toplam: %.2f TL\n", araToplam);
        System.out.printf("Combo Menü: %s, Happy Hour: %s, Öğrenci İndirimi: %s\n", combo ? "VAR" : "YOK",
                isHappyHour(saat) ? "EVET" : "HAYIR", ogrenci && isWeekday(gun) ? "EVET" : "HAYIR");

        double sonTutar = calculateDiscount(araToplam, combo, ogrenci, saat, icecekFiyat, gun);

        System.out.println("-------------");
        System.out.printf("**Toplam (İndirimli): %.2f TL**\n", sonTutar);

        double bahsis = calculateServiceTip(sonTutar);
        System.out.printf("Bahşiş Önerisi (%%10): %.2f TL\n", bahsis);

        scanner.close();
    }
}
