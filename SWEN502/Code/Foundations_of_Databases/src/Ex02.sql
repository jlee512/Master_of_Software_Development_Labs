-- Exercise 2 SQL queries

-- 1) Find all employees who are older than twice your age. Your query result
-- should show all the columns from the Employee table.
SELECT * FROM Employee WHERE Age > 52;

-- 2) Find employees whose last name ends with ‘son’.
SELECT * FROM Employee WHERE EmpName LIKE '%son';

-- 3) Find employees whose age is missing.
SELECT * FROM Employee WHERE Age IS NULL;

-- 4) Find managers who manage departments with a budget more than $750,000.
-- Sort the results by “Budget” in descending order
SELECT Manager.EmpID || '-' || EmpName AS 'Manager ID and Name', DeptName, Budget FROM Manager JOIN Department ON Manager.DeptID == Department.DeptID JOIN Employee ON Manager.EmpID = Employee.EmpID;

-- 5) Donald King, manager of the Marketing department, would like to know
-- which employees in his department are younger than 40 OR earn less than
-- $100,000. Your query should show all the columns from Employee table.
SELECT Employee.* FROM Employee JOIN Work ON Employee.EmpID == Work.EmpID JOIN Department ON Work.DeptID == Department.DeptID WHERE DeptName == 'Marketing' AND (Age < 40 OR Salary < 100000);

-- 6) Find employees who work half-time (50%) in the Hardware department or the
-- Operations department with more than $25,000 salary. Your query should
-- display “EmpID”, “EmpName”, “Salary” and “DeptName” columns. There
-- are multiple ways to show the query results. Try different SQL commands,
-- including OR, IN, and JOIN.
SELECT Employee.EmpID, EmpName, Salary, DeptName FROM Employee JOIN Work ON Employee.EmpID == Work.EmpID JOIN Department ON Work.DeptID == Department.DeptID WHERE (DeptName == 'Hardware' OR DeptName == 'Operations') AND Salary > 25000;

-- 7) The hardware department wants to give its employees 2% bonus because they
-- finished the project early. This 2% bonus is based on the percentage of time an
-- employee works for the Hardware department. For instance, if an employee
-- works 50% for the Hardware department and earns $20,000, this extra bonus
-- will be calculated on $10,000 because only 50% of total salary is paid by the
-- Hardware department.

-- Write a query to calculate the 2% bonus for all the employees’ of Hardware
-- department. This newly calculated column should be named as “Bonus from
-- Hardware Dept.”. Provide a report the same as the table below.
SELECT EmpName AS 'Name', Salary, Percent_Time, ROUND(Salary * Work.Percent_Time / 100 * 0.02, 2) AS 'Bonus from Hardware Dept.' FROM Employee JOIN Work ON Employee.EmpID == Work.EmpID JOIN Department ON Work.DeptID == Department.DeptID WHERE DeptName == 'Hardware';

