# Academics Planner 2026

A simple Android application designed to help students organize and manage their academic information.

## Home Page

The initial home page of the application provides navigation to the main sections of the Academics Planner.

<img width="388" height="867" alt="image" src="https://github.com/user-attachments/assets/0619d68d-c507-4151-967c-d126c7d6b9aa" />

## Subjects Activity

Created a dedicated Subjects screen with an **Add Subject** button and a section for displaying the user's subjects.

<img width="392" height="867" alt="image" src="https://github.com/user-attachments/assets/db8f65f9-8fb9-4d71-b9e1-1b31a7c6741e" />

## Navigation Using Buttons and Intents

Implemented navigation between activities using **Buttons** and **Intents**.

The current navigation flow is:

**Home Activity → Subjects Activity → Add Subject Activity**

<img width="377" height="836" alt="image" src="https://github.com/user-attachments/assets/620c9a3d-c45f-48f2-8f29-cc5ee3c6cbe6" />

## Subject Addition Functionality

Implemented the logic for adding a subject through the **Add Subject Activity**.

The user can enter:

- Subject Name
- Subject Code

The entered information is passed back to the Subjects Activity using the **Activity Result API**.

<table>
  <tr>
    <td><img width="391" height="867" alt="image" src="https://github.com/user-attachments/assets/f92776e6-01f1-49f3-9c34-df2e82658b23" /></td>
    <td><img width="386" height="866" alt="image" src="https://github.com/user-attachments/assets/690bd0bd-a524-4025-841b-31570f09c971" /></td>
  </tr>
</table>

## RecyclerView for Subjects

Replaced the single subject display with a **RecyclerView** to support displaying multiple subjects.

A custom `SubjectAdapter` and `Subject` data class were implemented to manage and display subject information.

Each subject is displayed using a **MaterialCardView** containing:

- Subject Name
- Subject Code

<table>
  <tr>
    <td><img width="388" height="866" alt="image" src="https://github.com/user-attachments/assets/e5c85024-7521-4957-bea9-faa59ccabbd2" /></td>
    <td><img width="386" height="867" alt="image" src="https://github.com/user-attachments/assets/ef666e83-4b20-48ee-9f14-c6367652968b" /></td>
  </tr>
</table>

## Added Delete Buttons to remove a Subject

Added a **Delete** button to each subject card.

When the delete button is clicked:

1. The selected subject is removed from the subject list.
2. The RecyclerView is notified of the change.
3. The corresponding subject card is removed from the screen.

This functionality is currently handled using the in-memory subject list.
<table>
  <tr>
    <td><img width="387" height="866" alt="image" src="https://github.com/user-attachments/assets/b05b6e71-cc12-433c-aa8c-2141d0462e9d" /></td>
    <td><img width="387" height="862" alt="image" src="https://github.com/user-attachments/assets/f6399116-5757-402a-a93e-b4c7d7fbe02f" />
</td>
  </tr>
</table>

## Current Progress

- [x] Android Studio project setup
- [x] Home page
- [x] Subjects Activity
- [x] Activity navigation using Intents
- [x] Add Subject Activity
- [x] Subject Name input
- [x] Subject Code input
- [x] Passing subject data between activities
- [x] RecyclerView implementation
- [x] Custom Subject Adapter
- [x] MaterialCardView subject design
- [x] Delete Subject functionality
- [x] ConstraintLayout-based UI
- [ ] Persistent subject storage using SQLite
- [ ] Edit Subject functionality
- [ ] Tasks module
- [ ] Exams module
- [ ] Timetable module
- [ ] Final UI improvements
- [ ] Testing and bug fixing
