Ad Soyad:Ecenur Üner
Öğrenci Numarası.250541078
Tarih:22.11.2025
Görev 2 - Sinema Bileti

  import java.util.Scanner;
public class SinemaBileti {
    private static final double INDIRIM_OGRENCİ_HAFTAICI = 0.20; // %20 (Pzt-Prş)
    private static final double INDIRIM_OGRENCİ_HAFTASONU = 0.15; // %15 (Cuma-Pazar)
    private static final double INDIRIM_65_YAS_USTU = 0.30; // %30(Her gün)
    private static final double INDIRIM_12_YAS_ALTI = 0.25; // %25(Her gün)
    private static final double INDIRIM_OGRETMEN_CARSAMBA = 0.35; // %35 (Sadece Çarşamba)

    public boolean isWeekend(int gun) {
        return gun == 6 || gun == 7;
    }

    public boolean isMatinee(double saat) {
        return saat < 12.00;
    }

    public double calculateBasePrice(int gun, double saat) {
        boolean isWeekend = isWeekend(gun);
        boolean isMatinee = isMatinee(saat);
        double basePrice = 0.0;

        if (!isWeekend) {
            if (isMatinee) {
                basePrice = 45.0;
            } else {
                basePrice = 65.0;
            }
        } else {
            if (isMatinee) {
                basePrice = 55.0;
            } else {
                basePrice = 85.0;
            }
        }
        return basePrice;
    }

    public double calculateDiscount(int yas, int meslek, int gun){
    double maxDiscountRate = 0.0;
    if (yas < 12) {
        maxDiscountRate = Math.max(maxDiscountRate, INDIRIM_12_YAS_ALTI);
    }else if ( yas >= 65) {
        maxDiscountRate = Math.max(maxDiscountRate, INDIRIM_65_YAS_USTU);
    }

    switch(meslek){
        case 1:
            if (gun >= 1 && gun <= 4){
                maxDiscountRate = Math.max(maxDiscountRate, INDIRIM_OGRENCİ_HAFTAICI);
            } else if (gun >= 5 && gun <= 7){
                maxDiscountRate = Math.max(maxDiscountRate, INDIRIM_OGRENCİ_HAFTASONU);
            }
            break;
        case 2:
            if (gun == 3){
                maxDiscountRate = Math.max(maxDiscountRate, INDIRIM_OGRETMEN_CARSAMBA);
            }
            break;
        case 3: // Diğer _Ek indirim yok
        default:
            //maxDiscountRate zaten 0.0 veya yaş indirimi
            break;
    }
    return maxDiscountRate;
    }

    public double getFormatExtra(int filmTuru){
        double extraFee = 0.0;

        switch (filmTuru){
            case 1: //2D 8Temel)
                extraFee = 0.0;
                break;
            case 2: // 3D
                extraFee = 25.0;
                break;
            case 3: // IMAX
                extraFee = 35.0;
                break;
            case 4: // 4DX
                extraFee = 50.0;
                break;
            default:
                System.out.println("Hata: Gecersiz film turu secimi.");
                extraFee = 0.0;
                break;
        }
        return extraFee;
}

  public double calculateFinalPrice(int gun, double saat, int yas, int meslek, int filmTuru){
    double basePrice = calculateBasePrice(gun, saat) ;
    double discountRate = calculateDiscount(yas, meslek, gun);
    double formatExtra = getFormatExtra(filmTuru);

    // İndirimli temel fiyatı
    double discountedBasePrice = basePrice * (1.0 - discountRate);

    //   Nihai fiyat =  İndirimli temel fiyat + Format ekstra üctreti
      double finalPrice = discountedBasePrice + formatExtra;

    // Fiyatı 2 ondalık basamağa yuvarlayalım
    return Math.round(finalPrice * 100.0) / 100.0;
    }

    public void generateTicketInfo(int gun, double saat, int yas, int meslek, int filmTuru){
        double basePrice = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double formatExtra = getFormatExtra(filmTuru);
        double finalPrice = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);

        String gunAdi = getDayName(gun);
        String meslekAdi = getProfessionName(meslek);
        String filmTuruAdi = getFormatName(filmTuru);

        // Bilet Bilgisi Çıktısı
        System.out.println("--- Bilet Bilgileri ---");
        System.out.println("Gun: " + gunAdi + " (Gun Kodu: " + gun + ")");
        System.out.println("Saat: " + String.format("%.2f", saat) + " (" + (isMatinee(saat) ? "Matinee" : "Normal") + ")");
        System.out.println("Yas: " + yas);
        System.out.println("Meslek: " + meslekAdi);
        System.out.println("Film Formati: " + filmTuruAdi);
        System.out.println("---------------------------");
        System.out.println("Temel Fiyat: " + String.format("%.2f", basePrice) + "TL");

        if (discountRate > 0){
            System.out.println("Uygulanan Indirim Orani: %" + (int)(discountRate * 100));
            double discountAmount = basePrice * discountRate;
            System.out.println("Indirim Tutari: " + String.format("%.2f", discountAmount) + " TL");
            System.out.println("Indirimli Fiyat: " + String.format("%.2f", (basePrice - discountAmount)) + " TL");
        }else{
            System.out.println("Indirim Uygulanmadi.");
        }

        if (formatExtra > 0){
            System.out.println("Format Ekstra Ucreti (" + filmTuruAdi + "): " + String.format("%.2f", formatExtra) + " TL");
        }else{
            System.out.println("Format Ekstra Ucreti: Yok");
        }

        System.out.println("-------------------");
        System.out.println("** Nihai Odenecek Tutar: " + String.format("%.2f", finalPrice) + " TL **");
    }

    private String getDayName(int gun){
        return switch (gun){
            case 1 -> "Pazartesi";
            case 2 -> "Sali";
            case 3 -> "Carsamba";
            case 4 -> "Persembe";
            case 5 -> "Cuma";
            case 6 -> "Cumartesi";
            case 7 -> "Pazar";
            default -> " Gecersiz Gun";
        };
    }

    private String getProfessionName(int meslek){
        return switch (meslek){
            case 1 -> "Ogrenci";
            case 2 -> "Ogretmen";
            case 3 -> "Diger";
            default -> "Bilinmiyor";
        };
    }

    private String getFormatName(int filmTuru){
        return switch (filmTuru){
            case 1 -> "2D";
            case 2 -> "3D";
            case 3 -> "IMAX";
            case 4 -> "4DX";
            default -> "Bilinmiyor";
        };
    }

    public static void main(String[] args) {
        SinemaBileti hesaplayici = new SinemaBileti();

        System.out.println("\n--- Ornek 1: Ogrenci, Sali, Matine, 3D (45-9+25 = 61.00 TL) ---");
        hesaplayici.generateTicketInfo(2, 11.00, 20, 1, 2);

        System.out.println("\n--- Ornek 2: 65+ Yas, Pazar, Normal, IMAX (85-25.5+35 = 94.50 TL) ---");
        hesaplayici.generateTicketInfo(7, 14.30, 70, 3, 3);

        System.out.println("\n--- Ornek 3: Ogretmen, Carsamba, Normal, 4DX (65-22.75+50 = 92.25 TL)---");
        hesaplayici.generateTicketInfo(3, 19.00, 40, 2, 4);

        System.out.println("\n--- Ornek 4: Diger, Cuma, Normal, 2D (85 TL) ---");
        hesaplayici.generateTicketInfo(5, 15.00, 30, 3, 1);
    }
}
