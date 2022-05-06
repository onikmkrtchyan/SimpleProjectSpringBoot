#Spring Boot Rest Application

##Implemented CRUD Operations in Spring Boot 

*    #### User object 
*    #### Passport object which connected to User object with @OneToOne relationship (One user can have one passport and one passport belongs to one user)
*    #### Car object which connected to User object with @ManyToOne relationship  (One user can have several cars ,but one car belongs only one user)
*    #### For last 2 cases resolved 'hibernate N+1 problem' (with Entity graph mechanism)
*    #### Implemented some validations for User's fields. ex. password , email.
*    #### Passwords store in DB as it's hash values.
*    #### Implemented most usable Endpoints
*    #### Implemented upload/download and export with PDF and Excel 

#### * For more convenient testing you can import postman_collection