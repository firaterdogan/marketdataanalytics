# Market Data Analytics

**Market Data Analytics** yazılımı hisse senedi fiyat değişimlerini 3 ayrı veri sağlayıcı firmadan alarak kullanıcılara hisse senedi piyasası için analitik bilgileri sağlamayı amaçlayan bir üründür.

## Nasıl Çalışıyor?

 **1.**  Üç ayrı veri sağlayıcı firmanın her biri, her gün kendilerinin ürettiği birer dosyaya sürekli hisse senedi fiyat değişimlerini yazar.

Her bir değişim satırı şu bilgileri içerir.

`yyyy-MM-dd HH:mm:ss formatında tarih | hisse senedi kodu | hisse senedi önceki fiyatı	| hisse senedi yeni fiyatı`

 **2.**  Yazılımdaki fiyat okuma modülü o güne ait bu 3 dosyayı sürekli okuyarak fiyat değişimlerini veritabanına kaydeder.
 
**3.** Market Data Analytics kullanıcıları uygulamanın ekranını açtıklarında, geçmiş 5 saniyedeki hisse senedi fiyat değişim adetlerini görebilmektedirler.

Her analitik satırı şu bilgileri içerir:

`hisse senedi kodu | artış sayısı | azalış sayısı`

Örnek bir satır:

`ISCTR | 7 | 4`

**4.** Kullanıcı ekranda olduğu sürece sürekli son 5 saniyedeki hisse senedi fiyat değişim adetleri gelir. Yazılımın veri yayınlama modülü her 1 er saniye bekleme yaparak sürekli kullanıcılara son güncel 5 saniyelik veriyi dağıtmaktadır.

**5.** Sistem admini yazılımın configurasyonunda **x** saniye boyunca 1 veri sağlayıcı firmadan yeni fiyat verisi gelmiyorsa sistemin admin kullanıcısına otomatik alarm epostası atabilmesini kontrol edebilmektedir.

## Nasıl Çalışmalı?

Ürün MVP sürecini tamamlamış ve müşterimiz tarafından çok beğenilmiştir. Ancak yazılımda fonksiyonalite ve performans açısından problemler bulunmaktadır. Müşterimiz ürünü kullanmaya başlamak için bu sorunların düzeltilmesini talep etmektedir.

**Senden beklentimiz aşağıdaki problemlere mevcut yazılımı değiştirerek çözüm bulman!
Müşterimizin ilettiği taleplerde herhangi bir öncelik sırası bulunmuyor, tek kural eksik veya hatalı ama kesinlikle çalışan bir yazılım teslim etmen!
Şimdiden çok teşekkürler!**

**1.** Veri sağlayıcı firmalardan alınarak veritabanında saklanan bilgileri veritabanından okuyarak kullanıcılara analitik sonuçlar iletiminde performans sorunları tespit edilmiştir. Mevcuttaki çalışma şeklinin yerine aşağıdaki yöntemlerden bir veya bir kaçı ile veya sizin uygulayacağınız farklı yöntemler ile performans probleminin giderilmesi beklenmektedir.
   
   - Piyasa verisi veritabanı yerine bellekte tutularak işlenebilir. (Önerilen çözüm)
   - Veritabanı veri modeli değiştirilebilir.
   - Veritabanından verinin okunma ve işlenme yöntemleri değiştirilebilir.
   - Cacheleme yöntemleri uygulanabilir.
   - Veri boyutları küçültülebilir.

**2.** (Verilerin bellekte tutulacağı yöntemde) Yönetici kullanıcı uygulamanın belleğinde tutulacak veri miktarını parametrik olarak kontrol etmek istemekte ve bu sayede uygulama belleğini etkili kullanmak istemektedir.

Örneğin uygulama belleğindeki veri sayısı "1000" satırı geçince en eski "250" satırı sil, 750 satırı bellekte tutmaya devam et şeklinde yönetimini talep etmektedir.
   
