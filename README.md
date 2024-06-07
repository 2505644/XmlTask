# Подготовка 
Создать таблицу article в любой доступной БД:
CREATE TABLE article
(
id_art int,
name varchar(255),
code varchar(255),
username varchar(255),
guid varchar(255)
);                                                                                                                                                                 Наполнить ее тестовыми данными, не более 10 строк.

# Задача  1
На основании запроса:
SELECT id_art, name, code, username, guid FROM article
WHERE rownum < 10;

Сформировать XML вида :

<articles>
    <articles id_art="104880" name="Батон нарезной в/с 0.4кг" code="1010050114" username="WHS" guid="6992B998083711DC87F900093D12899D/">
    ...
</articles>

# Задача 2
 Написать XSLT преобразование, которое приведет xml, полученный в предыдущей задаче к виду:

<articles>
    <article>
        <id_art>104880</id_art>
        <name>Батон нарезной в/с 0.4кг</name>
        <code>1010050114</code>
        <username>WHS</username>
        <guid>6992B998083711DC87F900093D12899D</guid>
    </article>
    ...
</articles>

# Задача 3
Написать программу, которая из xml, полученного из предыдущей задачи после преобразования, сформирует CSV файл вида
ID_ART,NAME,CODE,USERNAME,GUID
104880,Батон нарезной в/с 0.4кг,1010050114,WHS,6992B998083711DC87F900093D12899D
