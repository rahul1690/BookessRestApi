# BookessRestApi
The Bookess Application is used to handle book data online. Several authorities can be made there, at present we have User and Admin roles.
Instructions to Examine this application:-
The Database used: MySQL. change username and password.
Go to main method. uncomment the initial data declared in the Run method which belongs to the command line runner interface. Run the application
After successful insertion of the data comment the initial data.
Users can access endpoints that start with /user and default which are register, login, logout, and getMyDetails. He cannot perform Http Post method on book endpoints.
Admin can access all the endpoints(/admin,/user and default). Admin can give Admin role to a user and he can remove A user from Admin role.
But the admin who has the Main Admin role cannot be removed from Admin Role.
