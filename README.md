# Week 06 Lab

## Instructions
Fork this repository into the group given to you by the instructor. Once forked, clone it to work with it in your computer.

This repository has a Gradle project under the folder named `Gradle`. Import this folder into Eclipse as a Gradle project. Write your implementation files in its `src/main/java` folder. JUnit test files are given to you in the `src/test/java` folder. Use these files to validate your implementation.

Commit and push your work as often as possible. When pushed, your project will be automatically deployed to [Web-CAT](https://web-cat.cs.vt.edu) for grading. You can push as many times as you want. The last push before the lab is due represents your final submission.

## Exercise 1
### Required Class: `Album`

Albums are objects representing music albums. Albums have a name (e.g., "Back in Black") and are authored by a band (e.g., "AC/DC"). In addition, they have a length, which is given as the total running time of their songs (for simplicity, our album objects will only hold the length of songs, not their names).

The class has the following methods:
- `public Album(String name, String band)`: constructor; it initializes the album's name and band with the given values. It throws an *IllegalArgumentException* if the given name or band are null (with messages "name cannot be null" and "band cannot be null" respectively).
- `public String getName()`: getter; it returns the name of the album.
- `public String getBand()`: getter; it returns the name of the band.
- `public Duration getLength()`: getter; it returns the length of the album. `Duration` is a JDK class found in the `java.time` package.
- `public void addSong(Duration length)`: setter; it adds a new song length to the album. It throws an *IllegalArgumentException* if the given length is null (with message "length cannot be null").
- `public ArrayList<Duration> getSongs()`: getter; it returns a new array list with the duration of each and all songs in the album.
- `public boolean equals(Object obj)`: getter; it overrides the method in class `Object`. Two albums are the equal if they have the same name and band.
- `public String toString()`: getter; it overrides the method in class `Object` and returns a string in the form "Album [name=*name*,band=*band*,length=*length*]" where *name* and *band* are the name of the album and the name of the band, respectively, and *length* is the length of the album in hours, minutes and seconds. Length is formatted as *H*:*MM*:*SS*, where *H* are hours (0-9), *MM* are minutes (0-59), and *SS* are seconds (0-59). Minutes and seconds use 2 digits (with a leading zero if needed).
