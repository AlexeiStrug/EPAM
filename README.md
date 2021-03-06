<b>TASK FROM EPAM:</b><br>
Система <b>Автобаза</b>. <b>Диспетчер</b> распределяет <b>Заявки</b> на <b>Рейсы</b> между <b>Водителями</b>, за каждым из которых закреплен свой <b>Автомобиль</b>. На <b>Рейс</b> может быть назначен <b>Автомобиль</b>, находящийся в исправном состоянии и характеристики которого соответствуют <b>Заявке</b>. <b>Водитель</b> делает отметку о выполнении <b>Рейса</b> и состоянии <b>Автомобиля</b>.
<br>
<br>
<b> ADDITIONAL TASKS:</b>
<ol>
<li><b>Adjust logger configuration to use any of custom appenders (JDBC, JMS, SMTP etc)</b> - Сконфиругировать и показать пример использования 1-2х дополнительных аппендеров помимо Console и File (и его разновоидностей).<br>
<li><b>Integrate Spring convertes in web layer (DB entities <-> web models conversion)</b> -https://docs.spring.io/spring/docs/3.0.0.RC2/reference/html/ch05s05.html (для Spring4 возможно надо найти другую документацию/реализацию). <br></li>
<li><b>DAO layer refactoring. Extract parent class with common functionality (parametrization+method override)</b>	- Оптимизировать код в DAO слое , чтобы минимизировать кол-во повторяющегося кода (getById, deleteById, getAll etc.) Можно использовать наследование, параметризацию, аннотации (что-то еще?). <br></li>
<li><b>Auth result caching</b> - Kеширование результатов проверки Basic авторизации(чтобы избежать лишних запросов в БД) (Optional usage of REDIS, MEMCACHED). <br></li>
<li><b>Support i18n in "web" layer</b>	- Разработать поддержку локалей (только язык). Возможность переключения локали и/или передачу параметра в каждом запросе (header или url параметр). в зависимости от переданного параметра данные должны отдаваться на соответствующем языке. обеспечить поддержку "языка по умолчанию", если он не определен в запросе. <br></li>
<li><b>Search request caching (maps, collections)</b> - Реализовать "кеширование" результатов поиска в БД. При повторении запросов в течении определенного времени БД не должна быть задействована. При первом выполнении запроса в БД результат необходимо сохранить в памяти (java.util.Map). <br></li>
<li><b>Persistent cache created on application shutdown (serialization, IO)</b> - Предусмотреть возможность сохранения на файловую систему и восстановления в памяти кеша (см. пред. пункт) при остановке приложения. <br></li>
<li><b>Create load balancer and run multiple app instances</b> - Изменить логику класса StartJetty чтобы он запускал сразу несколько копий приложения (на разных портах). Написать свой 'load balancer', который будет принимать запросы от клиентов и прокидывать их на одну из запущенных копий приложения. Таким образом показать возможность масштабирования приложения "по горизонтали". Пример реализации можно попробовать найти по запросу в google "Java HTTP Proxy(Load Balancer) Server". <br></li>
<li><b>Performance testing/analysis</b> - "С помощью Apache JMeter  создать сценарии нагрузочного тестирования. провести тестирование с подключенным jvisual vm. Проанализировать результаты и  сделать выводы:
<ul>
<li>о поведении GC;</li>
<li>использовании cpu/memory;</li>
<li>etc.</li>
<ul>
</li>
</ol>
