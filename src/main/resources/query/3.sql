/*3. Поиск пассажира, который совершал более 10 перелетов в текущем квартале*/

SELECT t.id_passenger, p.name, p.surname, COUNT(t.id_passenger) as count
FROM ticket t
         INNER JOIN passenger p
where p.id_passenger = t.id_passenger
GROUP BY t.id_passenger
HAVING COUNT(t.id_passenger) > 10
   and QUARTER(CURTIME());