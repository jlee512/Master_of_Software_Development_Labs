-- Exercise 1 SQL queries

-- 1) Full details of all employees
SELECT * FROM Employee;

-- 2) Find employees who are older than 40 and have salary more than 20000. The
-- query result should display “EmpName”,“Age”, and “Salary” columns. Sort
-- the results in descending order by Age and then in ascending order by Salary

SELECT EmpName, Age, Salary FROM Employee WHERE Age > 40 AND Salary > 20000 ORDER BY AGE DESC, Salary ASC;

-- 3) Produce a report shown a list of monthly salaries of all employees. Round
-- Monthly Salary to 2 decimal places. The format of your report should be the
-- same as the table below.
-- -Note: Arithmetic Operators will be covered fully later. Use an arithmetic
-- division operator in the SELECT clause and give it a try.
--                                                      -Hint: use the Round function: ROUND (column_name, decimals)

SELECT EmpName AS 'Name', ROUND(Salary/12, 2) AS 'Monthly Salary' FROM Employee;

-- 4) The company wants to give year-end bonus to its employees. The bonus each
-- employee will get is the 5% of employee salary. Write a query to calculate the
-- bonus each employee will receive at the end of this year. This newly
-- calculated column should be named as “Bonus”. The query result should
-- display “EmpID”, “EmpName”, “Salary”, and “Bonus” columns.
-- -Note: Arithmetic Operators will be covered in Week 8. Use an arithmetic
-- multiplication operator in the SELECT clause and give it a try.

SELECT EmpID, EmpName, Salary, ROUND(Salary*0.05, 2) AS 'Bonus' FROM Employee;

-- 5) Write a query to find the “EmployeeID” of employees who work full-time
-- (100%) in a department. Your query should display “EmpID” and “DeptID”
-- columns.

SELECT EmpID, DeptID FROM Work WHERE Percent_Time == 100;

-- 6) List the department located in Wellington or Auckland.
-- Note: Logical Operators will be covered in Week 8. Use “OR” operator in the
-- WHERE clause and give it a try.

SELECT DeptName, DeptCity FROM Department WHERE DeptCity == 'Wellington' OR DeptCity == 'Auckland';
