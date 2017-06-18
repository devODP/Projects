
Purpose:
This project is an application of how a web server handles http requests from clients. This project intends to provide administrator a flexible way to manage a database from another end-system to provide version control support for files.

Description:
Upon connecting to http://ip_address:8080/DB_Ser_Cli/login.html, the user is then required to enter credentials for authentication. On the server side, the servlet "Login" is designed to handle the authentication from the user. Upon successful authentication, the servlet "Login" redirects the user to client.html to modify, retrieve data from database or upload files to the host machine. Each time a user is on the page of client.html, the client sends network information (IP address) to the servlet Reception for user identification along with the initial log-in information obtained in login.html. The IP address and user log-in information and related flag values together form a basic schema for user authentication. 

Currently, user can be successfully authenticated by entering correct credentials (e.g. username = "admin", password = "admin") and be able to upload files to the web server and retrieve data from the database from another end system under the same network. 
There can be only one admin logged in at a time. Second user is restricted to request any service on the web server until the first user logged out. The application can support inserting data into the database using pre-processed .csv file provided from users.


