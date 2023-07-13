# CleverTap Test Android

This document tells how the test app and test library works

# Library

This library exposes the 5 public methods for the user and the details of those methods are given below.
1. init() --> This method is responsible for initialization of the library
2. getImage() --> THis return the first image
3. getImages(input: Int) --> This method takes an input from the user and based on that it return the random number of images.
4. getNextImage() --> This method returns the next random image
5. getPreviousImage() --> This method returns the previous image

On the initialization of the library I trigger the given test Api to fetch 30 records and I store them it on arraylist to perform all the operations.

# App

On the app launch, I initialized the library and will show one action button and one input field to the user for seeing the single and multiple image.

If the user wanna see only one image at a time then you have to click on the [Show Image] button. Once the user clicked on that button then it will be hidden and the [Image], [Previous] and [Next] button is visible to the user.
Initially [Previous] button is inactive for any action. Once user clicked on the [Next] button then [Previous] button is active.

and apart from this, If the user wanna see multiple images then you have to enter a number in the input field and the input should be in the range of 1 to 10.
Once the user has given his input then the user have to click on the [Submit] button and then will show the images to the user based on the given input. To showcase the multiple image I used [HorizontalPager] with [DotsIndicator]. 