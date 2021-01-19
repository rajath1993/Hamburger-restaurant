# Hamburger-restaurant
Backend API endpoints for a hamburger restaurant - Spring boot, microservices, mongodb

## Table of Contents
* [General Info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Contact](#contact)

## General info
The hamburger restaurant is a backend application with all the CRUD operations. This mainly deals with Branch, reservations and menu details of the restaurant.

## Technologies
* Java
* Spring boot
* MongoDB
* Swagger - To represent all the API endpoints.

## Setup
To run this project, install it locally by cloning the GitHub respository.
### MongoDB
Install MongoDB on your machine - MongoDB compass community has a better UI which makes it more easy to navigate to the database
The application by default runs of port 27017 for mongodb - provide the same sort and start a new connection.
### Spring boot
Open the maven project in IDE, perform a maven build and run the spring boot application. You should see the application running on port:8080.

## Features
CRUD operations on Branch, Reservation and Menu.
This is built on the idea that one restaurant can have multiple branches.
Reservations are made based on the user details. Each user has a unique UUID.
Menu as a whole is separated into menu section. Each menu section will have a menu item.
The restaurant manager is the admin, and will be provided the authentication to perform CRUD operations on all the fields.

## Status
Project is finished with option to expand functionally.

## Contact
Create by [Rajath Anand](http://linkedin.com/in/rajathanand)

