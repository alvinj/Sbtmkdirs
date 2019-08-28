# sbtmkdirs

This is a Scala version of my 
[sbtmkdirs shell script](https://alvinalexander.com/sbtmkdirs).
As its name implies, I use it to create a directory structure
for Scala/SBT applications.

Since GraalVM lets us create native executables that start up
immediately, I decided to rewrite this in Scala.


## Usage

Here’s a quick example of how to use `sbtmkdirs` once it’s compiled:

````
$ sbtmkdirs 

Directory/Project Name: FooBar
Create .gitignore File? (Y/n): 
Create README.md File? (Y/n): 
Create ‘resources’ subdirs? (y/N): 
Create ‘java’ subdirs? (y/N): 

-----------------------------------------------
Directory/Project name:   FooBar
Create .gitignore file?:  y
Create README.md file?:   y
Create ‘resources’ dirs?: n
Create ‘java’ dirs?:      n
-----------------------------------------------
Create Project? (Y/n): y
Project created.
````

In that example I just hit the `[Enter]` key in response to the first four yes/no questions. This creates this directory structure:

````
$ find FooBar
FooBar
FooBar/README.md
FooBar/project
FooBar/.gitignore
FooBar/build.sbt
FooBar/src
FooBar/src/test
FooBar/src/test/scala
FooBar/src/main
FooBar/src/main/scala
````


## Compiling

I create the application like this:

1. Create a JAR file with `sbt package`.
2. That creates a file named *target/scala-2.12/sbtmkdirs_2.12-0.1.jar*.
3. `cd` into the *Graal* directory.
4. Run `. 1setup_graal` to set up my environment variables. (You’ll need to change that script for your system.)
5. Create the executable by running `./2compile_graal.sh`.

That last command creates a native executable named *sbtmkdirs*. I think copy that file to my *~/bin* directory so it’s on my PATH.

