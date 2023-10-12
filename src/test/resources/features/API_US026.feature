Feature: API_US026 Bir yönetici (admin) olarak API bağlantısı üzerinden tüm pickup listesine erişebilmek istiyorum.
  @US026
  Scenario: GET request gönderildiginde dönen status code'un 200 oldugu ve response message bilgisinin
            "Success" oldugu dogrulanmali. Response body icindeki lists icerigi (id'si = "1", olan veri içeriğindeki
            "title": "Cargo Security and Risk Management Crucial Aspects in Transportation", "image_id": 35) olduğu
             doğrulanmalı.

    Given Admin path'deki parametrleri kurar "api/blog/list".
    Then Admin pickup listesi icin GET request gonderir
    Then Donen status code'un 200 oldugu ve response message bilgisinin Success oldugu dogrulanmali
    Then Lists icerigi (id'si = 1, olan veri icerigindeki title: Cargo Security and Risk Management Crucial Aspects in Transportation, image_id: 35) oldugu dogrulanmali.