
#Docker Compose + Consul + Spring Boot + FeignClient

Пример того, как поднять локальный development environment с использованием 
* Docker Compose
* Consul
* Make 
* Spring Boot
* PostgreSQL 
* Browserless

###1. Настройка сервисов в Docker Compose
* POSTGRES_PASSWORD – пароль к базе данных пользователя по умолчанию postgres, 
* POSTGRES_DB – автоматически создаваемая база данных в контейнере

стопнуть все контейнеры <b>опционально</b>
 ``` docker stop $(docker ps -q) ```
Если есть  запущенный postgress то стапаем 
``` sudo service postgresql stop ```
затем мы можем стартонуть 
``` sudo service postgresql start ```
стартонуть докер композ
``` docker-compose up -d ```


#### освободить порты 
кто юзает порт 

``` lsof -i :8500 ```

``` sudo kill -9 17257 ```, где 17257 - PID 

После запуска всех контейнеров можно перейти по адресу в браузере http://localhost:8500 – должен открыться веб-клиент Consul’a

#### сделать файл запускаемым
``` chmod +x register-services.sh ```

``` chmod +x register-variables.sh ```

#### Регистрация сервисов в Consul’e 
можно провести отправив несколько post-запросов на адрес localhost:8500/v1/agent/service/register, например, используя curl.
все скрипты собраны в файле register-services.sh

###Make файл

``` make -f [FLINAME]``` общий формат работы с make файлами 

```make debug ``` запусти среду без спринг проекта, спринг стартует в редиме дебага (в среде разработки )

```make down ``` стопнуть все 

```make up```   запустит всю среду.

make up >LOG_FILE_NAME


## Что используется 
### Feign
декларативный HTTP -клиент, разработанный Netflix.
@FeignClient(name = “Джон Уик”) и указываем имя того сервиса, который нам нужен
https://www.codeflow.site/ru/article/intro-to-feign

###browserless 
is a web-service that allows for remote clients to connect, drive, and execute headless work; all inside of docker. It offers first-class integrations for puppeteer, selenium's webdriver, and a slew of handy REST APIs for doing more common work.

TODO: база будет пересоздаваться ? 
