# lineVnTest2

A.Requirement: improve code & DB, focus on 2 pages [/location/] và [/location/detail]. 

  1.List issues of the system: performance, error, exception etc.
  2.Update/add code to resolve these issues.

B.Implementation:
  
  I.Tool versions:
  
     - java: 1.8; 
     - mysql:  Ver 14.14 Distrib 5.7.19
     - nodejs: 6.9.1
     - bower: 1.8.0
     
  II.Run app:
    
    1.git clone https://github.com/anhvurz90/lineVnTest2.git
    2.cd lineVnTest2/MiniTest
    3.run the statements in "db/interview.sql"
    4.run the statements in "src/main/resources/updateDb.sql"
    5.bower install
    6.mvn spring-boot:run
    
  III.Bugs & performance issues that I found and resolved:
    
    1.Should add Unicode & utf-8 configuration in application.properties connection string. If not, inserted data can be incorrect
    2.In default/index.html, wrong url to location page: it should be ~/location/ but not ~/location/index
    3.Using jQuery to update HTML DOM is the old style. Everytime we change the css, we need to change the javascript code, too. So I change to use angularJS.
    4.In location/all.html page, all locations are requried & shown in the UI, that is very slow, so I add the pagination. Each time user can see only 1 page of the whole locations, and user can navigate from page to page.
    5.In location/detail.html page, the pagination does exist but it is implemented in the client side. That means all the data is fetched from the RDBMS to server then to the browser -> slow when data grows. So I implement the pagination in server & RDBMS sides, and only return 1 data page.
    6.LocationController.java#detail() method: {
	        users.forEach(user -> {
		      user.setCountChat(this.chatService.count(user.getUserId()));
		      user.setLatestChat(this.chatService.getLatestChat(user.getUserId()));
	        });
	}
	This is very slow. It requires 1 query to get users, and for each user 2 query to get count & latest chat. It means so many requests to DB. To overcome this issue I change the DB schema a little bit. In table 'user' I add 2 columns total_chat & latest_chat. Values of these 2 columns can be recalculated periodically or on the fly everytime when a new chat is added into DB. It is obvious that we store some redundant data but it helps to improve the performance (reduce the number of db query). These SQL statements can be found in https://github.com/anhvurz90/lineVnTest2/blob/master/MiniTest/src/main/resources/updateDb.sql lines 1, 2, 3
    7.There were no caching in the project. To improve the performance I enable level2 cache & query cache. So with the same query, no DB interaction repeats so it is much more faster.
    8.In location/detail.html page, dataTable was used and it provides the search functionality on the whole data set. But that is the "like %query%" search. Applying such behavior makes the performance down a lot because it scan the whole table in the database. So I change to "like %query" because it uses index to search -> much more faster. Another better anternative is to: 1. create fulltext index in table "user" and apply the MATCH AGAINST to perform the full text search. I already implemented that behavior in another branch: https://github.com/anhvurz90/lineVnTest2/tree/full_text_search with commit https://github.com/anhvurz90/lineVnTest2/commit/c2047674dc64c3bf7d1962b1718d1479e4d856ba but due to the bug https://jira.spring.io/browse/DATAJPA-980, the returned result is not what we expected. So may be we need next Spring Data Jpa release.
    9.In location/all.html page, when we choose "All", all locations only are shown. When we choose any area, all its location AND ITSELF are shown. The behavior is inconsistent so I think that is a small bug. So in the location list of any area, I remove the area itself.
    10.In table 'chat', there is the column 'location_id'. This column is not used anywhere in the code. The logic now is for each user, the total_chat & latest_chat values are calculated based on all his chats. This might be wrong, we might consider the chat location also, but I am not sure because there is no specification about these values. So I do not include the fix for this issue in the master branch. I put it in branch https://github.com/anhvurz90/lineVnTest2/tree/chat_in_location with commit https://github.com/anhvurz90/lineVnTest2/commit/eba9158c685e978d8d4ed155a28b11953df3a037
    11.In UserRepository.java, in the query there is no need for "group by"

  IV.Non-bug issue:
  
    1.There is not unitest nor intergration test. This is not a bug, just the lack of stability. Also it is not required in the task description, so I just mention here without any coding.
    2.It is better to use DTO for service & web layers, but only Entity.
