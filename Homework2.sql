--2.1
--Select all records from the Employee table.
SELECT * FROM Employee;

--Select all records from the Employee table where last name is King.
SELECT * FROM Employee WHERE LASTNAME = 'King';

--Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM Employee WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;

--2.2
--Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM Album ORDER BY TITLE DESC;

--Select first name from Customer and sort result set in ascending order by city
SELECT FIRSTNAME FROM Customer ORDER BY CITY ASC;

--2.3
--Insert two new records into Genre table
INSERT INTO Genre VALUES(26, 'Classic Rock');
INSERT INTO Genre VALUES(27, 'House');

--Insert two new records into Employee table
INSERT INTO Employee 
VALUES(9, 'Macaulay', 'Zach', 'IT Staff', 6, TO_DATE('1994-07-06 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2018-04-23 00:00:00',
'yyyy-mm-dd hh24:mi:ss'), '8536 Bentwood Dr', 'Charleston', 'SC', 'U.S.', '29406', '+1 (843)-513-4644', null, 'zmacaulay@gmail.com');

INSERT INTO Employee(EMPLOYEEID, LASTNAME, FIRSTNAME) VALUES(10, 'Else', 'Someone');

--Insert two new records into Customer table
INSERT INTO Customer(CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL) VALUES(60, 'New', 'Customer1', 'iAmCustomer@email.com');
INSERT INTO Customer(CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL) VALUES(61, 'New', 'Customer2', 'iAmAlsoACustomer@email.com');

--2.4
--Update Aaron Mitchell in Customer table to Robert Walter
UPDATE Customer
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE CUSTOMERID = 32;

--Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”	
UPDATE Artist
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';

--2.5
--Select all invoices with a billing address like “T%” 
SELECT * FROM Invoice WHERE BILLINGADDRESS LIKE 'T%';

--2.6
--Select all invoices that have a total between 15 and 50
SELECT * FROM Invoice WHERE TOTAL BETWEEN 15 AND 50;

--Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM Employee WHERE HIREDATE BETWEEN TO_DATE('2003-06-01 00:00:00','yyyy-mm-dd hh24:mi:ss')
AND TO_DATE('2004-03-01 00:00:00','yyyy-mm-dd hh24:mi:ss');

--2.7
--Delete a record in Customer table where the name is Robert Walter 
--(There may be constraints that rely on this, find out how to resolve them).
--run from here next time
DELETE FROM Customer
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

--3.1
--Create a function that returns the current time.
CREATE OR REPLACE FUNCTION CURRENT_SYSTEM_TIME
RETURN TIMESTAMP
IS SYSTEM_TIME TIMESTAMP;
BEGIN
    SYSTEM_TIME :=  CURRENT_TIMESTAMP;
    RETURN SYSTEM_TIME;
END;

--Create a function that returns the length of name in MEDIATYPE table
CREATE OR REPLACE FUNCTION MEDIATYPE_NAME_LENGTH
RETURN INTEGER
IS THIS_MEDIATYPE INTEGER;
BEGIN
    SELECT MAX(LENGTH(NAME)) INTO THIS_MEDIATYPE FROM MEDIATYPE;
    RETURN THIS_MEDIATYPE;
END;

--3.2
--Create a function that returns the average total of all invoices 
CREATE OR REPLACE FUNCTION AVERAGE_INVOICE
RETURN FLOAT
IS THIS_AVG FLOAT;
BEGIN
    SELECT AVG(TOTAL) INTO THIS_AVG FROM INVOICE;
    RETURN THIS_AVG;
END;

--Create a function that returns the most expensive track
CREATE OR REPLACE FUNCTION MOST_EXPENSIVE_TRACK
RETURN NUMBER
IS THIS_PRICE NUMBER;
BEGIN
    SELECT MAX(UNITPRICE) INTO THIS_PRICE FROM TRACK;
    RETURN THIS_PRICE;
END;

--3.3
--Create a function that returns the average price of invoiceline items in the invoiceline table
CREATE OR REPLACE FUNCTION INVOICELINE_AVERAGE
RETURN FLOAT
IS ITEM_AVG FLOAT;
BEGIN
    SELECT AVG(UNITPRICE) INTO ITEM_AVG FROM INVOICELINE;
    RETURN ITEM_AVG;
END;

--4.1
--Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE FIRSTNAME_LASTNAME_SELECT
AS 
    EMPLOYEE_FIRSTNAME VARCHAR2(30); 
    EMPLOYEE_LASTNAME VARCHAR2(30);
    CURSOR EMP_FIRSTLAST IS (SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE);
        
BEGIN
    OPEN EMP_FIRSTLAST;
    LOOP
        FETCH EMP_FIRSTLAST INTO EMPLOYEE_FIRSTNAME, EMPLOYEE_LASTNAME;
        DBMS_OUTPUT.PUT_LINE(EMPLOYEE_FIRSTNAME || ' ' || EMPLOYEE_LASTNAME);
        EXIT WHEN EMP_FIRSTLAST%NOTFOUND;
    END LOOP;
END;

BEGIN
    FIRSTNAME_LASTNAME_SELECT();
END;

--4.2
--Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE UPDATE_INFO (EMPLOYEE_ID IN NUMBER)
AS
BEGIN
    UPDATE EMPLOYEE SET TITLE = 'Does something' WHERE EMPLOYEE_ID = EMPLOYEEID;
END;

BEGIN
    UPDATE_INFO(10);
END;

--Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE MANAGER_RETURN(EMPLOYEE_ID IN NUMBER)
AS
    MANAGER_ID NUMBER;
BEGIN
    SELECT A.EMPLOYEEID, A.REPORTSTO INTO MANAGER_ID
    FROM EMPLOYEE A, EMPLOYEE B
    WHERE A.REPORTSTO = B.REPORTSTO
    AND A.EMPLOYEEID = B.EMPLOYEEID; 
END;

BEGIN
    MANAGER_RETURN(8);
END

--4.3
--Create a stored procedure that returns the name and company of a customer.

--6.1
--Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE OR REPLACE TRIGGER NEW_EMPLOYEE_INSERT
AFTER INSERT ON EMPLOYEE
BEGIN
    NULL;
END;

--Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE OR REPLACE TRIGGER NEW_ALBUM_INSERT
AFTER UPDATE ON ALBUM
BEGIN
    NULL;
END;

--Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE OR REPLACE TRIGGER CUSTOMER_DELETED
AFTER DELETE ON CUSTOMER
BEGIN
    NULL;
END;

--7.1
--Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.CUSTOMERID
FROM CUSTOMER
INNER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

--7.2
--Create an outer join that joins the customer and invoice table, specifying the CustomerId, 
--firstname, lastname, invoiceId, and total.
SELECT CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID, INVOICE.TOTAL
FROM CUSTOMER
FULL OUTER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID
ORDER BY CUSTOMER.CUSTOMERID;

--7.3
--Create a right join that joins album and artist specifying artist name and title
SELECT ARTIST.NAME, ALBUM.TITLE
FROM ARTIST
RIGHT JOIN ALBUM ON ARTIST.ARTISTID = ALBUM.ARTISTID;

--7.4
--Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT ARTIST.NAME, ALBUM.TITLE
FROM ARTIST
CROSS JOIN ALBUM
ORDER BY ARTIST.NAME ASC;

--7.5
--Perform a self-join on the employee table, joining on the reportsto column.
SELECT A.EMPLOYEEID, A.REPORTSTO
FROM EMPLOYEE A, EMPLOYEE B
WHERE A.REPORTSTO = B.REPORTSTO
AND A.EMPLOYEEID = B.EMPLOYEEID;