**3.** (Verilerin bellekte tutulacağı yöntemde) Silinen satırların, silindiği an silinme zamanı ile birlikte veritabanına taşınmasını istemektedir.

**4.** (Verilerin bellekte tutulacağı yöntemde) Yönetici kullanıcı, silinmesi gereken M satır içindeki verilere ekran kullanıcıları son X saniyede erişmişse bu satırlara istisna tanınarak silinmemesini

Örneğin, uygulamadaki veri satırı sayısı "1000" olmuş ve 250 satırın silinmesi gerekmektedir ancak bu 250 satırın 50 tanesi son 10 saniyede kullanıcılara verilen analitik datasının oluşturulması için kullanılmış, bu yüzden bu 50 satır silinmez sadece 200 satır silinir.

**5.** Kullanıcılar hisse senedi fiyat değişimi analitiğini görecekleri süreleri kendileri ekrandan yönetebilmek istemektedir.

Örneğin  Ahmet kullanıcısı ekranında 10 saniye seçerek son 10 saniyedeki fiyat değişimlerini görebilmeli, aynı anda Mehmet kullanıcısı ekranında 135 saniye seçerek son 135 saniyedeki analitik verisini görebilmelidir.

**6.** Kullanıcılar uygulamayı açtıklarında en son tercih ettikleri fiyat izleme aralığı değerinin kullanılmasını istemektedirler.
   
**7.** Mevcut yazılımda birden fazla veri sağlayıcı firma aynı anda, aynı fiyat değişimini yazması durumunda fiyat değişim adeti satır sayısı kadar hesaplanmıştır. Ancak müşterimiz aynı anda aynı fiyat değişimi birden fazla veri 	sağlayıcı tarafından dosyalarına yazılsa dahi bunun 1 fiyat değişimi olarak hesaplanmasını istemektedir.

   Örneğin 2020-05-07 12:11:59 - 2020-05-07 12:12:00 zaman aralığında:
   
**A Veri Sağlayıcı Firma Satırları**
   
`2020-05-07 12:11:59|AKBNK|1.01|0.18`

`2020-05-07 12:11:59|ISCTR|0.42|0.32`

`2020-05-07 12:11:59|GARAN|2.95|2.74`

`2020-05-07 12:12:00|AKBNK|0.18|0.91`

   
 **B Veri Sağlayıcı Firma Satırları**
   
`2020-05-07 12:11:59|AKBNK|0.79|0.18`

`2020-05-07 12:11:59|GARAN|2.95|3.71`

`2020-05-07 12:11:59|ISCTR|0.42|0.32`

`2020-05-07 12:12:00|GARAN|3.71|1.83`

`2020-05-07 12:12:00|AKBNK|0.18|0.91`

`2020-05-07 12:12:00|GARAN|1.83|1.57`


 **C Veri Sağlayıcı Firma Satırları**
   
`2020-05-07 12:11:59|GARAN|3.07|3.71`

`2020-05-07 12:11:59|AKBNK|0.03|0.18`

`2020-05-07 12:11:59|ISCTR|0.42|0.32`

`2020-05-07 12:11:59|AKBNK|0.18|0.25`

`2020-05-07 12:12:00|GARAN|3.71|1.83`

`2020-05-07 12:12:00|AKBNK|0.25|0.91`


ISCTR fiyat değişimi analitiği mevcut yazılımda şöyle gösterilmiştir.
   
`AKBNK | 5 | 2`
   
 Ancak aslında 
 "2020-05-07 12:12:00|AKBNK|0.18|0.91" satırı 2 dosya içinde tekrar ettiği için olması gereken gösterim
   
 `AKBNK | 4 | 2`
 
 şeklindedir.
 
 **8.** Mevcuttaki fiyat dağıtımı modülü, kullanıcısı sayısı arttıkça kullanıcıları yeni veriler için daha fazla bekletecek yapıdadır. Örneğin sisteme bağlı 10 kullanıcı var ise 1. kullanıcıdan başlayarak 10. kullanıcıya kadar veriler dağıtılır, ardından 1 saniye beklenerek yeniden 10 kullanıcıya dağıtım yapılır.
