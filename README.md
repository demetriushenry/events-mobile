# events-mobile
Teste de app com consumo de API REST.

Tecnologias utilizadas:
* Kotlin language
* Kotlin coroutines
* Google Material Design
* Retrofit
* okHttpInteceptor
* lifecycleLivedata
* Koin
* Navigation
* Picasso
* MVVM architecture

# executar app
* Iniciar o AVD (simulador) ou conectar dispositivo android previamente configurado (USB DEBUG on)
* No Android Studio:
  * Clique no botão run
* No terminal utilizando Gradle e ADB:
  * abra o terminal interno do Android Studio e execute os seguintes comandos:
  ```bash
  gradlew installDebug
  ```
  * instale o apk gerado no AVD (simulador) ou dispositivo android conectado
  ```bash
  adb install your_app.apk
  ```
  * aguarde a inicilização do aplicativo
