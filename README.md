# Umar's Assignment

## Understanding the provided JSON

Note that the assignment have been done to the best of my understanding. Any additional functionality is only added to showcase my skills as a software engineer.
The following is the JSON Provided for the assignment

The Blue Line indicate the Problems, Problem can be added dynamically.
The Red Line indicates the className and one problem can have many classNames.
The Green line indicate the drugs associated with the className for the specific problem.

I will be moving forward assuming the above state observations. 

![JSON Provided for the assignment](https://github.com/mybringback22/Umar-sAssigment/blob/main/screenshots/GivenJson.png?raw=true)


## Clean Architecture

I will be following the clean architecture along with MVVM as it is the market standard. Using clean architecture ensure that the project is scalable and the business and UI layers are separated.

![Clean Architecture](https://github.com/mybringback22/Umar-sAssigment/blob/main/screenshots/CleanArchitecture.png?raw=true)

### Expanded

![Clean Architecture](https://github.com/mybringback22/Umar-sAssigment/blob/main/screenshots/CleanArchitectureExpanded.png?raw=true)

## Data Layer 

I have put in the extra work in the data layers to follow clean architecture as closely as possible. 
I have made Data Transfer Objects to mimic how I will work on a large scale project where api will throw a lot of data towards frontend and frontend might not need all of it.
Data Transfer Object ensures that domain layer and presentation layer only get access to the data that is needed by them. 
So person working on the presentation layer does not get bombarded with unnecessary data.

![Clean Architecture](https://github.com/mybringback22/Umar-sAssigment/blob/main/screenshots/DataLayer.png?raw=true)

## Main Logic & Tools

I have used Retrofit as the main networking library, Room as the local storage solution and hilt as DI library. 
I have written a connectivity Manager to check the state of the internet connection.
If there is internet connection, we hit the mock api and get the data and store it in the database and then listen to the data from the database.
If there is no internet, we check the db for existing data and display data if there is any in the db.
if the internet is disconnected and there is data already loaded on the screen, the api is hit again when connectivity is restored and db is updated and so does the UI.

The data displayed on the UI is only being fetched from the db maintaining the principle of "Single Source of Truth"
Eg.  -> API Hit -> Store In DB -> Fetched from DB -> Displayed to UI
Eg#2.  No connection -> Fetch from DB -> Displayed to UI-> Connect restored -> API Hit -> update in DB -> Fetched from DB -> Displayed to UI

![Clean Architecture](https://github.com/mybringback22/Umar-sAssigment/blob/main/screenshots/Screenshot%202567-12-07%20at%201.50.55%E2%80%AFAM.png?raw=true)

## Unit Test 

I have added Three unit test as per the requirement for the single UseCase that this assignment have. 
There is a unit test for each possible case (Loading , Error , Success)

![Clean Architecture](https://github.com/mybringback22/Umar-sAssigment/blob/main/screenshots/UnitTest.png?raw=true)

## APP UI

### Login Screen 
![Clean Architecture](https://github.com/mybringback22/Umar-sAssigment/blob/main/screenshots/LoginScreen.png?raw=true)

### Login Screen Error
![Clean Architecture](https://github.com/mybringback22/Umar-sAssigment/blob/main/screenshots/LoginError.png)

### Login Screen Form Filled
![Clean Architecture](https://github.com/mybringback22/Umar-sAssigment/blob/main/screenshots/LoginFilled.png?raw=true)

### Landing Screen Loading State
![Clean Architecture](https://github.com/mybringback22/Umar-sAssigment/blob/main/screenshots/LoadingState.png?raw=true)

### Landing Screen Loaded State
![Clean Architecture](https://github.com/mybringback22/Umar-sAssigment/blob/main/screenshots/LoadedState.png?raw=true)

### Landing Screen Scrolled State
![Clean Architecture](https://github.com/mybringback22/Umar-sAssigment/blob/main/screenshots/ScrolledState.png?raw=true)

### Detail Screen
![Clean Architecture](https://github.com/mybringback22/Umar-sAssigment/blob/main/screenshots/DetailScreen.png?raw=true)