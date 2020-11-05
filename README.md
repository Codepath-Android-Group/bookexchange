# BookExchange

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Allows users to post and search for books they'd like to sell, trade, or buy.

### App Evaluation

- **Category:** 
Shopping 
- **Mobile:**  Currently, this application is limited to mobile functionality, but future versions could include a web application.
- **Story:** Users can post books for sale/trade as well as search and shop for desired books.
- **Market:** Any user interested in buying, trading, or selling books.
- **Habit:** Users would use this as often as they needed to buy, sell, or trade books.
- **Scope:** Allowing users to connect and exchange books.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User should be able to register an account
* User should be able to login
* User should see top sellers on search screen (by default) before a query is sent
* Allow user to search by author, title, genre
* Login should be persistent
* User should be able to log out
* User should be able to post book for sale/trade/buy
* User should be able to click on any book listing for more detail


**Optional Nice-to-have Stories**

* Filter results by buy/sell/trade
* Integrate with NYTimes book API (or similar) to mitigate user error
* Compare against store listing price
* Users should be able to message seller/buyer to initiate sale
* User should be able to see previous sales/buys they've done
* User should be able to rate transactions with others users

### 2. Screen Archetypes

* Login/Register
   * User should be able to register an account
   * User should be able to login
* Search Screen
   * User should see top sellers on search screen (by default) before a query is sent
   * Allow user to search by author, title, genre
* Detailed Screen
  * User should be able to click on any book listing for more detail
* Post Book Screen
  * User should be able to post book for sale/trade/buy



### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Logout
* Post

**Flow Navigation** (Screen to Screen)

* Login
   * Login (submit)
   * Register
* Search Screen
   * Search
   * Clicking on an individual listing will lead to Detailed Screen
* Detailed Screen
   * Back button (goes back to Search Screen)
* Post Book Screen
  * Post (submits new listing to our backend and returns)
* Confirmation screen
  * Back (Goes back to search screen-> stetch: display listing history)
  

## Wireframes

<img src="wireframe.png">

## Schema 
### Models

Property | Type | Description
------------ | ------------- | -------------
Content from cell 1 | Content from cell 2 | d
Content in the first column | Content in the second column | d
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
