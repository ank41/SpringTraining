# REST-сервис для проведения финансовых операций клиентов

В файле application.yml изменить поля
```
    url: jdbc:postgresql://localhost:5432/leaderIT
    username: postgres
    password: "123123"
 ```  
После первого запуска автоматически создается 4 таблица ("clients", "accounts","orders", "transactions").

Таблица "clients" содержит следующие строки
```
id | name | surname | secondname | codeword|

1  Иван  Иванов,  Иванович    1b20179e145cd23bc7d76fca8e858951e00c9d6abcfe46989a0f26b23fd77c8d07726e8107eb94e1   //изначально 123123

2  Петр  Иванов   Иванович    1b20179e145cd23bc7d76fca8e858951e00c9d6abcfe46989a0f26b23fd77c8d07726e8107eb94e1   //изначально 123123
```
Таблица "accounts" содержит следующие строки
```
id client_id номер   баланс     тип счета   Дата открытия  срок дейтсвия
             счета
             
1,    1,     4352,   111454,    базовый,    2022-11-30,     2022-11-30

2,    2,     4353,    100,      базовый,    2022-11-30,     2022-11-30

3,    1,     4351,    100,      базовый,    2022-11-30,     2022-11-30
```
Остальные таблицы пустые.

Чтобы получить информацию о всех клиентах необходимо отправить get запрос на http://localhost:8080/clients

информация о клиенте по его идентификатору: get запросна http://localhost:8080/clients/{id} - вместо {id} - нужный идентификатор

информация о счетах клиентов: get запрос на http://localhost:8080/accounts

информация о транзакциях по счету: get http://localhost:8080/transactions/{id}

информация о ордерах по счету: get http://localhost:8080/orders/account/{id}

Чтобы создать кассовый ордер на депозит/снятие: отправить post запрос http://localhost:8080/operations/operationWithAccount в json с форматом 
```
{
    "type": "deposit",         "deposit" / "withdrawal"
    "sum": 103,               сумма пополнения/снятия
    "accountNumber": "4352", номер счета
    "codeword": "123123"
}
```

Чтобы создать транзакцию на перевод; post на http://localhost:8080/operations/transfer
```
{
    "type": "Transfer",         
    "sum": 103,               
    "accountNumber": "4352", номер счета отправителя
    "accountNumberTo": "4353", номер счета получателя
    "codeword": "123123"
}
```
