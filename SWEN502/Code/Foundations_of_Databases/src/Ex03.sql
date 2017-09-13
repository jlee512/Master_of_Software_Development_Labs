-- Exercise 3 SQL Queries

-- 1. Find the total number of departments this company has. Your query should
-- return the calculated field and name it as “Total Number of Departments”
SELECT COUNT(DISTINCT DeptName) AS 'Total Number of Departments' FROM Department;

-- 2. List the average salary of the employees in each department. Round Average
-- Salary to 2 decimal places (Hint: use the Round function)
SELECT d.DeptName, ROUND(AVG(e.Salary), 2) AS 'Average Salary' FROM Department AS d, Employee AS e, Work AS w WHERE e.EmpID == w.EmpID AND w.DeptID == d.DeptID GROUP BY d.DeptName;

-- 3. List the departments with less than 5 part-time employees (i.e., Percent_Time
-- < 100%).
SELECT d.DeptName, COUNT(e.EmpID) AS 'Number of Part-Time Employees' FROM Department AS d, Employee as e, Work as w WHERE e.EmpID = w.EmpID AND w.DeptID = d.DeptID AND w.Percent_Time < 100 GROUP BY d.DeptName;

-- 4. Find the minimum, average, and maximum age of the employees for each
-- department.
SELECT d.DeptName, MIN(e.Age) AS 'MinAge', ROUND(AVG(e.Age), 2) AS 'AvgAge', MAX(e.Age) AS 'MaxAge' FROM Department AS d, Employee as e, Work as w WHERE e.EmpID == w.EmpID AND w.DeptID = d.DeptID GROUP BY d.DeptName;

-- 5. List the cities that have departments located in them. Eliminate duplicate cities
-- in your query results.
SELECT DISTINCT d.DeptCity FROM Department as d;


-- 6. Find employees who have been department managers.
SELECT e.EmpName AS 'Employee Name', d.DeptName AS 'Department Managed', m.FromDate AS 'Start Date', m.ToDate AS 'End Date' FROM Employee AS e, Department AS d, Manager as m WHERE e.EmpID == m.EmpID AND d.DeptID == m.DeptID;


-- 7. Find employees who were hired in 2014. Show their names, the dates of hire,
-- and the number of days since they join the company.
SELECT e.EmpName AS 'Employee Name', e.HireDate AS 'Hired Date', JULIANDAY(DATE('now')) - JULIANDAY(e.HireDate) AS 'Tenure (Days)' FROM Employee AS e;