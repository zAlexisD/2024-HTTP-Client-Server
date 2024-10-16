# HTTP Client/Server with Java Sockets
3rd Year mini-Lab
## HTTP Client
### Description
Objective : Write a JAVA program to retrieve a page identified by a URL passed
as an argument through a GET (HTTP) request from a web server and save it to disk.

For example : 
````
java clientHTTP http://www.faqs.org/rfcs/rfc2068.html
````
saves the contents of the page to the file __rfc2068.html__ if the 
statue __200 OK__ (otherwise it should display an error message)

Note : With this url, we need to add the User-Agent line in the HTTP request header

### Getting Started
Run the httpClient Class with entry : 
````
http://faqs.org/rfcs/rfc2068.html
````
### Work in Progress
* For now, the GET request seems to work, but we only have the 
__204 No Content__ statue so the returned file is empty.

* We should also consider adding the error message if the statue is not __200 OK__.


## License
This project is licensed under the [MIT License](LICENSE.md).

**Note: This project is an academic exercise and was developed for educational purposes as part of the curriculum at ENSEA.**