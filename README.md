# Code2UML
Code2UML is a open-source tool for creating UML diagrams from pseudo-code (syntax is pretty similar to java).
It lets you focus more on your projects structure instead of thinking about designing diagrams and UML Standards. 


## Get it running
If you want to try it out yourself, you can either just donwload and run a compiled version or donwload the source code and compile it yourself.

#### Run compiled version
  1. If you havent already download the latest Java SE version [here](https://www.oracle.com/java/technologies/javase-downloads.html) and install it.
  2. Select a released Version of the project from the [releases tab](https://github.com/DarkReaperDev/Code2UML/releases) and download the zip-folder with the versions name. 
  3. Open the folder on your computer and double click the `.jar named` Code2UML + version name, for example : `Code2UMLv.1.0.jar`.
  4. If it doesnt work, open your command prompt or shell of choice and navigate to the folder where the `.jar` file is located. Then run `java -jar filename.jar` putting the name of the `.jar` file as `filename`.  
  
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
  
  4. Now a new window should pop up, containing a UML diagram of your class. Congrats, you created your first UML diagram!<br>
  <img src="https://imgur.com/jOazwnc.png" alt="" width="400"/>\
  _created uml diagram_
  
  5. (Optionally) you can now save your uml script clicking on `file` and then `save as`. A window will show up where you can specify where you want to save the file. If you succesfulle saved your file you can always open it up again using `file` and then `open`. (It doesnt really matter if you save the file as a `.txt` or something else but I recommend saving it as a `.uml` file so you always know that it is more than just a text file.)
  
  6. For more information see [documentation](https://github.com/DarkReaperDev/Code2UML/edit/feature/README.md){coming soon}
  
## Features

* converting java-like code and into clean UML diagrams
* currently supporting class, interface, member and method declarations
* extending and implementing classes is also supported
* handling and displaying errors containing error line number
* saving and loading uml code

## Planned Features

* function to convert real language projects into uml diagrams
* export funtion for UML diagrams
