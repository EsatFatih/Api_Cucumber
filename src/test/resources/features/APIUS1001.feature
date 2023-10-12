Feature:
  @US1001
  Scenario: api/opdList endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde dönen status code'un 200 oldugu
  ve response message bilgisinin "Success" oldugu dogrulanmali

    Given Api kullanicisi sisteme admin olarak giris yapar
    And Api kullanicisi url ile birlikte iki parametreli giris yapar parametreler "api" ve "opdList"
    When Api kullanicisi body siz getRequest yapar
    Then Api kullanicisi istenen bilgileri test eder