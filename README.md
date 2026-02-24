

# Mobile App for Weighted Average Calculation

This repository contains a mobile application built with **Jetpack Compose**. The main feature of the app is to calculate the weighted arithmetic mean of numbers ranging from 2 to 5 with different coefficients.

## Features

- Input numbers from 2 to 5
- Assign custom coefficients (weights) to each number
- Calculate the weighted average based on inputs and coefficients
- Simple and intuitive UI designed with Jetpack Compose

## Build with

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)

## How It Works

1. User inputs numbers between 2 and 5
2. User sets the coefficient (weight) for each number
3. The app calculates the weighted average using the formula:

   weighted_average = (n1 * c1 + n2 * c2 + ... + nk * ck) / (c1 + c2 + ... + ck)

4. Result is displayed on the screen

## Installation and Usage

1. Clone the repository
2. Open the project in Android Studio
3. Build and run the app on an emulator or device
4. Enter numbers and their coefficients to see the weighted average calculation

## License

This project is open source and available under the MIT License.
