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

## SQLite Database Integration

Integrated **SQLite** using `SQLiteOpenHelper` to provide persistent local storage for the Subjects module.

### Database Structure

A local database named **AcademicsPlanner.db** is created with a `subjects` table.

| Column | Type | Description |
|--------|------|-------------|
| id | INTEGER | Primary key (Auto Increment) |
| name | TEXT | Subject name |
| code | TEXT | Subject code |

### Features Implemented

- Created a `DatabaseHelper` class using `SQLiteOpenHelper`.
- Automatically creates the `subjects` table when the application runs for the first time.
- Saves subject details into SQLite using `ContentValues` and `insert()`.
- Retrieves all saved subjects using SQL `SELECT` queries.
- Displays stored subjects in the RecyclerView when the Subjects screen opens.
- Subject data now persists even after closing and reopening the application.

### Data Flow

Add Subject -> SQLite Database -> Subjects -> RecyclerView

<table>
  <tr>
    <td><img width="380" height="836" alt="image" src="https://github.com/user-attachments/assets/97be7e2f-5899-4a3c-9722-39f906dfc4db" /></td>
    <td><img width="380" height="856" alt="image" src="https://github.com/user-attachments/assets/22fcc8e2-817d-4027-bf92-08c40aa405ee" /></td>
    <td><img width="380" height="857" alt="image" src="https://github.com/user-attachments/assets/a4563e9d-347e-4f83-9035-42f3cc657a72" /></td>
  </tr>
</table>


## Persistent Subject Storage

Subjects remain available even after restarting the application because they are loaded from the SQLite database during startup.

## SQLite-Powered Delete Functionality

The Delete Subject functionality has been integrated with the SQLite database.

### Features Implemented

- Delete button available on each subject card.
- Identifies the selected subject from the RecyclerView.
- Deletes the corresponding subject from the SQLite database.
- Removes the subject from the RecyclerView immediately.
- Deleted subjects remain removed after restarting the application.

### Data Flow

```text
Select Subject
      │
      ▼
Delete Button
      │
      ▼
Delete from SQLite
      │
      ▼
Remove from RecyclerView
```

### Demonstration

Subjects can now be added to and deleted from the SQLite database while the RecyclerView remains synchronized with the stored data.

<img width="372" height="861" alt="image" src="https://github.com/user-attachments/assets/6a312da1-4cfd-4d98-94da-3ad9c53e8d62" />

## Edit Subject Functionality

Implemented the **Edit Subject** feature to allow users to modify existing subject information.

### Features Implemented

- Added an Edit button to each subject card.
- Opens a dedicated Edit Subject Activity.
- Automatically displays the existing subject name and code.
- Allows users to modify subject details.
- Updates the corresponding record in the SQLite database.
- Refreshes the RecyclerView after updating.
- Updated information persists after restarting the application.

### Data Flow

```text
Select Subject
      │
      ▼
Edit Button
      │
      ▼
Edit Subject Activity
      │
      ▼
Update Subject Details
      │
      ▼
SQLite UPDATE
      │
      ▼
Refresh RecyclerView
```

### Demonstration

<table>
  <tr>
    <td><img width="380" height="852" alt="image" src="https://github.com/user-attachments/assets/bcf5220d-14ff-4a37-a520-1592d0c472d3" /></td>
    <td><img width="390" height="871" alt="image" src="https://github.com/user-attachments/assets/13736c75-2318-4f18-baf3-8b4946c57ad9" /></td>
    <td><img width="391" height="871" alt="image" src="https://github.com/user-attachments/assets/21135ebf-c594-4b5c-83f4-253c7d1e7bd2" /></td>
    <td><img width="391" height="861" alt="image" src="https://github.com/user-attachments/assets/b0153f55-b974-4110-9a3b-5bbdf8c0f2c4" /></td>
  </tr>
</table>

## Tasks Module
### Add Task

The Add Task feature allows users to create a new academic task by entering details such as:

Task Title
Task Description
Due Date
Priority

When the user clicks the Save Task button, the task details are validated and stored in the SQLite database. A confirmation message is displayed after successful insertion.

<table>
  <tr>
    <td><img width="381" height="867" alt="image" src="https://github.com/user-attachments/assets/96147c25-14ae-4b52-8b03-518f7e836249" /></td>
    <td><img width="387" height="845" alt="image" src="https://github.com/user-attachments/assets/1cd30b38-e883-4cc7-9e7e-93d63e77c3de" /></td>
  </tr>
</table>

### Delete Task
The Delete Task feature allows users to remove tasks that are no longer required.

Each task card contains a delete button. When the button is clicked, the corresponding task is deleted from the SQLite database and removed from the RecyclerView.

<table>
  <tr>
    <td><img width="390" height="866" alt="image" src="https://github.com/user-attachments/assets/15a660eb-d88e-45ac-8c2a-af160d3ddb27" /></td>
    <td><img width="395" height="867" alt="image" src="https://github.com/user-attachments/assets/b843ed71-0f40-4226-9d54-19067195eeae" /></td>
  </tr>
</table>

### Edit Task

The Edit Task feature allows users to modify previously added tasks. Users can update the task title, description, due date, or priority.

The selected task details are loaded into the Edit Task screen. After updating the information, the changes are saved in the SQLite database, and the updated task list is displayed.

