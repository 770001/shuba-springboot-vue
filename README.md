# shuba-springboot-vue CRM for buyer

## [shuba.herokuapp.com](https://shuba.herokuapp.com)

## Собрано по туториалу [https://github.com/jonashackt/spring-boot-vuejs](https://github.com/jonashackt/spring-boot-vuejs)

### Шаги

на Windows 

Ставим [chocolatey](https://chocolatey.org/) и выполняем `choco install npm`, затем `npm install -g @vue/cli`

Общая структура проекта: модуль frontend, модуль backend. В корне проекта и в модулях - по pom.xml

```text
shuba-springboot-vue
│
├─┬ backend     → модуль backend со `Spring Boot`
│ ├── src
│ └── pom.xml   свой pom.xml
│
├─┬ frontend    → модуль frontend с `Vue.js`
│ ├── src
│ └── pom.xml   свой pom.xml
│
└── pom.xml     → parent pom.xml - управляет модулями
```



