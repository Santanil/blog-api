
    # Blog API in Spring Boot


    ## Development


    ### Database setup
    
    #### MySQL

    #### PostgreSQL
    Setup:
    CREATE DATABASE blog;
    CREATE USER blog_user WITH ENCRYPTED PASSWORD 'blogpassword';
    GRANT ALL PRIVILEGES ON DATABASE blog TO blog_user; -> blog_user is the admin of blog db