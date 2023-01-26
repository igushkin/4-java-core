# 4-java-core

This is part of the Java Developer course from Yandex. Module - standard Java library and its features.

The repository contains my solution that has been verified from the reviewer to the task described below.

Technical specification
===================

Hurray! Practice! Let's continue working on the task tracker that you started doing in the last sprint. You will have to add new functionality to the tracker, as well as refactor the already written code, taking into account the studied principles of OOP.

### The manager is now an interface

From the topic of abstraction and polymorphism, you learned that when designing code, it is useful to separate the requirements for the desired functionality of objects and how this functionality is implemented. That is, it is better to put the set of methods that an object should have in the interface, and the implementation of these methods in the class that implements it. Now we need to apply this principle to the task manager.

1. The 'TaskManager` class should become an interface. In it, you need to collect a list of methods that any manager object should have. Auxiliary methods, if you created them, do not need to be transferred to the interface.
2. The manager class created earlier should be renamed to `InMemoryTaskManager'. The fact that the manager stores all the information in RAM is its main property that allows you to effectively manage tasks. The implementation of methods should remain inside the class. At the same time, it is important not to forget to implement `TaskManager`, because in Java the class must explicitly state that it fits the requirements of the interface.

### Task view history

Add new functionality to the program — you need the tracker to display the last tasks viewed by the user. To do this, add the `getHistory()` method to the `TaskManager` and implement it — it should return the last 10 viewed tasks. Viewing will be considered a call from the manager of the methods for getting the task by ID — `getTask(),` `getSubtask()` and `getEpic()'. There is no need to get rid of repeated views.

Example of creating a history of task views after calling the manager's methods:

![image](https://pictures.s3.yandex.net:443/resources/S3_23-2_1642680621.png)

The `getHistory()` method will have no parameters. This means that he forms his response by analyzing exclusively the internal state of the fields of the manager's object. Think about how and what data you will write to the manager's fields to be able to extract the history of visits from them. Since the history shows which tasks were accessed in the methods `getTask(),` `getSubtask()` and `getEpic()`, this data in the manager fields will be updated when these three methods are called.

Please note that any type of task can be viewed. That is, the returned task list can contain an object of one of three types at any position. To describe a cell of such a list, you need to remember about polymorphism and choose a type that is the common parent of both classes.

### Utility class

Over time, several implementations of the `TaskManager` interface will appear in the tracker application. In order not to depend on the implementation, create a utility class `Managers'. He will have all the responsibility for creating a task manager. That is, `Managers` must select the necessary implementation of `TaskManager' itself and return an object of the correct type.

`Managers' will have a `getDefault()` method. At the same time, the caller does not know the specific class, only that the object that returns `getDefault()` implements the `TaskManager` interface.

### Task statuses as enumeration

Since the options for possible statuses of the task are limited, it is better to have the enumerated type `enum` to store them in the program.

### Testing your solution

Make sure your solution works! In the main class, reproduce a simple user script:

* create multiple tasks of different types.
* call different methods of the `TaskManager` interface and print the browsing history after each call. If the code is working, the task browsing history will be displayed correctly.

### Make the task history an interface

In this sprint, the tracker's capabilities are limited — duplication is allowed in the browsing history and it can contain only ten tasks. In the next sprint, you will need to remove duplicates and expand its size. To prepare for this, refactor the code.

Create a separate interface for managing the browsing history — `HistoryManager'. He will have two methods. The first `add(Task task)` should mark tasks as viewed, and the second `getHistory()` should return a list of them.

Declare the `InMemoryHistoryManager` class and transfer part of the code for working with the history from the `InMemoryTaskManager` class to it. The new `InMemoryHistoryManager` class should implement the `HistoryManager` interface.

Add the static method `HistoryManager getDefaultHistory()` to the service class `Managers`. It should return the `InMemoryHistoryManager' object — the browsing history.

Check that `InMemoryTaskManager` now accesses the history manager via the `HistoryManager` interface and uses the implementation that the `getDefaultHistory()` method returns.

Test everything again!

Have fun programming!
