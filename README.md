## Food Truck Finder

#Installation steps
* Clone application from git@github.com:vprathaptechjava7/FoodTruckFinder.git
* Add or Open to any of the IDE such as IntelliJ or eclipse
* Do a Maven clean install to get all dependencies on local
* Run main class file src/main/java/FoodTruckFinder.java


#Suggestions to convert Food Truck Finder into fully plugged  web application 
*Divided application into 3 different layer

  1.) Business and Data layer :
  
       a.)Creating a spring boot project (as it reduces lots of development time and increases productivity)
       
       b.)Use micro service architecture (as it easy to build and maintain Application)
       
       c.)Use design pattern to structure code like prototype design pattern (as it makes communication between designers 
       more efficient)
       
       d.)Use Hibernate and JPA repository for easy and less complex connections to data base 
       
       e.)Add swagger for getting interactive UI for back-end micro services 
       
       
  2.) User Interface layer 
  
       ***a.)Creates a interaction bridge between backend service and User interface 
       
       ***b.)Acts as a wrapper for combing data from different micro services.
       
  3.) UI (with Augular or React )
  
        a.)Create a Model files for access data from API
        
        b.)Controller for managing all data between API 
        
        c.)View with managing the UI with CSS parser for designing
        
 *Add logger for tracking errors in different micro services (increases probability of fixing bugs and defects)
