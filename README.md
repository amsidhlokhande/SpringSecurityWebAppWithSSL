This is project which has springboot and https using SSL jks.
In order to create self signed certificate following command is used

keytool -genkey -alias sslexample -storetype JKS -keyalg RSA -keysize 4096 -validity 365 -keystore https-example.jks
keytool -importkeystore -srckeystore https-example.jks -destkeystore https-example-pkcs12.jks -deststoretype pkcs12

keytool -list -keystore https-example.jks






keytool -genkey -alias https-example -storetype JKS -keyalg RSA -keysize 4096 -validity 365 -keysote https-example.jks
keytool -importkeystore -srckeystore https-example.jks -destkeystore https-example-pkcs12.jks -deststoretype pkcs12


keytool -genkey -alias sslexample -storetype JKS -keyalg RSA -keysize 4096 -validity 365 -keystore https-example.jks
keytool -importkeystore -srckeystore https-example.jks -destkeystore https-example-pkcs12.jks -deststoretype pkcs12





keytool -genkeypair -alias baeldung -keyalg RSA -keysize 2048 -keystore baeldung.jks -validity 3650
keytool -importkeystore -srckeystore baeldung.jks -destkeystore baeldung.p12 -deststoretype pkcs12


For direct generating pkcs12 file

keytool -genkeypair -alias https-example -keyalg RSA -keysize 4096 -storetype PKCS12 -keystore https-example-pkcs12.p12 -validity 3650


Create .cert from jks
keytool -export -alias key -file server.cert -keystore keystore.jks
OR
keytool -export -alias key -file server.cert -keystore keystore.p12


Create .key file
openssl pkcs12 -in keystore.p12  -nodes -nocerts -out server.key

keytool -export -alias key -storepass Pass@123 -file server.cer -keystore keystore.jks



Extract .key and .crt files from JKS file
keytool -export -alias key -file server.der -keystore keystore.jks

Convert the .der file to unencrypted PEM (crt file)

openssl x509 -inform der -in server.der -out server.crt

openssl pkcs12 -in keystore.p12 -nodes -nocerts -out server.key






1) Its optional stept : First We will create the PCK12( Multi language support) certificate using keytool of java
 example -> keytool -genkeypair -alias baeldung -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore baeldung.p12 -validity 3650
 keytool -genkeypair -alias key -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650
 
 2) For generating jks type certificate use following command
 
 keytool -genkeypair -alias key -keyalg RSA -keysize 2048 -keystore keystore.jks -validity 3650
 keytool -genkey -alias key -keyalg RSA -validity 3650 -keysize 2048 -keypass Pass@123 -storepass Pass@123 -keystore keystore.jks 
 
 3) For creating server certificate cer file
     keytool -export -alias key -storepass Pass@123 -file server.cer -keystore keystore.jks
 4) To add the server certificate to the truststore file, cacerts.jks/truststore.jks, run keytool from the directory where you created the keystore and server certificate.
    create truststore file 
	keytool -import -v -trustcacerts -alias key -file server.cer -keystore truststore.jks -keypass Pass@123 -storepass Pass@123
 
 
 
 
Create .cert from jks
keytool -export -alias key -file server.cert -keystore keystore.jks
OR
keytool -export -alias key -file server.cert -keystore keystore.p12



Create .key file
openssl pkcs12 -in keystore.p12  -nodes -nocerts -out server.key
