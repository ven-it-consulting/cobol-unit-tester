Cobol Unit Tester
-------------------


1)	General information
-----------------------

This application is a Cobol unit tester with SWING GUI using JTOpen API.
It allows you to connect specific (IBM i-series) host server, run specific Cobol program modules and
analyze results. 

There is a configuration file beside the executable (CobolTester.jar) file in your working directory
and on the project's classpath. It’s called “config.properties”.
You can set host system dependent information by opening this properties file in a text editor.
CobolTester reads all this information from the properties file.

CobolTester can run on every OS but we only tested it on Mac OS X and Windows.
It can be run standalone on your local computer or on a file server also.
(When you run unit tester on a file server your properties file is public and every user having the same
configuration during run!)

JRE 7 or higher have to be installed to the computer where this application should run.
JRE 7 download link is here: http://www.oracle.com/technetwork/java/javase/downloads/jre7-downloads-1880261.html


2.) config.properties file entries
----------------------------------
 
# serverIP
This is the IP address of the host server.

# libl01 - libl10
This is the library list order going to set on the host server for finding programs in IFS.
 
# pcmlPathPrefix
This is the path prefix. First section of the path to find PCML files on the host server.

# devStr01 - devStr05
This is a list which you can pick an item from in GUI. It's optional.
It's needed only when there are more then one folder structure on the server and environment folder
name is injected into the full path.
(For example if there are developer, test and integration folder structures on the server.) 
So this may be the second section of the path.

# pcmlPathPostfix
This is the path postfix. Second or third section of the path to find PCML files on the host server.

# defaultSave
It's a flag to set if FileChooser default directory will be the "/Save" or your user directory.
 - If value is "Y": Default directory on choosing or saving a parameter file will be set to "/Save".
 - If value is "N": Default directory on choosing or saving a parameter file will be set to user dir. 


PCML structure name variables
-----------------------------

# pcmlStrRef
Name of the 'reference' stucture. This is the section of input-output variables in PCML.

# pcmlStrPar
Name of the 'parameter' stucture. This is the section of output variables in PCML.

# pcmlStrOut
Name where cardinality of 'parameter' stucture is set. This is the section of output parameters.

# pcmlStrRes
Name of the 'result' section. This is where calling result value can be found in PCML.
(0: call was successful; 1: there was a failure)

# pcmlStrErr
Name of the 'error' stucture. This is where the fail reason and error message can be found. 

# pcmlStrMet
Name of the methode node in PCML. This tells where to find short methode names in PCML.

# pcmlResNode
Name of the result set node in PCML.

# pcmlRetValue
Name of the result structure in PCML.

# pcmlFailVars
Name of the failure descriptor variable structure.

# pcmlFailField, pcmlFailNum, pcmlFailCorr, pcmlFailClass, pcmlFailMod
Varible names of the failure section.


3.) Scalability
---------------

There may be several type of PCML files on host servers where Cobol programs run.
CobolTester application has the ability to scale PCML node names by editing config.properties file.
In case your PCML structure is the same as in “Sample.pcml” (in your working folder) editing
config.properties is sufficient for make this application work.

If your Cobol compiler generates PCMLs into some other kind of structure you have to implement your
own parser class from com.VeN_IT.AS400Test.interfaces.readPCMLInt interface.
