# 📚 Academics Planner 2026

> Plan smarter. Stay organized. Achieve more.

An Android-based academic planning application designed to help students
manage subjects, tasks, examinations, and timetables in one place.

![Platform](https://img.shields.io/badge/Platform-Android-green)
![Language](https://img.shields.io/badge/Language-Kotlin-purple)
![Database](https://img.shields.io/badge/Database-SQLite-blue)
![Status](https://img.shields.io/badge/Status-Completed-success)

---

## 🌟 Project Overview

Academics Planner 2026 is a student-focused Android application developed to
simplify academic planning and daily organization.

The application provides a centralized platform for managing:

- Subjects
- Academic tasks and assignments
- Upcoming examinations
- Weekly class timetables
- Task completion progress

The project was developed using Android Studio and Kotlin, with SQLite as the
local database for persistent data storage.

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| Kotlin | Android application development |
| Android Studio | Integrated Development Environment |
| XML | User interface design |
| ConstraintLayout | Responsive screen layouts |
| SQLite | Local persistent database |
| SQLiteOpenHelper | Database creation and management |
| RecyclerView | Displaying dynamic lists |
| Material Components | UI elements and cards |
| Activity Result API | Passing data between activities |
| AlarmManager | Scheduling task reminders |
| Notification API | Sending task notifications |

---

## 🗄️ Database Structure

The application uses SQLite for local data storage.

The database contains the following major tables:

| Table | Purpose |
|-------|---------|
| `subjects` | Stores subject names and subject codes |
| `tasks` | Stores academic tasks, deadlines, priorities, and completion status |
| `exams` | Stores examination details such as subject, date, time, and venue |
| `timetable` | Stores weekly class schedules |

All records are identified using unique auto-incrementing IDs.

The use of SQLite allows data to remain available even after closing and
reopening the application.

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
  </tr>
  <tr>
    <td><img width="393" height="872" alt="image" src="https://github.com/user-attachments/assets/048ef13c-50ec-40f5-b45b-464fa02ddef0" /></td>
    <td><img width="393" height="876" alt="image" src="https://github.com/user-attachments/assets/df8221dc-abb5-4085-8d9a-bfef374f8721" /></td>
  </tr>
</table>

## Delete Exam
The Exams screen displays all saved examinations using a RecyclerView. Each exam item contains the subject, date, time, and venue, along with a Delete button.

### Manual Deletion
When the Delete button is clicked, the selected exam is removed from the SQLite database using its unique ID. The RecyclerView is then refreshed to reflect the deletion.
<table>
  <tr>
    <td><img width="390" height="866" alt="image" src="https://github.com/user-attachments/assets/c5032639-2293-4f83-8738-468aa1b269d6" /></td>
    <td><img width="395" height="865" alt="image" src="https://github.com/user-attachments/assets/987f5fb8-e6e4-47d5-a5b0-c33f98711f07" /></td>
  </tr>
</table>

### Automatic Expired Exam Deletion
The application includes an automatic cleanup mechanism for expired examinations. Whenever the Exams screen is opened or resumed, the application compares each exam's scheduled date and time with the current date and time.

If an exam's scheduled time has passed, the record is permanently deleted from the SQLite database and will no longer appear in the RecyclerView.

<table>
  <tr>
    <td> At 11:29 pm </td>
    <td> At 11:30 pm </td>
  </tr>
  <tr>
    <td><img width="392" height="873" alt="image" src="https://github.com/user-attachments/assets/33bbe225-8933-4174-a284-7822192b4fbf" /></td>
    <td><img width="390" height="863" alt="image" src="https://github.com/user-attachments/assets/d885c926-dfbb-43e4-b5be-5e2bc4bd8fa2" /></td>
  </tr>
</table>

## Timetable Module
The Timetable module helps students organize their weekly class schedules. Users can add timetable entries by specifying the day, subject, start time, end time, and venue. All entries are stored locally using SQLite and displayed in an organized RecyclerView.

Features
Add new timetable entries.
Select a day from a predefined dropdown list.
Select start and end times using TimePickerDialog.
Enter subject and venue details.
Store timetable entries in SQLite.
Display timetable entries in a scrollable RecyclerView.
Automatically sort entries by day of the week and start time.
Delete individual timetable entries.
Validate that all required fields are completed.

### Add Class
The Add Timetable screen provides a form for entering weekly class schedule details. A dropdown menu is used for selecting the day, while time picker dialogs are used for selecting class timings. The entered information is saved to the SQLite database after validation.

| File                         | Description                                                                |
| ---------------------------- | -------------------------------------------------------------------------- |
| `AddTimetableActivity.kt`    | Handles timetable input, day selection, time selection, and saving records |
| `activity_add_timetable.xml` | User interface for adding timetable entries                                |
| `Timetable.kt`               | Data class representing timetable information                              |
| `DatabaseHelper.kt`          | Manages the SQLite timetable table                                         |

<table>
  <tr>
    <td><img width="391" height="868" alt="image" src="https://github.com/user-attachments/assets/dbfb5b67-4eb0-4f76-81a9-8b3067cf5a34" /></td>
    <td><img width="382" height="866" alt="image" src="https://github.com/user-attachments/assets/7ed3e25b-30ef-4a11-b33e-9d02cce67b83" /></td>
    <td><img width="392" height="872" alt="image" src="https://github.com/user-attachments/assets/f4e6787e-c146-4acf-bf8f-f8580708a1e7" /></td>
    <td><img width="383" height="870" alt="image" src="https://github.com/user-attachments/assets/db7b131b-68cf-45a3-852a-238578c9a088" /></td>
  </tr>
</table>

Classes are Arranged According to WEEKDAY
<table>
  <tr>
    <td><img width="383" height="870" alt="image" src="https://github.com/user-attachments/assets/db7b131b-68cf-45a3-852a-238578c9a088" /></td>
    <td><img width="386" height="867" alt="image" src="https://github.com/user-attachments/assets/18fce1d0-ac56-42d1-9ef3-49106d8fb407" /></td>
    <td><img width="385" height="871" alt="image" src="https://github.com/user-attachments/assets/79d384fd-0c7a-46a3-9939-8282a92a3a7e" /></td>
  </tr>
</table>

### Delete Class
The Timetable screen displays all saved classes in a RecyclerView. Each timetable item shows the day, subject, class timing, and venue. Users can delete individual timetable entries, which permanently removes the selected record from the SQLite database.

| File                     | Description                                           |
| ------------------------ | ----------------------------------------------------- |
| `TimetableActivity.kt`   | Loads, sorts, displays, and deletes timetable records |
| `TimetableAdapter.kt`    | Connects timetable data with RecyclerView items       |
| `activity_timetable.xml` | Layout for the Timetable screen                       |
| `timetable_item.xml`     | Layout for individual timetable cards                 |

<table>
  <tr>
    <td><img width="383" height="868" alt="image" src="https://github.com/user-attachments/assets/459894f2-afb9-437b-b2ea-08eb7724ac77" /></td>
    <td><img width="391" height="866" alt="image" src="https://github.com/user-attachments/assets/b377ad0c-0c08-45b3-8fbb-956f971d8fa0" /></td>
  </tr>
</table>

### Timetable Structure

| Field       | Description                                |
| ----------- | ------------------------------------------ |
| `id`        | Unique identifier for each timetable entry |
| `day`       | Day of the week                            |
| `subject`   | Name of the subject                        |
| `startTime` | Class starting time                        |
| `endTime`   | Class ending time                          |
| `venue`     | Classroom or venue                         |

SQLite Database
```text
CREATE TABLE timetable (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    day TEXT,
    subject TEXT,
    startTime TEXT,
    endTime TEXT,
    venue TEXT
);
```
z
## Updated UI with back button

<table>
  <tr>
    <td><img width="391" height="865" alt="image" src="https://github.com/user-attachments/assets/c9cb374c-7fbb-4b28-b556-0d369987efbb" /></td>
  </tr>
</table>

## Dashboard UI Improvements

The Home Page was transformed from a simple navigation screen into a
centralized academic dashboard.

The redesigned dashboard gives students an immediate overview of their
academic progress without requiring them to open individual modules.

It dynamically retrieves information from the SQLite database and presents
subjects, pending tasks, upcoming deadlines, examinations, and completion
progress in a single interface.

<table>
  <tr>
    <td><img width="388" height="867" alt="image" src="https://github.com/user-attachments/assets/08a0557b-c0eb-45dd-ae17-be026a71d074" /></td>
    <td><img width="391" height="862" alt="image" src="https://github.com/user-attachments/assets/59cb7336-fcaa-4f15-8d05-d7cb5b366263" /></td>
  </tr>
</table>

## Task Notifications

A notification feature was added to remind students about upcoming tasks. Users can select a due date and time while creating a task, and the application schedules a reminder using AlarmManager.

The notification system includes a BroadcastReceiver to trigger reminders, a notification channel for Android devices, and permission handling for Android 13 and above. Notifications display the task title and description and are cancelled automatically when the task is completed.

### Demonstration
<table>
  <tr>
    <td><img width="370" height="802" alt="Screenshot 2026-09-17 230653" src="https://github.com/user-attachments/assets/17916734-e461-44d2-9aa7-4b91d42fd9d5" /></td>
    <td><img width="392" height="866" alt="Screenshot 2026-09-17 230811" src="https://github.com/user-attachments/assets/df3fce38-8cf8-4900-b2da-8bcc19714667" /></td>
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
  - [x] SQLite database integration
  - [x] Display exams in RecyclerView
  - [x] DatePicker and TimePicker integration
  - [x] Delete Exam (Manually and Automatically on expiration)
  - [x] Add Timetable Entry
  - [x] Classes Arragement
  - [x] Delete Class
- [x]  UI polishing
- [x]  Testing and bug fixes

