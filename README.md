# Gardening Journal App

## Description
The Gardening Journal App is a robust and user-friendly mobile application developed using Android Kotlin. It's designed to assist gardening enthusiasts in tracking and managing their gardens efficiently. The app features a sleek navigation structure with three main screens: Home, Garden Log, and Plant Details. It incorporates advanced concepts like ViewModel, LiveData, Navigation component, NavHostFragment, Room database, and coroutines to provide a seamless and responsive user experience.

### Features
- **Navigation Structure**: Utilizes the Navigation component for smooth navigation between the Home, Garden Log, and Plant Details screens, hosted in a NavHostFragment.
- **ViewModel and LiveData**: Each screen is managed by its ViewModel, ensuring UI data is retained across configuration changes. LiveData is used for observing and responding to data changes.
- **Room Database**: Features an entity class for plants, allowing storage and retrieval of plant data like name, type, watering frequency, and planting date through a Room database.
- **Coroutines**: Asynchronous operations like database queries are handled efficiently with coroutines, ensuring the main thread remains unblocked.
- **Garden Log Screen**: View a list of plants and add new plants to the database, with asynchronous data insertion.
- **Plant Details Screen**: Offers detailed views of selected plants, utilizing navigation arguments and database queries for detailed information.

## Installation Instructions
1. Clone the repository:
git clone [https://github.com/zadigg/Gardening-Journal-App.git](https://github.com/zadigg/Gardening-Journal-App.git)

2. Open Android Studio and navigate to 'Open an existing project'.
3. Select the cloned project folder and wait for Gradle to build the project.
4. Once the build is complete, run the app using an emulator of your choice.

## Implementation
This app is a showcase of advanced Android development techniques. The implementation of ViewModel and LiveData ensures that UI-related data is managed effectively, surviving configuration changes and keeping the user interface responsive and current. The Room database integration demonstrates a solid grasp of Android's storage capabilities, providing a robust and efficient way to handle data. Coroutines are extensively used for handling asynchronous tasks, crucial for maintaining a smooth user experience. Each screen, from the Garden Log to the detailed Plant Details, is thoughtfully designed to offer functionality and ease of use, making it a perfect tool for any gardening enthusiast.
