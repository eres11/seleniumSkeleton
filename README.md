seleniumSkeleton
================

this repo stores base skeleton for any selenium project. Implementation: selenium webDriver + java. 
Also  the  selenium skeleton provided is based on Page Object pattern approach. 
its idea can be understood in the following way:
src\main\java\com\tanjarine\automation\selenium  stores only page classes , 
e.g.
AdminHomePage.java
LoginPage.java
VenueManagementPage.java

src\main\java\com\tanjarine\automation\utils  stores Constants.java containing all the Constants within the projects:
URLs being used, etc..

selenium\src\test\java\com\tanjarine\automation\selenium
contains the following classes:
TestBase.java  - this class actually contains all the methods for the framework; i.e. methods for wating 
dynamic elements; methods for handlind js code, etc. Also it is worth mentioning, this one- is the parent class
for all other test classes' instances.
LoginPageTest.java
VenueManagemenentPageTest.java   - appropriate test classes containing instances of the page classes
described above in "java->com->tanjarine->automation->selenium"


 
 
