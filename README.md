Although this project has been completed only partially, the time that has gone into modularising and creating an optimised client-server interaction can be useful to others. It's also a nice setup if you are starting to learn Spring and want to integrate it with AngularJS ;). We use a combination of mvn and jetty for project management and server which can be installed easily. 

Build : 'mvn clean install' {will install pertinent dependecies}
Run : 'mvn jetty:run'

Placement Cell App

Introduction\
We intend to make a webapp which will facilitate the process of internships and placements. The exact nature of the functionalities which we are planning to implement have been listed below. Our primary aim with these is to store the data/information that will be collected across various seasons and make the non-sensitive part of that info visible to the students in a meaningful manner. We also want the rules involved in the process to be clear to the students and make this in such a way that enforcing those rules requires minimal manual work as a result of which the process will be less prone to mistakes. Another aspect to information display is towards the recruiters. We want our website to be attractive and boasting of past year achievements, the general culture of our institute and its reputation and some gross stats because we believe that it has an impact in general. We will display and implement a clear flow of events for companies too.

Architecture\
We are using Spring MVC and AngularJS to develop this single page app(more or less).
The server has a set of stateless REST services as usual and this API is secured using Spring Security. The layers in
the backend consists of a Router Layer which is the entry point for a url and is responsible for the response, a Service
Layer that enforces various constraints which define how we are going to use the data and finally a layer that interacts
with the Database(postgreSQL) to access that data. The authentication and authorisation problems are handled by spring security.
Angular in the front-end takes care of all the display logic. After transfer of some files initially, only JSON data is transferred between the client and the server. There are three
layers in the frontend, a view layer which consists of an html, css and custom angular
directives to extend html, a controller layer that contains that display logic and a service
layer that interacts with the backend to get the data.

FUTURE SCOPE OF WORK\
1. Students have various course projects and other projects that need instructor approval. We want to develop an interface that makes this easier for the teacher to approve. So we are planning to make another entity called instructors to whom Coordinator can send the projects mentioned and get them verified
2. Some tables in the database will become irrelevant after an internship season for example: resumes of students.We want to build an interface which is only accessible to a special user who can reset such stuff without having to go to the level of scripts.
3. A big problem that arises during placements is slotting of students across multiple
companies and the change in that slotting that is entailed by declaration of results. We will try to build an automated system for slotting student interviews and to convey the results of interviews as fast as possible to the student to avoid mismanagement on the placement day.
