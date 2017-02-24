#Code Challenge
Author: Codin Pangell | 2/23/17

Prior Java Experience: *zero*

Java Version: 1.8

#Method for implementation
I decided to use Spark as a framework because it seemed to have a lean REST architecture without a lot of 'fluff'.

I use `Advanced Rest API` plugin for Chrome as a method to test and I suggest it to any engineer.

####Scope Change To Make Deadline
Since I am brand new to Java, everything was a challenge. I had to search to determine which IDE I should be using, common frameworks, and many syntax related queries. As a result, I took a little over 8 hours and had to cut scope of unit tests. Obviously, tests are extremely important but I focused on a stable application first. 

####Using Service
```
Title : Display list of all decks of cards and their associated names
URL : /
Method : GET
URL Params :  NONE
Response Codes: Success (200 OK)
Response Type: application/json
```

```
Title : Display a deck by associated name
URL : /:name
Method : GET
URL Params :  (name)
Response Codes: Success (200 OK) NOT FOUND (422)
Response Type: application/json
```

```
Title : Generate a deck by associated name and store in sequential order of suit-rank relationship
URL : /:name
Method : PUT
URL Params :  (name)
Response Codes: Success CREATED (201 OK)
Response Type: application/json
```

```
Title : Delete a deck by associated name
URL : /:name
Method : DELETE
URL Params :  (name)
Response Codes: Success REMOVED (204 OK)
Response Type: application/json
```

```
Title : Shuffle a deck by associated name
URL : /:name
Method : POST
URL Params :  (name)
Response Codes: Success MODIFIED (204 OK) NOT FOUND (422)
Response Type: application/json
```

###Standard vs Complex Shuffle
A standard method of shuffling may be enough but if the administrator wishes to have a more complex shuffle a flag may be enabled within Nike.java

`boolean COMPLEX_SHUFFLING_ENABLED = true;`


#Provided Information As Guidelines
###Overview
Please create a RESTful microservice that implements a card shuffling algorithm, as defined below.  We’d like to see evidence of test-driven development with unit tests.  We’d prefer you use Gradle for the build, and Jetty to host, but these aren't requirements.  Use best practices of interfaces and generics for abstraction, preferably implementing a strategy pattern for deploy-time dependency injection of a shuffling algorithm.  Please document your decision making process with comments in the code, especially with regards to any scope reduction.
 
###Requirements

·         Create a microservice that stores and shuffles card decks.

·         A card may be represented as a simple string such as “5-heart”, or “K-spade”.

·         A deck is an ordered list of 52 standard playing cards.

·         Expose a RESTful interface that allows a user to:

·         PUT an idempotent request for the creation of a new named deck.  New decks are created in some initial sorted order.

·         POST a request to shuffle an existing named deck.

·         GET a list of the current decks persisted in the service.

·         GET a named deck in its current sorted/shuffled order.

·         DELETE a named deck.

·         Design your own data and API structure(s) for the deck.

·         Persist the decks in-memory only, but stub the persistence layer such that it can be later upgraded to a durable datastore.

·         Implement a simple shuffling algorithm that simply randomizes the deck in-place.

·         Implement a more complex algorithm that simulates hand-shuffling, i.e. splitting the deck in half and interleaving the two halves, repeating the process multiple times.

·         Allow switching the algorithms at deploy-time only via configuration.
 Provide the source code and instructions for building/running/using the microservice
