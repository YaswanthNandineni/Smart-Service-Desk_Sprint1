SELECT USER FROM dual;

-- USERS Table
CREATE TABLE Users (
    user_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    email VARCHAR2(100),
    role VARCHAR2(50)
);

-- AGENTS Table
CREATE TABLE Agents (
    agent_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    department VARCHAR2(100)
);

-- CATEGORIES Table
CREATE TABLE Categories (
    category_id NUMBER PRIMARY KEY,
    category_name VARCHAR2(100)
);

-- TICKETS Table
CREATE TABLE Tickets (
    ticket_id NUMBER PRIMARY KEY,
    title VARCHAR2(200),
    description CLOB,
    status VARCHAR2(50),
    created_at TIMESTAMP DEFAULT SYSTIMESTAMP,
    user_id NUMBER,
    agent_id NUMBER,
    category_id NUMBER,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (agent_id) REFERENCES Agents(agent_id),
    FOREIGN KEY (category_id) REFERENCES Categories(category_id)
);


-- SEQUENCE for Ticket ID auto-generation
CREATE SEQUENCE ticket_seq START WITH 1 INCREMENT BY 1;

-- Sample Users
INSERT INTO Users VALUES (101, 'Karthik R', 'karthik@gmail.com', 'Premium Customer');
INSERT INTO Users VALUES (102, 'Yash', 'yaswanth@gmail.com', 'Customer');

-- Sample Agents
INSERT INTO Agents VALUES (201, 'Vikas', 'Tech Support');
INSERT INTO Agents VALUES (202, 'Anil Kumar', 'Hardware');

-- Sample Categories
INSERT INTO Categories VALUES (301, 'Application Bug');
INSERT INTO Categories VALUES (302, 'Hardware Issue');

-- Sample Ticket using ticket_seq
INSERT INTO Tickets (ticket_id, title, description, status, created_at, user_id, agent_id, category_id)
VALUES (ticket_seq.NEXTVAL, 'Login Issue', 'Cannot login to dashboard', 'Open', SYSTIMESTAMP, 101, 201, 301);

-- Ticket 3
INSERT INTO Tickets (ticket_id, title, description, status, created_at, user_id, agent_id, category_id)
VALUES (ticket_seq.NEXTVAL, 'Monitor Flicker', 'Screen flickering during usage', 'In Progress', SYSTIMESTAMP, 102, 202, 302);

-- Ticket 4
INSERT INTO Tickets (ticket_id, title, description, status, created_at, user_id, agent_id, category_id)
VALUES (ticket_seq.NEXTVAL, 'VPN Issue', 'Unable to connect to VPN remotely', 'Open', SYSTIMESTAMP, 105, 204, 303);

INSERT INTO Users VALUES (104, 'Visweswar', 'visweswar@gmail.com', 'Manager');
INSERT INTO Users VALUES (105, 'Jhon', 'jhon@gmail.com', 'Team Lead');

INSERT INTO Agents VALUES (203, 'Ramesh', 'Operations');
INSERT INTO Agents VALUES (204, 'Priya', 'IT');


INSERT INTO Categories VALUES (303, 'Network Problem');
INSERT INTO Categories VALUES (304, 'Security Concern');


INSERT INTO Tickets (ticket_id, title, description, status, created_at, user_id, agent_id, category_id)
VALUES (ticket_seq.NEXTVAL, 'VPN Issue', 'Unable to connect to VPN remotely', 'Open', SYSTIMESTAMP, 105, 204, 303);

INSERT INTO Tickets (ticket_id, title, description, status, created_at, user_id, agent_id, category_id)
VALUES (ticket_seq.NEXTVAL, 'Antivirus Problem', 'Antivirus blocking internal apps', 'Open', SYSTIMESTAMP, 104, 204, 304);


SELECT * FROM Tickets;
SELECT * FROM Tickets WHERE status = 'Open';
UPDATE Tickets SET status = 'Resolved' WHERE ticket_id = 1;

SELECT * FROM Tickets WHERE ticket_id = 3;

UPDATE Tickets SET agent_id = 202 WHERE ticket_id = 5;

SELECT * FROM Agents WHERE agent_id = 202;


SELECT 
    T.ticket_id, T.title, T.status, T.created_at,
    U.name AS user_name,
    A.name AS agent_name,
    C.category_name
FROM Tickets T
JOIN Users U ON T.user_id = U.user_id
JOIN Agents A ON T.agent_id = A.agent_id
JOIN Categories C ON T.category_id = C.category_id;





