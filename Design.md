## Restaurant
### Rep Invariant
* id is not null
* name is not null
* Reviews has no null entries and is not null
* Location is not null and is unique
* Every key in the information maps has non-null value associated with it
### Abstraction Function
Represents a restaurant as an id with associated name, location, reviews, and other information, where associated type produces a result

## User
### Rep Invariant
* Id is not null
* name is not null
* Reviews has no null entries and is not null
### Abstraction Function
Represents a user as an id and name, with associated reviews

## Review
### Rep Invariant
* Id is not null
* text is not null
* rating is not null
### Abstraction Function
Represents review as id, text, and rating

# Database
* Set of Users
* Set of Restaurants
* Map of Reviews -> Users
* Map of Reviews -> Restaurants

### Rep Invariant
* users is not null and contains no null entries
* restaurants is not null and contains no null entries
* Maps contain all reviews contained by users and restaurants
* If user in users has review, Map must map review to user
* If restaurant in restaurants has review, Map must map review to restaurant

### Abstraction Function
Holds two sets, users and restaurants
These users and restaurants have reviews stored in them. To speed computation, has maps of reviews to restaurants and users.
