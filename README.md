Meal vote application

REST API documentation is available via Swagger: http://localhost:8080/swagger-ui.html 

The user, restaurant, meal and menu controllers are intended to be used by admins only.
Each of those controllers apply for create-update-delete of appropriate objects (user, restaurant, meal, menu). 
Menu object is designed to crossconnect restaurant and meal objects.

Any authenticated user can access application with Vote controller. TodayMenus method of VoteController can be used to get menus for current day.
Every menu is identified by the restaurant name.
Hear are the curl requests for testing:

#### get all menus for 2020-11-17
curl -s http://localhost:8080/votes/menus?date=2020-11-17 --user user1:12345

#### Create vote of user1 (date: 2021-01-03)
curl -s -X POST -d "{\"vote_date\":\"2021-01-03\"}" -H "Content-Type:application/json" http://localhost:8080/votes/100016 --user user1:12345

#### get all votes of user1
curl -s http://localhost:8080/votes --user user1:12345
