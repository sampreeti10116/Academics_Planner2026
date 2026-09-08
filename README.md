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

## Current Progress

- [x] Project setup
- [x] Home page
- [x] Subjects activity
- [x] Activity navigation using Intents
- [x] Add Subject activity
- [x] Subject Name and Subject Code input
- [x] Passing subject data between activities
- [x] RecyclerView implementation
- [x] Custom Subject Adapter
- [x] MaterialCardView for subject display
- [ ] Persistent subject storage using SQLite
- [ ] Edit/Delete subject functionality
- [ ] Tasks module
- [ ] Exams module
- [ ] Timetable module
