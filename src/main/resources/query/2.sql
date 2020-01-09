/*1. Поиск наименее прибыльного маршрута за текущий квартал*/

SELECT r.id_routes, r.departure, r.arrival, t.profit
FROM (
         SELECT MIN(profit) AS max_number
         FROM
             (
                 SELECT id_routes, SUM(price) AS profit
                 FROM ticket
                 GROUP BY id_routes
             ) AS xz
     ) AS max_sub
         JOIN
     (
         SELECT id_routes, SUM(price) AS profit
         FROM ticket
         GROUP BY id_routes
     ) AS t ON (max_sub.max_number = t.profit)
         JOIN
     routes r ON (r.id_routes = t.id_routes) WHERE QUARTER(CURTIME());