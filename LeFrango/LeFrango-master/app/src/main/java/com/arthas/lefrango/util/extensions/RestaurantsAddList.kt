package com.arthas.lefrango.util.extensions

import androidx.databinding.BindingAdapter
import com.arthas.lefrango.R
import com.arthas.lefrango.data.menu.home.Restaurants
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

class RestaurantsAddList {


    @BindingAdapter("android:src")
    fun bindImageView(view: RoundedImageView, url: String?) {
        Picasso.get()
            .load(url)
            .fit().centerCrop()
            .into(view)
    }

    fun listOfRestaurants(): List<Restaurants> {
        val restaurants = mutableListOf<Restaurants>()

        restaurants.add(
            Restaurants(
                R.drawable.ic_kizilay,
                "",
                "Kurulduğu 1868 yılından bu yana toplumsal dayanışmayı sağlamak, sosyal refahın gelişmesine katkıda bulunmak, yoksul ve muhtaç insanlara barınma, beslenme ve sağlık yardımı ulaştırmak için önemli görevler üstlenen Türk Kızılay, kan, afet, uluslararası yardım, göç ve mülteci hizmetleri, sosyal hizmetler, sağlık, ilk yardım, eğitim, gençlik ve mineralli su işletmeleri alanlarında faaliyet sunar."
            )
        )

        restaurants.add(
            Restaurants(
                R.drawable.ic_akut,
                "",
                "Mağara, dağ gibi ulaşılması zor olabilecek yerlerde veya doğal afetlerde kaybolanların aranması ve bu koşullarda kaza geçirenlerin kurtarılması için etkinlik gösteren, Türkiye'nin arama kurtarma konusunda ilk sivil toplum örgütü olan dernek."
            )
        )

        restaurants.add(
            Restaurants(
                R.drawable.ic_aciltip,
                "",
                "Acil Tıp Sistemi içinde yer alan konularda (acil tıp uzmanlığı, acil servisler, hastane öncesi acil yardım, ilkyardım, afet organizasyonu) projeler üretmek ve bu sistemin gelişmesine destek olmak amacıyla oluşturulmuş bir Sivil toplum kuruluşu'dur."
            )
        )

        restaurants.add(
            Restaurants(
                R.drawable.ic_afad,
                "",
                "29 Mayıs 2009 tarih ve 5902 sayılı kanunun 17 Haziran 2009'da Resmî Gazete'de yayımlanmasıyla; İçişleri Bakanlığı Sivil Savunma Genel Müdürlüğü (SSGM) ve Bayındırlık ve İskan Bakanlığı Afet İşleri Genel Müdürlüğü, Başbakanlık Afet ve Acil Durum Yönetimi Genel Müdürlüğü yerine kurulan afet ve acil durum yönetimi kurumudur. Afet öncesi hazırlık ve zarar azaltma, afet esnasında yapılacak müdahale ve afet sonrasındaki iyileştirme çalışmalarının yönetim ve koordinasyonunu gerçekleştirmek, kurumun temel görev ve amacıdır."
            )
        )

        restaurants.add(
            Restaurants(
                R.drawable.ic_tog,
                "",
                "2002 yılında kurulan Toplum Gönüllüleri Vakfı (TOG), “Gençlerin öncülüğünde, yetişkinlerin rehberliğinde” prensibiyle hareket eder ve gençlere sosyal sorumluluk eğitimleri vererek toplumsal sorunlara çözüm üreten projeler gerçekleştirmeleri için alan yaratır."
            )
        )

        restaurants.add(
            Restaurants(
                R.drawable.ic_sendegel,
                "",
                "Dernek, ulusal ve uluslararası alanda sınır tanımaksızın, sosyal ve ekonomik alanda nitelikli ve sürdürülebilir gelişimi sağlamak amacıyla yoksullukla mücadele ederek, istihdam yaratarak, kadınların ve gençlerin gelişimine yönelik projeler üreterek, ulusal ve uluslararası alanda sivil ve diğer benzer hedefleri bulunan kuruluşlarla işbirliği yaprak,Birleşmiş Miletler Bin Yıl Hedefleri’ne sadık kalarak ve özellikle “en az gelişmiş ülkelerin” sorunlarını gözeterek yapılandıracağı projeleri hayata geçirmek amacıyla kurulmuştur."
            )
        )

        return restaurants
    }
}