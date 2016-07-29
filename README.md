# WebUntisAPI
This is a simlpe Java Library/API to access the RESTful json RCP WebService from Untis

Please ensure you are using a common json library in your project too! I use:  http://jcenter.bintray.com/org/json/json/20151123/json-20151123.jar


To start using the API just bind it into your project as dependencie and follow the instructions:

First off all you will need a WebUntisConnection

<code>WebUntisConnection connection = new WebUntisConnection("school-sch", "poly", "username", "password");</code>

The first parameter is the name of the school in Webuntis itself. The second is the server prefix, look up whats yours, when you login on the website (hava a look on the URL). And the last ones are self explained. (Note that the account you are using has full permissions to view all information!!!)

Now we can login <code>connection.login()</code> //Note: The returned boolean says if the login is was successfully or not

As we are connected now, we can send requests like asking for a list of all teachers <code>connection.getTeachers()</code>

When we finished our requests, be sure to log out with <code>connection.logout()</code>


TODO:

- Add documentation
- Add more detailed example
