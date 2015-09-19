"Plan to learn"
===========

Application helps students by giving them information how many hours per week approximately they need to study to pass exams with highest grades. During the Sign up process, user takes a short test. Based on the result, app determines whether the user has a more manager or a more programmer studying profile. Additionally, admin role can add exams, delete them, and can access all exams' information. When adding new exam, app administrator enters various coefficients for determining different student profiles e.g. low manager, high manager, low programmer and high programmer, and different exam areas e.g. programming or management.(admin-> username:admin password:admin)


#Prerequisites

You will need Leiningen 2.0.0 or above installed.

#Running

To start a web server for the application, run:
``` 
lein ring server
``` 
#Libraries used

Leiningen

Version - 2.0 Leiningen is the easiest way to use Clojure. With a focus on project automation and declarative configuration, it gets out of your way and lets you focus on your code. http://leiningen.org/

Clojure

Clojure provides easy access to the Java frameworks, with optional type hints and type inference, to ensure that calls to Java can avoid reflection. http://clojure.org/
``` 
[org.clojure/clojure "1.6.0"]
``` 
Compojure 1.3.1

A concise routing library for Ring - https://github.com/weavejester/compojure
``` 
[compojure "1.3.1"]
``` 

Hiccup 1.0.5

A fast library for rendering HTML in Clojure - https://github.com/weavejester/hiccup
``` 
 [hiccup "1.0.5"]
``` 

MongoDB
``` 
 [congomongo "0.4.1"]
```
 
License

Distributed under the Eclipse Public License, the same as Clojure.
