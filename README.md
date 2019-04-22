# TheMuse - A Job Portal
## Problem Statement
Build a system to manage jobs.
## Requirements
1. The application needs to fetch existing jobs from https://www.themuse.com/developers/api/v2
2. A frontend where the user can **View and Bookmark jobs.** On click of the job the user should be able to see the actual landing page. ****
user.
## Modules
### AccountManager - should be able to manage user accounts.
### MuseUI (User interface) - should be able to
    - search jobs
    - jobs should also have paginated output
    - bookmark jobs
    - should be able to see jobs bookmarked by him
### MuseManager - should be able to store all the bookmarks and searches
## Tech Stack
- Spring Boot
- Angular
- CI (Gitlab Runner)
- Docker, Docker Compose
## Flow of Modules
### Building frontend
- Building responsive views:
	1. Jobs - Populating from external api
	2. A view to show Bookmarked Jobs
	3. A view to authenticate users 
- Using Services to populate these data in views
- Stitching these views using Routes and Guards
- Making the UI Responsive
- E2E test cases for few views
- unit test cases
- Writing CI configuration file
- Dockerize the frontend
### Building the Account Manager (Approximately 6 Hours) 
- Creating a server in Java to facilitate registration and Login using JWT token and MysSql
- Writing swagger documentation
- Unit Testing
- Write CI Configuration
- Dockerize the application
- Write docker-compose file to build both frontend and backend application
### Building the Muse Manager
- Building a server in Java to facilitate CRUD operation over Jobs and bookmarked resources stored in MySQL
- Writing Swagger Documentation
- Write Test Cases
- Write CI Configuration
- Dockerize the application
- Update the docker-compose
### Demonstrate the entire application