Kullanıcıları güncel veri için daha az bekletebilecek bir yapı kurgulanarak geliştirmesi yapılmalıdır.

**9.** Sistem admini yazılımın configurasyonunda **x** saniye boyunca 1 veri sağlayıcı firmadan yeni fiyat verisi gelmiyorsa sistemin admin kullanıcısına otomatik eposta atabilmesi kurgusunda şu şekilde bir değişiklik istenmektedir:
Eğer bu durum 3 veri sağlayıcı için de yaşanıyorsa yönetici kullanıcıya alarm epostası atılmasına gerek yoktur.

Örneğin:

A firmasından 10 saniye boyunca veri gelmemiş ama aynı 10 saniye içinde B ve C  firmalarından da yeni veri gelmemişse eposta atılmamalı, Ancak en az bir firmadan aynı süre içinde veri gelmiş, bir veya birden fazla firmadan veri gelmemişse, veri gelmeyen firma(lar) için alarm epostası atılmalıdır.

**10.** Yeni hisse senedi eklenmesi veya bir hisse senedinin kodunun değişmesi durumunda önyüzde yeni hisse senedi kodunun görüntülenemediği bulgusu iletilmiştir. Bu durumun düzeltilmesi istenmektedir.

# Modüller

## MarketDataProviderSimulator

MarketDataAnalytics yazılımı çalışırken piyasa verileri sağlayıcılarının verileri kendilerine ait dosyalara yazmasını simule eden uygulamadır. 

İçerisindeki start.bat ile çalıştırılabilir. start.bat içeriği aşağıdaki gibidir.

`java -jar -Dpath="C:\tmp\marketdata" -Dlamda=500 MarketDataProviderSimulator.jar`

dosyaların yazılacağı klasörü gösteren **path** ve test ederken verilerin yoğunluğunu kontrol etmek için kullanabileceğiniz **lamda** değişkenini kendiniz değiştirerek kullanabilirsiniz.

**lamda** değişkeni azaltıldıkça daha saniyede yazılacak fiyat değişim sayıları artacaktır, **lamda** attırıldıkça fiyat değişim sıklığı azalacaktır.

## MarketDataAnalytics

### Gereksinimler
- Java 8

### Konfigurasyon
- Uygulama konfigurasyonları /resource/application.yml üzerinden okunmaktadır.
- uygulama h2 inmemory veritabanı ile çalışmaktadır. Geliştirme aşamasında ayrı bir veritabanına ihtiyaç duyulmamaktadır.
- mail konfigurasyonlarında gerçek username ve password verilmemiştir geliştirme ortamında hata vermesi normaldir.
- aşağıdaki uygulama konfigurasyonlarının açıklamaları yanlarına yazılmıştır


`marketdataanalytics.dataloader.tail.delay` => veri sağlayıcı firmaların dosyalarında yeni veriler okunurken yeni veri satırı olmaması durumunda yapılan bekleme süresi (ms)

`marketdataanalytics.marketdata.root.folder` => veri sağlayıcı firmaların dosyalarının bulunduğu klasör

`marketdataanalytics.dataprovidercheck.check.interval` => firmaların veri akışının devam edip etmediği kontrolunde bakılan süre aralığı (ms)

`marketdataanalytics.dataprovidercheck.check.delay` => firmaların veri akışının devam edip etmediği kontrolünün sıklığı (ms)

`marketdataanalytics.dataprovidercheck.check.to` => firmaların veri akşında problem olması durumunda eposta atılacak adres

`marketdataanalytics.stockvolatilities.feeder.interval` => Önyüzlere gönderilen analitik veriyi oluşturmak için kullanılan süre aralığı

`marketdataanalytics.stockvolatilities.feeder.delay` => iki veri oluşturma döngüsü arasındaki bekleme süresi
