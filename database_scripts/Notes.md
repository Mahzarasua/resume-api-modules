# Database details
Here you will find the necessary steps to create the database, its tables and even some sample data so you can run the web service right out of the box.

## Steps
 1. You must create a db in postgres called: resume-api
 2. Run the following script to create the database:
        create_db_resume.sql  
 3. Run the following scripts to create the tables:  
         create_table_resumes.sql  
         create_table_users.sql  
 4. Run the following scripts to populate initial data to the tables:  
        insert_resumes.sql  
        insert_users.sql  
 5. Once the db and tables have been created and the data has been inserted, you should be able to run the service.  
You will find a postman collection in this folder so you can test.

## IMPORTANT
 The user defined in the insert_users.sql script will work for basic and/or JWT authorization so make sure to modify/update it in the script and in your postman collection