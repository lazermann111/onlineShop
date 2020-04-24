# onlineShop
Руководство по работе с программным комплексом «onlineShop»:

1.	Программный комплекс незакончен. В нем только частично реализованы:
1.1. функции работы с базой клиентов:
- регистрация нового клиента;
- вход зарегистрированного пользователя;
- изменение данных зарегистрированного пользователя (только в отношении себя);
- удаление зарегистрированного пользователя (только в отношении себя);
- изменение остатка на счету зарегистрированного пользователя (только в отношении себя);
- получение списка всех пользователей.
1.2. функции работы с базой товаров:
- получение списка всех категорий товаров;
- получение списка всех товаров;
- получение списка товаров выбранной категории;
- получение списка товаров по имени (ищется подстрока в имени);
- получение списка товаров в заданном ценовом диапазоне;
- добавление товара в базу;
- изменение информации о товаре в базе;
- удаление товара из базы.
Последние три функции нужно было бы оставить только для пользователя с правами администратора, но сейчас работает для любого зарегистрированного пользователя.
Совсем не реализованы функции подачи заказа на покупку, проведения покупки и оплаты товаров.

2.	При запуске программы выводится меню работы с информацией о пользователях (Login Menu), после которого предлагается ввести номер выбранной опции.

3.	Хранение данных о пользователях возможно как в оперативной памяти компьютера в течение выполнения программы, так и в отдельном текстовом файле.
В первом случае данные зарегистрированных пользователей утрачиваются после прекращения работы программы. Во втором случае они сохраняются и могут быть многократно вызваны при последующих запусках программы.
Выбор варианта записи информации о пользователе происходит при его регистрации:
- во входном меню необходимо выбрать опцию «Register»:
1. Login
2. Register
9. Back
0. Exit
- после этого вызовется меню выбора варианта записи информации о пользователе, в котором необходимо выбрать нужную опцию:
Select the option of storing user information:
0 - storage in RAM
1 - storage in file
То же касается вхождения зарегистрированного пользователя в программу. После выбора во входном меню опции «Login» вызовется вышеуказанное меню, в котором нужно выбрать то хранилище данных, в котором записан соответствующий пользователь.
При валидации имени и пароля пользователя учитывается регистр шрифта.

4.	Хранение данных о товарах осуществляется только в оперативной памяти компьютера. Проверить результаты операций с товарами можно путем вызова списка всех товаров.
