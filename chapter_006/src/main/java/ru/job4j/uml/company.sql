SELECT p.name, c.name FROM 'person' as p INNER JOIN 'company' as c ON p.company_id = c.id WHERE NOT c.id=5;
SELECT p.name, c.name FROM 'person' as p INNER JOIN 'company' as c ON p.company_id = c.id;
 
SELECT c.name as name, count(company_id) as num FROM 'company' as c INNER JOIN 'person' as p ON c.id = p.company_id GROUP BY company_id ORDER BY num DESC LIMIT 1;
 
CREATE VIEW temp_table AS  SELECT c.name as name,  count(company_id) as num FROM 'company' as c INNER JOIN 'person' as p ON c.id = p.company_id GROUP BY company_id;
SELECT name, MAX(num) FROM temp_table;


