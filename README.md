# ComitApplication
Comit örnek bir pair-programming projesidir.
Spring Boot ve Angular teknolojileri kullanarak hazırlanmış olan projenin detayları için https://aurorabilisim.atlassian.net/wiki/spaces/~626457728/pages/2367225873/COMIT adresini ziyaret edebilirsiniz.

Projenin client bölümü Angular ile hazırlanmış olup, çalıştırmak için bilgisayarınızda `npm` yüklü olması gerekmektedir. `npm` yüklemek için https://www.npmjs.com/get-npm adresini takip edebilirsiniz.
Angular CLI bilgisayarınında yüklü değilse aşağıda yazılı olan komutu çalıştırınız.
`npm install -g @angular/cli` 
Ardından
https://github.com/enesbahadir/ComitApplication/tree/main/client adresinde 
`ng serve` 
komutunu kullanınız ve http://localhost:4200/ sayfasını takip ediniz.

Projenin server bölümü Spring Boot ile hazırlanmış olup çalıştırmak için;
https://github.com/enesbahadir/ComitApplication/tree/main/comit adresinde
./mvnw clean komutunu ve 
java -jar target/*.war komutunu kullanınız. http://localhost:8080 adresinde server bölümü çalışmaya başlayacaktır.

Proje database olarak MySQL database kullanmaktadır. MySQL databse kurulumu için https://docs.oracle.com/javacomponents/advanced-management-console-2/install-guide/mysql-database-installation-and-configuration-advanced-management-console.htm#JSAMI116 adresini takip edebilirsiniz. 
Kullanıcı adı root, şifre root ve testdb adında bir schema oluşturunuz veya 
https://github.com/enesbahadir/ComitApplication/blob/main/comit/src/main/resources/application.properties adresinde bulunan

```
comit.app.jwtSecret= comitAppSecretKey
comit.app.jwtExpirationMs= 86400000

spring.datasource.url= jdbc:mysql://localhost:3306/testdb?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username= root
spring.datasource.password= root

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update
```
database ayarlarını kendi oluşturuduğunuz schema'ya göre değiştiriniz.

Programı çalıştırmadan önce MySQL database'de 
```
INSERT INTO roles(name) VALUES('USER');
INSERT INTO roles(name) VALUES('ADMIN');
```
sorgularını çalıştırınız.


