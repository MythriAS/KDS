![image](https://github.com/user-attachments/assets/e6a25a2c-4a4f-4e8f-b46c-47d237ec4cbc)
![image](https://github.com/user-attachments/assets/ec719cd2-7b79-41c1-a151-819db9ad747c)



The Kitchen Display System (KDS) is an application that fetches data using a fake API and displays it to the user. 
It utilizes *Retrofit* for data fetching and implements the MVVM architecture pattern for communication between fragments, activities, and the ViewModel. 
The system is divided into two tabs, Current Orders and Complete Orders, with real-time updates on the item count displayed across both fragments.

# Features
- *Tabs & Fragments*: The app is organized into two main fragments: Current Orders and Complete Orders.
- *RecyclerView*: Two RecyclerViews are used to display data from both tabs.
- *ViewModel*: Manages the data and updates for both fragments, ensuring smooth communication between them.
- *Retrofit*: Used for fetching data from a third-party API.
- *Real-Time Updates*: When an item is moved from the Current Order to the Complete Order, the item count is updated live in both tabs.
- *Item Bumping*: Items in the Current Order can be "bumped" to the Complete Order tab, reflecting the status change.
  
# Tech Stack
- *Retrofit*: For API data fetching
- *MVVM Architecture*: ViewModel for data management and Fragment communication
- *RecyclerView*: To display items in lists
- *Fragment Manager & Transactions*: For communication between fragments

# Usage
- The application will show the *Current Orders* by default, fetched from the API.
- *Current Orders Tab*: Displays a list of items with their names, images, and a "Bump" button.
- *Complete Orders Tab*: Displays the items that have been moved from the Current Orders.
- When you click the *Bump* button on an item in the Current Orders tab, it moves to the Complete Orders tab and updates the item count on both tabs.
- The total count of items in both the Current and Complete Orders tabs is displayed at the top of each tab and updates dynamically.

## Architecture
The app follows the *Model-View-ViewModel (MVVM)* architecture to ensure separation of concerns:
- *ViewModel*: Manages the business logic such as inserting an item from the Current Orders RecyclerView to the Complete Orders RecyclerView and updating the tab counts.
- *Fragments*: Display the UI elements (RecyclerViews) and observe the LiveData in ViewModel to update the UI.
- *Adapter*: Handles the binding of data to RecyclerViews.

## Communication
- *Fragment Communication*: Utilizes FragmentManager and FragmentTransaction to handle communication and data flow between fragments.
- *Activity & Fragment Communication*: Achieved using ViewModel and LiveData to share data between fragments and activities.
