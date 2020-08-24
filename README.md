# Code2UML
Code2UML is a open-source tool for creating UML diagrams from pseudo-code (syntax is pretty similar to java).
It lets you focus more on your projects structure instead of thinking about designing diagrams and UML Standards. 


## Get it running
If you want to try it out yourself, you can either just donwload and run a compiled version or donwload the source code and compile it yourself.

#### Run compiled version
  1. If you havent already download the newest version of the Java Runtime Environment (JRE) [here](https://www.java.com/de/download/) and install it.
  2. Select a released Version of the project from the [releases folder](https://github.com/DarkReaperDev/TextUML/tree/master/releases) and download the folder with the realeses name, for example: [v.1.0](https://github.com/DarkReaperDev/TextUML/tree/master/releases/v.1/v.1.0). 
  3. Open the folder on your computer and double click the `.jar named` Code2UML + version name, for example : `Code2UMLv.1.0.jar`.
  4. If it doesnt work, open your command prompt or shell of choice and navigate to the folder where the `.jar` file is located. Then run `java -jar filename.jar` putting the name of the `.jar` file as `filename`.
   
#### Compile the source code yourself
  1. If you havent already download the newest version of the Java Development Kit (JDK) [here](https://www.oracle.com/de/java/technologies/javase/javase-jdk8-downloads.html) and install it.
  2. Download the [com folder](https://github.com/DarkReaperDev/TextUML/tree/master/src/com) containing the source code from the [source folder](https://github.com/DarkReaperDev/TextUML/tree/master/src).
  3. Compile it using the self compile guide \[coming soon].
  
  
## Get started
  1. After running the programm, a blank editor window pops up, where you can write your uml code. <br>
  <img src="https://imgur.com/Mubn477.png" alt="" width="400"/>\
  _a blank editor window_
  
  2. Type in:
  ```
  class HelloWorld{
      private string message;
      public bool isHappy;
      int GetSomeInt();
      public void DoSomething();
  }
```  
  3. In the upper left corner click on `file` and then `run`. <br>
  <img src="https://imgur.com/xHcnAtq.png" alt="" width="400"/>\
  _editor file -> run_
  
  4. Now a new window should pop up, containing a UML diagram of your class. Congrats, you created your first UML diagram!
  <img src="https://imgur.com/jOazwnc.png" alt="" width="400"/>\
  _created uml diagram_
  
  5. (Optionally) you can now save your uml script clicking on `file` and then `save as`. A window will show up where you can specify where you want to save the file. If you succesfulle saved your file you can always open it up again using `file` and then `open`. (It doesnt really matter if you save the file as a `.txt` or something else but I recommend saving it as a `.uml` file so you always know that it is more than just a text file.)
  
## Features

* converting java-like code and into clean UML diagrams
* currently supporting class, member and method declarations
* handling and displaying errors containing error line number
* saving and loading uml code

## Planned Features

* adding support for programming concepts like derived classes and interfaces
* function to convert real language projects into uml diagrams
* export funtion for UML diagrams
