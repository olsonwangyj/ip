# Litchi User Guide

## Adding tasks
Users can add a task by entering the task description. The task will be stored in a list.

Example:
```
read book
```
Expected output:
```
____________________________________________________________
added: [ ] read book
____________________________________________________________
```

## Listing tasks
Users can view all added tasks by using the `list` command.

Example:
```
list
```
Expected output:
```
____________________________________________________________
Here are the tasks in your list:
1. [ ] read book
2. [ ] return book
3. [ ] buy bread
____________________________________________________________
```

## Marking a task as done
Users can mark a task as done by using the `mark` command followed by the task number.

Example:
```
mark 2
```
Expected output:
```
____________________________________________________________
Nice! I've marked this task as done:
  [X] return book
____________________________________________________________
```

## Unmarking a task
Users can mark a task as not done by using the `unmark` command followed by the task number.

Example:
```
unmark 2
```
Expected output:
```
____________________________________________________________
OK, I've marked this task as not done yet:
  [ ] return book
____________________________________________________________
```

## Exiting the program
Users can exit the program by typing `bye`.

Example:
```
bye
```
Expected output:
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

