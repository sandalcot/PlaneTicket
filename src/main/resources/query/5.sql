/*5. Поиск пассажиров, купивших билеты в указанный пункт назначения, на сумму, 
больше указанной в запросе, в указанный период времени
*/

SELECT id_passenger, price, date
FROM ticket
WHERE id_routes = 3
  AND price > 500
  AND DATE_ADD(CURDATE(), INTERVAL -30 DAY)