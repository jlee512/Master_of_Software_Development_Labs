-- Exercise 4 SQL Queries

-- 1. Find the number of employees who earn less than the average salary.
-- Average Salary Check
SELECT AVG(e.Salary) FROM Employee as e;

-- Query Solution
SELECT COUNT(e.EmpID) FROM Employee as e WHERE e.Salary < (SELECT AVG(e.Salary) FROM Employee as e);

-- 2. Find the managers who are older than average age of all employees. Your
-- query should return the name and age of those managers
-- Average Age Check/Manager Name Checks
SELECT AVG(e.Age) FROM Employee as e;
SELECT e.EmpName FROM Employee as e, Manager as m WHERE e.EmpID == m.EmpID;

-- Query Solution
SELECT e.EmpName, e.Age FROM Employee as e, Manager as m WHERE m.EmpID == e.EmpID AND e.Age > (SELECT AVG(e.Age) FROM Employee as e);

-- 3. Find the manager who manages the department with the largest budget.
SELECT e.EmpName AS 'Manager', d.DeptName, d.Budget FROM Employee AS e, Manager AS m, Department as d WHERE e.EmpID == m.EmpID AND d.DeptID == m.DeptID AND d.Budget == (SELECT MAX(d.Budget) FROM Department AS d);

-- 4. Calculate the average salary of the employees who work in the departments
-- with more than 5 full-time young employees (Age <30). Round Average
-- Salary to 2 decimal places.
SELECT d.DeptName AS 'Department', ROUND(AVG(e.Salary),2) AS 'Average Salary of the Department' FROM Department as d, Employee as e, Work as w WHERE e.EmpID == w.EmpID AND d.DeptID == w.DeptID AND d.DeptID IN (SELECT d.DeptID FROM Employee as e, Work as w, Department as d WHERE e.EmpID == w.EmpID AND w.DeptID == d.DeptID AND w.Percent_Time == 100 AND e.Age < 30 GROUP BY d.DeptName HAVING COUNT(e.EmpID) > 5) GROUP BY d.DeptName;

-- 5. List detailed information of departments, including the name of the
-- department, budget, the manager, start date of the manager, and the end date
-- of the manager. If the manager position of the department is vacant, only
-- show the name of department and budget.


-- 6. Find employees whose last name starts with ‘W’ and work full-time for the
-- Operations department. Show employees’ name and department. Note: there
-- are different ways of retrieving results. Use INNER JOIN for this question.