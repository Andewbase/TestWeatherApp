

![](https://github.com/Andewbase/TestWeatherApp/blob/master/weather_record.gif)

Тестовое задание

Задача: Создать приложение с 2 экранами.

Приложение "Погода"

На 1 экране(отображение) должно быть:

Возможность выбрать город (3-4 города)
Возможность выбрать сезон года
В зависимости от п.1 и п.2 - отображение средней температуры за сезон в городе
В зависимости от п.1 - отображать тип города (малый, средний, большой)
На 2 экране(настройки) должно быть:

Управление списком городов (город, тип)
Управление температурой по месяцам.
Приложение на первом экране должно отображать информацию, введенную пользователем на втором экране. Например, на втором экране вводим : город "Бишкек", тип "средний", температура июнь "23" июль "28" август "25" на первом экране в списке городов, должен отобразится Бишкек, тип "средний" и температура за сезон "лето" "25,3"(среднее арифметическое 3х месяцев).

Использовать паттерны: "Lazy singleton": На усмотрение "Factory": Получать тип города в зависимости от его названия "Decorator": При запросе средней температуры за сезон в городе - должна быть возможность получить строку для логгирования "Observer": Дополнительно выводить сообщение, о температуре, через Snackbar "Strategy": В зависимости от стратегии - выводить температуру в необходимом формате (градус Цельсия, градус Фаренгейта, Кельвин)

Условия: Без использования сети БД: SQLite Мы ожидаем выполнения задания без применения сторонних библиотек (Кроме Android Support Library и Android Architecture Components) Результат выложить на Github.com

Описание:
TODO коментариями по проекту отмечено выполнение патернов.
"Lazy singleton": https://github.com/Andewbase/TestWeatherApp/blob/master/app/src/main/java/com/example/testweather/di/AppModule.kt#L16

"Strategy": https://github.com/Andewbase/TestWeatherApp/tree/master/app/src/main/java/com/example/testweather/util/tempstrategy

"Factory": https://github.com/Andewbase/TestWeatherApp/blob/master/app/src/main/java/com/example/testweather/screen/main/MainFragment.kt#L81
https://github.com/Andewbase/TestWeatherApp/tree/master/app/src/main/java/com/example/testweather/util/factory

"Decorator":https://github.com/Andewbase/TestWeatherApp/blob/master/app/src/main/java/com/example/testweather/screen/main/MainFragment.kt#L82
https://github.com/Andewbase/TestWeatherApp/tree/master/app/src/main/java/com/example/testweather/util/decorator

"Observer": https://github.com/Andewbase/TestWeatherApp/blob/master/app/src/main/java/com/example/testweather/screen/main/MainFragment.kt#L75
