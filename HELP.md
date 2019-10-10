# Spotify music searcher

### Reference Documentation
Used technologies:

* Spring Boot (Java 8)
* MongoDb (Embedded)
* Slf4j
* Thymeleaf

### Description
The application contains several endpoints that communicate with the Spotify API to return to the user a list of artist names or track titles as searched.

Options (endpoint description):
* [Home Page](http://localhost:8080/) a form to search artists or tracks is shown, and you can see saved searches or logs. 
* [List of artist or tracks](http://localhost:8080/search?name=&type=/) list of searched names of artists or song titles (with song author) depending on the selected type from the list. To search, complete the field "search" and submit of the button "Search"
* After searching for an artist or track, you can save the search result to a database (button "Save search" saved result to embedded MongoDb)
* [Saved searches](http://localhost:8080/getAll-saved-searches/) all saved searches you can find after pressing button "Saved searches" on nav-bar
* [User logs](http://localhost:8080/log/) also you can see all logs from application


###### Made by Ż.M.