<table>
  <tr>
    <td><img width="392" height="870" alt="image" src="https://github.com/user-attachments/assets/6f36a18e-4e0a-42a1-9667-3c5e920d670d" /></td>
    <td><img width="378" height="867" alt="image" src="https://github.com/user-attachments/assets/0bd89bc3-9e86-4860-b42a-f4856a0b0f6d" /></td>
     <td><img width="377" height="862" alt="image" src="https://github.com/user-attachments/assets/3e9b69ac-7208-4900-a903-f95a7294d477" /></td>
    <td><img width="400" height="847" alt="image" src="https://github.com/user-attachments/assets/f6607591-e374-45b1-a84d-503822a33d4c" /></td>
  </tr>
</table>

### SQLite database integration

SQLite is used as the local database for storing task information.

The tasks table contains the following fields:

### Tasks Table Structure

| Field | Description |
| --- | --- |
| `id` | Unique identifier for each task |
| `title` | Name of the task |
| `description` | Detailed description of the task |
| `dueDate` | Deadline of the task |
| `priority` | Priority level of the task |
| `isCompleted` | Stores whether the task is completed or incomplete |

The database ensures that task data remains available even after navigating between activities or restarting the application.

### Mark as completed

A completion checkbox has been added to every task card. Users can mark a task as completed by selecting the checkbox.

Unchecked checkbox indicates an incomplete task.
Checked checkbox indicates a completed task.

The completion status is stored in SQLite using the isCompleted field, where 0 represents incomplete and 1 represents completed.

The checkbox state is restored whenever tasks are loaded from the database, ensuring that completion progress is preserved.

<table>
<tr>
    <td><img width="392" height="851" alt="image" src="https://github.com/user-attachments/assets/df25f950-94e8-4606-8243-d7ca14c1a74d" /></td>
    <td><img width="401" height="860" alt="image" src="https://github.com/user-attachments/assets/c0f602e7-9cf0-4d52-b6dc-e499a6868fbe" /></td>
  </tr>
</table>

## Exam Module
The Exams module allows students to manage their upcoming examinations. Users can add exam details such as subject, date, time, and venue. The saved exams are stored in the SQLite database and displayed in a scrollable RecyclerView.

Features
- Add Exam
- Select exam date using DatePickerDialog
- Select exam time using TimePickerDialog
- Store exam details using SQLite database
- Display all exams in a RecyclerView
- Automatically refresh the exam list when returning to the Exams screen

### Files Used

| File                    | Description                                   |
| ----------------------- | --------------------------------------------- |
| `Exam.kt`               | Data class representing exam details          |
| `AddExamActivity.kt`    | Handles adding new exam records               |
| `ExamActivity.kt`       | Displays the list of exams                    |
| `ExamAdapter.kt`        | Connects exam data with RecyclerView items    |
| `activity_add_exam.xml` | Layout for entering exam details              |
| `activity_exam.xml`     | Layout for the Exams screen                   |
| `exam_item.xml`         | Layout for individual exam cards              |
| `DatabaseHelper.kt`     | Creates and manages the exams table in SQLite |

### Exams Table Structure

| Field      | Description                     |
| ---------- | ------------------------------- |
| `id`       | Unique identifier for each exam |
| `subject`  | Name of the examination subject |
| `examDate` | Date of the examination         |
| `examTime` | Time of the examination         |
| `venue`    | Examination venue or classroom  |


### Add Exam
Allows students to add examination details including subject, date, time, and venue. Date and time can be selected using Android's DatePickerDialog and TimePickerDialog, and the data is stored locally in SQLite.

<table>
  <tr>
    <td><img width="382" height="870" alt="image" src="https://github.com/user-attachments/assets/fd207369-82cc-4fc7-91ff-65418b9a4b57" /></td>
    <td><img width="386" height="862" alt="image" src="https://github.com/user-attachments/assets/25375e6b-bce9-4ccb-ab5c-e809e623a6bc" /></td>
    <td><img width="393" height="872" alt="image" src="https://github.com/user-attachments/assets/048ef13c-50ec-40f5-b45b-464fa02ddef0" /></td>
    <td><img width="393" height="876" alt="image" src="https://github.com/user-attachments/assets/df8221dc-abb5-4085-8d9a-bfef374f8721" /></td>
  </tr>
</table>

## Current Progress

### Completed

- [x] Android Studio project setup
- [x] Home page
- [x] Subjects Activity
- [x] Navigation using Intents
- [x] Add Subject Activity
- [x] Subject Name & Subject Code input
- [x] Activity Result API for passing data
- [x] RecyclerView implementation
- [x] MaterialCardView subject cards
- [x] Add Subject functionality
- [x] Delete Subject functionality
- [x] ConstraintLayout-based UI
- [x] SQLite database integration
- [x] Persistent subject storage (Load on app restart)
- [x] Delete subject from SQLite database
- [x] Edit subject functionality
- [x] Tasks module
  - [x] Add Task
  - [x] Edit Task
  - [x] Delete Task
  - [x] SQLite database integration
  - [x] Add task completion Checkbox
- [x] Exams module
  - [x] Add Exam
  - [x] SQLlite database integration
  - [x] Display exams in RecyclerView
  - [x] DatePicker and TimePicker integration

### Upcoming Features

- [ ] Timetable module
- [ ] UI polishing
- [ ] Testing and bug fixes
